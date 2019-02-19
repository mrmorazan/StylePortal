package com.grupobeta.styleportal.component;

import java.util.Properties;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.domain.WorkTaskPolyPm;

public class FlowChartElement extends Panel {
	private static final long serialVersionUID = 1L;
	private WorkTaskPolyPm workTaskPolyPm;

	public FlowChartElement(String id, WorkTaskPolyPm workTaskPolyPm) {
		super(id);
		setWorkTaskPolyPm(workTaskPolyPm);
		
		if(getWorkTaskPolyPm().isDecision()) {
			this.add(new AttributeModifier("class", "flowchart-element-decision"));
		} else {
			this.add(new AttributeModifier("class", "flowchart-element-task"));
		}
		
		this.add(new Label("taskName", Model.of(getWorkTaskPolyPm().getTaskName())));
		
		String style = "";
		
		Properties properties = new Properties();
		if(getWorkTaskPolyPm()!=null) {
			String[] values = getWorkTaskPolyPm().getChartProperties().split(";");
			for (String value : values) {
				String[] propiedad = value.split("=");
				
				properties.setProperty(propiedad[0], propiedad[1]);
				
			}
			
		}
		
		style += "left:"+  (Integer.parseInt(properties.getProperty("Left")))+"px !important; ";
		style += "top:"+ (Integer.parseInt(properties.getProperty("Top")))+"px !important; ";
		style += "width:"+ (Integer.parseInt(properties.getProperty("Width"))+30)+"px !important; ";
		style += "height:"+ (Integer.parseInt(properties.getProperty("Height"))+30)+"px !important; ";
		
		
		
		this.add(new AttributeModifier("style", style));
		
		
	}
	
	public WorkTaskPolyPm getWorkTaskPolyPm() {
		return workTaskPolyPm;
	}

	public void setWorkTaskPolyPm(WorkTaskPolyPm workTaskPolyPm) {
		this.workTaskPolyPm = workTaskPolyPm;
	}
	
	

}
