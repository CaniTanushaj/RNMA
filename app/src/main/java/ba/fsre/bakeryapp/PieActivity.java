package ba.fsre.bakeryapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class PieActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_activity);

        // Dobavi referencu na ImageView
        ImageView imageViewPie = findViewById(R.id.imageViewPie);
        TextView textViewInProgress = findViewById(R.id.textViewInProgress);

        // Postavi sliku u ImageView
        imageViewPie.setImageResource(R.drawable.pita);
    }
}


