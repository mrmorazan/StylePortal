package com.grupobeta.styleportal.dao.hibernate;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.DocumentPolyPmDao;
import com.grupobeta.styleportal.domain.DocumentPolyPm;
import com.grupobeta.styleportal.domain.InstructionPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;

public class DocumentPolyPmDaoImpl extends AbstractHibernateDaoImpl<DocumentPolyPm, Integer> implements DocumentPolyPmDao {

	public DocumentPolyPmDaoImpl() {
		super(DocumentPolyPm.class);
	}

	@Override
	public DocumentPolyPm loadDocumentPolyPmComponent(String component) {
		DocumentPolyPm documentPolyPm = null;
		
		String sql ="SELECT TOP 1 " +
				"d.DocumentID " +
				",d.FilePath " +
				",d.TagID " +
				",d.FileName " +
				",d.FileType " +
				",ISNULL(REPLACE('file://GBSRVT39/Poly-Docs/PolyGB/Documents/'+ d.FilePath , '\\' , '/'),0) URLComponent " +
				"FROM Documents d " +
				"WHERE (d.StatusID IN (42, 62, 80, 100)) " +
				"AND (d.FileType IN ('\', 'bmp', 'gif', 'jpeg', 'jpg', 'png')) " +
				"AND (d.FilePath LIKE :componentUrl " +
				"AND d.TagID = 10 " +
				"AND d.FileType <> '\' " +
				"AND d.FileVersion = 0) " +
				"";
		
		Query query = getSession().createSQLQuery(sql).addEntity(DocumentPolyPm.class);
		
		String val = "ComponentLibrary\\"+component+"\\%";
		query.setParameter("componentUrl", val);
		
		if(query.uniqueResult()!=null) {
			documentPolyPm = (DocumentPolyPm) query.uniqueResult();
		}
		
		return documentPolyPm;
	}

	@Override
	public DocumentPolyPm loadDocumentPolyPmInstruction(StylePolyPm style,
			InstructionPolyPm instruction) {
		DocumentPolyPm documentPolyPm = null;
		
		String sql ="SELECT TOP 1 " +
				"d.DocumentID " +
				",d.FilePath " +
				",d.TagID " +
				",d.FileName " +
				",d.FileType " +
				",ISNULL(REPLACE('file://GBSRVT39/Poly-Docs/PolyGB/Documents/'+ d.FilePath , '\\' , '/'),0) URLComponent " +
				"FROM Documents d " +
				"WHERE (d.StatusID IN (42, 62, 80, 100)) " +
				"AND (d.FileType IN ('\', 'bmp', 'gif', 'jpeg', 'jpg', 'png')) " +
				"AND (d.FilePath LIKE :instructionUrl " +
				"AND d.TagID = 46 " +
				"AND d.FileType <> '\' " +
				"AND d.FileVersion = 0) " +
				"";
		
		
		
		Query query = getSession().createSQLQuery(sql).addEntity(DocumentPolyPm.class);
		
		String val = "";
		if(style.getSeasonName()!=null) {
			val ="Instructions\\Styles\\"+style.getStyleNumber()+style.getCustomerPolyPm().getCompanyNumber()+style.getSeasonName()+"\\";	
		} else {
			val ="Instructions\\Styles\\"+style.getStyleNumber()+style.getCustomerPolyPm().getCompanyNumber()+"\\";
		}
		
		val +=instruction.getInstructionSet()+"_"+instruction.getVersion()+"\\"+instruction.getInstructionName()+"\\%";
		//String val = "ComponentLibrary\\"+component+"\\%";
		
		query.setParameter("instructionUrl", val);
		
		if(query.uniqueResult()!=null) {
			documentPolyPm = (DocumentPolyPm) query.uniqueResult();
		}
		
		return documentPolyPm;
	}
	
	
	
}
