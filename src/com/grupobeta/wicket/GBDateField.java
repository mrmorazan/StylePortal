package com.grupobeta.wicket;

import java.util.Date;

import org.apache.wicket.extensions.markup.html.form.DateTextField;

import org.apache.wicket.model.IModel;
import org.wicketstuff.datetime.extensions.yui.calendar.DatePicker;

import com.grupobeta.util.DateFormats;

public class GBDateField extends DateTextField {
	private static final long serialVersionUID = 1L;
	public final static String DEFAULT_DATE_PATTERN = DateFormats.REPORT_DATE_FORMAT;

	public GBDateField(String id, IModel<Date> model, String datePattern) {
		super(id, model, datePattern);
		init();
	}

	public GBDateField(String id, IModel<Date> model) {
		super(id, model, DEFAULT_DATE_PATTERN);
		init();
	}

	public GBDateField(String id, String datePattern) {
		super(id, datePattern);
		init();
	}

	public GBDateField(String id) {
		super(id, DEFAULT_DATE_PATTERN);
		init();
	}

	protected void init() {
		this.add(new DatePicker());
	}
}
