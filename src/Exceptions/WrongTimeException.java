package Exceptions;

public class WrongTimeException extends Exception{

    public WrongTimeException(){
        super ("The time is incorrect, please, introduce it like this -> HH:MM:SS.");
    }

}
