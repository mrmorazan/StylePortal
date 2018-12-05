package com.grupobeta.styleportal.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.persistence.Transient;
import com.grupobeta.errors.GBException;
import com.grupobeta.util.EncryptionUtils;

@Entity
@Table(name = "Users", schema = "ADM")
public class Usuario extends AuditableObject {
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PASSWORD = "grupobeta";

	private long idUsuario;
	private String codUsuario;
	private String password;
	private String nombre;
	private String correoElectronico;
	private boolean estado;
	private String department;
	
	private Set<Rol> roles = new HashSet<Rol>(0);
	

	public Usuario() {
		this.estado = true;
		
		this.password = DEFAULT_PASSWORD;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID", nullable = false, unique = true)
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "UserCode", nullable = false, unique = true, length = 50)
	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	@Column(name = "Password", length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Name", nullable = false, length = 100)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Email", length = 100)
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	@Column(name = "Status", nullable = false)
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	@Column(name="Department", length=100)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public void encryptPassword() throws GBException {
		setPassword(EncryptionUtils.getHash(getPassword()));
	}

	public void resetPassword() {
		setPassword(DEFAULT_PASSWORD);
	}

	@Transient
	public boolean isActivo() {
		return getEstado();
	}

	public boolean toggleEstado() {
		setEstado(isActivo() ? false : true);
		return getEstado();
	}
	
	@Transient
	public List<String> getRolesAsStringList() {
		List<String> list = new ArrayList<String>(getRoles().size());
		for (Rol rol : getRoles())
			list.add(rol.getCodRol());
		return list;
	}

	@Transient
	public int getTimeout() {
		int timeout = 0;
		for (Rol rol : getRoles())
			if (rol.getTimeout() > timeout)
				timeout = rol.getTimeout();

		return timeout * 30;
	}

	@Override
	public String toString() {
		return getCodUsuario() + " - " + getNombre();
	}

}
