package com.grupobeta.errors;

public class GBException extends Exception {
	private static final long serialVersionUID = 1L;

	private String optionalMessage = null;
	private GBError error;

	public GBException(GBError error) {
		super(error.getMessage());
		this.error = error;
	}

	public GBException(GBError error, Throwable cause) {
		super(error.getMessage(), cause);
		this.error = error;
	}

	public GBException(GBError error, String optionalMessage) {
		this(error);
		this.optionalMessage = optionalMessage;
	}

	public GBException(GBError error, Throwable cause, String optionalMessage) {
		this(error, cause);
		this.optionalMessage = optionalMessage;
	}

	public GBError getError() {
		return error;
	}

	public String getOptionalMessage() {
		return this.optionalMessage;
	}

	@Override
	public String toString() {
		return getOptionalMessage() == null ? getMessage() : String.format("%s - %s", getMessage(), getOptionalMessage());
	}


}
