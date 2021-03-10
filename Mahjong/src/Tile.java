import java.awt.*;

import javax.swing.*;

public class Tile extends JPanel implements Cloneable {
	private static Dimension	SIZE;
	private static Polygon		SIDE;
	private static Polygon		SIDE2;
	private static Polygon		SIDE3;
	private static Polygon		SIDE4;
	private static Rectangle	FACE;
	private static GradientPaint	GRAD1;
	private static GradientPaint	GRAD2;
	private static int Width = 100;		//constants for the width, height, and edge of the tile
	private static int Height = 100;
	private static int Edge = 10;
	private boolean canPaint = true;	//this is false if the tile is to be removed from the screen
	private int row;
	private int col;
	private int layer;
	private boolean highlight = false;
	private int zOrder = 0;
	
	public Tile() {
		setPreferredSize(SIZE);
		setSize(SIZE);
		setOpaque(false);
	}
	public int getWid() {
		return Width;
	}
	public int getHeigt() {
		return Height;
	}
	public int getEdge() {
		return Edge;
	}
	public void setHighlight(boolean high) {
		highlight = high;
	}
	
	public void setZOrder()
	{
	    zOrder = getParent().getComponentZOrder(this);
	}

	public void resetZOrder()
	{
	    getParent().setComponentZOrder(this, zOrder);
	}

	public int getZOrder()
	{
		return zOrder;
	}
	
	public void paintComponent(Graphics g) {		//very similar to ThreeD example
		if(getVis() == false) {
			return;
		}
		super.paintComponent(g);
		
		g.drawPolygon(SIDE);
		g.drawPolygon(SIDE2);
		g.drawPolygon(SIDE3);
		g.drawPolygon(SIDE4);

		Graphics2D g2 = (Graphics2D)g;
		if(highlight == true) {
			g2.setPaint(Color.cyan);
		}
		else {
		g2.setPaint(GRAD1);
		}
		g2.fill(SIDE);
		g2.fill(SIDE2);
		g2.fill(FACE);
		g2.setPaint(Color.green);
		g2.fill(SIDE3);
		g2.fill(SIDE4);
		
		
		g2.setColor(Color.black);
		g2.draw(SIDE);
		g2.draw(SIDE2);
		g2.draw(SIDE3);
		g2.draw(SIDE4);
		
		g2.setColor(Color.black);
		g2.draw(FACE);
	}
	
	static
	{
		SIZE = new Dimension(121, 121);
		
		int[] x = { 10, 20, 20, 10 };
		int[] y = { 10, 0, 100, 110};
		SIDE = new Polygon(x, y, 4);
		
		int[] x1 = { 20, 120, 110, 10 };
		int[] y1 = { 100, 100, 110, 110};
		SIDE2 = new Polygon(x1,y1,4);
		
		int[] x2 = { 0, 10, 10, 0 };
		int[] y2 = { 20, 10, 110, 120};
		SIDE3 = new Polygon(x2,y2,4);
		
		int[] x3 = { 10, 110, 100, 0 };
		int[] y3 = { 110, 110, 120, 120};
		SIDE4 = new Polygon(x3,y3,4);
		
		FACE = new Rectangle(20, 0, 100, 100);
		Color Color1 = new Color(238,232,205);
		GRAD1 = new GradientPaint(20, 100, Color1, 120, 0, Color.white);
		GRAD2 = new GradientPaint(10, 110, Color.WHITE, 20, 0, Color.gray);
	}
	
	public boolean matches(Tile other) {
		if(other == null) {
			return false;
		}
		
		if(this == other) {
			return false;
		}
		
		if(this.getClass() == other.getClass()) {
			return true;
		}
		
		return false;
		
	}
	
	public void setVis(boolean vis) {
		canPaint = vis;
		
	}
	
	public boolean getVis() {
		return canPaint;
	}
	
	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tile");

		frame.add(new Tile());

		frame.pack();
		frame.setVisible(true);
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public Object clone() throws
    CloneNotSupportedException 
{ 
// Assign the shallow copy to new reference variable t 
return super.clone();
} 
}
