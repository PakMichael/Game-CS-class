import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Fifth extends JComponent {
	private JFrame frame;
	private JButton ok;
	private JTextField input;
	private JTextField field;

	public Fifth() {
		initFrame();

	}

	private void initFrame() {
		frame = new JFrame("Fifth");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setSize(250, 140);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		setButtons();
		frame.setVisible(true);
	}

	private void setButtons() {
		input = new JTextField(20);
		field = new JTextField(20);
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile(input.getText(), field.getText());
			}
		});
		frame.add(input);
		frame.add(field);
		frame.add(ok);
	}

	private void openFile(String path, String arg) {
		List l = new ArrayList();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			int letter = br.read();
			while (letter != -1) {
				l.add(((char) letter) ^ Integer.parseInt(arg));
				letter = br.read();
			}
			try (FileWriter fw = new FileWriter(path)) {
				for (int a = 0; a < l.size(); a++) {
					fw.write((int) l.get(a));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Fifth();
			}
		});
	}
}
