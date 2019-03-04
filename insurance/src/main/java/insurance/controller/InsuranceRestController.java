package insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import insurance.dto.*;
import static insurance.dto.ApiConstants.*;

import java.util.List;

import insurance.model.IInsuranceCompany;


@EnableJpaRepositories("insurance.repo")
@EnableMongoRepositories("")
@ComponentScan("insurance.model")
@EntityScan({ "insurance.entities", "insurance.entities" })

@RestController
public class InsuranceRestController {

	@Autowired
	IInsuranceCompany company;

	@PostMapping(value = ADD_POLICY)
	String addPocicy(@RequestBody PolicyDto policy) {
		return company.addPolicy(policy);
	}

	@PostMapping(value = ADD_MODEL)
	void addModel(@RequestBody ModelDto model) {
		company.addModel(model);
	}

	@GetMapping(value = GET_MODEL)
	ModelDto getModel(String modelName) {
		return company.getModel(modelName);
	}

	@PostMapping(value = ADD_PERSON)
	void addPerson(@RequestBody PersonDto person) {
		company.addPerson(person);
	}

	@GetMapping(value = GET_PERSON)
	PersonDto getPerson(int id) {
		return company.getPerson(id);
	}

	@PostMapping(value = ADD_EMPLOYEE)
	void addEmployee(@RequestBody EmployeeDto employee) {
		company.addEmployee(employee);
	}

	@GetMapping(value = GET_ALLPERSON)
	List<PersonDto> getAllPerson() {
		return company.getAllPerson();
	}

	@PostMapping(value = ADD_VECHICLE)
	void addVechicle(@RequestBody VehicleDto vehicleDto) {
		company.addVehicle(vehicleDto);
	}
	
	@PostMapping(value=ADD_LEGALENTITY)
	void addLegalEntity (@RequestBody LegalEntityDto legalEntityDto) {
		company.addLegalEntity(legalEntityDto);
	}
	
	
	
	

}
