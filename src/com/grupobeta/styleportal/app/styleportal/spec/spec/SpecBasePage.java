package com.grupobeta.styleportal.app.styleportal.spec.spec;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.grupobeta.styleportal.app.styleportal.spec.SpecLayoutBasePage;
import com.grupobeta.styleportal.app.styleportal.spec.SpecMenuPanel;
import com.grupobeta.styleportal.app.styleportal.spec.SpecRadialMenuPanel;
import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.DomainObject;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class SpecBasePage <T extends DomainObject> extends SpecLayoutBasePage {
	
private static final long serialVersionUID = 1L;
	
	protected Action action= Action.VIEW;
	protected T selectedObject = null;

	public SpecBasePage (StylePolyPm style){
		super(new SpecRadialMenuPanel(style), new SpecMenuPanel(style, "SPEC"));
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