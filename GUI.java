import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public int buttonRadius = 70;
	public int mode = 0; // 1 = CALIBRATION, 2 = PLAY

	public Button[] buttons = new Button[numButtons];

	public GUI() {
//		getSounds();

		// Create a sample listener and controller
		listener = new SampleListener(this);

		// key listener
		addKeyListener(this);

		// Have the sample listener receive events from the controller
		controller.addListener(listener);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		System.out.println(Arrays.toString(files));
		for (int i = 0; i < files.length; i++) {
//			URL url = this.getClass().getClassLoader()
//					.getResource(files[i]);
			if(files[i]==null) continue;
			AudioInputStream audioIn;
			try {
				System.out.println(files[i]);
				audioIn = AudioSystem.getAudioInputStream(files[i]);
				// Get a sound clip resource.
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				buttons[i] = new Button(null, clip);
//				if(i>2) buttons[i].setLooped(false);
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
				if (v != null && buttons[keyNum-1]!=null)
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
	
	private List<JButton> select = new ArrayList<>(6);
    public List<File> files = new ArrayList<>(6);
	JLabel welcome;
	JButton done;
	File directory1, directory2, directory3, directory4, directory5,
			directory6;
	JPanel press1 = new JPanel();
	JPanel press2 = new JPanel();
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV", "wav");

	
	void setPresses(){
		welcome = new JLabel(
				"<html>Welcome to LeapPad!<br>Select the corresponding soundtrack.<br><br></html>"); // construct
																																	// a
																																	// JLabel
		add(welcome, BorderLayout.PAGE_START);// add the label to the JFrame

		chooser.setFileFilter(filter);

		for (int i=0; i<6; i++)
		{
			select.add(new JButton("Select track "+(i+1)));
			select.get(i).addActionListener(this);
		}

        for(int i=0; i<3; i++){
            press1.add(select.get(i));
            press1.add(Box.createHorizontalStrut(30));
            press2.add(select.get(i+3));
            press2.add(Box.createHorizontalStrut(30));
        }
		add(press1, BorderLayout.CENTER);
		add(press2, BorderLayout.CENTER);
		
		done = new JButton("Done");
		done.addActionListener(this);
		add(done, BorderLayout.AFTER_LAST_LINE);
		this.setFocusable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((JButton) e.getSource() == select.get(0)) {
			int returnVal1 = chooser.showOpenDialog(this);
			if (returnVal1 == JFileChooser.APPROVE_OPTION) {
				directory1 = fileChoosing();
			}
		}

		if ((JButton) e.getSource() == select.get(1)) {
			int returnVal2 = chooser.showOpenDialog(this);
			if (returnVal2 == JFileChooser.APPROVE_OPTION) {
				directory2 = fileChoosing();
			}
		}

		if ((JButton) e.getSource() == select.get(2)) {
			int returnVal3 = chooser.showOpenDialog(this);
			if (returnVal3 == JFileChooser.APPROVE_OPTION) {
				directory3 = fileChoosing();
			}
		}

		if ((JButton) e.getSource() == select.get(3)) {
			int returnVal4 = chooser.showOpenDialog(this);
			if (returnVal4 == JFileChooser.APPROVE_OPTION) {
				directory4 = fileChoosing();
			}
		}

		if ((JButton) e.getSource() == select.get(4)) {
			int returnVal5 = chooser.showOpenDialog(this);
			if (returnVal5 == JFileChooser.APPROVE_OPTION) {
				directory5 = fileChoosing();
			}
		}

		if ((JButton) e.getSource() == select.get(5)) {
			int returnVal6 = chooser.showOpenDialog(this);
			if (returnVal6 == JFileChooser.APPROVE_OPTION) {
				directory6 = fileChoosing();
			}
		}

		if ((JButton) e.getSource() == done) {
			JPanel newPanel = new JPanel();
			getSounds(new File[] {directory1,directory2,directory3,directory4,directory5,directory6});
			getContentPane().removeAll();
			revalidate();
			repaint();
			welcome = new JLabel(
					"<html>Welcome to LeapPad!<br>Hover your finger in the right position and press a number 1-6 to calibrate button position.<br><br></html>"); // construct
			newPanel.add(welcome, BorderLayout.PAGE_START);// add the label to the JFrame
			getContentPane().add(newPanel);
			getContentPane().validate();
			mode=1;
		}
	}
	
	public File fileChoosing() {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		return chooser.getSelectedFile();
	}
}
