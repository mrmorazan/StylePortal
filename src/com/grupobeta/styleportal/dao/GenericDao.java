package com.grupobeta.styleportal.dao;

import java.io.Serializable;
import java.util.List;

import com.grupobeta.styleportal.domain.DomainObject;

public interface GenericDao {
	void refresh(DomainObject object);
	void delete(DomainObject object);
	<T extends DomainObject> T load(Class<T> type, Serializable id);
	void save(DomainObject object);
	void saveFromThead(DomainObject object);
	<T extends DomainObject> List<T> loadAll(Class<T> type);
	<T extends DomainObject> int countAll(Class<T> type);
}
