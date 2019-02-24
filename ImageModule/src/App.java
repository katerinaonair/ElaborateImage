/*
 * author: Katerina Elgina
 * email: katerina.elgina@gmail.com
 */


import java.net.MalformedURLException;
import java.net.URL;


public class App {

	public static void main(String[] args) throws MalformedURLException {
       
		/*
		 *  Assume the Parameters arrive from Front End
		 *  the image URL, the image width and the image heights 
		 */
		
        URL url = new URL("https://news.nationalgeographic.com/content/dam/news/2018/05/17/you-can-train-your-cat/02-cat-training-NationalGeographic_1484324.ngsversion.1526587209178.adapt.1900.1.jpg");       
        int newWidth = 410;
		int newHeight = 350;
        
		
		ImageToChange newImage = new ImageToChange();
	        
	    newImage.makeImageBlackWhite(url, newHeight, newWidth);
		
	}

}
