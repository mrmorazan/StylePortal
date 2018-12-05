package com.grupobeta.wicket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.util.convert.converter.AbstractConverter;



public class GBPatternDateConverter extends AbstractConverter<Date> {
	private static final long serialVersionUID = 1L;

	protected SimpleDateFormat formatter;

	public GBPatternDateConverter(String datePattern) {
		formatter = new SimpleDateFormat(datePattern);
	}

	@Override
	protected Class<Date> getTargetType() {
		return Date.class;
	}

	@Override
	public Date convertToObject(String value, Locale locale) {
		try {
			return formatter.parse(value);
		} catch (ParseException e) {
			throw newConversionException("'" + value + "' is an invalid date.", value, locale);
		}
	}

	@Override
	public String convertToString(final Date value, Locale locale)
	{
		return formatter.format(value);
	}

}
