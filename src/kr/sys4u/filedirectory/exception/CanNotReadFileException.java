package kr.sys4u.filedirectory.exception;

public class CanNotReadFileException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CanNotReadFileException() {
		super();
	}

	public CanNotReadFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public CanNotReadFileException(String message) {
		super(message);
	}

	public CanNotReadFileException(Throwable cause) {
		super(cause);
	}
	
	
}
