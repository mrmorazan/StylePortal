package com.grupobeta.wicket;

import java.util.Arrays;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public class EnumDropDownChoice<T extends Enum<T>> extends DropDownChoice<T> {
	private static final long serialVersionUID = 1L;

	public EnumDropDownChoice(String id, IModel<T> model, T[] choices, IChoiceRenderer<? super T> renderer) {
		super(id, model, Arrays.asList(choices), renderer);
	}

	public EnumDropDownChoice(String id, IModel<T> model, T[] choices) {
		super(id, model, Arrays.asList(choices));
	}

	public EnumDropDownChoice(String id, T[] choices, IChoiceRenderer<? super T> renderer) {
		super(id, Arrays.asList(choices), renderer);
	}

	public EnumDropDownChoice(String id, T[] choices) {
		super(id, Arrays.asList(choices));
	}
}
