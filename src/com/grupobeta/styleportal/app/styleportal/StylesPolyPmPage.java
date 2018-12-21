package com.grupobeta.styleportal.app.styleportal;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;

import com.grupobeta.styleportal.app.StylePortalSession;
import com.grupobeta.styleportal.domain.CustomerPolyPm;
import com.grupobeta.styleportal.domain.SeasonPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.CompoundPropertyForm;
import com.grupobeta.wicket.GBSubmitButton;
import com.grupobeta.wicket.GBTextField;
import com.grupobeta.wicket.LoadableDetachableDropDownChoice;
import com.grupobeta.wicket.PageablePropertyListView;

public class StylesPolyPmPage extends StylePortalBasePage<StylePolyPm> {
	private static final long serialVersionUID = 1L;
	CustomerPolyPm customerPolyPm;
	String styles;
	SeasonPolyPm seasonPolyPm;
	List<StylePolyPm> estilos;
	
	WebMarkupContainer divTable;
	PageablePropertyListView<StylePolyPm> table;
	
	public StylesPolyPmPage(CustomerPolyPm customerPolyPm) {
		
		setEstilos(new ArrayList<StylePolyPm>());
		
		Form<StylesPolyPmPage> form = new CompoundPropertyForm<StylesPolyPmPage>("form", this);
		form.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		
		form.add(new GBTextField("styles").add(new AjaxFormComponentUpdatingBehavior("change") {
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
				if(getStyles()==null) {
					return new ArrayList<SeasonPolyPm>();
				} else {
					return getTransService().loadAllSeasonPolyPmFromStyle(getStyles());
				}
			}
		});
		
		form.add(new GBSubmitButton("submit") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				if(getStyles()!=null) {
					String season = null;
					if(getSeasonPolyPm()!=null) {
						season = getSeasonPolyPm().getSeasonName();
					}
					setEstilos(getTransService().loadAllStylePolyPmFromCustomerStyleSeason(customerPolyPm, getStyles(), season));
					table.detachItems();
					target.add(divTable);
				}
			}
		});
		
		form.add(new AjaxLink<Void>("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
					setResponsePage(CustomersPage.class);			
			}
			
		});
		
		divTable = new WebMarkupContainer("divTable");
		divTable.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		
		table = new PageablePropertyListView<StylePolyPm>("table") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<StylePolyPm> item) {
				item.add(new AjaxLink<Void>("linkStyle") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						if(item.getModelObject().getStatusId()>=44 && item.getModelObject().getStatusId()<=64) {
							setResponsePage(new StyleDetailsInformationPage(item.getModelObject()));
						} else {
							String msj;
							if(StylePortalSession.get().getLocale().getLanguage().equals("es")) {
								msj="El estilo no está listo";
							} else {
								msj="Style is not Ready";
							}
							error(msj);
							target.add(getFeedbackPanel());
						}
												
					}
					
				}.add(new Label("styleNumber")));
				
				item.add(new Label("seasonName"));
				item.add(new Label("silhouetteName"));
				item.add(new Label("styleName"));
				item.add(new Label("statusName"));
				
			}
			
			@Override
			protected List<StylePolyPm> loadItems() {
				return getEstilos();
			}
		};
		
		table.setRowsPerPage(10);
		
		divTable.add(table);
		divTable.add(new PagingNavigator("navigator", table));
		
		this.add(divTable);
		
		this.add(form);
		
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

	public List<StylePolyPm> getEstilos() {
		return estilos;
	}

	public void setEstilos(List<StylePolyPm> estilos) {
		this.estilos = estilos;
	}

}
