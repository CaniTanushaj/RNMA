package ba.fsre.bakeryapp;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.Objects;

public class AddCategory extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private Button addBtn;
    private ImageView uploadImage;
    private Uri imageUri;
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);


        EditText recipeName = findViewById(R.id.categoryName);
        EditText recipeDescription = findViewById(R.id.categoryDescription);
        Button addBtn = findViewById(R.id.btnInsertData);
        ImageView uploadImage = findViewById(R.id.uploadImg);

            //TREBA DORADIT JER CRESHA UPLOAD
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                         if(o.getResultCode()== Activity.RESULT_OK){
                             Intent data = o.getData();
                             imageUri = data.getData();
                             uploadImage.setImageURI(imageUri);

                         } else {
                             Toast.makeText(AddCategory.this,"Niste odabrali sliku",Toast.LENGTH_SHORT).show();
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
                String name = recipeName.getText().toString();
                String description = recipeDescription.getText().toString();


                if(name.isEmpty()){
                    recipeName.setError("Prazno polje");
                }
                if(description.isEmpty()){
                    recipeDescription.setError("Prazno polje");
                }
                if(imageUri == null){
                    Toast.makeText(AddCategory.this, "Unesi sliku", Toast.LENGTH_SHORT).show();
                }
                insertCategory(name,description,imageUri);
            }
        });
    }
    private void insertCategory(String name,String description,Uri imageUri){
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
                        Category category = new Category(id,name,description,uri.toString());
                        dbRef.child(id).setValue(category).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AddCategory.this,"Data inserted!",Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                });
            }
        });








        Intent i = new Intent(AddCategory.this,ProfileActivity.class);
        startActivity(i);
    }

    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }
}