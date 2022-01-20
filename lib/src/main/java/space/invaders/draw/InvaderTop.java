package space.invaders.draw;

public class InvaderTop extends Invader{

	public InvaderTop(int x, int y) {
		super(x, y);
		setPoints(30);
		imageA = getImage("/img_invadertopA.gif");
		imageB = getImage("/img_invaderTopB.gif");
		setWidth(imageA.getWidth(null));
		setHeight(imageA.getHeight(null));
	}
		
}
