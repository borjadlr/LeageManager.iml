package Exceptions;

public class InvalidPlayerNumberException extends Throwable {
    public InvalidPlayerNumberException(){
        super ("The player number has to be higher than zero!");
    }
}
