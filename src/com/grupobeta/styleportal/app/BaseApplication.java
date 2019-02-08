package com.grupobeta.styleportal.app;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.settings.ExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;




public class BaseApplication extends AuthenticatedWebApplication {
	protected IModel<String> titleModel;

	@Override
	protected void init() {
		super.init();

		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("socket.permit_connect", "true");
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		//getRequestCycleListeners().add(new StockManRequestCycle());

		IRequestCycleListener myListener = new StylePortalRequestCycle();
		getRequestCycleListeners().add(myListener);

	//	setRootRequestMapper(new CryptoMapper(getRootRequestMapper(),this));
		getDebugSettings().setAjaxDebugModeEnabled(false);


	//	getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);

		getSecuritySettings().setUnauthorizedComponentInstantiationListener(
				new IUnauthorizedComponentInstantiationListener(){

					@Override
					public void onUnauthorizedInstantiation(Component component) {
						 component.setResponsePage(AccessDeniedPage.class);
					}

		} );
		//getApplicationSettings().setPageExpiredErrorPage(LogInPage.class);
		getApplicationSettings().setPageExpiredErrorPage(ExpiredPage.class);
		//getApplicationSettings().setInternalErrorPage(ErrorPage.class);
		getExceptionSettings().setUnexpectedExceptionDisplay(ExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);


		titleModel = new StringResourceModel("application");

	}


	@Override
	protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
		return StylePortalSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LogInPage.class;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		if(StylePortalSession.get().isSignedIn()){
			return HomePage.class;
		} else {
			return LogInPage.class;
		}

	}

	public static String getHomePageUrl() {
		HttpServletRequest req = (HttpServletRequest)((WebRequest)StylePortalRequestCycle.get().getRequest()).getContainerRequest();
		return RequestUtils.toAbsolutePath(req.getRequestURL().toString(), RequestUtils.toAbsolutePath(StylePortalRequestCycle.get().getUrlRenderer().getBaseUrl().getHost(), (String) StylePortalRequestCycle.get().urlFor(new RenderPageRequestHandler(new PageProvider(LogInPage.class)))));
	}
	
	


	public String getTitle() {
		return titleModel.getObject();
	}

}
