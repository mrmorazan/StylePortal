package com.grupobeta.styleportal.domain;

public class SVGLine extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private String stroke;
	
	public SVGLine() {
		this.width = 0;
		this.height = 0;
		this.x1 = 0;
		this.x2 = 0;
		this.y1 = 0;
		this.y2 = 0;
		this.stroke = "stroke:blue;stroke-width:1";
		
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public String getStroke() {
		return stroke;
	}
	public void setStroke(String stroke) {
		this.stroke = stroke;
	}
	
	

}
