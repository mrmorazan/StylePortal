package com.grupobeta.wicket;

import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

import com.grupobeta.util.DateFormats;

public class GBDateLabel extends Label {
	private static final long serialVersionUID = 1L;
	public final static String DEFAULT_DATE_PATTERN = DateFormats.REPORT_DATE_FORMAT;
	protected IConverter<Date> converter;

	public GBDateLabel(String id, IModel<?> model) {
		super(id, model);
		init(DEFAULT_DATE_PATTERN);
	}

	public GBDateLabel(String id) {
		super(id);
		init(DEFAULT_DATE_PATTERN);
	}

	public GBDateLabel(String id, IModel<?> model, String datePattern) {
		super(id, model);
		init(datePattern);
	}

	public GBDateLabel(String id, String datePattern) {
		super(id);
		init(datePattern);
	}

	protected void init(String datePattern) {
		converter = new GBPatternDateConverter(datePattern);
	}


}
