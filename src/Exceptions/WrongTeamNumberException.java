package Exceptions;

public class WrongTeamNumberException extends Exception{

    public WrongTeamNumberException(){
        super ("This number of teams is not possible");
    }


}
