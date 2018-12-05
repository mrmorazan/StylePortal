package com.grupobeta.styleportal.component;

public abstract class AjaxDeleteLink extends AjaxConfirmLink {
	private static final long serialVersionUID = 1L;

	public AjaxDeleteLink(String id, String noun) {
		super(id, "delete", noun);
	}
}
