package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private Thread thread;
	private boolean running = false;
	public static final int WIDTH = 600, HEIGHT = 300;
	
	public Images images;
	public Translator translator;
	public TranslatedImage[] translatedImage = new TranslatedImage[5];
	public boolean training = true;
	
	private BufferedImage[] imagesToDraw = new BufferedImage[5];
	
	public void init() { //instantiate objects
		images = new Images();
		translator = new Translator();
		for(int i = 0; i < 5; i++) {
			if(training) {
				translatedImage[i] = new TranslatedImage(images.trainingSet[i], translator);
				imagesToDraw[i] = images.trainingSet[i];
			}
			else {
				translatedImage[i] = new TranslatedImage(images.testingSet[i], translator);
				imagesToDraw[i] = images.testingSet[i];
			}
			translatedImage[i].identifyShape();
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		init();
		while(running) render();
		stop();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);;
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", Font.BOLD, 13));
		
		//draw images and shape identities
		for(int i = 0; i < imagesToDraw.length; i++) {
			g.drawImage(imagesToDraw[i], 0 + (i * 110), 0, 100, 100, null);
			g.drawString(translatedImage[i].shapeType, 0 + (i * 110), 150);
		}
		
		bs.show();
		g.dispose();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Training Set");
		Display display = new Display();
		frame.add(display);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(new Dimension(WIDTH, HEIGHT));
		frame.setLocationRelativeTo(null);
		display.start();

	}

}
