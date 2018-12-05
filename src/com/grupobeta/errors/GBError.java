package com.grupobeta.errors;

public interface GBError {
	public String getCode();
	public String getMessage();
	public GBException newException();
	public GBException newException(Throwable cause);
	public GBException newException(Throwable cause, String optionalMessage);
	public GBException newException(String optionalMessage);
	public void throwException() throws GBException;
	public void throwException(Throwable cause) throws GBException;
	public void throwException(Throwable cause, String optionalMessage) throws GBException;
	public void throwException(String optionalMessage) throws GBException;
	public boolean equals(GBError error);
}
