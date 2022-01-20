package space.invaders.draw;

import java.awt.Image;

import javax.sound.sampled.Clip;

public abstract class Ship extends Drawable {
	
	private boolean isHit = false;
	private Image hitImage;
	private Clip hitSound = getSound("/aud_hit.wav");
	private boolean isVisible = true;
	
	public Ship(int x, int y) {
		super(x, y);
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
		if(hitSound.isRunning()) {
			hitSound.stop();
		}
		hitSound.setFramePosition(0);
		hitSound.start();
	}

	public Image getHitImage() {
		return hitImage;
	}

	public void setHitImage(Image hitImage) {
		this.hitImage = hitImage;
	}
	
	public boolean gotHit(Missile missile) {
		if(missile != null) {
			int missileX = missile.getX();
			int missileY = missile.getY();
			
			if(!this.isHit() && missile.isVisible()) {
				if(missileX >= getX() && missileX <= getX()+getWidth()
					&& missileY >= getY() && missileY <= getY()+getHeight()) {
						return true;
					}
			}
			return false;
		}	
		
		return false;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
