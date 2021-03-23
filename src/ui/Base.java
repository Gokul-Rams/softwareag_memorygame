package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import entities.Game;
import entities.Theme;
import uilisteners.GameExitListner;
import uilisteners.GameHelpListner;
import uilisteners.GameHomeListner;
import uilisteners.GameResultListner;
import uilisteners.GameStartListner;

/**
 * Base Frame,panel added on top of this
 * Sets the basic properties of window
 * @author gokul
 *
 */
public class Base extends JFrame implements GameStartListner,GameExitListner,GameHomeListner,GameResultListner,GameHelpListner{
	
	/**
	 * constructor to sent properties and panel to frame
	 */
	public Base() {
		//setting up the JFrame properties
		setSize(Theme.width,Theme.height);
		setVisible(true);
		setLayout(new GridLayout());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//adding player details input panel on start
		PlayerInputPanel inputpanel = new PlayerInputPanel(this,this,this);
		add(inputpanel);
		inputpanel.setSize(Theme.width,Theme.height);
		inputpanel.setVisible(true);  
	}

	/**
	 * listner implementation to add game panel to frame i.e start game
	 */
	@Override
	public void startgame() {
		//listner implementation to add Game panel to frame i.e start game
		getContentPane().removeAll();
		repaint();
		GamePanel gamePanel = new GamePanel(this,this);
		add(gamePanel);
		gamePanel.setSize(Theme.width,Theme.height);
		gamePanel.setVisible(true);
	}

	/**
	 * listner implementation to end application
	 */
	@Override
	public void endgame() {
		//listner implementation to close application
		System.exit(0);
	}

	/**
	 * listner implementation to add playerinput panel to frame i.e panel to get palyer details for next game game
	 * called only on new game
	 */
	@Override
	public void returntohome(Boolean isnewplayer) {
		//listner implementation to add player input panel to frame
		if(isnewplayer) {
			//resets the value to default on new player i.e on exit of an ongoing game
			Game game = Game.getGame();
			game.setDuration(3);
			game.setIsautopilot(false);
			game.setNoofdigits(1);
			game.getCurrentplayer().setName("");
			game.getCurrentplayer().setAttempts(0);
			game.getCurrentplayer().setWin(0);
			game.getCurrentplayer().setLose(0);
			game.getCurrentplayer().setIslastwon(false);
		}
		getContentPane().removeAll();
		repaint();
		PlayerInputPanel inputpanel = new PlayerInputPanel(this,this,this);
		add(inputpanel);
		inputpanel.setSize(Theme.width,Theme.height);
		inputpanel.setVisible(true);
	}

	/**
	 * listner implementation to add game Result panel to frame
	 */
	@Override
	public void showgameresult() {
		//listner implementation to add Game Result panel to frame 
		getContentPane().removeAll();
		repaint();
		ResultPanel resultPanel = new ResultPanel(this,this,this);
		add(resultPanel);
		resultPanel.setSize(Theme.width,Theme.height);
		resultPanel.setVisible(true);
	}

	/**
	 * listner implementation to add game help panel to the frame
	 */
	@Override
	public void showgamehelp() {
		//listner implementation to add Game Help panel to frame 
		getContentPane().removeAll();
		repaint();
		HelpPanel helpPanel = new HelpPanel(this);
		add(helpPanel);
		helpPanel.setSize(Theme.width,Theme.height);
		helpPanel.setVisible(true);
	}
}
