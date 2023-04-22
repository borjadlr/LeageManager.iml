package Exceptions;

public class DNIException extends Exception {

    public DNIException() {
        super("The DNI introduced does not exist.\n");
    }
}
