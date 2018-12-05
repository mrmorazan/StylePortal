package com.grupobeta.styleportal.component;

import java.util.Date;

import org.apache.wicket.model.IModel;

import com.grupobeta.util.DateFormats;
import com.grupobeta.wicket.GBDateField;

public class StylePortalDateField extends GBDateField {
	private static final long serialVersionUID = 1L;

	public StylePortalDateField(String id, IModel<Date> model) {
		super(id, model, DateFormats.US_DATE_FORMAT);
	}

	public StylePortalDateField(String id) {
		super(id, DateFormats.US_DATE_FORMAT);
	}

}
