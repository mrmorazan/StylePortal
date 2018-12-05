package com.grupobeta.styleportal.component;

import org.apache.wicket.model.IModel;

import com.grupobeta.util.DateFormats;
import com.grupobeta.wicket.GBDateLabel;

public class StylePortalDateLabel extends GBDateLabel {
	private static final long serialVersionUID = 1L;

	public StylePortalDateLabel(String id, IModel<?> model) {
		super(id, model, DateFormats.US_DATE_FORMAT);
	}

	public StylePortalDateLabel(String id) {
		super(id, DateFormats.US_DATE_FORMAT);
	}
}
