package space.invaders.draw;

public class InvaderMiddle extends Invader{

	public InvaderMiddle(int x, int y) {
		super(x, y);
		imageA = getImage("/img_invadermiddleA.gif");
		imageB = getImage("/img_invadermiddleB.gif");
		setWidth(imageA.getWidth(null));
		setHeight(imageA.getHeight(null));
		setPoints(20);
	}

}
