package Exceptions;

public class RepeatedTeamException extends Throwable {

    public RepeatedTeamException(){
        super ("Existing team in this league! Please, change it.");
    }

}
