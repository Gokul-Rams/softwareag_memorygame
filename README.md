The source of the Project found in src folder

Author: Gokul R
	Kongu Engineering College

Clone the project from git hub repo
	https://github.com/Gokul-Rams/softwareag_memorygame
Developer Help:

Developed using Java Swing,Singlton object is used to energize the game flow ,Singlton
resource points to same memeory through the life time of the application the advantage
of using singleton object is it's ascessable from any UI part at any time.
The communication between different UI components is achived using Listners
implemented using Interfaces, Here the Game becomes more agile and Changes on an
UI part can be under a single function,which can be modified on changing requirements.
JFrame is the base of the application to which Panel are added depending upon
the operation
The Logic Part of the Game goes under the game logic package, Thread is implemented
in an seperate part and timer is updated from the thread.The generator logic to generate
random set of numbers also goes under this package
UI listners package also comprises of Thread listners to update and stop Timers.
The Entities here id Game,Player,Theme
Game - The entire Game properties (singlton resource)
Player - The properties of the current Player (singlton resource)
Theme - The properties of base Jframe, the properties can be changed in single point
promoting Agility.
Resources images are subjected to licensing and not commercially available used only
for developing purposes for this interview

For complete details on Methods and classes can generate JAVADOC from any IDE
available,have also added inline docs to methods and classes..

Package Structure
	src
	|
	|_entities => entity Bean classes
	|	|_Game => singleton resource represent the entire game
	|	|_Player => singlton resource represent the player in game
	|	|_Theme => window properties
	|
	|_ui => UI part of the game
	|	|_Base => base frame to add panels
	|	|_GamePanel => Game panel with timer and digits displayed
	|	|_HelpPanel => Help panel to disply the help window
	|	|_PlayerInputPanel => Player input panel to get player name, noof digits and
		 secounds of display
	|	|_ResultPanel => Panel, Display the result of the Game
	|	|_Main => main of the application start of app
	|
	|_gamelogic => Timer logic and random number generator logic
	|	|_NumberGenerator => Logic to Generate the Random numbers
	|	|_TimerThread => timer implementation using thread
	|
	|_uilistners => UI listners to communicate between different UI and Backruning Thread
	|	|_GameExitListner => Listner to exit from Home
	|	|_GameHelpListner => Listner to open help panel
	|	|_GameHomeListner => Listner to add player input panel to frame
	|	|_GameResultListner => Listner to display result of the game
	|	|_GameStartListner => Listner to add game panel to frame
	|	|_StopTimerListnet => Listner to stop updating timer from thread
	|	|_UpdateTimerListnet => Listner to update timer from thread
	|
	|_resources
