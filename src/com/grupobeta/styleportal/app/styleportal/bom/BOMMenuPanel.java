package com.grupobeta.styleportal.app.styleportal.bom;

import java.io.File;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.app.styleportal.StyleDetailsInformationPage;
import com.grupobeta.styleportal.app.styleportal.bom.fabric.FabricPage;
import com.grupobeta.styleportal.component.MenuPanel;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.RemoteImage;

public class BOMMenuPanel extends MenuPanel {
	private static final long serialVersionUID = 1L;
	
	public BOMMenuPanel(StylePolyPm style, String opcionName) {
		
		String pathFinal = "";
		try {
			String pathOrigen = style.getUrlStyleImage2();
			
			pathOrigen = pathOrigen.replace("\\", File.separator);
			
			pathFinal = File.separator + "media" + File.separator + "WinFiles" + pathOrigen;
		} catch (Exception e) {
			
		}
		
		WebMarkupContainer	divHeader = new WebMarkupContainer("divHeader");
		divHeader.add(new Label("styleNumber", Model.of(style.getStyleNumber())));
		divHeader.add(new Label("seasonName", Model.of(style.getSeasonName())));
		divHeader.add(new Label("url", Model.of(style.getUrlStyleImage())));
		divHeader.add(new Label("url2", Model.of(pathFinal)));
		divHeader.add(!style.getUrlStyleImage().equals("0") ? new RemoteImage("remoteImage", Model.of(style.getUrlStyleImage()), pathFinal) : new ContextImage("remoteImage", "img/notAvailableImage.png"));
		
		divHeader.add(new Link<Void>("backToDetail") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new StyleDetailsInformationPage(style));
			}
			
		});
		
		divHeader.add(new Link<Void>("fabric") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new FabricPage(style));
			}
			
		});
		
		this.add(divHeader);
		
		
	}

}
