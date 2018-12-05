package com.grupobeta.wicket;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.extensions.markup.html.tabs.ITab;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;

public class GBTabbedPanel <T extends ITab> extends Panel {
	private static final long serialVersionUID = 1L;

	public static final String TAB_PANEL_ID = "panel";

	private final List<T> tabs;

	/** the current tab */
	private int currentTab = -1;

	private transient VisibilityCache visibilityCache;

	public GBTabbedPanel(final String id, final List<T> tabs)
	{
		this(id, tabs, null);
	}

	public GBTabbedPanel(final String id, final List<T> tabs, IModel<Integer> model)
	{
		super(id, model);

		this.tabs = Args.notNull(tabs, "tabs");

		final IModel<Integer> tabCount = new AbstractReadOnlyModel<Integer>()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject()
			{
				return GBTabbedPanel.this.tabs.size();
			}
		};

		WebMarkupContainer tabsContainer = newTabsContainer("tabs-container");
		add(tabsContainer);

		// add the loop used to generate tab names
		tabsContainer.add(new Loop("tabs", tabCount)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final LoopItem item)
			{
				final int index = item.getIndex();
				final T tab = GBTabbedPanel.this.tabs.get(index);

				final WebMarkupContainer titleLink = newLink("link", index);

				titleLink.add(newTitle("title", tab.getTitle(), index));
				item.add(titleLink);
			}

			@Override
			protected LoopItem newItem(final int iteration)
			{
				return newTabContainer(iteration);
			}
		});

		add(newPanel());
	}

	@Override
	protected IModel<?> initModel()
	{
		return new Model<Integer>(-1);
	}


	protected WebMarkupContainer newTabsContainer(final String id)
	{
		return new WebMarkupContainer(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);
				tag.put("class", getTabContainerCssClass());
			}
		};
	}


	protected LoopItem newTabContainer(final int tabIndex)
	{
		return new LoopItem(tabIndex)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onConfigure()
			{
				super.onConfigure();

				setVisible(getVisiblityCache().isVisible(tabIndex));
			}

			@Override
			protected void onComponentTag(final ComponentTag tag)
			{
				super.onComponentTag(tag);

				String cssClass = tag.getAttribute("class");
				if (cssClass == null)
				{
					cssClass = " ";
				}
				cssClass += " tab" + getIndex();

				if (getIndex() == getSelectedTab())
				{
					cssClass += ' ' + getSelectedTabCssClass();
				}
				if (getVisiblityCache().getLastVisible() == getIndex())
				{
					cssClass += ' ' + getLastTabCssClass();
				}
				tag.put("class", cssClass.trim());
			}
		};
	}

	@Override
	protected void onBeforeRender()
	{
		int index = getSelectedTab();

		if ((index == -1) || (getVisiblityCache().isVisible(index) == false))
		{
			// find first visible tab
			index = -1;
			for (int i = 0; i < tabs.size(); i++)
			{
				if (getVisiblityCache().isVisible(i))
				{
					index = i;
					break;
				}
			}

			if (index != -1)
			{
				// found a visible tab, so select it
				setSelectedTab(index);
			}
		}

		setCurrentTab(index);

		super.onBeforeRender();
	}

	protected String getTabContainerCssClass()
	{
		return "tab-row";
	}

	protected String getLastTabCssClass()
	{
		return "last";
	}

	protected String getSelectedTabCssClass()
	{
		return "selected";
	}

	public final List<T> getTabs()
	{
		return tabs;
	}

	protected Component newTitle(final String titleId, final IModel<?> titleModel, final int index)
	{
		return new Label(titleId, titleModel);
	}

	protected WebMarkupContainer newLink(final String linkId, final int index)
	{
		return new Link<Void>(linkId)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setSelectedTab(index);
			}
		};
	}

	public GBTabbedPanel<T> setSelectedTab(final int index)
	{
		if ((index < 0) || (index >= tabs.size()))
		{
			throw new IndexOutOfBoundsException();
		}

		setDefaultModelObject(index);

		// force the tab's component to be aquired again if already the current tab
		currentTab = -1;
		setCurrentTab(index);

		return this;
	}

	private void setCurrentTab(int index)
	{
		if (this.currentTab == index)
		{
			// already current
			return;
		}
		this.currentTab = index;

		final Component component;

		if (currentTab == -1 || (tabs.size() == 0) || !getVisiblityCache().isVisible(currentTab))
		{
			// no tabs or the current tab is not visible
			component = newPanel();
		}
		else
		{
			// show panel from selected tab
			T tab = tabs.get(currentTab);
			component = tab.getPanel(TAB_PANEL_ID);
			if (component == null)
			{
				throw new WicketRuntimeException("ITab.getPanel() returned null. TabbedPanel [" +
					getPath() + "] ITab index [" + currentTab + "]");
			}
		}

		if (!component.getId().equals(TAB_PANEL_ID))
		{
			throw new WicketRuntimeException(
				"ITab.getPanel() returned a panel with invalid id [" +
					component.getId() +
					"]. You must always return a panel with id equal to the provided panelId parameter. TabbedPanel [" +
					getPath() + "] ITab index [" + currentTab + "]");
		}

		addOrReplace(component);
	}

	private WebMarkupContainer newPanel()
	{
		return new WebMarkupContainer(TAB_PANEL_ID);
	}

	public final int getSelectedTab()
	{
		return (Integer)getDefaultModelObject();
	}

	@Override
	protected void onDetach()
	{
		visibilityCache = null;

		super.onDetach();
	}

	private VisibilityCache getVisiblityCache()
	{
		if (visibilityCache == null)
		{
			visibilityCache = new VisibilityCache();
		}

		return visibilityCache;
	}

	private class VisibilityCache
	{

		/**
		 * Visibility for each tab.
		 */
		private Boolean[] visibilities;

		/**
		 * Last visible tab.
		 */
		private int lastVisible = -1;

		public VisibilityCache()
		{
			visibilities = new Boolean[tabs.size()];
		}

		public int getLastVisible()
		{
			if (lastVisible == -1)
			{
				for (int t = 0; t < tabs.size(); t++)
				{
					if (isVisible(t))
					{
						lastVisible = t;
					}
				}
			}

			return lastVisible;
		}

		public boolean isVisible(int index)
		{
			if (visibilities.length < index + 1)
			{
				Boolean[] resized = new Boolean[index + 1];
				System.arraycopy(visibilities, 0, resized, 0, visibilities.length);
				visibilities = resized;
			}

			if (visibilities.length > 0)
			{
				Boolean visible = visibilities[index];
				if (visible == null)
				{
					visible = tabs.get(index).isVisible();
					visibilities[index] = visible;
				}
				return visible;
			}
			else
			{
				return false;
			}
		}
	}



}
