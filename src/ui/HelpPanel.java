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
import javax.swing.JScrollPane;

import entities.Theme;
import uilisteners.GameHomeListner;

/**
 * show help to the user
 * @author gokul
 *
 */
public class HelpPanel extends JPanel {

	/**
	 * consuctor to set the components on the panel 
	 * @param homelistner
	 */
	public HelpPanel(GameHomeListner homelistner) {
		
		Font font_help = new Font(Font.DIALOG,Font.BOLD,15);
		JLabel lab_helptext = new JLabel("<html><body  style=\"text-align: justify;\"><center>Memory Game</center>"
				+ "<br>Author: Gokul R"
				+ "<br>&emsp&emsp&emsp Kongu Engineering College"
				+ "<br><br>Developer Help: "
				+ "<br>Developed using Java Swing,Singlton object is used to energize the game flow ,Singlton<br> resource points to same memeory through the life time of the application the advantage<br> of using singloten object is it's ascessable from any UI part at any time."
				+ "<br>The communication between different UI components is achived using Listners<br> implemented using Interfaces, Here the Game begomes more agile and Changes on an <br>UI part can be under a single function,which can be modified on changing requirements."
				+ "<br>JFrame is the base of the application to which Panel are added depending upon<br>the operation"
				+ "<br>The Logic Part of the Game goes under the game logic package, Thread is implemented<br>in an seperate part and timer is updated from the thread.The generator logic to generate<br> random set of numbers also goes under this package"
				+ "<br>UI listners package also comprises of Thread listners to update and stop Timers."
				+ "<br>The Entities here id Game,Player,Theme"
				+ "<br>Game - The entire Game properties (singlton resource)"
				+ "<br>Player - The properties of the current Player (singlton resource)"
				+ "<br>Theme - The properties of base Jframe, the properties can be changed in single point<br>promoting Agility."
				+ "<br>Resources images are subjected to licensing and not commercially available used only<br>for developing purposes for this interview"
				+ "<br><br>For complete details on Methods and classes can generate JAVADOC from any IDE<br>available have also added inline docs to methods and classes.."
				+ "<br>"
				+ "<br>Package Structure<br>src"
				+ "<br>&emsp|"
				+ "<br>&emsp|_entities => entity Bean classes<br>"
				+ "&emsp|&emsp|_Game => singleton resource represent the entire game<br>"
				+ "&emsp|&emsp|_Player => singlton resource represent the player in game<br>"
				+ "&emsp|&emsp|_Theme => window properties<br>&emsp|<br>"
				+ "&emsp|_ui => UI part of the game<br>"
				+ "&emsp|&emsp|_Base => base frame to add panels<br>"
				+ "&emsp|&emsp|_GamePanel => Game panel with timer and digits displayed<br>"
				+ "&emsp|&emsp|_HelpPanel => Help panel to disply the help window<br>"
				+ "&emsp|&emsp|_PlayerInputPanel => Player input panel to get player name, noof digits and<br>&emsp&emsp&emsp secounds of display<br>"
				+ "&emsp|&emsp|_ResultPanel => Panel, Display the result of the Game<br>"
				+ "&emsp|&emsp|_Main => main of the application start of app<br>&emsp|<br>"
				+ "&emsp|_gamelogic => Timer logic and random number generator logic<br>"
				+ "&emsp|&emsp|_NumberGenerator => Logic to Generate the Random numbers<br>"
				+ "&emsp|&emsp|_TimerThread => timer implementation using thread<br>&emsp|<br>"
				+ "&emsp|_uilistners => UI listners to communicate between different UI and Backruning Thread<br>"
				+ "&emsp|&emsp|_GameExitListner => Listner to exit from Home<br>"
				+ "&emsp|&emsp|_GameHelpListner => Listner to open help panel<br>"
				+ "&emsp|&emsp|_GameHomeListner => Listner to add player input panel to frame<br>"
				+ "&emsp|&emsp|_GameResultListner => Listner to display result of the game<br>"
				+ "&emsp|&emsp|_GameStartListner => Listner to add game panel to frame<br>"
				+ "&emsp|&emsp|_StopTimerListnet => Listner to stop updating timer from thread<br>"
				+ "&emsp|&emsp|_UpdateTimerListnet => Listner to update timer from thread<br>&emsp|<br>"
				+ "&emsp|_resources"
				+ "</body></html>");
		lab_helptext.setFont(font_help);
		lab_helptext.setForeground(Theme.Foreground);
		
		JScrollPane scroll_help = new JScrollPane(lab_helptext,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_help.setBounds(0,0,Theme.width-5,Theme.height-120);
		scroll_help.setOpaque(false);
		scroll_help.getViewport().setOpaque(false);
		add(scroll_help);
		
		JButton btn_gotohome = new JButton("Home ^");
		btn_gotohome.setBounds(Theme.width/2-50,Theme.height-100,100,30);
		add(btn_gotohome);
		
		//components and buttons lisners
		btn_gotohome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
