package com.grupobeta.styleportal.app.styleportal.art;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.grupobeta.styleportal.app.styleportal.art.art.ArtPage;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class ArtRadialMenuPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public ArtRadialMenuPanel(StylePolyPm style) {
		super("radialMenu");
		
		this.add(new Link<Void>("art") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ArtPage(style));
			}
		});
		
		
		
		
		
	}

}
