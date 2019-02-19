package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.InstructionPolyPmDao;
import com.grupobeta.styleportal.domain.InstructionPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class InstructionPolyPmDaoImpl extends AbstractHibernateDaoImpl<InstructionPolyPm, Integer> implements InstructionPolyPmDao {

	public InstructionPolyPmDaoImpl() {
		super(InstructionPolyPm.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstructionPolyPm> loadInstructionsForStyle(StylePolyPm style) {
		List<InstructionPolyPm> resultados = new ArrayList<InstructionPolyPm>();
		
		String sql = "SELECT " +
				"i.InstructionID " +
				", i.Sequence " +
				", i.InstructionName " +
				", i.Description14 OperationCode " +
				", i.Description15 Operation " +
				", i.Description16 Machine " +
				", ng.NeedleGauge Width " +
				", i.Description9 Comments " +
				", st.StitchType " +
				", i.Description11 SPI " +
				", i.Description8 ThreadText " +
				", i.Notes " +
				", iss.InstructionSet " +
				", iss.Version " +
				"FROM Styles s " +
				"LEFT JOIN Seasons s1 ON s.SeasonID = s1.SeasonID " +
				"LEFT JOIN Addresses a ON s.CustomerID = a.AddressID " +
				"LEFT JOIN InstructionSets iss ON s.StyleID = iss.StyleID " +
				"LEFT JOIN Instructions i ON iss.InstructionSetID = i.InstructionSetID " +
				"LEFT JOIN InstructionTypes it ON iss.InstructionTypeID = it.InstructionTypeID " +
				"LEFT JOIN NeedleGauges ng ON i.NeedleGaugeID = ng.NeedleGaugeID " +
				"LEFT JOIN StitchTypes st ON i.StitchTypeID = st.StitchTypeID " +
				"WHERE iss.InstructionTypeID = 2 " +
				"AND iss.StatusID In(10,15,40) " + 
				"AND s.StyleNumber = :style " ;
		
			if(style.getSeasonName()!=null) {
				sql +=	"AND s1.SeasonName = :season " ;
			} else {
				sql +=	"AND s1.SeasonName is null " ;
			}	
		
		sql +=	"ORDER BY i.Sequence";
		
		Query query = getSession().createSQLQuery(sql).addEntity(InstructionPolyPm.class);
		query.setParameter("style", style.getStyleNumber());
		
		if(style.getSeasonName()!=null) {
			query.setParameter("season", style.getSeasonName());
		}
		
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<InstructionPolyPm>(query.list());
		}
		
		
		return resultados;
	}
	
	
	
}
