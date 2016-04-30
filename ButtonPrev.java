import java.awt.Toolkit;

import com.leapmotion.leap.Vector;

public class ButtonPrev {
	public Vector pos;
	int len = 200; //millis
	long lastTime;
	
	
	ButtonPrev(Vector pos){
		this.pos = pos;
		lastTime = System.currentTimeMillis() % 10000000;
	}
	
	public void play(){
		long curTime = System.currentTimeMillis() % 10000000;
		System.out.println(curTime-lastTime);
		if(curTime-lastTime>len){
			System.out.println("BEEEEP");
			Toolkit.getDefaultToolkit().beep();
			lastTime = curTime;
		}
	}
}
