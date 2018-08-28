package insurance.model;

import java.time.LocalDate;
import java.util.List;

import insurance.dto.*;

public interface IInsuranceCompany {

	/*InsuranceReturnCode addModel(ModelDto model);

	InsuranceReturnCode addVehicle(VehicleDto vehicle);

	InsuranceReturnCode addOwner(PersonDto owner);
	
	InsuranceReturnCode addOwner(LegalEntityDto owner);

	InsuranceReturnCode addDriver(PersonDto driver);
	InsuranceReturnCode addPolicy(PolicyDto policy);
	// InsuranceReturnCode addAgent(AgentDto agent);
	InsuranceReturnCode addHovaLoss(HovaLessDto insuranceCase);
	InsuranceReturnCode addTsadGimelLoss(TsadGimelDto insuranceCase);
	InsuranceReturnCode addMakifLoss(MakifLossDto insuranceCase);
	
	InsuranceReturnCode addDriverToPolicy(int idPolicy, int idPerson);
	 
	PersonDto getDriver (int idDriver); //getting driver's data if it exist 
	
	LegalEntityDto getLegalEntity(int idLegalEntity);
	
	ModelDto getModel(String modelName);
	
	VehicleDto getVehicle(String regNumber);
	
	PolicyDto getPolicy(int idPolicy);

	HovaLessDto getHovaLess(int idHovaLess);
	
	
	 
	boolean getStatusPolicy(int idPolicy);
	
	//  метод, который будем по номеру машины выдает страховые случаи по конкретной машине
	List<String> getLessVehicl(int idVehicl);
//  метод, который будем по номеру машины выдает страховые случаи по конкретному человеку
	List<String> getLessDriver(int idDriver);
	
	// метод, который автомотически сам будет изменять статус полисов при истечении срока действия
	// метод, который автомотически будет отсылать на расчет убытков при страховом случае
	
	InsuranceReturnCode changeStatusPolisyIfExpireDate();
	InsuranceReturnCode changeStatusPolisy(boolean active); 
	InsuranceReturnCode policyBreakPoint(LocalDate dataBreak);
*/
	

}
