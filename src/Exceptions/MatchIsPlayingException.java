package Exceptions;

public class MatchIsPlayingException extends Throwable {


    public MatchIsPlayingException(){
        super ("Cannot delete team while match is in progress");
    }


}
