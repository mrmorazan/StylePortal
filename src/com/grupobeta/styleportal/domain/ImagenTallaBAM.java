package com.grupobeta.styleportal.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ImagenTallaBAM extends DomainObject {
	private static final long serialVersionUID = 1L;

	private int imagenTallaId;
	private String talla;
	private String posicionamiento;
	private BigDecimal alto;
	private BigDecimal ancho;
	
	@Id
	@Column(name="ImagenTallaID")
	public int getImagenTallaId() {
		return imagenTallaId;
	}
	public void setImagenTallaId(int imagenTallaId) {
		this.imagenTallaId = imagenTallaId;
	}
	
	@Column(name="Talla")
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	
	@Column(name="Posicionamiento")
	public String getPosicionamiento() {
		return posicionamiento;
	}
	public void setPosicionamiento(String posicionamiento) {
		this.posicionamiento = posicionamiento;
	}
	
	@Column(name="ALTO", precision=14)
	public BigDecimal getAlto() {
		return alto;
	}
	public void setAlto(BigDecimal alto) {
		this.alto = alto;
	}
	
	@Column(name="ANCHO", precision=14)
	public BigDecimal getAncho() {
		return ancho;
	}
	public void setAncho(BigDecimal ancho) {
		this.ancho = ancho;
	}
	
	
}
