package uilisteners;

/**
 * listner to stop the timer from thread
 * @author gokul
 *
 */
public interface StopTimerListner {
	/**
	 * listner method to stop thread
	 * iscomplete, on true timer is complete or there an exception in thread
	 * @param iscomplete
	 */
	public void stoptimer(boolean iscomplete);
}
