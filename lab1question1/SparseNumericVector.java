package lab1question1;

import java.util.*;

/**
 * Represents a sparse numeric vector. Elements are comprised of a (long)
 * location index an a (double) value.  The vector is maintained in increasing
 * order of location index, which facilitates numeric operations like
 * inner products (projections).  Note that location indices can be any integer
 * from 1 to Long.MAX_VALUE.  The representation is based upon a
 * singly-linked list.
 * The following methods are supported:  iterator, getSize, getFirst,
 * add, remove, and dot, which takes the dot product of the with a second vector
 * passed as a parameter.
 * @author jameselder
 */
public class SparseNumericVector implements Iterable {

    protected SparseNumericNode head = null;
    protected SparseNumericNode tail = null;
    protected long size;

  /**
     * Iterator
     */
    @Override
    public Iterator<SparseNumericElement> iterator() { //iterator
        return new SparseNumericIterator(this);
    }

    /**
     * @return number of non-zero elements in vector
     */
   public long getSize() {
        return size;
    }

     /**
     * @return the first node in the list.
     */
    public SparseNumericNode getFirst() {
        return head;
    }
    
    /**
     * Add the element to the vector.  It is inserted to maintain the
     * vector in increasing order of index.  If the element has zero value, or if 
     * an element with the same index already exists, an UnsupportedOperationException is thrown. 
     * @param e element to add
     */
  public void add(SparseNumericElement e) throws UnsupportedOperationException {
       
	  //implement this method
	  //check if element has zero value
	 
	  if (e.getValue() == 0){
		  throw new UnsupportedOperationException();
	  }
	  SparseNumericIterator it = new SparseNumericIterator(this);
	 
	  while (it.hasNext() ){
		  if(it.next().getIndex() == e.getIndex()){
			  throw new UnsupportedOperationException();
		  }  
	  }
	 
	  if (this.getFirst() == null){
		  head = new SparseNumericNode(e,null);
		  tail= head; 
		  size =1;
	  }else if(this.getSize() ==1){
		  SparseNumericNode current = head;
		  if(current.getElement().getIndex() < e.getIndex()){
			  SparseNumericNode n = new SparseNumericNode(e,null);
			  head.setNext(n);
			  tail=n;
		  }else{
			  current = new SparseNumericNode(current.getElement(),null);
			  head = new SparseNumericNode(e,current);
		  }
		  size =2;
		  
	  }else if(this.getSize() ==2){
		 SparseNumericNode current = head;
		 
		 if(head.getElement().getIndex() < e.getIndex()){
			 SparseNumericNode n = new SparseNumericNode(e,tail);
			 head.setNext(n);
			 size = 3;
		 }
	  }
	  else{
		  
		  SparseNumericNode current = head;
		  SparseNumericNode next = head.getNext();
		  
		  while(next.getElement().getIndex() < e.getIndex() && next.getNext().getElement().getIndex() > e.getIndex() && next.getNext() != null){
	
			  current = current.getNext();
			  next =next.getNext();
			  
		  }
		  if(next.getNext() == null){ 
			  SparseNumericNode n = new SparseNumericNode(e,null);
			  next.setNext(n);
			  tail =n;
		  }else{
			  SparseNumericNode n = new SparseNumericNode(e,next);
			  current.setNext(n);
			  next =n;
		  }
		 
		  
		  
		  size= size +1;
		 
		  
		  
	  }
	  
	 
	  
	  
    }

    /**
     * If an element with the specified index exists, it is removed and the
     * method returns true.  If not, it returns false.
     *
     * @param index of element to remove
     * @return true if removed, false if does not exist
     */
    public boolean remove(Long index) {
    	boolean result = false;
    	if (index < 0 || index > Long.MAX_VALUE){
    		result = false;
    	}
    	
    	if(this.getSize() ==0){
    		return result;
    	}else if(head==tail){
    		if(head.getElement().getIndex() == index){
    			head = null;
        		tail=null;
        		size =0;
        		result = true;
    		}
    		
    	}else if(this.getSize() ==2){
    		if(head.getElement().getIndex() == index){
        		head = tail;
        		size =1;
        		result = true;
    		}else if(tail.getElement().getIndex() == index){
    			head.setNext(null);
    			size =1;
    			result = true;
    		}
    	}else{
    		SparseNumericIterator it = new SparseNumericIterator(this);
    		SparseNumericNode current = head;
    		SparseNumericNode next = head.getNext();
    		if(current.getElement().getIndex() == index){
    			head =next;
    			size = size -1;
    			
    		}else{
    			for(int n=0; this.getSize() ==n ; n++){
        			
        			if(next.getElement().getIndex() == index){
        				current.setNext(next.getNext());
        				result = true;
        				size = size -1;
        				break;
        			}
        			current = current.getNext();
        			next = next.getNext();
        		}
    		}
    		
    	}
    	
        return result; 
        
    }

    /**
     * Returns the inner product of the vector with a second vector passed as a
     * parameter.  The vectors are assumed to reside in the same space.
     * Runs in O(m+n) time, where m and n are the number of non-zero elements in
     * each vector.
     * @param Y Second vector with which to take inner product
     * @return result of inner product
     */

    public double dot (SparseNumericVector Y) {
        //implement this method
        //this return statement is here to satisfy the compiler - replace it with your code.
    	SparseNumericNode vone = head;
    	SparseNumericNode vtwo = Y.getFirst();
    	int totalrun = (int) (this.getSize() +Y.getSize());
    	int c= 0;
    	double dot = 0;
    	//System.out.print("a");
    	for(int count = 0; count != totalrun ; count++){
    		//System.out.print("b");
    		if (vone ==null || vtwo ==null){
    			break;
    		}
    		if(vone.getElement().getIndex() == vtwo.getElement().getIndex()){
    			//System.out.print("c");
    			double a=vone.getElement().getValue();
    			double b=vtwo.getElement().getValue();
    			dot = dot + (a*b)  ;
    			vone =vone.getNext();
    			vtwo = vtwo.getNext();
    			
    		}
    		else if(vone.getElement().getIndex() < vtwo.getElement().getIndex()){
    			vone =vone.getNext();
    			//System.out.print("g");
    		}
    		else if(vone.getElement().getIndex() > vtwo.getElement().getIndex()){
    			vtwo = vtwo.getNext();
    			//System.out.print("h");
    		}
    		
    		
    	}
        return dot;
   }

       /**
     * returns string representation of sparse vector
     */

    @Override
    public String toString() {
        String sparseVectorString = "";
        Iterator<SparseNumericElement> it = iterator();
        SparseNumericElement x;
        while (it.hasNext()) {
            x = it.next();
            sparseVectorString += "(index " + x.getIndex() + ", value " + x.getValue() + ")\n";
        }
        return sparseVectorString;
    }
}

