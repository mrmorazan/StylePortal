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
	public String toString() {
		return getSeasonName();
	}

}
