package com.grupobeta.styleportal;

import com.grupobeta.errors.GBError;
import com.grupobeta.errors.GBException;

public enum StylePortalErrors implements GBError {
	SESION_EXPIRADA("QRT-EXP-02", "Your session has expired."),
	PAGINA_EXPIRADA("QRT-EXP-01", "The page has expired."),
	PASSWORD_NO_COINCIDE("QRT-AUT-04", "The password confirmation does not match your new password."),
	USUARIO_BLOQUEADO("QRT-AUT-03", "Your user is blocked. Contact the system administrator."),
	PASSWORD_INCORRECTO("QRT-AUT-02", "User / Password Incorrect."),
	USUARIO_NO_EXISTE("QRT-AUT-01", "User Incorrect."),
	ACCESO_DENEGADO("QRT-AUT-00", "No tiene autorizado el acceso a esta pagina. Favor contactar con el administrador del sistema."),
	ACCESS_DENIED("QRT-AUT-00-EN", "Not authorized to access this page. Contact the system administrator."),
	FORMATO_INVALIDO_ARCHIVO("QRT-FIL-01", "Invalid File Format."),
	ARCHIVO_NO_ENCONTRADO("QRT-FIL-02", "File Not Found."),
	FORMATO_INVALIDO_CELDA("QRT-FIL-03", "Cell is not valid Format.");

	private String code;
	private String message;

	private StylePortalErrors (String code, String message){
		this.code=code;
		this.message=message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public GBException newException() {
		return new GBException(this);
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
	public GBException newException(String optionalMessage) {
		return new GBException(this, optionalMessage);
	}

	@Override
	public void throwException() throws GBException {
		throw newException();
	}

	@Override
	public void throwException(Throwable cause) throws GBException {
		throw newException(cause);

	}

	@Override
	public void throwException(Throwable cause, String optionalMessage)
			throws GBException {
		throw newException(cause, optionalMessage);

	}

	@Override
	public void throwException(String optionalMessage) throws GBException {
		throw newException(optionalMessage);

	}

	@Override
	public boolean equals(GBError error) {
		return getCode().equals(error.getCode());
	}


}
