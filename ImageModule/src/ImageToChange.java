import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageToChange implements ImageFunctions{

	 URL url;
	 int width;
	 int height;
	
	public ImageToChange( )  {
		this.url = null;
		this.width = 0;
		this.height = 0;
	}
	
	@Override
	public File saveImage(BufferedImage image, ImageReader read, String fileName ) {
        
		// takes the date for the name of the file
		String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

		File outputFile = null;
		try {
			outputFile = new File(date+fileName+read.getFormatName());
			 System.out.println("The image is saved to: ");
		     System.out.println(outputFile.getAbsolutePath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        try {
			ImageIO.write(image, read.getFormatName(), outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputFile;
	} 
	

	@Override
	public BufferedImage resizeImage(BufferedImage img, int theWidth, int theHeight) {
		Image tmp = img.getScaledInstance(theWidth, theHeight, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(theWidth, theHeight, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        
        return resized;
	}

	@Override
	public File makeImageBlackWhite(URL theUrl, int theWidth, int theHeight) {
		BufferedImage image = null;
        
		try {
			image = ImageIO.read(theUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		// Get image extension
	    ImageInputStream iis = null;
			try {
				iis = ImageIO.createImageInputStream(theUrl.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
			Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

	         
	        ImageReader read = readers.next();
	            
		
		 //Save the original image
	      String fileName = "original.";
	      saveImage( image,  read, fileName );
         
         // Make changes  
         try {
			if(read.getFormatName() == "JPEG" || read.getFormatName() == "JPG" || read.getFormatName() == "PNG")
			 {
			 
			 // Resize image
			 BufferedImage resized = resizeImage(image, theHeight, theWidth);
			 
			 BufferedImage result = new BufferedImage(
			 		resized.getWidth(),
			 		resized.getHeight(),
			        BufferedImage.TYPE_INT_RGB);

			 // Make Black and White
			 Graphics2D graphic = result.createGraphics();
			 graphic.drawImage(resized, 0, 0, Color.WHITE, null);

			 for (int i = 0; i < result.getHeight(); i++) {
			     for (int j = 0; j < result.getWidth(); j++) {
			         Color c = new Color(result.getRGB(j, i));
			         int red = (int) (c.getRed() * 0.299);
			         int green = (int) (c.getGreen() * 0.587);
			         int blue = (int) (c.getBlue() * 0.114);
			         Color newColor = new Color(
			                 red + green + blue,
			                 red + green + blue,
			                 red + green + blue);
			         result.setRGB(j, i, newColor.getRGB());
			     }
			 }
			 
			//Save the elaborated image
		      String fileName2 = "garyScale.";
		      saveImage( result,  read, fileName2 );
			 }
			else {
				System.out.println("Format error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
         
		return null;
         
     }

	

}
