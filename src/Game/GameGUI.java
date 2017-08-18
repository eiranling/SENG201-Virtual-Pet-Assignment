package Game;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import food.Fish;
import food.Fly;
import food.Food;
import food.Fruit;
import food.Rat;
import food.Seeds;
import food.Steak;
import medicine.Bandage;
import medicine.EpiPen;
import medicine.Medicine;
import medicine.PetMedicine;
import pets.Bird;
import pets.Cat;
import pets.Dog;
import pets.Frog;
import pets.Hamster;
import pets.Pets;
import pets.Snake;
import toy.Bone;
import toy.HamBall;
import toy.Pool;
import toy.Slide;
import toy.Toy;
import toy.Tube;
import toy.WoolBall;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * Part of the MVC model, this class consists of the viewer and the controller.
 * 
 * Class constructs the GUI, consists of multiple JPanels for setting up the
 * game, setting up the players, playing the game and showing the score board.
 * Each JPanel will be hidden and shown as needed by the controller.
 * 
 * The controller has been embedded into the viewer, as the GUI is composed of
 * various components the user can interact with. Such components have been
 * associated with an action which determines an action being performed.
 * Collectively, this makes up the controller.
 * 
 * Various methods have been created for repetitive tasks, such as updating the
 * label which presents the player with messages relaying the actions that has
 * been performed.
 * 
 * 
 * To run the program, this class is to be compiled and run.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class GameGUI {

	private JFrame frame;
	private JPanel gameSetUpPanel;
	private JPanel playerSetUpPanel;
	private JPanel midGamePanel;
	private JPanel petSetUpPanel;
	private JPanel scoreBoardPanel;

	// The models that are part of the MVC model
	GameModel gameModel = new GameModel();

	// Instantiate all the images that will be used throughout the GUI. Images
	// are scaled later in the code when
	// labels are being set an icon; since there are many labels with varying
	// sizes.
	private Image noPetSelectedPic = new ImageIcon(this.getClass().getResource("/noPetSelected.png")).getImage();
	private Image UCLogo = new ImageIcon(this.getClass().getResource("/UCLogo.png")).getImage();
	private Image birdPic = new ImageIcon(this.getClass().getResource("/birdPic.png")).getImage();
	private Image catPic = new ImageIcon(this.getClass().getResource("/catPic.png")).getImage();
	private Image dogPic = new ImageIcon(this.getClass().getResource("/dogPic.png")).getImage();
	private Image frogPic = new ImageIcon(this.getClass().getResource("/frogPic.png")).getImage();
	private Image hamsterPic = new ImageIcon(this.getClass().getResource("/hamsterPic.png")).getImage();
	private Image snakePic = new ImageIcon(this.getClass().getResource("/snakePic.png")).getImage();
	private Image bkgdPic = new ImageIcon(this.getClass().getResource("/Grass.jpg")).getImage();
	private Image seedsPic = new ImageIcon(this.getClass().getResource("/seedsPic.png")).getImage();
	private Image fishPic = new ImageIcon(this.getClass().getResource("/fishPic.png")).getImage();
	private Image flyPic = new ImageIcon(this.getClass().getResource("/flyPic.png")).getImage();
	private Image fruitPic = new ImageIcon(this.getClass().getResource("/fruitPic.png")).getImage();
	private Image ratPic = new ImageIcon(this.getClass().getResource("/ratPic.png")).getImage();
	private Image steakPic = new ImageIcon(this.getClass().getResource("/steakPic.png")).getImage();
	private Image bandagePic = new ImageIcon(this.getClass().getResource("/bandagePic.png")).getImage();
	private Image epiPenPic = new ImageIcon(this.getClass().getResource("/epiPen.png")).getImage();
	private Image petMedicinePic = new ImageIcon(this.getClass().getResource("/petMedicine.png")).getImage();
	private Image bonePic = new ImageIcon(this.getClass().getResource("/bonePic.png")).getImage();
	private Image hamBallPic = new ImageIcon(this.getClass().getResource("/hamBallPic.png")).getImage();
	private Image poolPic = new ImageIcon(this.getClass().getResource("/poolPic.png")).getImage();
	private Image slidePic = new ImageIcon(this.getClass().getResource("/slidePic.png")).getImage();
	private Image tubePic = new ImageIcon(this.getClass().getResource("/tubePic.png")).getImage();
	private Image woolBallPic = new ImageIcon(this.getClass().getResource("/woolBallPic.png")).getImage();
	private Image greenBkgd = new ImageIcon(this.getClass().getResource("/GreenBkgd.jpg")).getImage();

	// Variables used in initialisePetSetUp
	private String selectedPetToAdd = "none"; // If value is still "none" then
												// player has not selected any
												// pets

	// Variables used in initialiseMidGame
	private Pets selectedPetToInteract = null;
	// Used for when the player is shopping and selects an item.
	private Toy selectedToy = null;
	private Food selectedFood = null;
	private Medicine selectedMedicine = null;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            There should be no inputs entered into the input stream. Game
	 *            does not need it to run.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI window = new GameGUI();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor which will call various methods to create the GUI. Each
	 * method call will consist of a JPanel which is added to the JFrame, which
	 * is instantiated in this constructor.
	 */
	public GameGUI() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBackground(new Color(0, 0, 0));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tomagatchi Game");
		frame.setSize(600, 600);
		frame.setFont(new Font("Verdana", Font.PLAIN, 12));

		initializeGameSetUp();
		showGameSetUp();
	}

	/**
	 * Method called to hide all JPanels except the gameSetUpPanel, hence player
	 * can only interact with the panel which asks for user the number of days
	 * and players.
	 */
	private void showGameSetUp() {
		frame.setSize(495, 400);
		gameSetUpPanel.setVisible(true);
		try {
			midGamePanel.setVisible(false);
			scoreBoardPanel.setVisible(false);
			playerSetUpPanel.setVisible(false);
		} catch (Exception NullPointerException) {
			/* ignore */
		}
	}

	/**
	 * Method called to hide all JPanels except the playerSetUpPanel, hence the
	 * players can only interact with the panel which asks for the names of the
	 * players.
	 */
	private void showPlayerSetUp() {
		frame.setSize(485, 330);
		gameSetUpPanel.setVisible(false);
		try {
			petSetUpPanel.setVisible(false);
		} catch (Exception NullPointerException) {
			/* ignore */
		}
		playerSetUpPanel.setVisible(true);

	}

	/**
	 * Method called to hide all JPanels except petSetUpPanel, hence the player
	 * can only interact with the panel which asks them for their name and have
	 * them choose the pets they want to play with.
	 */
	private void showPetSetUp() {
		frame.setSize(495, 400);
		try {
			gameSetUpPanel.setVisible(false);
			playerSetUpPanel.setVisible(false);
			petSetUpPanel.setVisible(true);
		} catch (Exception NullPointerException) {
			/* ignore */
		}

	}

	/**
	 * Method called to hide all JPanels except midGamePanel, hence the player
	 * can only play the game.
	 */
	private void showMidGamePanel() {
		frame.setSize(600, 400);
		try {
			gameSetUpPanel.setVisible(false);
			playerSetUpPanel.setVisible(false);
			petSetUpPanel.setVisible(false);
			midGamePanel.setVisible(true);
		} catch (Exception NullPointerException) {
			/* ignore */
		}
	}

	/**
	 * Method called to hide all JPanels except scoreBoardPanel, hence can only
	 * interact with the score board.
	 */
	private void showScoreBoardPanel() {
		frame.setSize(485, 340);
		try {
			gameSetUpPanel.setVisible(false);
			playerSetUpPanel.setVisible(false);
			scoreBoardPanel.setVisible(true);
		} catch (Exception NullPointerException) {
			/* ignore */
		}
	}

	/**
	 * Construct the GUI the player will use to select the pets they want to
	 * play with and enter a name for their pet. This will be called multiple
	 * times, once for each player in the game.
	 */
	private void initializePetSetUp(int playerNum) {
		// Local Variable to get the dimension of the label displaying a picture
		// of the pet
		int picWidth = 110; // Dimension to display picture to species
		int picHeight = picWidth;
		int buttonWidth = 65;
		int buttonHeight = buttonWidth;
		int maxNumPets = 3;
		// Pets 'added' by clicking the add button after choosing a pet will be
		// passed
		// as parameters into the Player constructor once the 'Done' button is
		// pressed
		ArrayList<Pets> chosenPets = new ArrayList<Pets>();

		String birdDescription = new Bird("Demo").getDescription();
		String catDescription = new Cat("Demo").getDescription();
		String dogDescription = new Dog("Demo").getDescription();
		String frogDescription = new Frog("Demo").getDescription();
		String hamsterDescription = new Hamster("Demo").getDescription();
		String snakeDescription = new Snake("Demo").getDescription();

		// Create the components for playerSetUpPanel
		petSetUpPanel = new JPanel();
		petSetUpPanel.setFont(new Font("Verdana", Font.PLAIN, 11));
		petSetUpPanel.setBounds(0, 0, 479, 371);
		frame.getContentPane().add(petSetUpPanel);
		petSetUpPanel.setLayout(null);
		petSetUpPanel.revalidate();
		petSetUpPanel.repaint();

		JLabel lblDisplayPlayerName = new JLabel(gameModel.getPlayerName(gameModel.getPlayers().get(playerNum)));
		lblDisplayPlayerName.setBounds(135, 6, 230, 33);
		lblDisplayPlayerName.setFont(new Font("Verdana", Font.PLAIN, 21));
		lblDisplayPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		petSetUpPanel.add(lblDisplayPlayerName);

		JLabel pet_picture = new JLabel("");
		pet_picture.setHorizontalAlignment(SwingConstants.CENTER);
		pet_picture.setBounds(240, 39, picWidth, picHeight);
		petSetUpPanel.add(pet_picture);
		pet_picture.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(picWidth, picHeight, 1)));

		JTextArea petDescription = new JTextArea("No pet has been selected yet.");
		petDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		petDescription.setBounds(143, 163, 323, 110);
		petDescription.setLineWrap(true);
		petDescription.setWrapStyleWord(true);
		petDescription.setEditable(false);
		petSetUpPanel.add(petDescription);

		JRadioButton rdbtnBird = new JRadioButton("Bird");
		rdbtnBird.setOpaque(false);
		rdbtnBird.setFont(new Font("Verdana", Font.BOLD, 12));
		rdbtnBird.setBounds(20, 72, 109, 23);
		rdbtnBird.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pet_picture.setIcon(new ImageIcon(birdPic.getScaledInstance(picWidth, picHeight, 1)));
				selectedPetToAdd = "Bird";
				petDescription.setText(birdDescription);
			}
		});
		petSetUpPanel.add(rdbtnBird);

		JRadioButton rdbtnCat = new JRadioButton("Cat");
		rdbtnCat.setOpaque(false);
		rdbtnCat.setFont(new Font("Verdana", Font.BOLD, 12));
		rdbtnCat.setBounds(20, 99, 109, 23);
		rdbtnCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pet_picture.setIcon(new ImageIcon(catPic.getScaledInstance(picWidth, picHeight, 1)));
				selectedPetToAdd = "Cat";
				petDescription.setText(catDescription);
			}
		});
		petSetUpPanel.add(rdbtnCat);

		JRadioButton rdbtnDog = new JRadioButton("Dog");
		rdbtnDog.setOpaque(false);
		rdbtnDog.setFont(new Font("Verdana", Font.BOLD, 12));
		rdbtnDog.setBounds(20, 126, 109, 23);
		rdbtnDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pet_picture.setIcon(new ImageIcon(dogPic.getScaledInstance(picWidth, picHeight, 1)));
				selectedPetToAdd = "Dog";
				petDescription.setText(dogDescription);
			}
		});
		petSetUpPanel.add(rdbtnDog);

		JRadioButton rdbtnFrog = new JRadioButton("Frog");
		rdbtnFrog.setOpaque(false);
		rdbtnFrog.setFont(new Font("Verdana", Font.BOLD, 12));
		rdbtnFrog.setBounds(20, 154, 109, 23);
		rdbtnFrog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pet_picture.setIcon(new ImageIcon(frogPic.getScaledInstance(picWidth, picHeight, 1)));
				selectedPetToAdd = "Frog";
				petDescription.setText(frogDescription);
			}
		});
		petSetUpPanel.add(rdbtnFrog);

		JRadioButton rdbtnHamster = new JRadioButton("Hamster");
		rdbtnHamster.setOpaque(false);
		rdbtnHamster.setFont(new Font("Verdana", Font.BOLD, 12));
		rdbtnHamster.setBounds(20, 181, 109, 23);
		rdbtnHamster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pet_picture.setIcon(new ImageIcon(hamsterPic.getScaledInstance(picWidth, picHeight, 1)));
				selectedPetToAdd = "Hamster";
				petDescription.setText(hamsterDescription);
			}
		});
		petSetUpPanel.add(rdbtnHamster);

		JRadioButton rdbtnSnake = new JRadioButton("Snake");
		rdbtnSnake.setOpaque(false);
		rdbtnSnake.setFont(new Font("Verdana", Font.BOLD, 12));
		rdbtnSnake.setBounds(20, 208, 109, 23);
		rdbtnSnake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pet_picture.setIcon(new ImageIcon(snakePic.getScaledInstance(picWidth, picHeight, 1)));
				selectedPetToAdd = "Snake";
				petDescription.setText(snakeDescription);
			}
		});
		petSetUpPanel.add(rdbtnSnake);

		JButton btnDoneButton = new JButton("Finish");
		btnDoneButton.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDoneButton.setBounds(364, 303, 102, 33);
		btnDoneButton.setToolTipText("Finish selecting pets");
		btnDoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosenPets.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You need to select some pets.", "Error", 2);
				} else {
					gameModel.getPlayers().get(playerNum).setPets(chosenPets);
					if (gameModel.getPlayers().size() > playerNum + 1) {
						// Recursively call the method to reset this panel for
						// the next
						// player to add their pets
						frame.remove(petSetUpPanel); // Resets everything
						frame.revalidate();
						initializePetSetUp(playerNum + 1);
						petSetUpPanel.revalidate(); // Necessary to show new
													// components
						petSetUpPanel.repaint(); // Necessary to clear away old
													// 'ghost' components

					} else {

						frame.remove(petSetUpPanel);
						frame.revalidate();

						initializeMidGame(0, 1);
						frame.revalidate();
						midGamePanel.revalidate();

						showMidGamePanel();
					}
				}
			}
		});
		petSetUpPanel.add(btnDoneButton);

		// Section which deals with removing a pet the player has already
		// chosen.
		JLabel lblClickToRemove = new JLabel("Click to Remove Pet:");
		lblClickToRemove.setFont(new Font("Verdana", Font.BOLD, 12));
		lblClickToRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickToRemove.setBounds(8, 307, 144, 24);
		petSetUpPanel.add(lblClickToRemove);

		JButton btnRemovePet3 = new JButton("");
		btnRemovePet3.setBounds(292, 294, buttonWidth, buttonHeight);
		btnRemovePet3.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(buttonWidth, buttonHeight, 1)));

		petSetUpPanel.add(btnRemovePet3);

		JButton btnRemovePet2 = new JButton("");
		btnRemovePet2.setBounds(231, 294, buttonWidth, buttonHeight);
		btnRemovePet2.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(buttonWidth, buttonHeight, 1)));
		petSetUpPanel.add(btnRemovePet2);

		JButton btnRemovePet1 = new JButton("");
		btnRemovePet1.setBounds(170, 294, buttonWidth, buttonHeight);
		btnRemovePet1.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(buttonWidth, buttonHeight, 1)));
		petSetUpPanel.add(btnRemovePet1);

		// Add the action listeners to each button that removes chosen pets
		btnRemovePet1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chosenPets.remove(0);
				updatePetRemovalButtons(btnRemovePet1, btnRemovePet2, btnRemovePet3, chosenPets, buttonWidth,
						buttonHeight);
			}
		});
		btnRemovePet2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chosenPets.remove(1);
				updatePetRemovalButtons(btnRemovePet1, btnRemovePet2, btnRemovePet3, chosenPets, buttonWidth,
						buttonHeight);
			}
		});
		btnRemovePet3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chosenPets.remove(2);
				updatePetRemovalButtons(btnRemovePet1, btnRemovePet2, btnRemovePet3, chosenPets, buttonWidth,
						buttonHeight);
			}
		});

		// Group the radio buttons so that only one can be selected at a time,
		// hence can only interact with one pet a time
		ButtonGroup selectPetButtonGroup = new ButtonGroup();
		selectPetButtonGroup.add(rdbtnBird);
		selectPetButtonGroup.add(rdbtnCat);
		selectPetButtonGroup.add(rdbtnDog);
		selectPetButtonGroup.add(rdbtnFrog);
		selectPetButtonGroup.add(rdbtnHamster);
		selectPetButtonGroup.add(rdbtnSnake);

		// Disable all the buttons for removing selected pets by default, since
		// there are no pets to remove
		btnRemovePet1.setEnabled(false);
		btnRemovePet2.setEnabled(false);
		btnRemovePet3.setEnabled(false);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack.setBounds(8, 11, 89, 33);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playerNum > 0) {
					frame.remove(petSetUpPanel);
					frame.revalidate();
					initializePetSetUp(playerNum - 1);
					frame.revalidate();
					frame.repaint();
				} else {
					showPlayerSetUp();
				}
			}
		});
		petSetUpPanel.add(btnBack);

		JButton btnAddPet = new JButton("Add Pet");
		btnAddPet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAddPet.setBounds(22, 250, 89, 23);
		btnAddPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosenPets.size() >= maxNumPets) {
					JOptionPane.showMessageDialog(frame, "You cannot have more than " + maxNumPets + " pets.");
				} else if (selectedPetToAdd == "none") {
					JOptionPane.showMessageDialog(frame, "You need to select a pet first!");
				} else {
					// Player has selected a pet to add, now get the pets name
					String petName = (String) JOptionPane.showInputDialog(frame, "Enter a name for your pet:",
							"Enter a name", JOptionPane.PLAIN_MESSAGE);

					if (petName == null) {
						// Do nothing, player hit cancel/exit. No value recorded
						// for the pet's name.
					} else if (petName.equals("")) { // Player hit 'OK' without
														// entering a name for
														// the pet.
						JOptionPane.showMessageDialog(frame, "You need to enter a name for your pet!");
					} else {
						boolean isValid = true;
						// First check the petName is unique
						playerLoop: for (Player player : gameModel.getPlayers()) {
							for (Pets pet : player.getPetArray()) {
								if (petName.toLowerCase().equals(pet.getName().toLowerCase())) {
									isValid = false;
									break playerLoop;
								}
							}
							for (Pets pet : chosenPets) {
								if (petName.toLowerCase().equals(pet.getName().toLowerCase())) {
									isValid = false;
									break playerLoop;
								}
							}
						}

						if (isValid) {
							// Add the pet to the list of pets chosen
							if (selectedPetToAdd == "Bird") {
								chosenPets.add(new Bird(petName));
							} else if (selectedPetToAdd == "Cat") {
								chosenPets.add(new Cat(petName));
							} else if (selectedPetToAdd == "Dog") {
								chosenPets.add(new Dog(petName));
							} else if (selectedPetToAdd == "Frog") {
								chosenPets.add(new Frog(petName));
							} else if (selectedPetToAdd == "Hamster") {
								chosenPets.add(new Hamster(petName));
							} else if (selectedPetToAdd == "Snake") {
								chosenPets.add(new Snake(petName));
							}

							// Update the buttons which allow the player to
							// remove pets they have selected
							updatePetRemovalButtons(btnRemovePet1, btnRemovePet2, btnRemovePet3, chosenPets,
									buttonWidth, buttonHeight);
						} else {
							JOptionPane.showMessageDialog(frame, "Pet cannot have the same name as other pets",
									"HEY! Listen!", 2);
						}

					}

				}
			}
		});
		petSetUpPanel.add(btnAddPet);

		JLabel lblBkgd = new JLabel("");
		lblBkgd.setBounds(0, 0, 503, 371);
		lblBkgd.setIcon(new ImageIcon(greenBkgd.getScaledInstance(495, 371, 1)));
		petSetUpPanel.add(lblBkgd);
	}

	/**
	 * Method used to update the pictures of the buttons in the petSetUpPanel
	 * which allows the player to remove pets they have chosen. As well as
	 * enable/disable the buttons appropriately. If the player has added 3 pets,
	 * all three buttons are enabled with the appropriate pictures. If only 2
	 * pets has been added, only the first two buttons are enabled and all
	 * buttons have the appropriate pictures (first two buttons have the picture
	 * of the pet and the third has the default picture). If only one pet as
	 * been added, the first button will have the picture of a pet and be
	 * enabled; the rest are disabled with the default picture. If no pets are
	 * added, all buttons are disabled with the default picture.
	 * 
	 * @param btnRemovePet1
	 *            The button which will show the first pet that has been added.
	 * @param btnRemovePet2
	 *            The button which will show the second pet that has been added.
	 * @param btnRemovePet3
	 *            The button which will show the third pet that has been added.
	 * @param chosenPets
	 *            The ArrayList of pets that the player has chosen.
	 */
	private void updatePetRemovalButtons(JButton btnRemovePet1, JButton btnRemovePet2, JButton btnRemovePet3,
			ArrayList<Pets> chosenPets, int width, int height) {
		// Enable/disable the appropriate buttons. Buttons disabled do not have
		// any pets displayed on them. Buttons enabled have pets displayed on
		// them.
		// Set the appropriate image on each button.
		switch (chosenPets.size()) {
		case 0:
			btnRemovePet1.setEnabled(false);
			btnRemovePet2.setEnabled(false);
			btnRemovePet3.setEnabled(false);
			btnRemovePet1.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(width, height, 1)));
			btnRemovePet2.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(width, height, 1)));
			btnRemovePet3.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(width, height, 1)));
			break;
		case 1:
			btnRemovePet1.setEnabled(true);
			btnRemovePet2.setEnabled(false);
			btnRemovePet3.setEnabled(false);
			btnRemovePet2.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(width, height, 1)));
			btnRemovePet3.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(width, height, 1)));
			break;
		case 2:
			btnRemovePet1.setEnabled(true);
			btnRemovePet2.setEnabled(true);
			btnRemovePet3.setEnabled(false);
			btnRemovePet3.setIcon(new ImageIcon(noPetSelectedPic.getScaledInstance(width, height, 1)));
			break;
		case 3:
			btnRemovePet1.setEnabled(true);
			btnRemovePet2.setEnabled(true);
			btnRemovePet3.setEnabled(true);
			break;
		}

		for (int i = 0; i < chosenPets.size(); i++) {
			Image image = decideImage(chosenPets.get(i));
			switch (i) {
			case 0:
				btnRemovePet1.setIcon(new ImageIcon(image.getScaledInstance(width, height, 1)));
				break;
			case 1:
				btnRemovePet2.setIcon(new ImageIcon(image.getScaledInstance(width, height, 1)));
				break;
			case 2:
				btnRemovePet3.setIcon(new ImageIcon(image.getScaledInstance(width, height, 1)));
				break;
			}
		}
	}

	/**
	 * Method which is used to return the correct image associated to the pet.
	 * Has an overloaded version which returns images for items.
	 * 
	 * @param pet
	 *            The pet which we want the appropriate image of.
	 * @return An image of the pet.
	 */
	private Image decideImage(Pets pet) {
		Image image = null;

		if (pet.getSpecies() == "Bird") {
			image = birdPic;
		} else if (pet.getSpecies() == "Cat") {
			image = catPic;
		} else if (pet.getSpecies() == "Dog") {
			image = dogPic;
		} else if (pet.getSpecies() == "Frog") {
			image = frogPic;
		} else if (pet.getSpecies() == "Hamster") {
			image = hamsterPic;
		} else if (pet.getSpecies() == "Snake") {
			image = snakePic;
		}

		return image;
	}

	/**
	 * Method which is used to return the correct image associated to the item.
	 * Has an overloaded version which returns images for pets.
	 * 
	 * @param item The item which we want the appropriate image of.
	 * @return An image of the item.
	 */
	private Image decideImage(Item item) {
		Image image = null;
		
		if(item.getName() == "Fish") {
			image = fishPic;
		} else if(item.getName() == "Fly") {
			image = flyPic;
		} else if (item.getName() == "Fruit") {
			image = fruitPic;
		} else if (item.getName() == "Rat") {
			image = ratPic;
		} else if (item.getName() == "Seeds") {
			image = seedsPic;
		} else if (item.getName() == "Steak") {
			image = steakPic;
		} else if (item.getName() == "Bandage") { 
			image = bandagePic;
		} else if (item.getName() == "EpiPen") {
			image = epiPenPic;
		} else if (item.getName() == "Pet Medicine") {
			image = petMedicinePic;
		} else if (item.getName() == "Squeaky Bone") {
			image = bonePic;
		} else if (item.getName() == "Hamster Ball") {
			image = hamBallPic;
		} else if (item.getName() == "Pool") {
			image = poolPic;
		} else if (item.getName() == "Slide") {
			image = slidePic;
		} else if (item.getName() == "Cardboard Tube") {
			image = tubePic;
		} else if (item.getName() == "Ball of Wool" ) {
			image = woolBallPic;
		}
		
		return image;
	}

	/**
	 * Constructs the JPanel which houses all the components for the player to play the game. JPanel consists of labels which display the palyer's 
	 * name, day and will be used as a way to show the player messages. In addition, a JTabbedPane is used for user actions; such as visiting stores 
	 * and viewing their inventory. The JTabbedPane is constructed out of JPanels, a JPanel for each tab. Each JPanel will consist of components
	 * which relate to the action the user can conduct. 
	 * 
	 * Radiobuttons within each JPanel in the JTabbedPane are grouped such that only one can be selected at a time. 
	 */
	private void initializeMidGame(int playerNum, int dayNum) {
		//Variables used for playing the game and setting up the GUI
		Player player = gameModel.getPlayers().get(playerNum);
		ArrayList<Pets> pets = player.getPetArray();
		int pictureWidth = 100;
		int pictureHeight = pictureWidth;
		
		//Initialize components for the midGamePanel
		//-----------------------------------------------------------------------------------------------------------------------
		//######################################### COMPONENTS COMMON TO ALL TABBED PANELS ######################################
		//-----------------------------------------------------------------------------------------------------------------------
		midGamePanel = new JPanel();
		midGamePanel.setBounds(0, 0, 488, 370);
		frame.getContentPane().add(midGamePanel);
		midGamePanel.setLayout(null);
		
		JTextPane lblMessages = new JTextPane();
		lblMessages.setBounds(10, 307, 442, 51);
		lblMessages.setEditable(false);
		midGamePanel.add(lblMessages);
		
		JButton btnEndDay = new JButton("End Day");
		btnEndDay.setFont(new Font("Verdana", Font.BOLD, 11));
		btnEndDay.setBounds(464, 307, 106, 23);
		btnEndDay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (playerNum < gameModel.getPlayers().size()-1){
					gameModel.endDayForPlayer(player);
					frame.remove(midGamePanel);
					frame.revalidate();
					initializeMidGame(playerNum+1, dayNum);
					midGamePanel.revalidate();
					midGamePanel.repaint();
				} else {
					if (dayNum < gameModel.getDays()){
						gameModel.endDayForPlayer(player);
						frame.remove(midGamePanel);
						frame.revalidate();
						initializeMidGame(0, dayNum+1);
						midGamePanel.revalidate();
						midGamePanel.repaint();
					} else {
						frame.remove(midGamePanel);
						frame.revalidate();
						frame.repaint();
						initializeScoreBoardSetUp();
						scoreBoardPanel.revalidate();
						scoreBoardPanel.repaint();
						showScoreBoardPanel();

					}
				}
			}
		});
		midGamePanel.add(btnEndDay);
		
		JLabel lblPlayerName = new JLabel();
		lblPlayerName.setForeground(Color.WHITE);
		lblPlayerName.setFont(new Font("Verdana", lblPlayerName.getFont().getStyle(), lblPlayerName.getFont().getSize()));
		lblPlayerName.setBounds(10, 11, 128, 14);
		
		
		JLabel lblDayNumber = new JLabel("");
		lblDayNumber.setForeground(Color.WHITE);
		lblDayNumber.setFont(new Font("Verdana", lblDayNumber.getFont().getStyle(), lblDayNumber.getFont().getSize()));
		lblDayNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDayNumber.setBounds(493, 11, 89, 14);
		
		
		//Creating the tabbed panel which allows the player to interact with their pets and view their stats
		JTabbedPane tabbedPlayerOptions = new JTabbedPane(JTabbedPane.TOP);
		tabbedPlayerOptions.setFont(new Font("Verdana", Font.BOLD, 11));
		tabbedPlayerOptions.setBounds(10, 37, 572, 263);
		
		
		//----------------------------------------------------------------------------------------------------------------------
		//########################################### INTERACT WITH PETS SECTION ###############################################
		//----------------------------------------------------------------------------------------------------------------------
		
		JPanel petPanel = new JPanel();
		tabbedPlayerOptions.addTab("Pets", null, petPanel, null);
		petPanel.setLayout(null);

		JTextPane petStatsTextPane = new JTextPane();
		petStatsTextPane.setEditable(false);
		petStatsTextPane.setBounds(113, 35, 331, 188);
		petPanel.add(petStatsTextPane);
		petStatsTextPane.setText("No pet has been selected yet."); //Default message
		
		//Group the radio buttons which select which pet to interact with so only one can be selected at a time
		ButtonGroup selectedPetButtonGroup = new ButtonGroup();
		
		//Add rdbtnPet1 by default since there must be least one pet
		JRadioButton rdbtnPet1 = new JRadioButton(pets.get(0).getName());
		rdbtnPet1.setFont(new Font("Verdana", rdbtnPet1.getFont().getStyle(), rdbtnPet1.getFont().getSize()));
		rdbtnPet1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPet1.setBounds(117, 5, 109, 23);
		selectedPetButtonGroup.add(rdbtnPet1);
		
		//sets the first pet to be selected by default
		rdbtnPet1.setSelected(true);
		selectedPetToInteract = pets.get(0);
		petStatsTextPane.setText(selectedPetToInteract.toString());
		
		petPanel.add(rdbtnPet1);
		
		JLabel labelPetPicture = new JLabel("");
		labelPetPicture.setBounds(455, 35, 100, 100);
		labelPetPicture.setIcon(new ImageIcon(decideImage(selectedPetToInteract).getScaledInstance(100, 100, 1)));
		petPanel.add(labelPetPicture);
		
		rdbtnPet1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				selectedPetToInteract = pets.get(0);
				petStatsTextPane.setText(selectedPetToInteract.toString());
				labelPetPicture.setIcon(new ImageIcon(decideImage(selectedPetToInteract).getScaledInstance(100, 100, 1)));
			}
		});
		
		//Now check there are more than 1 pet, add the required number of buttons
		if(pets.size() >= 2) {
			JRadioButton rdbtnPet2 = new JRadioButton(pets.get(1).getName());
			rdbtnPet2.setFont(new Font("Verdana", rdbtnPet1.getFont().getStyle(), rdbtnPet1.getFont().getSize()));
			rdbtnPet2.setHorizontalAlignment(SwingConstants.CENTER);
			rdbtnPet2.setBounds(228, 5, 109, 23);
			rdbtnPet2.setText(pets.get(1).getName());
			selectedPetButtonGroup.add(rdbtnPet2);
			petPanel.add(rdbtnPet2);
			
			rdbtnPet2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					selectedPetToInteract = pets.get(1);
					petStatsTextPane.setText(selectedPetToInteract.toString());
					labelPetPicture.setIcon(new ImageIcon(decideImage(selectedPetToInteract).getScaledInstance(100, 100, 1)));

				}
			});
		}
			
			
		if (pets.size() == 3) {
				
			JRadioButton rdbtnPet3 = new JRadioButton(pets.get(2).getName());
			rdbtnPet3.setFont(new Font("Verdana", rdbtnPet1.getFont().getStyle(), rdbtnPet1.getFont().getSize()));
			rdbtnPet3.setHorizontalAlignment(SwingConstants.CENTER);
			rdbtnPet3.setBounds(339, 5, 109, 23);
			rdbtnPet3.setText(pets.get(2).getName());
			selectedPetButtonGroup.add(rdbtnPet3);
			petPanel.add(rdbtnPet3);
			
			rdbtnPet3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					selectedPetToInteract = pets.get(2);
					petStatsTextPane.setText(selectedPetToInteract.toString());
					labelPetPicture.setIcon(new ImageIcon(decideImage(selectedPetToInteract).getScaledInstance(100, 100, 1)));

				}
			});
			
		 }

		JButton btnFeedPet = new JButton("Feed Pet");
		btnFeedPet.setFont(new Font("Verdana", Font.BOLD, 11));
		btnFeedPet.setBounds(10, 5, 95, 23);
		btnFeedPet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String inputFood = null;
				String[] foodItems = new String[gameModel.getFoodItems(player).size()];
				int counter = 0;
				//showOptionDialog requires an array to for the drop down.
				for (Item food : gameModel.getFoodItems(player)){
					foodItems[counter] = food.getName();
					counter++;
				}
				if (counter == 0){
					JOptionPane.showMessageDialog(frame, "You don't have any food to feed it with!", "Error", 1);
				} else {
					inputFood = (String) JOptionPane.showInputDialog(frame, "Please select an item to feed your pet with.", "Feed "+selectedPetToInteract.getName(), JOptionPane.QUESTION_MESSAGE, null, foodItems, foodItems[0]);
					if (inputFood == null){
						/*ignore*/
					} else{
					switch (inputFood){
					case "Steak": updateMessageDisplay(lblMessages, gameModel.feedPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Steak())))); break;
					case "Fish":  updateMessageDisplay(lblMessages, gameModel.feedPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Fish())))); break;
					case "Seeds": updateMessageDisplay(lblMessages, gameModel.feedPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Seeds())))); break;
					case "Fruit": updateMessageDisplay(lblMessages, gameModel.feedPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Fruit())))); break;
					case "Fly":   updateMessageDisplay(lblMessages, gameModel.feedPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Fly())))); break;
					case "Rat":   updateMessageDisplay(lblMessages, gameModel.feedPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Rat())))); break;
					default:      updateMessageDisplay(lblMessages, "An error occured"); 
					}
					}
					petStatsTextPane.setText(selectedPetToInteract.toString());
					midGamePanel.revalidate();
					
				}
				

			}
		});
		petPanel.add(btnFeedPet);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Verdana", Font.BOLD, 11));
		btnPlay.setBounds(10, 35, 95, 23);
		btnPlay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String inputToy = null;
				String[] toyItems = new String[gameModel.getToyItems(player).size()];
				int counter = 0;
				//showOptionDialog requires an array to for the drop down.
				for (Item toy : gameModel.getToyItems(player)){
					toyItems[counter] = toy.getName();
					counter++;
				}
				if (counter == 0){
					JOptionPane.showMessageDialog(frame, "You don't have any toys for it to play with!", "Error", 1);
				} else {
					inputToy = (String) JOptionPane.showInputDialog(frame, "Please select an item for your pet to play with.", "Give "+selectedPetToInteract.getName(), JOptionPane.QUESTION_MESSAGE, null, toyItems, toyItems[0]);
					if (inputToy == null){
						/*ignore*/
					} else{
					switch (inputToy){
					case "Slide": updateMessageDisplay(lblMessages, gameModel.playWithPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Slide())))); break;
					case "Cardboard Tube":  updateMessageDisplay(lblMessages, gameModel.playWithPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Tube())))); break;
					case "Squeaky Bone": updateMessageDisplay(lblMessages, gameModel.playWithPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Bone())))); break;
					case "Ball of Wool": updateMessageDisplay(lblMessages, gameModel.playWithPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new WoolBall())))); break;
					case "Pool":   updateMessageDisplay(lblMessages, gameModel.playWithPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Pool())))); break;
					case "Hamster Ball":   updateMessageDisplay(lblMessages, gameModel.playWithPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new HamBall())))); break;
					default:      updateMessageDisplay(lblMessages, "An error occured");
					}
					}
					petStatsTextPane.setText(selectedPetToInteract.toString());
					midGamePanel.revalidate();
					
				}
				

			}
		});
		petPanel.add(btnPlay);
			
		JButton btnPutToSleep = new JButton("Sleep");
		btnPutToSleep.setFont(new Font("Verdana", btnPutToSleep.getFont().getStyle(), btnPutToSleep.getFont().getSize()));
		btnPutToSleep.setBounds(12, 69, 93, 23);
		btnPutToSleep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updateMessageDisplay(lblMessages,gameModel.putPetToSleep(player, selectedPetToInteract));
				petStatsTextPane.setText(selectedPetToInteract.toString());
				midGamePanel.revalidate();
			}
		});
		petPanel.add(btnPutToSleep);
		
		JButton btnToilet = new JButton("Use Toilet");
		btnToilet.setFont(new Font("Verdana", btnToilet.getFont().getStyle(), 10));
		btnToilet.setBounds(13, 103, 92, 23);
		btnToilet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updateMessageDisplay(lblMessages, gameModel.takeToToilet(player, selectedPetToInteract));
				petStatsTextPane.setText(selectedPetToInteract.toString());
				midGamePanel.revalidate();
			}
		});
		petPanel.add(btnToilet);
		
		JButton btnHeal = new JButton("Heal");
		btnHeal.setFont(new Font("Verdana", btnPutToSleep.getFont().getStyle(), btnPutToSleep.getFont().getSize()));
		btnHeal.setBounds(13, 137, 92, 23);
		btnHeal.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String inputMed = null;
			String[] medItems = new String[gameModel.getMedItems(player).size()];
			int counter = 0;
			//showOptionDialog requires an array to for the drop down.
			for (Item med : gameModel.getMedItems(player)){
				medItems[counter] = med.getName();
				counter++;
			}
			if (counter == 0){
				JOptionPane.showMessageDialog(frame, "You don't have any medicine to heal it with!", "Error", 1);
			} else {
				inputMed = (String) JOptionPane.showInputDialog(frame, "Please select an item to heal your pet with.", "Heal "+selectedPetToInteract.getName(), JOptionPane.QUESTION_MESSAGE, null, medItems, medItems[0]);
				if (inputMed == null){
					/*ignore*/
				} else{
				switch (inputMed){
				case "Bandage": updateMessageDisplay(lblMessages, gameModel.healPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new Bandage())))); break;
				case "Pet Medicine":  updateMessageDisplay(lblMessages, gameModel.healPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new PetMedicine())))); break;
				case "EpiPen":   updateMessageDisplay(lblMessages, gameModel.healPet(player, selectedPetToInteract, player.getInventory().get(player.findItemIndex(new EpiPen())))); break;
				default:      updateMessageDisplay(lblMessages, "An error occured");
				}
				}
				petStatsTextPane.setText(selectedPetToInteract.toString());
				midGamePanel.revalidate();
				
			}
			

		}
	});
		petPanel.add(btnHeal);
		
		JButton btnRevive = new JButton("Revive");
		btnRevive.setFont(new Font("Verdana", btnPutToSleep.getFont().getStyle(), btnPutToSleep.getFont().getSize()));
		btnRevive.setBounds(13, 171, 92, 23);
		btnRevive.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updateMessageDisplay(lblMessages, gameModel.revivePet(selectedPetToInteract));
				petStatsTextPane.setText(selectedPetToInteract.toString());
			}
		});
		petPanel.add(btnRevive);
		
		JButton btnDiscipline = new JButton("Discipline");
		btnDiscipline.setFont(new Font("Verdana", btnDiscipline.getFont().getStyle(), 10));
		btnDiscipline.setBounds(13, 200, 92, 23);
		btnDiscipline.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updateMessageDisplay(lblMessages, selectedPetToInteract.discipline());
				petStatsTextPane.setText(selectedPetToInteract.toString());
			}
		});
		petPanel.add(btnDiscipline);
		
		
		//------------------------------------------------------------------------------------------------------------------
		//################################################ PLAYER INVENTORY SECTION ########################################
		//------------------------------------------------------------------------------------------------------------------
		
		JTextPane displayInventoryPane = new JTextPane();
		displayInventoryPane.setEditable(false);
		displayInventoryPane.setPreferredSize(new Dimension(100, 100));
		
		JScrollPane InventoryScrollPane = new JScrollPane(displayInventoryPane);
		tabbedPlayerOptions.addTab("Inventory", null, InventoryScrollPane, null);
		InventoryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		InventoryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		
		//------------------------------------------------------------------------------------------------------------------
		//################################################ TOY STORE SECTION ###############################################
		//------------------------------------------------------------------------------------------------------------------
		
		//Create the tabbed panel which allows the player to interact with the toy store and make purchases
		JPanel toyStorePanel = new JPanel();
		tabbedPlayerOptions.addTab("Toy Store", null, toyStorePanel, null);
		toyStorePanel.setLayout(null);
		
		JLabel labelToyPicture = new JLabel("");
		labelToyPicture.setBounds(455, 40, pictureHeight, pictureWidth);
		toyStorePanel.add(labelToyPicture);
		
		JTextPane txtpnSelectedToyDescription = new JTextPane();
		txtpnSelectedToyDescription.setEditable(false);
		txtpnSelectedToyDescription.setText("Nothing has been selected yet.");
		txtpnSelectedToyDescription.setBounds(166, 36, 278, 164);
		toyStorePanel.add(txtpnSelectedToyDescription);

		
		
		JRadioButton rdbtnSlide = new JRadioButton("Slide");
		rdbtnSlide.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnSlide.setBounds(6, 7, 146, 23);
		rdbtnSlide.setOpaque(false);
		rdbtnSlide.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedToy = new Slide();
					updateJTextPane(txtpnSelectedToyDescription, selectedToy, player);
					labelToyPicture.setIcon(new ImageIcon(decideImage(selectedToy).getScaledInstance(pictureHeight, pictureWidth, 1))); 

				}
			});
		toyStorePanel.add(rdbtnSlide);
		
		JRadioButton rdbtnWoolBall = new JRadioButton("Ball of Wool");
		rdbtnWoolBall.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnWoolBall.setBounds(6, 33, 217, 23);
		rdbtnWoolBall.setOpaque(false);
		rdbtnWoolBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedToy = new WoolBall();
				updateJTextPane(txtpnSelectedToyDescription, selectedToy, player);
				labelToyPicture.setIcon(new ImageIcon(decideImage(selectedToy).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		toyStorePanel.add(rdbtnWoolBall);
		
		JRadioButton rdbtnBone = new JRadioButton("Bone");
		rdbtnBone.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnBone.setBounds(6, 59, 217, 23);
		rdbtnBone.setOpaque(false);
		rdbtnBone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedToy = new Bone();
				updateJTextPane(txtpnSelectedToyDescription, selectedToy, player);
				labelToyPicture.setIcon(new ImageIcon(decideImage(selectedToy).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		toyStorePanel.add(rdbtnBone);
		
		JRadioButton rdbtnPool = new JRadioButton("Pool");
		rdbtnPool.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnPool.setBounds(6, 85, 217, 23);
		rdbtnPool.setOpaque(false);
		rdbtnPool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedToy = new Pool();
				updateJTextPane(txtpnSelectedToyDescription, selectedToy, player);
				labelToyPicture.setIcon(new ImageIcon(decideImage(selectedToy).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		toyStorePanel.add(rdbtnPool);
		
		JRadioButton rdbtnHamBall = new JRadioButton("Hamster Ball");
		rdbtnHamBall.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnHamBall.setBounds(6, 111, 217, 23);
		rdbtnHamBall.setOpaque(false);
		rdbtnHamBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedToy = new HamBall();
				updateJTextPane(txtpnSelectedToyDescription, selectedToy, player);
				labelToyPicture.setIcon(new ImageIcon(decideImage(selectedToy).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		toyStorePanel.add(rdbtnHamBall);
		
		JRadioButton rdbtnTube = new JRadioButton("Cardboard Tube");
		rdbtnTube.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnTube.setBounds(6, 137, 217, 23);
		rdbtnTube.setOpaque(false);
		rdbtnTube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedToy = new Tube();
				updateJTextPane(txtpnSelectedToyDescription, selectedToy, player);
				labelToyPicture.setIcon(new ImageIcon(decideImage(selectedToy).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		toyStorePanel.add(rdbtnTube);
		
		//Group the toy radio buttons so that only one item can be selected for purchase
		ButtonGroup toyStockButtonGroup = new ButtonGroup();
		toyStockButtonGroup.add(rdbtnSlide);
		toyStockButtonGroup.add(rdbtnWoolBall);
		toyStockButtonGroup.add(rdbtnBone);
		toyStockButtonGroup.add(rdbtnPool);
		toyStockButtonGroup.add(rdbtnHamBall);
		toyStockButtonGroup.add(rdbtnTube);
		

		
		JLabel lblYouHaveToyMoney = new JLabel("You have: $");
		lblYouHaveToyMoney.setFont(new Font("Verdana", Font.BOLD, 11));
		lblYouHaveToyMoney.setBounds(166, 9, 89, 14);
		toyStorePanel.add(lblYouHaveToyMoney);
		
		JLabel lblPlayersToyMoney = new JLabel("Player's money");
		lblPlayersToyMoney.setFont(new Font("Verdana", Font.BOLD, 11));
		lblPlayersToyMoney.setBounds(256, 10, 114, 14);
		toyStorePanel.add(lblPlayersToyMoney);
		
		
		JButton btnBuyToy = new JButton("Buy");
		btnBuyToy.setFont(new Font("Verdana", Font.BOLD, 11));
		btnBuyToy.setBounds(33, 177, 89, 23);
		btnBuyToy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(lblMessages, player, selectedToy);
				updatePlayerMoneyDisplay(lblPlayersToyMoney, player);
				
				//Player has now bought the item.
				//Now need to display that they own it
				updateJTextPane(txtpnSelectedToyDescription, selectedToy, player);
			}
		});
		toyStorePanel.add(btnBuyToy);
				
		
		//----------------------------------------------------------------------------------------------------------------------
		//################################################# FOOD STORE PANEL ###################################################
		//----------------------------------------------------------------------------------------------------------------------
		
		//Create the tabbed panel which allows the user to interact with the food store and buy food items
		JPanel foodStorePanel = new JPanel();
		tabbedPlayerOptions.addTab("Grocery Store", null, foodStorePanel, null);
		foodStorePanel.setLayout(null);
		
		JLabel labelFoodPicture = new JLabel("");
		labelFoodPicture.setBounds(455, 40, pictureHeight, pictureWidth);
		foodStorePanel.add(labelFoodPicture);
		
		JTextPane txtpnSelectedFoodDesciption = new JTextPane();
		txtpnSelectedFoodDesciption.setEditable(false);
		txtpnSelectedFoodDesciption.setText("No item has been selected yet.");
		txtpnSelectedFoodDesciption.setBounds(166, 36, 278, 164);
		foodStorePanel.add(txtpnSelectedFoodDesciption);
		
		JRadioButton rdbtnSeeds = new JRadioButton("Seeds");
		rdbtnSeeds.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnSeeds.setBounds(6, 7, 109, 23);
		rdbtnSeeds.setOpaque(false);
		rdbtnSeeds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedFood = new Seeds();
				updateJTextPane(txtpnSelectedFoodDesciption, selectedFood, player);
				labelFoodPicture.setIcon(new ImageIcon(decideImage(selectedFood).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		foodStorePanel.add(rdbtnSeeds);
		
		JRadioButton rdbtnFish = new JRadioButton("Fish");
		rdbtnFish.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnFish.setBounds(6, 33, 109, 23);
		rdbtnFish.setOpaque(false);
		rdbtnFish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedFood = new Fish(); 
				updateJTextPane(txtpnSelectedFoodDesciption, selectedFood, player);
				labelFoodPicture.setIcon(new ImageIcon(decideImage(selectedFood).getScaledInstance(pictureHeight, pictureWidth, 1)));
			}
		});
		foodStorePanel.add(rdbtnFish);
		
		JRadioButton rdbtnFruit = new JRadioButton("Fruit");
		rdbtnFruit.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnFruit.setBounds(6, 59, 109, 23);
		rdbtnFruit.setOpaque(false);
		rdbtnFruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedFood = new Fruit(); 
				updateJTextPane(txtpnSelectedFoodDesciption, selectedFood, player);
				labelFoodPicture.setIcon(new ImageIcon(decideImage(selectedFood).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		foodStorePanel.add(rdbtnFruit);
		
		JRadioButton rdbtnSteak = new JRadioButton("Steak");
		rdbtnSteak.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnSteak.setBounds(6, 85, 109, 23);
		rdbtnSteak.setOpaque(false);
		rdbtnSteak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedFood = new Steak(); 
				updateJTextPane(txtpnSelectedFoodDesciption, selectedFood, player);
				labelFoodPicture.setIcon(new ImageIcon(decideImage(selectedFood).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		foodStorePanel.add(rdbtnSteak);
		
		JRadioButton rdbtnFly = new JRadioButton("Fly");
		rdbtnFly.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnFly.setBounds(6, 111, 109, 23);
		rdbtnFly.setOpaque(false);
		rdbtnFly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedFood = new Fly(); 
				updateJTextPane(txtpnSelectedFoodDesciption, selectedFood, player);
				labelFoodPicture.setIcon(new ImageIcon(decideImage(selectedFood).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		foodStorePanel.add(rdbtnFly);
		
		JRadioButton rdbtnRat = new JRadioButton("Rat");
		rdbtnRat.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnRat.setBounds(6, 137, 109, 23);
		rdbtnRat.setOpaque(false);
		rdbtnRat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedFood = new Rat(); 
				updateJTextPane(txtpnSelectedFoodDesciption, selectedFood, player);
				labelFoodPicture.setIcon(new ImageIcon(decideImage(selectedFood).getScaledInstance(pictureHeight, pictureWidth, 1))); 
				
			}
		});
		foodStorePanel.add(rdbtnRat);
		
		//Group the food radio buttons such that the player can only select and buy one a time
		ButtonGroup foodStockButtonGroup = new ButtonGroup();
		foodStockButtonGroup.add(rdbtnSeeds);
		foodStockButtonGroup.add(rdbtnFish);
		foodStockButtonGroup.add(rdbtnFruit);
		foodStockButtonGroup.add(rdbtnSteak);
		foodStockButtonGroup.add(rdbtnFly);
		foodStockButtonGroup.add(rdbtnRat);
		
		JLabel lblYouHaveFoodMoney = new JLabel("You have: $");
		lblYouHaveFoodMoney.setFont(new Font("Verdana", Font.BOLD, 11));
		lblYouHaveFoodMoney.setBounds(166, 9, 89, 14);
		foodStorePanel.add(lblYouHaveFoodMoney);
		
		JLabel lblPlayersFoodMoney = new JLabel("Player's money");
		lblPlayersFoodMoney.setFont(new Font("Verdana", Font.BOLD, 11));
		lblPlayersFoodMoney.setBounds(256, 9, 114, 14);
		foodStorePanel.add(lblPlayersFoodMoney);
		
		JButton btnBuyFood = new JButton("Buy");
		btnBuyFood.setFont(new Font("Verdana", Font.BOLD, 11));
		btnBuyFood.setBounds(33, 177, 89, 23);
		btnBuyFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(lblMessages, player, selectedFood);
				updatePlayerMoneyDisplay(lblPlayersFoodMoney, player);
				
				//Player has now bought the item.
				//Now need to display that they own it
				updateJTextPane(txtpnSelectedFoodDesciption, selectedFood, player);
			}
		});
		foodStorePanel.add(btnBuyFood);
		
		
		//----------------------------------------------------------------------------------------------------------------------
		//############################################### PHARMACY STORE PANEL #################################################
		//----------------------------------------------------------------------------------------------------------------------
		
		//Create the tabbed panel which allows the player to interact with the medicine store and make medical purchases
		JPanel medicineStorePanel = new JPanel();
		tabbedPlayerOptions.addTab("Pharmacy", null, medicineStorePanel, null);
		medicineStorePanel.setLayout(null);
		
		JLabel labelMedicinePicture = new JLabel("");
		labelMedicinePicture.setBounds(455, 40, pictureHeight, pictureWidth);
		medicineStorePanel.add(labelMedicinePicture);
		
		JLabel lblYouHaveMedicineMoney = new JLabel("You have: $");
		lblYouHaveMedicineMoney.setFont(new Font("Verdana", Font.BOLD, 11));
		lblYouHaveMedicineMoney.setBounds(166, 9, 89, 14);
		medicineStorePanel.add(lblYouHaveMedicineMoney);
		
		JLabel lblPlayersMedicineMoney = new JLabel("Players Money");
		lblPlayersMedicineMoney.setFont(new Font("Verdana", Font.BOLD, 11));
		lblPlayersMedicineMoney.setBounds(256, 9, 114, 14);
		medicineStorePanel.add(lblPlayersMedicineMoney);
		
		JTextPane txtpnSelectedMedicineItemDescription = new JTextPane();
		txtpnSelectedMedicineItemDescription.setText("No item has been selected yet.");
		txtpnSelectedMedicineItemDescription.setBounds(166, 36, 278, 164);
		medicineStorePanel.add(txtpnSelectedMedicineItemDescription);
		
		JRadioButton rdbtnBandage = new JRadioButton("Bandage");
		rdbtnBandage.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnBandage.setBounds(6, 7, 109, 23);
		rdbtnBandage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedMedicine = new Bandage();
				updateJTextPane(txtpnSelectedMedicineItemDescription, selectedMedicine, player);
				labelMedicinePicture.setIcon(new ImageIcon(decideImage(selectedMedicine).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		medicineStorePanel.add(rdbtnBandage);
		
		JRadioButton rdbtnPetMedicine = new JRadioButton("Pet Medicine");
		rdbtnPetMedicine.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnPetMedicine.setBounds(6, 33, 109, 23);
		rdbtnPetMedicine.setOpaque(false);
		rdbtnPetMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedMedicine = new PetMedicine();
				updateJTextPane(txtpnSelectedMedicineItemDescription, selectedMedicine, player);
				labelMedicinePicture.setIcon(new ImageIcon(decideImage(selectedMedicine).getScaledInstance(pictureHeight, pictureWidth, 1)));
			}
		});
		medicineStorePanel.add(rdbtnPetMedicine);
		
		JRadioButton rdbtnEpiPen = new JRadioButton("EpiPen");
		rdbtnEpiPen.setFont(new Font("Verdana", Font.BOLD, 11));
		rdbtnEpiPen.setBounds(6, 59, 109, 23);
		rdbtnEpiPen.setOpaque(false);
		rdbtnEpiPen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedMedicine = new EpiPen();
				updateJTextPane(txtpnSelectedMedicineItemDescription, selectedMedicine, player);
				labelMedicinePicture.setIcon(new ImageIcon(decideImage(selectedMedicine).getScaledInstance(pictureHeight, pictureWidth, 1))); 
			}
		});
		medicineStorePanel.add(rdbtnEpiPen);
		
		//Group the radio buttons which select which item to buy so the player can only buy one button a time
		ButtonGroup medicineStockButtonGroup = new ButtonGroup();
		medicineStockButtonGroup.add(rdbtnBandage);
		medicineStockButtonGroup.add(rdbtnPetMedicine);
		medicineStockButtonGroup.add(rdbtnEpiPen);
		
		JButton btnBuyMedicine = new JButton("Buy");
		btnBuyMedicine.setFont(new Font("Verdana", Font.BOLD, 11));
		btnBuyMedicine.setBounds(33, 177, 89, 23);
		btnBuyMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(lblMessages, player, selectedMedicine);
				
				updatePlayerMoneyDisplay(lblPlayersMedicineMoney, player);	
				
				//Player has now bought the item.
				//Now need to display that they own it
				updateJTextPane(txtpnSelectedMedicineItemDescription, selectedMedicine, player);
			}
		});
		medicineStorePanel.add(btnBuyMedicine);		
		
		//Set the appropriate values to the labels
		lblPlayerName.setText(player.getName()+"'s turn.");
		lblDayNumber.setText(String.format("Day %1$d of %2$d", dayNum, gameModel.getDays()));
		midGamePanel.add(lblPlayerName);
		midGamePanel.add(lblDayNumber);
		midGamePanel.add(tabbedPlayerOptions);
		
		JLabel lblBkgd = new JLabel("");
		lblBkgd.setBounds(0, 0, 594, 571);
		lblBkgd.setIcon(new ImageIcon(bkgdPic.getScaledInstance(600, 600, 1)));
		midGamePanel.add(lblBkgd);
		midGamePanel.revalidate();
		midGamePanel.repaint();

		//If a player clicks a tab, update the required components to display the current information/stats
		tabbedPlayerOptions.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//Update labels in all the stores, player could go to anyone of them.
				//Might have bought something in one store, need to update the player's balance in all other stores
				updatePlayerMoneyDisplay(lblPlayersToyMoney, player);
				updatePlayerMoneyDisplay(lblPlayersFoodMoney, player);
				updatePlayerMoneyDisplay(lblPlayersMedicineMoney, player);
				
				updateInventoryDisplay(displayInventoryPane, player);
			}

		});
	}
	
	
	/**
	 * Returns how many of an item a player has.
	 * 
	 * @param player The player whose inventory is being searched.
	 * @param item The item being searched for in the player's inventory.
	 * @return A string which conveys how many of a certain item the player has.
	 */
	private String searchForItem(Player player, Item item) {
		String message = "You do not have this item.\n"; //Default message if the item is not found
		if(item.returnIsStackable()) {
			//Is a consumable, need to also return how many there are
			for(Item ownedItem : player.getInventory()) {
				if(ownedItem.equals(item)) {
					message = String.format("You already have %d of this item.\n", item.getNumUses());
					break;
				}
			}
		} else {
			//Is not a consumable, can only have one. Just need to return if the player has one in
			//their inventory already.
			for(Item ownedItem : player.getInventory()) {
				if(ownedItem.equals(item)) {
					message = "You already have this item.\n";
					break;
				}
			}
		}
		return message;
	}
	
	/**
	 * Method will be called when player has bought an item, if player has enough money and inventory space, the money will
	 * be deducted from the player's current balance and item will be added to their inventory. The label's text
	 * will be updated to inform the user of what has happened, for both cases if he was able or wasn't able to buy the item.
	 * 
	 * @param message JLabel that will be updated to inform the user of what has happened.
	 * @param player Player that will be buying the item.
	 * @param item Item that the player wants to buy.
	 */
	private void buyItem(JTextPane messagelbl, Player player, Item item) {
		if (item.returnIsStackable()){
			if (player.getMoney() - item.getCost() > 0){
				player.spendMoney(item.getCost());
				player.addItem(item);
				
				messagelbl.setText(String.format("You bought a %1$s", item.getName()));
			} else {
				messagelbl.setText("You don't have the money for that!");
			}
		} else {
		if (player.hasInventorySpace(item)){
			if (player.getMoney() - item.getCost() > 0){
				player.spendMoney(item.getCost());
				player.addItem(item);
				
				messagelbl.setText(String.format("You bought a %1$s", item.getName()));
			} else {
				messagelbl.setText("You don't have the money for that!");
			}
		} else {
			messagelbl.setText("You don't have the room to fit in your inventory.");
		}
		}
			
	}
	
	/**
	 *  Method used to update the the JTextPane which displays the user's inventory. 
	 *  Will also display the quantity of the consumables/stackable items while showing
	 *  the reamaining durability/uses of the non-consumables (i.e. Toys).
	 *  
	 *  @param pane The JTextPane that displays the player's inventory which will be updated.
	 *  @param player The player's inventory that will be searched.
	 */
	private void updateInventoryDisplay(JTextPane pane, Player player) {
		//Display the player's inventory
		String inventory = "";
		int counter = 1;
		if(player.getInventory().size() == 0) {
			//Inventory is empty, tell user that it is
			inventory += "You have no items in your inventory.";
		} else {
			//Has items, display all items
			inventory += "Your inventory...";
			for(Item item : player.getInventory()) {
				if (item.returnIsStackable()){
					inventory += "\n" + counter + ") " + item.getName() + " x"+ item.getNumUses();
				} else {
					inventory += "\n" + counter +") " + item.getName() + " (Remaining durability: "+item.getNumUses()+")";
				}
				counter++;
			}
			
		}
		pane.setText(inventory);
	}
	
	/**
	 * Updates the label which displays the player's current money.
	 * 
	 * @param label The JLabel which displays the player's money.
	 * @param player The player who is shopping.
	 */
	private void updatePlayerMoneyDisplay(JLabel label, Player player) {
		label.setText(String.format("%1$.2f", player.getMoney()));
	}
	
	/**
	 * Update the JTextPane which displays the information of the product the user has selected and
	 * possibly wants to buy. Shows the item description, cost, restore value and if the player already owns the item.
	 * 
	 * @param pane The JTextPane which will display the item description and cost.
	 * @param product The product's information and cost that will be displayed.
	 */
	private void updateJTextPane(JTextPane pane, Item product, Player player) {
		String ownedMessage = searchForItem(player, product); //Adds an extra String saying if item is already owned
		String display = product.toString() + ownedMessage;
		pane.setText(display);
	}
	
	/**
	 * Method to simply update the JTextPane which displays and informs the player what actions
	 * have been performed.
	 * 
	 * @param textField The JTextPane that houses displays the messages.
	 * @param message The message that will be displayed.
	 */
	private void updateMessageDisplay(JTextPane textField, String message){
		textField.setText(message);
	}
	
	/**
	 * Constructs the JPanel and it's associated components which allows the user to 
	 * enter the number of days and players. 
	 */
	private void initializeGameSetUp() {
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		gameSetUpPanel = new JPanel();
		frame.getContentPane().add(gameSetUpPanel);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setFont(new Font("Verdana", labelLogo.getFont().getStyle(), labelLogo.getFont().getSize()));
		labelLogo.setBounds(354, 0, 135, 131);
		labelLogo.setIcon(new ImageIcon(UCLogo.getScaledInstance(135, 135, 1)));
		
		JLabel lblNumberOfDays = new JLabel("Number of Days:");
		lblNumberOfDays.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNumberOfDays.setBounds(103, 146, 120, 14);
		lblNumberOfDays.setForeground(Color.BLACK);
		
		JLabel lblCreatedByPatrick = new JLabel("Created by Patrick Ma and Eiran Ling");
		lblCreatedByPatrick.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblCreatedByPatrick.setBounds(96, 345, 296, 14);
		lblCreatedByPatrick.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of players:");
		lblNumberOfPlayers.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNumberOfPlayers.setBounds(93, 194, 135, 14);
		lblNumberOfPlayers.setForeground(Color.BLACK);
		
		JComboBox<Integer> cmBoxPlayers = new JComboBox<Integer>();
		cmBoxPlayers.setBounds(254, 191, 108, 20);
		// add stuff to the dropdown box
		cmBoxPlayers.addItem(1);
		cmBoxPlayers.addItem(2);
		cmBoxPlayers.addItem(3);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(254, 143, 108, 20);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Verdana", btnNext.getFont().getStyle(), btnNext.getFont().getSize()));
		btnNext.setBounds(263, 247, 90, 23);
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String input = formattedTextField.getText();
				boolean isValid = false;
				// try to parse the input to an integer, if it can't display an error message box.
				try{
					Integer.parseInt(input);
					isValid = true;
					if(Integer.parseInt(input) <= 0) {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException Exception) {
					JOptionPane.showMessageDialog(frame, "Please enter an integer between 1 and 2147483647 for days.", "Error", 2);
					isValid = false;
				}
				if (isValid){
					gameModel.setDays(Integer.parseInt(input));
					int numPlayers = (int) cmBoxPlayers.getSelectedItem();
					initializePlayerSetUp(numPlayers);

					showPlayerSetUp();
				}
			}
		});
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setFont(new Font("Verdana", btnHelp.getFont().getStyle(), btnHelp.getFont().getSize()));
		btnHelp.setBounds(125, 247, 92, 23);
		btnHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				HelpGUI helpbox = new HelpGUI(frame.getLocation().x, frame.getLocation().y);
			}
		});
		gameSetUpPanel.setLayout(null);
		
		JLabel lblTomagatchiGame = new JLabel("Virtual Pet\r\n");
		lblTomagatchiGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblTomagatchiGame.setBounds(163, 64, 150, 26);
		lblTomagatchiGame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblTomagatchiGame.setForeground(Color.BLACK);
		lblTomagatchiGame.setFont(new Font("Verdana", Font.PLAIN, 21));
		gameSetUpPanel.add(lblTomagatchiGame);
		gameSetUpPanel.add(labelLogo);
		gameSetUpPanel.add(lblNumberOfDays);
		gameSetUpPanel.add(formattedTextField);
		gameSetUpPanel.add(lblNumberOfPlayers);
		gameSetUpPanel.add(cmBoxPlayers);
		gameSetUpPanel.add(btnHelp);
		gameSetUpPanel.add(btnNext);
		gameSetUpPanel.add(lblCreatedByPatrick);
		
		JLabel lblBkgd = new JLabel("");
		lblBkgd.setBounds(0, 0, 489, 371);
		lblBkgd.setIcon(new ImageIcon(greenBkgd.getScaledInstance(489, 371, 1)));
		gameSetUpPanel.add(lblBkgd);
		gameSetUpPanel.revalidate();
		gameSetUpPanel.repaint();
		
		

	}
	
	/**
	 * Construct the score board, consists of labels which display each player and their score. 
	 * Labels may be left blank to so the number of shown scores match the number of players.
	 */
	private void initializeScoreBoardSetUp() {
		scoreBoardPanel = new JPanel();
		scoreBoardPanel.setBounds(0, 0, 479, 313);
		frame.getContentPane().add(scoreBoardPanel);
		scoreBoardPanel.setLayout(null);
		ArrayList<Player> players = gameModel.getPlayers();
		gameModel.calculateScore();
		
		JLabel lblScoreBoard = new JLabel("Score Board");
		lblScoreBoard.setFont(new Font("Verdana", Font.BOLD, 17));
		lblScoreBoard.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreBoard.setBounds(166, 12, 147, 32);
		scoreBoardPanel.add(lblScoreBoard);
		
		JLabel lblPlayerName1 = new JLabel(gameModel.getPlayerName(gameModel.getPlayers().get(0)));
		lblPlayerName1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayerName1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerName1.setBounds(90, 67, 104, 14);
		scoreBoardPanel.add(lblPlayerName1);
		
		JLabel lblPlayerScore1 = new JLabel(String.format("%1$.2f", gameModel.getPlayers().get(0).getScore()));
		lblPlayerScore1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayerScore1.setBounds(284, 67, 104, 14);
		scoreBoardPanel.add(lblPlayerScore1);
		
		if (players.size() > 1) {
		JLabel lblPlayerName2 = new JLabel(gameModel.getPlayerName(gameModel.getPlayers().get(1)));
		lblPlayerName2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayerName2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerName2.setBounds(90, 148, 104, 14);
		scoreBoardPanel.add(lblPlayerName2);
		
		JLabel lblPlayerScore2 = new JLabel(String.format("%1$.2f", gameModel.getPlayers().get(1).getScore()));
		lblPlayerScore2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayerScore2.setBounds(284, 148, 104, 14);
		scoreBoardPanel.add(lblPlayerScore2);
		}
		
		if (players.size() > 2){
		JLabel lblPlayerName3 = new JLabel(gameModel.getPlayerName(gameModel.getPlayers().get(2)));
		lblPlayerName3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayerName3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerName3.setBounds(90, 229, 104, 14);
		scoreBoardPanel.add(lblPlayerName3);
		
		JLabel lblPlayerScore3 = new JLabel(String.format("%1$.2f", gameModel.getPlayers().get(2).getScore()));
		lblPlayerScore3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayerScore3.setBounds(284, 229, 104, 14);
		scoreBoardPanel.add(lblPlayerScore3);
		}
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Verdana", Font.BOLD, 12));
		btnQuit.setBounds(282, 268, 110, 32);
		btnQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//disposes of the frame.
				frame.setVisible(false);
				frame.dispose();
			}
		});
		scoreBoardPanel.add(btnQuit);
		
		JButton btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.setFont(new Font("Verdana", Font.BOLD, 12));
		btnPlayAgain.setBounds(86, 268, 110, 32);
		btnPlayAgain.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//restarts the whole thing.
				frame.setVisible(false);
				frame.dispose();
				main(new String[]{""});
			}
		});
		scoreBoardPanel.add(btnPlayAgain);
		
		JLabel lblBkgd = new JLabel("");
		lblBkgd.setBounds(0, 0, 508, 413);
		lblBkgd.setIcon(new ImageIcon(greenBkgd.getScaledInstance(508, 413, 1)));
		scoreBoardPanel.add(lblBkgd);
		
		
		
	}
	
	/**
	 * Constructs the JPanel which houses all the components needed for user to enter the player's names in the 
	 * text fields. Based on the number of players entered in play from the game set up, the appropriate number of
	 * entry fields will be disabled to match the number of players in play.
	 * 
	 * @param The number of players in the game, value obtained from the game set up.
	 */
	private void initializePlayerSetUp(int numPlayers){		
		playerSetUpPanel = new JPanel();
		playerSetUpPanel.setBounds(0, 0, 490, 371);
		frame.getContentPane().add(playerSetUpPanel);
		playerSetUpPanel.setLayout(null);
		
		JLabel lblPlayerNames = new JLabel("Customise Players");
		lblPlayerNames.setFont(new Font("Verdana", Font.BOLD, 21));
		lblPlayerNames.setBounds(107, 12, 262, 36);
		playerSetUpPanel.add(lblPlayerNames);
		
		
		JTextField player1TextField = new JTextField();
		player1TextField.setBounds(259, 86, 114, 20);
		playerSetUpPanel.add(player1TextField);
		player1TextField.setColumns(10);
		
		JLabel lblPlayer = new JLabel("Player 1:");
		lblPlayer.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayer.setBounds(167, 88, 74, 16);
		playerSetUpPanel.add(lblPlayer);
		
		JTextField player2TextField = new JTextField();
		player2TextField.setBounds(259, 118, 114, 20);
		playerSetUpPanel.add(player2TextField);
		player2TextField.setColumns(10);
		
		JLabel lblPlayer2 = new JLabel("Player 2:");
		lblPlayer2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayer2.setBounds(167, 120, 74, 16);
		playerSetUpPanel.add(lblPlayer2);
	
		
		JLabel lblPlayer3 = new JLabel("Player 3:");
		lblPlayer3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayer3.setBounds(167, 152, 74, 16);
		playerSetUpPanel.add(lblPlayer3);
		
		JTextField player3TextField = new JTextField();
		player3TextField.setBounds(259, 150, 114, 20);
		playerSetUpPanel.add(player3TextField);
		player3TextField.setColumns(10);
		//automatically disables the textfields on initialisation.

		player3TextField.setEnabled(false);
		player2TextField.setEnabled(false);
		
		//enables the textfields if the number of players exceed more than available.
		if (numPlayers >= 2){
			player2TextField.setEnabled(true);	
		}
		
		if (numPlayers == 3){
			player3TextField.setEnabled(true);
		}
		//end loop here.
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Verdana", Font.BOLD, 12));
		btnNext.setBounds(369, 273, 98, 26);
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if ((player1TextField.getText().equals("") && player1TextField.isEnabled()) || (player2TextField.getText().equals("") && player2TextField.isEnabled()) || (player3TextField.getText().equals("") && player3TextField.isEnabled())){
					JOptionPane.showMessageDialog(frame, "Please enter a name.", "Error", 2);
				} else {
						boolean isValid = true;
						String[] players = new String[3];
						players[0] = player1TextField.getText();
						if (numPlayers >= 2){
							if (player2TextField.getText().equals(player1TextField.getText())){
								JOptionPane.showMessageDialog(frame, "Players cannot have the same name.", "HEY! Listen!", 2);
								isValid = false;
							} else {
								players[1] = player2TextField.getText();
							
								if (numPlayers == 3){
									if (player3TextField.getText().equals(player2TextField.getText())||player3TextField.getText().equals(player1TextField.getText())){
										JOptionPane.showMessageDialog(frame, "Players cannot have the same name.", "HEY! Listen!", 2);
										isValid = false;
									} else 	{
										players[2] = player3TextField.getText();
									}
							}
							
							
							}
						}
						if (isValid){
							gameModel.addPlayers(players);
							//Start a recursive call which iterates as many times are there
							//are players. Begins set up for each player choosing their pets.
							initializePetSetUp(0);
							showPetSetUp();
						}
							
							

				}
			}
		});
		playerSetUpPanel.add(btnNext);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack_1.setBounds(12, 273, 98, 26);
		btnBack_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.remove(playerSetUpPanel);
				frame.revalidate();
				frame.repaint();
				showGameSetUp();
			}
		});
		playerSetUpPanel.add(btnBack_1);
		
		JLabel lblBkgd = new JLabel("");
		lblBkgd.setBounds(0, 0, 490, 371); 
		lblBkgd.setIcon(new ImageIcon(greenBkgd.getScaledInstance(500, 380, 1)));
		playerSetUpPanel.add(lblBkgd);
	}
}
