package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

	BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage[] trainingSet;
	BufferedImage[] testingSet;
	
	//loads training and testing images
	public Images() {
		trainingSet = new BufferedImage[5];
		testingSet = new BufferedImage[5];
		try {
			trainingSet[0] = loader.loadImage("/Training Set/Circle.png");
			trainingSet[1] = loader.loadImage("/Training Set/Rectangle.png");
			trainingSet[2] = loader.loadImage("/Training Set/Rhombus.png");
			trainingSet[3] = loader.loadImage("/Training Set/Square.png");
			trainingSet[4] = loader.loadImage("/Training Set/Triangle.png");
			
			testingSet[0] = loader.loadImage("/Testing Set/Image1.png");
			testingSet[1] = loader.loadImage("/Testing Set/Image2.png");
			testingSet[2] = loader.loadImage("/Testing Set/Image3.png");
			testingSet[3] = loader.loadImage("/Testing Set/Image4.png");
			testingSet[4] = loader.loadImage("/Testing Set/Image5.png");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
