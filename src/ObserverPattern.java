
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sid
 */
public class ObserverPattern {
    public static void main(String args[]){
        Subject weatherSatellite = new WeatherDataCollector();
        Observer nasa = new NasaWeatherObserver();
        Observer androidApp = new AndroidPhoneObserver();

        weatherSatellite.addObserver(nasa);
        weatherSatellite.addObserver(androidApp);
        
        weatherSatellite.updateMeasurement(10, 2);
        
        //android app no longer interested in observing data
        weatherSatellite.removeObserver(androidApp);
        
        //another update
        weatherSatellite.updateMeasurement(0, 0);
    }//end of method
}//end of class

//subject class directly collects data
interface Subject{
    void updateMeasurement(float temperature, float humidity);
    void addObserver(Observer o);
    void removeObserver(Observer o);
}//end of interface

//
interface Observer{
    void update(float temperature, float humidity);
}

//this is subject class. this knows about weather data
class WeatherDataCollector implements Subject{
    float temperature;
    float humidity;
    ArrayList<Observer> observer = new ArrayList<>();

    @Override
    public void updateMeasurement(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }//end of method

    
    public void notifyObservers() {
        for(Observer o : observer){
            o.update(temperature, humidity);
        }//end for
    }//end of method

    @Override
    public void addObserver(Observer o) {
        observer.add(o);
    }//end of method

    @Override
    public void removeObserver(Observer o) {
        int index = observer.indexOf(o);
        if(index >= 0)
            observer.remove(index);
    }//end of method
}//end of class

//concrete implementation of Observers
class NasaWeatherObserver implements Observer{
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("Nasa critical weather observer base has been notified");
    }//end of method
}//end of class

class AndroidPhoneObserver implements Observer{
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("Android phone app has been notified");
    }//end of method
}//end of class 
