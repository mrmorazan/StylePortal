package com.grupobeta.styleportal.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.grupobeta.styleportal.domain.Language;
import com.grupobeta.wicket.CompoundPropertyForm;
import com.grupobeta.wicket.FocusOnLoadBehavior;
import com.grupobeta.wicket.GBPasswordTextField;
import com.grupobeta.wicket.GBRequiredTextField;
import com.grupobeta.wicket.GBSubmitButton;
import com.grupobeta.wicket.LoadableDetachableDropDownChoice;

public class LogInPage extends BasePage {
	private static final long serialVersionUID = 1L;

	protected String codUsuario;
	protected String password;
	protected String newPassword;
	protected String confirmPassword;

	Language language;

	public LogInPage() {
		super(new HomeMenuPanel());
		
		setLanguage(getAdminService().loadLanguagebyCode(Language.ENGLISH));
		
		WebMarkupContainer divContainer = new WebMarkupContainer("divContainer");
		divContainer.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		
		final Form<LogInPage> form = new CompoundPropertyForm<LogInPage>("form", this);
		form.add(new GBRequiredTextField("codUsuario", 50).add(new FocusOnLoadBehavior()));
		form.add(new GBPasswordTextField("password", true, 20));

		IModel<Language> languageModel = new PropertyModel<Language>(this, "language");
		form.add(new LoadableDetachableDropDownChoice<Language>("language", languageModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Language> loadChoices() {
				return new ArrayList<Language>(getAdminService().loadAllLanguage());
			}

		}.add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (getLanguage() != null && getLanguage().getLanguageCode().equals(Language.SPANISH)) {
					StylePortalSession.get().setLocale(new Locale("es"));
				} else {
					StylePortalSession.get().setLocale(Locale.ENGLISH);
				}
				
			}
		}));

		form.add(new GBSubmitButton("submit") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				try {
					if (StylePortalSession.get().signIn(getCodUsuario(), getPassword())) {
						setResponsePage(BaseApplication.get().getHomePage());
						// setResponsePage(QueuePage.class);
					} else {
						// error("User does not exist");
						target.add(getFeedbackPanel());
					}

				} catch (Exception e) {
					error("Error de conexion a base de datos, favor volver a ingresar");
					target.add(getFeedbackPanel());
				}
				
				
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
				target.add(getFeedbackPanel());
			}
			
		});

		divContainer.add(form);
		
		this.add(divContainer);

	}

	protected boolean isChangingPassword() {
		return ((StylePortalSession) StylePortalSession.get()).getCodUsuario() != null;
	}

	protected String getCodUsuario() {
		return codUsuario;
	}

	protected void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	protected String getNewPassword() {
		return newPassword;
	}

	protected void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	protected String getConfirmPassword() {
		return confirmPassword;
	}

	protected void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
