import java.awt.*;


import javax.swing.*;

public class MahJongModel {
	private MahJongBoard board;
	private Tile[][][]	tiles = new Tile[16][16][5];
	
	
	public MahJongModel(MahJongBoard board)
	{
		this.board = board;
		TileDeck	deck = new TileDeck();
		int columns[] = {1, 3, 2, 1, 0, 2, 3, 1};	//column to start at
		int rlength[] = {13, 11, 12, 15, 13, 12, 11, 13}; //how long to go
		deck.shuffle();
		
		Tile top = deck.deal();
		positionTile(top, 0, 0, 0);	//top tile
		for (int layer = 0; layer < 4; layer++)	//add layers 1-3 to the deck
		{
			for(int rows = 0; rows < layer*2; rows++) {
				for(int cols = 0; cols < layer*2; cols++) {
					Tile	tile = deck.deal();
					if (tile == null)
					{
						JOptionPane.showMessageDialog(null, "Empty Deck",
								"Deal Error", JOptionPane.ERROR_MESSAGE);
						System.exit(1);
					}
					tile.setRow(rows);
					tile.setCol(cols);
					tile.setLayer(layer);
					positionTile(tile, rows, cols, layer);

				}
				
			
			}
		}
			
		for(int i = 0; i < 8; i++) {	//add layer 4 to the deck
			for(int cols = columns[i]; cols < rlength[i]; cols++) {
				Tile	tile = deck.deal();
				if (tile == null)
				{
					JOptionPane.showMessageDialog(null, "Empty Deck",
							"Deal Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
				tile.setRow(i);
				tile.setCol(cols);
				tile.setLayer(4);
				positionTile(tile, i, cols, 4);

			}
			
		}
		
		
		
	}
	
	public MahJongModel(MahJongBoard board, long gameNumber)	//version for specific game number
	{
		this.board = board;
		TileDeck	deck = new TileDeck();
		int columns[] = {1, 3, 2, 1, 0, 2, 3, 1};	//column to start at
		int rlength[] = {13, 11, 12, 15, 13, 12, 11, 13}; //how long to go
		deck.shuffle(gameNumber);
		
		Tile top = deck.deal();
		positionTile(top, 0, 0, 0);	//top tile
		for (int layer = 0; layer < 4; layer++)	//add layers 1-3 to the deck
		{
			for(int rows = 0; rows < layer*2; rows++) {
				for(int cols = 0; cols < layer*2; cols++) {
					Tile	tile = deck.deal();
					if (tile == null)
					{
						JOptionPane.showMessageDialog(null, "Empty Deck",
								"Deal Error", JOptionPane.ERROR_MESSAGE);
						System.exit(1);
					}
					tile.setRow(rows);
					tile.setCol(cols);
					tile.setLayer(layer);
					positionTile(tile, rows, cols, layer);

				}
				
			
			}
		}
			
		for(int i = 0; i < 8; i++) {	//add layer 4 to the deck
			for(int cols = columns[i]; cols < rlength[i]; cols++) {
				Tile	tile = deck.deal();
				if (tile == null)
				{
					JOptionPane.showMessageDialog(null, "Empty Deck",
							"Deal Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
				tile.setRow(i);
				tile.setCol(cols);
				tile.setLayer(4);
				positionTile(tile, i, cols, 4);

			}
			
		}
		
		
		
	}
	
	public void positionTile(Tile t, int row, int col, int layer) {
		tiles[row][col][layer] = t;
	}
	
	public Tile getTile(int row, int col, int layer) {
		if(row >= 0 && col >= 0 && layer>= 0)
		return tiles[row][col][layer];
		
		return null;
	}
	
	public boolean isTileOpen(Tile t) {
		if((t.getCol() == 0 && t.getLayer() == 4) || t.getCol() == 14 || t.getLayer() == 0) {
			return true;	//special tiles are automatically open
		}
		
		Tile right = getTile(t.getRow(), t.getCol()+1, t.getLayer());
		Tile left = getTile(t.getRow(), t.getCol()-1, t.getLayer());
		Tile above = null;
		
		switch(t.getLayer()) {
		
		case 0:
			above = null;
			break;
		case 1:
			above = getTile(0,0,0);
			break;
		case 2:
			above = getTile(t.getRow()-1, t.getCol()-1, t.getLayer()-1);
			break;
		case 3:
			above = getTile(t.getRow()-1, t.getCol()-1, t.getLayer()-1);
			break;
		case 4:
			above = getTile(t.getRow()-1, t.getCol()-4, t.getLayer()-1);
			break;
		
		}
		
		if(t.getRow() == 3 && t.getCol() == 1 && t.getLayer() == 4) {	//these tiles have special left
			left = getTile(4, 0, 4);
		}
		
		if((t.getRow() == 3 || t.getRow() == 4) && t.getCol() == 12) {	//these tiles have special right
			right = getTile(3, 13, 4);
		}
		
		if((above == null || above.getVis() == false) && ((right == null || right.getVis() == false)
				|| (left == null || left.getVis() == false))) {	//if top is null, and left or right is null, reurn true
			return true;
		}
		

		return false;
	}
	
	
		
}
