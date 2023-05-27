package Exceptions;

public class WrongTeamNumberException extends Throwable{

    public WrongTeamNumberException(){
        super ("This number of teams is not possible");
    }


}
