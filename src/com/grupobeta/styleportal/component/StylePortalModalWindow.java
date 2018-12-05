package com.grupobeta.styleportal.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;

public abstract class StylePortalModalWindow extends ModalWindow {
	private static final long serialVersionUID = 1L;

	public StylePortalModalWindow(String id) {
		super(id);
		this.showUnloadConfirmation(true);
		
		this.setWindowClosedCallback(new WindowClosedCallback() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClose(AjaxRequestTarget target) {
				StylePortalModalWindow.this.onClose(target);
			}
		});
	}

	protected abstract void onClose(AjaxRequestTarget target);
}
