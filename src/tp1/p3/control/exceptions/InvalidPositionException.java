package tp1.p3.control.exceptions;

public class InvalidPositionException extends CommandExecuteException {

	private static final long serialVersionUID = 1L;
	
	public InvalidPositionException() {
		super();
	}
	
	public InvalidPositionException(String message) {
		super(message);
	}
	
	public InvalidPositionException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public InvalidPositionException(Throwable cause) {
        super(cause);
    }
	
	protected InvalidPositionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}