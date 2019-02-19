package com.grupobeta.styleportal.app.styleportal.workflow;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.grupobeta.styleportal.app.styleportal.workflow.worktask.WorkTaskPage;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class WorkFlowRadialMenuPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public WorkFlowRadialMenuPanel(StylePolyPm style) {
		super("radialMenu");
		
		this.add(new Link<Void>("workflow") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new WorkTaskPage(style));
				
			}
		});
		
		
		
	}

}
