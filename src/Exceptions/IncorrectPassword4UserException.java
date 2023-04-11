package Exceptions;

public class IncorrectPassword4UserException extends Exception {
    public IncorrectPassword4UserException() {
        super("Incorrect password for this username.\n");
    }
}
