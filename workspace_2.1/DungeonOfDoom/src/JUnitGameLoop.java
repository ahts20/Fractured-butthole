import static org.junit.Assert.*;
import javax.swing.JFrame;
import main.Main;
import org.junit.After;
import org.junit.Test;

import GameStates.GameLoop;
import GameStates.GameStateManager;

public class JUnitGameLoop extends JFrame{
	private static final long serialVersionUID = 1L;
	private GameLoop gl;
	
	public JUnitGameLoop() {
		//JFrame
		add(gl = new GameLoop(Main.width, Main.height));
		setTitle("JUNIT");
		setSize(Main.width, Main.height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	@Test
	public void testAddNotify() {
		assertNotNull(gl.thread);
	}

	@Test
	public void testGameLoop() {
		assertEquals(Main.width, gl.width);
		assertEquals(Main.height, gl.height);
	}

	@Test
	public void testRun() {
		//sleepy
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(60, gl.fps, 5);
	}
	
	@Test
	public void testInit() {
		assertNotNull(gl.off_screen_gr_img);
		assertEquals(true, gl.running);
	}

	@Test
	public void testUpdate() {
		assertNotNull(GameStateManager.states);
		assertNotNull(GameStateManager.states.peek());
	}

	@SuppressWarnings("deprecation")
	@After
	public void cleanIndex() {
	    gl.thread.stop();
	}

}