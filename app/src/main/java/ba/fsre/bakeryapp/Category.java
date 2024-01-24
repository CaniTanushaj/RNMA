package ba.fsre.bakeryapp;

public class Category {
    String id;
    String name;
    String description;
    String category;
    String imageURL;

    public Category() {
    }

    public Category(String name, String description,String id,String category,String imageURL) {
        this.name = name;
        this.description = description;
        this.id=id;
        this.imageURL=imageURL;
        this.category=category;
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

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
