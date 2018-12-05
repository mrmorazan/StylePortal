package com.grupobeta.wicket;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;

public class UpperCaseModifier extends AttributeAppender {
	private static final long serialVersionUID = 1L;

	public UpperCaseModifier() {
		super("style", new Model<String>("text-transform: uppercase"), ";");
	}
}
