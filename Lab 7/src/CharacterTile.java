import java.util.HashMap;
import java.awt.*;
import javax.swing.*;

public class CharacterTile extends Tile {

	protected char symbol;
	 static HashMap <Character, Character> map = new HashMap<>();
	
	 public void paintComponent(Graphics g) {
		 if(getVis() == false) {
				return;
			}
		 super.paintComponent(g);
		 
		 String str = String.valueOf(map.get(symbol));		//get the string value of the symbol

		 Font	f = g.getFont().deriveFont(15F);	//set the font			
		 g.setFont(f);	
			
		 
		g.setColor(Color.red);		//draw the red numbers on the top right
		g.drawString(String.valueOf(symbol), getWidth()-25,getHeight()-(getHeight()-20));

		f = g.getFont().deriveFont(40F);			//set the font			
		g.setFont(f);	
			
		FontMetrics	fm = g.getFontMetrics();		//get the string width
		int		wid = fm.stringWidth(str);
		
		 if(symbol > 48 && symbol < 58) {		//draw the number characters with a certain size
			 
			 g.setColor(Color.black);
			 g.drawString(str, (getWidth() - wid+17) / 2, getHeight()/2-20);
			 g.setColor(Color.red);		//draw the wan symbol
			 g.drawString(String.valueOf('\u842C'), getWidth()/2-10, getHeight()/2+30);
			 
		 }
		 
		 else {							//draw the special characters with another size/color
			 g.setColor(Color.black);
			 f = g.getFont().deriveFont(85F);			
				g.setFont(f);	
			 if(symbol == 'C') {
				 g.setColor(Color.red);
			 }
			 if(symbol == 'F') {
				 Color Color1 = new Color(0,205,0);
				 g.setColor(Color1);
			 }
			 g.drawString(str, getWidth()/2-30, getHeight()/2+30);
		 }
		 
	 }
	 
	 static {
		 map.put('1', '\u4E00');
		 map.put('2', '\u4E8C');
		 map.put('3', '\u4E09');
		 map.put('4', '\u56DB');
		 map.put('5', '\u4E94');
		 map.put('6', '\u516D');
		 map.put('7', '\u4E03');
		 map.put('8', '\u516B');
		 map.put('9', '\u4E5D');
		 map.put('N', '\u5317');
		 map.put('E', '\u6771');
		 map.put('W', '\u897F');
		 map.put('S', '\u5357');
		 map.put('C', '\u4E2D');
		 map.put('F', '\u767C');
	 }
	
	 public String toChinese() {
		 String chinese = Character.toString(map.get(symbol));
		 
		 return chinese;
	 }
	 
	public CharacterTile(char symbol) {
		
		this.symbol = symbol;
		setToolTipText(toString());
	}
	
	public boolean matches(Tile other) {
		if(super.matches(other)) {
			CharacterTile otherObject = (CharacterTile)other;
			if(otherObject.symbol == this.symbol) {
				return true;
			}
		}
		
		return false;
	}
	
	public String toString() {
		if(symbol == 'N') {
			return "North Wind";
		}
		if(symbol == 'E') {
			return "East Wind";
		}
		if(symbol == 'W') {
			return "West Wind";
		}
		if(symbol == 'S') {
			return "South Wind";
		}
		if(symbol == 'C') {
			return "Red Dragon";
		}
		if(symbol == 'F') {
			return "Green Dragon";
		}
		
		return "Character " + symbol;
	}
	                         
	public static void main(String[] args)
	{
		JFrame		frame = new JFrame();
		JPanel		tiles = new JPanel();
		JScrollPane	scroller = new JScrollPane(tiles);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Character Tiles");
		frame.add(scroller);

		// Try something like this if your tiles don't fit on the screen.
		// Replace "tile width" and "tile height" with your values.
		//scroller.setPreferredSize(new Dimension(8 * tile width, 40 + tile height));

		tiles.add(new CharacterTile('1'));
		tiles.add(new CharacterTile('2'));
		tiles.add(new CharacterTile('3'));
		tiles.add(new CharacterTile('4'));
		tiles.add(new CharacterTile('5'));
		tiles.add(new CharacterTile('6'));
		tiles.add(new CharacterTile('7'));
		tiles.add(new CharacterTile('8'));
		tiles.add(new CharacterTile('9'));
		tiles.add(new CharacterTile('N'));
		tiles.add(new CharacterTile('E'));
		tiles.add(new CharacterTile('W'));
		tiles.add(new CharacterTile('S'));
		tiles.add(new CharacterTile('C'));
		tiles.add(new CharacterTile('F'));

		frame.pack();
		frame.setVisible(true);
	}
}
