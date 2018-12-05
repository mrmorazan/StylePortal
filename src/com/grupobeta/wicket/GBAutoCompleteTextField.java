package com.grupobeta.wicket;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.model.IModel;

public abstract class GBAutoCompleteTextField<T extends Serializable> extends AutoCompleteTextField<T> {
	private static final long serialVersionUID = 1L;

	public GBAutoCompleteTextField(String id) {
		this(id, false);
	}

	public GBAutoCompleteTextField(String id, IModel<T> object) {
		this(id, object, false);
	}

	public GBAutoCompleteTextField(String id, boolean required) {
		super(id);
		setRequired(required);
	}

	public GBAutoCompleteTextField(String id, IModel<T> object, boolean required) {
		super(id, object);
		setRequired(required);
	}

	@Override
	protected Iterator<T> getChoices(String input) {
		return loadChoices(input).iterator();
	}

	protected abstract List<T> loadChoices(String input);
}
