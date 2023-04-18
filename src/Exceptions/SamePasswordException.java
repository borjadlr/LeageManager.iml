package Exceptions;

public class SamePasswordException extends Exception {
    public SamePasswordException(){
        super ("Passwords are not equal, please, check it.\n");
    }
}
