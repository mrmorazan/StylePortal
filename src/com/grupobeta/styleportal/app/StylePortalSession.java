package com.grupobeta.styleportal.app;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.grupobeta.errors.GBException;
import com.grupobeta.styleportal.StylePortalErrors;
import com.grupobeta.styleportal.domain.AuditInterceptor;
import com.grupobeta.styleportal.domain.Rol;
import com.grupobeta.styleportal.domain.Usuario;
import com.grupobeta.styleportal.service.AdministracionService;


public class StylePortalSession extends AuthenticatedWebSession {
	private static final long serialVersionUID = 1L;

	@SpringBean
	protected AdministracionService adminService;

	
	protected String codUsuario = null;
	protected String password = null;
	protected String codUsuarioError = null;
	protected int numErrores = 0;
	protected RuntimeException lastException;
	protected int defaultTimeout = 0;


	public StylePortalSession(Request request) {
		super(request);
		Injector.get().inject(this);
		setLocale(Locale.US);
	}

	protected boolean revisarPasswordIncorrecto(String codUsuario) {
		if (codUsuario.equals(getCodUsuarioError())) {
			if (incrementNumErrores() >= 3) {
				try {
					getAdminService().activarInactivarUsuario(codUsuario);
					setNumErrores(0);
					return true;
				} catch (GBException ex) {
				}
			}
		} else {
			setCodUsuarioError(codUsuario);
			setNumErrores(1);
		}

		return false;
	}

	@Override
	public boolean authenticate(String username, String password) {
		setCodUsuario(null);
		boolean flag = false;
		if (!username.equals("admin")) {
			try {
				flag = getAdminService().authenticateDA(username, password);
			} catch (GBException ex) {
				ex = StylePortalErrors.PASSWORD_INCORRECTO.newException();
				error(ex.toString());
			}

			if (flag) {
				setCodUsuario(username);
				setPassword(password);
				setTimeoutUser();

			} else {
				GBException ex = StylePortalErrors.PASSWORD_INCORRECTO.newException();
				error(ex.toString());
			}
		} else {
			try {
				getAdminService().authenticate(username, password);
				setCodUsuario(username);
				flag = !password.equals(Usuario.DEFAULT_PASSWORD);
			} catch (GBException ex) {
				if (ex.getError().equals(StylePortalErrors.PASSWORD_INCORRECTO))
					if (revisarPasswordIncorrecto(username))
						ex = StylePortalErrors.USUARIO_BLOQUEADO.newException();
				error(ex.toString());
			}
		}

		return flag;
	}

	@Override
	public Roles getRoles() {
		Roles roles = new Roles();
		Usuario usuario = getUsuario();
		if (usuario != null) {
			roles.addAll(usuario.getRolesAsStringList());
		} else {
			roles.add(Rol.USUARIO);
		}
		return roles;
	}

	@Override
	public void signOut() {
		super.signOut();
		setCodUsuario(null);
		AuditInterceptor.destroy();
		setTimeout(getDefaultTimeout());
	}

	public void setTimeoutUser() {
		initDefaultTimeout();
		Usuario usuario = this.getCodUsuario() == null ? null
				: getAdminService().loadByCodUsuarioActivo(getCodUsuario());

		if (usuario != null)
			setTimeout(usuario.getTimeout());
	}

	public static StylePortalSession get() {
		return (StylePortalSession) AuthenticatedWebSession.get();
	}

	protected void initDefaultTimeout() {
		if (getDefaultTimeout() == 0)
			this.defaultTimeout = getTimeout();
	}

	public int getTimeout() {
		return getHttpSession().getMaxInactiveInterval();
	}

	public static HttpSession getHttpSession() {
		HttpSession  session = ((ServletWebRequest) RequestCycle.get().getRequest())
				.getContainerRequest().getSession();
		
	/*	session.setAttribute("Locale", "ENGLISH");
		session.setAttribute("userName", "abc");*/
		
		return session;
	}

	public void setTimeout(int timeout) {
		if (timeout > 0){
			getHttpSession().setMaxInactiveInterval(timeout);
		}
	}

	public AdministracionService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdministracionService adminService) {
		this.adminService = adminService;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getCodUsuarioError() {
		return codUsuarioError;
	}

	public void setCodUsuarioError(String codUsuarioError) {
		this.codUsuarioError = codUsuarioError;
	}

	public int getNumErrores() {
		return numErrores;
	}

	public void setNumErrores(int numErrores) {
		this.numErrores = numErrores;
	}

	public RuntimeException getLastException() {
		return lastException;
	}

	public void setLastException(RuntimeException lastException) {
		this.lastException = lastException;
	}

	public int getDefaultTimeout() {
		return defaultTimeout;
	}

	public void setDefaultTimeout(int defaultTimeout) {
		this.defaultTimeout = defaultTimeout;
	}

	protected int incrementNumErrores() {
		return ++this.numErrores;
	}

	public Usuario getUsuario() {
		return isSignedIn() ? getAdminService().loadByCodUsuarioActivo(
				this.getCodUsuario()) : null;
	}

	public byte[] getFotoBase64(){
		byte[] foto=null;

		/*try {
			//foto = getWebServiceClient().getImageLoginBase64(getCodUsuario());
		} catch (GBMultipleException e) {

		}*/

		return foto;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	


	


}
