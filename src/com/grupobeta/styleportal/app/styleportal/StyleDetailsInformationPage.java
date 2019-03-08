package com.grupobeta.styleportal.app.styleportal;

import java.io.File;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.app.styleportal.art.art.ArtPage;
import com.grupobeta.styleportal.app.styleportal.bom.fabric.FabricPage;
import com.grupobeta.styleportal.app.styleportal.spec.spec.SpecPage;
import com.grupobeta.styleportal.app.styleportal.stitching.stitching.StitchingPage;
import com.grupobeta.styleportal.app.styleportal.techpack.techpack.TechPackPage;
import com.grupobeta.styleportal.app.styleportal.workflow.worktask.WorkTaskPage;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.RemoteImage;

public class StyleDetailsInformationPage extends StylePortalBasePage<StylePolyPm> {
	private static final long serialVersionUID = 1L;
	WebMarkupContainer divHeader;
	WebMarkupContainer divDetails;
	WebMarkupContainer divSections;
	
	public StyleDetailsInformationPage(StylePolyPm stylePolyPm) {
		this.addOrReplace(new StylePortalMenuPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("class", "page-title-area-container background-gray");
			}
			
		});
		
		String pathFinal = "";
		try {
			setSelectedObject(getTransService().loadStylePolyPm(stylePolyPm.getStyleId(), stylePolyPm.getSeasonName()));
			getSelectedObject().setCustomerPolyPm(stylePolyPm.getCustomerPolyPm());
			
			String pathOrigen = File.separator + getSelectedObject().getUrlStyleImage2();
			
			pathOrigen = pathOrigen.replace("\\", File.separator);
			
			pathFinal = File.separator + "media" + File.separator + "WinFiles" + pathOrigen;
		} catch (Exception e) {
			
		}
		
		
	//	File file = new File(pathFinal);
		
		divHeader = new WebMarkupContainer("divHeader", getSelectedObjectModel());
		divHeader.add(new Label("styleNumber", Model.of(getSelectedObject().getStyleNumber())));
		divHeader.add(new Label("seasonName", Model.of(getSelectedObject().getSeasonName())));
		divHeader.add(new Label("url", Model.of(getSelectedObject().getUrlStyleImage())));
		divHeader.add(new Label("url2", Model.of(pathFinal)));
		divHeader.add(!getSelectedObject().getUrlStyleImage().equals("0") ? new RemoteImage("remoteImage", Model.of(getSelectedObject().getUrlStyleImage()), pathFinal) : new ContextImage("remoteImage", "img/notAvailableImage.png"));
		
		
		divDetails = new WebMarkupContainer("divDetails", getSelectedObjectModel());
		divDetails.add(new Label("divisionName", Model.of(getSelectedObject().getDivisionName())));
		divDetails.add(new Label("styleCategoryName", Model.of(getSelectedObject().getStyleCategoryName())));
		divDetails.add(new Label("pattern", Model.of(getSelectedObject().getPattern())));
		divDetails.add(new Label("styleSubCategoryName", Model.of(getSelectedObject().getStyleSubCategoryName())));
		divDetails.add(new Label("yarnType", Model.of(getSelectedObject().getYarnType())));
		divDetails.add(new Label("collectionName", Model.of(getSelectedObject().getCollectionName())));
		divDetails.add(new Label("fiber", Model.of(getSelectedObject().getFiber())));
		divDetails.add(new Label("gearLine", Model.of(getSelectedObject().getGearLine())));
		divDetails.add(new Label("silhouette", Model.of(getSelectedObject().getSilhouetteName())));
		
		
		/*String urlSpec = "http://gbsrvt11.grupobeta.com/ReportServer?%2FGBReports%2FPOLY-PM%2FSpecsReport&Season="+getSelectedObject().getSeasonName()+"&Style="+getSelectedObject().getStyleNumber()+"&rs%3AParameterLanguage=en-US";
		divSections.add(new ExternalLink("urlSpec", urlSpec));*/
		 
		divSections = new WebMarkupContainer("divSections");
		
		divSections.add(new Link<Void>("urlSpec") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new SpecPage(getSelectedObject()));
			}
			
			 @Override
	            protected void onComponentTag(ComponentTag tag)
	            {
	                super.onComponentTag(tag);
	                tag.put("target", "_blank"); 
	            }
			
		});
		
		
		
		divSections.add(new Link<Void>("bomsPages") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new FabricPage(getSelectedObject()));
			}
			
			
		});
		
		divSections.add(new Link<Void>("stitching") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new StitchingPage(getSelectedObject()));
			}
		});
		
		divSections.add(new Link<Void>("workflow") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new WorkTaskPage(getSelectedObject()));
			}
		});
		
		divSections.add(new Link<Void>("techPack") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new TechPackPage(getSelectedObject()));
			}
			
			 @Override
	            protected void onComponentTag(ComponentTag tag)
	            {
	                super.onComponentTag(tag);
	                tag.put("target", "_blank"); 
	            }
			
		});
		
		divSections.add(new Link<Void>("art") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ArtPage(getSelectedObject()));
			}

		});
		
		
		this.add(divHeader);
		this.add(divDetails);
		this.add(divSections);
			
	}

}
