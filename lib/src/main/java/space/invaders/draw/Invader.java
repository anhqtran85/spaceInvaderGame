package space.invaders.draw;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Invader extends Ship {
	
	private int points;
	protected Image imageA;
	protected Image imageB;
	private boolean alternate = true;
	private int velocity = 5; 
	

	public Invader(int x, int y) {
		super(x, y);
		setHitImage(getImage("/img_invaderhit.gif"));
	}
	
	@Override
	public void draw(Graphics2D g2) {
		if(isHit()) {
			g2.drawImage(getHitImage(), getX(), getY(), null);
		} else if(alternate) {
			g2.drawImage(imageA, getX(), getY(), null);
		} else {
			g2.drawImage(imageB, getX(), getY(), null);
		}
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isAlternate() {
		return alternate;
	}

	public void setAlternate(boolean alternate) {
		this.alternate = alternate;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	

}
