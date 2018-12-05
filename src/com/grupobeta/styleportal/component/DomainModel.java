package com.grupobeta.styleportal.component;

import java.io.Serializable;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.grupobeta.styleportal.domain.DomainObject;
import com.grupobeta.styleportal.service.TransaccionesService;

public abstract class DomainModel <T extends DomainObject, IDType extends Serializable> extends LoadableDetachableModel<T> {
	private static final long serialVersionUID = 1L;

	@SpringBean
	protected TransaccionesService transService;
	private Class<T> domainClass;
	protected IDType id;

	protected DomainModel(Class<T> domainClass, IDType id) {
		Injector.get().inject(this);
		this.id = id;
		this.domainClass = domainClass;
	}

	@Override
	protected T load() {
		if (id == null) {
			try {
				return this.domainClass.newInstance();
			} catch (Exception ex) {
				return null;
			}
		} else
			return getTransService().load(this.domainClass, id);
	}

	public TransaccionesService getTransService() {
		return transService;
	}

	public void setTransService(TransaccionesService transService) {
		this.transService = transService;
	}

	public IDType getId() {
		return id;
	}

	public void setId(IDType id) {
		this.id = id;
		detach();
	}

}
