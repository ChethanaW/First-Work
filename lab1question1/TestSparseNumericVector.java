
package lab1question1;
/**
*
* @author jameselder
*/
public class TestSparseNumericVector {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       SparseNumericVector X = new SparseNumericVector();
       SparseNumericVector Y = new SparseNumericVector();
       double projection;

       X.add(new SparseNumericElement(150000, 3.1415));
       //System.out.println("a");
       X.add(new SparseNumericElement(15, 3));
       //System.out.println("b");

       X.add(new SparseNumericElement(1500, 3.14));

       X.add(new SparseNumericElement(150, 3.1));
       X.add(new SparseNumericElement(15000, 3.141));
    

       Y.add(new SparseNumericElement(150000, 1));
       

       Y.add(new SparseNumericElement(15, 1));
      

       X.remove((long) 13);

       projection = X.dot(Y);
       //System.out.print(X.remove((long) 150));
       System.out.println("The inner product of");
       System.out.print(X);
       System.out.println("and");
       System.out.print(Y);
       System.out.println("is ");
       System.out.printf("%.5f\n\n",projection); //answer should be 3*1 + 3.1415*1 = 6.1415
    }

}