package com.grupobeta.wicket;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class CompoundPropertyForm <T extends Serializable> extends Form<T>{
	private static final long serialVersionUID = 1L;

	public CompoundPropertyForm(String id, T object) {
		super(id, new CompoundPropertyModel<T>(object));
	}

	public CompoundPropertyForm(String id, IModel<T> model) {
		super(id, new CompoundPropertyModel<T>(model));
	}


}
