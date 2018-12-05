package com.grupobeta.styleportal.dao;

import java.io.Serializable;
import java.util.List;

import com.grupobeta.styleportal.domain.DomainObject;

public interface Dao  <T extends DomainObject, IDType extends Serializable>  {
	void refresh (T o);

	void refreshFromThread (T o);

	void delete (T o);

	T load (IDType id);

	void save (T o);

	void saveFromThread (T o);
	
	void onlySave (T o);

	List<T> loadAll();

	int countAll();
}
