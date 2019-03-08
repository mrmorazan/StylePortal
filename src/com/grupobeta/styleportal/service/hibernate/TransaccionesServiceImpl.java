package com.grupobeta.styleportal.service.hibernate;

import java.util.List;

import com.grupobeta.styleportal.dao.ArteBamDao;
import com.grupobeta.styleportal.dao.ColorBamDao;
import com.grupobeta.styleportal.dao.CustomerPolyPmDao;
import com.grupobeta.styleportal.dao.DocumentPolyPmDao;
import com.grupobeta.styleportal.dao.ImagenBamDao;
import com.grupobeta.styleportal.dao.ImagenTallaBAMDao;
import com.grupobeta.styleportal.dao.InstructionPolyPmDao;
import com.grupobeta.styleportal.dao.MaterialPolyPmDao;
import com.grupobeta.styleportal.dao.PackingManualDao;
import com.grupobeta.styleportal.dao.SeasonPolyPmDao;
import com.grupobeta.styleportal.dao.StylePolyPmDao;
import com.grupobeta.styleportal.dao.TecnicaBamDao;
import com.grupobeta.styleportal.dao.WorkTaskPolyPmDao;
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
import com.grupobeta.styleportal.service.TransaccionesService;

public class TransaccionesServiceImpl extends AbstractHibernateServiceImpl implements TransaccionesService {
	private CustomerPolyPmDao customerPolyPmDao;
	private StylePolyPmDao stylePolyPmDao;
	private SeasonPolyPmDao seasonPolyPmDao;
	private MaterialPolyPmDao materialPolyPmDao;
	private DocumentPolyPmDao documentPolyPmDao;
	private InstructionPolyPmDao instructionPolyPmDao;
	private WorkTaskPolyPmDao workTaskPolyPmDao;
	private PackingManualDao packingManualDao;
	private ArteBamDao arteBamDao;
	private ImagenBamDao imagenBamDao;
	private ImagenTallaBAMDao imagenTallaBAMDao;
	private ColorBamDao colorBamDao;
	private TecnicaBamDao tecnicaBamDao;
	
	public ColorBamDao getColorBamDao() {
		return colorBamDao;
	}

	public void setColorBamDao(ColorBamDao colorBamDao) {
		this.colorBamDao = colorBamDao;
	}

	public TecnicaBamDao getTecnicaBamDao() {
		return tecnicaBamDao;
	}

	public void setTecnicaBamDao(TecnicaBamDao tecnicaBamDao) {
		this.tecnicaBamDao = tecnicaBamDao;
	}

	public ImagenTallaBAMDao getImagenTallaBAMDao() {
		return imagenTallaBAMDao;
	}

	public void setImagenTallaBAMDao(ImagenTallaBAMDao imagenTallaBAMDao) {
		this.imagenTallaBAMDao = imagenTallaBAMDao;
	}

	public ArteBamDao getArteBamDao() {
		return arteBamDao;
	}

	public void setArteBamDao(ArteBamDao arteBamDao) {
		this.arteBamDao = arteBamDao;
	}

	public ImagenBamDao getImagenBamDao() {
		return imagenBamDao;
	}

	public void setImagenBamDao(ImagenBamDao imagenBamDao) {
		this.imagenBamDao = imagenBamDao;
	}

	public PackingManualDao getPackingManualDao() {
		return packingManualDao;
	}

	public void setPackingManualDao(PackingManualDao packingManualDao) {
		this.packingManualDao = packingManualDao;
	}

	public WorkTaskPolyPmDao getWorkTaskPolyPmDao() {
		return workTaskPolyPmDao;
	}

	public void setWorkTaskPolyPmDao(WorkTaskPolyPmDao workTaskPolyPmDao) {
		this.workTaskPolyPmDao = workTaskPolyPmDao;
	}

	public InstructionPolyPmDao getInstructionPolyPmDao() {
		return instructionPolyPmDao;
	}

	public void setInstructionPolyPmDao(InstructionPolyPmDao instructionPolyPmDao) {
		this.instructionPolyPmDao = instructionPolyPmDao;
	}

	public DocumentPolyPmDao getDocumentPolyPmDao() {
		return documentPolyPmDao;
	}

	public void setDocumentPolyPmDao(DocumentPolyPmDao documentPolyPmDao) {
		this.documentPolyPmDao = documentPolyPmDao;
	}

	public MaterialPolyPmDao getMaterialPolyPmDao() {
		return materialPolyPmDao;
	}

	public void setMaterialPolyPmDao(MaterialPolyPmDao materialPolyPmDao) {
		this.materialPolyPmDao = materialPolyPmDao;
	}

	public SeasonPolyPmDao getSeasonPolyPmDao() {
		return seasonPolyPmDao;
	}

	public void setSeasonPolyPmDao(SeasonPolyPmDao seasonPolyPmDao) {
		this.seasonPolyPmDao = seasonPolyPmDao;
	}

	public StylePolyPmDao getStylePolyPmDao() {
		return stylePolyPmDao;
	}

	public void setStylePolyPmDao(StylePolyPmDao stylePolyPmDao) {
		this.stylePolyPmDao = stylePolyPmDao;
	}

	public CustomerPolyPmDao getCustomerPolyPmDao() {
		return customerPolyPmDao;
	}

	public void setCustomerPolyPmDao(CustomerPolyPmDao customerPolyPmDao) {
		this.customerPolyPmDao = customerPolyPmDao;
	}

	@Override
	public List<CustomerPolyPm> loadAllCustomerActivesPolyPm(String customerCode, boolean inactives) {
		return getCustomerPolyPmDao().loadAllCustomerActivesPolyPm(customerCode, inactives);
	}

	@Override
	public List<StylePolyPm> loadAllStylePolyPmFromCustomerStyleSeason(CustomerPolyPm customerPolyPm, String style,
			String season) {
		return getStylePolyPmDao().loadAllStylePolyPmFromCustomerStyleSeason(customerPolyPm, style, season);
	}

	@Override
	public List<SeasonPolyPm> loadAllSeasonPolyPmFromStyle(String style) {
		return getSeasonPolyPmDao().loadAllSeasonPolyPmFromStyle(style);
	}

	@Override
	public StylePolyPm loadStylePolyPm(int styleId, String season) {
		return getStylePolyPmDao().loadStylePolyPm(styleId, season);
	}

	@Override
	public List<MaterialPolyPm> loadListMaterialPolyPmFromCategory(StylePolyPm style, int categoryId) {
		return getMaterialPolyPmDao().loadListMaterialPolyPmFromCategory(style, categoryId);
	}

	@Override
	public DocumentPolyPm loadDocumentPolyPmComponent(String component) {
		return getDocumentPolyPmDao().loadDocumentPolyPmComponent(component);
	}

	@Override
	public List<InstructionPolyPm> loadInstructionsForStyle(StylePolyPm style) {
		return getInstructionPolyPmDao().loadInstructionsForStyle(style);
	}

	@Override
	public DocumentPolyPm loadDocumentPolyPmInstruction(StylePolyPm style, InstructionPolyPm instruction) {
		return getDocumentPolyPmDao().loadDocumentPolyPmInstruction(style, instruction);
	}

	@Override
	public List<WorkTaskPolyPm> loadWorkTaskFromStyleSeason(StylePolyPm style) {
		return getWorkTaskPolyPmDao().loadWorkTaskFromStyleSeason(style);
	}

	@Override
	public DocumentPolyPm loadDocumentPolyPmSpec(StylePolyPm style) {
		return getDocumentPolyPmDao().loadDocumentPolyPmSpec(style);
	}

	@Override
	public DocumentPolyPm loadDocumentPolyPmTechPack(StylePolyPm style) {
		return getDocumentPolyPmDao().loadDocumentPolyPmTechPack(style);
	}

	@Override
	public void savePackingManual(PackingManual packingManual) {
		getPackingManualDao().save(packingManual);
		
	}

	@Override
	public List<PackingManual> loadAllPackingManualFrom(String customer, String season, String style) {
		return getPackingManualDao().loadAllPackingManualFrom(customer, season, style);
	}

	@Override
	public List<PackingManual> loadAllPackingManualFrom(String customer, String season) {
		return getPackingManualDao().loadAllPackingManualFrom(customer, season);
	}

	@Override
	public List<PackingManual> loadAllPackingManualFromFilter(String customer, String season, String style) {
		return getPackingManualDao().loadAllPackingManualFromFilter(customer, season, style);
	}

	@Override
	public List<SeasonPolyPm> loadAllSeasonPolyPmFromCustomer(String customerCode) {
		return getSeasonPolyPmDao().loadAllSeasonPolyPmFromCustomer(customerCode);
	}

	@Override
	public SeasonPolyPm loadSeason(int seasonId) {
		return getSeasonPolyPmDao().loadSeason(seasonId);
	}

	@Override
	public CustomerPolyPm loadCustoemrPolyPM(int customerId) {
		return getCustomerPolyPmDao().loadCustoemrPolyPM(customerId);
	}

	@Override
	public List<ArteBam> loadArtesFromBam(String customerCode, String estilo, String season) {
		return getArteBamDao().loadArtesFromBam(customerCode, estilo, season);
	}

	@Override
	public List<ImagenBam> loadAllImagenBamFromArte(ArteBam arteBam) {
		return getImagenBamDao().loadAllImagenBamFromArte(arteBam);
	}

	@Override
	public List<ImagenTallaBAM> loadAllImagenesTallaBAM(ImagenBam imagenBam) {
		return getImagenTallaBAMDao().loadAllImagenesTallaBAM(imagenBam);
	}

	@Override
	public List<ColorBam> loadAllColorFromImage(ImagenBam imagenBam) {
		return getColorBamDao().loadAllColorFromImage(imagenBam);
	}

	@Override
	public List<TecnicaBam> loadAllTecnicasFromImage(ImagenBam imagenBam) {
		return getTecnicaBamDao().loadAllTecnicasFromImage(imagenBam);
	}

	@Override
	public List<PackingManual> loadAllPackingManualFromStyle(String customer, String style) {
		return getPackingManualDao().loadAllPackingManualFromStyle(customer, style);
	}
	
	
	
	
	
}