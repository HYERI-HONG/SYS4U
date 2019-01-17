package kr.sys4u.exception;

public class FailToReadStreamException extends RuntimeException{

	private static final long serialVersionUID = -7228334152686494484L;

	public FailToReadStreamException() {
		super();
	}

	public FailToReadStreamException(String message, Throwable cause) {
		super(message, cause);
	}

	public FailToReadStreamException(String message) {
		super(message);
	}

	public FailToReadStreamException(Throwable cause) {
		super(cause);
	}

}
