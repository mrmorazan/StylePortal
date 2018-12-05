package com.grupobeta.styleportal.dao;

import com.grupobeta.styleportal.domain.Rol;

public interface RolDao extends Dao<Rol, Integer>{
	Rol loadByCod(String input);
}
