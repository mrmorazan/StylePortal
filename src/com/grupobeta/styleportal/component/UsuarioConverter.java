package com.grupobeta.styleportal.component;

import java.util.Locale;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.converter.AbstractConverter;

import com.grupobeta.styleportal.domain.Usuario;
import com.grupobeta.styleportal.service.AdministracionService;
import com.grupobeta.util.StringUtils;


public class UsuarioConverter extends AbstractConverter<Usuario> {
	private static final long serialVersionUID = 1L;

	@SpringBean
	protected AdministracionService adminService;

	public UsuarioConverter() {
		Injector.get().inject(this);
	}

	@Override
	public Usuario convertToObject(String value, Locale locale) {
		if (StringUtils.IsNullOrEmpty(value))
			return null;
		else {
			String codUsuario = value.split("-")[0].trim();
			Usuario usuario = getAdminService().loadByCodUsuario(codUsuario);
			if (usuario == null)
				throw newConversionException("'" + value + "' is not a valid System User.", value, locale);

			return usuario;
		}
	}


	public AdministracionService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdministracionService adminService) {
		this.adminService = adminService;
	}

	@Override
	protected Class<Usuario> getTargetType(){
		return Usuario.class;
	};
}
