
import java.util.ArrayList;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sid
 */
public class CompositePattern {
    public static void main(String args[]){
        FolderElement fileSystem = new MyDirectory(("/"));
        MyCompositeIterator cmpItr;
        FolderElement root = new MyDirectory("root");
        fileSystem.addFolderElement(root);
        
        cmpItr = new MyCompositeIterator(fileSystem.getIterator());
        
        MyFile a;
        a = new MyFile("root->a");
        root.addFolderElement(a);
        a = new MyFile("root->b");
        root.addFolderElement(a);
        
        FolderElement inside = new MyDirectory("root->in");
        root.addFolderElement(inside);
        a = new MyFile("root->in->c");
        inside.addFolderElement(a);
        a = new MyFile("root->in->d");
        inside.addFolderElement(a);
        
        //System.out.println("Simple Iterator output");
        MyIterator simpleIterator = root.getIterator();
        while(simpleIterator.hasNext()){
            FolderElement elem = (FolderElement)simpleIterator.next();
           // System.out.println(elem.getName());
        }//end while
        
        System.out.println("Composite Iterator output");
        while(cmpItr.hasNext()){
            FolderElement elem = (FolderElement)cmpItr.next();
            System.out.println(elem.getName());
        }//end while
        
        
    }//end of method
}//end of class


abstract class FolderElement{
    protected String name;
    
    boolean isDirectory(){
        throw new UnsupportedOperationException();
    }//end
    
    boolean isFile(){
        throw new UnsupportedOperationException();
    }//end
    
    MyIterator getIterator(){
        throw new UnsupportedOperationException();
    }//end 
    
    String getName(){
        return name;
    }//end
    
    void addFolderElement(FolderElement elem){
        throw new UnsupportedOperationException();
    }//end
    
    void removeFolderElement(){
        throw new UnsupportedOperationException();
    }//end
    
    FolderElement getFolderElement(int index){
        throw new UnsupportedOperationException();
    }//end

    public FolderElement(String name) {
        this.name = name;
    }
    
    public int getSize(){
        throw new UnsupportedOperationException();
    }//end
}//end of class


class MyFile extends FolderElement{

    public MyFile(String name) {
        super(name);
    }//end of class

    @Override
    boolean isFile() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean isDirectory() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    MyIterator getIterator() {
        return new MyNullIterator(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}//end class


class MyDirectory extends FolderElement{
    private ArrayList<FolderElement> folderElement;
    
    public MyDirectory(String name) {
        super(name);
        folderElement = new ArrayList<FolderElement>();
    }//end of class

    @Override
    boolean isFile() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean isDirectory() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void addFolderElement(FolderElement elem) {
        folderElement.add(elem);
    }

    @Override
    FolderElement getFolderElement(int index) {
        return folderElement.get(index);
    }

    @Override
    public int getSize() {
        return folderElement.size(); //To change body of generated methods, choose Tools | Templates.
    }//end of method  

    @Override
    MyIterator getIterator() {
        return new ArrayListIterator(folderElement); //To change body of generated methods, choose Tools | Templates.
    } 
}//end class

//end of array list iterator
class ArrayListIterator implements MyIterator{
    ArrayList data;
    int cursor = 0;
    public ArrayListIterator(ArrayList data){
        this.data = data;
    }//end method

    @Override
    public boolean hasNext() {
        if(cursor < data.size()){
            return true;
        }
        return false;
    }//end 

    @Override
    public Object next() {
        Object ret = data.get(cursor);
        cursor++;
        return ret;
    }//end
}//end of class

class MyNullIterator implements MyIterator{

    @Override
    public boolean hasNext() {
        return false;
    }//end 

    @Override
    public Object next() {
        return null;
    }//end
}//end of class

class MyCompositeIterator implements MyIterator{
    Stack stack;

    public MyCompositeIterator(MyIterator itr) {
        stack = new Stack();
        stack.push(itr);
    }//end of class
    
    @Override
    public boolean hasNext() {
        if(stack.empty()){
            return false;
        }//end if
        MyIterator elem = (MyIterator)stack.peek();
        if(elem.hasNext()){
            return true;
        }//end if
        stack.pop();
        return hasNext();
    }//end of method

    @Override
    public Object next() {
        if(!hasNext())
            return null;
        //
        MyIterator itr = (MyIterator)stack.peek();
        FolderElement elem = (FolderElement) itr.next(); //root
        stack.add(elem.getIterator());
        return elem;
    }//end of method

}//end class