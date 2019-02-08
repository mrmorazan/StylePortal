package com.grupobeta.styleportal.app;

import org.apache.wicket.markup.html.link.Link;

import com.grupobeta.styleportal.app.styleportal.CustomersPage;

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;

	public HomePage() {
		super(new HomeMenuPanel());
		
		this.add(new Link<Void>("stylePortal") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(CustomersPage.class);
			}
		});
	
	}
}
