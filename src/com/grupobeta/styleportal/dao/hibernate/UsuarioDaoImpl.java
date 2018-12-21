package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.grupobeta.styleportal.dao.UsuarioDao;
import com.grupobeta.styleportal.domain.Usuario;

public class UsuarioDaoImpl extends AbstractHibernateDaoImpl<Usuario, Long>
		implements UsuarioDao {

	public UsuarioDaoImpl() {
		super(Usuario.class);
	}

	@Override
	protected Criteria getLoadAllCriteria() {
		return super.getLoadAllCriteria().addOrder(Order.asc("codUsuario"));
	}

	@Override
	public Usuario loadByCod(String codUsuario) {
		String hql = "from Usuario where"
				+ " codUsuario = :input and estado=true";
		Query query = getSession().createQuery(hql);
		query.setParameter("input", codUsuario);

		@SuppressWarnings("unchecked")
		Iterator<Usuario> iterator = (Iterator<Usuario>) query.iterate();
		return iterator.hasNext() ? iterator.next() : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> find(String input, String codRol) {
		if (codRol == null)
			return find(input);
		else if (input == null || input.length() < 2)
			return new ArrayList<Usuario>(0);
		else {
			String hql = "from Usuario as U"
					+ " where :codRol in elements(U.roles)"
					+ " and (U.codUsuario like :input or U.nombre like :input)";

			Query query = getSession().createQuery(hql);
			// query.setParameter("activo", EstadoUsuario.A);
			query.setParameter("codRol", codRol);
			query.setParameter("input", "%" + input + "%");

			return (List<Usuario>) query.list();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> find(String input) {
		if (input == null || input.length() < 2)
			return new ArrayList<Usuario>(0);
		else {
			String hql = "from Usuario "
					+ " where (codUsuario like :input or nombre like :input)";
			Query query = getSession().createQuery(hql);
			// query.setParameter("activo", EstadoUsuario.A);
			query.setParameter("input", "%" + input + "%");

			return new ArrayList<Usuario>(query.list());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> find(String input, boolean status) {
		
		String hql = "from Usuario "
				+ " where estado = :status";
		if (input != null ) {
			hql+= " and (codUsuario like :input or nombre like :input) ";
		}	
				
		Query query = getSession().createQuery(hql);
		 query.setParameter("status", status);
		if (input != null ) {
			query.setParameter("input", "%" + input + "%");
		}

		return new ArrayList<Usuario>(query.list());
	}

	@Override
	public Usuario loadByCodEstado(String codUsuario) {
		String hql = "from Usuario where" + " codUsuario = :input";
		Query query = getSession().createQuery(hql);
		query.setParameter("input", codUsuario);

		getOpenSession().close();
		@SuppressWarnings("unchecked")
		Iterator<Usuario> iterator = (Iterator<Usuario>) query.iterate();
		return iterator.hasNext() ? iterator.next() : null;
	}

	@Override
	public Usuario loadByEmail(String email) {
		String hql = "from Usuario where"
				+ " correoElectronico = :input and estado=true";

		Session session = getSession();

			Query query = session.createQuery(hql);
			query.setParameter("input", email);
			query.setMaxResults(1);

			Usuario usuario = null;
			@SuppressWarnings("unchecked")
			Iterator<Usuario> iterator = (Iterator<Usuario>) query.iterate();
			if(iterator.hasNext()){
				usuario = iterator.next();
				Hibernate.initialize(usuario.getRoles());
			}
			return usuario;


	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> loadUserCodeFromPlant(String planCode) {
		String sql = "SELECT DISTINCT u.UserCode FROM ADM.Users u INNER JOIN AUX.Plants p ON u.PlantID = p.PlantID ";
				sql += " WHERE p.PlantCode = :code ";

		Query query = getSession().createSQLQuery(sql);
		query.setParameter("code", planCode);

		return new ArrayList<String>(query.list());
	}

	
	@Override
	public String loadUserDA(String user) {
		String sql = "DECLARE @User NVARCHAR(100)	= :user, @qry NVARCHAR(MAX) "
				+ "SET @qry = 'select displayName "
				+ "from openquery(ADSI, '' "
				+ "select displayName "
				+ "from ''''LDAP://DC=grupobeta,DC=com'''' "
				+ "where sAMAccountName= ''''' + @User + ''''' "
				+ "AND displayname <> ''''0_UW_Template_Remote'''' "
				+ "ORDER BY displayName '') ' "
				+ " EXEC sp_executesql	@qry ";

		Query query = getSession().createSQLQuery(sql);
		query.setParameter("user", user);

		return (String) query.uniqueResult();
	}
	

}
