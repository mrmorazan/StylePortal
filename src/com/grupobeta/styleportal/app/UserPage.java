package com.grupobeta.styleportal.app;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;

import com.grupobeta.errors.GBException;
import com.grupobeta.styleportal.domain.Usuario;
import com.grupobeta.wicket.CompoundPropertyForm;
import com.grupobeta.wicket.EmailAddressTextField;
import com.grupobeta.wicket.FocusOnLoadBehavior;
import com.grupobeta.wicket.GBPasswordTextField;
import com.grupobeta.wicket.GBRequiredTextField;

import org.apache.wicket.markup.html.link.Link;

public class UserPage extends BasePage {
	private static final long serialVersionUID = 1L;

	protected String password;
	protected String newPassword;
	protected String confirmPassword;
	final protected boolean changingPassword;
	final protected Usuario usuario;

	public UserPage(boolean changingPassword) {
		super(new HomeMenuPanel());
		this.changingPassword = changingPassword;
		this.usuario = ((StylePortalSession) StylePortalSession.get()).getUsuario();

		final Form<Usuario> form = new CompoundPropertyForm <Usuario>("form", getUsuario()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				try {
					getAdminService().saveUsuario(getUsuario());
					setResponsePage(getApplication().getHomePage());
				} catch (GBException ex) {
					error(ex.toString());
				}
			}

			@Override
			public boolean isVisible() {
				return super.isVisible() && !isChangingPassword();
			}
		};
		form.add(new Label("codUsuario"));
		form.add(new Label("comment"));
		form.add(new GBRequiredTextField("nombre", 100).add(new FocusOnLoadBehavior()));
		form.add(new EmailAddressTextField("correoElectronico", 100));
		form.add(new Link<Void>("cancel"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(getApplication().getHomePage());
			}
		});
		this.add(form);
		this.add(form);

		final Form<UserPage> formPassword = new CompoundPropertyForm<UserPage>("formPassword", this) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				String codUsuario = ((StylePortalSession) StylePortalSession.get()).getCodUsuario();
				try {
					getAdminService().cambiarPasswordUsuario(codUsuario, getPassword(), getNewPassword(), getConfirmPassword());
					setResponsePage(getApplication().getHomePage());
				} catch (GBException ex) {
					error(ex.toString());
				}
			}

			@Override
			public boolean isVisible() {
				return super.isVisible() && isChangingPassword() && false;
			}
		};
		formPassword.add(new GBPasswordTextField("password", true, 20).add(new FocusOnLoadBehavior()));
		formPassword.add(new GBPasswordTextField("newPassword", true, 20));
		formPassword.add(new GBPasswordTextField("confirmPassword", true, 20));

		formPassword.add(new Link<Void>("cancel"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(getApplication().getHomePage());
			}
		});
		this.add(formPassword);
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

	protected boolean isChangingPassword() {
		return changingPassword;
	}

	protected Usuario getUsuario() {
		return usuario;
	}

}
