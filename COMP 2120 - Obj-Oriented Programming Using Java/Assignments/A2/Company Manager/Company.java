
import java.util.ArrayList;
import java.util.Collections;

public class Company {

    private String companyName;
    private int startingYear;
    private ArrayList<Employee> companyEmployees;

    public Company(String companyName, int startingYear) {

        this.companyName = companyName;
        this.startingYear = startingYear;
        companyEmployees = new ArrayList<Employee>();
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setStartingYear(int startingYear) {
        this.startingYear = startingYear;
    }

    public int getStartingYear() {
        return startingYear;
    }

    public void addEmployee(Employee emp) {
        companyEmployees.add(emp);
        if (emp.getManager() != null) {
            Manager m = (Manager) emp.getManager();
            m.addEmployee(emp);
        }

    }

    public void sort() {
        Collections.sort(companyEmployees);
    }

    public int numberOfEmployees() {
        return companyEmployees.size();
    }

    public Employee findManagerOfEmployee(String firstName, String lastName) {
        Employee q = new Employee(firstName, lastName);
        for (int i = 0; i < companyEmployees.size(); i++) {
            if (companyEmployees.get(i).equals(q)) {
                return companyEmployees.get(i).getManager();
            }
        }
        return null;
    }

    public Employee isManager(String firstName, String lastName) {
        Employee q = new Employee(firstName, lastName);
        for (Employee e : companyEmployees) {
            if (e.equals(q))
                if (e instanceof Manager)
                    return e;
                else
                    return null;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder st= new StringBuilder();
        for (Employee p : companyEmployees) {
            st.append("----------------------------\n");
            st.append("Name:" + p.getFirstName() + " " + p.getLastName() + "\n");
            st.append("Hiring Year:" + p.getHiringYear() + "\n");
            st.append("Annual Salary:" + p.getAnnualSalary() + "\n");
            if (p instanceof Manager) {
                st.append("This emplyee is a manager.\n");
                st.append("Monthly Bonus:" + ((Manager) p).getMonthlyBonus() + "\n");
            }
            else {
                st.append("Manager name:" + p.getManager().getFirstName() + " " + p.getManager().getLastName() + "\n");
            }
        }
        st.append("----------------------------\n");

        return st.toString();
    }

    public String listByManager() {
        StringBuilder st= new StringBuilder();

        for (Employee p : companyEmployees) {
            if (p instanceof Manager) {
                st.append("*************\n");
                st.append("Manager Name:" + p.getFirstName() + " " + p.getLastName() + "\n");
                st.append("------- Employees -------\n");
                for (Employee e : ((Manager) p).getEmployees()) {
                    st.append("Name:" + e.getFirstName() + " " + e.getLastName() + "\n");
                }
            }
        }
        st.append("----------------------------\n");

        return st.toString();
    }

}
