package com.grupobeta.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GBMultipleException extends Exception {
	private static final long serialVersionUID = 1L;

	private List<GBException> exceptions =  new ArrayList<GBException>();

	public GBMultipleException() {

	}

	public GBMultipleException(GBException exception) {
		addException(exception);
	}

	public GBMultipleException(GBException[] list) {
		addExceptions(list);
	}

	public GBMultipleException(Collection<GBException> ex) {
		addExceptions(ex);
	}

	public List<GBException> getExceptions() {
		return exceptions;
	}

	public void addException(GBException ex) {
		getExceptions().add(ex);
	}

	public void addExceptions(GBException[] list) {
		this.addExceptions(Arrays.asList(list));
	}

	public void addExceptions(Collection<GBException> list) {
		getExceptions().addAll(list);
	}

	public boolean isEmpty() {
		return getExceptions().size() == 0;
	}

	public void throwIfNotEmpty() throws GBMultipleException {
		if (!isEmpty())
			throw this;
	}
}
