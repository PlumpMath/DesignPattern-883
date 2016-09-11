/*
 create composite objects dynamically with various ingredients.
 */

/**
 *
 * @author sid
 */
public class DecoratorPattern {
    //create an order, houseblend with double mocha with milk
    
    public static void main(String args[]){
        Beverage order = new HouseBlend();
        order = new Mocha(order);
        order = new Mocha(order);
        order = new Milk(order);
        
        System.out.println(order.cost() + " " + order.getDescription());
    }
}//end of class

abstract class Beverage{
    protected String description = "Unknown Description";
    
    public String getDescription(){
        return description;
    }
    
    public abstract double cost();
}//end of class

//Concrete Implementation for coffee types
class Espresso extends Beverage{
    Espresso(){
        description = "Espresso";
    }
    
    @Override
    public double cost() {
        return 1.99;
    }
}//end of class

class HouseBlend extends Beverage{

    public HouseBlend() {
        description = "HouseBlend";
    }
    
    @Override
    public double cost() {
        return .89;
    }
}//end of class


//now implement codinements
class Milk extends Beverage{
    Beverage beverage;
    public Milk(Beverage b) {
        beverage = b;
        description = "Milk";
    }
    
    @Override
    public double cost() {
        return .20 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return "Milk, " + beverage.getDescription(); //To change body of generated methods, choose Tools | Templates.
    }
}//edn of class

class Mocha extends Beverage{
    Beverage beverage;
    public Mocha(Beverage b) {
        beverage = b;
        description = "Milk";
    }
    
    @Override
    public double cost() {
        return .70 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return "Mocha, " + beverage.getDescription(); //To change body of generated methods, choose Tools | Templates.
    }
}//edn of class