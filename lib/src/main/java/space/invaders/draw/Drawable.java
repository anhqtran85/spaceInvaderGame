package space.invaders.draw;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public abstract class Drawable {

	public enum Direction {UP, DOWN, LEFT, RIGHT};
	private int x;
	private int y;
	private int width;
	private int height;
	private int pulseCounter = 0;
	
	protected Drawable(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public abstract void draw(Graphics2D g2);
	
	public void move(Direction d, int distance) {
		switch(d) {
			case LEFT 	: this.x -= distance; break; 
			case RIGHT 	: this.x += distance; break;
			case UP		: this.y -= distance; break;
			case DOWN	: this.y += distance; break;
		}
	};	
		
	protected Image getImage(String filename) {
		URL 		url		= getClass().getResource(filename);
		ImageIcon 	icon	= new ImageIcon(url);
		return icon.getImage();
	}
	
	protected Clip getSound(String fileName) {
		Clip clip = null;
		try {
			InputStream		in		= getClass().getResourceAsStream(fileName);
			InputStream		buf		= new BufferedInputStream(in);
			AudioInputStream	stream = AudioSystem.getAudioInputStream(buf);
			clip = AudioSystem.getClip();
			clip.open(stream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		return clip;
	}

	public int getPulseCounter() {
		return pulseCounter;
	}

	public void setPulseCounter(int pulseCounter) {
		this.pulseCounter = pulseCounter;
	}
}
