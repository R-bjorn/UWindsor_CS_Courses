package singleton;
public class CanadianCompany extends Company {
	private float companyRegistrationNumber;
	
	public static Company getCompanyInstance(){
		if (instance == null) {
			instance = new CanadianCompany();
			return instance;
		}
		else return instance;
	}
	
	public String getCompanyDetails() {
		return super.getCompanyDetails() + " CanadianCompany: "  + this.companyRegistrationNumber;
	}
}
