package DAO;

public class NullDocumentException extends Exception {

    public NullDocumentException() { super(); }

    public NullDocumentException(String message) { super(message); }

    public NullDocumentException(Throwable cause) { super(cause); }

    public NullDocumentException(String message, Throwable cause) { super(message, cause); }

}
