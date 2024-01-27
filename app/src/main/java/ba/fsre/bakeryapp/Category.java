package ba.fsre.bakeryapp;

import android.widget.EditText;

public class Category {
    String id;
    String name;
    String weight;
    String category;
    String imageURL;
    private String  itemCount;


    public Category() {
    }

    public Category(String name, String weight, String id, String category, String imageURL) {
        this.name = name;
        this.weight = weight;
        this.id = id;
        this.imageURL = imageURL;
        this.category = category;;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public String getItemCount() {
        return itemCount;
    }
    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

}
