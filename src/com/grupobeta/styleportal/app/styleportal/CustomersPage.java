package com.grupobeta.styleportal.app.styleportal;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.RepeatingView;


import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.component.CustomerPpmPanel;
import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.wicket.CompoundPropertyForm;
import com.grupobeta.wicket.GBSubmitButton;
import com.grupobeta.wicket.GBTextField;

public class CustomersPage extends StylePortalBasePage<CustomerPolyPm> {
	private static final long serialVersionUID = 1L;
	List<CustomerPolyPm> customers;
	RepeatingView repeatingView;
	WebMarkupContainer divAct;
	protected String customerCode;
	protected Boolean viewInactive;
	
	
	public CustomersPage() {
		setViewInactive(false);
		setCustomerCode(null);
		divAct = new WebMarkupContainer("divAct");
		divAct.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		
		Form<CustomersPage> formSearch = new CompoundPropertyForm<CustomersPage>("formSearch", this);
		
		
		formSearch.add(new GBSubmitButton("submit2") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				setCustomers(getTransService().loadAllCustomerActivesPolyPm(getCustomerCode(), getViewInactive()));
				
				repeatingView = new RepeatingView("lista");
				for(CustomerPolyPm customerPolyPm : getCustomers()) {
					repeatingView.add(new CustomerPpmPanel(repeatingView.newChildId(), customerPolyPm));
				}
				
				divAct.addOrReplace(repeatingView);
				target.add(divAct);
			}
		});
		
		formSearch.add(new GBTextField("customerCode"));
		
		formSearch.add(new AjaxCheckBox("viewInactive") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				
				
			}
			
		});
		
		formSearch.add(new Link<Void>("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setAction(Action.VIEW);
				setResponsePage(CustomersPage.class);
			}
		});

		this.add(formSearch);
		
		setCustomers(getTransService().loadAllCustomerActivesPolyPm(getCustomerCode(), getViewInactive()));
		
		repeatingView = new RepeatingView("lista");
		for(CustomerPolyPm customerPolyPm : getCustomers()) {
			repeatingView.add(new CustomerPpmPanel(repeatingView.newChildId(), customerPolyPm));
		}
		
		divAct.add(repeatingView);
		this.add(divAct);
		
	}

	public List<CustomerPolyPm> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerPolyPm> customers) {
		this.customers = customers;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Boolean getViewInactive() {
		return viewInactive;
	}

	public void setViewInactive(Boolean viewInactive) {
		this.viewInactive = viewInactive;
	}
	
	
	
}
