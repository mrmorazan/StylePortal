package com.grupobeta.styleportal.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="Rol", schema="ADM")
public class Rol extends AuditableObject  {
	private static final long serialVersionUID = 1L;

	public final static String ADMINISTRADOR = "ADMIN";
	public final static String USUARIO = "USER";
	

	private int idRol;
	private String codRol;
	private String nombre;
	private int timeout;

	private Set<Usuario> usuarios = new HashSet<Usuario>(0);
	
	
	public Rol() {
		this.timeout=0;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RolID", nullable=false , unique=true)
	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	@Column(name = "RolCode", unique=true, nullable=false, length=50)
	public String getCodRol() {
		return codRol;
	}


	public void setCodRol(String codRol) {
		this.codRol = codRol;
	}

	@Column(name = "Name", nullable=false, length=100)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="TimeOut", nullable=false)
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "UserRol", schema="ADM", joinColumns = { @JoinColumn(name = "RolID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "UserID", nullable = false, updatable = false) })
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@Override
	public String toString() {
		return getNombre();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codRol == null) ? 0 : codRol.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		if (codRol == null) {
			if (other.codRol != null)
				return false;
		} else if (!codRol.equals(other.codRol))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}


	

}
