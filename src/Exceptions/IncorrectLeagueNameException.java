package Exceptions;

public class IncorrectLeagueNameException extends Throwable {

    public IncorrectLeagueNameException(){
        super ("Existing league! Please, change it.");
    }

}
