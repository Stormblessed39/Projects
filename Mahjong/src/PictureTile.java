import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class PictureTile extends Tile {
	private	static ImageIcon[]	image = new ImageIcon[10];		
	private String name;
	private String[]	files = { "Bamboo","Chrysanthemum","dragon_bg","Fall", "Orchid", "Plum", "Sparrow", "Spring", "Summer",  "Winter" };
	// files consists of all of the image files names in the image folder
	
	public PictureTile(String name) {
		this.name = name;
		setToolTipText(toString());
		for (int i = 0; i < files.length; i++)
		{
			URL	url = PictureTile.class.getResource("images/" + files[i] + ".png"); //get the file name and add images/ and .png to it
			image[i] = new ImageIcon(url);
		}
		
	}
	
	public String toString() {
		return name;
	}
	
	public void paintComponent(Graphics g) {
		if(getVis() == false) {
			return;
		}
		super.paintComponent(g);
		int i;
		for(i = 0; i < files.length; i++) {
			if(files[i].equals(name)) {		//find the image that we want and then break
				break;
			}
		}
		g.drawImage(image[i].getImage(), getWidth()/2-35, getHeight()/2-55, 90, 90, this);		//draw the image
		
	}
	
	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Picture Tiles");

		frame.add(new Bamboo1Tile());

		frame.add(new FlowerTile("Chrysanthemum"));
		frame.add(new FlowerTile("Orchid"));
		frame.add(new FlowerTile("Plum"));
		frame.add(new FlowerTile("Bamboo"));

		frame.add(new SeasonTile("Spring"));
		frame.add(new SeasonTile("Summer"));
		frame.add(new SeasonTile("Fall"));
		frame.add(new SeasonTile("Winter"));

		frame.pack();
		frame.setVisible(true);
	}
}
