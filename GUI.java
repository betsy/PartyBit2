import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Vector;

public class GUI extends JFrame implements KeyListener, ActionListener {

	private static final long serialVersionUID = 6411499808530678723L;
	public SampleListener listener;
	public Controller controller = new Controller();

	public static int numButtons = 6;
	public int buttonRadius = 50;
	public int mode = 1; // 1 = CALIBRATION, 2 = PLAY

	public Button[] buttons = new Button[numButtons];

	public GUI() {
//		getSounds();

		// Create a sample listener and controller
		listener = new SampleListener(this);

		// key listener
		addKeyListener(this);

		// Have the sample listener receive events from the controller
		controller.addListener(listener);

		setLayout(new FlowLayout()); // set the layout manager
		setResizable(false);
		setSize(500, 200);
		setPresses();
		setVisible(true);

		// Keep this process running until Enter is pressed
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(listener);
	}

	public void getSounds(File[] files) {
		for (int i = 0; i < files.length; i++) {
//			URL url = this.getClass().getClassLoader()
//					.getResource(files[i]);
			AudioInputStream audioIn;
			try {
				System.out.println(files[i]);
				audioIn = AudioSystem.getAudioInputStream(files[i]);
				// Get a sound clip resource.
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				buttons[i] = new Button(null, clip);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Open audio clip and load samples from the audio input stream.
			catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// SoundManager sou = new SoundManager();
		// sou.addClip("cow-moo3.wav");
		// sou.playSound(0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == ' ') { // switch mode
			if(mode == 1) mode = 2;
			else if(mode == 2) mode = 1;
			String modeName = mode == 1 ? "CALIBRATE" : "PLAY";
			System.out.println("SWITCHING MODE TO " + modeName);
		}
		if (mode == 1) { // calibrate and store a virtual button
			int keyNum = e.getKeyCode() - '0';
			if (keyNum > 0 && keyNum <= numButtons) {
				Vector v = listener.getSinglePointedFingerPos();
				if (v != null)
					buttons[keyNum - 1].setPos(v);
				System.out.println(keyNum);
			}
			System.err.println(Arrays.toString(buttons));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
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
	JPanel press1 = new JPanel();
	JPanel press2 = new JPanel();
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV", "wav");

	
	void setPresses(){
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

		press1.add(select1);
		press1.add(Box.createHorizontalStrut(30));
		press1.add(select2);
		press1.add(Box.createHorizontalStrut(30));
		press1.add(select3);
		press1.add(Box.createHorizontalStrut(30));

		press2.add(select4);
		press2.add(Box.createHorizontalStrut(30));
		press2.add(select5);
		press2.add(Box.createHorizontalStrut(30));
		press2.add(select6);
		press2.add(Box.createHorizontalStrut(30));
		add(press1, BorderLayout.CENTER);
		add(press2, BorderLayout.CENTER);

		done = new JButton("Done");
		done.addActionListener(this);
		add(done, BorderLayout.AFTER_LAST_LINE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((JButton) e.getSource() == select1) {
			int returnVal1 = chooser.showOpenDialog(this);
			if (returnVal1 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory1);
			}
		}

		if ((JButton) e.getSource() == select2) {
			int returnVal2 = chooser.showOpenDialog(this);
			if (returnVal2 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory2);
			}
		}

		if ((JButton) e.getSource() == select3) {
			int returnVal3 = chooser.showOpenDialog(this);
			if (returnVal3 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory3);
			}
		}

		if ((JButton) e.getSource() == select4) {
			int returnVal4 = chooser.showOpenDialog(this);
			if (returnVal4 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory4);
			}
		}

		if ((JButton) e.getSource() == select5) {
			int returnVal5 = chooser.showOpenDialog(this);
			if (returnVal5 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory5);
			}
		}

		if ((JButton) e.getSource() == select6) {
			int returnVal6 = chooser.showOpenDialog(this);
			if (returnVal6 == JFileChooser.APPROVE_OPTION) {
				fileChoosing(directory6);
			}
		}

		if ((JButton) e.getSource() == done) {
			removeAll();
			revalidate();
			repaint();
			getSounds(new File[] {directory1,directory2,directory3,directory4,directory5,directory6});
//			mode=2;
		}
	}
	
	public void fileChoosing(File file) {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		file = chooser.getSelectedFile();
		System.out.println(file.getAbsolutePath());
	}
}
