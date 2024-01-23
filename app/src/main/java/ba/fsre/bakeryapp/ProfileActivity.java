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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser loggedUser;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recyclerView;
    MyReclyerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.auth = FirebaseAuth.getInstance();
        this.loggedUser = this.auth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav);
        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton addBtn = findViewById(R.id.addBtn);

        //NEMOJ BRISAT OVO NECE RADIT DUGME ZA CRUD KATEGORIJA
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, AddCategory.class);
                startActivity(i);
            }
        });

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
                    Toast.makeText(ProfileActivity.this, "Kategorije", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfileActivity.this, PastryActivity.class));
                }
                if (item.getItemId() == R.id.history) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(ProfileActivity.this, "Povijest", Toast.LENGTH_SHORT).show();
                    fragmentR(new HistoryFragment());
                }
                return true;
            }
        });

//        // Postavljanje RecyclerView-a
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Dohvat podataka iz Firebase baze
//        getDataFromFirebase();
//
//        // DUGME ZA DODAVANJE KATEGORIJA
//        FloatingActionButton add = findViewById(R.id.addBtn);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(ProfileActivity.this, AddCategory.class);
//                startActivity(i);
//            }
//        });

    }

    private void getDataFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("categories");

        FirebaseRecyclerOptions<Category> options =
                new FirebaseRecyclerOptions.Builder<Category>()
                        .setQuery(databaseReference, Category.class)
                        .build();

        adapter = new MyReclyerViewAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void fragmentR(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }
}
