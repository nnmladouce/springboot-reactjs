package de.ladouce.customerapi.exception;

public class CustNotFoundException extends RuntimeException{
    public CustNotFoundException(Long id){
        super("could not find Cust "+ id);
    }
}
