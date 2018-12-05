package com.grupobeta.styleportal.app.mantenimiento;

import org.apache.wicket.markup.html.link.Link;

import com.grupobeta.styleportal.component.MenuPanel;


public class MantenimientoMenuPanel extends MenuPanel {
	private static final long serialVersionUID = 1L;
	

	public MantenimientoMenuPanel() {
		super();

				
		this.add(new Link<Void>("managementRoles") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(AdminRolPage.class);
			}
		});
		
		
		
	}
}
