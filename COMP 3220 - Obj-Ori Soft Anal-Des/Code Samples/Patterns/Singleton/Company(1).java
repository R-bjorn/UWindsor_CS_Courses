package singleton;
public class Company {
	protected static Company instance;
	private String companyName;
	private String companyAddress;
	
	public static Company getCompanyInstance(){
		if (instance == null) {
			instance = new Company();
			return instance;
		}
		else return instance;
	}
	
	public String getCompanyDetails() {
		return companyName + " " + companyAddress;
	}
	
	public void setName(String name) {
		this.companyName = name;
	}
	
	public void setAddress(String address) {
		this.companyAddress = address;
	}
}
