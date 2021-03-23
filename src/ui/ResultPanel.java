package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.Game;
import entities.Theme;
import uilisteners.GameExitListner;
import uilisteners.GameHomeListner;
import uilisteners.GameStartListner;

public class ResultPanel extends JPanel{
	
	/**
	 * Result panel to display result of the game
	 * @param exitlistner
	 * @param homelistner
	 * @param startlistner
	 */
	public ResultPanel(GameExitListner exitlistner,GameHomeListner homelistner,GameStartListner startlistner) {
		//setting up the panel properties
		setLayout(null);
		
		//getting the singloten game resource
		Game game = Game.getGame();
		System.out.println("name : " + game.getCurrentplayer().getName());
		System.out.println("attempts : " + game.getCurrentplayer().getAttempts());
		System.out.println("win : " + game.getCurrentplayer().getWin());
		System.out.println("lose : " + game.getCurrentplayer().getLose());
		System.out.println("Attempt Log : " + game.getCurrentplayer().getAutopilotattemptlog());;
		
		//generalized font..
		Font font_header = new Font(Font.DIALOG,Font.BOLD,25);
		Font font_body = new Font(Font.DIALOG,Font.BOLD,35);
		
		//creating and adding components 
		JLabel lab_playername = new JLabel(game.getCurrentplayer().getName());
		Font font_playername = new Font(Font.DIALOG,Font.BOLD,20);
		lab_playername.setForeground(Theme.Foreground);
		lab_playername.setFont(font_playername);
		lab_playername.setBounds(10,10,300,30);
		add(lab_playername);
		
		JLabel lab_attemptsheader = new JLabel("Attempts",JLabel.CENTER);
		lab_attemptsheader.setFont(font_header);
		lab_attemptsheader.setForeground(Theme.Foreground);
		lab_attemptsheader.setBounds(0,40, Theme.width,30);
		add(lab_attemptsheader);
		
		JLabel lab_attempts = new JLabel("" + game.getCurrentplayer().getAttempts(),JLabel.CENTER);
		lab_attempts.setFont(font_body);
		lab_attempts.setForeground(Theme.Foreground);
		lab_attempts.setBounds(0,80,Theme.width,30);
		add(lab_attempts);
		
		JLabel lab_winheader = new JLabel("Win",JLabel.CENTER);
		lab_winheader.setFont(font_header);
		lab_winheader.setForeground(Theme.Foreground);
		lab_winheader.setBounds(0,140,Theme.width/2,30);
		add(lab_winheader);
		
		JLabel lab_win = new JLabel("" + game.getCurrentplayer().getWin(),JLabel.CENTER);
		lab_win.setFont(font_body);
		lab_win.setForeground(Theme.Foreground);
		lab_win.setBounds(0,170,Theme.width/2,30);
		add(lab_win);
		
		JLabel lab_loseheader = new JLabel("Lose",JLabel.CENTER);
		lab_loseheader.setFont(font_header);
		lab_loseheader.setForeground(Theme.Foreground);
		lab_loseheader.setBounds(Theme.width/2,140,Theme.width/2,30);
		add(lab_loseheader);
		
		JLabel lab_lose = new JLabel("" + game.getCurrentplayer().getLose(),JLabel.CENTER);
		lab_lose.setFont(font_body);
		lab_lose.setForeground(Theme.Foreground);
		lab_lose.setBounds(Theme.width/2,170,Theme.width/2,30);
		add(lab_lose);
		
		JLabel lab_lastresulticon = new JLabel();
		lab_lastresulticon.setBounds((Theme.width/2)-35,120,70,70);
		
		//displaying result of last attempt
		if(game.getCurrentplayer().isIslastwon()) {
			lab_lastresulticon.setIcon(new ImageIcon("src\\resources\\icon_right.png"));
		}
		else{
			lab_lastresulticon.setIcon(new ImageIcon("src\\resources\\icon_wrong.png"));
		}
		add(lab_lastresulticon);
		
		JLabel lab_lastresult = new JLabel("",JLabel.CENTER);
		lab_lastresult.setBounds(0,190,Theme.width,30);
		lab_lastresult.setFont(font_header);
		lab_lastresult.setForeground(Theme.Foreground);
		if(game.getCurrentplayer().isIslastwon()) {
			lab_lastresult.setText("You Won :)");
		}
		else{
			lab_lastresult.setText("You Lose :(");
		}
		add(lab_lastresult);
		
		//generalized font for button
		Font font_buttons = new Font(Font.DIALOG,Font.BOLD,20);
		
		//buttons to continue exit and go to home
		JButton btn_exit = new JButton("<< Exit");
		btn_exit.setFont(font_buttons);
		btn_exit.setBounds(100,240,150,30);
		add(btn_exit);
		
		JButton btn_continue = new JButton("Continue >>");
		btn_continue.setFont(font_buttons);
		btn_continue.setBounds(400,240,150,30);
		add(btn_continue);
		
		JButton btn_gotohome = new JButton("Home ^");
		btn_gotohome.setFont(font_buttons);
		btn_gotohome.setBounds((Theme.width/2)-72,240,150,30);
		
		//auto pilot next attempt description
		JLabel lab_autopilotdes = new JLabel("Auto Pilot Mode Enabled",JLabel.CENTER);
		lab_autopilotdes.setBounds(0,Theme.height-60,Theme.width,30);
		lab_autopilotdes.setForeground(Theme.Foreground);
		if(game.getIsautopilot()) {
			//add auto pilot description only on auto pilot mode
			add(lab_autopilotdes);
		}
		
		//Components action and event listners
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//making return to home btn visible
				lab_lastresult.setVisible(false);
				lab_lastresulticon.setVisible(false);
				remove(btn_continue);
				remove(btn_exit);
				add(btn_gotohome);
			}
		});
		
		btn_continue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//continue to next game
				game.getCurrentplayer().setIsnewplayer(false);
				if(!game.getIsautopilot()) {
					//not an auto pilot return to home
					homelistner.returntohome(false);
				}
				else {
					//autopilot directly display the game panel
					String playerattemptlog = game.getCurrentplayer().getAutopilotattemptlog();
					if(playerattemptlog.length() >= 3) {
						String lastthreeattempts = playerattemptlog.substring(playerattemptlog.length()-3);
						System.out.println("Last three attempt" + lastthreeattempts);
						if(playerattemptlog.length()%3 == 0) {
							if(lastthreeattempts.equalsIgnoreCase("www")) {
								//last three attempts is win
								game.setNoofdigits(game.getNoofdigits() + 1);
							}
							else if(lastthreeattempts.equalsIgnoreCase("lll")) {
								//last three attempts is lose
								if(!(game.getNoofdigits() <= 1)) {
									//if the no of digits is greater thean 1
									game.setNoofdigits(game.getNoofdigits()-1);
								}
							}
						}
					}
					startlistner.startgame();
				}
			}
		});
		
		btn_gotohome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//return to home panel
				game.getCurrentplayer().setIsnewplayer(true);
				homelistner.returntohome(true);
			}
		});
	}

	/**
	 * add back drop image to panel
	 */
	@Override
    public void paintComponent(Graphics g) 
    {
		//Paint the panel with the back drop image
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("src\\resources\\backdrop.jpg" );
        g.drawImage(img.getImage(),0,0,Theme.width,Theme.height,null);
    }
}
