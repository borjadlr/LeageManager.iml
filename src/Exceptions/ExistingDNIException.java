package Exceptions;

public class ExistingDNIException extends Exception{

    public ExistingDNIException(){
        super ("Existing DNI! Please, change it.");
    }

}

