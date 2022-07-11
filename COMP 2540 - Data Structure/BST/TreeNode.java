public class TreeNode{
    private int data;
    private TreeNode leftChild;
    private TreeNode rightChild;
    
    //Constructor
    public TreeNode(int data){
        this.data = data;
    }
    
    //Getters and Setters 
    public void setleftChild(TreeNode leftChild){   this.leftChild = leftChild;     }
    public void setrightChild(TreeNode rightChild){     this.rightChild=rightChild;     }
    
    public int getData(){   return this.data;   }
    public TreeNode getleftChild() {    return this.leftChild;      }
    public TreeNode getrightChild(){    return this.rightChild;     }
    
    // Other methods
    public TreeNode find(int number){
        if(this.data = number)
            return this;
        if(this.data < number && leftChild != null)
            return leftChild.find(number);
        if(this.data > number && rightChild != null)
            return rightChild.find(number);
            
        return null;
    }
    
    public void insert(int data){
        if(data >= this.data){
            if(this.rightChild == null){
                this.rightChild = new TreeNode(data);
            }else{
                this.rightChild.insert(data);
            }
        }else{
            if(this.leftChild == null){
                this.leftChild = new TreeNode(data);
            }else{
                this.leftChild.insert(data);
            }
        }
    }
}
















































