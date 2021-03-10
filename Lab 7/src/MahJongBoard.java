import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Stack;

import javax.swing.*;

public class MahJongBoard extends JPanel implements MouseListener, Cloneable {
	private MahJong game;
	private MahJongModel model;
	private	static ImageIcon image;
	private int x;
	private int y;
	private PlayClip clip = new PlayClip("audio/stone-scraping.wav", true);
	private Stack<Tile> stack = new Stack<Tile>();
	public ScrollDemo	demo;
	private Fireworks reward;
	private boolean sound = true;
	private int score;
	private boolean won = false;
	public 	Stack<Tile>	undoStack = new Stack<Tile>();
	public	Stack<Tile>	redoStack = new Stack<Tile>();	
	public	JPanel[]	discard = new JPanel[2];
	public Stack<Tile> discardStack = new Stack<Tile>();
	public Stack<Tile> discardReStack = new Stack<Tile>();
	public Tile temp1 = new Tile();
	public Tile temp2 = new Tile();
	
	public MahJongBoard(MahJong game)
	{
		this.game = game;
		
		demo = new ScrollDemo();
		demo.setSize(350, 300);
		model = new MahJongModel(this);
		setLayout(null);
		add(demo);
		demo.setVisible(false);
		
		int columns[] = {1, 3, 2, 1, 0, 2, 3, 1};	//column to start at
		int rlength[] = {13, 11, 12, 15, 13, 12, 11, 13};	//how far to go
		int xOffset[] = {655, 690, 560, 430};	//layer x offset
		int yOffset[] = {200, 270, 180, 90};	//layer y offset
		
		Tile top = model.getTile(0, 0, 0);	//adding the top tile
		top.addMouseListener(this);
		x = top.getWid()  + top.getEdge() + xOffset[0];
		y = top.getHeigt() + top.getEdge() + yOffset[0];
		top.setLocation(x,y);
		add(top);
		
		for (int layer = 0; layer < 4; layer++)		//adding layers 1-3
		{
			for(int rows = layer*2-1; rows >= 0; rows--) {
				for(int cols = 0; cols < layer*2; cols++) {
					Tile	tile = model.getTile(rows, cols, layer);
					tile.addMouseListener(this);
					x = (cols * tile.getWid()) + (layer * tile.getEdge()) + xOffset[layer];
					y = (rows * tile.getHeigt()) + (layer * tile.getEdge()) + yOffset[layer];
					tile.setLocation(x, y);
					add(tile);

				}
				
			}
		}
		
		for(int i = 7; i >= 0; i--) {	//adding layer 4
			for(int cols = columns[i]; cols < rlength[i]; cols++) {
				Tile	tile = model.getTile(i, cols, 4);
				tile.addMouseListener(this);
				x = (cols * tile.getWid()) + (4 * tile.getEdge());
				y = (i * tile.getHeigt()) + (4 * tile.getEdge());
				if(cols == 0) {
					x = (cols * tile.getWid()) + (4 * tile.getEdge());
					y = (i * tile.getHeigt());
				}
				if(cols == 13 || cols == 14) {
					x = (cols * tile.getWid()) + (4 * tile.getEdge());
					y = (i * tile.getHeigt()) + (9*tile.getEdge());
				}
				tile.setLocation(x, y);
				add(tile);

			}
			
		}
	
		URL	url = PictureTile.class.getResource("images/dragon_bg.png");
		image = new ImageIcon(url);
	}
	
	public MahJongBoard(MahJong game, long gameNumber)	//version for specific gamenumber
	{
		this.game = game;
		demo = new ScrollDemo();
		demo.setSize(350, 300);
		model = new MahJongModel(this, gameNumber);
		setLayout(null);
		add(demo);
		demo.setVisible(false);
		
		
		int columns[] = {1, 3, 2, 1, 0, 2, 3, 1};	//column to start at
		int rlength[] = {13, 11, 12, 15, 13, 12, 11, 13};	//how far to go
		int xOffset[] = {655, 690, 560, 430};	//layer x offset
		int yOffset[] = {200, 270, 180, 90};	//layer y offset
		
		Tile top = model.getTile(0, 0, 0);	//adding the top tile
		top.addMouseListener(this);
		x = top.getWid()  + top.getEdge() + xOffset[0];
		y = top.getHeigt() + top.getEdge() + yOffset[0];
		top.setLocation(x,y);
		add(top);
		
		for (int layer = 0; layer < 4; layer++)		//adding layers 1-3
		{
			for(int rows = layer*2-1; rows >= 0; rows--) {
				for(int cols = 0; cols < layer*2; cols++) {
					Tile	tile = model.getTile(rows, cols, layer);
					tile.addMouseListener(this);
					x = (cols * tile.getWid()) + (layer * tile.getEdge()) + xOffset[layer];
					y = (rows * tile.getHeigt()) + (layer * tile.getEdge()) + yOffset[layer];
					tile.setLocation(x, y);
					add(tile);

				}
				
			}
		}
		
		for(int i = 7; i >= 0; i--) {	//adding layer 4
			for(int cols = columns[i]; cols < rlength[i]; cols++) {
				Tile	tile = model.getTile(i, cols, 4);
				tile.addMouseListener(this);
				x = (cols * tile.getWid()) + (4 * tile.getEdge());
				y = (i * tile.getHeigt()) + (4 * tile.getEdge());
				if(cols == 0) {
					x = (cols * tile.getWid()) + (4 * tile.getEdge());
					y = (i * tile.getHeigt());
				}
				if(cols == 13 || cols == 14) {
					x = (cols * tile.getWid()) + (4 * tile.getEdge());
					y = (i * tile.getHeigt()) + (9*tile.getEdge());
				}
				tile.setLocation(x, y);
				add(tile);

			}
			
		}
	
		URL	url = PictureTile.class.getResource("images/dragon_bg.png");
		image = new ImageIcon(url);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.orange);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	
	public void mousePressed(MouseEvent e) {	//mouse event to remove a tile
		
		Tile t = (Tile)e.getSource();
		
		if(model.isTileOpen(t)) {	//if tile is open repaint with highlight
		stack.push(t);
		t.setHighlight(true);
		repaint();
		if(stack.size() == 2) {		//if the second tile you click is also open
			Tile a = (Tile)stack.get(0);
			Tile b = (Tile)stack.get(1);
			stack.pop();
			stack.pop();
			if(a.matches(b)) {	//compare the tiles
			if(sound == true) {
				clip.play();
			}
				addScore();
			
				repaint();
				undoStack.push(b);	//push onto undo stack in case player wants to undo move
				undoStack.push(a);
				try {
					 temp1 = (Tile)a.clone();	//copy the tiles into other tiles so that you can display them in the history
					 temp2 = (Tile)b.clone();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				temp1.setHighlight(false);
				temp2.setHighlight(false);
				a.setVis(false);
				b.setVis(false);
				repaint();
				
				redoStack.clear();	//clear the redostack if they made a move
				b.setZOrder();
				remove(b);
				a.setZOrder();
				remove(a);
				temp1.removeMouseListener(this);	//make sure the player cant click on tiles in the history
				temp2.removeMouseListener(this);
				demo.addToUndo(temp1,temp2);	//add the copies to the hsitory
				discardStack.push(temp2);
				discardStack.push(temp1);
				
				startReward();	//check to see if they have won
			}
			else {
				a.setHighlight(false);	//if the tiles do not match make sure they are both not highlighted anymore
				b.setHighlight(false);
			}
			}
		}
		
	}
	
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public class ScrollDemo extends JScrollPane	//edited scrolldemo example
	{
	
		private		int		count = 0;


		public ScrollDemo()
		{
			Tile t = new Tile();
			setPreferredSize(new Dimension(0, 2 * t.getHeigt() + 33));
			setBorder(BorderFactory.createRaisedBevelBorder());
		

			discard[0] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			discard[1] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			discard[0].setPreferredSize(new Dimension(0, t.getHeigt()));
			discard[1].setPreferredSize(new Dimension(0, t.getHeigt()));

			setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

			JPanel	panel = new JPanel(new BorderLayout());
			setViewportView(panel);

			panel.add(discard[0], BorderLayout.NORTH);
			panel.add(discard[1], BorderLayout.SOUTH);

			discard[0].setBackground(new Color(254, 205, 33));
			discard[1].setBackground(new Color(254, 205, 33));
			panel.setBackground(new Color(254, 205, 33));
		}
		
		public void addToUndo(Tile t1, Tile t2)
		{
			

			Dimension	size = new Dimension(++count * t1.getWid(), t1.getHeigt() + 27);
			discard[0].setPreferredSize(size);
			discard[1].setPreferredSize(size);


			// This version puts the most recently added tiles on the left - it doesn't
			// need to scroll - the most recently added tiles are always visible.

			discard[0].add(t1, 0);
			discard[1].add(t2, 0);

			revalidate();
			repaint();
		}
	}
	
	private void startReward()
	{
		if (getScore() < 144)	//if they are not at 144 points yet just return
			return;

		won = true;	//else start the reward
		reward = new Fireworks(this);
		reward.setSound(sound);
		reward.fire();
	}
	
	//helper functions
	public boolean getWon() {		
		return won;
	}
	public void setWon(boolean won) {
		this.won = won;
	}
	
	public Fireworks getReward() {
		return reward;
	}
	
	public void setSound(boolean sound) {
		this.sound = sound;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore() {
		score = score + 2;
	}
	
	public void subtractScore() {
		score = score - 2;
	}
	
	public Object clone() throws
    CloneNotSupportedException 
	{ 
	return super.clone();
	} 
	
	public void Hist() {
		if(demo.isVisible())
		demo.setVisible(false);
		else {
			demo.setVisible(true);
		}
	}
	

	
}
