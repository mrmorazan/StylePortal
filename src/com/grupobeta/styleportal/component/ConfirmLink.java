package com.grupobeta.styleportal.component;

import org.apache.wicket.markup.html.link.Link;

import com.grupobeta.wicket.ConfirmModifier;

public abstract class ConfirmLink extends Link<Void> {
	private static final long serialVersionUID = 1L;

	public ConfirmLink(String id, String verb, String noun) {
		super(id);
		add(new ConfirmModifier("Are you sure you want to " + verb + " the " + noun + "?"));
	}



}
