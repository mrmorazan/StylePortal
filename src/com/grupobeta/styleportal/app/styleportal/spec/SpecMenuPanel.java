package com.grupobeta.styleportal.app.styleportal.spec;

import java.io.File;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.app.styleportal.StyleDetailsInformationPage;
import com.grupobeta.styleportal.app.styleportal.art.art.ArtPage;
import com.grupobeta.styleportal.app.styleportal.bom.fabric.FabricPage;
import com.grupobeta.styleportal.app.styleportal.spec.spec.SpecPage;
import com.grupobeta.styleportal.app.styleportal.stitching.stitching.StitchingPage;
import com.grupobeta.styleportal.app.styleportal.techpack.techpack.TechPackPage;
import com.grupobeta.styleportal.app.styleportal.workflow.worktask.WorkTaskPage;
import com.grupobeta.styleportal.component.MenuPanel;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.RemoteImage;

public class SpecMenuPanel extends MenuPanel {
	private static final long serialVersionUID = 1L;
	
	public SpecMenuPanel(StylePolyPm style, String opcionName) {
		
		String pathFinal = "";
		try {
			String pathOrigen = File.separator + style.getUrlStyleImage2();
			
			pathOrigen = pathOrigen.replace("\\", File.separator);
			
			pathFinal = File.separator + "media" + File.separator + "WinFiles" + pathOrigen;
		} catch (Exception e) {
			
		}
		
		WebMarkupContainer	divHeader = new WebMarkupContainer("divHeader");
		Link<Void> back;
		divHeader.add(back = new Link<Void>("backToDetail") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new StyleDetailsInformationPage(style));
			}
			
		});
		
		back.add(new Label("styleNumber", Model.of(style.getStyleNumber())));
		back.add(new Label("seasonName", Model.of(style.getSeasonName())));
		back.add(new Label("url", Model.of(style.getUrlStyleImage())));
		back.add(new Label("url2", Model.of(pathFinal)));
		back.add(!style.getUrlStyleImage().equals("0") ? new RemoteImage("remoteImage", Model.of(style.getUrlStyleImage()), pathFinal) : new ContextImage("remoteImage", "img/notAvailableImage.png"));
		
	/*	String urlSpec = "http://gbsrvt11.grupobeta.com/ReportServer?%2FGBReports%2FPOLY-PM%2FSpecsReport&Season="+style.getSeasonName()+"&Style="+style.getStyleNumber()+"&rs%3AParameterLanguage=en-US";
		divHeader.add(new ExternalLink("urlSpec", urlSpec));*/
		
		divHeader.add(new Link<Void>("urlSpec") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new SpecPage(style));
			}
			
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("target", "_blank");
			}
			
		});
		
		divHeader.add(new Link<Void>("fabric") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new FabricPage(style));
			}
			
		});
		
		divHeader.add(new Link<Void>("stitching") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new StitchingPage(style));
			}
		});
		
		divHeader.add(new Link<Void>("workflow") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new WorkTaskPage(style));
			}
		});
		
		divHeader.add(new Link<Void>("techPack") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new TechPackPage(style));
			}

			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("target", "_blank");
			}

		});
		
		divHeader.add(new Link<Void>("art") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ArtPage(style));
			}

		});
		
		this.add(divHeader);
		
		
	}

}
