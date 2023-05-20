package Exceptions;

public class DNIOrMailDontExistException extends Exception {
    public DNIOrMailDontExistException() {
        super("The username introduced does not exist.\n");
    }

}
