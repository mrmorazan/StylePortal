package com.grupobeta.styleportal.component;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.IConverter;

import com.grupobeta.styleportal.domain.Usuario;
import com.grupobeta.styleportal.service.AdministracionService;
import com.grupobeta.wicket.GBAutoCompleteTextField;


public class UsuarioField extends GBAutoCompleteTextField<Usuario> {
	private static final long serialVersionUID = 1L;

	@SpringBean
	protected AdministracionService adminService;
	protected IConverter<Usuario> converter;
	protected String codRol;

	public UsuarioField(String id, IModel<Usuario> model) {
		super(id, model);
		init();
	}

	public UsuarioField(String id) {
		super(id);
		init();
	}

	public UsuarioField(String id, boolean required) {
		super(id, required);
		init();
	}

	public UsuarioField(String id, IModel<Usuario> object, boolean required) {
		super(id, object, required);
		init();
	}

	protected void init() {
		this.converter = new UsuarioConverter();
	}

	@Override
	protected List<Usuario> loadChoices(String input) {
		return getAdminService().findUsuarios(input, getCodRol());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C> IConverter<C> getConverter(Class<C> type){
		 return ((IConverter<C>) this.converter);
	};

	protected AdministracionService getAdminService() {
		return adminService;
	}

	protected void setAdminService(AdministracionService adminService) {
		this.adminService = adminService;
	}

	public String getCodRol() {
		return codRol;
	}

	public UsuarioField setCodRol(String codRol) {
		this.codRol = codRol;
		return this;
	}
}
