import java.util.ArrayList;

public class Manager extends Employee {

    private double monthlyBonus;
    private ArrayList<Employee> employees;

    public Manager(Employee emp, double monthlyBonus) {

        super(emp);
        this.monthlyBonus = monthlyBonus;
        employees = new ArrayList<Employee>();

    }

    public void setMonthlyBonus(double monthlyBonus) {
        this.monthlyBonus = monthlyBonus;
    }

    public double getMonthlyBonus() {
        return monthlyBonus;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
}
