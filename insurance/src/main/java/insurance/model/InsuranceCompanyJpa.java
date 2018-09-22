package insurance.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import insurance.dto.BillDto;
import insurance.dto.EmployeeDto;
import insurance.dto.LegalEntityDto;
import insurance.dto.ModelDto;
import insurance.dto.PersonDto;
import insurance.dto.PolicyDto;
import insurance.dto.VehicleDto;
import insurance.dto.enums.InsuranceReturnCode;
import insurance.dto.lossesDto.HovaLossDto;
import insurance.dto.lossesDto.MakifLossDto;
import insurance.dto.lossesDto.TsadGimelLossDto;
import insurance.entities.PersonJpa;

@SuppressWarnings("serial")
@Service
public class InsuranceCompanyJpa extends AbstractInsuranceCompany {

	@Override
	public InsuranceReturnCode addModel(ModelDto model) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addVehicle(VehicleDto vehicle) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addLegalEntity(LegalEntityDto legalEntity) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addPerson(PersonDto person) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addPolicy(PolicyDto policy) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addEmployee(EmployeeDto employee) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addBill(BillDto bill) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addHovaLoss(HovaLossDto insuranceCase) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addTsadGimelLoss(TsadGimelLossDto insuranceCase) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addMakifLoss(MakifLossDto insuranceCase) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode addDriverToPolicy(int idPolicy, int idPerson) {
		
		return null;
	}

	@Override
	public PersonDto getDriver(int idDriver) {
		
		return null;
	}

	@Override
	public LegalEntityDto getLegalEntity(int idLegalEntity) {
		
		return null;
	}

	@Override
	public ModelDto getModel(String modelName) {
		
		return null;
	}

	@Override
	public VehicleDto getVehicle(String regNumber) {
		
		return null;
	}

	@Override
	public PolicyDto getPolicy(int idPolicy) {
		
		return null;
	}

	@Override
	public HovaLossDto getHovaLoss(String idHovaLoss) {
		
		return null;
	}

	@Override
	public TsadGimelLossDto getTsadGimelLoss(String idTsadGimelLoss) {
		
		return null;
	}

	@Override
	public MakifLossDto getMakifLoss(String idMakifLoss) {
		
		return null;
	}

	@Override
	public boolean getStatusPolicy(int idPolicy) {
		
		return false;
	}

	@Override
	public double getTotalAmountOfPolicy(int idPolicy) {
		
		return 0;
	}

	@Override
	public double getAmounСompensation(int idInsuranceEvent) {
		
		return 0;
	}

	@Override
	public List<PersonJpa> getAllDriversInPolicy(int idPolicy) {
		
		return null;
	}

	@Override
	public List<String> getAllInsuranceEventOfVehicle(int idVehicle) {
		
		return null;
	}

	@Override
	public List<String> getAllInsuranceEventOfDriver(int idPerson) {
		
		return null;
	}

	@Override
	public List<String> getAllLossPolicy(int idPolicy) {
		
		return null;
	}

	@Override
	public List<PersonDto> getVictim(int idPerson) {
		
		return null;
	}

	@Override
	public List<PolicyDto> getPolicyByCar(int idPerson) {
		
		return null;
	}

	@Override
	public void changeStatusPolisyIfExpireDate() {
		
		
	}

	@Override
	public InsuranceReturnCode policyBreakPoint(LocalDate dataBreak) {
		
		return null;
	}

	@Override
	public InsuranceReturnCode changeStatusPolisy(boolean active) {
		
		return null;
	}

	@Override
	public List<PolicyDto> clearPolicy(LocalDate date) {
		
		return null;
	}

	@Override
	public List<VehicleDto> clearVehicalesDto(LocalDate date) {
		
		return null;
	}

}
