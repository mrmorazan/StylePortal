package com.grupobeta.styleportal.domain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import net.coobird.thumbnailator.Thumbnails;

@Entity
public class ImagenBam extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private long imagenBamID;
	private String ubicacion;
	private String printMode;
	private BigDecimal alto;
	private BigDecimal ancho;
	private byte[] imagen = new byte[0];
	private byte[] thumbnail = new byte[0];
	private String instructions;
	
	@Id
	@Column(name="ImagenID")
	public long getImagenBamID() {
		return imagenBamID;
	}
	public void setImagenBamID(long imagenBamID) {
		this.imagenBamID = imagenBamID;
	}
	
	@Column(name="Ubicacion")
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	@Column(name="PrintMode")
	public String getPrintMode() {
		return printMode;
	}
	public void setPrintMode(String printMode) {
		this.printMode = printMode;
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
	
	@Column(name="IMAGEN")
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	@Column(name="Instructions")
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	@Transient
	public byte[] getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public byte[] getThumbnails(byte[] imagen) {
		ByteArrayInputStream input = new ByteArrayInputStream(imagen);
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			BufferedImage image = ImageIO.read(input);
			if (image.getWidth() <= 128 && image.getHeight() <= 128)
				return imagen;
			else
				Thumbnails.of(image).size(128, 128).outputFormat("png").toOutputStream(output);
		} catch (IOException ex) {
			
		}

		return output.toByteArray();
	}
	
	

}
