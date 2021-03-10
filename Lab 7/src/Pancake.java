import java.awt.*;
import javax.swing.*;

public class Pancake extends CircleTile.Circle{


	public Pancake(CircleTile circleTile, int x, int y, Color color, int size) {
		circleTile.super(x, y, color, size);
		
	}

	public void draw(Graphics g) {
		super.draw(g);				//draw the middle circle
		g.setColor(Color.white);
		int x = 38;
		int y = 47;
		for(int i = 0; i < 4; i++) {			//draw the repeating pattern with several for loops
			g.fillOval(x+(i*8),y-(i*7),7,7);
		}
		for(int i = 0; i < 4; i++) {
			g.fillOval(x+(i*8),y+(i*7),7,7);
		}
		x = 96;
		y = 47;
		for(int i = 0; i < 4; i++) {
			g.fillOval(x-(i*8),y-(i*7),7,7);
		}
		
		for(int i = 0; i < 4; i++) {
			g.fillOval(x-(i*8),y+(i*7),7,7);
		}
		
	}
}
