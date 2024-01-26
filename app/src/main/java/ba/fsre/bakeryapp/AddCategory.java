package ba.fsre.bakeryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddCategory extends AppCompatActivity {

    String[] item = {"Kruh","Pecivo","Lisnato","Burek"};

    AutoCompleteTextView autoCompele;
    public String category;

    ArrayAdapter<String> adapterItem;
    private EditText name;
    private EditText weight;
    private Button addBtn;
    private ImageView uploadImage;
    private Uri imageUri;
    private ImageButton btnBack;
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        name = findViewById(R.id.categoryName);
        weight = findViewById(R.id.categoryWeight);
        addBtn = findViewById(R.id.btnInsertData);
        uploadImage = findViewById(R.id.uploadImg);
        btnBack = findViewById(R.id.btnBack);
        autoCompele = findViewById(R.id.auto_complete_txt);
        adapterItem = new ArrayAdapter<String>(this,R.layout.list_item,item);

        autoCompele.setAdapter(adapterItem);

        autoCompele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     category = parent.getItemAtPosition(position).toString();

            }
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK) {
                            Intent data = o.getData();
                            imageUri = data.getData();
                            uploadImage.setImageURI(imageUri);

                        } else {
                            Toast.makeText(AddCategory.this, "Niste odabrali sliku", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = name.getText().toString();
                String categoryWeight = weight.getText().toString();

                if (categoryName.isEmpty()) {
                    name.setError("Prazno polje");
                    return;
                }
                if (categoryWeight.isEmpty()) {
                    weight.setError("Prazno polje");
                    return;
                }
                if (imageUri == null) {
                    Toast.makeText(AddCategory.this, "Unesi sliku", Toast.LENGTH_SHORT).show();
                    return;
                }

                insertCategory(categoryName, categoryWeight,category, imageUri);
            }
        });

        // Dodani dio za gumb za povratak <-//imagebuton a ne button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void insertCategory(String name, String weight,String cat, Uri imageUri) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("categories");
        StorageReference imageRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
        String id = dbRef.push().getKey();

        imageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Category category = new Category(name, weight, id,cat, uri.toString());
                        dbRef.child(id).setValue(category).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AddCategory.this, "Data inserted!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        Intent i = new Intent(AddCategory.this, ProfileActivity.class);
        startActivity(i);
    }

    private String getFileExtension(Uri fileUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }
}
