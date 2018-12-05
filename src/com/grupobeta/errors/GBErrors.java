package com.grupobeta.errors;

public enum GBErrors implements GBError {
	ENCRIPCION("GB-ENC-01", "Encryption Error.");

	private String code;
	private String message;

	private GBErrors(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public GBException newException() {
		return new GBException(this);
	}

	@Override
	public GBException newException(String optionalMessage) {
		return new GBException(this, optionalMessage);
	}

	@Override
	public void throwException() throws GBException {
		throw newException();
	}

	@Override
	public void throwException(String optionalMessage) throws GBException {
		throw newException(optionalMessage);
	}

	@Override
	public GBException newException(Throwable cause) {
		return new GBException(this, cause);
	}

	@Override
	public GBException newException(Throwable cause, String optionalMessage) {
		return new GBException(this, cause, optionalMessage);
	}

	@Override
	public void throwException(Throwable cause) throws GBException {
		throw newException(cause);
	}

	@Override
	public void throwException(Throwable cause, String optionalMessage) throws GBException {
		throw newException(cause, optionalMessage);
	}

	@Override
	public boolean equals(GBError error) {
		return getCode().equals(error.getCode());
	}

}
