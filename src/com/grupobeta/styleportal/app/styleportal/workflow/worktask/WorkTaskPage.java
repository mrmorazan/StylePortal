package com.grupobeta.styleportal.app.styleportal.workflow.worktask;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;

import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.domain.WorkTaskPolyPm;
import com.grupobeta.wicket.PageablePropertyListView;

public class WorkTaskPage extends WorkTaskBasePage<WorkTaskPolyPm> {
	private static final long serialVersionUID = 1L;
	
	List<WorkTaskPolyPm> tareas = new ArrayList<WorkTaskPolyPm>();

	public WorkTaskPage(StylePolyPm style) {
		super(style);
		
		setTareas(getTransService().loadWorkTaskFromStyleSeason(style));
		
		this.add(new Link<Void>("generateFlowChart") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new FlowChartPage(style, getTareas()));
			}
			
		});
		
		PageablePropertyListView<WorkTaskPolyPm> tableTask = new PageablePropertyListView<WorkTaskPolyPm>("tableTask") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<WorkTaskPolyPm> item) {
				item.add(new Label("flowTaskID"));
				item.add(new Label("taskName"));
				item.add(new Label("taskDescription"));
				
			}
			
			@Override
			protected List<WorkTaskPolyPm> loadItems() {
				return getTareas();
			}
		};
		
		tableTask.setRowsPerPage(10);
		this.add(tableTask);

		this.add(new PagingNavigator("navigator", tableTask));
		
		this.add(tableTask);
		
		
	}

	public List<WorkTaskPolyPm> getTareas() {
		return tareas;
	}

	public void setTareas(List<WorkTaskPolyPm> tareas) {
		this.tareas = tareas;
	}
	
	

}
