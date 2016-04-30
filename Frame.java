import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel welcome;
	JButton select1;
	JButton select2;
	JButton select3;
	JButton select4;
	JButton select5;
	JButton select6;
	JButton done;
	File directory1, directory2, directory3, directory4, directory5,
			directory6;
	JPanel buttons = new JPanel();
	JPanel buttons2 = new JPanel();
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV", "wav");

	static Frame SetUp;
	static Frame music;

	// constructor
	Frame(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout()); // set the layout manager
		this.setResizable(false);

		welcome = new JLabel(
				"<html>Welcome to LeapPad!<br>Hover your finger in the right position and press Enter to select.<br><br></html>"); // construct
																																	// a
																																	// JLabel
		add(welcome, BorderLayout.PAGE_START);// add the label to the JFrame

		chooser.setFileFilter(filter);

		select1 = new JButton("Select track 1");
		select1.addActionListener(this);

		select2 = new JButton("Select track 2");
		select2.addActionListener(this);

		select3 = new JButton("Select track 3");
		select3.addActionListener(this);

		select4 = new JButton("Select track 4");
		select4.addActionListener(this);

		select5 = new JButton("Select track 5");
		select5.addActionListener(this);

		select6 = new JButton("Select track 6");
		select6.addActionListener(this);

		buttons.add(select1);
		buttons.add(Box.createHorizontalStrut(30));
		buttons.add(select2);
		buttons.add(Box.createHorizontalStrut(30));
		buttons.add(select3);
		buttons.add(Box.createHorizontalStrut(30));

		buttons2.add(select4);
		buttons2.add(Box.createHorizontalStrut(30));
		buttons2.add(select5);
		buttons2.add(Box.createHorizontalStrut(30));
		buttons2.add(select6);
		buttons2.add(Box.createHorizontalStrut(30));
		add(buttons, BorderLayout.CENTER);
		add(buttons2, BorderLayout.CENTER);

		done = new JButton("Done");
		done.addActionListener(this);
		add(done, BorderLayout.AFTER_LAST_LINE);
	}

	public void actionPerformed(ActionEvent e) {
		if ((JButton) e.getSource() == select1) {
			int returnVal1 = chooser.showOpenDialog(Frame.this);
			if (returnVal1 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory1);
			}
		}

		if ((JButton) e.getSource() == select2) {
			int returnVal2 = chooser.showOpenDialog(Frame.this);
			if (returnVal2 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory2);
			}
		}

		if ((JButton) e.getSource() == select3) {
			int returnVal3 = chooser.showOpenDialog(Frame.this);
			if (returnVal3 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory3);
			}
		}

		if ((JButton) e.getSource() == select4) {
			int returnVal4 = chooser.showOpenDialog(Frame.this);
			if (returnVal4 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory4);
			}
		}

		if ((JButton) e.getSource() == select5) {
			int returnVal5 = chooser.showOpenDialog(Frame.this);
			if (returnVal5 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory5);
			}
		}

		if ((JButton) e.getSource() == select6) {
			int returnVal6 = chooser.showOpenDialog(Frame.this);
			if (returnVal6 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory6);
			}
		}

		if ((JButton) e.getSource() == done) {
			music.setVisible(true);
		}
	}

	public void fileChoosing(File file) {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		file = chooser.getSelectedFile();
		System.out.println(file.getAbsolutePath());
	}

	public static void main(String[] args) {
		SetUp = new Frame("Hello"); // construct a MyFrame object
		SetUp.pack();
		SetUp.setSize(500, 200);
		SetUp.setVisible(true); // ask it to become visible
		music = new Frame("Hello");
		music.setVisible(false);
	}
}