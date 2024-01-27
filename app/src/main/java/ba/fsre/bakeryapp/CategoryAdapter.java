package ba.fsre.bakeryapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

// CategoryAdapter.java
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private ArrayList<Category> categoryList;
    private Context context;

    public CategoryAdapter(ArrayList<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Glide.with(context).load(categoryList.get(position).getImageURL()).into(holder.recyclerImage);
        holder.recyclerCaption.setText(categoryList.get(position).getName());

        // Set the item count based on the stored value in the Category object
        holder.itemCountEditText.setText(categoryList.get(position).getItemCount());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    // New method to get itemCount for a specific position
    public int getItemCountAtPosition(int position) {
        return Integer.parseInt(categoryList.get(position).getItemCount());
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerImage;
        TextView recyclerCaption;
        EditText itemCountEditText;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerImage = itemView.findViewById(R.id.recyclerImage);
            recyclerCaption = itemView.findViewById(R.id.recyclerCaption);
            itemCountEditText = itemView.findViewById(R.id.itemCount);

            // Add a TextWatcher to update the itemCount in the Category object when the user types
            itemCountEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // Update the itemCount for the corresponding Category object
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        categoryList.get(adapterPosition).setItemCount(editable.toString());
                    }
                }
            });
        }
    }
}
