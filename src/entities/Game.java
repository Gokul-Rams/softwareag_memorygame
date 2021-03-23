package entities;

/**
 * Singlton Resource<br>
 * Represent the entire Game 
 * @author gokul
 *
 */
public class Game {
	
	/**
	 * the current player of the game
	 */
	private Player currentplayer = new Player();
	/**
	 * number of digits to display for user to memorize
	 */
	private int noofdigits=1;
	/**
	 * The no of secounds the digits to be diaplayed
	 */
	private int duration=3;
	/**
	 * boolean flag represents wheather the game is in autopilot mode
	 */
	private Boolean isautopilot = false;
	//static variable used to as singlton resource
	static Game CurrentGame = new Game();
	
	private Game() {
		
	}
	
	//used to get the singlton game object
	public static Game getGame() {
		return CurrentGame;
	}

	public Player getCurrentplayer() {
		return currentplayer;
	}

	public void setCurrentplayer(Player currentplayer) {
		this.currentplayer = currentplayer;
	}

	public int getNoofdigits() {
		return noofdigits;
	}

	public void setNoofdigits(int noofdigits) {
		this.noofdigits = noofdigits;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Boolean getIsautopilot() {
		return isautopilot;
	}

	public void setIsautopilot(Boolean isautopilot) {
		this.isautopilot = isautopilot;
	}
}
