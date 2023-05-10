package Exceptions;

public class IncorrectTeamNameException extends Throwable {
    public IncorrectTeamNameException(){
        super ("Existing team! Please, change it.");
    }
}
