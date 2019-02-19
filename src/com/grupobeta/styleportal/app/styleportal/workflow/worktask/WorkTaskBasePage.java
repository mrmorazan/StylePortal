package com.grupobeta.styleportal.app.styleportal.workflow.worktask;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.grupobeta.styleportal.app.styleportal.workflow.WorkFlowLayoutBasePage;
import com.grupobeta.styleportal.app.styleportal.workflow.WorkFlowMenuPanel;
import com.grupobeta.styleportal.app.styleportal.workflow.WorkFlowRadialMenuPanel;
import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.DomainObject;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class WorkTaskBasePage  <T extends DomainObject> extends WorkFlowLayoutBasePage {
	
private static final long serialVersionUID = 1L;
	
	protected Action action= Action.VIEW;
	protected T selectedObject = null;

	public WorkTaskBasePage (StylePolyPm style){
		super(new WorkFlowRadialMenuPanel(style), new WorkFlowMenuPanel(style, "WORKFLOW"));
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