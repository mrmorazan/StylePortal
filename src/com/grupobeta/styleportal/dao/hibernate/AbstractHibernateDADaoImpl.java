package com.grupobeta.styleportal.dao.hibernate;

import com.grupobeta.styleportal.dao.DaoDA;
import com.grupobeta.styleportal.domain.DomainObject;
import com.grupobeta.util.LoginLDAP;

public abstract class AbstractHibernateDADaoImpl<T extends DomainObject>
		implements DaoDA<T> {

	private Class<T> domainClass;
	private LoginLDAP authenticatedLDAP;

	public AbstractHibernateDADaoImpl(Class<T> domainClass) {
		this.setDomainClass(domainClass);
	}

	public LoginLDAP getAuthenticatedLDAP() {
		return authenticatedLDAP;
	}

	public void setAuthenticatedLDAP(LoginLDAP authenticatedLDAP) {
		this.authenticatedLDAP = authenticatedLDAP;
	}

	public Class<T> getDomainClass() {
		return domainClass;
	}

	public void setDomainClass(Class<T> domainClass) {
		this.domainClass = domainClass;
	}

	public boolean isAuthenticate(String username, String password) {
		return true;
	//	return getAuthenticatedLDAP().IsAuthenticateUser("grupobeta.com",389, username, password);
	}

}
