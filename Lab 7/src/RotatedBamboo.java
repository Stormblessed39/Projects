import java.awt.*;

import javax.swing.*;

public class RotatedBamboo extends BambooTile.Bamboo {
	private double rotation;
	
	public RotatedBamboo(BambooTile bambooTile, int x, int y, Color color, double rotation) {
		bambooTile.super(x, y, color);
		this.rotation = rotation;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(rotation, 53, 53);		//rotate the way you want
		super.draw(g2);
		g2.rotate(-rotation, 53, 53);		//rotate back
	}

}
