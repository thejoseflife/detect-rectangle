package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;
	
	//find images with path in source folder
	public BufferedImage loadImage(String path) throws IOException {
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
	
}
