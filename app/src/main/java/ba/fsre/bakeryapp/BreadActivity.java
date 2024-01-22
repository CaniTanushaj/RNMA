package ba.fsre.bakeryapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bread_activity);

        // Dobavi referencu na ImageView
        ImageView imageViewBread = findViewById(R.id.imageViewBread);
        TextView textViewInProgress = findViewById(R.id.textViewInProgress);

        // Postavi sliku u ImageView
        imageViewBread.setImageResource(R.drawable.kruh);
    }
}
