package com.microservices.CitizenService.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.CitizenService.Entity.Citizen;
import com.microservices.CitizenService.Repositories.CitizenRepo;

@RestController
@RequestMapping("/citizen")
public class CitizenController 
{
	@Autowired
	private CitizenRepo repo;
	
	@RequestMapping(path="/test")
	public ResponseEntity<String> test()
	{
		return new ResponseEntity<>("hello", HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen)
	{
		Citizen citizen = repo.save(newCitizen);
		
		return new ResponseEntity<>(citizen, HttpStatus.OK);
	}
	
	@RequestMapping(path="/getAll")
	public ResponseEntity<List<Citizen>> getAllCitizens()
	{
		List<Citizen> citizenList = repo.findAll();
		
		return new ResponseEntity<>(citizenList, HttpStatus.OK);
	}
	
	@RequestMapping(path="/getById/{id}")
	public Optional<Citizen> getById(@PathVariable Integer id)
	{
		Optional<Citizen> citizen = repo.findById(id);
	
		return citizen;
	}
	
	@RequestMapping(path="/getByVaccinationCenterId/{id}")
	public ResponseEntity<List<Citizen>> getByVaccinationCenterId(@PathVariable Integer id)
	{
		List<Citizen> citizenList = repo.findByVaccinationCenterId(id);
	
		return new ResponseEntity<>(citizenList, HttpStatus.OK);
	}
}
