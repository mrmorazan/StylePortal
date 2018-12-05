package com.grupobeta.styleportal.app.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;

import com.grupobeta.styleportal.component.ConfirmLink;
import com.grupobeta.styleportal.component.UsuarioField;
import com.grupobeta.styleportal.domain.Rol;
import com.grupobeta.styleportal.domain.Usuario;
import com.grupobeta.wicket.CompoundPropertyForm;
import com.grupobeta.wicket.FocusOnLoadBehavior;
import com.grupobeta.wicket.PageablePropertyListView;


public class RolesPage extends AdminBasePage<Usuario> {
	private static final long serialVersionUID = 1L;
	protected Rol rol;
	protected Usuario usuario;
	DropDownChoice<Rol> ddRol;
	public RolesPage() {
		final WebMarkupContainer tableRefresh = new WebMarkupContainer("tableRefresh");
		tableRefresh.setOutputMarkupId(true);
		final PageablePropertyListView<Usuario> table = new PageablePropertyListView<Usuario>("table"){
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Usuario> loadItems() {
				if (getRol() == null)
					return new ArrayList<Usuario>(0);
				else {
					setRol(getAdminService().loadRol(getRol().getIdRol()));
					return new ArrayList<Usuario>(getRol().getUsuarios());
				}
			}

			@Override
			protected void populateItem(final Item<Usuario> item) {
				item.add(new Label("usuario", item.getModelObject().toString()));
				item.add(new ConfirmLink("remove", "remove", "user") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						getAdminService().removerUsuarioRol(getRol(), item.getModelObject());
						detachItems();
					}
				});
			}
		};
		tableRefresh.add(table);

		final Form<RolesPage> form = new CompoundPropertyForm<RolesPage>("form", this){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				getAdminService().agregarUsuarioRol(getRol(), getUsuario());
				table.detachItems();
				setUsuario(null);
			}
		};


	form.add(tableRefresh);

	List<Rol> listRol = new ArrayList<Rol>(getAdminService().loadRoles());

	form.add(ddRol = (DropDownChoice<Rol>) new DropDownChoice<Rol>("rol", listRol).setRequired(true));
		ddRol.add(new FocusOnLoadBehavior());
		ddRol.add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				table.detachItems();
				target.add(getFeedbackPanel());
				target.add(tableRefresh);
			}
		});


		tableRefresh.add(new UsuarioField("usuario").setRequired(true));
		this.add(form);
	}

	protected Rol getRol() {
		return rol;
	}

	protected void setRol(Rol rol) {
		this.rol = rol;
	}

	protected Usuario getUsuario() {
		return usuario;
	}

	protected void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

