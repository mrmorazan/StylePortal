package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.TecnicaBamDao;
import com.grupobeta.styleportal.domain.ImagenBam;
import com.grupobeta.styleportal.domain.TecnicaBam;

public class TecnicaBamDaoImpl extends AbstractHibernateDaoImpl<TecnicaBam, Integer> implements TecnicaBamDao {
	
	public TecnicaBamDaoImpl() {
		super(TecnicaBam.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TecnicaBam> loadAllTecnicasFromImage(ImagenBam imagenBam) {
		List<TecnicaBam> resultados = new ArrayList<TecnicaBam>();
		
		String sql = "SELECT DISTINCT " + 
				"    AT.ID_TECNICA TecnicaID, " + 
				"    AT.DESCRIPCION TecnicaName " + 
				"FROM REG_COMPOSICION rc " + 
				"INNER JOIN REG_DET_COMPOSICION rdc ON rc.ID_IMAGEN = rdc.ID_IMAGEN AND rc.COD_COLOR_TELA = rdc.COD_COLOR_TELA " + 
				"INNER JOIN AUX_TECNICAS at ON rdc.ID_TECNICA = at.ID_TECNICA " + 
				"WHERE rc.ID_IMAGEN = :idImagen ";
		
		Query query = getSession().createSQLQuery(sql).addEntity(TecnicaBam.class);
		query.setParameter("idImagen", imagenBam.getImagenBamID());
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<TecnicaBam>(query.list());
		}
		
		return resultados;
	}
	
	

}
