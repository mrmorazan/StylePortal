package com.grupobeta.styleportal.app;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;

import com.grupobeta.styleportal.StylePortalErrors;


public class ExpiredPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public ExpiredPage() {
		if (StylePortalSession.get().isSignedIn()) {
			StylePortalSession.get().warn(
					StylePortalErrors.PAGINA_EXPIRADA.getMessage());
			throw new RestartResponseException(getApplication().getHomePage());
		} else {
			StylePortalSession.get().warn(
					StylePortalErrors.SESION_EXPIRADA.getMessage());
			throw new RestartResponseException(LogInPage.class);
		}
	}

}
