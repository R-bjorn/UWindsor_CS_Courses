public class Node{
    private String name;
    private Node nextNode;
    
    // Constructor
    public Node(String name){        this.name = name;    }
    
    // Getters and Setters
    public String getName(){        return name;    }
    public Node getNextNode(){        return nextNode;    }
    public void setName(String name){        this.name = name;    }
    public void setNextNode(Node nextNode){        this.nextNode = nextNode;    }
    
    //Other methods
    public String toString(){
        return "Data : "+this.name;
    }
}
