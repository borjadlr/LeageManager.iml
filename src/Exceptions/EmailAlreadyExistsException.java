package Exceptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(){
        super ("Existing email. You cannot have two accounts with the same email address.");
    }

}