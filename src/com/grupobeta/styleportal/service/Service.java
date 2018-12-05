package com.grupobeta.styleportal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.styleportal.domain.DomainObject;

public interface Service {

	@Transactional
	void refresh(DomainObject object);

	@Transactional
	void delete(DomainObject object);

	<T extends DomainObject> T load(Class<T> type, Serializable id);

	@Transactional
	void save(DomainObject object);

	<T extends DomainObject> List<T> loadAll(Class<T> type);

	<T extends DomainObject> int countAll(Class<T> type);
	
}
