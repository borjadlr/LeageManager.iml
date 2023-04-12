package Business.Entities;

public class User {
    private String dni;
    private String password;
    private String email;

    public User() {
    }

    public User(String user_name, String password, String email) {
        this.dni = user_name;
        this.password = password;
        this.email = email;
    }

    public User(String dni, String emails, String pass, int number, String phone) {
    }


    //Getters and Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

