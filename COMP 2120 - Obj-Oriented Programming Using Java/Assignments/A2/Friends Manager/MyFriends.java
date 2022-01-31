import java.util.Scanner;

public class MyFriends {

    static Scanner in = new Scanner(System.in);

    public static Person getInfo() {

        System.out.println("Enter first name of your friend: ");
        String fn = in.next();
        System.out.println("Enter last name of your friend: ");
        String ln = in.next();
        System.out.println("Enter her/his cell number: ");
        String cell = in.next();
        System.out.println("Enter birth month of your friend: ");
        int m = in.nextInt();
        System.out.println("Enter birth day of your friend: ");
        int d = in.nextInt();
        return new Person(fn, ln, cell, m, d);
    }

    public static void main(String[] args) {

        FriendsList myFriends = new FriendsList();
        int e;
        do {
            myFriends.addFriend(getInfo());
            System.out.println("Do you want to add more friends? (0 to exit) ");
            e = in.nextInt();

        } while (e != 0);

        System.out.println(myFriends);

        int answer;
        do {
            System.out.println("0-Exit\n1-Add new friend\n2-Change info of an existing friend\n" +
                               "3-List of friends\n4-Number of friends\n5-Who born this month\n" +
                               "6-Who born today\n7-Find cell number");
            answer = in.nextInt();
            switch (answer) {
                case 0: break;
                case 1: {
                    do {
                        myFriends.addFriend(getInfo());
                        System.out.println("Do you want to add more friends? (0 to exit) ");
                        e = in.nextInt();
                    } while (e != 0);
                    break;
                }
                case 2: {
                    System.out.println("Enter first name of your friend: ");
                    String fn0 = in.next();
                    System.out.println("Enter last name of your friend: ");
                    String ln0 = in.next();
                    System.out.println("Enter the new first name: ");
                    String fn = in.next();
                    System.out.println("Enter the new last name: ");
                    String ln = in.next();
                    System.out.println("Enter the new cell number: ");
                    String cell = in.next();
                    System.out.println("Enter the new birth month: ");
                    int m = in.nextInt();
                    System.out.println("Enter the new birth day: ");
                    int d = in.nextInt();
                    Person p = new Person(fn, ln, cell, m, d);
                    if (!myFriends.setFriend(fn0,ln0,p)) {
                        System.out.println("----------------------------");
                        System.out.println("Friend not found!");
                        System.out.println("----------------------------");
                    }
                    break;
                }
                case 3: {
                    myFriends.sort();
                    System.out.println(myFriends);
                    break;
                }
                case 4: {
                    System.out.println("----------------------------");
                    System.out.println("You have " + myFriends.numberOfFriends() + " friends.");
                    System.out.println("----------------------------");
                    break;
                }
                case 5: {
                    System.out.println("Enter the birth month: ");
                    int m = in.nextInt();
                    System.out.println(myFriends.friendsMonth(m));
                    break;
                }
                case 6: {
                    System.out.println("Enter the birth month: ");
                    int m = in.nextInt();
                    System.out.println("Enter the birth day: ");
                    int d = in.nextInt();
                    System.out.println(myFriends.friendsMonthDay(m,d));
                    break;
                }
                case 7: {
                    System.out.println("Enter first name of your friend: ");
                    String fn = in.next();
                    System.out.println("Enter last name of your friend: ");
                    String ln = in.next();
                    System.out.println("----------------------------");
                    System.out.println("Cell number of " + fn + " " + ln + " is: " +
                                        myFriends.findCell(fn,ln) + ".");
                    System.out.println("----------------------------");
                    break;
                }
                default: {
                    answer = 0;
                    break;
                }
            }
        } while (answer != 0);
    }
}
