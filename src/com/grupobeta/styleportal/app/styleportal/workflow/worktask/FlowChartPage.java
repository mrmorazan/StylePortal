package com.grupobeta.styleportal.app.styleportal.workflow.worktask;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.component.FlowChartElement;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.domain.WorkTaskPolyPm;

public class FlowChartPage extends WorkTaskBasePage<WorkTaskPolyPm> {
	private static final long serialVersionUID = 1L;
	
	List<WorkTaskPolyPm> tareas = new ArrayList<WorkTaskPolyPm>();
	RepeatingView repeatingView;
	
	public FlowChartPage(StylePolyPm style, List<WorkTaskPolyPm> tareas) {
		super(style);
		setTareas(tareas);
		
		repeatingView = new RepeatingView("divFlowChart");
		
		
		/*Inicio*/
		String styleInit = "";
		Properties properties = new Properties();
		if(getTareas().get(0)!=null) {
			String[] values = getTareas().get(0).getChartProperties().split(";");
			for (String value : values) {
				String[] propiedad = value.split("=");
				properties.setProperty(propiedad[0], propiedad[1]);
				
			}
			
		}
		
		styleInit += "left:"+ (Integer.parseInt(properties.getProperty("Left")))+"px !important; ";
		styleInit += "top:"+ (Integer.parseInt(properties.getProperty("Top"))-50)+"px !important; ";
		styleInit += "width:"+ (Integer.parseInt(properties.getProperty("Width"))+30)+"px !important; ";
		styleInit += "height:"+ (Integer.parseInt(properties.getProperty("Height"))+30)+"px !important; ";
		

		/*Elementos del chart*/
		repeatingView.add(new Label("start", Model.of("Start")).setMarkupId("start").add(new AttributeModifier("class", "flowchart-element-init")).add(new AttributeModifier("style", styleInit)));
		
	
		for (WorkTaskPolyPm tarea : getTareas()) {
			repeatingView.add(new FlowChartElement(String.valueOf(tarea.getFlowTaskID()), tarea).setMarkupId(String.valueOf(tarea.getFlowTaskID())));
		}
		/*Fin*/
		String styleFin = "";
		Properties propertiesFin = new Properties();
		if(getTareas().get(getTareas().size()-1)!=null) {
			String[] values = getTareas().get(getTareas().size()-1).getChartProperties().split(";");
			for (String value : values) {
				String[] propiedad = value.split("=");
				propertiesFin.setProperty(propiedad[0], propiedad[1]);
				
			}
			
		}
		styleFin += "left:"+  (Integer.parseInt(propertiesFin.getProperty("Left")))+"px !important; ";
		styleFin += "top:"+ (Integer.parseInt(propertiesFin.getProperty("Top"))+50)+"px !important; ";
		styleFin += "width:"+ (Integer.parseInt(propertiesFin.getProperty("Width"))+30)+"px !important; ";
		styleFin += "height:"+ (Integer.parseInt(propertiesFin.getProperty("Height"))+30)+"px !important; ";

	
		repeatingView.add(new Label("finish", Model.of("Finish")).setMarkupId("finish").add(new AttributeModifier("class", "flowchart-element-finish")).add(new AttributeModifier("style", styleFin)));
		
		this.add(repeatingView);
		
		this.add(new AjaxEventBehavior("load") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				String script = "jsPlumb.ready(function() { "
						+ " jsPlumb.Defaults.Overlays = [ " + 
						"            [ \"Arrow\", { " + 
						"                location:1, " + 
						"                id:\"arrow\"," + 
						"                length:7, " + 
						"                foldback:0.6" + 
						"            } ] " + 
						"]; "
						+ " jsPlumb.setContainer($('#container'));  " ;
				
				script += generateConectorScript("start", String.valueOf(1), "Bottom", "Top") + " ";
				
				for (WorkTaskPolyPm workTaskPolyPm : tareas) {
					script += generateConectorScript(String.valueOf(workTaskPolyPm.getFlowTaskID()), String.valueOf(workTaskPolyPm.getNextTask()), "Bottom", "Top") + " ";
					
					if(workTaskPolyPm.getRejectTask()!=0) {
						if(workTaskPolyPm.isDecision()) {
							script += generateConectorScript(String.valueOf(workTaskPolyPm.getFlowTaskID()), String.valueOf(workTaskPolyPm.getRejectTask()), "Left", "Left") + " ";
						} else {
							script += generateConectorScript(String.valueOf(workTaskPolyPm.getFlowTaskID()), String.valueOf(workTaskPolyPm.getRejectTask()), "Right", "Left") + " ";
						}
					}
					
					if(workTaskPolyPm.getAlternateTask()!=0) {
						script += generateConectorScript(String.valueOf(workTaskPolyPm.getFlowTaskID()), String.valueOf(workTaskPolyPm.getAlternateTask()), "Right", "Top") + " ";
					}
				}
				
				script += generateConectorScript(String.valueOf(getTareas().get(getTareas().size()-1).getFlowTaskID()), "finish", "Bottom", "Top") + " ";
						
				script += "}); ";
				target.appendJavaScript(script);
				
			}
		});
		
		
	}
	
	private String generateConectorScript(String nodoOrigen, String nodoFinal, String conectorOrigen, String conectorFinal) {
		String script =  "jsPlumb.connect({ "
				+ "source:\""+nodoOrigen+"\", "
				+ " target:\""+nodoFinal+"\", "
				+ " anchors:[\""+conectorOrigen+"\", \""+conectorFinal+"\"], "
				+ " connector:[ \"Flowchart\", {stub: [40, 60], cornerRadius: 5, alwaysRespectStubs: true}], "
				+ " endpoint:\"Blank\" "
			/*	+ " overlays:[\"PlainArrow\", { location: 1, width: 15, length: 12 }] "*/ 
			/*	+ " connectorOverlays: [ [ \"Arrow\", { width=20, length=20, location:0.8, foldback=0.623 } ] ] "*/
				+ "}); "
				;
		
		return script;
	}
	
	
	
	public List<WorkTaskPolyPm> getTareas() {
		return tareas;
	}

	public void setTareas(List<WorkTaskPolyPm> tareas) {
		this.tareas = tareas;
	}
	
	

}
