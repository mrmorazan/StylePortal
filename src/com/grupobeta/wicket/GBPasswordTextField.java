package com.grupobeta.wicket;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;

public class GBPasswordTextField extends PasswordTextField {
	private static final long serialVersionUID = 1L;

	public GBPasswordTextField(String id) {
		this(id, 0);
	}

	public GBPasswordTextField(String id, IModel<String> model) {
		this(id, model, 0);
	}

	public GBPasswordTextField(String id, int maximum) {
		super(id);
		this.init(false, maximum);
	}

	public GBPasswordTextField(String id, IModel<String> model, int maximum) {
		super(id, model);
		this.init(false, maximum);
	}

	public GBPasswordTextField(String id, boolean required) {
		super(id);
		this.init(required, 0);
	}

	public GBPasswordTextField(String id, IModel<String> model, boolean required) {
		super(id, model);
		this.init(required, 0);
	}

	public GBPasswordTextField(String id, boolean required, int maximum) {
		super(id);
		this.init(required, maximum);
	}

	public GBPasswordTextField(String id, IModel<String> model, boolean required, int maximum) {
		super(id, model);
		this.init(required, maximum);
	}

	protected void init(boolean required, int maximum) {
		this.setRequired(required);
		if (maximum > 0)
			this.add(StringValidator.maximumLength(maximum));
	}

}
