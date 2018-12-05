package com.grupobeta.wicket;

import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public class GBChoiceRenderer<T extends Renderable> implements IChoiceRenderer<T> {
	private static final long serialVersionUID = 1L;

	@Override
	public Object getDisplayValue(T object) {
		return object.getDisplayValue();
	}

	@Override
	public String getIdValue(T object, int index) {
		return object.getIdValue(index);
	}

	@Override
	public T getObject(String id, IModel<? extends List<? extends T>> choices) {
		return choices.getObject().get(0);
	}
}
