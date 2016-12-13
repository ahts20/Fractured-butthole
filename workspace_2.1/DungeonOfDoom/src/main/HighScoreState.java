package main;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import GameStates.GameState;
import GameStates.GameStateManager;
/**
* HighScoreState called when the High Score button is clicked in the Menu State.
* Uses MenuState class to allow the user further interaction
* Uses GameStateButton class to allow the user to change the game states when clicked on the buttons.
* Uses MouseInput class to allow user to use the mouse for interaction.
* (Extends GameState to use the init(), update() and render() functions which connect to the GameLoop and JFrame respectively.)
*
* @version 1.0
* @release 13/12/2016
* @See HighScoreState.java
*/
public class HighScoreState extends GameState{
	//Classes Declared
	GameStateButton back;
	MouseInput mi;
	//Array storing the score
	ArrayList<String> lines = new ArrayList<String>();
	/**
	 * Constructor. Sets the field values.
	 * @param GameStateManager
	 * 		calls the GameStateManager class
	 */
	public HighScoreState(GameStateManager gsm) {
		super(gsm);

	}
	/**
	 * Part of GameLoop, Initialises the declared classes and fields.
	 * Specifies the GameStates to change to, for each GameStateButton.
	 * Adds score to the array.
	 * @exception IOException
	 * 		calls the exception when the text file is not found
	 * @see GameStates.GameState#init()
	 */
	@Override
	public void init() {
		mi = new MouseInput();
		back = new GameStateButton((Main.width / 2) - (GameStateButton.width / 2), (Main.height - 200), new MenuState(gsm), gsm, "BACK");
	
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("res/score.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Part of GameLoop, Updates the declared classes and fields (60 FramesPerSecond)
	 * @see GameStates.GameState#update()
	 */
	@Override
	public void update() {
		mi.update();
		back.update();

	    
	}
	/**
	 * Part of GameLoop, Sets the graphics for JFrame.
	 * Draws the buttons on the Screen.
	 * Draws the highest score on the Screen. 
	 * @see GameStates.GameState#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		mi.render(g);
		back.render(g);
		int highest = Integer.parseInt(lines.get(0));
		
		for(int i = 1; i < lines.size(); i++){
			if(Integer.parseInt(lines.get(i)) > highest){
				highest = Integer.parseInt(lines.get(i));
			}
		}
		
		g.drawString(String.valueOf(highest), 400, 200);
		
		g.clipRect(0,  0, Main.width, Main.height);
		
	}
}