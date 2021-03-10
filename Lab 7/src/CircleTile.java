import java.awt.*;


import javax.swing.*;


public class CircleTile extends RankTile {
	
	private Circle[] circles = new Circle[rank+1];
	
	public CircleTile(int rank) {		//initialize rank and circle locations/sizes
		super(rank);
		setToolTipText(toString());
		 Color Color1 = new Color(0,205,0);
		switch(rank) {
		case 1:
			circles[0] = new Circle(getWidth()/2-30, getHeight()/2-50, Color1, 80);
			circles[1] = new Pancake(this, getWidth()/2 + 1, getHeight()/2 - 19, Color.red, 20);
			break;
		case 2:
			circles[0] = new Circle(50, 10, Color1, 40); 
			circles[1] = new Circle(50, 53, Color.red, 40); 
			break;
		case 3:
			circles[0] = new Circle(25, 5, Color.blue, 30); 
			circles[1] = new Circle(55, 35, Color.red, 30); 
			circles[2] = new Circle(85, 65, Color1, 30); 
			break;
		case 4:
			circles[0] = new Circle(30, 15, Color.blue, 38); 
			circles[1] = new Circle(75, 15, Color1, 38); 
			circles[2] = new Circle(30, 55, Color1, 38); 
			circles[3] = new Circle(75, 55, Color.blue, 38); 
			break;
		case 5:
			circles[0] = new Circle(25, 5, Color.blue, 27); 
			circles[1] = new Circle(90, 5, Color1, 27); 
			circles[2] = new Circle(25, 70, Color1, 27); 
			circles[3] = new Circle(90, 70, Color.blue, 27); 
			circles[4] = new Circle(57, 40, Color.red, 27); 
			break;
		case 6:
			circles[0] = new Circle(35, 5, Color1, 29); 
			circles[1] = new Circle(75, 5, Color1, 29); 
			circles[2] = new Circle(35, 35, Color.red, 29); 
			circles[3] = new Circle(75, 35, Color.red, 29); 
			circles[4] = new Circle(75, 65, Color.red, 29); 
			circles[5] = new Circle(35, 65, Color.red, 29); 
			break;
		case 7:
			circles[0] = new Circle(30, 15, Color1, 17); 
			circles[1] = new Circle(60, 25, Color1, 17); 
			circles[2] = new Circle(90, 35, Color1, 17); 
			circles[3] = new Circle(40, 55, Color.red, 17); 
			circles[4] = new Circle(40, 75, Color.red, 17); 
			circles[5] = new Circle(80, 55, Color.red, 17); 
			circles[6] = new Circle(80, 75, Color.red, 17);
			break;
		case 8:
			circles[0] = new Circle(40, 5, Color.blue, 18); 
			circles[1] = new Circle(80, 5, Color.blue, 18); 
			circles[2] = new Circle(40, 30,Color.blue, 18); 
			circles[3] = new Circle(80, 30, Color.blue, 18); 
			circles[4] = new Circle(80, 55, Color.blue, 18); 
			circles[5] = new Circle(40, 55, Color.blue,18); 
			circles[6] = new Circle(80, 80, Color.blue, 18); 
			circles[7] = new Circle(40, 80, Color.blue, 18); 
			break;
		case 9:
			circles[0] = new Circle(25, 5, Color1, 28); 
			circles[1] = new Circle(90, 5, Color1, 28); 
			circles[2] = new Circle(25, 70, Color.blue, 28); 
			circles[3] = new Circle(90, 70, Color.blue, 28); 
			circles[4] = new Circle(57, 38, Color.red, 28); 
			circles[5] = new Circle(57, 5, Color1, 28);
			circles[6] = new Circle(25, 38, Color.red, 28);
			circles[7] = new Circle(90, 38, Color.red, 28);
			circles[8] = new Circle(57, 70, Color.blue, 28);
			break;
		
		}
		
	}
	
	
	
	public String toString() {	//simple to string
		return "Circle " + rank;
	}
	
	public void paintComponent(Graphics g)		//provided code that loops through circles array
	{
		if(getVis() == false) {
			return;
		}
		super.paintComponent(g);
		
		
		for (Circle c : circles)
			if (c != null)
				c.draw(g);
	}


	
	
	
public class Circle {

		private int x;
		private int y;
		private Color color;
		private int size;
		
	public Circle (int x, int y, Color color, int size) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.size = size;
	}

	public void draw(Graphics g) {		//use the x, y, color, and size to create the circle
			g.setColor(color);
			
			g.fillOval(x, y, size, size);
			
			if (rank == 1 && circles[1].getClass() != this.getClass()) { // only run this to make the first outer circle on the rank 1 tile
				g.setColor(Color.black);
				g.drawOval(x, y, size, size);
			}
			
			if(rank != 1 || circles[1].getClass() == this.getClass()) {	//if statement to make sure it only runs on normal circles
				g.setColor(Color.white);
				g.drawLine(x+5, y+5, x+size-5, y+size-5);
				g.drawLine(x+5, y+size-5, x+size-5, y+5);
			}
	}
		
}
	
	public static void main(String[] args)		//provided main
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Circle Tiles");

		frame.add(new CircleTile(1));
		frame.add(new CircleTile(2));
		frame.add(new CircleTile(3));
		frame.add(new CircleTile(4));
		frame.add(new CircleTile(5));
		frame.add(new CircleTile(6));
		frame.add(new CircleTile(7));
		frame.add(new CircleTile(8));
		frame.add(new CircleTile(9));

		frame.pack();
		frame.setVisible(true);
	}
}
