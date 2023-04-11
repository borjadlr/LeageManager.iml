package Exceptions;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(){
        super("Invalid email!\nEmail should have an '@' character and a dot '.'. \n");
    }

}
