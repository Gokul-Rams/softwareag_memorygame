package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.temporal.JulianFields;
import java.util.Scanner;

import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;

import entities.Game;
import entities.Theme;
import uilisteners.GameExitListner;
import uilisteners.GameHelpListner;
import uilisteners.GameHomeListner;
import uilisteners.GameStartListner;

/**
 * get Player inputs from the player
 * @author gokul
 *
 */
public class PlayerInputPanel extends JPanel{
	
	GameStartListner startListner;
	GameExitListner exitListner;
	public PlayerInputPanel(GameStartListner startlistner,GameExitListner exitListner,GameHelpListner helplistner) {
		
		//setting up the panel
		setLayout(null);
		
		//gettting the singloten game resource
		Game game = Game.getGame();
		
		//assiging the listners to local variables
		this.startListner = startlistner;
		this.exitListner = exitListner;
		
		//generalized font for JLabel
		Font font_playerinputpanel = new Font(Font.DIALOG,Font.BOLD,15);
		
		//user imput components
		JLabel lab_name = new JLabel("Enter the Name");
		lab_name.setForeground(Theme.Foreground);
		JTextField txt_name = new JTextField();
		
		JLabel lab_noofdigits = new JLabel("Enter the No of Digits");
		lab_noofdigits.setForeground(Theme.Foreground);
		SpinnerModel mod_digits = new SpinnerNumberModel(1,1,100,1);
		JSpinner spn_noofdigits = new JSpinner(mod_digits);
		JTextField txt_noofdigits = new JTextField();
		
		JLabel lab_duration = new JLabel("Enter the Duration");
		lab_duration.setForeground(Theme.Foreground);
		SpinnerModel mod_duration = new SpinnerNumberModel(3,3,60,1);
		JSpinner spn_duration = new JSpinner(mod_duration);
		JTextField txt_duration = new JTextField();
		
		lab_name.setBounds(30,50,150,30);
		add(lab_name);
		txt_name.setBounds(155,50,200,30);
		add(txt_name);
		
		lab_noofdigits.setBounds(30,90,150,30);
		add(lab_noofdigits);
		txt_noofdigits.setBounds(155,90,200,30);
		add(txt_noofdigits);
		
		lab_duration.setBounds(30,130,150,30);
		add(lab_duration);
		txt_duration.setBounds(new Rectangle(155, 130, 200, 30));
		add(txt_duration);
		
		//auto pilot components
		JLabel lab_autopilot = new JLabel("Auto Pilot",JLabel.CENTER);
		lab_autopilot.setBounds(355,50,Theme.width-355,30);
		lab_autopilot.setForeground(Theme.Foreground);
		add(lab_autopilot);
		
		//auto pilot help text
		JLabel lab_autopilotdes = new JLabel("<html>Auto Pilot mode:<br>The number of digits decrease by one on three contionus lose<br>the number of digits increase by one on the contionus win");
		lab_autopilotdes.setFont(font_playerinputpanel);
		lab_autopilotdes.setBounds(370,130,Theme.width-385,150);
		lab_autopilotdes.setForeground(Theme.Foreground);
		add(lab_autopilotdes);
		
		JToggleButton btn_autopilot = new JToggleButton();
		btn_autopilot.setSelected(false);
		btn_autopilot.setBounds((Theme.width-355/2)-8,110,64,30);
		btn_autopilot.setSelectedIcon(new ImageIcon("src\\resources\\switch-on.png"));
		btn_autopilot.setIcon(new ImageIcon("src\\resources\\switch-off.png"));
		add(btn_autopilot);
		
		//Game operation Buttons 
		JButton btn_start = new JButton("Start Game");
		btn_start.setBounds(225,200,100,30);
		add(btn_start);
		
		JButton btn_exit = new JButton("Exit Game");
		btn_exit.setBounds(55, 200, 100, 30);
		add(btn_exit);
		
		JLabel lab_nextattemptdes = new JLabel("Player name and auto pilot mode cannot be changed on next attempt",JLabel.CENTER);
		lab_nextattemptdes.setForeground(Theme.Foreground);
		lab_nextattemptdes.setBounds(0,0,Theme.width,40);
		
		txt_name.setText(game.getCurrentplayer().getName());
		txt_duration.setText("" + game.getDuration());
		txt_noofdigits.setText("" + game.getNoofdigits());
		
		if(!game.getCurrentplayer().isIsnewplayer()) {
			//make name unchangable on new interation of a game
			txt_name.setText(txt_name.getText() + "(Unchangable)");
			txt_name.setEditable(false);
			//disable autopilot on new interation of a game
			btn_autopilot.setEnabled(false);
			add(lab_nextattemptdes);
		}
		
		//help options..
		JLabel lab_helpdes = new JLabel("<html>Get complete help from here <u> Help!.. </u></html>");
		lab_helpdes.setFont(font_playerinputpanel);
		lab_helpdes.setBounds(30,Theme.height-120,Theme.width,30);
		lab_helpdes.setForeground(Theme.Foreground);
		add(lab_helpdes);
		
		//Component and buttons Action Listners
		btn_autopilot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//enable or disble auto pilot
				if(btn_autopilot.isSelected()) {
					game.setIsautopilot(true);
				}
				else {
					game.setIsautopilot(false);
				}
			}
		});
		
		btn_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//start game
				int noofdigits = Integer.parseInt(txt_noofdigits.getText().trim().toString());
				int duration = Integer.parseInt(txt_duration.getText().trim().toString());
				if(game.getCurrentplayer().isIsnewplayer()) {
					String playername = txt_name.getText().trim().toString();
					game.getCurrentplayer().setName(playername);
					game.getCurrentplayer().setAutopilotattemptlog("");
				}
				game.setNoofdigits(noofdigits);
				game.setDuration(duration);
				
				startlistner.startgame();
				
			}
		});
		
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//end game and exit window
				exitListner.endgame();
			}
		});
		
		lab_helpdes.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				helplistner.showgamehelp();
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