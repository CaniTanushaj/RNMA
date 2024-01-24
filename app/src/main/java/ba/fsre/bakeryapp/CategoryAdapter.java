package ba.fsre.bakeryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private ArrayList<Category> categoryList;
    private Context context;

    public CategoryAdapter(ArrayList<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @androidx.annotation.NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull CategoryViewHolder holder, int position) {
        Glide.with(context).load(categoryList.get(position).getImageURL()).into(holder.recyclerImage);
        holder.recyclerCaption.setText(categoryList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return categoryList.size()  ;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerImage;
        TextView recyclerCaption;
        public CategoryViewHolder(@NonNull View itemView){
            super(itemView);
            recyclerImage = itemView.findViewById(R.id.recyclerImage);
            recyclerCaption = itemView.findViewById(R.id.recyclerCaption);


        }
    }
}
