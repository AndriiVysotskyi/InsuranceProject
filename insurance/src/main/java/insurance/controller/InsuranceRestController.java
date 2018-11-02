package insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import insurance.dto.ApiConstants;
import insurance.dto.PolicyDto;
import insurance.model.IInsuranceCompany;

@EnableJpaRepositories("insurance.repo")
@EnableMongoRepositories("")
@ComponentScan("insurance.model")
@EntityScan({ "insurance.entities", "insurance.entities" })

@RestController
public class InsuranceRestController {

	@Autowired
	IInsuranceCompany company;

	@PostMapping(value = ApiConstants.ADD_POLICY)
	PolicyDto addPocicy(@RequestBody PolicyDto policy) {
		return company.addPolicy(policy);
	}

}
