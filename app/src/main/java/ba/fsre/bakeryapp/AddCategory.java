package ba.fsre.bakeryapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class AddCategory extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);


        EditText recipeName = findViewById(R.id.categoryName);
        EditText recipeDescription = findViewById(R.id.categoryDescription);
        Button addBtn = findViewById(R.id.btnInsertData);





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
                insertCategory(name,description);
            }
        });
    }
    private void insertCategory(String name,String description){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("categories");
        String id = dbRef.push().getKey();


        Category category = new Category(id,name,description);

        dbRef.child(id).setValue(category).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddCategory.this,"Data inserted!",Toast.LENGTH_SHORT).show();
            }

        });

        Intent i = new Intent(AddCategory.this,ProfileActivity.class);
        startActivity(i);
    }
}