public class LinkedList{
    private Node head;
    
    public void insertAtHead(String data){
        Node newNode = new Node(data);
        newNode.setNextNode(this.head);
        this.head = newNode;
    }
    
    public void deleteAtHead(){
        this.head = this.head.getNextNode();
    }
    
    public String search(String data){
        Node current = this.head;
        while(current != null){
            if(current.getName().equals(data)){
                return "Found : "+current.getName();
            }
            current = current.getNextNode();
        }
        return data+" : Not Found!!!";
    }
    
    public String toString(){
        String result = "{";
        Node current = this.head;
        
        while(current != null){
            result += current.toString() + ",";
            current = current.getNextNode();
        }
        
        result += "}";
            
        return result;
    }
    
    public int length(){
        int count = 0 ;
        Node current = this.head;
        while(current != null){
            count++;
            current = current.getNextNode();
        }
        return count;
    }
}
