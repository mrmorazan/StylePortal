package com.grupobeta.styleportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SeasonPolyPm extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int seasonId;
	private String seasonName;
	
	@Id
	@Column(name="SeasonID")
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	
	@Column(name="SeasonName")
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seasonName == null) ? 0 : seasonName.hashCode());
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
		SeasonPolyPm other = (SeasonPolyPm) obj;
		if (seasonName == null) {
			if (other.seasonName != null)
				return false;
		} else if (!seasonName.equals(other.seasonName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getSeasonName();
	}

}
