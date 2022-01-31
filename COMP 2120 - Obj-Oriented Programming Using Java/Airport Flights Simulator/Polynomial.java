import java.util.*;
import java.io.IOException;

public class Polynomial{
    // Instant Variables
    private Map<Integer , Integer> polynomial;
    private int degree = 0;
    
    // Constructor 
    public Polynomial(){        // with no parameters 
        // Creates an empty Polynomial
        polynomial = new HashMap<Integer , Integer>();
    }
    
    public Polynomial(int co_eff, int power){        // with 2 parameters 
        this();
        if(power < 0){
            System.out.println("java.lang.IllegalArgumentException: Power of a term can't be negative.");
            return;
        }
        if(degree < power){
            degree = power;
        }
        polynomial.put(power,co_eff);  
    }
    
    // Getters and Setters
    public int getPower(){
        for(Map.Entry<Integer,Integer> me : polynomial.entrySet()){
            return me.getKey();
        }
        return -1;
    }
    
    public int getCoeff(){
        for(Map.Entry<Integer,Integer> me : polynomial.entrySet()){
            return me.getValue();
        }
        return -1;
    }
    
    public int getDegree(){
        return this.degree;
    }
    
    
    // Other methods 
    
    public void add(Polynomial p1){
        if(p1.getPower() < 0){
            System.out.println("Exception in thread \"PolynomialTester\" java.lang.IllegalArgumentException: Power of a term can't be negative.");
            return;
        }
        if(degree < p1.getPower()){
            degree = p1.getPower();
        }
        boolean same = false;
        int total = 0;
        for(Map.Entry<Integer,Integer> me : polynomial.entrySet()){
            if(me.getKey() == p1.getPower()){
                same = true;
                total = me.getValue() + p1.getCoeff();
            }
        }
        
        if(same){
            polynomial.put(p1.getPower(),total); 
        }else{
            polynomial.put(p1.getPower(),p1.getCoeff()); 
        }
    }
    
    public Polynomial subtract(Polynomial p){
        // subtract this.Polynomial with p1
        add(p);
        return p;   
    }
    
    public Polynomial multiply(Polynomial p){
        // multiply this.Polynomial with p
        Polynomial result = new Polynomial();
        
        return result;
    }
    
    public int degree(){
        return degree;
    }
    
    public int coefficient(int power){
        for(Map.Entry<Integer,Integer> me : polynomial.entrySet()){
            if(me.getValue() == power){
                return me.getKey();
            }
        }
        return -1;
    }
    
    public double evaluate(double x){
        double result = 0;
        for(Map.Entry<Integer,Integer> me : polynomial.entrySet()){
            result += me.getKey()*Math.pow(x,me.getValue());
        }
        return result;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        return degree == that.degree &&
                Objects.equals(polynomial, that.polynomial);
    }
    
    @Override
    public String toString(){
        String result = "";
        for(Map.Entry<Integer,Integer> me : polynomial.entrySet()){
            if(me.getValue() > 0){
                if(me.getKey() == 0){
                    result += "+"+me.getValue() + " ";
                }
                else if(me.getKey() == 1){
                    result += "+"+me.getValue() + "x ";
                }else{
                    result += "+"+me.getValue() + "x" +me.getKey() + " ";
                }
            }else{
                if(me.getKey() == 0){
                    result += me.getValue() + " ";
                }
                else if(me.getKey() == 1){
                    result += me.getValue() + "x ";
                }else{
                    result += me.getValue() + "x" +me.getKey() + " ";
                }
            }
        }
        return result;
    }
}

















