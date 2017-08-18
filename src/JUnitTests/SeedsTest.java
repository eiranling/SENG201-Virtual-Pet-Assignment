package JUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Game.Item;
import Game.Player;
import food.Seeds;
import food.Steak;
import medicine.PetMedicine;
import pets.Cat;
import pets.Pets;

public class SeedsTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Test if I can break a seeds. Since it is a consumable, should expect it to fail and throw an exception
	 * if there are still uses.
	 */
	@Test
	public void testBreakSeeds() {
		Item testSeeds = new Seeds();
		Player itemOwner = new Player(new ArrayList<Pets>(), "Test Player");
		itemOwner.addItem(testSeeds);
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Can't remove/break a consumable when still has uses.");
		testSeeds.breakItem(itemOwner);
		
		
	}
	
	
	/**
	 * Test if we can accurately check if two different instances are seeds.
	 */
	@Test
	public void testEquals() {
		Item seeds1 = new Seeds();
		Item seeds2 = new Seeds();
		Item notSeeds = new PetMedicine();
		
		assertTrue(seeds1.equals(seeds2));
		assertFalse(seeds1.equals(notSeeds));
	}
	
	/**
	 * Test the hash code generated by the seeds item.
	 */
	@Test
	public void testHashCode() {
		Item seeds = new Seeds();
		double restoreValue = seeds.getRestoreValue();
		int hashCode = (int) restoreValue * 31 + 17;
		assertEquals(hashCode, seeds.hashCode());
	}
	
	/**
	 * Test if we can increase the number of uses of a seeds instance. 
	 */
	@Test
	public void testIncreaseNumUses() {
		Item testSeeds = new Seeds();
		testSeeds.increaseNumUses();
		assertEquals(2, testSeeds.getNumUses());
		testSeeds.increaseNumUses(2);
		assertEquals(4, testSeeds.getNumUses());
	}

	/**
	 * Test if a single consumable food item added to the player's inventory will be
	 * removed after its use. Inventory should be empty afterwards.
	 */
	@Test
	public void testUseSeeds() {
		ArrayList<Pets> pets = new ArrayList<Pets>();
		pets.add(new Cat("Test Cat"));
		Player testPlayer = new Player(pets, "Test Player");
		testPlayer.addItem(new Seeds());
		
		Pets testPet = testPlayer.getPetArray().get(0);
		
		testPlayer.getInventory().get(0).use(testPlayer, testPet);
		
		assertEquals(0, testPlayer.getInventory().size());
	}
	
	/**
	 * Test if multiple food instances (that are stacked in the player's inventory) 
	 * will be removed after use.
	 */
	@Test
	public void testUseStackedSeeds() {
		ArrayList<Pets> pets = new ArrayList<Pets>();
		pets.add(new Cat("Test Cat"));
		Player testPlayer = new Player(pets, "Test Player");
		testPlayer.addItem(new Seeds());
		testPlayer.addItem(new Seeds());
		
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
	 * Test it correctly identifies a seeds instance to be a food item.
	 */
	@Test
	public void testIsFood() {
		Item testFood = new Seeds();
		assertFalse(testFood.isMedicine());
		assertTrue(testFood.isFood());
		assertFalse(testFood.isToy());
	}
}