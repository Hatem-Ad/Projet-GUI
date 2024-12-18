package Entite;


public class Challenge {
    private int id;
    private String name;
    private String description;
    private int categoryId; // Clé étrangère vers la table Categorie

    // Constructeur pour l'ajout
    public Challenge(String name, String description, int categoryId) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    // Constructeur pour la mise à jour et la suppression
    public Challenge(int id, String name, String description, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
