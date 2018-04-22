package com.me.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="airplane")
public class Airplane {
	
	@Id 
	@GeneratedValue
	@Column(name="airplane_id", unique = true, nullable = false)
	private long airplane_id;
	
	@Column(name="airlineName")
	private String airlineName;
	
	
	@Column(name="owner")
	private String owner;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="airplane_id")
	private Set<FlightDetails> flights = new HashSet<FlightDetails>();
	
	
	
	public Airplane(){
		
	}


	public Airplane(String airlineName, String owner) {
		
		
		this.airlineName = airlineName;
		this.owner = owner;
	}

	

	public Set<FlightDetails> getFlights() {
		return flights;
	}


	public void setFlights(Set<FlightDetails> flights) {
		this.flights = flights;
	}


	public long getAirplane_id() {
		return airplane_id;
	}


	public void setAirplane_id(long airplane_id) {
		this.airplane_id = airplane_id;
	}


	public String getAirlineName() {
		return airlineName;
	}


	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
	
	

	

}
