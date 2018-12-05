package com.grupobeta.styleportal.dao;

import com.grupobeta.styleportal.domain.DomainObject;

public interface DaoDA <T extends DomainObject> {
	boolean isAuthenticate(String username, String password);
}
