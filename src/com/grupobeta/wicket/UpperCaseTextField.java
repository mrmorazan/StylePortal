package com.grupobeta.wicket;

import org.apache.wicket.model.IModel;

public class UpperCaseTextField extends GBTextField {
	private static final long serialVersionUID = 1L;

	public UpperCaseTextField(String id, boolean required, int maximum) {
		super(id, required, maximum);
		this.init();
	}

	public UpperCaseTextField(String id, boolean required) {
		super(id, required);
		this.init();
	}

	public UpperCaseTextField(String id, IModel<String> model,
			boolean required, int maximum) {
		super(id, model, required, maximum);
		this.init();
	}

	public UpperCaseTextField(String id, IModel<String> model, boolean required) {
		super(id, model, required);
		this.init();
	}

	public UpperCaseTextField(String id, IModel<String> model, int maximum) {
		super(id, model, maximum);
		this.init();
	}

	public UpperCaseTextField(String id, IModel<String> model) {
		super(id, model);
		this.init();
	}

	public UpperCaseTextField(String id, int maximum) {
		super(id, maximum);
		this.init();
	}

	public UpperCaseTextField(String id) {
		super(id);
		this.init();
	}

	protected void init() {
		this.add(new UpperCaseModifier());
	}

	@Override
	public void updateModel() {
		String input = getConvertedInput();
		setModelObject(input == null ? null : input.toUpperCase());
	}
}
