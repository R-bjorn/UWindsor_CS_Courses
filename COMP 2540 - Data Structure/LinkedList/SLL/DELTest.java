// Doubly Ended List

public class DELTest
{
	public static void main(String[] args) {
		DoublyEndedList list = new DoublyEndedList();
		
		list.insertAtTail("Abir");
		list.insertAtTail("Niyu");
		list.insertAtTail("Ravi");
		list.insertAtTail("Mike");
		list.insertAtTail("Taylor");
		
		System.out.println(list);
// 		System.out.println("Length : "+list.length());
// 		list.deleteAtHead();
// 		System.out.println(list);
// 		System.out.println("Length : "+list.length());
// 		System.out.println(list.search("Abir"));
// 		System.out.println(list.search("XYZ"));
		
	}
}

