package singleton;
public class EuropeanCompany extends Company {
	private String companyRegistrationNumber;
	
	public static Company getCompanyInstance(){
		if (instance == null) {
			instance = new EuropeanCompany();
			return instance;
		}
		else return instance;
	}
	
	public String getCompanyDetails() {
		return super.getCompanyDetails() + " EuropeanCompany: " + this.companyRegistrationNumber;
	}
}
