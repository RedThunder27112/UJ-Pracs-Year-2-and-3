/**
 * An exception that will be thrown by the Matrix class.
 * 
 * This is a checked exception and must be handled.
 * @author Ian Ellefsen
 */
public class MatrixException extends Exception {
    public MatrixException() {
        super();
    }
    
    public MatrixException(String message) {
        super(message);
    }
    
    public MatrixException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MatrixException(Throwable cause) {
        super(cause);
    } 
}
