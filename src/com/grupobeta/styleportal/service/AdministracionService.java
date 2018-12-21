package com.grupobeta.styleportal.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.Dictionary;
import com.grupobeta.styleportal.domain.Language;
import com.grupobeta.styleportal.domain.Rol;
import com.grupobeta.styleportal.domain.Usuario;

public interface AdministracionService extends Service {
	Usuario loadByCodUsuario(String Nombre);
	Usuario loadByCodUsuarioActivo(String codUsuario);

	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void authenticate(String Nombre, String password) throws GBException;

	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	boolean authenticateDA(String Nombre, String password) throws GBException;

	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void activarInactivarUsuario(String Nombre) throws GBException;
	
	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void saveUsuario(Usuario usuario) throws GBException;

	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void cambiarPasswordUsuario(String codUsuario, String nuevoPassword, String confirmPassword) throws GBException;

	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void cambiarPasswordUsuario(String codUsuario, String password, String nuevoPassword, String confirmPassword) throws GBException;

	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void removerUsuarioRol(Rol rol, Usuario usuario);

	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void agregarUsuarioRol(Rol rol, Usuario usuario);

	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void saveRol(Rol rol);

	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void initUsuario(Usuario usuario, String url) throws GBException;

	List<Usuario> loadUsuarios();

	List<Usuario> findUsuarios(String input, String codRol);
	List<Usuario> findUsuarios(String input);
	List<Usuario> find(String input, boolean status);

	List<Rol> loadRoles();

	Rol loadRol(int idRol);
	
	List<Language> loadAllLanguage();
	List<Language> loadAllLanguageActives();
	
	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void saveLanguage(Language language);
	
	Language loadLanguagebyCode(String code);
	
	List<Dictionary> loadAllDictionary();
	List<Dictionary> loadAllDictionaryForSearch(Language language, String originalText);
	
	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void saveDictionary(Dictionary dictionary);
	
	@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
	void deleteDictionary(Dictionary dictionary);
	
}
