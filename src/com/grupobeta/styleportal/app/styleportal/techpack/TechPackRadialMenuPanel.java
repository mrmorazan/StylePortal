package com.grupobeta.styleportal.app.styleportal.techpack;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.grupobeta.styleportal.app.styleportal.techpack.techpack.TechPackPage;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class TechPackRadialMenuPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public TechPackRadialMenuPanel(StylePolyPm style) {
		super("radialMenu");
		
		this.add(new Link<Void>("techpack") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new TechPackPage(style));
			}
		});
		
		
		
		
		
	}

}
