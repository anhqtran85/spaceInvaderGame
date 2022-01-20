package space.invaders;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class SpaceInvaders extends JFrame{
	public static int FRAME_WIDTH = 500;
	public static int FRAME_HEIGHT = 450;
	public static int INVADER_DISTANCE_X = 35;
	public static int INVADER_DISTANCE_Y = 25;
	public static int INIT_X = (FRAME_WIDTH - INVADER_DISTANCE_X*10)/2;
	public static int INIT_Y = 80;
	public static int INVADER_ROW = 5;
	public static int INVADER_COL = 10;
	
	private Panel p;
	
	
	public SpaceInvaders() {
		super("Space Invaders");
		setResizable(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null); //displayed center 	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		
		JPanel p1 =  new JPanel(new FlowLayout(FlowLayout.LEFT));		
		JMenu game = new JMenu("Game");
				
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem resume = new JMenuItem("Resume");
		JMenuItem quit = new JMenuItem("Quit");
		
		
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		
		menuBar.add(game);
		menuBar.add(help);
		
		
		help.add(about);
		game.add(newGame);
		game.add(pause);
		game.add(resume);
		game.add(quit);
		
		setJMenuBar(menuBar);
		
		
//		pack();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
			
		p = new Panel();
		add(p);
		
		
		newGame.addActionListener(e -> {
			if(e.getSource() == newGame) {
				p.initBoard();
				p.initGame();
			}
		});
		
		resume.addActionListener(e -> {
			if(e.getSource() == resume) {
				if(!p.getTimer().isRunning()) {
					p.getTimer().start();
				}
			}
		});
		
		pause.addActionListener(e -> {
			if(e.getSource() == pause) {
				if(p.getTimer().isRunning()) {
					p.getTimer().stop();
				}
			}		
 		});
		
		quit.addActionListener(e -> {
			if (e.getSource() == quit) {			
				int selected = JOptionPane.showConfirmDialog(null,"Dare to quit?","Confirm", JOptionPane.YES_NO_OPTION);
				setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				if (selected == 0) {
					System.exit(0);
				}
			}
		});
		
		about.addActionListener(e -> {
			if (e.getSource() == about) {			
				//int selected = JOptionPane.showConfirmDialog(null,"SpaceInvader by Anh Tran","About...", JOptionPane.OK_OPTION);
				JOptionPane.showConfirmDialog(null,"SpaceInvader by Anh Tran","About...",JOptionPane.PLAIN_MESSAGE);			
			}		
		});
	}
	
	public static void main (String[] args) {
		JFrame jf = new SpaceInvaders();
		jf.setVisible(true);

	}
	
}
