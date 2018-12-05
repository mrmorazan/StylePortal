package com.grupobeta.styleportal.app;

import java.util.Calendar;

import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.grupobeta.styleportal.component.MenuPanel;
import com.grupobeta.styleportal.service.AdministracionService;
import com.grupobeta.styleportal.service.MantenimientoService;
import com.grupobeta.styleportal.service.TransaccionesService;

public abstract class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	protected MantenimientoService mantService;
	@SpringBean
	protected TransaccionesService transService;
	@SpringBean
	protected AdministracionService adminService;

	final protected FeedbackPanel feedbackPanel;


	protected BasePage(final MenuPanel menu) {
		this(menu, new HeaderPanel(), new MenuMain());
	}

	protected BasePage(final MenuPanel menu, final HeaderPanel header, final MenuMain menuMain) {
		feedbackPanel = new FeedbackPanel("feedback") {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return super.isVisible() && !getCurrentMessages().isEmpty();
			}
		};
		feedbackPanel.setOutputMarkupPlaceholderTag(true);
		feedbackPanel.setFilter((IFeedbackMessageFilter) new ErrorLevelFeedbackMessageFilter(FeedbackMessage.INFO));

		this.add(feedbackPanel);
		this.add(new Label("year",String.valueOf(Calendar.getInstance().get(Calendar.YEAR))));
		this.add(menu);
		this.add(header);
		this.add(menuMain);

		this.add(new Label("welcome", StylePortalSession.get().isSignedIn() ? "Hola, " + StylePortalSession.get().getCodUsuario() : "Bienvenido/a"));


	}

	public FeedbackPanel getFeedbackPanel() {
		return feedbackPanel;
	}

	protected MantenimientoService getMantService() {
		return mantService;
	}

	protected void setMantService(MantenimientoService mantService) {
		this.mantService = mantService;
	}

	protected TransaccionesService getTransService() {
		return transService;
	}

	protected void setTransService(TransaccionesService transService) {
		this.transService = transService;
	}

	protected AdministracionService getAdminService() {
		return adminService;
	}

	protected void setAdminService(AdministracionService adminService) {
		this.adminService = adminService;
	}

	public BaseApplication getBaseApplication() {
		return (BaseApplication) getApplication();
	}

}
