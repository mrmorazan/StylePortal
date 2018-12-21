package com.grupobeta.styleportal.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.grupobeta.styleportal.app.styleportal.StylesPolyPmPage;
import com.grupobeta.styleportal.domain.CustomerPolyPm;

public class CustomerPpmPanel extends Panel {
	private static final long serialVersionUID = 1L;
	Link<Void> link;

	public CustomerPpmPanel(String id, CustomerPolyPm customerPolyPm) {
		super(id);
		
		link = new Link<Void>("setCustomer") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new StylesPolyPmPage(customerPolyPm));
			}
			
		};
		
		link.add(new ContextImage("image", "img/noimage2.png"));
		link.add(new Label("companyNumber", customerPolyPm.getCompanyNumber()));
		link.add(new Label("companyName", customerPolyPm.getCompanyName()));
		link.add(new Label("portalCode", customerPolyPm.getPortalCode()));
		
		add(link);
		
	}
	

}
