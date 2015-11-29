package biz.allrounder.jee7sample.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class ProjectApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public ProjectApplicationException() {
        super();
    }
    
    public ProjectApplicationException(String message) {
        super(message);
    }
    
    public ProjectApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ProjectApplicationException(Throwable cause) {
        super(cause);
    }

}
