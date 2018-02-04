package ar.com.sourceSistemas.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class SlideView extends JFrame {

	JLabel pic;
	Timer tm;
	int x = 0;
	int time = 0;

	File[] list;
	int width = 0;
	int height = 0;

	public SlideView() {
		super("Java SlideShow");
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.showOpenDialog(null);
		String timer = JOptionPane.showInputDialog(null, "cuanto tiempo duran ? ");
		time = Integer.valueOf(timer);
		File file = jfc.getSelectedFile();
		list = file.listFiles();
		init();

	}

	public void init() {
		pic = new JLabel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = Integer.valueOf((int) screenSize.getWidth());
		height = Integer.valueOf((int) screenSize.getHeight());
		pic.setBounds(40, 30, width, height);

		// Call The Function SetImageSize
		SetImageSize(6);

		// set a timer
		tm = new Timer(time, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SetImageSize(x);
				x += 1;
				if (x >= list.length)
					x = 0;
			}
		});
		add(pic);
		tm.start();
		setLayout(null);
		setSize(width, height);
		getContentPane().setBackground(Color.decode("#bdb67b"));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	// create a function to resize the image
	public void SetImageSize(int i) {
		ImageIcon icon = new ImageIcon(list[i].toString());
		Image img = icon.getImage();
		// Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(),
		// Image.SCALE_SMOOTH);
		Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon newImc = new ImageIcon(newImg);
		pic.setIcon(newImc);
	}

}
