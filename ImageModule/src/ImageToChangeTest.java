import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ImageToChangeTest {

	//can save an image
	@Test
	public void canSaveImage()
	{
		//Given
        URL url = null;
		try {
			url = new URL("https://news.nationalgeographic.com/content/dam/news/2018/05/17/you-can-train-your-cat/02-cat-training-NationalGeographic_1484324.ngsversion.1526587209178.adapt.1900.1.jpg");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}       
		ImageToChange imageToChange = new ImageToChange();
		BufferedImage image = null;
		ImageInputStream iis = null;
		try {
			image = ImageIO.read(url);
			iis = ImageIO.createImageInputStream(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

         
        ImageReader read = readers.next();
		String fileName = "test.";
		
	     
	    //When
		File img = imageToChange.saveImage(image, read, fileName);
		
		//Then
		Assert.assertEquals(imageToChange.getClass(), img.getAbsolutePath());
	}
	
	//can resize an image
	@Test
	public void canResizeImage() throws IOException
	{
		BufferedImage image = null;
		int theHeight = 600;
		int theWidth = 800;
		URL url = null;
		try {
			url = new URL("https://news.nationalgeographic.com/content/dam/news/2018/05/17/you-can-train-your-cat/02-cat-training-NationalGeographic_1484324.ngsversion.1526587209178.adapt.1900.1.jpg");
			image = ImageIO.read(url);

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}  
		
		ImageToChange imageToChange = new ImageToChange();
		imageToChange.resizeImage(image, theHeight, theWidth);
		
		Assert.assertEquals(0, imageToChange.resizeImage(image, theWidth, theHeight));
	}
}
