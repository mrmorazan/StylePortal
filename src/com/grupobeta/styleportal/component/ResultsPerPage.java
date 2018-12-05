package com.grupobeta.styleportal.component;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;


public class ResultsPerPage extends Panel {
	private static final long serialVersionUID = 1L;

	private String cookieName = "SRM-RPP";
	private List<Integer> choices;
	private IModel<Integer> resultsPerPage;

	public ResultsPerPage(String id, Integer... choices) {
		super(id);
		this.choices = Arrays.asList(choices);
		this.resultsPerPage = new Model<Integer>();
		initResultsPerPage();

		this.add(new ListView<Integer>("links", this.choices){
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<Integer> item) {
				item.add(new Link<Void>("link"){
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						ResultsPerPage.this.setResultsPerPage(item.getModelObject());
					}

					@Override
					public boolean isEnabled() {
						return super.isEnabled() && getResultsPerPage() != item.getModelObject();
					}
				}.add(new Label("value", item.getModel())));
			}
		});
	}

	/**
	 * Returns the name of cookie that is used to remember last choice.
	 *
	 * @return Name of the cookie
	 */
	public String getCookieName()
	{
		return cookieName;
	}

	/**
	 * Sets the name of the cookie that is used to remember last choice.
	 *
	 * @param cookieName
	 *            Name of the cookie
	 * @return this
	 */
	public ResultsPerPage setCookieName(String cookieName)
	{
		if (cookieName != null && cookieName.indexOf(",") != -1 && cookieName.indexOf("|") != -1)
		{
			throw new IllegalArgumentException("Cookie name may not contain ',' or '|' characters.");
		}
		this.cookieName = cookieName;
		initResultsPerPage();
		return this;
	}

	public List<Integer> getChoices() {
		return choices;
	}

	protected void initResultsPerPage() {
		Cookie cookie = ((WebRequest)getRequestCycle().getRequest()).getCookie(getCookieName());
		if (cookie == null)
			setResultsPerPage(-1);
		else
			setResultsPerPage(Integer.parseInt(cookie.getValue()));
	}

	public int getResultsPerPage() {
		return resultsPerPage.getObject();
	}

	public ResultsPerPage setResultsPerPage(int resultsPerPage) {
		Integer r = resultsPerPage;

		if (!getChoices().contains(r))
			r = getChoices().get(0);

		Cookie cookie = new Cookie(getCookieName(), r.toString());
		cookie.setMaxAge(Integer.MAX_VALUE);
		((WebResponse)getRequestCycle().getResponse()).addCookie(cookie);
		this.resultsPerPage.setObject(r);
		onResultsPerPageChanged(r);
		return this;
	}

	protected void onResultsPerPageChanged(int resultsPerPage) {

	}
}
