package JUnitTests;
import static org.junit.Assert.*;	
import org.junit.Before;
import org.junit.Test;

import food.*;
import medicine.*;
import pets.Bird;
import pets.Pets;
import toy.*;

public class TestBird{
	
	private static Pets animal;
	
	@Before
	public void setUp(){
		animal = new Bird("Birb");
	}
	
	@Test
	//checks that the animal is constructed properly.
	public void testPetNormal() {
		assertTrue(animal.getDescription() == "A light cute pet that gains happiness very easily, but does not gain as much happiness from toys.");
		assertTrue(animal.getName() == "Birb");
		assertTrue(animal.checkSick() == false);
		assertTrue(animal.checkMisbehaving() == false);
		assertTrue(animal.getSpecies() == "Bird");
		assertTrue(animal.checkFavouriteToy().equals(new Slide()));
		assertTrue(animal.checkfavouriteFood().equals(new Fruit()));
		assertTrue(animal.getHappiness() == 50);
		assertTrue(animal.getActionsLeft() == 2);
		assertTrue(animal.getEnergy() == 70);
		assertTrue(animal.getNeedForToilet() == 25);
		assertTrue(animal.getHealth() == 100);
		assertTrue(animal.getHunger() == 100);
	}
	
	@Test
	public void testPetEmpty(){
		Pets empty = new Bird("");
		assertTrue(empty.getDescription() == "A light cute pet that gains happiness very easily, but does not gain as much happiness from toys.");
		assertTrue(empty.getName() == "");
		assertTrue(empty.checkSick() == false);
		assertTrue(empty.checkMisbehaving() == false);
		assertTrue(empty.getSpecies() == "Bird");
	}

	@Test
	public void testEat() {
		animal.setHunger(50); //lowers the hunger
		animal.eat(new Fruit());
		assertEquals(new Fruit().getRestoreValue() + 50, animal.getHunger(), 0);
		assertTrue(animal.getActionsLeft()==1);
		assertTrue(50+new Fruit().getRestoreValue()*0.4*2.25 == animal.getHappiness());
		assertTrue(25+new Fruit().getRestoreValue()*0.15 == animal.getNeedForToilet());
		Pets animal2 = new Bird("Birb");
		animal2.setHunger(50);
		animal2.eat(new Rat());
		assertTrue(animal2.getHunger() == 50);
		assertTrue(animal2.getActionsLeft() == 2);
	}
	
	@Test
	public void testDieThroughEat(){
		animal.setNeedForToilet(99);
		animal.eat(new Fruit());
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
		animal.playWithToy(new Slide());
		assertTrue(animal.getEnergy() == 70 - new Slide().getRestoreValue()*0.35*0.5);
		assertTrue(animal.getHappiness() == 50+new Slide().getRestoreValue()*0.5);
		assertTrue(animal.getWeight() == startWeight - new Slide().getRestoreValue() * 0.1);
		assertTrue(animal.getActionsLeft() == 1);
		
	}
	
	@Test
	public void testInvalidToy(){
		animal.setEnergy(0);
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
		assertTrue(animal.getActionsLeft() == 2);
	}

	@Test
	public void testRevive(){
		Pets animal = new Bird("Birb");
		animal.die(0);
		assertEquals("Birb has been revived!", animal.revive());
		assertEquals("Birb is not dead!", animal.revive());
		animal.die(0);
		assertEquals("Birb cannot be revived again!", animal.revive());
		
	}
	
	@Test
	public void testNoActions(){
		animal.sleep();
		animal.sleep();
		assertTrue(animal.getActionsLeft() == 0);
		String initialStats = animal.toString();
		animal.playWithToy(new Slide());
		assertEquals(initialStats, animal.toString());
		animal.eat(new Fruit());
		assertEquals(initialStats, animal.toString());
		animal.heal(new PetMedicine());
		assertEquals(initialStats, animal.toString());
	}
	
	@Test
	public void testDead(){
		animal.die(0);
		assertTrue(animal.checkDead());
		animal.sleep();
		animal.eat(new Seeds());
		animal.playWithToy(new Slide());
		animal.heal(new PetMedicine());
		assertTrue(animal.getActionsLeft() == 2);
	}
	
	@Test
	public void testHeal(){
		animal.setHealth(20);
		animal.setSick(true);
		animal.heal(new PetMedicine());
		assertEquals(40, animal.getHealth(), 0.05);
		assertFalse(animal.checkSick());
		assertTrue(animal.getHappiness() == 50 - 20*0.15);
	}



}
