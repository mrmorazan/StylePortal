package com.grupobeta.styleportal.service;

import java.util.List;

import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.domain.DocumentPolyPm;
import com.grupobeta.styleportal.domain.InstructionPolyPm;
import com.grupobeta.styleportal.domain.MaterialPolyPm;
import com.grupobeta.styleportal.domain.SeasonPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.domain.WorkTaskPolyPm;

public interface TransaccionesService extends Service {

	List<CustomerPolyPm> loadAllCustomerActivesPolyPm(String customerCode, boolean inactives);
	
	List<StylePolyPm> loadAllStylePolyPmFromCustomerStyleSeason(CustomerPolyPm customerPolyPm, String style, String season);
	
	List<SeasonPolyPm> loadAllSeasonPolyPmFromStyle(String style);
	
	StylePolyPm loadStylePolyPm(int styleId, String season);
	
	List<MaterialPolyPm> loadListMaterialPolyPmFromCategory(StylePolyPm style, int categoryId);
	
	DocumentPolyPm loadDocumentPolyPmComponent(String component);
	
	List<InstructionPolyPm> loadInstructionsForStyle(StylePolyPm style);
	
	DocumentPolyPm loadDocumentPolyPmInstruction(StylePolyPm style, InstructionPolyPm instruction);
	
	List<WorkTaskPolyPm> loadWorkTaskFromStyleSeason(StylePolyPm style);
	
}
