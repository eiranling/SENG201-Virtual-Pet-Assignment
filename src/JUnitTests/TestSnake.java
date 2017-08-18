package JUnitTests;
import junit.framework.TestCase;

import medicine.*;
import pets.Pets;
import pets.Snake;
import toy.*;

import org.junit.Test;
import org.junit.Before;

import food.*;

public class TestSnake extends TestCase{

	private static Pets animal;
	@Before
	public void setUp(){
		animal = new Snake("Tibbles");
	}
	
	@Test
	//checks that the animal is constructed properly.
	public void testPetNormal() {
		assertTrue(animal.getDescription() == "A slithery pet that needs more sleep, gains happiness slower and misbehaves often. However, it requires less food.");
		assertTrue(animal.getName() == "Tibbles");
		assertTrue(animal.checkSick() == false);
		assertTrue(animal.checkMisbehaving() == false);
		assertTrue(animal.getSpecies() == "Snake");
		assertTrue(animal.checkFavouriteToy().equals(new Tube()));
		assertTrue(animal.checkfavouriteFood().equals(new Rat()));
		assertTrue(animal.getHappiness() == 50);
		assertTrue(animal.getActionsLeft() == 2);
		assertTrue(animal.getEnergy() == 70);
		assertTrue(animal.getNeedForToilet() == 25);
		assertTrue(animal.getHealth() == 100);
		assertTrue(animal.getHunger() == 100);
	}
	
	@Test
	public void testPetEmpty(){
		Pets empty = new Snake("");
		assertTrue(empty.getDescription() == "A slithery pet that needs more sleep, gains happiness slower and misbehaves often. However, it requires less food.");
		assertTrue(empty.getName() == "");
		assertTrue(empty.checkSick() == false);
		assertTrue(empty.checkMisbehaving() == false);
		assertTrue(empty.getSpecies() == "Snake");
	}
	
	@Test
	public void testEat() {
		animal.setHunger(30); //lowers the hunger
		animal.eat(new Rat());
		assertEquals(new Rat().getRestoreValue()*2.5 + 30, animal.getHunger());
		assertTrue(animal.getActionsLeft()==1);
		assertTrue(50+new Rat().getRestoreValue()*0.2 == animal.getHappiness());
		assertTrue(25+new Rat().getRestoreValue()*0.15 == animal.getNeedForToilet());
	}
	
	@Test
	public void testDieThroughEat(){
		animal.setNeedForToilet(99);
		animal.eat(new Rat());
		assertTrue(animal.checkDead());
		assertTrue(animal.getNeedForToilet() == 100);
		assertTrue(animal.getActionsLeft() == 1);
	}
	
	@Test
	public void testUseToilet() {
		animal.useToilet();
		assertTrue(animal.getNeedForToilet() == 0);
		assertTrue(animal.getHappiness() == 55);
		assertTrue(animal.getActionsLeft() == 1);
		
	}
	
	@Test
	public void testPlayWithToy() {
		double startWeight = animal.getWeight();
		animal.playWithToy(new Tube());
		assertTrue(animal.getEnergy() == 70 - new Tube().getRestoreValue()*1.25);
		assertTrue(animal.getHappiness() == 50+new Tube().getRestoreValue()*0.5);
		assertTrue(animal.getWeight() == startWeight - new Tube().getRestoreValue() * 0.1);
		assertTrue(animal.getActionsLeft() == 1);
		
	}
	
	@Test
	public void testInvalidToy(){
		animal.setEnergy(1);
		animal.playWithToy(new Tube());
		assertTrue(animal.getActionsLeft() == 2);
	}
	
	@Test
	public void testSleep() {
		Pets animal = new Snake("Danger Noodle");
		animal.sleep();
		//want to primarily check the key parts of sleep.
		assertTrue(animal.getEnergy() == 95);
		assertTrue(animal.getHunger() == 90);
		assertTrue(animal.getHappiness() == 50);
		assertTrue(animal.getActionsLeft() == 1);
		
	}
	
	@Test
	public void testCantSleep(){
		animal.setHunger(1);
		String initialStats = animal.toString();
		animal.sleep();
		assertEquals(initialStats, animal.toString());
		animal.setHunger(10);
		initialStats = animal.toString();
		animal.sleep();
		assertEquals(initialStats, animal.toString());
		assertTrue(animal.getActionsLeft() == 2);

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
	public void testMisbehaves(){
		animal.willMisbehave(25);
		assertFalse(animal.checkMisbehaving());
		animal.willMisbehave(24);
		assertTrue(animal.checkMisbehaving());
	}

}
