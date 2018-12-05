package com.grupobeta.styleportal.dao.hibernate;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import com.grupobeta.styleportal.dao.GenericDao;
import com.grupobeta.styleportal.domain.DomainObject;

public class GenericDaoImpl implements GenericDao{

	 private SessionFactory sessionFactory;

	  public GenericDaoImpl() {
	  }

	  public SessionFactory getSessionFactory() {
	    return sessionFactory;
	  }

	  public void setSessionFactory(SessionFactory sf) {
	    this.sessionFactory = sf;
	  }

	  public Session getSession() {
	    // presumes a current session, which we have through the
	    // OpenSessionInViewFilter; doesn't work without that
	    return sessionFactory.getCurrentSession();
	  }

	  public Session getOpenSession() {
		    // presumes a current session, which we have through the
		    // OpenSessionInViewFilter; doesn't work without that
		    return sessionFactory.openSession();
		  }

	@SuppressWarnings("unchecked")
	@Override
	public <T extends DomainObject> T load(Class<T> type, Serializable id) {
	    return (T) getSession().get(type, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends DomainObject> List<T> loadAll(Class<T> type) {
		  Criteria criteria = getSession().createCriteria(type);
		  return (List<T>) criteria.list();
	}

	@Override
	public void refresh(DomainObject object) {
		getSession().refresh(object);

	}

	@Override
	public void delete(DomainObject object) {
		getSession().delete(object);
	}

	@Override
	public void save(DomainObject object) {
	    getSession().saveOrUpdate(object);
	}

	@Override
	public void saveFromThead(DomainObject object) {
	    getOpenSession().saveOrUpdate(object);
	}

	@Override
	public <T extends DomainObject> int countAll(Class<T> type) {
		Criteria criteria = getSession().createCriteria(type);
		criteria.setProjection(Projections.rowCount());
		return (Integer) criteria.uniqueResult();
	}
}
