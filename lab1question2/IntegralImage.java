package lab1question2;

/**
 * Represents an integer integral image, which allows the user to query the mean
 * value of an arbitrary rectangular subimage in O(1) time.  Uses O(n) memory,
 * where n is the number of pixels in the image.
 *
 * @author jameselder
 */
public class IntegralImage {

    private final int[][] integralImage;
    private final int imageHeight; // height of image (first index)
    private final int imageWidth; // width of image (second index)
    private final int[][] sumImage;
   
    /**
     * Constructs an integral image from the given input image.  
     *
     * @author jameselder
     * @param image The image represented
     * @throws InvalidImageException Thrown if input array is not rectangular
     */
    public IntegralImage(int[][] image) throws InvalidImageException {
        //implement this method.
    	
    	integralImage = image;
    	for (int n =0 ; n < image.length ; n++){
    		if(image[n].length != image.length){
    			throw new InvalidImageException();
    		}
    	}
    	imageHeight = image[0].length;//number of rows
    	imageWidth = image.length;// number of columns
    	sumImage= new int[imageHeight+1][imageWidth+1];
    	//int[][] newimage = new int[imageHeight+1][imageWidth+1];
    	
    	for (int i =1 ; i <= imageHeight ; i++){
    		for(int j = 1; j<= imageWidth; j++){
    			//newimage[i][j]= sumImage[i-1][j] + sumImage [i][j-1] + sumImage[i][j] - image[i-1][j-1];save forlater
    			//newimage[i][j] = integralImage[i-1][j-1];
    			//System.out.print(newimage[i][j]);
    			sumImage[i][j] = sumImage[i-1][j] + sumImage [i][j-1] + integralImage[i-1][j-1] - sumImage[i-1][j-1];
    			//System.out.print(sumImage[i][j]);
    			//System.out.print("a");
    		}
    		//System.out.println(" ");
    		//System.out.println(sumImage[1][1]);
    	}	
    }
    
  

    /**
     * Returns the mean value of the rectangular sub-image specified by the
     * top, bottom, left and right parameters. The sub-image should include
     * pixels in rows top and bottom and columns left and right.  For example,
     * top = 1, bottom = 2, left = 1, right = 2 specifies a 2 x 2 sub-image starting
     * at (top, left) coordinate (1, 1).  
     *
     * @author jameselder
     * @param top top row of sub-image
     * @param bottom bottom row of sub-image
     * @param left left column of sub-image
     * @param right right column of sub-image
     * @return 
     * @throws BoundaryViolationException if image indices are out of range
     * @throws NullSubImageException if top > bottom or left > right
     */
    public double meanSubImage(int top, int bottom, int left, int right) throws BoundaryViolationException, NullSubImageException {
        //implement this method
    	if (top < 0 || bottom < 0 || left <0 || right <0){
    		throw new BoundaryViolationException();
    	}
    	if (top > bottom || left > right){
    		throw new NullSubImageException();
    	}
    	double diff = sumImage[right+1][bottom+1] + sumImage[left][top] -sumImage[right+1][top] - sumImage[left][bottom+1];
    	int num = (bottom-top +1) * (right-left +1);
    	double mean = diff/num;
    
        return mean; //dummy value - remove once coded.
    }
}
