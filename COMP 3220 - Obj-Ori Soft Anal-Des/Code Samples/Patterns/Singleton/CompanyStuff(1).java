package singleton;
public class CompanyStuff {

	public static void main(String[] args) {
		
		Company usCompany = USCompany.getCompanyInstance();
		usCompany.setName("Ryan's Cool Cars");
		usCompany.setAddress("123 Fake Street");
		((USCompany)usCompany).setRegistrationNumber(7);
		System.out.println(usCompany);
		System.out.println(usCompany.getCompanyDetails());
		
		
		Company myCompany = Company.getCompanyInstance();
		//myCompany.setName("Ryan's Cool Cats");
		//myCompany.setAddress("1234 Real Street");

		myCompany = Company.getCompanyInstance();
		System.out.println(myCompany);
		System.out.println(usCompany.getCompanyDetails());
		
		myCompany = Company.getCompanyInstance();
		System.out.println(myCompany);
		System.out.println(usCompany.getCompanyDetails());
		
		myCompany = Company.getCompanyInstance();
		System.out.println(myCompany);
		System.out.println(usCompany.getCompanyDetails());
		
		
		Company canCompany = CanadianCompany.getCompanyInstance();
		System.out.println(canCompany);
		System.out.println(usCompany.getCompanyDetails());
		
		Company euCompany = EuropeanCompany.getCompanyInstance();
		System.out.println(euCompany);
		System.out.println(usCompany.getCompanyDetails());
	}
}
