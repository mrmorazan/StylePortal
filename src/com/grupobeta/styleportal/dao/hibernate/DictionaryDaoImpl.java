package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.DictionaryDao;
import com.grupobeta.styleportal.domain.Dictionary;
import com.grupobeta.styleportal.domain.Language;

public class DictionaryDaoImpl extends AbstractHibernateDaoImpl<Dictionary, Integer> implements DictionaryDao {
	
	public DictionaryDaoImpl() {
		super(Dictionary.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> loadAllDictionary() {
		List<Dictionary> resultados = new ArrayList<Dictionary>();
		String hql = "from Dictionary";
		
		Query query = getSession().createQuery(hql);
		resultados = new ArrayList<Dictionary>(query.list());
		
		return resultados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> loadAllDictionaryGeneralFromLanguage(Language language) {
		List<Dictionary> resultados = new ArrayList<Dictionary>();
		String hql = "from Dictionary where language = :language and isGeneral = true ";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("language", language);
		
		resultados = new ArrayList<Dictionary>(query.list());
		
		return resultados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> loadAllDictionaryAllFromLanguageAndMessageSource(Language language, String messageSource) {
		List<Dictionary> resultados = new ArrayList<Dictionary>();
		String hql = "from Dictionary where language = :language and messageSource = :messageSource ";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("language", language);
		query.setParameter("messageSource", messageSource);
		
		resultados = new ArrayList<Dictionary>(query.list());
		
		return resultados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> loadAllDictionaryGeneralFromLanguageAndOriginalText(Language language,
			String originalText) {
		List<Dictionary> resultados = new ArrayList<Dictionary>();
		String hql = "from Dictionary where language = :language and originalText = :originalText ";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("language", language);
		query.setParameter("originalText", originalText);
		
		resultados = new ArrayList<Dictionary>(query.list());
		
		return resultados;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> loadAllDictionaryForSearch(Language language, String originalText) {
		List<Dictionary> resultados = new ArrayList<Dictionary>();
		
		String hql = "from Dictionary ";
		if(language!=null || originalText!=null) {
			hql += " where ";
			
			if(language!=null) {
				hql += " language = :language ";
				
				if(originalText!=null) {
					hql +=" and ";
				}
			}
			
			if(originalText!=null) {
				hql +=" originalText like :originalText ";
			}
			
		}
		
		Query query = getSession().createQuery(hql);
		
		if(language!=null) {
			query.setParameter("language", language);
		}
		
		if(originalText!=null) {
			query.setParameter("originalText", "%"+originalText+"%");
		}
		
		
		resultados = new ArrayList<Dictionary>(query.list());
		
		return resultados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> loadAllDictionaryAllFromLanguageAndMessageSourceAndOriginalText(Language language,
			String messageSource, String originalText) {
		List<Dictionary> resultados = new ArrayList<Dictionary>();
		String hql = "from Dictionary where language = :language and messageSource = :messageSource and originalText = :originalText ";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("language", language);
		query.setParameter("messageSource", messageSource);
		query.setParameter("originalText", originalText);
		
		resultados = new ArrayList<Dictionary>(query.list());
		
		return resultados;
	}

	

}
