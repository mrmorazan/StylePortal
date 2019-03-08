package com.grupobeta.styleportal.app.styleportal;

import org.apache.wicket.markup.html.link.Link;

import com.grupobeta.styleportal.component.MenuPanel;

public class StylePortalMenuPanel extends MenuPanel {
	private static final long serialVersionUID = 1L;
	

	public StylePortalMenuPanel() {
		super();

				
		this.add(new Link<Void>("stylePortal") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(CustomersPage.class);
			}
		});
		
		this.add(new Link<Void>("packingConfig") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(PackingManualPage.class);
			}
		});
		
		
		
	}
}
