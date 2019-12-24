package main;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class TranslatedImage {

	public int[][] array;
	public String shapeType = "Not Rectangle";
	//binary sequences of possible vertices
	private String[] fourDigitVertices = { //based off of training shapes
			"0111", "1011", "1101", "1110"
	};
	
	public TranslatedImage(BufferedImage image, Translator translator) {
		array = translator.translateImage(image);
	}
	
	public void identifyShape() { //counts number of vertices in object to determine shape
		int[][] twoByTwo = new int[2][2]; //array to store 2x2 sequences
		LinkedList<String> vertices = new LinkedList<String>();
		
		//2x2 search for four digit binary sequences
		for(int i = 0; i < array.length - 1; i++) {
			for(int j = 0; j < array.length - 1; j++) {
				twoByTwo[0][0] = array[i][j];
				twoByTwo[0][1] = array[i][j + 1];
				twoByTwo[1][0] = array[i + 1][j];
				twoByTwo[1][1] = array[i + 1][j + 1];
				//compare binary digits in vertices array to 2x2 codes
				for(String s: fourDigitVertices) {
					if(convertToBinaryNumber(twoByTwo).equals(s)) vertices.add(s);
				}
			}
		} //end of 4 digit search, 4 vertices = rectangle
		if(vertices.size() == 4) shapeType = "Rectangle";
	}
	
	//Convert array parameter into binary number
	public String convertToBinaryNumber(int[][] array) {
		String s = "";
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				s += array[i][j];
			}
		}
		return s;
	}
	
	public void printArray() { //Print array
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
