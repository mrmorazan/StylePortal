package com.grupobeta.styleportal.app;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.protocol.http.PageExpiredException;


public class ErrorPage extends BasePage {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ErrorPage.class);

	public ErrorPage(Exception ex) {
		super(new HomeMenuPanel());


	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	ex.printStackTrace(pw);

	if((!(ex instanceof UnauthorizedInstantiationException)) && (!(ex instanceof PageExpiredException))){
		logger.error("Error message", ex);
	}

	this.add(new Label("message", ex.getMessage()));
	final Label error = new Label("error", sw.toString());
	error.setOutputMarkupPlaceholderTag(true);
	error.setVisible(false);
	this.add(error);
	pw.close();

	this.add(new AjaxFallbackLink<Void>("showError"){
		private static final long serialVersionUID = 1L;

		@Override
		public void onClick(Optional<AjaxRequestTarget> target) {
			error.setVisible(true);
			target.get().add(error);
		}
	});
}
}
