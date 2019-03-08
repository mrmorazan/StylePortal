package com.grupobeta.styleportal.app.styleportal.bom;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.grupobeta.styleportal.app.styleportal.bom.fabric.FabricPage;
import com.grupobeta.styleportal.app.styleportal.bom.packing.PackingPage;
import com.grupobeta.styleportal.app.styleportal.bom.supplies.SuppliesPage;
import com.grupobeta.styleportal.app.styleportal.bom.thread.ThreadPage;
import com.grupobeta.styleportal.app.styleportal.bom.trim.TrimPage;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class BOMRadialMenuPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public BOMRadialMenuPanel(StylePolyPm style) {
		super("radialMenu");
		
		this.add(new Link<Void>("fabric") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new FabricPage(style));
				
			}
		});
		
		this.add(new Link<Void>("thread") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ThreadPage(style));
				
			}
		});
		
		this.add(new Link<Void>("trim") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new TrimPage(style));
				
			}
		});
		
		this.add(new Link<Void>("supplies") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new SuppliesPage(style));
				
			}
		});
		
		this.add(new Link<Void>("packing") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new PackingPage(style));
				
			}
		});
		
	}

}
