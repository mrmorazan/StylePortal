package com.grupobeta.styleportal.app.styleportal.bom.thread;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;

import com.grupobeta.styleportal.domain.MaterialPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.PageablePropertyListView;

public class ThreadPage extends ThreadBasePage<MaterialPolyPm> {
	private static final long serialVersionUID = 1L;
	

	List<MaterialPolyPm> materiales = new ArrayList<MaterialPolyPm>();

	public ThreadPage(StylePolyPm style) {
		super(style);
		
		setMateriales(getTransService().loadListMaterialPolyPmFromCategory(style, 3));
		
		PageablePropertyListView<MaterialPolyPm> tableThread = new PageablePropertyListView<MaterialPolyPm>("tableThread") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<MaterialPolyPm> item) {
				item.add(new Label("partNumber"));
				item.add(new Label("description"));
				item.add(new Label("bodyPart"));
			}
			
			@Override
			protected List<MaterialPolyPm> loadItems() {
				return getMateriales();
			}
		};
		
		tableThread.setRowsPerPage(10);
		this.add(tableThread);

		this.add(new PagingNavigator("navigator", tableThread));
		
		this.add(tableThread);
		
		
	}

	public List<MaterialPolyPm> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<MaterialPolyPm> materiales) {
		this.materiales = materiales;
	}
	
}
