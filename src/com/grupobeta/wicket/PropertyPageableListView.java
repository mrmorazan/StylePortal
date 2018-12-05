package com.grupobeta.wicket;

import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.IModel;

import com.grupobeta.styleportal.domain.DomainObject;



public class PropertyPageableListView<T extends DomainObject> extends PageableListView<T> {
	private static final long serialVersionUID = 1L;

	public PropertyPageableListView(String id, final IModel<? extends List<T>> model, int itemsPerPage) {
		super(id, model, itemsPerPage);
	}

	@Override
	protected void populateItem(ListItem<T> item) {


	}



}
