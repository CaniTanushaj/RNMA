package ba.fsre.bakeryapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BagleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bagle_activity);

        // Dobavi referencu na ImageView
        ImageView imageViewBagle = findViewById(R.id.imageViewBagle);
        TextView textViewInProgress = findViewById(R.id.textViewInProgress);

        // Postavi sliku u ImageView
        imageViewBagle.setImageResource(R.drawable.pecivo);
    }
}
