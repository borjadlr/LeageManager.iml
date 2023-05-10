package Exceptions;

public class TeamAlreadyExistsException extends Throwable {

    public TeamAlreadyExistsException(){
        super ("Existing team!");
    }
}
