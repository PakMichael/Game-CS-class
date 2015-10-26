import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

/* Size of this component is w=294; h=372
 * 
 */

public class View extends JComponent implements Observer {
	private Model model;

	public View(Model model) {
		this.model = model;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// g2.drawImage(model.getBackground(), 0, 0, this);
		g2.drawLine(0, (int) model.getFloorLevel(), getWidth(),
				(int) model.getFloorLevel());
		g2.setColor(Color.BLUE);
		for (Point2D bullet : model.getBullets())
			g2.drawString(".", (int) bullet.getX(), (int) bullet.getY());
		g2.draw(model.getObjects().getBlock());
		// g2.scale(0.3, 0.3);
		// g2.drawImage(model.getPlayerSkin(), (int) model.getObjects().getX(),
		// (int) model.getObjects().getY(), this);
		// g2.drawImage(model.getForeground(), 0, 0, this);
	}

	public boolean imageUpdate(Image img, int flags, int x, int y, int w, int h) {
		repaint(); // repaint(x, y, w, h);
		return true;
	}

	@Override
	public void update(Observable e, Object arg1) {
		// System.out.println("Update initiated (at View.update)");
		repaint();

	}

}
