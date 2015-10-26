import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Observable;
import java.util.Observer;

public class Block extends Gravity {
	private boolean isMoving = false;
	private boolean isJumping = false;

	public Block(double x, double y, double height, double width) {
		super.shape = new Rectangle2D.Double(x, y, height, width);
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

	public double getHeight() {
		return shape.getHeight();
	}

	public double getWidth() {
		return shape.getWidth();
	}

	public void jump() {
		if (!isJumping && isOnGround()) {
			isJumping = true;
			Thread t = new Thread(new Runnable() {
				public void run() {
					/*
					 * "Energy" of a jumping figure is evaluated in a very
					 * abstract form: it is implicitly stated that any object of
					 * height H can jump 2H and that the energy at the beginning
					 * of the jump is bigger than in the apex. 10 iterations
					 * means that sum("10 moments")=H;
					 */
					double startingPoint = getY();
					double velocity = (getHeight() - 10) / 45;
					for (int a = 10; a > 0; a--) {
						shape.setFrame(getX(), getY()
								- (1 + (a - 1) * velocity), getWidth(),
								getHeight());
						setChanged();
						notifyObservers();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					isJumping = false;
					start();
				}
			});
			t.start();
		}
	}

	public void moveLeft() {
		if (!isMoving) {
			isMoving = true;
			Thread t = new Thread(new Runnable() {
				public void run() {
					double startingPoint = getX();
					for (int a = 0; a <= getWidth(); a += 2) {
						shape.setFrame(startingPoint - a, getY(), getWidth(),
								getHeight());
						setChanged();
						notifyObservers();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					isMoving = false;

				}
			});
			t.start();
		}
	}

	public void moveRight() {
		if (!isMoving) {
			isMoving = true;
			Thread t = new Thread(new Runnable() {
				public void run() {
					double startingPoint = getX();
					for (int a = 0; a <= getWidth(); a += 2) {
						shape.setFrame(startingPoint + a, getY(), getWidth(),
								getHeight());
						setChanged();
						notifyObservers();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					isMoving = false;

				}
			});
			t.start();
		}
	}
}
