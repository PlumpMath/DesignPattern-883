/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sid
 */
public class SingletonPattern {
    
    public static void main(String args[]){
        Earth e = Earth.getEarthInstance();
        System.out.println("the temperature is " + e.getTemperature());
        e.setTemperature(55);
        
        //get a different earth instance
        Earth e1 = Earth.getEarthInstance();
        System.out.println("the temperature is " + e1.getTemperature());
    }//end of main
}//end of class

//there should be only one instance of the Earth
class Earth{
    static private Earth earth;
    private double temperature;
    
    public void setTemperature(double t){
        temperature = t;
    }//end of method
    
    public double getTemperature(){
        return temperature;
    }//end of method
    
    private Earth(){
        temperature = 10;
    }//end of constructor
    
    public static Earth getEarthInstance(){
        if(earth == null){
            earth = new Earth();
        }
        return earth;
    }//end of method
    
}//end of class