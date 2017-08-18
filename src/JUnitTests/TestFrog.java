package JUnitTests;
import junit.framework.TestCase;

import medicine.*;
import pets.Frog;
import pets.Pets;
import toy.*;

import org.junit.Test;
import org.junit.Before;
import food.*;

public class TestFrog extends TestCase{
	
	private static Pets animal;
	@Before
	public void setUp(){
		animal = new Frog("Tibbles");
	}
	
	@Test
	//checks that the animal is constructed properly.
	public void testPetNormal() {
		assertTrue(animal.getDescription() == "A slimy animal that does not enjoy playing much, but enjoys eating and won't get sick as often.");
		assertTrue(animal.getName() == "Tibbles");
		assertTrue(animal.checkSick() == false);
		assertTrue(animal.checkMisbehaving() == false);
		assertTrue(animal.getSpecies() == "Frog");
		assertTrue(animal.checkFavouriteToy().equals(new Pool()));
		assertTrue(animal.checkfavouriteFood().equals(new Fly()));
		assertTrue(animal.getHappiness() == 50);
		assertTrue(animal.getActionsLeft() == 2);
		assertTrue(animal.getEnergy() == 70);
		assertTrue(animal.getNeedForToilet() == 25);
		assertTrue(animal.getHealth() == 100);
		assertTrue(animal.getHunger() == 100);
	}
	
	@Test
	public void testPetEmpty(){
		Pets empty = new Frog("");
		assertTrue(empty.getDescription() == "A slimy animal that does not enjoy playing much, but enjoys eating and won't get sick as often.");
		assertTrue(empty.getName() == "");
		assertTrue(empty.checkSick() == false);
		assertTrue(empty.checkMisbehaving() == false);
		assertTrue(empty.getSpecies() == "Frog");
	}
	
	@Test
	public void testEat() {
		animal.setHunger(50); //lowers the hunger
		animal.eat(new Fly());
		assertEquals(new Fly().getRestoreValue() + 50, animal.getHunger());
		assertTrue(animal.getActionsLeft()==1);
		assertTrue(50+new Fly().getRestoreValue()*0.4*2.5 == animal.getHappiness());
		assertTrue(25+new Fly().getRestoreValue()*0.15 == animal.getNeedForToilet());
		Pets animal2 = new Frog("epeP");
		animal2.setHunger(50);
		animal2.eat(new Rat());
		assertTrue(animal2.getHunger() == 50);
		assertTrue(animal2.getActionsLeft() == 2);
	}
	
	@Test
	public void testDieThroughEat(){
		animal.setNeedForToilet(99.5);
		animal.eat(new Fly());
		assertTrue(animal.checkDead());
		assertTrue(animal.getNeedForToilet() == 100);
		assertTrue(animal.getActionsLeft() == 1);
	}
	
	@Test
	public void testUseToilet() {
		Pets animal = new Frog("Pepe");
		assertEquals("Pepe used the litter bin and gained 5.00 happiness!", animal.useToilet());
		assertTrue(animal.getNeedForToilet() == 0);
		assertTrue(animal.getHappiness() == 55);
		assertTrue(animal.getActionsLeft() == 1);
		
	}
	
	@Test
	public void testPlayWithToy() {
		Pets animal = new Frog("Pepe");
		double startWeight = animal.getWeight();
		animal.playWithToy(new Pool());
		assertTrue(animal.getEnergy() == 70 - new Pool().getRestoreValue()*0.35*0.9);
		assertTrue(animal.getHappiness() == 50+new Pool().getRestoreValue()*0.9);
		assertTrue(animal.getWeight() == startWeight - new Pool().getRestoreValue() * 0.1);
		assertTrue(animal.getActionsLeft() == 1);
		
	}
	
	@Test
	public void testInvalidToy(){
		animal.setEnergy(0);
		String initialStats = animal.toString();
		animal.playWithToy(new Pool());
		assertEquals(initialStats, animal.toString());
		assertTrue(animal.getActionsLeft() == 2);
	}
	
	@Test
	public void testSleep() {
		animal.sleep();
		//want to primarily check the key parts of sleep.
		assertTrue(animal.getEnergy() == 100);
		assertTrue(animal.getHunger() == 67);
		assertTrue(animal.getHappiness() == 50);
		assertTrue(animal.getActionsLeft() == 1);
		
	}
	
	@Test
	public void testCantSleep(){
		animal.setHunger(1);
		String initialStats = animal.toString();
		animal.sleep();
		assertEquals(initialStats, animal.toString());
		animal.setHunger(32);
		initialStats = animal.toString();
		animal.sleep();
		assertEquals(initialStats, animal.toString());
		assertTrue(animal.getActionsLeft() == 2);
		animal.setHunger(33);
		animal.sleep();
		assertTrue(animal.getActionsLeft()==2);
	}

	@Test
	public void testRevive(){
		animal.die(0);
		animal.revive();
		assertFalse(animal.checkDead());
		assertTrue(animal.checkDied());
		animal.die(0);
		animal.revive();
		assertTrue(animal.checkDead());
	}
	
	@Test
	public void testNoActions(){
		animal.sleep();
		animal.sleep();
		assertTrue(animal.getActionsLeft() == 0);
		String initialStats = animal.toString();
		animal.sleep();
		assertEquals(initialStats, animal.toString());
		animal.eat(new Fly());
		assertEquals(initialStats, animal.toString());
		animal.playWithToy(new Pool());
		assertEquals(initialStats, animal.toString());
		animal.heal(new PetMedicine());
		assertEquals(initialStats, animal.toString());
	}
	
	@Test
	public void testDead(){
		animal.die(0);
		animal.sleep();
		animal.playWithToy(new Pool());
		animal.eat(new Fly());
		animal.heal(new PetMedicine());
		assertTrue(animal.getActionsLeft() == 2);
	}
	
	@Test
	public void testHeal(){
		animal.setHealth(20);
		animal.setSick(true);
		animal.heal(new PetMedicine());
		assertTrue(animal.getHealth() == 40);
		assertTrue(animal.checkSick() == false);
		assertTrue(animal.getHappiness() == 50 - 20*0.15);
	}
	
	@Test
	public void testGetSick(){
		animal.getsSick(3);
		assertFalse(animal.checkSick());
		animal.getsSick(2);
		assertTrue(animal.checkSick());
	}



}
