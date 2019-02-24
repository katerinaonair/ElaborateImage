import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageReader;

public interface ImageFunctions {
	
	/*
	 * Makes the image Black and White
	 * Elaborates the original image to grayscale
	 * Parameters: url, width, height
	 */
	File makeImageBlackWhite(URL theUrl, int theWidth, int theHeight);
	
	
	/*
	 * Resizes the original image
	 */
	BufferedImage resizeImage(BufferedImage img, int theWidth, int theHeight);
	
	/*
	 * Saves the original image with file name and SimpleData like 
	 * 20190224_181216
	 * Parameters: Buffered image, width, fileName - new name for the file
	 */
	File saveImage(BufferedImage image, ImageReader read, String fileName );
	
}
