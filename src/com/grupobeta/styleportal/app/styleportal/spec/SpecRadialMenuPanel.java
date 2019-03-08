package com.grupobeta.styleportal.app.styleportal.spec;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.grupobeta.styleportal.app.styleportal.spec.spec.SpecPage;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class SpecRadialMenuPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public SpecRadialMenuPanel(StylePolyPm style) {
		super("radialMenu");
		
		this.add(new Link<Void>("spec") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new SpecPage(style));
			}
		});
		
		
		
		
		
	}

}
