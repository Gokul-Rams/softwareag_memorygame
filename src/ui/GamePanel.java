package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entities.Game;
import entities.Theme;
import gamelogic.NumberGenerator;
import gamelogic.TimerThread;
import uilisteners.GameHomeListner;
import uilisteners.GameResultListner;
import uilisteners.StopTimerListner;
import uilisteners.UpdateTimerListner;

/**
 * Game planel to display digits and get input from the user
 * @author gokul
 *
 */
public class GamePanel extends JPanel implements UpdateTimerListner,StopTimerListner{
	
	JLabel lab_timer;
	JLabel lab_digitstring;
	JTextField txt_useranswerField;
	GameHomeListner homelistner;
	GameResultListner resultlistner;
	String generatednumber;
	JButton btn_submit;
	TimerThread timer;
	JLabel lab_operationdes;
	/**
	 * Game panel constructor to add components to panel
	 * @param homelistner
	 * @param resultlistner
	 */
	public GamePanel(GameHomeListner homelistner,GameResultListner resultlistner) {
		
		//assiging the listners to local variables
		this.homelistner = homelistner;
		this.resultlistner = resultlistner;
		
		//setting up the panel
		setLayout(null);
		
		//gettting the singloten game resource
		Game game = Game.getGame();
		System.out.println("name : " + game.getCurrentplayer().getName());
		System.out.println("duration : " + game.getDuration());
		System.out.println("no of digits : " + game.getNoofdigits());
		System.out.println("is autopilot : " + game.getIsautopilot());
		
		//diaplay generated string
		Font font_digstring = new Font(Font.DIALOG, Font.BOLD,30);
		Font font_description = new Font(Font.DIALOG,Font.BOLD,20);
		
		generatednumber = NumberGenerator.generatenumber(game.getNoofdigits());
		
		lab_operationdes = new JLabel("Memorise The Digits",JLabel.CENTER);
		lab_operationdes.setFont(font_description);
		lab_operationdes.setForeground(Theme.Foreground);
		lab_operationdes.setBounds(0,110,Theme.width,30);
		add(lab_operationdes);
		
		lab_digitstring = new JLabel(generatednumber,JLabel.CENTER);
		lab_digitstring.setFont(font_digstring);
		lab_digitstring.setForeground(Theme.Foreground);
		lab_digitstring.setBounds(0,150,Theme.width,30);
		add(lab_digitstring);
		
		//display player name
		JLabel lab_playername = new JLabel(game.getCurrentplayer().getName());
		lab_playername.setForeground(Theme.Foreground);
		lab_playername.setFont(font_description);
		lab_playername.setBounds(10,10,300,40);
		add(lab_playername);
		
		//display timer label
		lab_timer = new JLabel();
		Font font_timer = new Font(Font.DIALOG,Font.BOLD,50);
		lab_timer.setForeground(Theme.Foreground);
		lab_timer.setFont(font_timer);
		lab_timer.setBounds(550,50,40,40);
		add(lab_timer);
		
		//txt input for getting digits input
		txt_useranswerField = new JTextField();
		txt_useranswerField.setHorizontalAlignment(JTextField.CENTER);
		Font font_answer = new Font(Font.DIALOG,Font.BOLD,30);
		txt_useranswerField.setFont(font_answer);
		txt_useranswerField.setBounds(30,150,Theme.width-60,30);
		
		Font font_buttons = new Font(Font.DIALOG,Font.BOLD,20);
		JButton btn_returnhome = new JButton("<< Home");
		btn_returnhome.setFont(font_buttons);
		btn_returnhome.setBounds(100,230,150,30);
		add(btn_returnhome);
		
		btn_submit = new JButton("Submit >>");
		btn_submit.setFont(font_buttons);
		btn_submit.setBounds(400,230,150,30);
		btn_submit.setEnabled(false);
		add(btn_submit);
		
		//auto pilot next attempt description
		JLabel lab_autopilotdes = new JLabel("Auto Pilot Mode Enabled",JLabel.CENTER);
		lab_autopilotdes.setBounds(0,Theme.height-60,Theme.width,30);
		lab_autopilotdes.setForeground(Theme.Foreground);
		if(game.getIsautopilot()) {
			//add auto pilot description only on auto pilot mode
			add(lab_autopilotdes);
		}
		
		//start timer from a new Thread created by the Timer Thread class at game logic package
		timer = new TimerThread(game.getDuration(),this,this);
		timer.start();
		
		//Component and buttons Action Listners
		btn_submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Game Results..
				game.getCurrentplayer().setAttempts(
						game.getCurrentplayer().getAttempts()+1
						);
				
				String player_answer = txt_useranswerField.getText().toString().trim();
				if(player_answer.equals(generatednumber)) {
					//Player wins the game
					game.getCurrentplayer().setIslastwon(true);
					int win = game.getCurrentplayer().getWin();
					game.getCurrentplayer().setWin(++win);
					game.getCurrentplayer().pushlastattempt(true);
				}
				else {
					//Player lose the game
					game.getCurrentplayer().setIslastwon(false);
					int lose = game.getCurrentplayer().getLose();
					game.getCurrentplayer().setLose(++lose);
					game.getCurrentplayer().pushlastattempt(false);
				}
				resultlistner.showgameresult();
				
			}
		});
		
		btn_returnhome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				game.getCurrentplayer().setIsnewplayer(true);
				homelistner.returntohome(true);
			}
		});
	}
	
	/**
	 * lisner implementation to update the timer from the thread
	 */
	@Override
	public void updatetimer(int currentsec) {
		if(currentsec <= 2) {
			lab_timer.setForeground(Color.RED);
		}
		lab_timer.setText("" + currentsec);
	}

	/**
	 * lisner implementation to stop the timer from the thread
	 */
	@Override
	public void stoptimer(boolean iscomplete) {
		lab_operationdes.setText("Enter the Digits Displayed..");
		lab_digitstring.setVisible(false);
		add(txt_useranswerField);
		btn_submit.setEnabled(true);
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
