package space.invaders.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import space.invaders.SpaceInvaders;

public class Missile extends Drawable{

	public Missile(int x, int y) {
		super(x, y);
		super.setWidth(2);
		super.setHeight(10);
	}
	
	public boolean isVisible() {
		return getY() > 0 && getY() + getHeight() < SpaceInvaders.FRAME_HEIGHT;
	}

	@Override
	public void draw(Graphics2D g2) {
		Shape retangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
		g2.setColor(Color.WHITE);
		g2.fill(retangle);
	}

}
