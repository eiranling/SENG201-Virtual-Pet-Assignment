package JUnitTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Game.Item;
import Game.Player;
import food.Fish;
import food.Food;
import food.Seeds;
import medicine.Bandage;
import medicine.Medicine;
import medicine.PetMedicine;
import pets.Cat;
import pets.Dog;
import pets.Hamster;
import pets.Pets;
import toy.Pool;
import toy.Slide;
import toy.Toy;
import toy.WoolBall;

public class PlayerTest {
	/**
	 * JUnit test class for testing the associated method with the Player class.
	 */
	private Player testPlayer;
	ArrayList<Pets> testPetsArray;
	
	@Before
	public void setUp() throws Exception {
		testPetsArray = new ArrayList<Pets>();
		testPetsArray.add(new Cat("Test Cat"));
		testPetsArray.add(new Dog("Test Dog"));
		testPetsArray.add(new Hamster("Test Hamster"));
		
		testPlayer = new Player(testPetsArray, "Player1");
	}

	/**
	 * Test the player constructor create players correctly.
	 */
	@Test
	public void testPlayer() {
		ArrayList<Pets> validPetArray = new ArrayList<Pets>();
		validPetArray.add(new Cat("Test Cat"));
		validPetArray.add(new Dog("Test Dog"));
		validPetArray.add(new Hamster("Test Hamster"));
		
		assertEquals(0, testPlayer.getScore(), 0);
		assertEquals("Player1", testPlayer.getName());
		assertEquals(1000, testPlayer.getMoney(), 0);
		assertEquals(new ArrayList<Item>(), testPlayer.getInventory());
		
		assertEquals(validPetArray.get(0).getName(), testPlayer.getPetArray().get(0).getName());
		assertEquals(validPetArray.get(1).getName(), testPlayer.getPetArray().get(1).getName());
		assertEquals(validPetArray.get(2).getName(), testPlayer.getPetArray().get(2).getName());
	}

	/**
	 * Test if a stack-able item can be added to a player's inventory. If
	 * multiple of the same item are added, only the number of uses should increase and
	 * should not be counted as a separate item in the player's inventory.
	 */
	@Test
	public void testAddStackableItem() {
		Food testSeeds = new Seeds();
		testPlayer.addItem(new Seeds());
		//Added stack-able item, should be one type of food in inventory
		assertEquals(1, testPlayer.getInventory().size()); 
		assertTrue(testSeeds.equals(testPlayer.getInventory().get(0)));
		
		//Only one seed added, should only have one use
		assertEquals(1, testPlayer.getInventory().get(0).getNumUses()); 
		
		//Added another seed, it's a stackable item
		testPlayer.addItem(new Seeds());
		assertEquals(1, testPlayer.getInventory().size(), 0); //Stacks, only one unique item
		assertEquals(2, testPlayer.getInventory().get(0).getNumUses(), 0); //Number of uses increased by 1
	}
	
	/**
	 * Test if a non-stackable item can be added to the player's inventory. If different
	 * toys are added they should be counted as separate elements in the player's inventory.
	 */
	@Test
	public void testAddNonStackableItem() {
		//Add toy, and medicine, test if we can add multiple types
		Toy testSlide = new Slide();
		Medicine testBandage = new Bandage();
		
		testPlayer.addItem(new Slide());
		assertEquals(1, testPlayer.getInventory().size(), 0);
		
		
		testPlayer.addItem(new Bandage());
		assertEquals(2, testPlayer.getInventory().size(), 0);
		
		assertTrue(testSlide.equals(testPlayer.getInventory().get(0)));
		assertTrue(testBandage.equals(testPlayer.getInventory().get(1)));
	}

	/**
	 * Test if the players score changes correctly.
	 */
	@Test
	public void testChangeScore() {
		testPlayer.changeScore(50);
		assertEquals(50, testPlayer.getScore(), 0);
		testPlayer.changeScore(-30);
		assertEquals(20, testPlayer.getScore(), 0);
	}

	/**
	 * See if a player disciplines a pet.
	 */
	@Test
	public void testDiscipline() {
		//Force the pet to misbehave.
		while (!testPlayer.getPetArray().get(0).checkMisbehaving()){
			testPlayer.getPetArray().get(0).willMisbehave();
		}
		assertTrue(testPlayer.getPetArray().get(0).checkMisbehaving()); //Make sure pet is misbehaving in the first place
		
		Pets testPet = testPlayer.getPetArray().get(0);
		testPlayer.discipline(testPet);
		assertFalse(testPlayer.getPetArray().get(0).checkMisbehaving());
		
	}
	
	/**
	 * Test that a player correctly earns money, i.e. their money attribute correctly increases
	 */
	@Test
	public void testEarnedMoney() {
		testPlayer.earnedMoney(100);
		assertEquals(1100, testPlayer.getMoney(), 0);
	}
	
	
	/**
	 * Test if a player has inventory space, can only have a max of 1 type of toy.
	 */
	@Test
	public void testHasInventorySpace() {
		//Can only have a max of five non stack-able objects
		assertEquals(0, testPlayer.getInventory().size(), 0); //Should be empty
		assertTrue(testPlayer.hasInventorySpace(new Pool()));
	
		testPlayer.addItem(new Pool());
		
		//Can only have one type of toy at a time
		assertFalse(testPlayer.hasInventorySpace(new Pool())); 
		
		//Can have as many stack-able items as possible
		assertTrue(testPlayer.hasInventorySpace(new Seeds()));
		for(int i = 0; i < 10; i++) {
			testPlayer.addItem(new Seeds());
		}
		assertTrue(testPlayer.hasInventorySpace(new Seeds()));
	}
	

	/**
	 * Test if a player can put a pet to sleep.
	 */
	@Test
	public void testRest() {
		assertEquals(2, testPlayer.getPetArray().get(0).getActionsLeft(),0);
		Pets petToPutToSleep = testPlayer.getPetArray().get(0);
		testPlayer.rest(petToPutToSleep);
		assertEquals(1, testPlayer.getPetArray().get(0).getActionsLeft(),0);
	}

	/**
	 * Test if a player can revive a dead pet.
	 */
	@Test
	public void testRevive() {
		Pets petToKill = testPlayer.getPetArray().get(0);
		petToKill.die(0);
		assertTrue(testPlayer.getPetArray().get(0).checkDead());
		testPlayer.revive(petToKill);
		assertFalse(testPlayer.getPetArray().get(0).checkDead());
	}
	
	/**
	 * Test if a player can spend money.
	 */
	@Test
	public void testSpendMoney() {
		testPlayer.spendMoney(100);
		assertEquals(900, testPlayer.getMoney(), 0);
	}
	
	/**
	 * Test if a player can let a pet go to the toilet.
	 */
	@Test
	public void testTakeToToilet() {
		Pets petToGetSchwifty = testPlayer.getPetArray().get(0);
	
		assertEquals(2, testPlayer.getPetArray().get(0).getActionsLeft(),0);
		testPlayer.takeToToilet(petToGetSchwifty);
		assertEquals(0, testPlayer.getPetArray().get(0).getNeedForToilet(), 0);
		assertEquals(1, testPlayer.getPetArray().get(0).getActionsLeft(),0);
	}

	/**
	 * Test if a player can feed their pet.
	 */
	@Test
	public void testFeed() {
		Item testFish = new Fish();
		Pets testCat = testPlayer.getPetArray().get(0);
		double newNeedForToilet = testFish.getRestoreValue() * 0.15 + testCat.getNeedForToilet();
		testPlayer.feed(testCat, testFish);
		
		assertEquals(newNeedForToilet, testCat.getNeedForToilet(), 0);
		
	}

	/**
	 * Test if a player can play with their pet.
	 */
	@Test
	public void testPlayWithPet() {
		Item testWoolBall = new WoolBall();
		Pets testCat = testPlayer.getPetArray().get(0);
		double newEnergy = testCat.getEnergy()- testWoolBall.getRestoreValue()*0.35;
		
		testPlayer.playWithPet(testCat, testWoolBall);
		assertEquals(newEnergy, testCat.getEnergy(), 0);
	}

	
	/**
	 * Test if a player can heal their pet.
	 */
	@Test
	public void testHealPet() {
		Pets testCat = testPlayer.getPetArray().get(0);
		while(!testCat.checkSick()) {
			testCat.getsSick();
		}
		assertTrue(testCat.checkSick());
		Item testMedicine = new PetMedicine();
		testPlayer.heal(testCat, testMedicine);
		assertFalse(testCat.checkSick());
	}









}
