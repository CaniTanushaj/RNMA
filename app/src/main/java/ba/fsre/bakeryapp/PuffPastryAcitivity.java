package ba.fsre.bakeryapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PuffPastryAcitivity extends AppCompatActivity{
     @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.puff_pastry_activity);

            // Dobavi referencu na ImageView
            ImageView imageViewPuffPastry = findViewById(R.id.imageViewPuffPastry);
            TextView textViewInProgress = findViewById(R.id.textViewInProgress);

            // Postavi sliku u ImageView
            imageViewPuffPastry.setImageResource(R.drawable.lisnato);
        }
    }
