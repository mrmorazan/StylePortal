package com.grupobeta.styleportal.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.Usuario;


	public interface UsuarioDao extends Dao<Usuario, Long>{
		Usuario loadByCod(String codUsuario);
		Usuario loadByCodEstado(String codUsuario);

		@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
		Usuario loadByEmail(String email);
		List<Usuario> find(String input, String codRol);
		List<Usuario> find(String input);

		List<String> loadUserCodeFromPlant(String planCode);
		
		@Transactional(rollbackFor = { GBException.class, GBMultipleException.class })
		String loadUserDA(String user);



}
