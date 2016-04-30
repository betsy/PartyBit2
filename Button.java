import javax.sound.sampled.Clip;

import com.leapmotion.leap.Vector;

public class Button {
	public Vector pos;
	int len = 200; // millis
	long lastTime;
	boolean on = false;
	boolean looped = true;
	Clip clip;

	Button(Vector pos, Clip c) {
		this.pos = pos;
		lastTime = System.currentTimeMillis() % 10000000;
		clip = c;
	}

	void setPos(Vector pos) {
		this.pos = pos;
	}
	
	void setLooped(boolean looped){
		this.looped=looped;
	}

	public void play() {
		long curTime = System.currentTimeMillis() % 10000000;
		if (curTime - lastTime > len) {
//			Toolkit.getDefaultToolkit().beep();
			on = !on;
			lastTime = curTime;
			if (!on)
				clip.stop();
			else {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
	}
}
