package com.grupobeta.styleportal.service.hibernate;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

import com.grupobeta.errors.GBException;
import com.grupobeta.styleportal.StylePortalErrors;
import com.grupobeta.styleportal.dao.DictionaryDao;
import com.grupobeta.styleportal.dao.LanguageDao;
import com.grupobeta.styleportal.dao.RolDao;
import com.grupobeta.styleportal.dao.UsuarioDADao;
import com.grupobeta.styleportal.dao.UsuarioDao;
import com.grupobeta.styleportal.domain.Dictionary;
import com.grupobeta.styleportal.domain.Language;
import com.grupobeta.styleportal.domain.Rol;
import com.grupobeta.styleportal.domain.Usuario;
import com.grupobeta.styleportal.service.AdministracionService;
import com.grupobeta.util.EncryptionUtils;

public class AdministracionServiceImpl extends AbstractHibernateServiceImpl implements AdministracionService {
	private UsuarioDao usuarioDao;
	private RolDao rolDao;
	private UsuarioDADao usuarioDADao;
	private LanguageDao languageDao;
	private DictionaryDao dictionaryDao;
	
	public DictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}

	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}

	public LanguageDao getLanguageDao() {
		return languageDao;
	}

	public void setLanguageDao(LanguageDao languageDao) {
		this.languageDao = languageDao;
	}

	public UsuarioDADao getUsuarioDADao() {
		return usuarioDADao;
	}

	public void setUsuarioDADao(UsuarioDADao usuarioDADao) {
		this.usuarioDADao = usuarioDADao;
	}
	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public RolDao getRolDao() {
		return rolDao;
	}

	public void setRolDao(RolDao rolDao) {
		this.rolDao = rolDao;
	}
	
	@Override
	public void authenticate(String codUsuario, String password) throws GBException
	{
		Usuario usuario = getUsuarioDao().loadByCod(codUsuario);
		if (usuario == null)
			StylePortalErrors.USUARIO_NO_EXISTE.throwException();
		else if (!usuario.isActivo())
			StylePortalErrors.USUARIO_BLOQUEADO.throwException();
		else {
			password = EncryptionUtils.getHash(password);
			if (!usuario.getPassword().equals(password))
				StylePortalErrors.PASSWORD_INCORRECTO.throwException();
		}


	}

	@Override
	public void activarInactivarUsuario(String codUsuario) throws GBException {
		Usuario usuario = getUsuarioDao().loadByCodEstado(codUsuario);
		if (usuario != null) {
			usuario.toggleEstado();
			saveUsuario(usuario);
		}
	}

	
	@Override
	public void saveUsuario(Usuario usuario) throws GBException {
		if (usuario.getPassword().equals(Usuario.DEFAULT_PASSWORD))
			usuario.encryptPassword();
		usuarioDao.save(usuario);
	}


	@Override
	public Usuario loadByCodUsuario(String codUsuario) {
		return getUsuarioDao().loadByCod(codUsuario);
	}

	@Override
	public Usuario loadByCodUsuarioActivo(String codUsuario) {
		return getUsuarioDao().loadByCod(codUsuario);
	}

	@Override
	public void cambiarPasswordUsuario(String codUsuario, String nuevoPassword,
			String confirmPassword) throws GBException {
		if (nuevoPassword.equals(confirmPassword)) {
			Usuario usuario = getUsuarioDao().loadByCod(codUsuario);
			usuario.setPassword(nuevoPassword);
			usuario.encryptPassword();
			saveUsuario(usuario);
		} else
			StylePortalErrors.PASSWORD_NO_COINCIDE.throwException();

	}

	@Override
	public void cambiarPasswordUsuario(String codUsuario, String password,
			String nuevoPassword, String confirmPassword) throws GBException {
		authenticate(codUsuario, password);
		cambiarPasswordUsuario(codUsuario, nuevoPassword, confirmPassword);
	}

	@Override
	public List<Usuario> loadUsuarios() {
		return getUsuarioDao().loadAll();
	}

	@Override
	public List<Usuario> findUsuarios(String input, String codRol) {
		return getUsuarioDao().find(input, codRol);
	}

	@Override
	public List<Rol> loadRoles() {
		return getRolDao().loadAll();
	}

	@Override
	public void initUsuario(Usuario usuario, String url) throws GBException {
		if (usuario.getPassword().equals(Usuario.DEFAULT_PASSWORD))
			usuario.encryptPassword();
		getUsuarioDao().save(usuario);
		if (usuario.getCorreoElectronico() != null) {
			SimpleMailMessage message = newMessage();

			message.setTo(usuario.getCorreoElectronico());
			message.setCc("emeza@grupobeta.com");
			message.setBcc("emeza@grupobeta.com");
			message.setSubject("[StylePortal] Welcome to Style Portal");

			StringBuilder builder = new StringBuilder();

			builder.append("Welcome to StylePortal. your user is ");
			builder.append(usuario.getCodUsuario());
			builder.append(". Your password is the same as used to access in Windows.\n\n");

			builder.append("To access the StockMan can use the following link:");
			builder.append(url);

			message.setText(builder.toString());
			sendMessage(message);
		}
	}

	@Override
	public void removerUsuarioRol(Rol rol, Usuario usuario) {
	//	Rol roles = getRolDao().loadByCod(rol.getCodRol());
//		rol.getUsuarios().remove(usuario);
//		getRolDao().save(rol);
		rol = getRolDao().loadByCod(rol.getCodRol());
		usuario = getUsuarioDao().loadByCod(usuario.getCodUsuario());
		rol.getUsuarios().remove(usuario);
		getRolDao().save(rol);
	}

	@Override
	public void agregarUsuarioRol(Rol rol, Usuario usuario) {
		Rol roles = getRolDao().loadByCod(rol.getCodRol());
		roles.getUsuarios().add(usuario);
		getRolDao().save(roles);

	}

	@Override
	public Rol loadRol(int idRol) {
		return getRolDao().load(idRol);
	}

	@Override
	public boolean authenticateDA(String username, String password) throws GBException {
		if(getUsuarioDADao().isAuthenticate(username, password)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void saveRol(Rol rol) {
		getRolDao().save(rol);
	}

	@Override
	public List<Usuario> findUsuarios(String input) {
		return getUsuarioDao().find(input);
	}
	
	@Override
	public List<Usuario> find(String input, boolean status) {
		return getUsuarioDao().find(input, status);
	}

	@Override
	public List<Language> loadAllLanguage() {
		return getLanguageDao().loadAll();
	}

	@Override
	public List<Language> loadAllLanguageActives() {
		return getLanguageDao().loadAll();
	}

	@Override
	public void saveLanguage(Language language) {
		getLanguageDao().save(language);
		
	}

	@Override
	public Language loadLanguagebyCode(String code) {
		return getLanguageDao().loadByCode(code);
	}

	@Override
	public List<Dictionary> loadAllDictionary() {
		return getDictionaryDao().loadAllDictionary();
	}

	@Override
	public void saveDictionary(Dictionary dictionary) {
		getDictionaryDao().save(dictionary);
		
	}

	@Override
	public void deleteDictionary(Dictionary dictionary) {
		getDictionaryDao().delete(dictionary);
		
	}

	@Override
	public List<Dictionary> loadAllDictionaryForSearch(Language language, String originalText) {
		return getDictionaryDao().loadAllDictionaryForSearch(language, originalText);
	}

	
}
