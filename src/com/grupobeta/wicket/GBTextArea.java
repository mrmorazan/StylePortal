package com.grupobeta.wicket;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;

public class GBTextArea extends TextArea<String> {
	private static final long serialVersionUID = 1L;

	public GBTextArea(String id) {
		this(id, false, 0);
	}

	public GBTextArea(String id, IModel<String> model) {
		this(id, model, false, 0);
	}

	public GBTextArea(String id, int maximum) {
		this(id, false, maximum);
	}

	public GBTextArea(String id, IModel<String> model, int maximum) {
		this(id, model, false, maximum);
	}

	public GBTextArea(String id, boolean required) {
		this(id, required, 0);
	}

	public GBTextArea(String id, IModel<String> model, boolean required) {
		this(id, model, required, 0);
	}

	public GBTextArea(String id, boolean required, int maximum) {
		super(id);
		init(required, maximum);
	}

	public GBTextArea(String id, IModel<String> model, boolean required, int maximum) {
		super(id, model);
		init(required, maximum);
	}

	protected void init(boolean required, int maximum) {
		this.setRequired(required);
		if (maximum > 0)
			this.add(StringValidator.maximumLength(maximum));
	}
}
