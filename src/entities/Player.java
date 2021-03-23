package entities;

/**
 * Singlton Resource <br>
 * Entitiy that represents the player flow int the game
 * @author gokul
 *
 */
public class Player {
	/**
	 * name of player 
	 */
	private String name = "" ;
	/**
	 * attempts made by the player
	 */
	private int attempts = 0;
	/**
	 * No of Attempts won by the player 
	 */
	private int win = 0;
	/**
	 * No of attempts lose by the user 
	 */
	private int lose = 0;
	/**
	 * boolean flag represent weather the last game is won
	 */
	private boolean islastwon;
	/**
	 * sets to true on start on each game
	 */
	private boolean isnewplayer=true;
	/**
	 * contains the win/lose log eg:wwlw represents win,win,lose,win
	 */
	private String autopilotattemptlog = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public boolean isIslastwon() {
		return islastwon;
	}
	public void setIslastwon(boolean islastwon) {
		this.islastwon = islastwon;
	}
	public boolean isIsnewplayer() {
		return isnewplayer;
	}
	public void setIsnewplayer(boolean isnewplayer) {
		this.isnewplayer = isnewplayer;
	}
	//used to push the result of last attempt to playerattemptlog
	public void pushlastattempt(boolean iswin) {
		if(iswin)
			autopilotattemptlog = autopilotattemptlog + "w";
		else 
			autopilotattemptlog = autopilotattemptlog + "l";
	}
	public String getAutopilotattemptlog() {
		return autopilotattemptlog;
	}
	public void setAutopilotattemptlog(String autopilotattemptlog) {
		this.autopilotattemptlog = autopilotattemptlog;
	}
}
