package com.grupobeta.styleportal.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Projections;

import com.grupobeta.styleportal.dao.Dao;
import com.grupobeta.styleportal.domain.DomainObject;

public abstract class AbstractHibernateDaoImpl <T extends DomainObject, IDType extends Serializable>
implements Dao<T, IDType> {

	private Class<T> domainClass;

	private SessionFactory sf;

	public AbstractHibernateDaoImpl(Class<T> domainClass) {
		this.domainClass = domainClass;
	}

	public SessionFactory getSessionFactory() {
		return sf;
	}

	public void setSessionFactory(SessionFactory sf) {
		this.sf = sf;
	}

	public void refresh(T object){
		getSession().refresh(object);
	}

	public void refreshFromThread(T object){
		getOpenSession().refresh(object);
	}

	public void delete (T object) {
		getSession().delete(object);
	}

	@SuppressWarnings("unchecked")
	public T load (IDType id){
		return (T) getSession().get(domainClass, id);
	}

	@SuppressWarnings("unchecked")
	protected <U extends DomainObject> U load(Class<U> type, Serializable id){
		return (U) getSession().get(type, id);
	}

	@Transactional
	public void save(T object){
		getSession().saveOrUpdate(object);
	}

	@Transactional
	public void saveFromThread(T object){
		Session session = getOpenSession();
		try{
			session.saveOrUpdate(object);
		} finally {
			session.close();
		}
	}
	
	@Override
	public void onlySave(T o) {
		getSession().save(o);
	}

	@SuppressWarnings("unchecked")
	public List<T> loadAll() {
		return (List<T>) getLoadAllCriteria().list();
	}

	protected Criteria getLoadAllCriteria() {
		return getSession().createCriteria(domainClass);
	}

	public int countAll() {
		Criteria criteria = getSession().createCriteria(domainClass);
		criteria.setProjection(Projections.rowCount());
		return (Integer) criteria.uniqueResult();
	}

	public Session getSession() {
		return sf.getCurrentSession();
	}

	public Session getOpenSession() {
		return sf.openSession();
	}

	public StatelessSession getThreadSession() {
		return sf.openStatelessSession();
	}

}
