
public class PersonManager  implements Comparable{

    private String firstName;
    private String lastName;
    private String cellNumber;
    private int birthMonth;
    private int birthDay;

    public PersonManager() {

    }

    public PersonManager(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonManager(String firstName, String lastName, String cellNumber, int birthMonth, int birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellNumber = cellNumber;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
    }

    public PersonManager(PersonManager p) {
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.cellNumber = p.cellNumber;
        this.birthMonth = p.birthMonth;
        this.birthDay = p. birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return this.lastName + "," + this.firstName;
    }

    @Override
    public int compareTo(Object o) {
        PersonManager other = (PersonManager) o;
        return (this.lastName + this.firstName).compareTo(other.lastName + other.firstName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PersonManager) {
            PersonManager other = (PersonManager) obj;
            return this.firstName.equals(other.firstName) &&
                    this.lastName.equals(other.lastName);
        }
        return false;
    }
}
