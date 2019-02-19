package com.grupobeta.styleportal.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.panel.Panel;

import com.grupobeta.styleportal.domain.SVGLine;


public class FlowChartLine extends Panel {
	private static final long serialVersionUID = 1L;
	private SVGLine svgLine;

	public FlowChartLine(String id, SVGLine svgLine) {
		super(id);
		
		setSvgLine(svgLine);
		
	//	this.add(new AttributeModifier("width", getSvgLine().getWidth()));
	//	this.add(new AttributeModifier("height", getSvgLine().getHeight()));
		
		this.add(new WebComponent("line") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("x1", getSvgLine().getX1());
				tag.put("y1", getSvgLine().getY1());
				tag.put("x2", getSvgLine().getX2());
				tag.put("y2", getSvgLine().getY2());
				tag.put("stroke", getSvgLine().getStroke());
			}
			
		});
		
	}

	public SVGLine getSvgLine() {
		return svgLine;
	}

	public void setSvgLine(SVGLine svgLine) {
		this.svgLine = svgLine;
	}
	
	
	
	

}
