import java.util.*;

public class Stack{
    int data[];
    int top;
    
    public Stack(){
        top = -1;
        data = new int[10];
    }
    
    public int size(){
        return top+1;
    }
    
    public void push(int number){
        data[++top] = number;
    }
    
    public int pop(){
        if(top == -1){
            return -1;
        }
        int temp = data[top];
        data[top--] = 0;
        return temp;
    }
    
    public String toString(){
        String result = "Stack : { ";
        for(Integer i : data){
            if(i == 0){
                break;
            }
            result += String.valueOf(i) + ", ";
        }
        result += "}";
        return result;
    }
}
