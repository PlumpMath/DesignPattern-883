
import java.util.ArrayList;

/*
 Iterator pattern gives a away to iterate through a data holder.
the data holder may store data in any data structures
a specific iterator would go through a specific datastructure one by one and return one
element at a time.
 */

/**
 *
 * @author sid
 */
public class IteratorPattern {
    public static void main(String args[]){
        FolderElement root = new MyDirectory("root");
        MyFile a;
        a = new MyFile("a");
        root.addFolderElement(a);
        a = new MyFile("b");
        root.addFolderElement(a);
        
        FolderElement inside = new MyDirectory("in");
        root.addFolderElement(inside);
        a = new MyFile("a");
        inside.addFolderElement(a);
        a = new MyFile("b");
        inside.addFolderElement(a);
        
        MyIterator rootItr = root.getIterator();
        while(rootItr.hasNext()){
            FolderElement elem = (FolderElement)rootItr.next();
            System.out.println(elem.getName());
        }
        
        int [] arr = {1, 3, 4, 5, 6, 7, 3, 5, 6};
        MyIterable dataHolder = new ArrayDataHolder(arr);
        MyIterator itr = dataHolder.getIterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }//end while
        
    }//end of main
}//end of class


//iterator interface
interface MyIterator {
    boolean hasNext();
    Object next();
}//end of interface

interface MyIterable{
    MyIterator getIterator();
}//end of class

class ArrayIterator implements MyIterator{
    int curPos = 0;
    Integer[] data;
    int dataSize;
    @Override
    public boolean hasNext() {
        if(curPos < dataSize){
            return true;
        }
        return false;
    }//end of method

    @Override
    public Object next() {
        Integer ret = data[curPos];
        curPos++;
        return ret;
    }//end of method

    public ArrayIterator(Integer[] data) {
        this.data = data;
        dataSize = data.length;
        curPos = 0;
    }
}//end of class

//this class stores data in an array
class ArrayDataHolder implements MyIterable{
    Integer[] data;

    public ArrayDataHolder(int [] arr) {
        data = new Integer[arr.length];
        for(int i = 0; i < arr.length; i++){
            data[i] = arr[i];
        }//end for
    }//end of constructor

    @Override
    public MyIterator getIterator() {
        MyIterator ret = new ArrayIterator(data);
        return ret;
    }//end of method
}//end of class


//for each kind of data holder create one Iterator
//data holder class does not implement iterator interface because then it has
//two reasons to change.