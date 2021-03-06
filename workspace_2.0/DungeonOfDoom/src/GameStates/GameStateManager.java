package GameStates;

import java.awt.Graphics;
import java.util.Stack;
import main.LevelLoader;

public class GameStateManager {
	
	//Stack allows you to push states
	public static Stack<GameState> states;
	
	public GameStateManager() {
		states = new Stack<GameState>();
		states.push(new LevelLoader(this));
	}
	
	public void update(){
		states.peek().update();
	}
	
	public void render(Graphics g){
		states.peek().render(g);
	}

	public void init() {
		states.peek().init();
	}
}