package com.grupobeta.styleportal.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.grupobeta.styleportal.dao.LanguageDao;
import com.grupobeta.styleportal.domain.Language;

public class LanguageDaoImpl extends AbstractHibernateDaoImpl<Language, Integer> implements LanguageDao {
	
	public LanguageDaoImpl() {
		super(Language.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Language> loadAllLanguageActives() {
		List<Language> resultados = new ArrayList<Language>();
		
		String hql = "from Language where status = true";
		
		Query query = getSession().createQuery(hql);
		if(!query.list().isEmpty()) {
			resultados = new ArrayList<Language>(query.list());
		}
		
		return resultados;
	}

	@Override
	public Language loadByCode(String code) {
		Language language = null;
		
		String hql = "from Language where languageCode = :code";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("code", code);
		query.setMaxResults(1);
		
		if(query.uniqueResult()!=null) {
			language = (Language) query.uniqueResult();
		}
		
		return language;
	}

}
