package ba.fsre.bakeryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PastryActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pastry_activty);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView kruhImg = findViewById(R.id.kruhImg);
        ImageView lisnatoImg = findViewById(R.id.lisnatoImg);
        ImageView pecivoImg = findViewById(R.id.pecivoImg);
        ImageView pitaImg = findViewById(R.id.pitaImg);


        kruhImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                openBreadActivity();
            }
        });


        lisnatoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Kliknuli ste na drugi ImageView");
            }
        });

        pecivoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Kliknuli ste na treći ImageView");
            }
        });

        pitaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Kliknuli ste na četvrti ImageView");
            }
        });
    }

    private void openBreadActivity() {
        Intent intent = new Intent(this, BreadActivity.class);
        startActivity(intent);
    }


    // Pomoćna metoda za prikazivanje Toast poruke
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

