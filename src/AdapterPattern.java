/*
 Client(MySoftware) does not know how to send packet through a linux network
it only is aware about an interface MySoftwareNetworkUtility. To run the software in linux
we need an adapter that is wrapped around LinuxNetworkUtility.
 */

/**
 *
 * @author sid
 */
public class AdapterPattern {
    public static void main(String[] args) {
        LinuxNetworkUtility linuxNetworkUtility = new LinuxNetworkUtility();
        //wrap around linux network utility
        MySoftwareNetworkUtility linuxNetworkAdapter = new MyLinuxNetworkAdapter(linuxNetworkUtility);
        //instantite my software
        MySoftware mySoftware = new MySoftware(linuxNetworkAdapter);
        //run my software on linux
        mySoftware.doCoolThingOverNetwork();
    }//end of main
}//end of class



//interface that MySoftware knows about
interface MySoftwareNetworkUtility{
    void sendToDestination(String text, int ip);
}//end of interface


// does not know anything about linux network utility
//knows only about MySoftwareNetworkUtility interface
class MySoftware{
    MySoftwareNetworkUtility networkUtility;

    public MySoftware(MySoftwareNetworkUtility networkUtility) {
        this.networkUtility = networkUtility;
    }//end of consturctor
    
    void doCoolThingOverNetwork(){
        networkUtility.sendToDestination("saif is boss", 34);
    }//end of method
}//end of class

//how packets are sent through linux
class LinuxNetworkUtility{
    // urgency 0 is of highest priority
    void sendPacket(int ip, String text, int urgency){
        System.out.println("Sending packet through Linux network");
    }//end of method
}//end of class

//adapter for linux network utility
class MyLinuxNetworkAdapter implements MySoftwareNetworkUtility{
    LinuxNetworkUtility linuxNetUtil;

    public MyLinuxNetworkAdapter(LinuxNetworkUtility linuxNetUtil) {
        this.linuxNetUtil = linuxNetUtil;
    }//end of constructor    
    
    @Override
    public void sendToDestination(String text, int ip) {
        linuxNetUtil.sendPacket(ip, text, 10); //by default 10 priority for linux
    }//end of method
}//end of class