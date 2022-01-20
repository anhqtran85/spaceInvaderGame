package space.invaders.draw;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

public class Mystery extends Invader {
	private Image image = getImage("/img_mystery.gif");
	private Clip sound = getSound("/aud_mystery.wav");

	public Mystery(int x, int y) {
			super(x, y);
			
			// randomly assign points for mystery
			Random rand = new Random();
			int i = rand.nextInt(4);
			switch(i) {
			case 0 : setPoints(300); break;
			case 1 : setPoints(150); break;
			case 2 : setPoints(100); break;
			case 3 : setPoints(50);	 break;
			}
			
			setWidth(image.getWidth(null));
			setHeight(image.getHeight(null));
		}


	@Override
	public void draw(Graphics2D g2) {
		if(isHit()) {
			g2.drawImage(getHitImage(), getX(), getY(), null);
		} else {
			g2.drawImage(image, getX(), getY(), null);
		}
	}
	
	public void playSound() {
		if(!isHit()) {
			if(!sound.isRunning()) {
				sound.setFramePosition(0);
				sound.start();
			}			
		}
		
	}

}
