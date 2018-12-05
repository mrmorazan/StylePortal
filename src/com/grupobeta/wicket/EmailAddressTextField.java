package com.grupobeta.wicket;

import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

public class EmailAddressTextField extends GBTextField {
	private static final long serialVersionUID = 1L;

	public EmailAddressTextField(String id) {
		super(id);
		this.init();
	}

	public EmailAddressTextField(String id, IModel<String> model) {
		super(id, model);
		this.init();
	}

	public EmailAddressTextField(String id, int maximum) {
		super(id, maximum);
		this.init();
	}

	public EmailAddressTextField(String id, IModel<String> model, int maximum) {
		super(id, model, maximum);
		this.init();
	}

	public EmailAddressTextField(String id, boolean required) {
		super(id, required);
		this.init();
	}

	public EmailAddressTextField(String id, IModel<String> model,
			boolean required) {
		super(id, model, required);
		this.init();
	}

	public EmailAddressTextField(String id, boolean required, int maximum) {
		super(id, required, maximum);
		this.init();
	}

	public EmailAddressTextField(String id, IModel<String> model,
			boolean required, int maximum) {
		super(id, model, required, maximum);
		this.init();
	}

	public void init() {
		this.add(EmailAddressValidator.getInstance());
	}

}
