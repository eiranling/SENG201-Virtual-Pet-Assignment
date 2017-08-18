package JUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Game.Item;
import Game.Player;
import food.Fish;
import food.Fruit;
import medicine.PetMedicine;
import pets.Cat;
import pets.Pets;

public class FishTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Test if I can break a fish. Since it is a consumable, should expect it to fail and throw an exception
	 * if there are still uses.
	 */
	@Test
	public void testBreakFish() {
		Item testFish = new Fish();
		Player itemOwner = new Player(new ArrayList<Pets>(), "Test Player");
		itemOwner.addItem(testFish);
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Can't remove/break a consumable when still has uses.");
		testFish.breakItem(itemOwner);
		
		
	}
	
	
	/**
	 * Test if we can accurately check if two different instances are fishes.
	 */
	@Test
	public void testEquals() {
		Item fish1 = new Fish();
		Item fish2 = new Fish();
		Item notFish = new PetMedicine();
		
		assertTrue(fish1.equals(fish2));
		assertFalse(fish1.equals(notFish));
	}
	
	/**
	 * Test the hash code generated by the fish item.
	 */
	@Test
	public void testHashCode() {
		Item fish = new Fish();
		double restoreValue = fish.getRestoreValue();
		int hashCode = (int) restoreValue * 31 + 17;
		assertEquals(hashCode, fish.hashCode());
	}
	
	/**
	 * Test if we can increase the number of uses of a fish instance. 
	 */
	@Test
	public void testIncreaseNumUses() {
		Item testFish = new Fish();
		testFish.increaseNumUses();
		assertEquals(2, testFish.getNumUses());
		testFish.increaseNumUses(2);
		assertEquals(4, testFish.getNumUses());
	}

	/**
	 * Test if a single consumable food item added to the player's inventory will be
	 * removed after its use. Inventory should be empty afterwards.
	 */
	@Test
	public void testUseFish() {
		ArrayList<Pets> pets = new ArrayList<Pets>();
		pets.add(new Cat("Test Cat"));
		Player testPlayer = new Player(pets, "Test Player");
		testPlayer.addItem(new Fish());
		
		Pets testPet = testPlayer.getPetArray().get(0);
		
		testPlayer.getInventory().get(0).use(testPlayer, testPet);
		
		assertEquals(0, testPlayer.getInventory().size());
	}
	
	/**
	 * Test if multiple food instances (that are stacked in the player's inventory) 
	 * will be removed after use.
	 */
	@Test
	public void testUseStackedFish() {
		ArrayList<Pets> pets = new ArrayList<Pets>();
		pets.add(new Cat("Test Cat"));
		Player testPlayer = new Player(pets, "Test Player");
		testPlayer.addItem(new Fish());
		testPlayer.addItem(new Fish());
		
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
	 * Test it correctly identifies a fish instance to be a food item.
	 */
	@Test
	public void testIsFood() {
		Item testFood = new Fish();
		assertFalse(testFood.isMedicine());
		assertTrue(testFood.isFood());
		assertFalse(testFood.isToy());
	}

}
