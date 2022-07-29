package com.microservices.VaccinationCenter.Model;

import java.util.List;

import com.microservices.VaccinationCenter.Entity.VaccinationCenter;

public class CenterResponse 
{
	private VaccinationCenter center;	
	private List<Citizen> citizenList;
	
	public VaccinationCenter getCenter() 
	{
		return center;
	}
	
	public void setCenter(VaccinationCenter center)
	{
		this.center = center;
	}
	
	public List<Citizen> getCitizenList() 
	{
		return citizenList;
	}
	
	public void setCitizenList(List<Citizen> citizenList) 
	{
		this.citizenList = citizenList;
	}
	
	public CenterResponse(VaccinationCenter center, List<Citizen> citizenList) 
	{
		this.center = center;
		this.citizenList = citizenList;
	}
	
	public CenterResponse() 
	{
	}
}
