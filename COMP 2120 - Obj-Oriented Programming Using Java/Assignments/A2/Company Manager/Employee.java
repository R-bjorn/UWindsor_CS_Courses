
public class Employee extends PersonManager {

    private int hiringYear;
    private double annualSalary;
    private int vacationDays;
    private int unusedVacation;
    private Employee manager;

    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Employee(PersonManager p, int hiringYear, double annualSalary, int vacationDays) {

        super(p);
        this.hiringYear = hiringYear;
        this.annualSalary = annualSalary;
        this.vacationDays = vacationDays;
    }

    public Employee(PersonManager p, int hiringYear, double annualSalary, int vacationDays, Employee manager) {

        this(p, hiringYear, annualSalary, vacationDays);
        this.manager = manager;
    }

    public Employee(Employee emp) {

        super(emp);
        this.hiringYear = emp.hiringYear;
        this.annualSalary = emp.annualSalary;
        this.vacationDays = emp.vacationDays;
    }

    public Employee(Employee emp, Employee manager) {

        this(emp);
        this.manager = manager;
    }

    public void setHiringYear(int hiringYear) {
        this.hiringYear = hiringYear;
    }

    public int getHiringYear() {
        return hiringYear;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setUnusedVacation(int unusedVacation) {
        this.unusedVacation = unusedVacation;
    }

    public int getUnusedVacation() {
        return unusedVacation;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Employee getManager() {
        return manager;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            Employee other = (Employee) obj;
            return this.getFirstName().equals(other.getFirstName()) &&
                    this.getLastName().equals(other.getLastName());
        }
        return false;
    }

}
