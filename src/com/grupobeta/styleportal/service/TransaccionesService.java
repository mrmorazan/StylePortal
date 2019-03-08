package com.grupobeta.styleportal.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.ArteBam;
import com.grupobeta.styleportal.domain.ColorBam;
import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.domain.DocumentPolyPm;
import com.grupobeta.styleportal.domain.ImagenBam;
import com.grupobeta.styleportal.domain.ImagenTallaBAM;
import com.grupobeta.styleportal.domain.InstructionPolyPm;
import com.grupobeta.styleportal.domain.MaterialPolyPm;
import com.grupobeta.styleportal.domain.PackingManual;
import com.grupobeta.styleportal.domain.SeasonPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.domain.TecnicaBam;
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
	
	DocumentPolyPm loadDocumentPolyPmSpec(StylePolyPm style);
	
	DocumentPolyPm loadDocumentPolyPmTechPack(StylePolyPm style);
	
	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void savePackingManual(PackingManual packingManual);
	
	List<PackingManual> loadAllPackingManualFrom(String customer, String season, String style);
	List<PackingManual> loadAllPackingManualFrom(String customer, String season);
	List<PackingManual> loadAllPackingManualFromStyle(String customer, String style);
	List<PackingManual> loadAllPackingManualFromFilter(String customer, String season, String style);
	
	List<SeasonPolyPm> loadAllSeasonPolyPmFromCustomer(String customerCode);
	
	SeasonPolyPm loadSeason(int seasonId);
	
	CustomerPolyPm loadCustoemrPolyPM(int customerId);
	
	List<ArteBam> loadArtesFromBam(String customerCode, String estilo, String season);
	
	List<ImagenBam> loadAllImagenBamFromArte(ArteBam arteBam);
	
	List<ImagenTallaBAM> loadAllImagenesTallaBAM(ImagenBam imagenBam);
	
	List<ColorBam> loadAllColorFromImage(ImagenBam imagenBam);
	
	List<TecnicaBam> loadAllTecnicasFromImage(ImagenBam imagenBam);
	
}
