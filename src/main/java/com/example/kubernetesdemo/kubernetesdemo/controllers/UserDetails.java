package com.example.kubernetesdemo.kubernetesdemo.controllers;

import java.io.Serializable;
import java.util.List;

public class UserDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String reportsTo;
	private Double experience;
	private List<String> skills;
	private String hostName;

	public UserDetails() {
		
	}
	
	public UserDetails(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public UserDetails(Integer id, String name, String reportsTo, Double experience, List<String> skills) {
		super();
		this.id = id;
		this.name = name;
		this.reportsTo = reportsTo;
		this.experience = experience;
		this.skills = skills;
	}



	public Integer getId() {
		return id;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public Double getExperience() {
		return experience;
	}

	public void setExperience(Double experience) {
		this.experience = experience;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", reportsTo=" + reportsTo + ", experience=" + experience
				+ ", skills=" + skills + "]";
	}
}
