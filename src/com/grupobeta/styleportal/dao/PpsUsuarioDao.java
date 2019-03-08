package com.grupobeta.styleportal.dao;

import org.springframework.transaction.annotation.Transactional;

import com.grupobeta.errors.GBException;
import com.grupobeta.errors.GBMultipleException;
import com.grupobeta.styleportal.domain.PpsUsuario;

public interface PpsUsuarioDao extends Dao<PpsUsuario, String> {
	
	@Transactional(value="transactionManagerPPS", rollbackFor = { GBException.class, GBMultipleException.class })
	PpsUsuario getUsuario(String usuario);
	
}
