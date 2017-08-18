package JUnitTests;
import food.*;	
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import medicine.Medicine;
import medicine.PetMedicine;
import pets.Dog;
import pets.Pets;
import toy.*;

public class TestDog extends TestCase{
	
	private static Pets animal;
	
	@Before
	public void setUp(){
		animal = new Dog("Tibbles");
	}
	
	@Test
	//checks that the animal is constructed properly.
	public void testPetNormal() {
		assertTrue(animal.getDescription() == "An adorable pet that uses more energy when playing, but gains happiness faster. It doesn't enjoy sleeping as much and gets hungry often. However, it is loyal and will not misbehave that often.");
		assertTrue(animal.getName() == "Tibbles");
		assertTrue(animal.checkSick() == false);
		assertTrue(animal.checkMisbehaving() == false);
		assertTrue(animal.getSpecies() == "Dog");
		assertTrue(animal.checkFavouriteToy().equals(new Bone()));
		assertTrue(animal.checkfavouriteFood().equals(new Steak()));
		assertTrue(animal.getHappiness() == 50);
		assertTrue(animal.getActionsLeft() == 2);
		assertTrue(animal.getEnergy() == 70);
		assertTrue(animal.getNeedForToilet() == 25);
		assertTrue(animal.getHealth() == 100);
		assertTrue(animal.getHunger() == 100);
	}
	
	@Test
	public void testPetEmpty(){
		Pets empty = new Dog("");
		assertTrue(empty.getDescription() == "An adorable pet that uses more energy when playing, but gains happiness faster. It doesn't enjoy sleeping as much and gets hungry often. However, it is loyal and will not misbehave that often.");
		assertTrue(empty.getName() == "");
		assertTrue(empty.checkSick() == false);
		assertTrue(empty.checkMisbehaving() == false);
		assertTrue(empty.getSpecies() == "Dog");
	}
	
	@Test
	public void testEat() {
		animal.setHunger(50); //lowers the hunger
		animal.eat(new Steak());
		assertEquals(new Steak().getRestoreValue() + 50, animal.getHunger());
		assertTrue(1 == animal.getActionsLeft());
		assertTrue(50+new Steak().getRestoreValue()*0.4*1.75 == animal.getHappiness());
		assertTrue(25+new Steak().getRestoreValue()*0.15 == animal.getNeedForToilet());
		Pets animal2 = new Dog("topS");
		animal2.setHunger(50);
		animal2.eat(new Rat());
		assertTrue(animal2.getHunger() == 50);
		assertTrue(animal2.getActionsLeft() == 2);
	}
	
	@Test
	public void testDieThroughEat(){
		animal.setNeedForToilet(99);
		animal.eat(new Steak());
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
		animal.playWithToy(new Bone());
		assertTrue(animal.getEnergy() == 70 - new Bone().getRestoreValue()*0.35*2.25);
		assertTrue(animal.getHappiness() == 50+new Bone().getRestoreValue()*2.25);
		assertTrue(animal.getWeight() == startWeight - new Bone().getRestoreValue() * 0.1);
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
		assertEquals("Tibbles is too hungry to go to sleep!", animal.sleep());
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
		String initialStats = animal.toString();
		animal.eat(new Steak());
		assertEquals(initialStats, animal.toString());
		animal.playWithToy(new Bone());
		assertEquals(initialStats, animal.toString());
		animal.heal(new PetMedicine());
		assertEquals(initialStats, animal.toString());
	}
	
	@Test
	public void testDead(){
		Pets animal = new Dog("topS");
		animal.die(0);
		animal.playWithToy(new Bone());
		animal.sleep();
		animal.eat(new Steak());
		animal.heal(new PetMedicine());
		assertTrue(animal.getActionsLeft()==2);
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
	public void testMisbehave(){
		animal.willMisbehave(5);
		assertFalse(animal.checkMisbehaving());
		animal.willMisbehave(4);
		assertTrue(animal.checkMisbehaving());
	}


}
