public class Queue{
    int data[];
    int head;
    int tail;
    
    public Queue(){
        data = new int[3];
        head = -1;
        tail = -1;
    }
    
    public void enqueue(int number){
        if(head == -1 && tail == -1){
            head = 0;
        }
        if(Math.abs(tail - head) >= data.length - 1){
            System.out.println("\nQueue is FULL !!! Can't add '"+number+"'\n");
        }else if(tail+1 == data.length){
            tail = 0;
            if(data[tail] == 0){
                data[tail] = number;
            }
        }else{
            data[++tail] = number;
        }
        
        
    }
    
    public void HT(){
        System.out.println("Head at : "+data[head]);
        System.out.println("Tail at : "+data[tail]+"\n");
    }
    
    public int dequeue(){
        if(head == -1){
            return -1;
        }
        int temp = data[head];
        data[head] = 0;
        if(head+1 == data.length){
            head = 0;
        }
        else{
            head++;
        }
        return temp;
    }
    
    public int peek(){
        return data[head];
    }
    
    public String toString(){
        String result = "\nQueue : { ";
        for(Integer i : data){
            result += String.valueOf(i)+", ";
        }
        result += "}\n";
        return result;
    }
}




