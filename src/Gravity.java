import java.awt.Shape;
import java.awt.geom.RectangularShape;
import java.util.Observable;

public class Gravity extends Observable implements Runnable {
	private Thread t;
	private boolean onGround = true;
	public RectangularShape shape;

//	public Gravity() {
//		t = new Thread(this);
//		t.start();
//	}

	public void run() {
		for (double a = 0; !onGround; a++) {
			shape.setFrame(shape.getX(), shape.getY() + a, shape.getWidth(),
					shape.getHeight());
			setChanged();
			notifyObservers();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stop() {
//		System.out.println("Stop falling (at Gravity.stop)");
		onGround = true;
	}

	public void start() {
		if (onGround) {
			t = new Thread(this);
			t.start();
			onGround = false;
		}
	}

	public boolean isOnGround() {
		return onGround;
	}
}
