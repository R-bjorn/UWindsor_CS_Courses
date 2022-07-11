public class DoublyEndedList{
    private Node head;
    private Node tail;
    
    public void insertAtTail(String data){
        Node newNode = new Node(data);
        
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        }
        
        if(this.tail != null){
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
    }
    
    public String toString(){
        String result = "{";
        Node current =this.head;
        
        while(current != null){
            result += current.toString() + ",";
            current = current.getNextNode();
        }
        result += "}";
        
        return result;
    }
    
}
