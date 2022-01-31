/**
 * This is a Java class keep the information of persons.
 */
public class Person {

    private String name;
    private int age;

    public Person(String name) {
        this.name = name;
    }

    /**
     *
     * @param name Name of the person
     * @param age  Age of the person
     */
    public Person(String name, int age) {
        this(name);
        this.age = age;
    }

    /**
     *
     * @param name Name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Name of the person
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param age Age of the person
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return Age of the person
     */
    public int getAge() {
        return age;
    }

}
