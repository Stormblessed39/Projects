import java.awt.*;

import javax.swing.*;

public class BambooTile extends RankTile{
	private Bamboo[] bamboos = new Bamboo[rank];
	
	BambooTile(int rank){
		super(rank);
		setToolTipText(toString());
		 Color Color1 = new Color(0,205,0);
		switch(rank) {		//create the bamboo at the correct location and with correct color
		
		case 2:
			bamboos[0] = new Bamboo(65, 20, Color.blue);
			bamboos[1] = new Bamboo(65, 55, Color1);
			break;
		case 3:
			bamboos[0] = new Bamboo(65, 20, Color.blue);
			bamboos[1] = new Bamboo(47, 55, Color1);
			bamboos[2] = new Bamboo(83, 55, Color1);
			break;
		case 4:
			bamboos[0] = new Bamboo(47, 20, Color.blue);
			bamboos[1] = new Bamboo(83, 20, Color1);
			bamboos[2] = new Bamboo(47, 55, Color1);
			bamboos[3] = new Bamboo(83, 55, Color.blue);
			break;
		case 5:
			bamboos[0] = new Bamboo(38, 20, Color1);
			bamboos[1] = new Bamboo(92, 20, Color.blue);
			bamboos[2] = new Bamboo(38, 55, Color.blue);
			bamboos[3] = new Bamboo(92, 55, Color1);
			bamboos[4] = new Bamboo(65, 37, Color.red);
			break;
		case 6:
			bamboos[0] = new Bamboo(38, 20, Color1);
			bamboos[1] = new Bamboo(92, 20, Color1);
			bamboos[2] = new Bamboo(38, 55, Color.blue);
			bamboos[3] = new Bamboo(92, 55, Color.blue);
			bamboos[4] = new Bamboo(65, 55, Color.blue);
			bamboos[5] = new Bamboo(65, 20, Color1);
			break;
		case 7:
			bamboos[0] = new Bamboo(38, 34, Color1);
			bamboos[1] = new Bamboo(92, 34, Color1);
			bamboos[2] = new Bamboo(38, 69, Color1);
			bamboos[3] = new Bamboo(92, 69, Color1);
			bamboos[4] = new Bamboo(65, 69, Color.blue);
			bamboos[5] = new Bamboo(65, 34, Color.blue);
			bamboos[6] = new Bamboo(65, 2, Color.red);
			break;
		case 8:
			bamboos[0] = new Bamboo(30, 20, Color1);
			bamboos[1] = new Bamboo(100, 20, Color1);
			bamboos[2] = new Bamboo(30, 55, Color.blue);
			bamboos[3] = new Bamboo(100, 55, Color.blue);
			bamboos[4] = new RotatedBamboo(this, 36, 28, Color1, 3.14/3.5);	
			bamboos[5] = new RotatedBamboo(this, 83, 52, Color1, -3.14/3.5);
			bamboos[6] = new RotatedBamboo(this, 80, 25, Color.blue, 3.14/3.5);
			bamboos[7] = new RotatedBamboo(this, 39, 48, Color.blue, -3.14/3.5);
			break;
		case 9:
			bamboos[0] = new Bamboo(38, 34, Color.red);
			bamboos[1] = new Bamboo(92, 34, Color1);
			bamboos[2] = new Bamboo(38, 69, Color.red);
			bamboos[3] = new Bamboo(92, 69, Color1);
			bamboos[4] = new Bamboo(65, 69, Color.blue);
			bamboos[5] = new Bamboo(65, 34, Color.blue);
			bamboos[6] = new Bamboo(65, 2, Color.blue);
			bamboos[7] = new Bamboo(92, 2, Color1);
			bamboos[8] = new Bamboo(38, 2, Color.red);
			break;
		
		
		}
	}
	
	public String toString() {
		return "Bamboo " + rank; 
	}
	
	public void paintComponent(Graphics g) {
		if(getVis() == false) {
			return;
		}
		super.paintComponent(g);
		
		for (Bamboo c : bamboos)
			if (c != null)
				c.draw(g);
	}
	
	public class Bamboo {
		private int x;
		private int y;
		private Color color;
		
		public Bamboo (int x, int y, Color color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
		
		public void draw(Graphics g) {
			g.setColor(color);
			g.fillRect(x, y, 7, 29);					//vertical rectangle
			g.fillRoundRect(x-4, y-2,14,6,20, 20);		//horizontal rectangle
			g.fillRoundRect(x-4, y+23,14,6, 20, 20);
			g.setColor(Color.white);
			g.drawLine(x+4, y+2, x+4, y+26);			//draw line
			g.setColor(color);
			g.fillRoundRect(x-4,y+11, 14, 6, 20, 20);	//fill in this rectangle last so it covers part of line
		}
	}
	
	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Bamboo Tiles");

		frame.add(new BambooTile(2));
		frame.add(new BambooTile(3));
		frame.add(new BambooTile(4));
		frame.add(new BambooTile(5));
		frame.add(new BambooTile(6));
		frame.add(new BambooTile(7));
		frame.add(new BambooTile(8));
		frame.add(new BambooTile(9));

		frame.pack();
		frame.setVisible(true);
	}

}
