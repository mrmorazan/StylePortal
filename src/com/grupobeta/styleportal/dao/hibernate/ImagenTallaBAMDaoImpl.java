package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.ImagenTallaBAMDao;
import com.grupobeta.styleportal.domain.ImagenBam;
import com.grupobeta.styleportal.domain.ImagenTallaBAM;

public class ImagenTallaBAMDaoImpl extends AbstractHibernateDaoImpl<ImagenTallaBAM, Integer> implements ImagenTallaBAMDao {

	public ImagenTallaBAMDaoImpl() {
		super(ImagenTallaBAM.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenTallaBAM> loadAllImagenesTallaBAM(ImagenBam imagenBam) {
		List<ImagenTallaBAM> resultados = new ArrayList<ImagenTallaBAM>();
		
		String sql = "SELECT " + 
				"    ROW_NUMBER() OVER(ORDER BY(at.COD_TALLA)) ImagenTallaID " + 
				"    ,at.DESCRIPCION Talla " + 
				"   ,rit.POSICIONAMIENTO Posicionamiento " + 
				" 	,rit.ANCHO " + 
				" 	,rit.ALTO  "+
				"FROM REG_ARTES ra " + 
				"INNER JOIN REG_IMAGENES ri " + 
				"    ON ra.ID_ARTE = ri.ID_ARTE " + 
				"INNER JOIN REG_IMAGENES_TALLAS rit " + 
				"    ON ri.ID_IMAGEN = rit.ID_IMAGEN " + 
				"INNER JOIN AUX_TALLAS at " + 
				"    ON rit.COD_TALLA = at.COD_TALLA " + 
				"WHERE ri.ID_IMAGEN = :imagenBAM";
		
		Query query = getSession().createSQLQuery(sql).addEntity(ImagenTallaBAM.class);
		query.setParameter("imagenBAM", imagenBam.getImagenBamID());
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<ImagenTallaBAM>(query.list());
		}
		
		return resultados;
	}
	

}
