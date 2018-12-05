package com.grupobeta.wicket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public abstract class PageableCompoundPropertyModel < T extends Serializable> extends LoadableDetachableModel<List<T>> {
	private static final long serialVersionUID = 1L;

	public long getCount(){
		return getObject().size();
	}

	public Iterator<IModel<T>> getModels(long offset, long size) {
		int offsetint = (int)offset;
		int sizeint = (int)size;
		List<T> subList = getObject().subList(offsetint, offsetint + sizeint);
		List<IModel<T>> models = new ArrayList<IModel<T>>(subList.size());
		for(T object: subList)
			models.add(new CompoundPropertyModel<T>(object));

		return models.iterator();
	}

}
