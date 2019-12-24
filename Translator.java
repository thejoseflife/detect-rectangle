package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Translator {

	//Translates colors into 0's(white) and 1's(black)
	public int[][] translateImage(BufferedImage image) {
		int[][] array = new int[image.getWidth()][image.getHeight()];
		for(int i = 0; i < image.getWidth(); i++) {
			for(int j = 0; j < image.getHeight(); j++) {
				if(new Color(image.getRGB(i, j)).getRed() > 240 &&
						new Color(image.getRGB(i, j)).getGreen() > 240 &&
						new Color(image.getRGB(i, j)).getBlue() > 240) array[i][j] = 0;
				else array[i][j] = 1;
			}
		}
		return array;
	}
	
}
