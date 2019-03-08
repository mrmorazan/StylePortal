package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.ImagenBamDao;
import com.grupobeta.styleportal.domain.ArteBam;
import com.grupobeta.styleportal.domain.ImagenBam;

public class ImagenBamDaoImpl extends AbstractHibernateDaoImpl<ImagenBam, Long> implements ImagenBamDao {
	
	public ImagenBamDaoImpl() {
		super(ImagenBam.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenBam> loadAllImagenBamFromArte(ArteBam arteBam) {
		List<ImagenBam> resultados = new ArrayList<ImagenBam>();
		
		String sql = "SELECT DISTINCT " + 
				"    ri.ID_IMAGEN ImagenID " + 
				"   ,au.DESCRIPCION Ubicacion " + 
				"   ,apm.DESCRIPCION PrintMode " + 
				"   ,rit.ALTO " + 
				"   ,rit.ANCHO " + 
				"   ,ri.IMAGEN " + 
				"   ,rit.POSICIONAMIENTO Instructions " + 
				"FROM REG_ARTES ra " + 
				"INNER JOIN REG_IMAGENES ri " + 
				"    ON ra.ID_ARTE = ri.ID_ARTE " + 
				"INNER JOIN AUX_UBICACIONES au " + 
				"    ON ri.ID_UBICACION = au.ID_UBICACION " + 
				"LEFT JOIN AUX_PRINT_MODE apm " + 
				"    ON ri.ID_PRINT_MODE = apm.ID_PRINT_MODE " + 
				"LEFT JOIN REG_IMAGENES_TALLAS rit " + 
				"    ON ri.ID_IMAGEN = rit.ID_IMAGEN " + 
				"WHERE ra.ID_ARTE = :arteID ; " ; 
		
		Query query = getSession().createSQLQuery(sql).addEntity(ImagenBam.class);
		query.setParameter("arteID", arteBam.getArteId());
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<ImagenBam>(query.list());
			for (ImagenBam imagenBam : resultados) {
				imagenBam.setThumbnail(imagenBam.getThumbnails(imagenBam.getImagen()));
			}
		}
		
		return resultados;
	}

}
