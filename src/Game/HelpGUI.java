package Game;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * A class to create a help GUI for the virtual pet game.
 * @author Eiran
 *
 */
public class HelpGUI {

	private JFrame frame;
	private JPanel introPanel;
	private JPanel GUIPanel;
	private JPanel customisePlayerPanel;
	private JPanel customisePetPanel;
	private JPanel gamePanel;
	private JPanel storePanel;
	private JPanel scorePanel;
	private Image firstScreen = new ImageIcon(this.getClass().getResource("/FirstScreenHelp.PNG")).getImage();
	private Image secondScreen = new ImageIcon(this.getClass().getResource("/SecondPage.PNG")).getImage();
	private Image thirdScreen = new ImageIcon(this.getClass().getResource("/ThirdPage.PNG")).getImage();
	private Image fourthScreen = new ImageIcon(this.getClass().getResource("/FourthPage.PNG")).getImage();
	private Image fifthScreen = new ImageIcon(this.getClass().getResource("/FifthPage.PNG")).getImage();
	private Image sixthScreen = new ImageIcon(this.getClass().getResource("/SixthPage.PNG")).getImage();

	/**
	 * Create the application.
	 * @param x -- current x coordinate of the parent window
	 * @param y -- current y coordinate of the parent window.
	 */
	public HelpGUI(int x, int y) {
		frame = new JFrame();
		frame.setBounds(x, y, 549, 519);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setTitle("Help");
		initialize();
		showPage(0);
		frame.setVisible(true);
	}
	/**
	 * Method to call from buttons to show a certain page.
	 */
	private void showPage(int page){
		
	switch (page){
	case 0: introPanel.setVisible(true);
		frame.setSize(365, 372);
		try {
			GUIPanel.setVisible(false);
		} catch(NullPointerException e){
			/*ignore*/
		}
			break;
			
	case 1: introPanel.setVisible(false);
			frame.setSize(586, 490);
			try {
			GUIPanel.setVisible(true);
			} catch (NullPointerException e){
				initializeFirstPage();
			}
			try {
				customisePlayerPanel.setVisible(false);
			} catch (NullPointerException e){
				/*ignore*/
			}
			
			break;
	case 2: GUIPanel.setVisible(false);
			frame.setSize(653, 492);
			try{
				customisePlayerPanel.setVisible(true);
			} catch (NullPointerException e) {
				initializeSecondPage();
			}
			
			try {
				customisePetPanel.setVisible(false);
			} catch (NullPointerException e){
				/*ignore*/
			}
			break;
			
	case 3: customisePlayerPanel.setVisible(false);
			frame.setSize(696, 518);
			try {
				customisePetPanel.setVisible(true);
			} catch (NullPointerException e){
				initializeThirdPage();
			}
			
			try {
				gamePanel.setVisible(false);
			} catch (NullPointerException e){
				/*ignore*/
			}
			
			break;
			
	case 4: customisePetPanel.setVisible(false);
			frame.setSize(735, 667);
			try {
				gamePanel.setVisible(true);
			} catch (NullPointerException e){
				initializeFourthPage();
			}
			
			try {
				storePanel.setVisible(false);
			} catch (NullPointerException e){
				/*ignore*/
			}
			break;
	
	case 5: gamePanel.setVisible(false);
			frame.setSize(668, 503);
			try {
				storePanel.setVisible(true);
			} catch (NullPointerException e) {
				initializeFifthPage();
			}
			
			try {
				scorePanel.setVisible(false);
			} catch (NullPointerException e){
				/*ignore*/
			}
			break;
	case 6: storePanel.setVisible(false);
			frame.setSize(549, 519);
			try {
				scorePanel.setVisible(true);
			} catch (NullPointerException e){
				initializeSixthPage();
			}
		}
	

	}
	


	
	/**
	 * Initialize the welcome screen of the Help GUI.
	 */
	private void initialize() {

		introPanel = new JPanel();
		introPanel.setFont(new Font("Verdana", Font.PLAIN, 11));
		frame.getContentPane().add(introPanel, BorderLayout.CENTER);
		introPanel.setLayout(null);
		
		JButton btnNextPage = new JButton("Next");
		btnNextPage.setBounds(223, 298, 112, 23);
		btnNextPage.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNextPage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(1);
			}
		});
		introPanel.add(btnNextPage);
		
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Verdana", Font.BOLD, 19));
		lblWelcome.setBounds(12, 12, 325, 36);
		introPanel.add(lblWelcome);
		
		JLabel lblInstruction1 = new JLabel("Your objective is to keep your virtual pets alive");
		lblInstruction1.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblInstruction1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction1.setBounds(2, 60, 345, 16);
		introPanel.add(lblInstruction1);
		
		JLabel lblInstruction2 = new JLabel("and happy. Compete against other players to");
		lblInstruction2.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction2.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblInstruction2.setBounds(2, 74, 345, 16);
		introPanel.add(lblInstruction2);
		
		JLabel lblInstruction3 = new JLabel("gain the most points by the end of the selected days.");
		lblInstruction3.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction3.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblInstruction3.setBounds(2, 88, 345, 16);
		introPanel.add(lblInstruction3);
		
		JLabel lblInstruction4 = new JLabel("This is done by interacting with the pet in many");
		lblInstruction4.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction4.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblInstruction4.setBounds(2, 135, 345, 16);
		introPanel.add(lblInstruction4);
		
		JLabel lblInstruction5 = new JLabel("ways such as feeding it, playing with it etc.");
		lblInstruction5.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction5.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblInstruction5.setBounds(2, 150, 345, 16);
		introPanel.add(lblInstruction5);
		
		JLabel lblInstruction6 = new JLabel("But beware, as if any of your pets' stats fall to 0,");
		lblInstruction6.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction6.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblInstruction6.setBounds(2, 183, 345, 16);
		introPanel.add(lblInstruction6);
		
		JLabel lblInstruction7 = new JLabel("it will die. You may revive it. But only once.");
		lblInstruction7.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction7.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblInstruction7.setBounds(2, 198, 345, 16);
		introPanel.add(lblInstruction7);
		

	}
	
	/**
	 * Method to initialize the first page of the help.
	 */
	private void initializeFirstPage(){
		GUIPanel = new JPanel();
		GUIPanel.setFont(new Font("Verdana", Font.PLAIN, 11));
		frame.getContentPane().add(GUIPanel, BorderLayout.CENTER);
		GUIPanel.setLayout(null);
		
		JLabel lblSetUpScreen = new JLabel("Set up screen");
		lblSetUpScreen.setFont(new Font("Verdana", Font.BOLD, 19));
		lblSetUpScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetUpScreen.setBounds(124, 12, 321, 24);
		GUIPanel.add(lblSetUpScreen);
		
		JLabel screenPic = new JLabel("");
		screenPic.setBounds(12, 44, 546, 310);
		screenPic.setIcon(new ImageIcon(firstScreen));
		GUIPanel.add(screenPic);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNext.setBounds(460, 413, 98, 26);
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(2);
			}
		});
		GUIPanel.add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnPrevious.setBounds(12, 413, 98, 26);
		btnPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(0);
			}
		});
		GUIPanel.add(btnPrevious);
		
		JLabel lblInstructionLabel = new JLabel("<html><p>Enter the number of days you wish to play for and the number of players you would like to play with.</p>\r\n</html>");
		lblInstructionLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblInstructionLabel.setBounds(12, 366, 546, 35);
		GUIPanel.add(lblInstructionLabel);
		
	}
	
	/**
	 * Initialize the second page of the help GUI
	 */
	public void initializeSecondPage(){
		customisePlayerPanel = new JPanel();
		customisePlayerPanel.setFont(new Font("Verdana", Font.PLAIN, 11));
		frame.getContentPane().add(customisePlayerPanel, BorderLayout.CENTER);
		customisePlayerPanel.setLayout(null);
		
		JLabel lblSetUpScreen = new JLabel("Customise Players");
		lblSetUpScreen.setFont(new Font("Verdana", Font.BOLD, 19));
		lblSetUpScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetUpScreen.setBounds(158, 12, 321, 24);
		customisePlayerPanel.add(lblSetUpScreen);
		
		JLabel screenPic = new JLabel("");
		screenPic.setBounds(12, 44, 610, 310);
		screenPic.setIcon(new ImageIcon(secondScreen));
		customisePlayerPanel.add(screenPic);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNext.setBounds(527, 415, 98, 26);
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(3);
			}
		});
		customisePlayerPanel.add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnPrevious.setBounds(12, 415, 98, 26);
		btnPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(1);
			}
		});
		customisePlayerPanel.add(btnPrevious);
		
		JLabel lblInstructionLabel = new JLabel("<html>E<>nter the names of the players and click the next button to begin. Note that two players may not share the same name. </p></html>");
		lblInstructionLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblInstructionLabel.setBounds(12, 352, 610, 37);
		customisePlayerPanel.add(lblInstructionLabel);
	}
	
	/**
	 * Initialize the third page of the help GUI
	 */
	private void initializeThirdPage(){
		customisePetPanel = new JPanel();
		customisePetPanel.setFont(new Font("Verdana", Font.PLAIN, 11));
		frame.getContentPane().add(customisePetPanel, BorderLayout.CENTER);
		customisePetPanel.setLayout(null);
		
		JLabel lblSetUpScreen = new JLabel("Customise Pets");
		lblSetUpScreen.setFont(new Font("Verdana", Font.BOLD, 19));
		lblSetUpScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetUpScreen.setBounds(124, 12, 321, 24);
		customisePetPanel.add(lblSetUpScreen);
		
		JLabel screenPic = new JLabel("");
		screenPic.setBounds(12, 44, 659, 334);
		screenPic.setIcon(new ImageIcon(thirdScreen));
		customisePetPanel.add(screenPic);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNext.setBounds(573, 441, 98, 26);
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(4);
			}
		});
		customisePetPanel.add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnPrevious.setBounds(12, 441, 98, 26);
		btnPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(2);
			}
		});
		customisePetPanel.add(btnPrevious);
		
		JLabel lblInstructionLabel = new JLabel("<html><p>Add up to three pets per player, and click on their icons to cancel selection. By clicking add pet, a dialog box will appear prompting you for a name for the pet. Pets cannot share the same name or be empty.</p></html>");
		lblInstructionLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblInstructionLabel.setBounds(12, 390, 656, 39);
		customisePetPanel.add(lblInstructionLabel);
	}
	
	/**
	 * Initialize the fourth page of the help GUI
	 */
	private void initializeFourthPage(){
		gamePanel = new JPanel();
		gamePanel.setFont(new Font("Verdana", Font.PLAIN, 11));
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(null);
		
		JLabel lblSetUpScreen = new JLabel("Game Screen");
		lblSetUpScreen.setFont(new Font("Verdana", Font.BOLD, 19));
		lblSetUpScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetUpScreen.setBounds(199, 12, 321, 24);
		gamePanel.add(lblSetUpScreen);
		
		JLabel screenPic = new JLabel("");
		screenPic.setBounds(12, 44, 695, 334);
		screenPic.setIcon(new ImageIcon(fourthScreen));
		gamePanel.add(screenPic);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNext.setBounds(609, 590, 98, 26);
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(5);
			}
		});
		gamePanel.add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnPrevious.setBounds(12, 590, 98, 26);
		btnPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(3);
			}
		});
		gamePanel.add(btnPrevious);
		
		JLabel lblInstructonLabel = new JLabel("<html><p>Here you can interact with your pet. You can 5 basic actions up to a maximum of 2 times per pet, per day.</p>\r\n<p><strong>Feed - </strong>Feeds the pet. Feeding the pet it&#39;s favourite food gives you a bonus multiplier on the amount the food restores.</p>\r\n<p><strong>Play - </strong>Play with the pet. Giving the pet its favourite toy will give you a bonus multiplier on the amount the toy restores.</p>\r\n<p><strong>Sleep -</strong> Puts the pet to sleep. Making it hungry and regaining energy.</p>\r\n<p><strong>Use Toilet -</strong> Take the pet to the toilet. Reducing the pet&#39;s need for toilet to 0. Cannot be used if it's need for toilet is 0.</p>\r\n<p><strong>Heal -</strong> Heal the pet with medicine. If the pet is sick, then it will be healed.</p>\r\n<p><strong>Revive -</strong> Revives the pet if it is dead. Restarting it at lower stats.</p>\r\n<p><strong>Discipline -</strong> Disciplines the pet if it is misbehaving. Lowers the pet&#39;s happiness. Does nothing if the pet is not misbehaving.</p></html>\r\n");
		lblInstructonLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblInstructonLabel.setBounds(12, 390, 695, 198);
		gamePanel.add(lblInstructonLabel);
	}
	
	/**
	 * Initialize the fifth page of the help GUI
	 */
	private void initializeFifthPage(){
		storePanel = new JPanel();
		storePanel.setFont(new Font("Verdana", Font.PLAIN, 11));
		frame.getContentPane().add(storePanel, BorderLayout.CENTER);
		storePanel.setLayout(null);
		
		JLabel lblSetUpScreen = new JLabel("Store Screen");
		lblSetUpScreen.setFont(new Font("Verdana", Font.BOLD, 19));
		lblSetUpScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetUpScreen.setBounds(165, 12, 321, 24);
		storePanel.add(lblSetUpScreen);
		
		JLabel screenPic = new JLabel("");
		screenPic.setBounds(12, 44, 695, 316);
		screenPic.setIcon(new ImageIcon(fifthScreen));
		storePanel.add(screenPic);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNext.setBounds(542, 434, 98, 26);
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(6);
			}
		});
		storePanel.add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnPrevious.setBounds(12, 434, 98, 26);
		btnPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(4);
			}
		});
		storePanel.add(btnPrevious);
		
		JLabel lblInstructionLabel = new JLabel("<html><p> Here you can buy food and toys for your beloved pets. The description box tells you how many of an item you have. Note that you may only own one of each toy at a time. The EpiPen, much like in real life, is unaffordable.</p></html>");
		lblInstructionLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblInstructionLabel.setBounds(12, 362, 628, 60);
		storePanel.add(lblInstructionLabel);
	}
	
	/**
	 * Initializes the sixth page of the help GUI.
	 */
	private void initializeSixthPage(){
		scorePanel = new JPanel();
		scorePanel.setFont(new Font("Verdana", Font.PLAIN, 11));
		frame.getContentPane().add(scorePanel, BorderLayout.CENTER);
		scorePanel.setLayout(null);
		
		JLabel lblSetUpScreen = new JLabel("Scoreboard screen");
		lblSetUpScreen.setFont(new Font("Verdana", Font.BOLD, 19));
		lblSetUpScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetUpScreen.setBounds(106, 12, 321, 24);
		scorePanel.add(lblSetUpScreen);
		
		JLabel screenPic = new JLabel("");
		screenPic.setBounds(12, 44, 695, 320);
		screenPic.setIcon(new ImageIcon(sixthScreen));
		scorePanel.add(screenPic);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnPrevious.setBounds(12, 442, 98, 26);
		btnPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showPage(5);
			}
		});
		scorePanel.add(btnPrevious);
		
		JLabel lblInstructionLabel = new JLabel("<html><p>This screen reveals the final scores between the players. The highest score wins! It is calculated based on how your pets happy your pets are, whether they have died throughout the game etc.</p></html>");
		lblInstructionLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblInstructionLabel.setBounds(12, 362, 509, 50);
		scorePanel.add(lblInstructionLabel);
	}
}
