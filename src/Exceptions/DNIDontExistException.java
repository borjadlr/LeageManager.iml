package Exceptions;

public class DNIDontExistException extends Exception {
    public DNIDontExistException() {
        super("The username introduced does not exist.\n");
    }

}
