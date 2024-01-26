package ba.fsre.bakeryapp;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

public class BreadActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser loggedUser;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recyclerView;
    ArrayList<Category> categoryList;
    CategoryAdapter adapter;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("categories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bread_activity);
        this.auth = FirebaseAuth.getInstance();
        this.loggedUser = this.auth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav);
        recyclerView = findViewById(R.id.recyclerView);
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
                    Toast.makeText(BreadActivity.this, "Kategorije", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BreadActivity.this, PastryActivity.class));
                }
                if (item.getItemId() == R.id.history) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(BreadActivity.this, "Povijest", Toast.LENGTH_SHORT).show();
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

        databaseReference.orderByChild("category").equalTo("Kruh").addListenerForSingleValueEvent(new ValueEventListener() {
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