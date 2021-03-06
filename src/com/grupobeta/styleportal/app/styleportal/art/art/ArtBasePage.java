package com.grupobeta.styleportal.app.styleportal.art.art;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.grupobeta.styleportal.app.styleportal.art.ArtLayoutBasePage;
import com.grupobeta.styleportal.app.styleportal.art.ArtMenuPanel;
import com.grupobeta.styleportal.app.styleportal.art.ArtRadialMenuPanel;
import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.DomainObject;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class ArtBasePage <T extends DomainObject> extends ArtLayoutBasePage {
	
private static final long serialVersionUID = 1L;
	
	protected Action action= Action.VIEW;
	protected T selectedObject = null;

	public ArtBasePage (StylePolyPm style){
		super(new ArtRadialMenuPanel(style), new ArtMenuPanel(style, "ART"));
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