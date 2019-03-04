package insurance.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import insurance.dto.*;
import insurance.dto.enums.*;
import insurance.dto.exceptions.NoDataException;
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
	ContactsRepository contacts;

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
	public void addModel(ModelDto model) {
		if (vehiclesModels.existsById(model.getModelName())) {
			throw new NoDataException("Model already exist");
		}
		vehiclesModels.save(new ModelJpa(model.getModelName(), model.getCompany(), model.getCountry(),
				model.getModelYear(), model.getBasicTarif()));
	}

	@Override
	@Transactional
	public void addVehicle(VehicleDto vehicle) {
		if (vehicles.existsById(vehicle.getRegNumber())) {
			throw new NoDataException("Vechicle already exist"); // TODO
		}

		ModelJpa vehicleModel = vehiclesModels.findById(vehicle.getVehicleModel()).orElse(null);
		if (vehicleModel == null) {
			throw new NoDataException("Model didn't found in the database");
		}

		if ((vehicle.getPersonOwnerID() != 0 && vehicle.getLegalEntityOwnerID() != 0)
				|| vehicle.getPersonOwnerID() == 0 && vehicle.getLegalEntityOwnerID() == 0) {
			throw new NoDataException("wrong owner");
		}

		if (vehicle.getPersonOwnerID() != 0) {
			PersonJpa owner = personsRepo.findById(vehicle.getPersonOwnerID()).orElse(null);
			if (owner == null) {
				throw new NoDataException("Owner is not exists");
			}
			vehicles.save(createVehicleJpa(vehicle, owner, vehicleModel));
		} else {
			LegalEntityJpa owner = legalEntities.findById(vehicle.getLegalEntityOwnerID()).orElse(null);
			if (owner == null) {
				throw new NoDataException("Owner is not exists");
			}
			vehicles.save(createVehicleJpa(vehicle, owner, vehicleModel));
		}

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
	@Transactional
	public void addLegalEntity(LegalEntityDto legalEntity) {
		if (legalEntities.existsById(legalEntity.getIdNumber())) {
			throw new NoDataException("LegalEntity already exists");
		}
		AdditionalInfoDto address = legalEntity.getAdditionalInfo();
		ContactsJpa contactsJpa = getContacts(address);

		legalEntities.save(new LegalEntityJpa(legalEntity.getIdNumber(), legalEntity.getFirstName(),
				legalEntity.getLastName(), legalEntity.getCompanyName(), contactsJpa));

	}

	@Override
	public List<PersonDto> getAllPerson() {
		List<PersonJpa> personsJpa = personsRepo.findAll();

		if (personsJpa == null) {
			System.out.println("");
			return null;
		}
		List<PersonDto> personsDto = new ArrayList<>();
		for (PersonJpa person : personsJpa) {
			personsDto.add(getPerson(person));
		}
		return personsDto;
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

	private PersonDto getPerson(PersonJpa person) {
		AdditionalInfoDto additionalInfo = new AdditionalInfoDto(person.getContactsJpa().getAddressJpa().getCity(),
				person.getContactsJpa().getAddressJpa().getStreet(),
				person.getContactsJpa().getAddressJpa().getHouseNumber(), person.getContactsJpa().getFlatNumber(),
				person.getContactsJpa().getZipCode(), person.getContactsJpa().getEmailAddress(),
				person.getContactsJpa().getPhoneNumber(), person.getContactsJpa().getAddressJpa().isGarageAddress());

		return new PersonDto(person.getPersonId(), person.getTitle(), person.getFirstName(), person.getLastName(),
				parseDate(person.getBirthDate()), person.getGender(), person.getLicenseNumber(),
				parseDate(person.getLicenseIssueDate()), parseDate(person.getLicenseExpirationDate()),
				parseDate(person.getCreateDate()), additionalInfo);
	}

	private ContactsJpa getContacts(AdditionalInfoDto address) {
		AddressJpa addressJpa = new AddressJpa(address.getCity(), address.getStreet(), address.getHouseNumber(),
				address.isGarageAddress());
		ContactsJpa contact = new ContactsJpa(address.getEmailAddress(), address.getPhoneNumber(), addressJpa,
				address.getFlatNumber(), address.getZipCode());
		contacts.save(contact);
		return contact;
	}

	@Override
	@Transactional
	public void addPerson(PersonDto person) {
		if (personsRepo.existsById(person.getIdPerson())) {
			throw new NoDataException("Person already exist");
		}
		AdditionalInfoDto address = person.getAdditionalInfo();
		ContactsJpa contact = getContacts(address);

		PersonJpa personJpa = new PersonJpa(person.getIdPerson(), person.getTitle(), person.getFirstName(),
				person.getLastName(), parseDate(person.getDateOfBirth()), person.getGender(), person.getLicenseNumber(),
				parseDate(person.getLicenseIssueDate()), parseDate(person.getLicenseExpirationDate()), contact);
		personsRepo.save(personJpa);
	}

	private EmployeeJpa getEmployee(int id) {
		return employees.findById(id).get();
	}

	private LocalDate parseDate(String dataStr) {
		try {
			DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(dataStr, ft);
		} catch (Exception e) {
			System.out.println("parseDate - exception");
		}
		return LocalDate.now();

	}

	private String parseDate(LocalDate date) {
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return date.format(ft);
	}

	@Override // totalAmount calculation of the field "totalAmount" after entering all the
				// data???
	@Transactional
	public String addPolicy(PolicyDto policy) {// TODO

		EmployeeJpa agent = getEmployee(policy.getAgentID());
		if (agent == null) {
			throw new NoDataException("Agent is not exist");
		}

		VehicleJpa vehicle = vehicles.findById(policy.getRegNumberOfVehicle()).orElse(null);
		if (vehicle == null) {
			throw new NoDataException("Vehicle is not exist");
		}

		List<PersonJpa> drivers = getPersons(policy.getDriversID());
		if (drivers == null) {
			throw new NoDataException("Persons are not exist");
		}

		PolicyJpa policyJpa = new PolicyJpa(policy.getInsuranceType(), parseDate(policy.getPolicyEffectiveDate()),
				parseDate(policy.getPolicyExpireDate()), policy.getTotalAmount(), policy.getAdditionalInfo(), agent,
				vehicle, drivers);
		policies.save(policyJpa);

		return policyJpa.getId();
	}

	private double getTotalAmount(PolicyDto policy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public void addEmployee(EmployeeDto employee) {
		if (employees.existsById(employee.getWorkersId())) {
			throw new NoDataException("Employee already exist");
		}
		employees.save(new EmployeeJpa(employee.getWorkersId(), employee.getFirstName(), employee.getLastName(),
				employee.getPosition()));
	}

	@Override
	public InsuranceReturnCode addBill(BillDto bill) {// TODO Andrii

		return null;
	}

	@Override
	@Transactional
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
	@Transactional
	public InsuranceReturnCode addTsadGimelLoss(TsadGimelLossDto insuranceCase, AdditionalInfoDto address) {

		// TODO Anna
		return null;
	}

	@Override
	@Transactional
	public InsuranceReturnCode addMakifLoss(MakifLossDto insuranceCase, AdditionalInfoDto address) {
// TODO Anna
		return null;
	}

	@Override
	@Transactional
	public InsuranceReturnCode addDriverToPolicy(String idPolicy, int idPerson) {
		PersonJpa driver = personsRepo.findById(idPerson).orElse(null);
		if (driver == null)
			return InsuranceReturnCode.NO_PERSON;

		PolicyJpa policy = policies.findById(idPolicy).get();
		if (policy == null) {
			return InsuranceReturnCode.NO_POLICY;
		}

		List<PersonJpa> drivers = policy.getDrivers();
		drivers.add(driver);
		policy.setDrivers(drivers);

		return InsuranceReturnCode.OK;
	}

	@Override
	public PersonDto getPerson(int idPerson) {
		PersonJpa driver = personsRepo.findById(idPerson).orElse(null);
		if (driver == null)
			return null;

		return new PersonDto(idPerson, driver.getTitle(), driver.getFirstName(), driver.getLastName(),
				parseDate(driver.getBirthDate()), driver.getGender(), driver.getLicenseNumber(),
				parseDate(driver.getLicenseIssueDate()), parseDate(driver.getLicenseExpirationDate()),
				parseDate(driver.getCreateDate()), additionalInfo(driver));

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
		return getPerson(idOwner);
	}

	@Override
	public LegalEntityDto getLegalEntity(int idLegalEntity) {
		LegalEntityJpa legalEntity = legalEntities.findById(idLegalEntity).get();
		return new LegalEntityDto(idLegalEntity, legalEntity.getFirstName(), legalEntity.getFirstName(),
				legalEntity.getCompanyName());
	}

	@Override
	public ModelDto getModel(String modelName) {
		ModelJpa model = vehiclesModels.findById(modelName).orElse(null);
		if (model == null) {
			return null;
		}

		return new ModelDto(modelName, model.getCompany(), model.getCountry(), model.getModelYear(),
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
		/*PolicyJpa policy = null;try {
			policies.findById(idPolicy).get();
			policy = policies.findById(idPolicy).get();
		} catch (Exception e) {
			throw new NoPolicyException();
		}
*/
		PolicyJpa policyJpa=policies.findById(idPolicy).orElse(null);
		if (policyJpa==null) {
			throw new NoDataException("No policy");
		}
		return getPolicyDto(policyJpa);
	}

	private PolicyDto getPolicyDto(PolicyJpa policyJpa) {
		List<Integer> driversID = policyJpa.getDrivers().stream().map(x -> x.getPersonId()).collect(Collectors.toList());
		int legalEntityID = policyJpa.getVehicle().getLegalEntityOwner() == null
				? policyJpa.getVehicle().getOwner().getPersonId()
				: policyJpa.getVehicle().getLegalEntityOwner().getId();
		int agentID=policyJpa.getAgent().getWorkersId();
		
		String regNumberOfVehicle=policyJpa.getVehicle().getRegNumber();
		String policyExpireDate=parseDate(policyJpa.getPolicyExpireDate());
		String policyEffectiveDate=parseDate(policyJpa.getPolicyEffectiveDate());
		String policyBreakPoint=parseDate(policyJpa.getPolicyBreakPoint());
		String createDate=parseDate(policyJpa.getCreateDate());
		return new PolicyDto(policyJpa.getId(), policyJpa.getInsuranceType(), 
				policyEffectiveDate, policyExpireDate, policyBreakPoint, createDate, 
				policyJpa.getTotalAmount(), policyJpa.isActive(), policyJpa.getAdditionalInfo(), agentID,regNumberOfVehicle, driversID);
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
			throw new NoDataException("Policy is not exsits");
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
	public List<PersonDto> getAllDriversInPolicy(String idPolicy) {
		PolicyJpa policy = policies.findById(idPolicy).orElse(null);
		if (policy == null) {
			throw new NoDataException("No policy");
		}
		/*List<Integer> personsDto=policy.getDrivers().stream().map(PersonJpa::getPersonId).collect(Collectors.toList());
				map(InsuranceCompanyJpa::getPerson); */
		List<PersonDto> personsDto = null;
		for (PersonJpa person : policy.getDrivers()) {
			personsDto.add(getPerson(person));
		}
		return personsDto;
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
