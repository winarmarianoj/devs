package edu.labIV.exception;

public class PostException extends Exception{
    protected int idError;
    protected String error;

    public PostException() {
    }

    public PostException(String message) {
        super(message);
    }

    public String getError(){
        return this.error;
    }

    protected void setError(String message){
        this.error = message;
    }
}
