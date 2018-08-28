package insurance.model;

import java.io.Serializable;

import insurance.dto.InsuranceCompanyData;

@SuppressWarnings("serial")
public abstract class AbstractInsuranceCompany implements IInsuranceCompany, Serializable {
	protected InsuranceCompanyData companyData = new InsuranceCompanyData();

/*	@Override
	public void setCompanyData(InsuranceCompanyData companyData) {
		this.companyData = companyData;
	}*/
}
