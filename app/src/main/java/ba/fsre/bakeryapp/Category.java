package ba.fsre.bakeryapp;

public class Category {
    String id;
    String name;
    String description;

    public Category() {
    }

    public Category(String name, String description,String id) {
        this.name = name;
        this.description = description;
        this.id=id;
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
}
