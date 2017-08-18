package JUnitTests;
import food.Fish;
import food.Rat;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before;
import medicine.PetMedicine;
import pets.Cat;
import pets.Pets;
import toy.WoolBall;


public class TestCat extends TestCase {
	
	private static Pets animal;
	
	@Before
	public void setUp(){ 
		animal = new Cat("Tibbles");
	}
	
	@Test
	//checks that the animal is constructed properly.
	public void testPetNormal() {
		assertTrue(animal.getDescription() == "A super fluffy pet that enjoys more sleep but doesn't enjoy playing as much.");
		assertTrue(animal.getName() == "Tibbles");
		assertTrue(animal.checkSick() == false);
		assertTrue(animal.checkMisbehaving() == false);
		assertTrue(animal.getSpecies() == "Cat");
		assertTrue(animal.checkFavouriteToy().equals(new WoolBall()));
		assertTrue(animal.checkfavouriteFood().equals(new Fish()));
		assertTrue(animal.getHappiness() == 50);
		assertTrue(animal.getActionsLeft() == 2);
		assertTrue(animal.getEnergy() == 70);
		assertTrue(animal.getNeedForToilet() == 25);
		assertTrue(animal.getHealth() == 100);
		assertTrue(animal.getHunger() == 100);
	}
	
	@Test
	public void testPetEmpty(){
		Pets empty = new Cat("");
		assertTrue(empty.getDescription() == "A super fluffy pet that enjoys more sleep but doesn't enjoy playing as much.");
		assertTrue(empty.getName() == "");
		assertTrue(empty.checkSick() == false);
		assertTrue(empty.checkMisbehaving() == false);
		assertTrue(empty.getSpecies() == "Cat");
	}

	@Test
	public void testEat() {

		animal.setHunger(50); //lowers the hunger
		animal.eat(new Fish());
		assertEquals(new Fish().getRestoreValue() * 1.33 + 50, animal.getHunger());
		assertTrue(1 == animal.getActionsLeft());
		assertTrue(50+new Fish().getRestoreValue()*0.4*1.33 == animal.getHappiness());
		assertTrue(25+new Fish().getRestoreValue()*0.15 == animal.getNeedForToilet());
		Pets animal2 = new Cat("stooB");
		animal2.setHunger(50);
		animal2.eat(new Rat());
		assertTrue(animal2.getHunger() == new Rat().getRestoreValue()+50);
	}
	
	@Test
	public void testDieThroughEat(){
		animal.setNeedForToilet(99);
		animal.eat(new Fish());
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
		animal.playWithToy(new WoolBall());
		assertTrue(animal.getEnergy() == 70 - new WoolBall().getRestoreValue()*0.35);
		assertTrue(animal.getHappiness() == 50+new WoolBall().getRestoreValue()*1.5);
		assertTrue(animal.getWeight() == startWeight - new WoolBall().getRestoreValue() * 0.1);
		assertTrue(animal.getActionsLeft() == 1);
		
	}
	
	@Test
	public void testInvalidToy(){
		animal.setEnergy(0); 
		animal.playWithToy(new WoolBall());
		assertTrue(animal.getActionsLeft() == 2);
	}

	@Test
	public void testSleep() {
		animal.sleep();
		//want to primarily check the key parts of sleep.
		assertTrue(animal.getEnergy() == 100);
		assertTrue(animal.getHunger() == 67);
		assertTrue(animal.getHappiness() == 75);
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
		animal.die(0);
		animal.revive();
		assertTrue(animal.checkDead());
		
	}
	
	@Test
	public void testNoActions(){
		animal.sleep();
		animal.sleep();
		String initialStats = animal.toString();
		animal.eat(new Fish());
		assertEquals(initialStats, animal.toString());
		animal.heal(new PetMedicine());
		assertEquals(initialStats, animal.toString());
		animal.playWithToy(new WoolBall());
		assertEquals(initialStats, animal.toString());
	}
	
	@Test
	public void testDead(){
		animal.die(0);
		animal.sleep();
		animal.eat(new Fish());
		animal.heal(new PetMedicine());
		animal.playWithToy(new WoolBall());
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
	//this test a generic pet class, so it will only need to be tested once.
	public void testDiscipline(){
		animal.setMisbehaving(true);
		animal.discipline();
		assertTrue(animal.checkMisbehaving() == false);
		assertTrue(animal.getHappiness() == 20);
	}
	
	@Test
	public void testSickDamage(){
		animal.setSick(true);
		animal.takeSickDamage();
		assertTrue(animal.getHealth() == 90);
		
	}
	
	@Test
	public void testDieFromSickness(){
		animal.setSick(true);
		animal.setHealth(10);
		animal.takeSickDamage();
		assertTrue(animal.checkDead());
	}
	
	@Test
	public void testGetHungry(){
		animal.becomeHungry();
		assertTrue(animal.getHunger() == 95);
	}
	
	@Test
	public void testDieFromHunger(){
		animal.setHunger(1);
		animal.becomeHungry();
		assertTrue(animal.checkDead());
	}
	
	@Test
	public void testMisbehave(){
		animal.willMisbehave(15);
		assertFalse(animal.checkMisbehaving());
		animal.willMisbehave(14);
		assertTrue(animal.checkMisbehaving());
		
	}

}
