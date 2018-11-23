package insurance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import insurance.dto.*;
import insurance.dto.enums.*;
import insurance.dto.exceptions.NoPolicyException;
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
	PersonsRepository personsRepo;

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
	@Transactional
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
			PersonJpa owner = personsRepo.findById(vehicle.getPersonOwnerID()).orElse(null);
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
		if (personsRepo.existsById(person.getIdPerson())) {
			return InsuranceReturnCode.PERSON_EXISTS;
		}
		personsRepo.save(new PersonJpa(person.getIdPerson(), person.getTitle(), person.getFirstName(),
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
		boolean active = true;

		Set<PersonJpa> drivers = new HashSet<>(getPersons(policy.getDriversID()));
		if (drivers == null) {
			return null;
		}

		LocalDateTime createDate = LocalDateTime.now();
		String idPolicy = policy.getInsuranceType().toString() + createDate.toString();// TODO

		policies.save(new PolicyJpa(idPolicy, policy.getInsuranceType(), policy.getPolicyEffectiveDate(),
				policy.getPolicyEffectiveDate(), createDate, getTotalAmount(policy), active, policy.getAdditionalInfo(),
				agent, vehicle, drivers));

		return getPolicy(idPolicy);
	}

	private double getTotalAmount(PolicyDto policy) {
		// TODO Auto-generated method stub
		return 0;
	}

	private List<PersonJpa> getPersons(List<Integer> personsIds) {
		// List<PersonJpa> personDrivers = new ArrayList<PersonJpa>();

		/*
		 * for (Integer idDriver : persons) { PersonJpa personJpa =
		 * personsRepo.findByPersonIdAndLicenseNumberNotNull(idDriver); if (personJpa ==
		 * null) { return null; } personDrivers.add(personJpa); }
		 */

		int countListPersons = personsRepo.countPersonsJpa(personsIds);
		if (personsIds.size() != countListPersons) {
			return null;
		}

		return personsRepo.findAllById(personsIds).stream().collect(Collectors.toList());
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

		List<PersonJpa> victims = getVictims(insuranceCase.getVictimID());
		if (victims == null) {
			return InsuranceReturnCode.NO_VICTIMS;
		}

		PersonJpa driver = personsRepo.findById(insuranceCase.getDriverID()).orElse(null);
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
		int countVictimList = personsRepo.countListVictimJpa(victimID);
		if (victimID.size() != countVictimList) {
			return null;
		}
		return personsRepo.findAllById(victimID).stream().collect(Collectors.toList());
	}

	@Override
	public InsuranceReturnCode addTsadGimelLoss(TsadGimelLossDto insuranceCase, AdditionalInfoDto address) {

		// TODO Anna
		return null;
	}

	@Override
	public InsuranceReturnCode addMakifLoss(MakifLossDto insuranceCase, AdditionalInfoDto address) {
// TODO Anna
		return null;
	}

	@Override
	public InsuranceReturnCode addDriverToPolicy(String idPolicy, int idPerson) {
		PersonJpa driver = personsRepo.findById(idPerson).orElse(null);
		if (driver == null)
			return InsuranceReturnCode.NO_PERSON;

		PolicyJpa policy = policies.findById(idPolicy).get();
		if (policy == null) {
			return InsuranceReturnCode.NO_POLICY;
		}

		Set<PersonJpa> drivers = policy.getDrivers();
		drivers.add(driver);
		policy.setDrivers(drivers);

		return InsuranceReturnCode.OK;
	}

	@Override
	public PersonDto getDriver(int idDriver) {
		PersonJpa driver = personsRepo.findById(idDriver).orElse(null);
		if (driver == null)
			return null;

		return new PersonDto(idDriver, driver.getTitle(), driver.getFirstName(), driver.getLastName(),
				driver.getBirthDate(), driver.getGender(), driver.getLicenseNumber(), driver.getLicenseIssueDate(),
				driver.getLicenseExpirationDate(), driver.getCreateDate(), additionalInfo(driver));

	}

	private AdditionalInfoDto additionalInfo(PersonJpa driver) {
		ContactsJpa contactsDriver = driver.getContactsJpa();

		String city = contactsDriver.getAddressJpa().getCity();
		String street = contactsDriver.getAddressJpa().getStreet();
		String houseNumber = contactsDriver.getAddressJpa().getHouseNumber();
		int flatNumber = contactsDriver.getFlatNumber();
		String emailAddress = contactsDriver.getEmailAddress();
		int phoneNumber = contactsDriver.getPhoneNumber();
		boolean isGarageAddress = contactsDriver.getAddressJpa().isGarageAddress();
		int zipCode = contactsDriver.getZipCode();

		return new AdditionalInfoDto(city, street, houseNumber, flatNumber, zipCode, emailAddress, phoneNumber,
				isGarageAddress);

	}

	@Override
	public PersonDto getOwner(int idOwner) {
		return getDriver(idOwner);
	}

	@Override
	public LegalEntityDto getLegalEntity(int idLegalEntity) {
		LegalEntityJpa legalEntity = legalEntities.findById(idLegalEntity).get();
		return new LegalEntityDto(idLegalEntity, legalEntity.getFirstName(), legalEntity.getFirstName(),
				legalEntity.getCompanyName());
	}

	@Override
	public VehiclesModelDto getModel(String modelName) {
		ModelJpa model = vehiclesModels.findById(modelName).orElse(null);
		if (model == null) {
			return null;
		}

		return new VehiclesModelDto(modelName, model.getCompany(), model.getCountry(), model.getModelYear(),
				model.getBasicTarif());
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
	public PolicyDto getPolicy(String idPolicy) {// TODO
		PolicyJpa policy = null;

		try {
			policies.findById(idPolicy).get();
			policy = policies.findById(idPolicy).get();
		} catch (Exception e) {

			throw new NoPolicyException();
		}

		List<Integer> driversID = policy.getDrivers().stream().map(x -> x.getPersonId()).collect(Collectors.toList());

		int legalEntityID = policy.getVehicle().getLegalEntityOwner() == null
				? policy.getVehicle().getOwner().getPersonId()
				: policy.getVehicle().getLegalEntityOwner().getId();

		return new PolicyDto(idPolicy, policy.getInsuranceType(), policy.getPolicyEffectiveDate(),
				policy.getPolicyEffectiveDate(), policy.getPolicyBreakPoint(), policy.getCreateDate(),
				policy.getTotalAmount(), policy.isActive(), policy.getAdditionalInfo(),
				policy.getAgent().getWorkersId(), policy.getVehicle().getRegNumber(), driversID, legalEntityID);

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
	public boolean getStatusPolicy(String idPolicy) {
		PolicyJpa policy = policies.findById(idPolicy).get(); // TODO
		if (policy == null) {
			// throw Exception;
		}
		return policy.isActive();
	}

	@Override
	public double getTotalAmountOfPolicy(String idPolicy) {
		PolicyJpa policy = policies.findById(idPolicy).orElse(null);

		return policy.getTotalAmount();
	}

	@Override
	public double getAmounСompensation(int idInsuranceEvent) {

		return 0;
	}

	@Override
	public Set<PersonJpa> getAllDriversInPolicy(String idPolicy) {
		PolicyJpa policy = policies.findById(idPolicy).orElse(null);
		if (policy == null) {
			return null;
		}
		return policy.getDrivers();
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
	public List<String> getAllLossPolicy(String idPolicy) {

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
	public InsuranceReturnCode policyBreakPoint(String idPolicy, LocalDate dataBreak) {
		PolicyJpa policy = policies.findById(idPolicy).orElse(null);
		if (policy.getPolicyBreakPoint() != null) {
			return InsuranceReturnCode.IS_DATE_EXIST;
		}
		policy.setPolicyBreakPoint(dataBreak);
		return InsuranceReturnCode.OK;
	}

	@Override
	public InsuranceReturnCode changeStatusPolisy(boolean active, String idPolicy) {

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
	public InsuranceReturnCode changeContactsPerson(PersonDto idPerson) {

		return null;
	}

}
