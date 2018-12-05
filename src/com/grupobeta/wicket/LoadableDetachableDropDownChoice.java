package com.grupobeta.wicket;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public abstract class LoadableDetachableDropDownChoice<T extends Serializable>
		extends DropDownChoice<T> {
	private static final long serialVersionUID = 1L;
	final protected IModel<List<T>> choicesModel;

	public LoadableDetachableDropDownChoice(String id, IModel<T> model) {
		this(id);
		this.setModel(model);
	}

	public LoadableDetachableDropDownChoice(String id, IChoiceRenderer<? super T> renderer) {
		this(id);
		this.setChoiceRenderer(renderer);
	}

	public LoadableDetachableDropDownChoice(String id, IModel<T> model, IChoiceRenderer<? super T> renderer) {
		this(id);
		this.setModel(model);
		this.setChoiceRenderer(renderer);
	}
	
	public LoadableDetachableDropDownChoice(String id) {
		super(id);
		choicesModel = new LoadableDetachableModel<List<T>>() {          
			private static final long serialVersionUID = 1L;

			@Override                                       
	        protected List<T> load() {
				return loadChoices();      
			}
	    };

	    this.setChoices(choicesModel);
	}
	
	protected abstract List<T> loadChoices();
	public void detachChoices() {
		choicesModel.detach();
	}
}
