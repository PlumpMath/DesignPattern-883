/*
Factory pattern.
Program to abstraction (super class).
Client is a concrete class of super class LivingCreature. 
Client knows about SuperClass body part and Body Part Factory Targetted to it.
It does not know about concrete implemenatations of the BodyPart super class.
The body part factory creates body parts necessary to the Living creature class
 */

/**
 *
 * @author sid
 */
public class FactoryPattern {
    public static void main(String args[]){
        LivingCreature human = new LivingHuman();
        human.see();
        human.move();
        human.eat();
    }
    
}//end of class

abstract class BodyPart{
    String desc = "unknown part";
    void function(){
        System.out.println("Using " + desc);
    }//end of method
}//end of class

abstract class BodyPartFactory{
    abstract BodyPart createEye();
    abstract BodyPart createLeg();
    abstract BodyPart createMouth();
}//end of class

//super class for all the living creatures
abstract class LivingCreature{
    BodyPartFactory bfactory;
    BodyPart legs, eyes, mouth;
    String desc = "Undefined";
    
    
    void see(){
        System.out.print("a " + desc + " Viewing ");
        eyes.function();
    }//end see
    
    void eat(){
        System.out.print("a " + desc + " Eating ");
        mouth.function();
    }//end of method 
    
    void move(){
        System.out.print("a " + desc + " Moving ");
        legs.function();
    }//end of method
}//end of class

//creating a human body parts
class HumanEyes extends BodyPart{

    public HumanEyes() {
        desc = "Human Eyes";
    }//end of constructor
}//end of class

class HumanMouth extends BodyPart{
    public HumanMouth(){
        desc = "Human Mouth";
    }//end of consturctor
}//end of class

class HumanLegs extends BodyPart{
    public HumanLegs(){
        desc = "Human Legs";
    }//end of consturctor
}//end of class

//create a human body part factory
//this class knows about all the concrete classes of human body parts
class HumanBodyPartFactory extends BodyPartFactory{

    @Override
    BodyPart createEye() {
        BodyPart b = new HumanEyes();
        return b;
    }//end of methods

    @Override
    BodyPart createLeg() {
        BodyPart b = new HumanLegs();
        return b;
    }//end of methods

    @Override
    BodyPart createMouth() {
        BodyPart b = new HumanMouth();
        return b;
    }//end of methods
}//end of class

//this class knows about human body part factory; and super class BodyPart
//It does not know about the concrete implementations of the BodyPart classes
class LivingHuman extends LivingCreature{
    
    public LivingHuman() {
        bfactory = new HumanBodyPartFactory();
        desc = "Human";
        legs = bfactory.createLeg();
        eyes = bfactory.createEye();
        mouth = bfactory.createMouth();
    }//end of class
    
}//end of class

//we can add new Living creatures without touching the above tested code