public class DLL{
    private Node head;
    
    public void insertAtHead(int data){
        Node  newnode = new Node(data);
        newnode.setNext(this.head);
        
        if(this.head != null){
            this.head.setPrev(newnode);
        }
        
        this.head = newnode;
    }
    
    public String toString(){
        String result = "";
        Node current = this.head;
        while(current != null){
            result += current.toString() + "\n";
            current = current.getNext();
        }
        
        return result;
    }
}
