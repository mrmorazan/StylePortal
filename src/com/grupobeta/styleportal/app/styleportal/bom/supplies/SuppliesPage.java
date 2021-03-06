package com.grupobeta.styleportal.app.styleportal.bom.supplies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.domain.DocumentPolyPm;
import com.grupobeta.styleportal.domain.MaterialPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.PageablePropertyListView;
import com.grupobeta.wicket.RemoteImage;

public class SuppliesPage extends SuppliesBasePage<MaterialPolyPm> {
	private static final long serialVersionUID = 1L;
	

	List<MaterialPolyPm> materiales = new ArrayList<MaterialPolyPm>();

	public SuppliesPage(StylePolyPm style) {
		super(style);
		
		setMateriales(getTransService().loadListMaterialPolyPmFromCategory(style, 5));
		
		PageablePropertyListView<MaterialPolyPm> tableSupplies = new PageablePropertyListView<MaterialPolyPm>("tableSupplies") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<MaterialPolyPm> item) {
				DocumentPolyPm documentPolyPm = getTransService().loadDocumentPolyPmComponent(item.getModelObject().getComponentName());
				
				String pathFinal = "";
				try {
					String pathOrigen = File.separator + documentPolyPm.getFilePath();
					
					pathOrigen = pathOrigen.replace("\\", File.separator);
					
					pathFinal = File.separator + "media" + File.separator + "WinFiles" + pathOrigen;
				} catch (Exception e) {
					
				}
				
				item.add(new Label("partNumber"));
				item.add(new Label("description"));
				item.add(new Label("bodyPart"));
				
				if(documentPolyPm!=null) {
					item.add(!documentPolyPm.getUrlComponent().equals("0") ? new RemoteImage("remoteImage", Model.of(documentPolyPm.getUrlComponent()), pathFinal) : new ContextImage("remoteImage", "img/notAvailableImage.png"));
				} else {
					item.add(new ContextImage("remoteImage", "img/notAvailableImage.png"));
				}
				
				
			}
			
			@Override
			protected List<MaterialPolyPm> loadItems() {
				return getMateriales();
			}
		};
		
		tableSupplies.setRowsPerPage(10);
		this.add(tableSupplies);

		this.add(new PagingNavigator("navigator", tableSupplies));
		
		this.add(tableSupplies);
		
		
	}

	public List<MaterialPolyPm> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<MaterialPolyPm> materiales) {
		this.materiales = materiales;
	}
	
}
