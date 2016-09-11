/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sid
 */
public class TemplateMethodPattern {
    public static void main(String args[]){
        AngryMomToday momAngry = new AngryMomToday();
        HappyMomToday momHappy = new HappyMomToday();
        
        momAngry.algorithmOfaDay();
        System.out.println();
        System.out.println();
        momHappy.algorithmOfaDay();
                
    }//end of method
}//end class

//encapsulates an algorithm
//some implemented at the super class
//some left for base class
abstract class TemplateClass{
    void play(){
        System.out.println("Playing");
    }
    
    void sing(){
        System.out.println("Singing");
    }
    
    void playVideoGames(){
        System.out.println("playing video games");
    }
    //leave for sub class to implement
    abstract boolean momPermits();
    
    //this is the algorithm that the Templateclass encapsulates
    void algorithmOfaDay(){
        play();
        sing();
        if(momPermits()){
            playVideoGames();
        }//end if
    }//end of method
}// end of template class

//concrete implementation of classes
class AngryMomToday extends TemplateClass{
    @Override
    boolean momPermits() {
        return false;
    }//end of method
}//end of class

class HappyMomToday extends TemplateClass{
    @Override
    boolean momPermits() {
        return true;
    }//end of method
}//end of class
