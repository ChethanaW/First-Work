package lab1question3;

import java.util.*;

/**
 * Specializes the stack data structure for comparable elements, and provides
 * a method for determining the maximum element on the stack in O(1) time.
 * @author jameselder
 */
public class MaxStack<E extends Comparable<E>> extends Stack<E> {

    private Stack<E> maxStack;
    private Stack<E> tempStack;
    //private int size;

    public MaxStack() {
        maxStack = new Stack<>();
        tempStack = new Stack<>();
        
    }

    /* must run in O(1) time */
    public E push(E element) {
    	tempStack.add(element);
    	//E atbottom = maxStack.lastElement();
    	if(maxStack.empty()){
    		maxStack.add(element);
    	}else if(element.compareTo(maxStack.lastElement()) >0 ){
    		maxStack.add(element);
    	}else{
    		maxStack.add(maxStack.lastElement());
    	}
    	
        return element; //Dummy return to satisfy compiler.  Remove once coded.
    }

    /* @exception  EmptyStackException  if this stack is empty. */
    /* must run in O(1) time */
   public synchronized E pop() {
	   if(maxStack.isEmpty()){
		   throw new EmptyStackException();
	   }  
	   maxStack.remove(maxStack.size()-1);
	   tempStack.remove(maxStack.size()-1);
	   return null;
    }

    /* Returns the maximum value currenctly on the stack. */
    /* @exception  EmptyStackException  if this stack is empty. */
    /* must run in O(1) time */
    public synchronized E max() {
    	return maxStack.lastElement();
    	
    	
         //Dummy return to satisfy compiler.  Remove once coded.
    }
}