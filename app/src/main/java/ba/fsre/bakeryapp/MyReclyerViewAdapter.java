package ba.fsre.bakeryapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MyReclyerViewAdapter extends FirebaseRecyclerAdapter<Category, MyReclyerViewAdapter.CategoryViewHolder> {

    public MyReclyerViewAdapter(@NonNull FirebaseRecyclerOptions<Category> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Category model) {
        holder.textViewName.setText(model.getName());
        holder.textViewDescription.setText(model.getDescription());

        // Prikaz slike koristeći Picasso biblioteku
        Picasso.get().load(model.image).into(holder.imageView);}

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_layout, parent, false);
        return new CategoryViewHolder(view);
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName, textViewDescription;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.uploadImg);
            this.textViewName = itemView.findViewById(R.id.categoryName);
            this.textViewDescription = itemView.findViewById(R.id.categoryDescription);
        }
    }
}
