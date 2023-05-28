package Exceptions;

public class NumberOfTeamsDoNotRelateException extends Exception {

    public NumberOfTeamsDoNotRelateException() {
        super("The number of teams do not coincide with the teams you have selected. Please, choose it wisely. \n");
    }


}
