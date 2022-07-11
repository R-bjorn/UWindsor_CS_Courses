//Singal Linked List


public class LLTest
{
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		
		list.insertAtHead("Abir");
		list.insertAtHead("Niyu");
		list.insertAtHead("Ravi");
		list.insertAtHead("Mike");
		list.insertAtHead("Taylor");
		
		System.out.println(list);
		System.out.println("Length : "+list.length());
		list.deleteAtHead();
		System.out.println(list);
		System.out.println("Length : "+list.length());
		System.out.println(list.search("Abir"));
		System.out.println(list.search("XYZ"));
		
	}
}

