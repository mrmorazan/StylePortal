package com.grupobeta.wicket;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.AbstractPageableView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.core.util.lang.PropertyResolver;



public abstract class PageablePropertyListView<T extends Serializable> extends AbstractPageableView<T> {
		private static final long serialVersionUID = 1L;
		protected PageableCompoundPropertyModel<T> model;

		public PageablePropertyListView(String id) {
			super(id);
			this.model = new PageableCompoundPropertyModel<T>() {
				private static final long serialVersionUID = 1L;

				@Override
				protected List<T> load() {
					return loadItems();
				}
			};
		}


		protected abstract List<T> loadItems();

		@Override
		protected Iterator<IModel<T>> getItemModels(long offset, long size) {
			return this.model.getModels(offset, size);
		}

		@Override
		protected long internalGetItemCount() {
			return this.model.getCount();
		}

		public long getRowsPerPage(){
			return getItemsPerPage();
		}

		public PageablePropertyListView<T> setRowsPerPage(long items){
			setItemsPerPage(items);
			return this;
		}

		public void detachItems() {
			this.model.detach();
		}

		public long getExpressionSum(String expression){
			long total = 0;
			Number value;
			for(T object: this.model.getObject()) {
				value = (Number) PropertyResolver.getValue(expression, object);
				if (value != null)
					total += value.longValue();
			}
			return total;
		}


}
