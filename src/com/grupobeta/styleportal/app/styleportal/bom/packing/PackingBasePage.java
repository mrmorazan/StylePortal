package com.grupobeta.styleportal.app.styleportal.bom.packing;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.grupobeta.styleportal.app.styleportal.bom.BOMLayoutBasePage;
import com.grupobeta.styleportal.app.styleportal.bom.BOMMenuPanel;
import com.grupobeta.styleportal.app.styleportal.bom.BOMRadialMenuPanel;
import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.DomainObject;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class PackingBasePage <T extends DomainObject> extends BOMLayoutBasePage {
	
private static final long serialVersionUID = 1L;
	
	protected Action action= Action.VIEW;
	protected T selectedObject = null;

	public PackingBasePage (StylePolyPm style){
		super(new BOMRadialMenuPanel(style), new BOMMenuPanel(style, "BOM"));
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
