package com.grupobeta.styleportal.app.styleportal;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.RemoteImage;

public class StyleDetailsInformationPage extends StylePortalBasePage<StylePolyPm> {
	private static final long serialVersionUID = 1L;
	WebMarkupContainer divHeader;
	WebMarkupContainer divDetails;
	
	public StyleDetailsInformationPage(StylePolyPm stylePolyPm) {
		setSelectedObject(getTransService().loadStylePolyPm(stylePolyPm.getStyleId(), stylePolyPm.getSeasonName()));
		
		divHeader = new WebMarkupContainer("divHeader", getSelectedObjectModel());
		divHeader.add(new Label("styleNumber", Model.of(getSelectedObject().getStyleNumber())));
		divHeader.add(new Label("seasonName", Model.of(getSelectedObject().getSeasonName())));
		divHeader.add(new Label("url", Model.of(getSelectedObject().getUrlStyleImage())));
		divHeader.add(new Label("url2", Model.of(getSelectedObject().getUrlStyleImage2())));
		divHeader.add(!getSelectedObject().getUrlStyleImage().equals("0") ? new RemoteImage("remoteImage", Model.of(getSelectedObject().getUrlStyleImage()), getSelectedObject().getUrlStyleImage2()) : new ContextImage("remoteImage", "img/notAvailableImage.png"));
		
		
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
		
		this.add(divHeader);
		this.add(divDetails);
			
	}

}
