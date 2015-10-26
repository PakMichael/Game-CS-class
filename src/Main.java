import javax.swing.*;

public class Main {
	private JFrame frame;
	// private Model model;
	private View view;
	private Model model;
	private Controller controller;

	// private Controller controller;

	public Main() {
		initFrame();
		initMVC();
	}

	private void initFrame() {
		frame = new JFrame();
		frame.setSize(300, 400); // Ratio 4x3
//		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void initMVC() {
		model = new Model();
		view = new View(model);
		controller = new Controller(model, view);
		model.addObserver(view);
		frame.add(view);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});

	}

}
