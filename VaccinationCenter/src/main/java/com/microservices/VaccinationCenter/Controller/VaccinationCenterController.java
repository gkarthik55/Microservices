package com.microservices.VaccinationCenter.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.VaccinationCenter.Entity.VaccinationCenter;
import com.microservices.VaccinationCenter.Model.CenterResponse;
import com.microservices.VaccinationCenter.Model.Citizen;
import com.microservices.VaccinationCenter.Repository.VaccinationCenterRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {
	@Autowired
	private VaccinationCenterRepo centerRepo;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/add")
	public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter vc) {
		VaccinationCenter vaccinationCenter = centerRepo.save(vc);

		return new ResponseEntity<>(vaccinationCenter, HttpStatus.OK);
	}

	@RequestMapping("/getAllDataById/{id}")
	@HystrixCommand(fallbackMethod = "handleCitizenDownTime")
	public ResponseEntity<CenterResponse> getAllDataBasedOnCenterId(@PathVariable int id) {
		CenterResponse centerResponse = new CenterResponse();

		// First get all the vaccination center details.
		VaccinationCenter center = centerRepo.findById(id).get();
		centerResponse.setCenter(center);

		// Then get all the Citizen details from Citizen Service./
		java.util.List<Citizen> citizenList = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/getByVaccinationCenterId/" + id, List.class);
		centerResponse.setCitizenList(citizenList);

		// Then get all the Citizen registered to vaccination center.
		return new ResponseEntity<>(centerResponse, HttpStatus.OK);
	} 
	 
	public ResponseEntity<CenterResponse> handleCitizenDownTime(@PathVariable int id)
	{
		CenterResponse centerResponse = new CenterResponse();
		VaccinationCenter center = centerRepo.findById(id).get();
		centerResponse.setCenter(center);
		
		return new ResponseEntity<CenterResponse>(centerResponse, HttpStatus.OK);
	}
}
