package com.grupobeta.styleportal.app.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;

import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.Rol;
import com.grupobeta.wicket.CompoundPropertyForm;
import com.grupobeta.wicket.GBRequiredTextField;
import com.grupobeta.wicket.GBSubmitButton;
import com.grupobeta.wicket.PageablePropertyListView;

public class AdminRolPage extends MantenimientoBasePage<Rol> {
	private static final long serialVersionUID = 1L;

	public AdminRolPage(){
		final PageablePropertyListView<Rol> table = new PageablePropertyListView<Rol>("table") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Rol> loadItems() {
				return new ArrayList<Rol>(getAdminService().loadRoles());
			}

			@Override
			protected void populateItem(Item<Rol> item) {
				item.add(new Label("codRol"));
				item.add(new Label("nombre"));
				item.add(new Link<Void>("edit"){
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setSelectedObject(item.getModelObject());
						setAction(Action.EDIT);
					}

					@Override
					public boolean isVisible(){
						return super.isVisible() ;
					}

				});
			}

			@Override
			public boolean isVisible() {
				return super.isVisible() && getAction() == Action.VIEW;
			}

		};
		table.setRowsPerPage(10);
		this.add(table);

		this.add(new PagingNavigator("navigator", table));

		final Form<Rol> form = new CompoundPropertyForm<Rol>("form", getSelectedObjectModel()){
			private static final long serialVersionUID = 1L;

		/*	@Override
			protected void onSubmit() {
				getAdminService().saveRol(getSelectedObject());
				table.detachItems();
				setAction(Action.VIEW);
			}*/

			@Override
			public boolean isVisible() {
				return super.isVisible() && (getAction() == Action.ADD || getAction() == Action.EDIT);
			}

		};

		form.add(new GBRequiredTextField("codRol"));
		form.add(new GBRequiredTextField("nombre"));
		
		form.add(new GBSubmitButton("submit") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				getAdminService().saveRol(getSelectedObject());
				table.detachItems();
				setAction(Action.VIEW);
				setResponsePage(AdminRolPage.class);
			}
			
			@Override
			protected void onError(AjaxRequestTarget target) {
				target.add(getFeedbackPanel());
			}
			
		});

		form.add(new Link<Void>("cancel"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setAction(Action.VIEW);
			}
		});

		this.add(form);
		this.add(newAddLink("add1"));
		this.add(newAddLink("add2"));

	}

	protected Link<Void> newAddLink(String id) {
		return new Link<Void>(id) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setSelectedObject(new Rol());
				setAction(Action.ADD);
			}

			@Override
			public boolean isVisible(){
				return super.isVisible() && getAction()== Action.VIEW;
			}
		};
	}


}
