package uilisteners;

/**
 * Listner to add Home panel i.e get player input to start a new game
 * @author gokul
 *
 */
public interface GameHomeListner {
	/**
	 * listner method to add player input pane to frame
	 * isnewplayer is to get details of a new player after an game end
	 * @param isnewplayer
	 */
	public void returntohome(Boolean isnewplayer);
}
