package com.grupobeta.styleportal.app.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;


import com.grupobeta.errors.GBException;
import com.grupobeta.styleportal.app.BaseApplication;
import com.grupobeta.styleportal.app.StylePortalSession;
import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.Usuario;
import com.grupobeta.wicket.CompoundPropertyForm;
import com.grupobeta.wicket.EmailAddressTextField;
import com.grupobeta.wicket.FocusOnLoadBehavior;
import com.grupobeta.wicket.GBRequiredTextField;
import com.grupobeta.wicket.GBSubmitButton;
import com.grupobeta.wicket.GBTextField;

import com.grupobeta.wicket.PageablePropertyListView;

public class UsersPage extends AdminBasePage<Usuario> {
	private static final long serialVersionUID = 1L;
	protected String searchUser = null;
	protected GBTextField txtSearch;
	GBRequiredTextField txcodusuario;
	GBRequiredTextField txnombre;
	protected Boolean status;

	public UsersPage() {
		setStatus(Boolean.FALSE);
		final PageablePropertyListView<Usuario> table = new PageablePropertyListView<Usuario>(
				"table") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Usuario> loadItems() {
				if(getSearchUser()==null && getStatus()==null){
					return new ArrayList<Usuario>(getAdminService().loadUsuarios());
					} else {
						
						return new ArrayList<Usuario>(
								getAdminService().find(getSearchUser(), !getStatus())
								);
					}
				
			}

			@Override
			protected void populateItem(final Item<Usuario> item) {
				item.add(new Label("codUsuario"));
				item.add(new Label("nombre"));
				item.add(new Label("correoElectronico"));
				item.add(new Label("department"));
				item.add(new ContextImage("estado", item.getModelObject().isActivo() ? "img/fa-check.png" : "img/fa-cancel.png"));
				item.add(new Link<Void>("toggle") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						try {
							getAdminService().activarInactivarUsuario(item.getModelObject().getCodUsuario());
							detachItems();
						} catch (GBException ex) {
							error(ex.toString());
						}
					}

					@Override
					public boolean isEnabled() {
						return super.isEnabled() && !item.getModelObject().equals(StylePortalSession.get().getUsuario());
					}
				}.add(new Label("toggleLabel", Model.of(item.getModelObject().isActivo() ? "Active" : "Inactive" ))));
				
				
				
				item.add(new Link<Void>("edit") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setSelectedObject(item.getModelObject());
						setAction(Action.EDIT);
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


		Form<UsersPage> formSearch = new CompoundPropertyForm<UsersPage>(
				"formSearch", this) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return super.isVisible() && getAction() == Action.VIEW;
			}
		};
		txtSearch = new GBTextField("txtSearch", Model.of(""));
		formSearch.add(txtSearch);
		formSearch.add(new GBSubmitButton("submit2") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				super.onSubmit(target);
				if (getStatus()==null) {
					setStatus(Boolean.TRUE);
				}
				
				setSearchUser((String) txtSearch.getDefaultModelObject());
				table.detachItems();
				target.add(this.getPage());
			}
		});
		
		formSearch.add(new AjaxCheckBox("status") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				
				
			}
			
		});
		
		formSearch.add(new Link<Void>("cancel2") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setAction(Action.VIEW);
				setResponsePage(UsersPage.class);
			}
		});

		this.add(formSearch);
		
		final Form<Usuario> form = new CompoundPropertyForm<Usuario>("form",
				getSelectedObjectModel()) {
			private static final long serialVersionUID = 1L;
			

			@Override
			public boolean isVisible() {
				return super.isVisible() && getAction() != Action.VIEW;
			}
		};

		this.add(form);

		
		txcodusuario = (GBRequiredTextField) new GBRequiredTextField("codUsuario", 50){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() {
				return super.isEnabled() && getAction() == Action.ADD;
			}
		}.add(new FocusOnLoadBehavior());
		
		txcodusuario.setOutputMarkupId(true);
		
		txcodusuario.add(new AjaxFormComponentUpdatingBehavior("blur") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
			/*	String codigo = getComponent().getDefaultModelObject().toString();
			//	String ppsdata = getTransService().getUsuario(codigo);
				if (ppsdata != null){
					getSelectedObject().setNombre(ppsdata);
					target.add(txnombre);
				}else{
					//error("User: " + getSelectedObject().getCodUsuario() + " does not exist !!");
				}*/
				
			}

		});
		form.add(txcodusuario);
	
		txnombre = (GBRequiredTextField) new GBRequiredTextField("nombre", 100);
		txnombre.setOutputMarkupId(true);
		form.add(txnombre);
		form.add(new EmailAddressTextField("correoElectronico", 100));
		form.add(new GBTextField("department"));
		
		form.add(new Link<Void>("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setAction(Action.VIEW);
				setResponsePage(UsersPage.class);
			}
		});
		
		form.add(new GBSubmitButton("submit") {
			private static final long serialVersionUID = 1L;
			
		@Override
		protected void onSubmit(AjaxRequestTarget target) {
			if (getAction() == Action.ADD) {
				Usuario validator = getAdminService().loadByCodUsuario(getSelectedObject().getCodUsuario());
				if (validator != null){
					error("User already exists !!");
				}else{
					try {
						getAdminService().initUsuario(getSelectedObject(), BaseApplication.getHomePageUrl());
						table.detachItems();
						setAction(Action.VIEW);
						setResponsePage(UsersPage.class);
					} catch (GBException e) {
						table.detachItems();
						setAction(Action.VIEW);
						setResponsePage(UsersPage.class);
					}	
				}
			} else {
				try {
					getAdminService().saveUsuario(getSelectedObject());
					table.detachItems();
					setAction(Action.VIEW);
					setResponsePage(UsersPage.class);
				} catch (GBException e) {
					table.detachItems();
					setAction(Action.VIEW);
					setResponsePage(UsersPage.class);
				}
			}
			
			table.detachItems();
			setAction(Action.VIEW);
			setResponsePage(UsersPage.class);
		}
		
		@Override
		protected void onError(AjaxRequestTarget target) {
			target.add(getFeedbackPanel());
		}
			
		});
		
		this.add(form);

		this.add(newAddLink("add"));
		this.add(newAddLink("add2"));

	}

	protected Link<Void> newAddLink(String id) {
		return new Link<Void>(id) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setSelectedObject(new Usuario());
				setAction(Action.ADD);
			}
		};
	}

	public String getSearchUser() {
		return searchUser;
	}

	public void setSearchUser(String searchUser) {
		this.searchUser = searchUser;
	}

	public GBTextField getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(GBTextField txtSearch) {
		this.txtSearch = txtSearch;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	

	

}
