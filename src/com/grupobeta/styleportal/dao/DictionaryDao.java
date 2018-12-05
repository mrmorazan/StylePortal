package com.grupobeta.styleportal.dao;

import java.util.List;

import com.grupobeta.styleportal.domain.Dictionary;
import com.grupobeta.styleportal.domain.Language;

public interface DictionaryDao extends Dao<Dictionary, Integer> {
	
	List<Dictionary> loadAllDictionary();
	List<Dictionary> loadAllDictionaryForSearch(Language language, String originalText);
	List<Dictionary> loadAllDictionaryGeneralFromLanguage(Language language);
	List<Dictionary> loadAllDictionaryAllFromLanguageAndMessageSource(Language language, String messageSource);
	List<Dictionary> loadAllDictionaryGeneralFromLanguageAndOriginalText(Language language, String originalText);
	List<Dictionary> loadAllDictionaryAllFromLanguageAndMessageSourceAndOriginalText(Language language, String messageSource, String originalText);
	
	

}
