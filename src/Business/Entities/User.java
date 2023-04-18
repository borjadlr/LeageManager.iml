package Business.Entities;

public class User {
    private String dni;
    private String password;
    private String email;

    private int number;

    private String phone;

    public User() {
    }

    public User(String user_name, String password, String email) {
        this.dni = user_name;
        this.password = password;
        this.email = email;
    }

    public User(String dni, String email, String pass, int number, String phone) {
        this.email = email;
        this.dni = dni;
        this.password = pass;
        this.number = number;
        this.phone = phone;
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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNumber() {
        return number;
    }

    public String getPhone() {
        return phone;
    }
}

