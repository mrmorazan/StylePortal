package com.grupobeta.styleportal.component;

public abstract class DeleteLink extends ConfirmLink {
	private static final long serialVersionUID = 1L;

	public DeleteLink(String id, String noun) {
		super(id, "delete", noun);
	}
}
