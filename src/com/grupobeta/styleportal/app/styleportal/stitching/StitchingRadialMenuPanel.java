package com.grupobeta.styleportal.app.styleportal.stitching;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.grupobeta.styleportal.app.styleportal.stitching.stitching.StitchingPage;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class StitchingRadialMenuPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public StitchingRadialMenuPanel(StylePolyPm style) {
		super("radialMenu");
		
		this.add(new Link<Void>("stitching") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(StitchingPage.class);
			}
		});
		
		
		
		
		
	}

}
