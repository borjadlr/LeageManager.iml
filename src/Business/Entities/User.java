package Business.Entities;

/**
 * The `User` class represents a logged-in user.
 */
public class User {
    private String dni;
    private String email;
    private String password;
    private int number;
    private String phone;

    /**
     * Constructs a new `User` object with the specified attributes.
     *
     * @param dni      The DNI (identification) of the user.
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @param number   The number associated with the user.
     * @param phone    The phone number of the user.
     */
    public User(String dni, String email, String password, int number, String phone) {
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.number = number;
        this.phone = phone;
    }

    /**
     * Constructs a new empty `User` object.
     */
    public User() {
    }

    /**
     * Returns the DNI (identification) of the user.
     * @return The DNI of the user.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Returns the email address of the user.
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password of the user.
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the number associated with the user.
     * @return The number associated with the user.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the phone number of the user.
     * @return The phone number of the user.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the user.
     * @param phone The phone number of the user.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the number associated with the user.
     * @param number The number associated with the user.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Sets the DNI (identification) of the user.
     * @param dni The DNI of the user.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Sets the email address of the user.
     * @param email The email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password of the user.
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}