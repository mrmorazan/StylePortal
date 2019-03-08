package com.grupobeta.styleportal.app.styleportal.bom.packing;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.domain.MaterialPolyPm;
import com.grupobeta.styleportal.domain.PackingManual;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.PageablePropertyListView;

public class PackingPage extends PackingBasePage<MaterialPolyPm> {
	private static final long serialVersionUID = 1L;
	
	List<PackingManual> manuales = new ArrayList<PackingManual>();

	public PackingPage(StylePolyPm style) {
		super(style);
		
		setManuales(getTransService().loadAllPackingManualFrom(style.getCustomerPolyPm().getCompanyNumber(), style.getSeasonName(), style.getStyleNumber()));
		
		if(getManuales().isEmpty()) {
			setManuales(getTransService().loadAllPackingManualFrom(style.getCustomerPolyPm().getCompanyNumber(), style.getSeasonName()));
		}
		
		if(getManuales().isEmpty()) {
			setManuales(getTransService().loadAllPackingManualFromStyle(style.getCustomerPolyPm().getCompanyNumber(), style.getStyleNumber()));
		}
		
		
		PageablePropertyListView<PackingManual> tableFabric = new PageablePropertyListView<PackingManual>("tablePacking") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<PackingManual> item) {
				ExternalLink externalLink = new ExternalLink("linkAlfresco", item.getModelObject().getUrlFile()) {
					private static final long serialVersionUID = 1L;

					@Override
					protected void onComponentTag(ComponentTag tag) {
						super.onComponentTag(tag);
						tag.put("target", "_blank");
					}
				};
				externalLink.add(new Label("fileName", Model.of(item.getModelObject().getFileName())));
				item.add(externalLink);
		
			}
			
			@Override
			protected List<PackingManual> loadItems() {
				return getManuales();
			}
		};
		
		tableFabric.setRowsPerPage(10);
		this.add(tableFabric);

		this.add(new PagingNavigator("navigator", tableFabric));
		
		this.add(tableFabric);
		
		
	}

	public List<PackingManual> getManuales() {
		return manuales;
	}

	public void setManuales(List<PackingManual> manuales) {
		this.manuales = manuales;
	}

	
	

}
