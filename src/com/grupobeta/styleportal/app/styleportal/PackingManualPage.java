package com.grupobeta.styleportal.app.styleportal;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.domain.PackingManual;
import com.grupobeta.styleportal.domain.SeasonPolyPm;
import com.grupobeta.wicket.CompoundPropertyForm;
import com.grupobeta.wicket.GBRequiredTextField;
import com.grupobeta.wicket.GBSubmitButton;
import com.grupobeta.wicket.GBTextField;
import com.grupobeta.wicket.LoadableDetachableDropDownChoice;
import com.grupobeta.wicket.PageablePropertyListView;

public class PackingManualPage extends StylePortalBasePage<PackingManual> {
	private static final long serialVersionUID = 1L;
	CustomerPolyPm customerPolyPm;
	String styles;
	SeasonPolyPm seasonPolyPm;
	PageablePropertyListView<PackingManual> table;
	List<PackingManual> packingManuals = new ArrayList<PackingManual>();
	
	public PackingManualPage() {
		
		Form<PackingManualPage> formSearch = new CompoundPropertyForm<PackingManualPage>("formSearch", this) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return super.isVisible() && getAction() == Action.VIEW;
			}
		};
		formSearch.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		
		formSearch.add(new LoadableDetachableDropDownChoice<CustomerPolyPm>("customerPolyPm") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<CustomerPolyPm> loadChoices() {
				return getTransService().loadAllCustomerActivesPolyPm(null, false);
			}
		}.add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(formSearch);
				
			}
		}));
		
		formSearch.add(new LoadableDetachableDropDownChoice<SeasonPolyPm>("seasonPolyPm") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<SeasonPolyPm> loadChoices() {
				if(getCustomerPolyPm()==null) {
					return new ArrayList<SeasonPolyPm>();
				} else {
					return getTransService().loadAllSeasonPolyPmFromCustomer(getCustomerPolyPm().getCompanyNumber());
				}
			}
		}.add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(formSearch);
				
			}
		}));
		
		formSearch.add(new GBTextField("styles").add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				
			}
		}));
		
		formSearch.add(new GBSubmitButton("submit2") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				String companyNumber = null;
				String seasonname = null;
				if(getCustomerPolyPm()!=null) {
					companyNumber = getCustomerPolyPm().getCompanyNumber();
				}
				
				if(getSeasonPolyPm()!=null) {
					seasonname = getSeasonPolyPm().getSeasonName();
				}
				
				setPackingManuals(getTransService().loadAllPackingManualFromFilter(companyNumber, seasonname, getStyles()));
				table.detachItems();
				target.add(this.getPage());
			}
			
		});
		
		formSearch.add(new Link<Void>("cancel2") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setAction(Action.VIEW);
				setResponsePage(PackingManualPage.class);
			}
		});
		
		this.add(formSearch);
		
		table = new PageablePropertyListView<PackingManual>("table") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<PackingManual> loadItems() {
				return getPackingManuals();
			}

			@Override
			protected void populateItem(Item<PackingManual> item) {
				item.add(new Label("polyCustomer", Model.of(item.getModelObject().getPolyCustomerCode()+" - "+ item.getModelObject().getPolyCustomerName())));
				item.add(new Label("seasonName"));
				item.add(new Label("styleNumber"));
				item.add(new ExternalLink("linkAlfresco", item.getModelObject().getUrlFile()).add(new Label("fileName")));
				item.add(new ContextImage("estado", item.getModelObject().isStatus() ? "img/fa-check.png" : "img/fa-cancel.png"));
				item.add(new Link<Void>("edit") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setSelectedObject(item.getModelObject());
						getSelectedObject().setCustomerPolyPm(getTransService().loadCustoemrPolyPM(item.getModelObject().getPolyCustomerId()));
						getSelectedObject().setSeasonPolyPm(item.getModelObject().getSeasonPolyPm());
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
		this.add(new PagingNavigator("navigator", table));
		this.add(table);
		
		
		
		Form<PackingManual> form = new CompoundPropertyForm<PackingManual>("form", getSelectedObjectModel()) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isVisible() {
				return super.isVisible() && getAction() != Action.VIEW;
			}
			
			
		};
		form.add(new LoadableDetachableDropDownChoice<CustomerPolyPm>("customerPolyPm") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<CustomerPolyPm> loadChoices() {
					return getTransService().loadAllCustomerActivesPolyPm(null, false);
				}
			
		}.setNullValid(false).add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(form);
				
			}
		}));
		
		form.add(new LoadableDetachableDropDownChoice<SeasonPolyPm>("seasonPolyPm") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<SeasonPolyPm> loadChoices() {
				if(getSelectedObject()!=null && getSelectedObject().getCustomerPolyPm()!=null) {
					return new ArrayList<SeasonPolyPm>(getTransService().loadAllSeasonPolyPmFromCustomer(getSelectedObject().getCustomerPolyPm().getCompanyNumber()));
				} else {
					return new ArrayList<SeasonPolyPm>();
				}
			}
		}.setNullValid(true).add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(form);
				
			}
		}));
		
		form.add(new GBTextField("styleNumber").add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				
			}
		}));
		
		form.add(new GBRequiredTextField("fileName"));
		form.add(new GBRequiredTextField("urlFile"));
		
		form.add(new CheckBox("status"));
		
		form.add(new Link<Void>("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setAction(Action.VIEW);
				setResponsePage(PackingManualPage.class);
			}
		});
		
		form.add(new GBSubmitButton("submit") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				getSelectedObject().setPolyCustomerCode(getSelectedObject().getCustomerPolyPm().getCompanyNumber());
				getSelectedObject().setPolyCustomerName(getSelectedObject().getCustomerPolyPm().getCompanyName());
				getSelectedObject().setPolyCustomerId(getSelectedObject().getCustomerPolyPm().getCustomerId());
				
				if(getSelectedObject().getSeasonPolyPm()!=null) {
					getSelectedObject().setSeasonId(getSelectedObject().getSeasonPolyPm().getSeasonId());
					getSelectedObject().setSeasonName(getSelectedObject().getSeasonPolyPm().getSeasonName());
				}
				
				
				getTransService().savePackingManual(getSelectedObject());
				
				table.detachItems();
				setAction(Action.VIEW);
				setResponsePage(PackingManualPage.class);
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
				setSelectedObject(new PackingManual());
				getSelectedObject().setCustomerPolyPm(getCustomerPolyPm());
				getSelectedObject().setSeasonPolyPm(getSeasonPolyPm());
				getSelectedObject().setStyleNumber(getStyles());
				setAction(Action.ADD);
			}
		};
	}
	
	public CustomerPolyPm getCustomerPolyPm() {
		return customerPolyPm;
	}

	public void setCustomerPolyPm(CustomerPolyPm customerPolyPm) {
		this.customerPolyPm = customerPolyPm;
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public SeasonPolyPm getSeasonPolyPm() {
		return seasonPolyPm;
	}

	public void setSeasonPolyPm(SeasonPolyPm seasonPolyPm) {
		this.seasonPolyPm = seasonPolyPm;
	}

	public List<PackingManual> getPackingManuals() {
		return packingManuals;
	}

	public void setPackingManuals(List<PackingManual> packingManuals) {
		this.packingManuals = packingManuals;
	}
	
	

}
