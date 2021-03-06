package GameStates;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.GameGraphics;
import main.Player;


public class GameLoop extends JPanel implements Runnable{
	
	private boolean running = false;
	private Thread thread;
	
	public Graphics graphics;
	public BufferedImage off_screen_gr_img;
	
	public GameStateManager gsm;
	
	private int width;
	private int height;
	
	public static double amountOfTicks = 60.0;
	
	
	public GameLoop(int width, int height) {
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 120.0;
		double ns = 1000000000 / amountOfTicks;
	
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
	
		while(running){

			//It takes time to get from lastTime to now
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			boolean shouldRender = false;
			
			while(delta >= 1){
				
				updates++;
				update();
				//Gets out of the if statements
				delta--;
				shouldRender = true;
			}
			
			if(shouldRender == true){
				frames++;
				render();
			}
			
			//sleepy
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//every second just to display
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println(updates);
				updates = 0;
				frames = 0;
			}
		
		}

	}
	
	public void init(){	
		//Making the off_screen_gr_img into a canvas to draw graphics on
		off_screen_gr_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//Setting graphics variable to allow the canvas to be an image
		//This STOPS the flicker as well as allows for lesser lag (Memory Management)
		graphics = off_screen_gr_img.createGraphics();
		
		running = true;
		//Initialising GameStateManager
		gsm = new GameStateManager();
		gsm.init();
		
	}
	
	public void update(){
		//Ticking the GameStateManager
		gsm.update();
	}
	
	public void render(){	
		//Clearing the canvas for further drawing
		graphics.clearRect(0, 0, width, height);
		//GameStateManager allowing to input graphics from other 
		gsm.render(graphics);
		//Drawing and disposing of the image by using the loop
		//This is where the graphics are drawn on the screen
		drawImage();
	}
	
	public void drawImage(){
		//Initialising new graphics 
		Graphics g2 = getGraphics();
		if(off_screen_gr_img != null){
			//Drawing the image on the screen, this will contain graphics taken from other classes
				//e,g, Player, Map etc.
			g2.drawImage(off_screen_gr_img, 0, 0, null);
		}
		//Disposing of the image, redrawing the image on the screen
		g2.dispose();
	}
		
}
