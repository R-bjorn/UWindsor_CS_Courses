
import java.util.ArrayList;
import java.util.Collections;

public class FriendsList{

    private ArrayList<Person> friends;

    public FriendsList() {
        friends = new ArrayList<Person>();
    }

    public void addFriend(Person p) {
        friends.add(p);
    }

    public boolean removeFriend(Person p) {
        if (friends.contains(p)) {
            friends.remove(p);
            return true;
        }
        else
            return false;
    }

    public boolean setFriend(Person p) {
        if (friends.contains(p)) {
            return true;
        }
        else
            return false;
    }

    public int numberOfFriends() {
        return friends.size();
    }

    public void sort() {
        Collections.sort(friends);
    }

    public boolean setFriend(String firstName, String lastName, Person person) {
        Person q = new Person(firstName, lastName);
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).equals(q)) {
                friends.set(i,person);
                return true;
            }
        }
        return false;
    }

    public String friendsMonth(int month) {
        String st="";
        for (Person p : friends) {
            if (p.getBirthMonth() == month) {
                st = st.concat("----------------------------\n");
                st = st.concat("Name:" + p.getFirstName() + " " + p.getLastName() + "\n");
                st = st.concat("Number:" + p.getCellNumber() + "\n");
                st = st.concat("BirthDay:" + p.getBirthMonth() + "/" + p.getBirthDay() + "\n");
            }
        }
        if (st.equals(""))
            st = "----------------------------\nNo friends born in " + month + ".\n----------------------------";
        else
            st = "Friends born in " + month + " are:\n" + st.concat("----------------------------\n");
        return st;
    }

    public String friendsMonthDay(int month, int day) {
        String st="";
        for (Person p : friends) {
            if (p.getBirthMonth() == month && p.getBirthDay() == day) {
                st = st.concat("----------------------------\n");
                st = st.concat("Name:" + p.getFirstName() + " " + p.getLastName() + "\n");
                st = st.concat("Number:" + p.getCellNumber() + "\n");
                st = st.concat("BirthDay:" + p.getBirthMonth() + "/" + p.getBirthDay() + "\n");
            }
        }
        if (st.equals(""))
            st = "----------------------------\nNo friends born in " + month + "/"+ day +".\n----------------------------";
        else
            st = "Friends born in " + month + "/"+ day + " are:\n" + st.concat("----------------------------\n");
        return st;
    }

    public String findCell(String firstName, String lastName) {
        Person q = new Person(firstName, lastName);
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).equals(q)) {
                return friends.get(i).getCellNumber();
            }
        }
        return "Not found";
    }

    @Override
    public String toString() {
        String st="";
        for (Person p : friends) {
            st = st.concat("----------------------------\n");
            st = st.concat("Name:" + p.getFirstName() + " " + p.getLastName() + "\n");
            st = st.concat("Number:" + p.getCellNumber() + "\n");
            st = st.concat("BirthDay:" + p.getBirthMonth() + "/" + p.getBirthDay() + "\n");
        }
        st = st.concat("----------------------------\n");
        return st;
    }

}
