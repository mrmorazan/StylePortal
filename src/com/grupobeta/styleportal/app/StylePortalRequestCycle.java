package com.grupobeta.styleportal.app;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.protocol.http.PageExpiredException;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import com.grupobeta.styleportal.domain.AuditInterceptor;


//@SuppressWarnings("deprecation")
public class StylePortalRequestCycle implements IRequestCycleListener {

	public StylePortalRequestCycle() {
//		super();
	}

	@Override
	public void onBeginRequest(RequestCycle cycle) {
		IRequestCycleListener.super.onBeginRequest(cycle);

		if (StylePortalSession.get().isSignedIn())
			AuditInterceptor.setCurrentUser(StylePortalSession.get().getCodUsuario());
	}

	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception ex) {
		if (ex instanceof PageExpiredException) {
			return new RenderPageRequestHandler(new PageProvider(LogInPage.class));
		}

		return new RenderPageRequestHandler(new PageProvider(new ErrorPage(ex)));
	}

	public static RequestCycle get() {
		return RequestCycle.get();

	}

	@Override
	public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler) {
		if (handler instanceof IPageRequestHandler) {
			IPageRequestHandler pageHandler = (IPageRequestHandler) handler;

			HttpServletRequest request = (HttpServletRequest) cycle.getRequest().getContainerRequest();

			// check whether the requested session has expired
			boolean expired = request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid();

			// check whether the requested page can be instantiated with the current session
			 boolean authorized = StylePortalSession.get().getAuthorizationStrategy().isInstantiationAuthorized(pageHandler.getPageClass());

			if (expired && !authorized) {
				throw new PageExpiredException("Session has expired!");
			}

		}

		IRequestCycleListener.super.onRequestHandlerResolved(cycle, handler);
	}

	@Override
	public void onEndRequest(RequestCycle cycle) {
	}

	@Override
	public void onDetach(RequestCycle cycle) {
	}

	@Override
	public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler) {
	}

	@Override
	public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler, Exception exception) {
	}

	@Override
	public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler) {
	}

	@Override
	public void onUrlMapped(RequestCycle cycle, IRequestHandler handler, Url url) {
	}

}
