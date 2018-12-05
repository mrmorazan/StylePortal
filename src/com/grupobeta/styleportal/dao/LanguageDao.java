package com.grupobeta.styleportal.dao;

import java.util.List;

import com.grupobeta.styleportal.domain.Language;

public interface LanguageDao extends Dao<Language, Integer> {

	List<Language> loadAllLanguageActives();
	
	Language loadByCode(String code);
	
}
