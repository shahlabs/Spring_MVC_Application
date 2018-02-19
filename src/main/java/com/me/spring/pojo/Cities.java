package com.me.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class Cities {

	@Id
	@Column(name="cityname")
	private String cityname;

	
	public Cities() {
		
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	
	
}
