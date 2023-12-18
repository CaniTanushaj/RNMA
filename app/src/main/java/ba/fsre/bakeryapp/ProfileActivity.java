package ba.fsre.bakeryapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;




public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.auth=FirebaseAuth.getInstance();


        this.loggedUser=this.auth.getCurrentUser();

    }
}
