package Business.Entities;

/**
 * Clase que representa el usuario que esta logeado
 */
public class User {
    private String dni;
    private String email;
    private String password;
    private int number;
    private String phone;

    public User(String name, String email, String password,int number, String phone) {
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.number = number;
        this.phone = phone;

    }

    public User () {
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getNumber() {return number;}

    public String getPhone() {return phone;}
}
