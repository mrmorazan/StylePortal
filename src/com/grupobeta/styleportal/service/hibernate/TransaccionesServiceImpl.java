package com.grupobeta.styleportal.service.hibernate;

import java.util.List;

import com.grupobeta.styleportal.dao.CustomerPolyPmDao;
import com.grupobeta.styleportal.dao.DocumentPolyPmDao;
import com.grupobeta.styleportal.dao.MaterialPolyPmDao;
import com.grupobeta.styleportal.dao.SeasonPolyPmDao;
import com.grupobeta.styleportal.dao.StylePolyPmDao;
import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.domain.DocumentPolyPm;
import com.grupobeta.styleportal.domain.MaterialPolyPm;
import com.grupobeta.styleportal.domain.SeasonPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.service.TransaccionesService;

public class TransaccionesServiceImpl extends AbstractHibernateServiceImpl implements TransaccionesService {
	private CustomerPolyPmDao customerPolyPmDao;
	private StylePolyPmDao stylePolyPmDao;
	private SeasonPolyPmDao seasonPolyPmDao;
	private MaterialPolyPmDao materialPolyPmDao;
	private DocumentPolyPmDao documentPolyPmDao;
	
	
	
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
	
	
	
	
	
}