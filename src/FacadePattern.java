/*
 * Facadae pattern gives simple interface to a complex system. The system may have
    lots of classes. With a simple interface, the facade pattern lets the client operate
    on the system.
 */

/**
 *
 * @author sid
 */
public class FacadePattern {
    public static  void main(String args[]){
        Tv t = new Tv();
        Light l = new Light();
        Projector p = new Projector();
        Sound s = new Sound();
        
        //simple interface to the system
        HomeTheaterFacade facade = new HomeTheaterFacade(t, p, s, l);
        
        //operate on the system
        facade.movieModeOn();
        System.out.println();
        //movie ended; stop it
        facade.movieModeOff();
        
    }//end of main
}

//Home Theatre Facade class hides the complexity of underlying system
class HomeTheaterFacade{
    Tv tv;
    Projector pr;
    Sound s;
    Light l;

    public HomeTheaterFacade(Tv tv, Projector pr, Sound s, Light l) {
        this.tv = tv;
        this.pr = pr;
        this.s = s;
        this.l = l;
    }//end of constructor    
    
    void movieModeOn(){
        tv.on();
        pr.down();
        s.on();
        l.setIntesity(.3); //dim the light to 30%
    }//end of method
    
    void movieModeOff(){
        tv.off();
        pr.up();
        s.off();
        l.setIntesity(1);
    }//end of method
}//end of class

////////////// Complex System //////////////////////////////
class Tv{
    void on(){
        System.out.println("tv on");
    }
    
    void off(){
        System.out.println("tv off");
    }
}//end of class

class Projector{
    void down(){
        System.out.println("projector down");
    }
    
    void up(){
        System.out.println("projector up");
    }
}//end of class

class Sound{
    void on(){
        System.out.println("sound on");
    }
    
    void off(){
        System.out.println("sound off");
    }
}//end of class

class Light{
    void setIntesity(double i){
        System.out.println("light set to intensity on " + i);
    }
}//end of class