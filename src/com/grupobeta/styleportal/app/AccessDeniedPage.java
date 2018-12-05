package com.grupobeta.styleportal.app;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;

import com.grupobeta.styleportal.StylePortalErrors;
import com.grupobeta.styleportal.domain.Language;

public class AccessDeniedPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public AccessDeniedPage(){
		if(StylePortalSession.get().getLocale().getLanguage()==Language.SPANISH) {
			StylePortalSession.get().warn(StylePortalErrors.ACCESO_DENEGADO.getMessage());
		} else {
			StylePortalSession.get().warn(StylePortalErrors.ACCESS_DENIED.getMessage());
		}
		
		throw new RestartResponseException(getApplication().getHomePage());
	}

}
