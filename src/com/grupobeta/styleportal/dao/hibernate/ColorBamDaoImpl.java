package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.ColorBamDao;
import com.grupobeta.styleportal.domain.ColorBam;
import com.grupobeta.styleportal.domain.ImagenBam;

public class ColorBamDaoImpl extends AbstractHibernateDaoImpl<ColorBam, Integer> implements ColorBamDao {
	
	public ColorBamDaoImpl() {
		super(ColorBam.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ColorBam> loadAllColorFromImage(ImagenBam imagenBam) {
		List<ColorBam> resultados = new ArrayList<ColorBam>();
		
		String sql = "SELECT  " + 
				"    ROW_NUMBER() OVER(ORDER BY act.COD_COLOR_TELA) ColorID " + 
				"    ,act.COD_COLOR_TELA ColorCode " + 
				"   ,act.DESCRIPCION	ColorName " + 
				"FROM REG_COMPOSICION rc " + 
				"INNER JOIN AUX_COLORES_TELA act " + 
				"    ON act.COD_COLOR_TELA = rc.COD_COLOR_TELA " + 
				"WHERE rc.ID_IMAGEN = :idImagen ";
		
		Query query = getSession().createSQLQuery(sql).addEntity(ColorBam.class);
		query.setParameter("idImagen", imagenBam.getImagenBamID());
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<ColorBam>(query.list());
		}
		
		return resultados;
	}

}
