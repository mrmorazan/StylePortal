package com.grupobeta.styleportal.dao.hibernate;

import org.hibernate.Query;

import com.grupobeta.errors.GBException;
import com.grupobeta.styleportal.dao.PpsUsuarioDao;
import com.grupobeta.styleportal.domain.PpsUsuario;



public class PpsUsuarioDaoImpl extends AbstractHibernateDaoImpl<PpsUsuario, String> 
implements PpsUsuarioDao{

	public PpsUsuarioDaoImpl() {
		super(PpsUsuario.class);
	}

	public PpsUsuario getUsuario(String usuario) {
		PpsUsuario ppsUsuario = null;
		Query query = getSession().getNamedQuery("PpsUsuarios");
		query.setParameter("input", usuario);
		if(query.uniqueResult()!=null) {
			ppsUsuario = (PpsUsuario) query.uniqueResult();
			try {
				ppsUsuario.setFotoCompress(ppsUsuario.getThumbnail(ppsUsuario.getFoto()));
			} catch (GBException e) {
				
			}
		}
		
		return  ppsUsuario; 
	}
	
	
	
	
	
	
}	



