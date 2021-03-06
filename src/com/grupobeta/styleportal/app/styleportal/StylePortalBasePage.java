package com.grupobeta.styleportal.app.styleportal;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.grupobeta.styleportal.app.BasePage;
import com.grupobeta.styleportal.app.HeaderPanel;
import com.grupobeta.styleportal.app.MenuMain;
import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.DomainObject;

public class StylePortalBasePage <T extends DomainObject>  extends BasePage {
	private static final long serialVersionUID = 1L;
	
	protected Action action= Action.VIEW;
	protected T selectedObject = null;

	public StylePortalBasePage (){
		super(new StylePortalMenuPanel(), new HeaderPanel(true), new MenuMain());
	}

	protected Action getAction() {
		return action;
	}

	protected void setAction(Action action) {
		this.action = action;
		if (action == Action.VIEW)
			setSelectedObject(null);
	}

	protected T getSelectedObject() {
		return selectedObject;
	}

	protected void setSelectedObject(T selectedObject) {
		this.selectedObject = selectedObject;
	}

	protected IModel<T> getSelectedObjectModel() {
		return new PropertyModel<T>(this, "selectedObject");
	}
}
