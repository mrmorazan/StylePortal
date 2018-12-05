package com.grupobeta.wicket;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.core.util.lang.PropertyResolver;

public class PropertyChoiceRenderer<T extends Serializable> implements IChoiceRenderer<T> {
	private static final long serialVersionUID = 1L;

	protected final String idProperty;
	protected final String displayProperty;

	public PropertyChoiceRenderer(String idProperty) {
		this(idProperty, null);
	}

	public PropertyChoiceRenderer(String idProperty, String displayProperty) {
		this.idProperty = idProperty;
		this.displayProperty = displayProperty;
	}

	@Override
	public Object getDisplayValue(T object) {
		return displayProperty == null ? object.toString() : PropertyResolver.getValue(displayProperty, object);
	}

	@Override
	public String getIdValue(T object, int index) {
		return PropertyResolver.getValue(idProperty, object).toString();
	}

	@Override
	public T getObject(String id, IModel<? extends List<? extends T>> choices) {
		return null;
	}
}
