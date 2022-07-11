public class Node{
    private int data;
    private Node prev;
    private Node next;
    
    public Node(int data){
        this.data = data;
    }
    
    public int getData(){  return data;    }
    public Node getPrev(){  return prev;    }
    public Node getNext(){  return next;    }
    
    public void setData( int Data){     this.data = Data;   }   
    public void setPrev(Node prev){     this.prev = prev;   } 
    public void setNext(Node next){     this.next = next;   }
    
    public String toString(){
        return "Data : "+this.data;
    }
}
