package com.grupobeta.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;


public class FocusOnLoadBehavior extends Behavior {
	private static final long serialVersionUID = 1L;
	protected Component component;

	public FocusOnLoadBehavior() { }

	@Override
	public void bind(Component component) {
		this.component = component;
		component.setOutputMarkupId(true);
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		this.component = component;
		component.setOutputMarkupId(true);
		super.renderHead(component, response);
		response.render(OnLoadHeaderItem.forScript("document.getElementById('" + component.getMarkupId() + "').focus();"));
	}
}
