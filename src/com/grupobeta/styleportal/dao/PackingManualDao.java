package com.grupobeta.styleportal.dao;

import java.util.List;

import com.grupobeta.styleportal.domain.PackingManual;

public interface PackingManualDao extends Dao<PackingManual, Long> {
	
	List<PackingManual> loadAllPackingManualFrom(String customer, String season, String style);
	List<PackingManual> loadAllPackingManualFrom(String customer, String season);
	List<PackingManual> loadAllPackingManualFromStyle(String customer, String style);
	List<PackingManual> loadAllPackingManualFromFilter(String customer, String season, String style);

}
