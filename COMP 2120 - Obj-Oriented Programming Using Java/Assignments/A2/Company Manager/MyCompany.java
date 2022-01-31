
import java.util.Scanner;

public class MyCompany {

    static Scanner in = new Scanner(System.in);

    public static Employee getInfo(Company tesla) {

        System.out.println("Enter first name of the employee: ");
        String fn = in.next();
        System.out.println("Enter last name of the employee: ");
        String ln = in.next();
        System.out.println("Enter her/his cell number: ");
        String cell = in.next();
        System.out.println("Enter birth month of the employee: ");
        int m = in.nextInt();
        System.out.println("Enter birth day of the employee: ");
        int d = in.nextInt();
        System.out.println("Enter hiring year of the employee: ");
        int h = in.nextInt();
        System.out.println("Enter annual salary of the employee: ");
        double s = in.nextDouble();
        System.out.println("Enter vacation days of the employee: ");
        int v = in.nextInt();
        System.out.println("Is the employee a manager? (Y/y for Yes) ");
        String manager = in.next();
        if (manager.toUpperCase().equals("Y")) {
            System.out.println("Enter monthly bonus of this manager: ");
            double b = in.nextDouble();
            return new Manager(new Employee(new PersonManager(fn, ln, cell, m, d), h, s, v), b);
        }
        else {
            System.out.println("Enter first name of the employee's manager: ");
            String fnm = in.next();
            System.out.println("Enter last name of the employee's manager: ");
            String lnm = in.next();
            Employee mTemp = tesla.isManager(fnm, lnm);
            if (mTemp == null)
                System.out.println("There is no manager with this info!");
            return new Employee(new PersonManager(fn, ln, cell, m, d), h, s, v, mTemp);
        }
    }

    public static void main(String[] args) {

        Company tesla = new Company("Tesla", 2003);

        char e;
        do {
            tesla.addEmployee(getInfo(tesla));
            System.out.println("Do you want to add more employees? (E to exit) ");
            e = in.next().toUpperCase().charAt(0);

        } while (e != 'E');

        System.out.println(tesla);

        char answer;
        do {
            System.out.println("E-Exit\nA-Add new employee\nL-List of employees\n" +
                               "M-List of manager-employees\nN-Number of employees\n");
            answer = in.next().toUpperCase().charAt(0);
            switch (answer) {
                case 'E': break;
                case 'A': {
                    do {
                        tesla.addEmployee(getInfo(tesla));
                        System.out.println("Do you want to add more employees? (E to exit) ");
                        e = in.next().toUpperCase().charAt(0);
                    } while (e != 'E');
                    break;
                }
                case 'L': {
                    tesla.sort();
                    System.out.println(tesla);
                    break;
                }
                case 'M': {
                    System.out.println(tesla.listByManager());
                    break;
                }
                case 'N': {
                    System.out.println("----------------------------");
                    System.out.println("You have " + tesla.numberOfEmployees() + " employees.");
                    System.out.println("----------------------------");
                    break;
                }
                default: {
                    answer = 'E';
                    break;
                }
            }
        } while (answer != 'E');


    }
}
