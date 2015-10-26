import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

public class Model extends Observable implements Observer {
	private double componentHight;
	private double componentWidth;
	private Block block;
	private int mouseX;
	private int mouseY;
	private double floor = componentHight;
	private List<Point2D> bulletPath = new ArrayList<Point2D>();
	private List<Point2D> bulletRound = new ArrayList<Point2D>();
	private int roundSize = 10;
	private Image playerSkin;
	private Image background;
	private Image foreground;
	private Image[] images;

	public Model() {
		createBlockAt(50, 50);
	}

	private void initializeSkins() {
		try {
			playerSkin = Toolkit.getDefaultToolkit().getImage("src/go.gif");
			background = ImageIO.read(new File("src/cave.png"));
			// background = background.getScaledInstance(294, 372, 100);
			foreground = ImageIO.read(new File("src/front.png"));
			// foreground = foreground.getScaledInstance(294, 372, 100);
			images = new Image[3];// { background, foreground, playerSkin };
			images[0] = background;
			images[1] = foreground;
			images[2] = playerSkin;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setFloorLevel(int level) {
		floor = componentHight - level;
	}

	public void createBlockAt(int X, int Y) {
		mouseX = X;
		mouseY = Y;
		createBlock();
		setModelToChanged();
	}

	public void setMousePosition(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	public int getX() {
		return mouseX;
	}

	public int getY() {
		return mouseY;
	}

	private void setModelToChanged() {
		// System.out.println("Model has been changed (at Model.modelChanged)");
		setChanged();
		notifyObservers();
	}

	public void setComponentSize(Dimension size) {
		componentHight = size.getHeight();
		componentWidth = size.getWidth();
		setFloorLevel(30);
		block.start();
		setModelToChanged();
	}

	public void setBackgroundSize(Dimension size) {
		images[0] = background.getScaledInstance((int) size.getWidth(),
				(int) size.getHeight(), 100);
		setModelToChanged();
	}

	public void setForegroundSize(Dimension size) {
		images[1] = foreground.getScaledInstance((int) size.getWidth(),
				(int) size.getHeight(), 100);
		setModelToChanged();
	}

	private void createBlock() {
		// if (mouseY > floor - 15)
		// return;
		// if (block != null)
		// block.stop();
		block = new Block(mouseX, mouseY, 25, 25);
		block.addObserver(this);
		block.start();
		// System.out.println("Block created (at Model.createBlock)");
	}

	public Block getObjects() {
		return block;
	}

	public double getFloorLevel() {
		return floor;
	}

	public void moveTo(char direction) {
		Rectangle2D tmp = block.getBlock();
		if (direction == ' ' && block.isOnGround()) {
			block.getBlock().setFrame(tmp.getX(), tmp.getY() - 20,
					tmp.getWidth(), tmp.getHeight());
			block.start();
		}
		if (direction == 'd') {
			block.getBlock().setFrame(tmp.getX()+tmp.getWidth(), tmp.getY(),
					tmp.getWidth(), tmp.getHeight());
		}
		if (direction == 'a') {
			block.getBlock().setFrame(tmp.getX() - tmp.getWidth(), tmp.getY(),
					tmp.getWidth(), tmp.getHeight());
		}
		setChanged();
		notifyObservers();
	}

	public void setTrajectoryTo(int X, int Y) {
		System.out.println(block.getX() + " " + block.getY());
		Line2D l = new Line2D.Double(block.getX(), block.getY(), X, Y);
		for (Point2D point : new LineIterator(l)) {
			bulletPath.add(point);
		}
	}

	public void shoot() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				for (int a = 0; a < bulletPath.size() / roundSize; a++) {
					bulletRound = bulletPath.subList(a * roundSize, a
							* roundSize + roundSize);
					setModelToChanged();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				bulletPath = new ArrayList<Point2D>();
				bulletRound = new ArrayList<Point2D>();
				setModelToChanged();
			}
		});
		t.start();
	}

	public List<Point2D> getBullets() {
		return bulletRound;
	}

	public Image getPlayerSkin() {
		return images[2];
	}

	public Image getBackground() {
		return images[0];
	}

	public Image getForeground() {
		return images[1];
	}

	public void update(Observable o, Object arg) {
		if (block.getY() >= floor - block.getBlock().getHeight()) {
			// System.out.println("Terminating falling (at Model.update)");
			block.stop();
			block.setPosition(block.getX(), block.getY()
					- (block.getY() - (floor - block.getBlock().getHeight())));
		}
		setModelToChanged();

	}
}
