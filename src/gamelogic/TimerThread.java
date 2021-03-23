package gamelogic;

import entities.Theme;
import uilisteners.StopTimerListner;
import uilisteners.UpdateTimerListner;

/**
 * Thread to implement stop timer
 * @author gokul
 *
 */
public class TimerThread extends Thread{

	private int durationinsecs;
	private UpdateTimerListner updatelistner;
	private StopTimerListner stoplistner;
	/**
	 * Constructor to initilize listeners
	 * @param durationinsecs
	 * @param updatelistner
	 * @param stoplistner
	 */
	public TimerThread(int durationinsecs,UpdateTimerListner updatelistner,StopTimerListner stoplistner) {
		this.durationinsecs = durationinsecs;
		this.updatelistner = updatelistner;
		this.stoplistner = stoplistner;
	}
	
	/**
	 * Thread logic to update and stop timer
	 */
	@Override
	public void run() {
		for(int i=durationinsecs;i>=1;i--) {
			//update timer
			updatelistner.updatetimer(i);
			
			try {
				//make thread sleep for one secound
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				stoplistner.stoptimer(false);
				break;
			}
		}
		//update secounds to 0 and stop timer..
		updatelistner.updatetimer(0);
		stoplistner.stoptimer(true);
	}
	
}
