package com.microservices.VaccinationCenter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.VaccinationCenter.Entity.VaccinationCenter;

public interface VaccinationCenterRepo extends JpaRepository<VaccinationCenter, Integer>	 
{
	
}
