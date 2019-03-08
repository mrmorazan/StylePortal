package com.grupobeta.styleportal.app.styleportal.art.art;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.repeater.RepeatingView;

import com.grupobeta.styleportal.component.ArtPanel;
import com.grupobeta.styleportal.domain.ArteBam;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class ArtPage extends ArtBasePage<ArteBam> {
	private static final long serialVersionUID = 1L;
	
	List<ArteBam> artes = new ArrayList<ArteBam>();
	RepeatingView repeatingView;

	public ArtPage(StylePolyPm style) {
		super(style);
		
		setArtes(getTransService().loadArtesFromBam(style.getCustomerPolyPm().getCompanyNumber(), style.getStyleNumber(), style.getSeasonName()));
		
		repeatingView = new RepeatingView("lista");
		for(ArteBam arteBam : getArtes()) {
			repeatingView.add(new ArtPanel(repeatingView.newChildId(), arteBam, style));
		}
		
		this.add(repeatingView);
		
	}

	public List<ArteBam> getArtes() {
		return artes;
	}

	public void setArtes(List<ArteBam> artes) {
		this.artes = artes;
	}

}
