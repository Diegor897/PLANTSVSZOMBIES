package tp1.p3.control.exceptions;

public class RecordException extends CommandExecuteException{

	private static final long serialVersionUID = 1L;

    public RecordException() {
        super();
    }

    public RecordException(String message) {
        super(message);
    }

    public RecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordException(Throwable cause) {
        super(cause);
    }

    protected RecordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}
