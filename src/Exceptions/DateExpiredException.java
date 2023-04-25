package Exceptions;

public class DateExpiredException extends Exception{

    public DateExpiredException() {
        super("The due date has expired! :( \n");
    }

}
