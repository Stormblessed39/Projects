import java.awt.*;

import	java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class MahJong extends JFrame {
	private MahJongBoard board;
	private Help rulesHelp = new Help("help/rules.html", "Rules");
	private Help OperationHelp = new Help("help/operation.html", "Operation");
	private boolean isTournament = false;
	public static int time = 0;
	private JMenuBar	menuBar = new JMenuBar();
	
	
	public MahJong()
	{
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter()
				{ public void windowClosing(WindowEvent e)
					{ exit(); }
				});
		setJMenuBar(menuBar);
		setTitle("MahJong");
		setSize(1600,1000);

		board = new MahJongBoard(this);
		add(board);
		Dimension	screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,				// centers frame
				(screenSize.height - getHeight()) / 2);
		
		makeMenu();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MahJong();
	}
	
	class Helper extends TimerTask 	//helper task for timer
	{  
	    public void run() 
	    { 
	    	setTitle("MahJong Score: " + board.getScore() + " Time: " + ++time);
	    } 
	} 
	
	private void makeMenu()		//very bulky but it creates every menu item
	{	
		//setting up the menus
		JMenu	GameMenu = new JMenu("Game");
		GameMenu.setMnemonic('G');	
		JMenu	SoundMenu = new JMenu("Sound");
		SoundMenu.setMnemonic('S');	
		JMenu	MoveM = new JMenu("Move");
		MoveM.setMnemonic('M');	
		JMenu	HelpMenu = new JMenu("Help");
		HelpMenu.setMnemonic('H');	
		
		//adding the Items to the game menu
		menuBar.add(GameMenu);
		JMenuItem	PlayItem = new JMenuItem("Play", 'P');
		PlayItem.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
		PlayItem.setToolTipText("Starts a new game");
		
		JMenuItem	RestartItem = new JMenuItem("Restart", 'R');
		RestartItem.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		RestartItem.setToolTipText("Restarts the current game");
		
		JMenuItem	NumberedItem = new JMenuItem("Numbered", 'N');
		NumberedItem.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		NumberedItem.setToolTipText("Starts a new game based off a game seed");
		
		JMenuItem	TournamentItem = new JMenuItem("Tournament", 'T');
		TournamentItem.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		TournamentItem.setToolTipText("Starts a new game in Tournament mode");
		
		JMenuItem	ExitItem = new JMenuItem("Exit", 'E');
		ExitItem.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
		ExitItem.setToolTipText("Exits the game");
		
		JMenuItem	History = new JMenuItem("Toggle History", 'H');
		History.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));
		History.setToolTipText("Shows removed tiles");
		
		GameMenu.add(PlayItem);
		PlayItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ Play(); }
		});

		GameMenu.add(RestartItem);
		RestartItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ Restart(); }
		});
		GameMenu.add(NumberedItem);
		NumberedItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ Numbered(); }
		});
		GameMenu.add(History);
		History.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ board.Hist(); }
		});
		GameMenu.add(TournamentItem);
		TournamentItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ Tournament(); }
		});
		GameMenu.add(ExitItem);
		ExitItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ exit(); }
		});
		
		
		//adding the Items to the sound menu
		menuBar.add(SoundMenu);
		JMenuItem	OnItem = new JMenuItem("On", 'O');
		OnItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		OnItem.setToolTipText("Sets the game sounds to on");
		
		JMenuItem	OffItem = new JMenuItem("Off", 'O');
		OffItem.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
		OffItem.setToolTipText("Sets the game sounds to off");
		
		SoundMenu.add(OnItem);
		OnItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ board.setSound(true); }
		});
		SoundMenu.add(OffItem);
		OffItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ board.setSound(false); }
		});
		
		//adding the Items to the move menu
		menuBar.add(MoveM);
		JMenuItem	UndoItem = new JMenuItem("Undo", 'U');
		UndoItem.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		UndoItem.setToolTipText("Undo the last move");
		
		
		JMenuItem	RedoItem = new JMenuItem("Redo", 'R');
		RedoItem.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
		RedoItem.setToolTipText("Redo the last move");
		
		MoveM.add(UndoItem);
		UndoItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ Undo(); }
		});
		MoveM.add(RedoItem);
		RedoItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ Redo(); }
		});
		
		//adding the Items to the help menu
		menuBar.add(HelpMenu);
		JMenuItem	OperationItem = new JMenuItem("Operation", 'O');
		OperationItem.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		OperationItem.setToolTipText("Displays how to use the game");
		
		JMenuItem	RulesItem = new JMenuItem("Game Rules", 'G');
		RulesItem.setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
		RulesItem.setToolTipText("Displays the rules of the game");
		
		HelpMenu.add(OperationItem);
		OperationItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ Operation(); }
		});
		
		HelpMenu.add(RulesItem);
		RulesItem.addActionListener(new ActionListener()
		{ public void actionPerformed(ActionEvent e)
			{ Rules(); }
		});
		
		if(isTournament == true) {	///sets some buttons to not enabled if tournament mode is active
			UndoItem.setEnabled(false);
			RedoItem.setEnabled(false);
			RestartItem.setEnabled(false);
			NumberedItem.setEnabled(false);
			History.setEnabled(false);
		}
		else {
			UndoItem.setEnabled(true);
			RedoItem.setEnabled(true);
			RestartItem.setEnabled(true);
			NumberedItem.setEnabled(true);
			History.setEnabled(true);
		}
		
		
	}
	
	public void Play() {	//starts a fresh new game, removing anything that might have been from tournament
		if (JOptionPane.showConfirmDialog(this,
				"Are you sure you want to start a new game? (all progress will be lost)", "Play",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
			isTournament = false;
			setTitle("MahJong");
			remove(board);
			board = new MahJongBoard(this);
			add(board);
			setVisible(true);
		    menuBar = new JMenuBar();
		    makeMenu();
		    setJMenuBar(menuBar);
			board.setScore(0);
			 revalidate();
			
		}
	}
	
	public void Restart() {	//undos until there is nothing more to undo
		if (JOptionPane.showConfirmDialog(this,
				"Are you sure you want to restart? (all progress will be lost)", "Restart",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
			while(!board.undoStack.isEmpty()) {
				Undo();
			}
			board.setScore(0);
		}
		
	}
	
	public void Numbered() {
		String[] options = new String[] {"New Numbered game", "Play previous numbered game","Cancel"};
	    int response = JOptionPane.showOptionDialog(null, "Message", "Title",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
	        null, options, options[0]);
	    
	    	if(response == 0) {	//a new game with a random game number
	    		long gameNumber = System.currentTimeMillis() % 100000;
	    		setTitle("MahJong " + gameNumber);
				remove(board);
				board = new MahJongBoard(this, gameNumber);
				add(board);
				setVisible(true);
	    	}
	    
	    	if(response == 1) {	//a new game based off of a inputed game number
				long gameNumber = Long.parseLong(JOptionPane.showInputDialog("Input a game number"));
				remove(board);
				board = new MahJongBoard(this, gameNumber);
				add(board);
				setVisible(true);
	    	}
	    	board.setScore(0);
	    	
		
		
	}
	
	public void Tournament(){	//starts a new game but with a timer and score visible
		if (JOptionPane.showConfirmDialog(this,
				"Are you sure you want to start a Tournament? (all progress will be lost)", "Tournament",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
			setTitle("MahJong Score: " + board.getScore());
			isTournament = true;
			remove(board);
			board = new MahJongBoard(this);
			add(board);
			setVisible(true);
			Timer timer = new Timer(); 
			TimerTask task = new Helper(); 
		    timer.schedule(task, 0, 1000); 
		    menuBar = new JMenuBar();
		    makeMenu();
		    setJMenuBar(menuBar);
		    board.setScore(0);
		    revalidate();
			
			
		}
	}
	
	public void exit()	//exits the game
	{
		if (JOptionPane.showConfirmDialog(this,
					"Are you sure want to end this program?", "End Program",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
			System.exit(0);
	}
	
	public void Undo() {	//undos the last move
		if(!board.undoStack.isEmpty()) {
			Tile a = (Tile)board.undoStack.pop();
			Tile b = (Tile)board.undoStack.pop();
			
			board.redoStack.push(b);	//push onto redo stack so that they can redo
			board.redoStack.push(a);
			Tile temp2 = (Tile)board.discardStack.pop();	//these variables are for history purposes
			Tile temp1 = (Tile)board.discardStack.pop();	//so they can be readded to the history or taken off if need be
			board.discardReStack.push(temp1);
			board.discardReStack.push(temp2);
			
		
			board.discard[0].remove(temp2);
			board.discard[1].remove(temp1);
			board.repaint();
			
			a.setVis(true);
			a.setHighlight(false);
			b.setVis(true);
			b.setHighlight(false);
			
			board.add(a, a.getZOrder());	//add the tiles back in the right order
			board.add(b, b.getZOrder());
			
			
			board.subtractScore();
			board.revalidate();
			board.repaint();
		}
	}
	
	public void Redo() {	//redos the undo
		if(!board.redoStack.isEmpty()) {
			Tile b = (Tile)board.redoStack.pop();
			Tile a = (Tile)board.redoStack.pop();
			
			
			board.undoStack.push(b);	//push back onto undo stack so that you can undo again if you want
			board.undoStack.push(a);
			
			
			a.setVis(false);
			b.setVis(false);
			
			
			b.setZOrder();		//same logic as when you take the tile off the board
			board.remove(b);
			a.setZOrder();
			board.remove(a);
			
			
			Tile temp2 = (Tile)board.discardReStack.pop();	//variables to readd the tile to the history again
			Tile temp1 = (Tile)board.discardReStack.pop();
	
			board.demo.addToUndo(temp2,temp1);
			board.demo.repaint();
			board.discardStack.push(temp1);
			board.discardStack.push(temp2);
			
			board.addScore();
			board.revalidate();
			board.repaint();
			
		}
	}
	
	public void Rules() {	//display the rules
		rulesHelp.display();
	}
	
	public void Operation() {	//display the operation
		OperationHelp.display();
	}
	
	
}
