import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

class SampleListener extends Listener {
	Frame frame;
	GUI gui;
	
	public SampleListener(GUI gui){
		this.gui = gui;
	}
	
	public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    public void onFrame(Controller controller) {
        frame = controller.frame();
        if(gui.mode == 2){ //if in PLAY mode
        	checkFingers();
        }
    }
    
    public void checkFingers(){
    	FingerList extendedFingers = getExtendedFingers();
    	for(Finger f:extendedFingers){
    		for(int i=0;i<gui.buttons.length;i++){
    			Button button=gui.buttons[i];
    			if(button == null || button.pos == null) continue;
    			if(button.pos.distanceTo(f.stabilizedTipPosition())<gui.buttonRadius){
    				button.play();
//    				System.out.println("PRESSED: "+i);
    			};
    		}
    	}
    }
    
    public Vector getSinglePointedFingerPos(){
    	FingerList extendedFingers = getExtendedFingers();
    	if(extendedFingers.count() == 0){
    		System.err.println("No pointed fingers");
    		return null;
    	}
    	if(extendedFingers.count() > 1){
    		System.err.println("Multiple pointed fingers");
    		return null;
    	}
    	System.err.println("SET POSITION FOR BUTTON");
    	return extendedFingers.get(0).stabilizedTipPosition();
    }
    
    public FingerList getExtendedFingers(){
    	return frame.fingers().extended();
    }
}
