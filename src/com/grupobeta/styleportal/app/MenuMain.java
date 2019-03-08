package com.grupobeta.styleportal.app;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.grupobeta.styleportal.app.admin.DictionaryPage;
import com.grupobeta.styleportal.app.admin.RolesPage;
import com.grupobeta.styleportal.app.admin.UsersPage;
import com.grupobeta.styleportal.app.mantenimiento.AdminRolPage;
import com.grupobeta.styleportal.app.styleportal.CustomersPage;
import com.grupobeta.styleportal.app.styleportal.PackingManualPage;

public class MenuMain extends Panel {
	private static final long serialVersionUID = 1L;

	public MenuMain() {
		super("menuMain");

		this.add(new Label("userName", StylePortalSession.get().getUsuario()!=null ? StylePortalSession.get().getUsuario().getCodUsuario() : StylePortalSession.get().isSignedIn() ? StylePortalSession.get().getCodUsuario() : "Bienvenido" ));

		this.add(new AjaxLink<Void>("home") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(HomePage.class);// home vacio 
				//setResponsePage(QueuePage.class);
				
			}
		});
		
		this.add(new Link<Void>("adminUsers") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(UsersPage.class);
			}
		});

		this.add(new Link<Void>("adminGroups") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(RolesPage.class);
			}
		});
		
		this.add(new Link<Void>("adminDictionary") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(DictionaryPage.class);
			}
		});
		
		this.add(new Link<Void>("managementRoles") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(AdminRolPage.class);
			}
		});
		
		this.add(new Link<Void>("stylePortal") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(CustomersPage.class);
			}
		});
		
		this.add(new Link<Void>("packingManual") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(PackingManualPage.class);
			}
		});


	}
}
