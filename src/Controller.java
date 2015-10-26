import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;

public class Controller implements MouseListener, ComponentListener,
		MouseMotionListener {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		addListeners();
	}

	private void addListeners() {
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		view.addComponentListener(this);
		view.setFocusable(true);
		view.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				model.moveTo(e.getKeyChar());

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		model.setTrajectoryTo(e.getX(), e.getY());
		model.shoot();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("Mouse was pressed (at Controller.mousePressed)");
		// model.setMousePosition(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent e) {
		System.out.println("Frame resized (at Controller.componentResized)");
		model.setComponentSize(e.getComponent().getSize());
//		model.setBackgroundSize(e.getComponent().getSize());
//		model.setForegroundSize(e.getComponent().getSize());

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		model.setMousePosition(e.getX(), e.getY());

	}
}
