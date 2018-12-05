package com.grupobeta.wicket;

import org.apache.wicket.model.IModel;

public class GBRequiredTextField extends GBTextField {
	private static final long serialVersionUID = 1L;

	public GBRequiredTextField(String id) {
		super(id, true);
	}

	public GBRequiredTextField(String id, IModel<String> model) {
		super(id, model, true);
	}

	public GBRequiredTextField(String id, int maximum) {
		super(id, true, maximum);
	}

	public GBRequiredTextField(String id, IModel<String> model, int maximum) {
		super(id, model, true, maximum);
	}

}
