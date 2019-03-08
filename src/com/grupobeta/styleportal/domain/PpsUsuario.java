package com.grupobeta.styleportal.domain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

import com.grupobeta.errors.GBException;
import com.grupobeta.styleportal.StylePortalErrors;

import net.coobird.thumbnailator.Thumbnails;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(name = "PpsUsuarios", query = "EXEC [RHS].[up_RHS_EmpleadoDatosByLogin] @EmpleadoLoginName = :input", resultClass = PpsUsuario.class) })

public class PpsUsuario extends DomainObject {
	private static final long serialVersionUID = 1L;

	private String usuarioCode;
	private String nombreempleado;
	private String correo;
	private byte[] foto;
	private String puestoNombre;
	private String direccionNombre;
	private String departamentoNombre;
	private String seccionNombre;
	private byte[] fotoCompress;

	public PpsUsuario() {
	}

	@Id
	@Column(name = "EmpleadoCode")
	public String getUsuarioCode() {
		return usuarioCode;
	}

	public void setUsuarioCode(String usuarioCode) {
		this.usuarioCode = usuarioCode;
	}

	@Column(name = "EmpleadoFullName")
	public String getNombreempleado() {
		return nombreempleado;
	}

	public void setNombreempleado(String nombreempleado) {
		this.nombreempleado = nombreempleado;
	}

	@Column(name = "CompanyEmail")
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "DocumentoImagen")
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Column(name = "PuestoName")
	public String getPuestoNombre() {
		return puestoNombre;
	}

	public void setPuestoNombre(String puestoNombre) {
		this.puestoNombre = puestoNombre;
	}

	@Column(name = "DireccionName")
	public String getDireccionNombre() {
		return direccionNombre;
	}

	public void setDireccionNombre(String direccionNombre) {
		this.direccionNombre = direccionNombre;
	}

	@Column(name = "DepartamentoNombre")
	public String getDepartamentoNombre() {
		return departamentoNombre;
	}

	public void setDepartamentoNombre(String departamentoNombre) {
		this.departamentoNombre = departamentoNombre;
	}

	@Column(name = "SeccionNombre")
	public String getSeccionNombre() {
		return seccionNombre;
	}

	public void setSeccionNombre(String seccionNombre) {
		this.seccionNombre = seccionNombre;
	}

	@Transient
	public byte[] getFotoCompress() {
		return fotoCompress;
	}

	public void setFotoCompress(byte[] fotoCompress) {
		this.fotoCompress = fotoCompress;
	}

	public byte[] getThumbnail(byte[] imagen) throws GBException {
		ByteArrayInputStream input = new ByteArrayInputStream(imagen);
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			BufferedImage image = ImageIO.read(input);
			if (image == null)
				StylePortalErrors.ARCHIVO_NO_ENCONTRADO.throwException();
			else if (image.getWidth() <= 128 && image.getHeight() <= 128)
				return imagen;
			else
				Thumbnails.of(image).size(128, 128).outputFormat("png").toOutputStream(output);
		} catch (IOException ex) {
			StylePortalErrors.ARCHIVO_NO_ENCONTRADO.throwException(ex);
		}

		return output.toByteArray();
	}

}
