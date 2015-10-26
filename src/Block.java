import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Observable;
import java.util.Observer;

public class Block extends Gravity {

	public Block(double x, double y, double height, double width) {
		shape = new Rectangle2D.Double(x, y, height, width);
	}

	public Rectangle2D getBlock() {
		return (Rectangle2D) shape;
	}

	public void setPosition(double X, double Y) {
		((Rectangle2D) shape).setFrame(X, Y, ((Rectangle2D) shape).getWidth(),
				((Rectangle2D) shape).getHeight());
	}

	public double getX() {
		return ((Rectangle2D) shape).getX();
	}

	public double getY() {
		return ((Rectangle2D) shape).getY();
	}
	
}
