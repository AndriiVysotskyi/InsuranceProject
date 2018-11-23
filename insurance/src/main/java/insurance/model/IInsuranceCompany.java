package insurance.model;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import insurance.dto.*;
import insurance.dto.enums.InsuranceReturnCode;
import insurance.dto.lossesDto.HovaLossDto;
import insurance.dto.lossesDto.MakifLossDto;
import insurance.dto.lossesDto.TsadGimelLossDto;
import insurance.entities.PersonJpa;


public interface IInsuranceCompany {

	InsuranceReturnCode addModel(VehiclesModelDto model);

	InsuranceReturnCode addVehicle(VehicleDto vehicle);
	
	//???InsuranceReturnCode addAddress(AddressDto address);

	InsuranceReturnCode addLegalEntity(LegalEntityDto legalEntity, AdditionalInfoDto address);

	InsuranceReturnCode addPerson(PersonDto person,AdditionalInfoDto address);

	PolicyDto addPolicy(PolicyDto policy);

	InsuranceReturnCode addEmployee(EmployeeDto employee);
	
	InsuranceReturnCode addBill(BillDto bill);

	InsuranceReturnCode addHovaLoss(HovaLossDto insuranceCase, AdditionalInfoDto address);

	InsuranceReturnCode addTsadGimelLoss(TsadGimelLossDto insuranceCase, AdditionalInfoDto address);

	InsuranceReturnCode addMakifLoss(MakifLossDto insuranceCase, AdditionalInfoDto address);

	InsuranceReturnCode addDriverToPolicy(String idPolicy, int idPerson);

	PersonDto getDriver(int idDriver); // getting driver's data if it exist
	 
	PersonDto getOwner(int idOwner);

	LegalEntityDto getLegalEntity(int idLegalEntity);

	VehiclesModelDto getModel(String modelName);

	VehicleDto getVehicle(String regNumber);

	PolicyDto getPolicy(String idPolicy);

	HovaLossDto getHovaLoss(String idHovaLoss);

	TsadGimelLossDto getTsadGimelLoss(String idTsadGimelLoss);
	
	MakifLossDto getMakifLoss(String idMakifLoss);

	boolean getStatusPolicy(String idPolicy);
	
	double getTotalAmountOfPolicy (String idPolicy);
	
	double getAmounСompensation(int idInsuranceEvent);
	
	Set<PersonJpa> getAllDriversInPolicy(String idPolicy); 

	List<String> getAllInsuranceEventOfVehicle(int idVehicle);//method, which will issue insurance cases according to the number of the machine
	
	List<String> getAllInsuranceEventOfDriver(int idPerson);//method, which will be the number of the car gives insurance cases for a particular person
	
	List<String> getAllLossPolicy(String idPolicy);
	
	List<PersonDto> getVictim(int idPerson);// method that will give information about the victim

	List<PolicyDto> getPolicyByCar(int idPerson); //policy data by car number
	
	void changeStatusPolisyIfExpireDate(); // method that automatically modifies the status of policies on expiration

	InsuranceReturnCode policyBreakPoint(String idPolicy, LocalDate dataBreak);

	InsuranceReturnCode changeStatusPolisy(boolean active, String idPolicy);
	
	InsuranceReturnCode changeofirstNamePerson(PersonDto idPerson);
	
	InsuranceReturnCode changeoLastNamePerson(PersonDto idPerson);
	
	InsuranceReturnCode changeContactsPerson(PersonDto idPerson);


	// метод, который удаляет старые записи
	List<PolicyDto> clearPolicy (LocalDate date);
	
	List<VehicleDto> clearVehicalesDto(LocalDate date);
	
	// метод, который автомотически будет отсылать на расчет убытков при страховом случае
	
	
	
	
}
