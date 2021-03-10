import java.awt.*;
import javax.swing.*;

public class WhiteDragonTile extends Tile{
	
	public WhiteDragonTile()
	{
		setToolTipText("White Dragon");
	}
	
	public String toString()
	{
		return "White Dragon";
	}
	
	public void paintComponent(Graphics g) {
		if(getVis() == false) {
			return;
		}
		 super.paintComponent(g);		//uses for loops to create rectangles consisting of the sides of the box
		
		g.setColor(Color.blue);
		int x = getWidth()/2-32;
		int y = getHeight()/2-50;
		
		for(int i = 0; i < 6; i++) {
			g.setColor(Color.blue);
			g.drawRect(x, y, 14, 7);
			
			if(i%2 != 0) {
				g.setColor(Color.white);
			}
			else{
				g.setColor(Color.blue);
			}
			g.fillRect(x, y, 14, 7);
			x += 14;
		}
		y += 7;
		x-= 7;
		
		for(int i = 0; i < 5; i++) {
			g.setColor(Color.blue);
			g.drawRect(x, y, 7, 13);
			
			if(i%2 != 0) {
				g.setColor(Color.white);
			}
			else{
				g.setColor(Color.blue);
			}
			g.fillRect(x, y, 7, 13);
			y += 14;
		}
		
		 x = getWidth()/2-32;
		 y = getHeight()/2+27;
		
		for(int i = 0; i < 6; i++) {
			g.setColor(Color.blue);
			g.drawRect(x, y, 14, 7);
			
			if(i%2 != 0) {
				g.setColor(Color.white);
			}
			else{
				g.setColor(Color.blue);
			}
			g.fillRect(x, y, 14, 7);
			x += 14;
		}
		
		 x = getWidth()/2-32;
		 y = getHeight()/2-42;
		 
		for(int i = 0; i < 5; i++) {
			g.setColor(Color.blue);
			g.drawRect(x, y, 7, 13);
			
			if(i%2 == 0) {
				g.setColor(Color.white);
			}
			else{
				g.setColor(Color.blue);
			}
			g.fillRect(x, y, 7, 13);
			y += 14;
		}
		 
	}
	
	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("White Dragon");

		frame.add(new WhiteDragonTile());

		frame.pack();
		frame.setVisible(true);
	}
	
}
