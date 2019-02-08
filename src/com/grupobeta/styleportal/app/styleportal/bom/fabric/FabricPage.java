package com.grupobeta.styleportal.app.styleportal.bom.fabric;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;

import com.grupobeta.styleportal.domain.MaterialPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.PageablePropertyListView;

public class FabricPage extends FabricBasePage<MaterialPolyPm> {
	private static final long serialVersionUID = 1L;
	
	List<MaterialPolyPm> materiales = new ArrayList<MaterialPolyPm>();

	public FabricPage(StylePolyPm style) {
		super(style);
		
		setMateriales(getTransService().loadListMaterialPolyPmFromCategory(style, 1));
		
		PageablePropertyListView<MaterialPolyPm> tableFabric = new PageablePropertyListView<MaterialPolyPm>("tableFabric") {
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
		
		tableFabric.setRowsPerPage(10);
		this.add(tableFabric);

		this.add(new PagingNavigator("navigator", tableFabric));
		
		this.add(tableFabric);
		
		
	}

	public List<MaterialPolyPm> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<MaterialPolyPm> materiales) {
		this.materiales = materiales;
	}
	
	

}
