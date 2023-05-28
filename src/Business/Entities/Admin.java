package Business.Entities;

/**
 * This class is about the entity of the User when is an Admin
 */
public class Admin extends User {

    private String name;

    /**
     * Getter for the name of the admin
     * @return Name of admin
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the name of the admin
     * @param name Name of admin
     */
    public void setName(String name) {
        this.name = name;
    }
}
