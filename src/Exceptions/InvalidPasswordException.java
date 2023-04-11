package Exceptions;

public class InvalidPasswordException extends Exception {

    public InvalidPasswordException(){
        super("Invalid password!\nPassword should have 8 characters or more as well as lowercase, uppercase and numeric characters.\nIt also allows other alphanumeric characters. \n");
    }

}

