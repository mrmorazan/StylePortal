package com.grupobeta.styleportal.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.RolDao;
import com.grupobeta.styleportal.domain.Rol;

public class RolDaoImpl extends AbstractHibernateDaoImpl<Rol, Integer> implements RolDao{

		public RolDaoImpl() {
			super(Rol.class);
		}

		@Override
		public Rol loadByCod(String input) {
			String hql = "from Rol where"
					+ " codRol = :input ";
			Query query = getSession().createQuery(hql);
			query.setParameter("input", input);

			@SuppressWarnings("unchecked")
			List<Rol> list = (List<Rol>) query
					.list();
			return list.size() > 0 ? list.get(0) : null;
		}

}
