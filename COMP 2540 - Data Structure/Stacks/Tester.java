// Stack && Queue OP

public class Tester
{
	public static void main(String[] args) {
	    System.out.println("Stack : ");
		Stack s = new Stack();
		
		s.push(5);
		s.push(10);
		s.push(15);
		
		System.out.println(s);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s);

        System.out.println("\n\nQueue : ");
        Queue q = new Queue();
        
        q.enqueue(10);
        q.enqueue(15);
        q.enqueue(20);
        q.enqueue(25);
        System.out.println(q);
        q.HT();
        System.out.println("DeQueue : "+q.dequeue()+"\n");
        System.out.println(q);
        q.HT();
        
        q.enqueue(25);
        System.out.println(q);
        q.HT();
	}
}

