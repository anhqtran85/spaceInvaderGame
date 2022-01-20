package space.invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import space.invaders.draw.Base;
import space.invaders.draw.Drawable.Direction;
import space.invaders.draw.Invader;
import space.invaders.draw.InvaderBottom;
import space.invaders.draw.InvaderMiddle;
import space.invaders.draw.InvaderTop;
import space.invaders.draw.Missile;
import space.invaders.draw.Mystery;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	private Timer timer;
	
	private Invader[][] invaders;
	private Base base;
	private List<Missile> invaderMissiles;
	private Missile baseMissile;
	private Mystery mystery;
		
	private int score;
	private int vector;
	private int mysteryVector;
	
	private int invaderPulseLimit;
	private int invaderMissilePulseLimit;
	private int mysteryPulseLimist;
	
	private boolean invaderShiftDown = false;
	
	public boolean gameOver = true;	
	
	
	public Panel() {
		initBoard();		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT	:
					if(base.getX() > 0 ) {
						base.move(Direction.LEFT, 5); 
					}
					break;
				case KeyEvent.VK_RIGHT	:
					if(base.getX()+base.getWidth() < getSize().width) {
						base.move(Direction.RIGHT, 5); 
					}
					break;
				case KeyEvent.VK_SPACE 	:
					//play shooting sound
					if(baseMissile == null) {
						base.playShootingSound();
						baseMissile = new Missile(base.getX() + base.getWidth()/2, base.getY());
					}
					break;
				}
			}
		});
	}
	
	public void initBoard() {
		score = 0;
		invaderPulseLimit = 40;
		invaderMissilePulseLimit = 2;
		mysteryPulseLimist = 2;
		vector = 1;
		
		setBackground(Color.BLACK);	
		setFocusable(true);
		
		invaderMissiles = new ArrayList<>();
		invaders = new Invader[5][10];
		mystery = null;
				
		// add top invaders
		for (int j= 0; j < 10; j++) {
			Invader topInvader = new InvaderTop(SpaceInvaders.INIT_X + j*SpaceInvaders.INVADER_DISTANCE_X, 
					SpaceInvaders.INIT_Y);
			invaders[0][j] = topInvader;
		}
		// add middle invaders
		for(int i = 1; i < 3; i++) {
			for(int j = 0; j < 10; j++) {
				Invader middleInvader = new InvaderMiddle(SpaceInvaders.INIT_X + j*SpaceInvaders.INVADER_DISTANCE_X, 
						SpaceInvaders.INIT_Y + SpaceInvaders.INVADER_DISTANCE_Y*i);
				invaders[i][j] = middleInvader;
			}
		}
		// add bottom invaders
		for(int i = 3; i < 5; i++) {
			for(int j = 0; j < 10; j++) {
				Invader bottomInvader = new InvaderBottom(SpaceInvaders.INIT_X + j*SpaceInvaders.INVADER_DISTANCE_X, 
						SpaceInvaders.INIT_Y + SpaceInvaders.INVADER_DISTANCE_Y*i);
				invaders[i][j] = bottomInvader;
			}
		}
		
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				update();
				
			}		
		});
	}
	
	public void initGame() {
		
		base = new Base(0, 0);
		base.setX((getSize().width - base.getWidth())/2);
		base.setY(getSize().height - base.getHeight());
		gameOver = false;
		timer.start();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		FontMetrics fm;
		// draw score
		String message = "Score: " + score;
		Font scoreFont = new Font("Arial", Font.BOLD, 10);
		g.setFont(scoreFont);
		g.setColor(Color.GREEN);
		fm = getFontMetrics(scoreFont);
		g2.drawString(message, getSize().width - fm.stringWidth(message) - 10, 15);
		
				
		// draw invaders
		if(invaders != null) {
			for(int row = 0; row < 5; row++) {
				for(int col = 0; col < 10; col++) {
					Invader invader = invaders[row][col];
					if(invader.isVisible()) {
						invader.draw(g2);
					}
				}
			};
		}
			
		// draw base
		if(base != null) {
			base.draw(g2);
		}
		
		// draw base missiles
		if(baseMissile != null) {
			baseMissile.draw(g2);
		}
		
		// draw invader missiles
		if(invaderMissiles != null) {
			for(Missile missile: invaderMissiles) {
				if(missile.isVisible()) {
					missile.draw(g2);
				}			
			}
		}
				
		// draw mystery ship
		if(mystery != null && mystery.isVisible()) {
			mystery.draw(g2);
		}
		
		if(gameOver) {
			String gameoverMessage = "Game Over";
			Font gameOverFont = new Font("Arial", Font.BOLD, 40);
			g.setFont(gameOverFont);
			fm = g.getFontMetrics();
			g2.setColor(Color.GREEN);
			g2.drawString(gameoverMessage, (getSize().width-fm.stringWidth(gameoverMessage))/2, 
					getSize().height/2+fm.getHeight());
		}
		
	}
	
	private void update() {
		Random rand = new Random();
		if(gameOver) {
			timer.stop();
		}
		
		// invader missile
		for(Missile missile: invaderMissiles) {
			if(missile.isVisible()) {
				if(missile.getPulseCounter() > invaderMissilePulseLimit) {
					missile.move(Direction.DOWN, 5);
					missile.setPulseCounter(0);
				}
				missile.setPulseCounter(missile.getPulseCounter()+1);
				
				// check if base got hit
				if(base.gotHit(missile)) {
					base.setHit(true);
					gameOver = true;
				}
			}			
		}
		
		// base missile
		if(baseMissile != null) {
			if(baseMissile.isVisible()) {
				baseMissile.move(Direction.UP, 5);
			} else {
				baseMissile = null;
			}
		}
			
		// invader
		if(invaders[0][0].getX() <= 0) {
			vector = 1;
			invaderPulseLimit = invaderPulseLimit*80/100;
			invaderShiftDown = true;
		} else if(invaders[4][9].getX() + invaders[4][9].getWidth() >= getSize().getWidth()) {
			vector = -1;
			invaderPulseLimit = invaderPulseLimit*80/100;
			invaderShiftDown = true;
		}
		
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 10; col++) {
				Invader invader = invaders[row][col];		
				// check if invader land
				if(invader.getY()+invader.getHeight() >= getSize().getHeight() && !invader.isHit()) {
					gameOver = true;
					timer.stop();
				}
				
				// invader status
				if(invader.gotHit(baseMissile)) {
					invader.setHit(true);
					score = score + invader.getPoints();
					baseMissile = null;
				}
							
				if(invader.getPulseCounter() > invaderPulseLimit) {
					if(invader.isHit()) {
						invader.setVisible(false);
					}
					
					// invader missiles				
 					if(invaderCanFireMissile(row,col)) {
						if(rand.nextInt(100) < 80) {
							Missile missile = new Missile(invader.getX() + invader.getWidth()/2, invader.getY()+invader.getHeight());
							invaderMissiles.add(missile);
						}
					}					
					
					// invader movement
					if(vector == 1) {
						invader.move(Direction.RIGHT, 5);			
					} else {
						invader.move(Direction.LEFT, 5);
					}
					
					if(invaderShiftDown) {
						invader.move(Direction.DOWN, 12);
					}
					
					invader.setAlternate(!invader.isAlternate());	// use this to alternate between imageA and imageB									
					invader.setPulseCounter(0);				
				} else {
					invader.setPulseCounter(invader.getPulseCounter()+1);
				}		
			}
		}
		
		// mystery ship
		if(mystery == null) { // initialize mystery ship
			if(rand.nextInt(1000) <= 2) {
				mystery = new Mystery(rand.nextInt(SpaceInvaders.FRAME_WIDTH - 40), 40);
				mystery.playSound();
				if(rand.nextInt(100) < 50) {
					mysteryVector = -1;
				} else {
					mysteryVector = 1;
				}
			}
		} else { // if there is mystery ship
			if(mystery.gotHit(baseMissile)) {
				mystery.setHit(true);
				score = score + mystery.getPoints();
				baseMissile = null;
			}
			
			if(mystery.getX() <= 0) {
				mysteryVector = 1;
			} else if(mystery.getX() + 40 >= SpaceInvaders.FRAME_WIDTH) {
				mysteryVector = -1;
			}
			
			if(mystery.getPulseCounter() > mysteryPulseLimist) {
				if(mystery.isHit()) {
					mystery.setVisible(false);
				}
				if(mysteryVector == 1) {
					mystery.move(Direction.RIGHT, 5);
				} else if (mysteryVector == -1) {
					mystery.move(Direction.LEFT, 5);
				}				
				mystery.setPulseCounter(0);
			} else {
				mystery.setPulseCounter(mystery.getPulseCounter()+1);
			}
		}
			
		if(invaderShiftDown) {
			invaderShiftDown = !invaderShiftDown;
		}
				
	}
	
	private boolean invaderCanFireMissile(int row, int col) {
		int count = 0;
 		for(Missile missile: invaderMissiles) {
			if(missile.isVisible()) {
				count++;
			}
		}
		
		if(count >= 3) {
			return false;
		}
		
		if(invaders[row][col].isHit()) {
			return false;
		}
		
		if (row != SpaceInvaders.INVADER_ROW-1) {
			Invader invader = invaders[row+1][col];
			if(!invader.isHit()) {
				return false;
			};
		} 
		
		return true;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	
	

}
