package com.grupobeta.styleportal.app.styleportal.stitching.stitching;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.domain.DocumentPolyPm;
import com.grupobeta.styleportal.domain.InstructionPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.PageablePropertyListView;
import com.grupobeta.wicket.RemoteImage;

public class StitchingPage extends StitchingBasePage<InstructionPolyPm> {
	private static final long serialVersionUID = 1L;
	
	List<InstructionPolyPm> instrucciones = new ArrayList<InstructionPolyPm>();

	public StitchingPage(StylePolyPm style) {
		super(style);
		
		setInstrucciones(getTransService().loadInstructionsForStyle(style));
		
		PageablePropertyListView<InstructionPolyPm> tableStitching = new PageablePropertyListView<InstructionPolyPm>("tableStitching") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<InstructionPolyPm> item) {
				DocumentPolyPm documentPolyPm = getTransService().loadDocumentPolyPmInstruction(style, item.getModelObject());
				
				item.add(new Label("sequence"));
				item.add(new Label("instructionName"));
				item.add(new Label("operationCode"));
				item.add(new Label("operation"));
				item.add(new Label("machine"));
				item.add(new Label("width"));
				item.add(new Label("comments"));
				item.add(new Label("stitchType"));
				item.add(new Label("spi"));
				item.add(new Label("threadText"));
				item.add(new Label("notes"));
				
				if(documentPolyPm!=null) {
					String pathFinal = "";
					try {
						String pathOrigen = documentPolyPm.getFilePath();
						
						pathOrigen = File.separator + pathOrigen.replace("\\", File.separator);
						
						pathFinal = File.separator + "media" + File.separator + "WinFiles" + pathOrigen;
					} catch (Exception e) {
						
					}
					item.add(!documentPolyPm.getUrlComponent().equals("0") ? new RemoteImage("remoteImage", Model.of(documentPolyPm.getUrlComponent()), pathFinal) : new ContextImage("remoteImage", "img/notAvailableImage.png"));
				} else {
					item.add(new ContextImage("remoteImage", "img/notAvailableImage.png"));
				}
				
			}
			
			@Override
			protected List<InstructionPolyPm> loadItems() {
				return getInstrucciones();
			}
		};
		
		tableStitching.setRowsPerPage(10);
		this.add(tableStitching);

		this.add(new PagingNavigator("navigator", tableStitching));
		
		this.add(tableStitching);
		
	}

	public List<InstructionPolyPm> getInstrucciones() {
		return instrucciones;
	}

	public void setInstrucciones(List<InstructionPolyPm> instrucciones) {
		this.instrucciones = instrucciones;
	}
	
	

}
