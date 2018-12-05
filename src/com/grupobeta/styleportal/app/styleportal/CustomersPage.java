package com.grupobeta.styleportal.app.styleportal;

import java.util.List;

import org.apache.wicket.markup.repeater.RepeatingView;

import com.grupobeta.styleportal.component.CustomerPpmPanel;
import com.grupobeta.styleportal.domain.CustomerPolyPm;

public class CustomersPage extends StylePortalBasePage<CustomerPolyPm> {
	private static final long serialVersionUID = 1L;
	List<CustomerPolyPm> customers;
	RepeatingView repeatingView;
	
	public CustomersPage() {
		
		setCustomers(getTransService().loadAllCustomerActivesPolyPm());
		
		repeatingView = new RepeatingView("lista");
		for(CustomerPolyPm customerPolyPm : getCustomers()) {
			repeatingView.add(new CustomerPpmPanel(repeatingView.newChildId(), customerPolyPm));
		}
		
		add(repeatingView);
		
	}

	public List<CustomerPolyPm> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerPolyPm> customers) {
		this.customers = customers;
	}
	
	
	
}
