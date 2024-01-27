package ba.fsre.bakeryapp;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class BagleActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser loggedUser;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recyclerView;
    ArrayList<Category> categoryList;
    CategoryAdapter adapter;
    Button finalBtn;
    double totalResult;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("categories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bagle_activity);
        this.auth = FirebaseAuth.getInstance();
        this.loggedUser = this.auth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav);
        recyclerView = findViewById(R.id.recyclerView);
        finalBtn = findViewById(R.id.izracunajBtn);
        finalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalResult = 0;

                for (int i = 0; i < categoryList.size(); i++) {
                    View itemView = recyclerView.getChildAt(i);
                    EditText itemCountEditText = itemView.findViewById(R.id.itemCount);

                    // Get the multiplier from the EditText
                    String itemCountString = itemCountEditText.getText().toString();

                    // Check if itemCount is not empty
                    if (!TextUtils.isEmpty(itemCountString)) {

                        double itemCount = Double.parseDouble(itemCountString);

                        // Get the value of the "weight" attribute from each item
                        Double weight = Double.valueOf(categoryList.get(i).getWeight());


                        // Check if weight is not null
                        if (weight != null) {
                            // Perform the multiplication
                            double result = weight * itemCount;
                            totalResult += result;
                        } else {
                            // Handle the case where weight is null
                            Toast.makeText(BagleActivity.this, "Weight is null for an item", Toast.LENGTH_SHORT).show();
                            Log.e("BreadActivity", "Weight is null for item at position: " + i);
                        }
                    } else {
                        Toast.makeText(BagleActivity.this, "Please enter a valid item count for item at position: " + i, Toast.LENGTH_SHORT).show();
                    }
                }

                // Display the total in a single Toast after processing all items
                Toast.makeText(BagleActivity.this, "Total Result: " + totalResult, Toast.LENGTH_SHORT).show();
                Log.d("BreadActivity", "Total Result: " + totalResult);
            }
        });
//        FloatingActionButton addBtn = findViewById(R.id.addBtn);

        //NEMOJ BRISAT OVO NECE RADIT DUGME ZA CRUD KATEGORIJA
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(ProfileActivity.this, AddCategory.class);
//                startActivity(i);
//            }
//        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.categori) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(BagleActivity.this, "Kategorije", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BagleActivity.this, PastryActivity.class));
                }
                if (item.getItemId() == R.id.history) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(BagleActivity.this, "Povijest", Toast.LENGTH_SHORT).show();
                    fragmentR(new HistoryFragment());
                }
                return true;
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(categoryList,this);
        recyclerView.setAdapter(adapter);

        databaseReference.orderByChild("category").equalTo("Pecivo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoryList.clear(); // Clear existing data before adding filtered data
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Category categoryClass = dataSnapshot.getValue(Category.class);
                    categoryList.add(categoryClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void fragmentR(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}