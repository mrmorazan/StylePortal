package com.grupobeta.wicket.slimbox;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;

public class SlimboxBehavior extends AbstractAjaxBehavior {
	private static final long serialVersionUID = 1L;

	public SlimboxBehavior() {
		super();
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);
		response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(SlimboxBehavior.class, "js/jquery.js")));
		response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(SlimboxBehavior.class, "js/slimbox2.js")));
		response.render(CssHeaderItem.forReference(new PackageResourceReference(SlimboxBehavior.class, "css/slimbox2.css"),"screen"));
	}

	@Override
	public void onRequest() {
		
		
	}
}
