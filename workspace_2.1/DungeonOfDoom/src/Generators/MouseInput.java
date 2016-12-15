package Generators;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener,  MouseMotionListener {
	
	public static int mouseMovedX, mouseMovedY;
	public static Point mouse;
	public static boolean pressed;
	
	public void update(){
		mouse = new Point(mouseMovedX, mouseMovedY);
	}
	
	public void render(Graphics g){
//		g.fillRect(mouseMovedX, mouseMovedY, 4, 4);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			pressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			pressed = false;
		}

	}
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static int getMouseMovedX() {
		return mouseMovedX;
	}

	public static void setMouseMovedX(int mouseMovedX) {
		MouseInput.mouseMovedX = mouseMovedX;
	}

	public static int getMouseMovedY() {
		return mouseMovedY;
	}

	public static void setMouseMovedY(int mouseMovedY) {
		MouseInput.mouseMovedY = mouseMovedY;
	}

	public static boolean isPressed() {
		return pressed;
	}

	public static void setPressed(boolean pressed) {
		MouseInput.pressed = pressed;
	}
	

}