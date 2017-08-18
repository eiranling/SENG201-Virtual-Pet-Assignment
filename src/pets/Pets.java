package pets;
import java.util.Random;	

import Game.Item;
import Game.Player;
import food.Food;
import toy.Toy;

/**
 * 	Class pet that Players can take care of.
 * 
 * @author Eiran Ling
 * @author Patrick Ma
 * @version 2
 */
public abstract class Pets{
	private String name;
	protected String species;
	protected String description;
	protected int actions=2;
	protected double weight;
	protected double hunger;
	protected double happiness;
	protected double energy;
	protected double needForToilet;
	protected double health;
	protected boolean isMale;
	protected boolean isSick;
	protected boolean isMisbehaving;
	protected boolean hasDied;
	protected boolean isDead;
	protected Food favouriteFood;
	protected Toy favouriteToy;
	private Player owner;
	protected double startWeight;
	
	/**
	 * Constructor to construct an abstract pet object.
	 * @param givenName Name given to pet by the player.
	 */
	public Pets(String givenName){
		name = givenName;
		switch(new Random().nextInt(2)){ //assigns random gender
		case 0: isMale=true; break;
		case 1: isMale=false; break;
		}
		
		hunger = 100.0;
		happiness = 50.0;
		energy = 70.0;
		needForToilet = 25.0;
		health = 100.0;
		isSick = false;
		isMisbehaving = false;
		hasDied = false;
		isDead = false;
	}
	
	/**
	 * Returns the name of the pet
	 * @return the given name of the pet.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the species of the pet.
	 * @return the species of the pet.
	 */
	public String getSpecies(){
		return species;
	}
	
	/**
	 * Returns the description of the pet.
	 * @return A quick summary of the pets and its specialties.
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Returns the gender of the pet.
	 * @return the gender of the pet.
	 */
	public char getGender(){
		if (isMale){
			return 'M';
		} else {
			return 'F';
		}
	}
	
	/**
	 * Returns the hunger level of the pet.
	 * @return the current hunger level of the pet.
	 */
	public double getHunger(){
		return hunger;
	}
	
	/**
	 * The happiness level of the pet.
	 * @return the happiness level of the pet.
	 */
	public double getHappiness(){
		return happiness;
	}
	
	/**
	 * Return the energy of the pet.
	 * @return the energy level of the pet.
	 */
	public double getEnergy(){
		return energy;
	}
	
	/**
	 * The pets need for toilet.
	 * @return the pet's current need for the toilet.
	 */
	public double getNeedForToilet(){
		return needForToilet;
	}
	
	/**
	 * The pets current health
	 * @return the current health of the pet.
	 */
	public double getHealth(){
		return health;
	}
	
	/**
	 * Returns true if the pet is sick. false otherwise.
	 * @return the current sickness state of the pet.
	 */
	public boolean checkSick(){
		return isSick;
	}
	
	/**
	 * Returns true if the pet is misbehaving. false otherwise.
	 * @return the current misbehaving state of the pet.
	 */
	public boolean checkMisbehaving(){
		return isMisbehaving;
	}
	
	/**
	 * Returns true if the pet has died. False otherwise.
	 * @return whether or not the pet has died.
	 */
	public boolean checkDied(){
		return hasDied;
	}
	
	/**
	 * Returns true if the pet is currently dead. false otherwise.
	 * @return whether or not the pet is dead.
	 */
	public boolean checkDead(){
		return isDead;
	}
	
	/**
	 * Returns the number of actions the pet has remaining.
	 * @return how many actions the pet has left in the day.
	 */
	public int getActionsLeft(){
		return actions;
	}
	
	/**
	 * Returns the favourite food of the pet.
	 * @return -- the pet's favourite food
	 */
	public Food checkfavouriteFood(){
		return favouriteFood;
	}
	
	/**
	 * Returns the pets favourite toy.
	 * @return -- the pet's favourite toy
	 */
	public Toy checkFavouriteToy(){
		return favouriteToy;
	}
	
	/**
	 * Returns the current weight of the pet.
	 * @return -- the pet's weight
	 */
	public double getWeight(){
		return weight;
	}
	
	/**
	 * Method to set the pet's owner
	 * @param player - A Player Object to establish the owner of the pet. 
	 */
	public void setOwner(Player player){
		owner = player;
	}
	
	/**
	 * Method to refresh the pet's actions
	 */
	public void refreshActions(){
		actions = 2;
	}
	
	/**
	 * Setter for the hunger property.
	 * @param hunger -- Level to set the hunger to.
	 */
	public void setHunger(double hunger){
		this.hunger = hunger;
	}
	
	/**
	 * Setter for the needForToilet property
	 * @param needForToilet -- level at which to set the needForToilet property
	 */
	public void setNeedForToilet(double needForToilet){
		this.needForToilet = needForToilet;
	}
	
	/**
	 * Setter for health
	 * @param health -- level at which to set the health level
	 */
	public void setHealth(double health){
		this.health = health;
	}
	
	/**
	 * Setter for sickness
	 * @param sick -- boolean to set the pet's sickness state
	 */
	public void setSick(boolean sick){
		this.isSick = sick;
	}
	
	/**
	 * Setter for energy
	 * @param energy -- value to set the pets energy level
	 */
	public void setEnergy(double energy){
		this.energy = energy;
	}
	
	/**
	 * Setter for misbehaving
	 * @param isMisbehaving -- a boolean to set the pet's misbehavioural state
	 */
	public void setMisbehaving(boolean isMisbehaving){
		this.isMisbehaving = isMisbehaving;
	}
	
	/**
	 * Abstract method to be called by the player class to feed the pet.
	 * @param f A Food item. See the Item class documentation for more information.
	 * @return a confirmation message
	 */
	public abstract String eat(Item f);
	
	/**
	 * Abstract method to be called by the player class to put the pet to sleep.
	 * @return a confirmation message
	 */
	public abstract String sleep();
	
	/**
	 * Abstract method to be called by the player class to play with the pet.
	 * @param t A Toy. See the Item class for more documentation.
	 * @return a confirmation message
	 */
	public abstract String playWithToy(Item t);
	
	/**
	 * Method to break a toy. called if the pet is too rough with it.
	 * @param t -- a Toy Item
	 * @return -- A message to inform the user the item was broken.
	 */
	public String breakToy(Item t){
		String itemName = t.getName();
		
		String message = name+" was too rough with the toy and broke the "+itemName+".";
		t.breakItem(owner);
		return message;
	}
	
	/**
	 * Method to randomly change the misbehaving state of the pet.
	 */
	public void willMisbehave(){
		if (new Random().nextInt(100) < 15){ // 15% chance to misbehave for most pets.
			isMisbehaving = true;
		}
	}
	
	/**
	 * Overloaded method to test if the pet misbehaves on expected values given by the random number generator.
	 * @param i An integer to simulate the output of a random number generator.
	 */
	public void willMisbehave(int i){
		if (i < 15){
			isMisbehaving = true;
		}
	}
	
	/**
	 * Method to randomly determine if the pet will be sick the next day. Called by Game class.
	 */
	public void getsSick(){
		if (new Random().nextInt(100) < 15){ // 15% chance to get sick for most pets.
			isSick = true;
		}
	}
	
	
	/**
	 * Overloaded method to determine if the code will work as expected.
	 * @param i -- integer that simulates a random number generate output.
	 */
	public void getsSick(int i){
		if (i < 15){
			isSick = true;
		}
	}
	/**
	 * Abstract method to be called by the Player class to heal the pet.
	 * @param m A medicine item. Consult the item class documentation for more information.
	 * @return A confirmation message.
	 */
	public abstract String heal(Item m);
	
	/**
	 * Method called by the Player class to make the pet use the toilet.
	 * Not an abstract methods as it can be used across all the different species of pets.
	 * @return a confirmation string to acknowledge that the pet has been taken the toilet.
	 */
	public String useToilet(){
		if (actions == 0){
			return name+" has no more actions left.";
		}
		if (needForToilet == 0){
			return name+" has no need to go to the toilet.";
		}
		if (!isDead){
		needForToilet = 0; // resets it to 0
		actions -= 1;
		double hGain = 5.0;
		if (happiness+hGain > 100){ hGain = 100 - happiness; happiness = 100;}
		else {happiness += hGain;} // Don't you feel happy to take a shit?
		
		return String.format("%1$s used the litter bin and gained %2$.2f happiness!", name, hGain);
		
		} else {
			return name+" is dead.";
		}
	}
	
	/**
	 * Method to be called by the Player class to discipline the pet 
	 * @return a message confirming that the pet has been disciplined.
	 */
	public String discipline(){
		if(!isDead){ // check to make sure pet is alive
			if(isMisbehaving){ // check to see if pet is misbehaving first.
				isMisbehaving = false;
				
				happiness -= 30;
				if (happiness < 0.005){
					happiness = 0;
				}
				
				return String.format("%1$s stopped misbehaving. But is less happy towards you.", name);
			} else {
				return name+" is not misbehaving! No need to discipline.";
			}
		} else {
			return name+" is dead.";
		}
	}
	
	/**
	 * Method called if the pet is meant to die.
	 * @param deathCode -- A code to help determine cause of death.
	 * @return message telling the player that the pet died, null if it's already dead.
	 */
	public String die(int deathCode){ 
		if (!isDead){
			isDead = true; 
			String message = name+" died because ";
			
			switch (deathCode){
			case 0: message += "it ran out of health!"; break;
			case 1: message += "it got too hungry!"; break;
			case 2: message += "it is too malnourished!"; break;
			case 3: message += "it is too constipated!"; break;
			} 
			
			return message;
		} else {
			return null; 
		}
	}
		
		
	
	/**
	 * Method to revive the pet if it were dead.
	 * Returns an error message if the pet has died previously and therefore cannot be revived again.
	 * @return message to see the outcome was successful or not.
	 */
	public String revive(){ //resets pet to lower stats that it was.
		if (isDead){
			if(!hasDied){
				happiness = 40.0;
				weight = startWeight;
				needForToilet = 25.0;
				energy = 40.0;
				health = 50.0;
				isDead = false;
				hasDied = true;
				isSick = false;
				isMisbehaving = false;
				return name+" has been revived!";
		
			} else {
				return name+" cannot be revived again!";
			}
		} else {
			return name+" is not dead!";
		}
	}
	
	/**
	 * 
	 * Method to make the pet take damage when after the day passes. Called by game
	 * @return -- Returns a message notifying user that the pet has taken damage
	 */
	public String takeSickDamage(){
		if (isSick){
		health -= 10;
		happiness -= 5;
		if (health < 0.005){
			return die(0);
		}
		return getName()+" suffered 10 damage because it is sick!";
		} else {
			return null;
		}
		
	}
	
	/**
	 * Method to lower the pet's hunger on day change
	 */
	public void becomeHungry(){
		hunger -= 5;
		if (hunger < 0.005){
			die(1);
		}
	}
	/**
	 * Provides a legible string for the class.
	 * @return a string printout of the object.
	 */
	@Override
	public String toString() {
		String statMessage = new String();
		if (!checkDead()){
		statMessage = getName()+" has "+getActionsLeft()+" actions left.\n";
		statMessage += getName()+String.format(" is a %1$s that weighs %2$.2f kg.\n", species.toLowerCase(), weight);
		statMessage += "Favourite food: "+favouriteFood.getName()+"\r\n";
		statMessage += "Favourite toy: "+favouriteToy.getName()+"\r\n";
		statMessage += "Its current stats are:\n";
		statMessage += String.format("Happiness: %1$.2f\nHunger: %2$.2f\nEnergy: %3$.2f\nNeed for Toilet: %4$.2f\nHealth: %5$.2f\n", happiness, hunger, energy, needForToilet, health);
		if (checkSick()){
			statMessage += getName()+" is sick!\n";
		}
		if (checkMisbehaving()){
			statMessage += getName()+" is misbehaving!\n";
		}
		} else{
			statMessage = getName() + " is dead.";
		}
		return statMessage;
	}

}