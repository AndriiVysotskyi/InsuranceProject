package insurance.model;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurance.dto.*;
import insurance.dto.enums.*;
import insurance.dto.lossesDto.*;
import insurance.entities.*;
import insurance.entities.losses.*;
import insurance.repo.*;

@SuppressWarnings("serial")
@Service
public class InsuranceCompanyJpa extends AbstractInsuranceCompany {
	@Autowired
	VehiclesModelsRepository vehiclesModels;

	@Autowired
	VehiclesRepository vehicles;

	@Autowired
	EmployeeRepository employees;
	@Autowired
	LegalEntityRepository legalEntities;
	@Autowired
	PersonsRepository personses;

	@Autowired
	PoliciesRepository policies;

	@Autowired
	HovaLossRepository hovaLosses;
	@Autowired
	TsadGimelLossRepository tsadGimelLosses;
	@Autowired
	MakifLossRepository makifLossese;

	@Autowired
	BillsRepository bills;

	@Override
	public InsuranceReturnCode addModel(VehiclesModelDto model) {
		if (vehiclesModels.existsById(model.getModelName())) {
			return InsuranceReturnCode.MODEL_EXISTS;
		}
		vehiclesModels.save(new ModelJpa(model.getModelName(), model.getCompany(), model.getCountry(),
				model.getModelYear(), model.getBasicTarif()));
		return InsuranceReturnCode.OK;
	}

	@Override
	public InsuranceReturnCode addVehicle(VehicleDto vehicle) {
		if (vehicles.existsById(vehicle.getRegNumber())) {
			return InsuranceReturnCode.VEHICLE_EXISTS;
		}

		ModelJpa vehicleModel = vehiclesModels.findById(vehicle.getVehicleModel()).orElse(null);
		if (vehicleModel == null) {
			return InsuranceReturnCode.NO_MODEL;
		}

		if ((vehicle.getPersonOwnerID() != 0 && vehicle.getLegalEntityOwnerID() != 0)
				|| vehicle.getPersonOwnerID() == 0 && vehicle.getLegalEntityOwnerID() == 0) {
			return InsuranceReturnCode.WRONG_OWNER;
		}

		if (vehicle.getPersonOwnerID() != 0) {
			PersonJpa owner = personses.findById(vehicle.getPersonOwnerID()).orElse(null);
			if (owner == null) {
				return InsuranceReturnCode.NO_PERSON;
			}
			vehicles.save(createVehicleJpa(vehicle, owner, vehicleModel));
		} else {
			LegalEntityJpa owner = legalEntities.findById(vehicle.getLegalEntityOwnerID()).orElse(null);
			if (owner == null) {
				return InsuranceReturnCode.NO_LEGALENTITY;
			}
			vehicles.save(createVehicleJpa(vehicle, owner, vehicleModel));
		}
		return InsuranceReturnCode.OK;
	}

	private VehicleJpa createVehicleJpa(VehicleDto vehicle, LegalEntityJpa owner, ModelJpa vehicleModel) {
		return new VehicleJpa(vehicle.getRegNumber(), vehicle.getYear(), vehicle.getEngineVolume(),
				vehicle.getActualPrice(), vehicle.getColor(), vehicle.getKilometrage(), vehicle.getVinnumber(),
				vehicle.getCreateDate(), owner, vehicleModel);
	}

	private VehicleJpa createVehicleJpa(VehicleDto vehicle, PersonJpa owner, ModelJpa vehicleModel) {
		return new VehicleJpa(vehicle.getRegNumber(), vehicle.getYear(), vehicle.getEngineVolume(),
				vehicle.getActualPrice(), vehicle.getColor(), vehicle.getKilometrage(), vehicle.getVinnumber(),
				vehicle.getCreateDate(), owner, vehicleModel);
	}

	@Override
	public InsuranceReturnCode addLegalEntity(LegalEntityDto legalEntity, AdditionalInfoDto address) {
		if (legalEntities.existsById(legalEntity.getIdNumber())) {
			return InsuranceReturnCode.LEGALENTITY_EXISTS;
		}
		ContactsJpa contactsJpa = getContacts(address);

		legalEntities.save(new LegalEntityJpa(legalEntity.getIdNumber(), legalEntity.getFirstName(),
				legalEntity.getLastName(), legalEntity.getCompanyName(), contactsJpa));

		return InsuranceReturnCode.OK;
	}

	private ContactsJpa getContacts(AdditionalInfoDto address) {
		AddressJpa addressJpa = new AddressJpa(address.getCity(), address.getStreet(), address.getHouseNumber(),
				address.isGarageAddress());

		return new ContactsJpa(address.getEmailAddress(), address.getPhoneNumber(), addressJpa, address.getFlatNumber(),
				address.getZipCode());
	}

	@Override
	public InsuranceReturnCode addPerson(PersonDto person, AdditionalInfoDto address) {
		if (personses.existsById(person.getIdPerson())) {
			return InsuranceReturnCode.PERSON_EXISTS;
		}
		personses.save(new PersonJpa(person.getIdPerson(), person.getTitle(), person.getFirstName(),
				person.getLastName(), person.getDateOfBirth(), person.getGender(), person.getLicenseNumber(),
				person.getLicenseIssueDate(), person.getLicenseExpirationDate(), person.getCreateDate(),
				getContacts(address)));

		return InsuranceReturnCode.OK;
	}

	private EmployeeJpa getEmployee(int id) {
		return employees.findById(id).get();
	}

	@Override // totalAmount calculation of the field "totalAmount" after entering all the
				// data???
	public PolicyDto addPolicy(PolicyDto policy) {// TODO

		EmployeeJpa agent = getEmployee(policy.getAgentID());

		VehicleJpa vehicle = vehicles.findById(policy.getRegNumberOfVehicle()).orElse(null);
		if (vehicle == null) {
			return null;
		}
		boolean active = true; // ???

		Set<PersonJpa> drivers = new HashSet<>(getPersons(policy.getDriversID()));
		LocalDate createDate = LocalDate.now();
		int idPolicy = 1;// TODO
		policies.save(new PolicyJpa(idPolicy, policy.getInsuranceType(), policy.getPolicyEffectiveDate(),
				policy.getPolicyEffectiveDate(), createDate, getTotalAmount(policy), active, policy.getAdditionalInfo(),
				agent, vehicle, drivers));

		return getPolicy(idPolicy);
	}

	private double getTotalAmount(PolicyDto policy) {
		// TODO Auto-generated method stub
		return 0;
	}

	private List<PersonJpa> getPersons(List<Integer> persons) {
		List<PersonJpa> personDrivers = new ArrayList<PersonJpa>();
		// persons.stream().map(x ->
		// personses.findByIdAndLicenceNumberNotNull(x)).collect(Collectors.toList());
		for (Integer idDriver : persons) {
			PersonJpa personJpa = personses.findByPersonIdAndLicenseNumberNotNull(idDriver);
			if (personJpa == null) {
				return null;
			}

			personDrivers.add(personJpa);
		}

		/*
		 * for (Integer idDriver : drivers) { if(!personses.existsById(idDriver)) {
		 * return null; } } return drivers.stream().map(x ->
		 * personses.findById(x).get()).collect(Collectors.toList());
		 */
		return personDrivers;
	}

	@Override
	public InsuranceReturnCode addEmployee(EmployeeDto employee) {
		if (employees.existsById(employee.getWorkersId())) {
			return InsuranceReturnCode.EMPLOYEE_EXISTS;
		}
		employees.save(new EmployeeJpa(employee.getWorkersId(), employee.getFirstName(), employee.getLastName(),
				employee.getPosition()));
		return InsuranceReturnCode.OK;
	}

	@Override
	public InsuranceReturnCode addBill(BillDto bill) {// TODO Andrii

		return null;
	}

	@Override
	public InsuranceReturnCode addHovaLoss(HovaLossDto insuranceCase, AdditionalInfoDto address) {
		EmployeeJpa employeeOfLosses = employees.findById(insuranceCase.getEmployeeOfLossesID()).orElse(null);
		if (employeeOfLosses == null) {
			return InsuranceReturnCode.NO_EMPLOYEE;
		}

		List<PersonJpa> victims = getVictims(insuranceCase.getVictimID());

		PersonJpa driver = personses.findById(insuranceCase.getDriverID()).orElse(null);
		if (driver == null) {
			return InsuranceReturnCode.NO_PERSON;
		}

		PolicyJpa policyJpa = policies.findById(insuranceCase.getPolicyID()).orElse(null);
		if (policyJpa == null) {
			return InsuranceReturnCode.NO_POLICY;
		}

		AddressJpa addressJpa = new AddressJpa(address.getCity(), address.getStreet(), address.getHouseNumber(),
				address.isGarageAddress());

		hovaLosses.save(new HovaLossJpa(insuranceCase.getId(), insuranceCase.getEventData(),
				insuranceCase.getCreation(), addressJpa, insuranceCase.isAmbulance(), insuranceCase.getCostAmbulance(),
				insuranceCase.getDaysOfDsability(), insuranceCase.getAverageSalaryInDay(), insuranceCase.getInjury(),
				insuranceCase.getAmounСompensation(), policyJpa, driver, victims, employeeOfLosses));
		return InsuranceReturnCode.OK;
	}

	private List<PersonJpa> getVictims(List<Integer> victimID) {
		List<PersonJpa> personVictims = new ArrayList<PersonJpa>();

		for (Integer idVictim : victimID) {
			PersonJpa personJpa = personses.findByPersonIdAndLicenseNumberNotNull(idVictim);
			if (personJpa == null) {
				return null;
			}

			personVictims.add(personJpa);
		}
		return personVictims;
	}

	@Override
	public InsuranceReturnCode addTsadGimelLoss(TsadGimelLossDto insuranceCase, AdditionalInfoDto address) {

		return null;
	}

	@Override
	public InsuranceReturnCode addMakifLoss(MakifLossDto insuranceCase, AdditionalInfoDto address) {

		return null;
	}

	@Override
	public InsuranceReturnCode addDriverToPolicy(int idPolicy, int idPerson) {
		PersonJpa driver = personses.findById(idPerson).orElse(null);
		if (driver == null)
			return InsuranceReturnCode.NO_PERSON;

		PolicyJpa policy = policies.findById(idPolicy).get();
		Set<PersonJpa> drivers = policy.getDrivers();
		drivers.add(driver);
		policy.setDrivers(drivers);

		return null;
	}

	@Override
	public PersonDto getDriver(int idDriver) {
		PersonJpa driver = personses.findById(idDriver).orElse(null);
		if (driver == null)
			return null;

		return new PersonDto();// TODO
		/*
		 * PersonDto(idDriver, driver.getTitle(), driver.getFirstName(),
		 * driver.getLastName(), driver.getBirthDate(), driver.getGender(),
		 * driver.getLicenseNumber(), driver.getLicenseIssueDate(),
		 * driver.getLicenseIssueDate(), driver.getCreateDate(),
		 * driver.getAdditionalInfo());
		 */
	}

	@Override
	public PersonDto getOwner(int idOwner) {

		return null;
	}

	@Override
	public LegalEntityDto getLegalEntity(int idLegalEntity) {
		LegalEntityJpa legalEntity = legalEntities.findById(idLegalEntity).get();
		return new LegalEntityDto(idLegalEntity, legalEntity.getFirstName(), legalEntity.getFirstName(),
				legalEntity.getCompanyName());
	}

	@Override
	public VehiclesModelDto getModel(String modelName) {

		return null;
	}

	@Override
	public VehicleDto getVehicle(String regNumber) {
		VehicleJpa vehicleJpa = vehicles.findById(regNumber).orElse(null);
		return vehicleJpa == null ? null : getVehicle(vehicleJpa);
	}

	private VehicleDto getVehicle(VehicleJpa vehicleJpa) {
		return new VehicleDto(vehicleJpa.getRegNumber(), vehicleJpa.getYear(), vehicleJpa.getEngineVolume(),
				vehicleJpa.getActualPrice(), vehicleJpa.getColor(), vehicleJpa.getKilometrage(),
				vehicleJpa.getVinnumber(), vehicleJpa.getCreateDate(), vehicleJpa.getOwner().getPersonId(),
				vehicleJpa.getLegalEntityOwner().getId(), vehicleJpa.getVehicleModel().getModelName());
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

	@Override
	public InsuranceReturnCode changeofirstNamePerson(PersonDto idPerson) {

		return null;
	}

	@Override
	public InsuranceReturnCode changeoLastNamePerson(PersonDto idPerson) {

		return null;
	}

	@Override
	public InsuranceReturnCode changeoContactsPerson(PersonDto idPerson) {

		return null;
	}

}
