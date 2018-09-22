package insurance.model;


import java.time.LocalDate;
import java.util.List;

import insurance.dto.*;
import insurance.dto.enums.InsuranceReturnCode;
import insurance.dto.lossesDto.HovaLossDto;
import insurance.dto.lossesDto.MakifLossDto;
import insurance.dto.lossesDto.TsadGimelLossDto;
import insurance.entities.PersonJpa;


public interface IInsuranceCompany {

	InsuranceReturnCode addModel(ModelDto model);

	InsuranceReturnCode addVehicle(VehicleDto vehicle);
	
	//???InsuranceReturnCode addAddress(AddressDto address);

	InsuranceReturnCode addLegalEntity(LegalEntityDto legalEntity);

	InsuranceReturnCode addPerson(PersonDto person);

	InsuranceReturnCode addPolicy(PolicyDto policy);

	InsuranceReturnCode addEmployee(EmployeeDto employee);
	
	InsuranceReturnCode addBill(BillDto bill);

	InsuranceReturnCode addHovaLoss(HovaLossDto insuranceCase);

	InsuranceReturnCode addTsadGimelLoss(TsadGimelLossDto insuranceCase);

	InsuranceReturnCode addMakifLoss(MakifLossDto insuranceCase);

	InsuranceReturnCode addDriverToPolicy(int idPolicy, int idPerson);

	PersonDto getDriver(int idDriver); // getting driver's data if it exist

	LegalEntityDto getLegalEntity(int idLegalEntity);

	ModelDto getModel(String modelName);

	VehicleDto getVehicle(String regNumber);

	PolicyDto getPolicy(int idPolicy);

	HovaLossDto getHovaLoss(String idHovaLoss);

	TsadGimelLossDto getTsadGimelLoss(String idTsadGimelLoss);
	
	MakifLossDto getMakifLoss(String idMakifLoss);

	boolean getStatusPolicy(int idPolicy);
	
	double getTotalAmountOfPolicy (int idPolicy);
	
	double getAmounСompensation(int idInsuranceEvent);
	
	List<PersonJpa> getAllDriversInPolicy(int idPolicy); 

	List<String> getAllInsuranceEventOfVehicle(int idVehicle);//method, which will issue insurance cases according to the number of the machine
	
	List<String> getAllInsuranceEventOfDriver(int idPerson);//method, which will be the number of the car gives insurance cases for a particular person
	
	List<String> getAllLossPolicy(int idPolicy);
	
	List<PersonDto> getVictim(int idPerson);// method that will give information about the victim

	List<PolicyDto> getPolicyByCar(int idPerson); //policy data by car number
	
	void changeStatusPolisyIfExpireDate(); // method that automatically modifies the status of policies on expiration

	InsuranceReturnCode policyBreakPoint(LocalDate dataBreak);

	InsuranceReturnCode changeStatusPolisy(boolean active);

	// метод, который удаляет старые записи
	List<PolicyDto> clearPolicy (LocalDate date);
	
	List<VehicleDto> clearVehicalesDto(LocalDate date);
	
	// метод, который автомотически будет отсылать на расчет убытков при страховом случае
	
	
	
	
}
