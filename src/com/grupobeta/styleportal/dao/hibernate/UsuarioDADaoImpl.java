package com.grupobeta.styleportal.dao.hibernate;

import com.grupobeta.styleportal.dao.UsuarioDADao;
import com.grupobeta.styleportal.domain.UsuarioDA;

public class UsuarioDADaoImpl extends AbstractHibernateDADaoImpl<UsuarioDA> implements UsuarioDADao {

	public UsuarioDADaoImpl(){
		super(UsuarioDA.class);
	}
}
