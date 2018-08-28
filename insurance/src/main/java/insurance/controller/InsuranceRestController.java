package insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;

import insurance.model.IInsuranceCompany;

@EnableJpaRepositories("insurance.repo")
@EnableMongoRepositories("")
@ComponentScan({"", "" })
@EntityScan("insurance.entities")
@RestController
public class InsuranceRestController {
	@Autowired
	IInsuranceCompany company;

}
