package com.grupobeta.styleportal.app.admin;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;

import com.grupobeta.styleportal.component.Action;
import com.grupobeta.styleportal.domain.Dictionary;
import com.grupobeta.styleportal.domain.Language;
import com.grupobeta.wicket.CompoundPropertyForm;
import com.grupobeta.wicket.GBRequiredTextField;
import com.grupobeta.wicket.GBSubmitButton;
import com.grupobeta.wicket.GBTextField;
import com.grupobeta.wicket.LoadableDetachableDropDownChoice;
import com.grupobeta.wicket.PageablePropertyListView;

public class DictionaryPage extends AdminBasePage<Dictionary> {
	private static final long serialVersionUID = 1L;
	String originalText;
	Language language;
	
	Form<DictionaryPage> formSearch;
	PageablePropertyListView<Dictionary> table;

	public DictionaryPage() {
		formSearch = new CompoundPropertyForm<DictionaryPage>("formSearch", this) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isVisible() {
				return super.isVisible() && getAction() == Action.VIEW;
			}
			
			
		};
		formSearch.add(new LoadableDetachableDropDownChoice<Language>("language") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Language> loadChoices() {
				return getAdminService().loadAllLanguageActives();
			}
			
		});
		
		formSearch.add(new GBTextField("originalText"));
		formSearch.add(new GBSubmitButton("submit2") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				super.onSubmit(target);
				table.detachItems();
				target.add(this.getPage());
			}
		});
		this.add(formSearch);
		
		table = new PageablePropertyListView<Dictionary>("table") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Dictionary> item) {
				item.add(new Label("language"));
				item.add(new Label("messageSource"));
				item.add(new Label("originalText"));
				item.add(new Label("translatedText"));
				item.add(new ContextImage("isGeneral", item.getModelObject().isGeneral() ? "img/fa-check.png" : "img/fa-cancel.png" ));
				item.add(new ContextImage("status", item.getModelObject().isGeneral() ? "img/fa-check.png" : "img/fa-cancel.png" ));
				item.add(new Link<Void>("edit"){
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setSelectedObject(item.getModelObject());
						setAction(Action.EDIT);
					}
				});
				
			}
			
			@Override
			protected List<Dictionary> loadItems() {
				return getAdminService().loadAllDictionaryForSearch(getLanguage(), getOriginalText());
			}
			
			@Override
			public boolean isVisible() {
				return super.isVisible() && getAction() == Action.VIEW;
			}
			
		};
		
		table.setRowsPerPage(25);
		this.add(table);

		this.add(new PagingNavigator("navigator", table));
		
		
		Form<Dictionary> form = new CompoundPropertyForm<Dictionary>("form", getSelectedObjectModel()) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return super.isVisible() && getAction() != Action.VIEW;
			}
		};
		
		form.add(new LoadableDetachableDropDownChoice<Language>("language") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Language> loadChoices() {
				return getAdminService().loadAllLanguageActives();
			}
			
		});
		
		form.add(new GBTextField("messageSource"));
		form.add(new GBRequiredTextField("originalText"));
		form.add(new GBRequiredTextField("translatedText"));
		form.add(new CheckBox("isGeneral"));
		form.add(new CheckBox("status"));
		
		form.add(new Link<Void>("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setAction(Action.VIEW);
				setResponsePage(DictionaryPage.class);
			}
		});
		
		form.add(new GBSubmitButton("submit") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				getAdminService().saveDictionary(getSelectedObject());
				
				table.detachItems();
				setAction(Action.VIEW);
				setResponsePage(DictionaryPage.class);
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
			setSelectedObject(new Dictionary());
			setAction(Action.ADD);
		}
	};
}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	
	

}
