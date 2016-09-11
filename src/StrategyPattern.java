/*
 * Design Principle : Separate what changes from what varies. Prefer composition
    over inheritance.
    Strategy Pattern deals with a set of strageis, which can be assigned to an object
    on the fly.
 */


public class StrategyPattern {
    public static void main(String args[]){
        //behavior changes with emotion so it is not part of human class.
        //it is separated
        Behavior angry = new AngryBehavior();
        Behavior happy = new HappyBehavior();
        
        Human human = new Human(angry);
        
        human.see();
        human.behave();
        
        //change behavior dynamically
        human.setBehave(happy);
        human.behave();
        
    }//end of main
}//end of class

class Human{
    private Behavior behavior; 
    /*viewing ability does not change so part of class*/
    void see(){
        System.out.println("Seeing");
    }
    
    //behavoir changes with mood, so set dynamically
    void setBehave(Behavior b){
        behavior = b;
    }
    void behave(){
        behavior.behave();
    }//end of method

    public Human(Behavior behavior) {
        this.behavior = behavior;
    }//end of constructor 
    
}//end of class

//what changes separated
interface Behavior{
    void behave();
}

//concrete implementation of behavor

class AngryBehavior implements Behavior{

    @Override
    public void behave() {
        System.out.println("I am angry");
    }
}//end of class


class HappyBehavior implements Behavior{
    @Override
    public void behave() {
        System.out.println("I am happy");
    }//end of method    
}//end of class

// add more behavior as required