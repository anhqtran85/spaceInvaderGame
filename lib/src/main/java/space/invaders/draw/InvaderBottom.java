package space.invaders.draw;

public class InvaderBottom extends Invader{

	public InvaderBottom(int x, int y) {
		super(x, y);
		setPoints(10);
		imageA = getImage("/img_invaderbottomA.gif");
		imageB = getImage("/img_invaderbottomB.gif");
		setWidth(imageA.getWidth(null));
		setHeight(imageA.getHeight(null));
	}

}
