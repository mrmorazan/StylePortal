package com.grupobeta.wicket;

import org.apache.wicket.AttributeModifier;

public class ConfirmModifier extends AttributeModifier {
	private static final long serialVersionUID = 1L;

	public ConfirmModifier(String mensaje) {
		super("onClick", "return confirm('" + mensaje + "')");
	}

}
