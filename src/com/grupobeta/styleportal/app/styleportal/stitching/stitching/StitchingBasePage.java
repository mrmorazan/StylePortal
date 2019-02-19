package com.grupobeta.styleportal.app.styleportal.stitching.stitching;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.grupobeta.styleportal.app.styleportal.stitching.StitchingLayoutBasePage;
import com.grupobeta.styleportal.app.styleportal.stitching.StitchingMenuPanel;
import com.grupobeta.styleportal.app.styleportal.stitching.StitchingRadialMenuPanel;
import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.DomainObject;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class StitchingBasePage <T extends DomainObject> extends StitchingLayoutBasePage {
	
private static final long serialVersionUID = 1L;
	
	protected Action action= Action.VIEW;
	protected T selectedObject = null;

	public StitchingBasePage (StylePolyPm style){
		super(new StitchingRadialMenuPanel(style), new StitchingMenuPanel(style, "STITCHING"));
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