package JUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Game.Item;
import Game.Player;
import food.Fruit;
import food.Steak;
import medicine.PetMedicine;
import pets.Cat;
import pets.Pets;

public class FruitTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Test if I can break a fruit. Since it is a consumable, should expect it to fail and throw an exception
	 * if there are still uses.
	 */
	@Test
	public void testBreakFruit() {
		Item testFruit = new Fruit();
		Player itemOwner = new Player(new ArrayList<Pets>(), "Test Player");
		itemOwner.addItem(testFruit);
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Can't remove/break a consumable when still has uses.");
		testFruit.breakItem(itemOwner);
		
		
	}
	
	
	/**
	 * Test if we can accurately check if two different instances are fruit.
	 */
	@Test
	public void testEquals() {
		Item fruit1 = new Fruit();
		Item fruit2 = new Fruit();
		Item notFruit = new PetMedicine();
		
		assertTrue(fruit1.equals(fruit2));
		assertFalse(fruit1.equals(notFruit));
	}
	
	/**
	 * Test the hash code generated by the fruit item.
	 */
	@Test
	public void testHashCode() {
		Item fruit = new Fruit();
		double restoreValue = fruit.getRestoreValue();
		int hashCode = (int) restoreValue * 31 + 17;
		assertEquals(hashCode, fruit.hashCode());
	}
	
	/**
	 * Test if we can increase the number of uses of a fruit instance. 
	 */
	@Test
	public void testIncreaseNumUses() {
		Item testFruit = new Fruit();
		testFruit.increaseNumUses();
		assertEquals(2, testFruit.getNumUses());
		testFruit.increaseNumUses(2);
		assertEquals(4, testFruit.getNumUses());
	}

	/**
	 * Test if a single consumable food item added to the player's inventory will be
	 * removed after its use. Inventory should be empty afterwards.
	 */
	@Test
	public void testUseFruit() {
		ArrayList<Pets> pets = new ArrayList<Pets>();
		pets.add(new Cat("Test Cat"));
		Player testPlayer = new Player(pets, "Test Player");
		testPlayer.addItem(new Fruit());
		
		Pets testPet = testPlayer.getPetArray().get(0);
		
		testPlayer.getInventory().get(0).use(testPlayer, testPet);
		
		assertEquals(0, testPlayer.getInventory().size());
	}
	
	/**
	 * Test if multiple food instances (that are stacked in the player's inventory) 
	 * will be removed after use.
	 */
	@Test
	public void testUseStackedFruit() {
		ArrayList<Pets> pets = new ArrayList<Pets>();
		pets.add(new Cat("Test Cat"));
		Player testPlayer = new Player(pets, "Test Player");
		testPlayer.addItem(new Fruit());
		testPlayer.addItem(new Fruit());
		
		Pets testPet = testPlayer.getPetArray().get(0);
		
		assertEquals(1, testPlayer.getInventory().size());
		assertEquals(2, testPlayer.getInventory().get(0).getNumUses());
		
		testPlayer.getInventory().get(0).use(testPlayer, testPet);
		
		assertEquals(1, testPlayer.getInventory().size());
		assertEquals(1, testPlayer.getInventory().get(0).getNumUses());
		
		testPlayer.getInventory().get(0).use(testPlayer, testPet);
		
		assertEquals(0, testPlayer.getInventory().size());
	}
	
	/**
	 * Test it correctly identifies a fruit instance to be a food item.
	 */
	@Test
	public void testIsFood() {
		Item testFood = new Fruit();
		assertFalse(testFood.isMedicine());
		assertTrue(testFood.isFood());
		assertFalse(testFood.isToy());
	}

}
