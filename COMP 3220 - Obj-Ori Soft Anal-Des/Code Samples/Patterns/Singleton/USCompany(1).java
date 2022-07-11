package singleton;
public class USCompany extends Company {
	private int companyRegistrationNumber;
	
	public static Company getCompanyInstance(){
		if (instance == null) {
			instance = new USCompany();
			return instance;
		}
		else return instance;
	}
	
	public void setRegistrationNumber(int num) {
		companyRegistrationNumber = num;
	}
	
	public String getCompanyDetails() {
		return super.getCompanyDetails() + " USCompany: " + this.companyRegistrationNumber;
	}
}
