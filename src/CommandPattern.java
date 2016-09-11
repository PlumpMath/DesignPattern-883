//from head first chapter 6
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
//command loader class
//creates bunch object and commands to be acted upon the objects. 
//There is a command pool that holds the commands and invokes when instructed
public class CommandPattern {
    public static void main(String args[]){
        //objects to act upon
        TV tv = new TV();
        Fridge fridge = new Fridge();
        
        //command invoker
        CommandInovoker invoker = new CommandInovoker();
        //create and add commands
        TVSwitchChannelCommand c1 = new TVSwitchChannelCommand(tv);
        invoker.addCommand(c1);
        FridgeTempChangeCommand c2 = new FridgeTempChangeCommand(fridge, 12);
        invoker.addCommand(c2);
        
        //commands added now invoke them
        invoker.invokeCommand();
        
    }//end of method
}//end of class

//does not know about concrete command types, only aware of Command interface
class CommandInovoker{
    ArrayList<Command> commandPool = new ArrayList<>();
    void addCommand(Command c){
        commandPool.add(c);
    }//end of method
    
    void invokeCommand(){
        for(Command c: commandPool){
            c.execute();
        }//end for
        commandPool.clear();
    }//end of method
}//end of class

interface Command{
    void execute();
}//end 

// some concrete objects to act on

class TV{
    public void switchChannel(){
        System.out.println("Switching tv channel");
    }//end of method
}//end of class

class Fridge{
    public void setTemperature(int temp){
        System.out.println("Setting fridge temperature to " + temp);
    }//end of method
}//end of class

//make concrete command object for each command
class TVSwitchChannelCommand implements Command{
    TV tv;
    @Override
    public void execute() {
        tv.switchChannel();
    }//end of method

    public TVSwitchChannelCommand(TV tv) {
        this.tv = tv;
    }
}//end of class

class FridgeTempChangeCommand implements Command{
    Fridge fridge;
    int temp;

    @Override
    public void execute() {
        fridge.setTemperature(temp);
    }//end of method

    public FridgeTempChangeCommand(Fridge fridge, int temp) {
        this.fridge = fridge;
        this.temp = temp;
    }//end of const   
}//end of class