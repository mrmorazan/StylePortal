package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.ArteBamDao;
import com.grupobeta.styleportal.domain.ArteBam;

public class ArteBamDaoImpl extends AbstractHibernateDaoImpl<ArteBam, Long> implements ArteBamDao {
	
	public ArteBamDaoImpl() {
		super(ArteBam.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArteBam> loadArtesFromBam(String customerCode, String estilo, String season) {
		List<ArteBam> resultados = new ArrayList<ArteBam>();
		
		String sql = "SELECT DISTINCT " + 
				"    ra.ID_ARTE " + 
				"   ,ra.GRAFICO " + 
				"FROM REG_ARTES ra " + 
				"INNER JOIN REG_IMAGENES ri " + 
				"    ON ra.ID_ARTE = ri.ID_ARTE " + 
				"INNER JOIN AUX_UBICACIONES au " + 
				"    ON ri.ID_UBICACION = au.ID_UBICACION " + 
				"LEFT JOIN AUX_PRINT_MODE apm " + 
				"    ON ri.ID_PRINT_MODE = apm.ID_PRINT_MODE " + 
				"LEFT JOIN REG_IMAGENES_TALLAS rit " + 
				"    ON ri.ID_IMAGEN = rit.ID_IMAGEN " + 
				"LEFT JOIN AUX_CLIENTES ac " + 
				"    ON ra.COD_CLIENTE = ac.COD_CLIENTE " + 
				"WHERE ra.ESTILO = :estilo " +
				"AND ra.COD_CLIENTE = :customerCode ";
				
		if(season!=null) {
			sql += "AND ra.TEMPORADA = :season ";
		} else {
			sql += "AND ra.TEMPORADA is null ";
		}
				 
				 
			sql +=	"AND ra.REVISION = (SELECT " + 
				"	   MAX(ra1.REVISION) " + 
				"    FROM REG_ARTES ra1 " + 
				"    WHERE ra1.ESTILO = :estilo " +
				" AND ra1.COD_CLIENTE = :customerCode ";
				if(season!=null) {	
					sql += " AND ra1.TEMPORADA = :season )";
				} else {
					sql += " AND ra1.TEMPORADA is null )";
				}
				
		Query query = getSession().createSQLQuery(sql).addEntity(ArteBam.class);
		
		query.setParameter("estilo", estilo);
		query.setParameter("customerCode", customerCode);
		
		if(season!=null) {
			query.setParameter("season", season);
		}
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<ArteBam>(query.list());
		}
		
		return resultados;
	}

}
