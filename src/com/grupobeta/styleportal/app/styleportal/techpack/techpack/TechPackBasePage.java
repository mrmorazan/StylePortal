package com.grupobeta.styleportal.app.styleportal.techpack.techpack;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.grupobeta.styleportal.app.styleportal.techpack.TechPackLayoutBasePage;
import com.grupobeta.styleportal.app.styleportal.techpack.TechPackMenuPanel;
import com.grupobeta.styleportal.app.styleportal.techpack.TechPackRadialMenuPanel;
import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.DomainObject;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class TechPackBasePage <T extends DomainObject> extends TechPackLayoutBasePage {
	
private static final long serialVersionUID = 1L;
	
	protected Action action= Action.VIEW;
	protected T selectedObject = null;

	public TechPackBasePage (StylePolyPm style){
		super(new TechPackRadialMenuPanel(style), new TechPackMenuPanel(style, "TECHPACK"));
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