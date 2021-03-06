package JUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Game.Item;
import Game.Player;
import medicine.Bandage;
import medicine.PetMedicine;
import pets.Cat;
import pets.Pets;

public class BandageTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Test if I can break a bandage. Since it is a consumable, should expect it to fail and throw an exception
	 * if there are still uses.
	 */
	@Test
	public void testBreakBandage() {
		Item testBandage = new Bandage();
		Player itemOwner = new Player(new ArrayList<Pets>(), "Test Player");
		itemOwner.addItem(testBandage);
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Can't remove/break a consumable when still has uses.");
		testBandage.breakItem(itemOwner);
		
		
	}
	
	
	/**
	 * Test if we can accurately check if two different instances are bandages.
	 */
	@Test
	public void testEquals() {
		Item bandage1 = new Bandage();
		Item bandage2 = new Bandage();
		Item notBandage = new PetMedicine();
		
		assertTrue(bandage1.equals(bandage2));
		assertFalse(bandage1.equals(notBandage));
	}
	
	/**
	 * Test the hash code generated by the bandage item.
	 */
	@Test
	public void testHashCode() {
		Item bandage = new Bandage();
		double restoreValue = bandage.getRestoreValue();
		int hashCode = (int) restoreValue * 31 + 17;
		assertEquals(hashCode, bandage.hashCode());
	}
	
	/**
	 * Test if we can increase the number of uses of a bandage instance. 
	 */
	@Test
	public void testIncreaseNumUses() {
		Item testBandage = new Bandage();
		testBandage.increaseNumUses();
		assertEquals(2, testBandage.getNumUses());
		testBandage.increaseNumUses(2);
		assertEquals(4, testBandage.getNumUses());
	}

	/**
	 * Test if a single consumable medicine item added to the player's inventory will be
	 * removed after its use. Inventory should be empty afterwards.
	 */
	@Test
	public void testUseBandage() {
		ArrayList<Pets> pets = new ArrayList<Pets>();
		pets.add(new Cat("Test Cat"));
		Player testPlayer = new Player(pets, "Test Player");
		testPlayer.addItem(new Bandage());
		
		Pets testPet = testPlayer.getPetArray().get(0);
		
		testPlayer.getInventory().get(0).use(testPlayer, testPet);
		
		assertEquals(0, testPlayer.getInventory().size());
	}
	
	/**
	 * Test if multiple medicine instances (that are stacked in the player's inventory) 
	 * will be removed after use.
	 */
	@Test
	public void testUseStackedBandage() {
		ArrayList<Pets> pets = new ArrayList<Pets>();
		pets.add(new Cat("Test Cat"));
		Player testPlayer = new Player(pets, "Test Player");
		testPlayer.addItem(new Bandage());
		testPlayer.addItem(new Bandage());
		
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
	 * Test it correctly identifies a bandage to be a medical item.
	 */
	@Test
	public void testIsMedicine() {
		Item testBandage = new Bandage();
		assertTrue(testBandage.isMedicine());
		assertFalse(testBandage.isFood());
		assertFalse(testBandage.isToy());
	}
	
}
