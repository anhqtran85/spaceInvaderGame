package space.invaders.draw;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.sound.sampled.Clip;

public class Base extends Ship {
	private Image base = getImage("/img_base.gif");
	private Clip sound = getSound("/aud_basefire.wav");

	public Base(int x, int y) {
		super(x, y);
		setHitImage(getImage("/img_basehit.gif"));
		setWidth(base.getWidth(null));
		setHeight(base.getHeight(null));
	}
	
	public void playShootingSound() {
		if(sound.isRunning()) {
			sound.stop();
		}
		sound.setFramePosition(0);
		sound.start();
	}
	
	@Override
	public void draw(Graphics2D g2) {
		if(isHit()) {
			g2.drawImage(getHitImage(), getX(), getY(), null);
		} else {
			g2.drawImage(base, getX(), getY(), null);
		}
	}
}
