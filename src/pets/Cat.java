package pets;
import java.util.Random;

import Game.Item;
import food.Fish;
import toy.WoolBall;

/**
 * Class that creates a cat object that inherits most of it's basic functionality from the Pets class.
 * It overrides the eat, sleep, heal and playWithToy methods
 * @author eiran
 */
public class Cat extends Pets {
	
	/**
	 * A constructor that sets the properties of the pet.
	 * @param givenName the name given to the pet by the player.
	 */
	public Cat(String givenName){
		super(givenName);
		weight = 5.0+(5*(new Random().nextDouble()));
		startWeight = weight;
		description = "A super fluffy pet that enjoys more sleep but doesn't enjoy playing as much.";
		species = "Cat";
		favouriteFood = new Fish();
		favouriteToy = new WoolBall();
	}
	
	/**
	 * Method to be called by the Player class to feed the pet.
	 * @param f a item of type food.
	 * @return a confirmation message.
	 */
	@Override
	public String eat(Item f) {
		if (getActionsLeft() == 0){
			return (getName()+" has no more actions left.");
		}
		if (!isDead){
			double multiplier = 1.0;
			if (f.equals(favouriteFood)){
				multiplier = 1.33;
			}
			actions -= 1;
			double hunGain = f.getRestoreValue()*multiplier;
			hunger += hunGain;
			if (hunger > 100){
				hunGain -= (hunger - 100);
				hunger = 100;
			}
			
			double hapGain = f.getRestoreValue()*0.4*multiplier;
			happiness += hapGain;
			if (happiness > 100){
				hapGain -= (happiness - 100); // sets it for the output.
				happiness = 100;
			}
			
			weight += f.getRestoreValue() * 0.05;
			double toiGain = f.getRestoreValue() * 0.15;
			needForToilet += toiGain;
			if (needForToilet > 100){
				die(3);
				needForToilet = 100;
			}
			return (getName()+" ate "+f.getName()+" and gained " + String.format("%1$.2f less hungry and %2$.2f more happy. It gained %3$.2f kg and needed to use the toilet %4$.2f%% more.", hunGain, hapGain, f.getRestoreValue()*0.05, toiGain));
			} else {
				return (getName() + " is dead.");
			}
		}
	
	

	/**
	 * Method to put the pet to sleep and allow it to regain energy, but lose hunger.
	 * It can occasionally heal itself.
	 * @return a confirmation message.
	 */
	@Override
	public String sleep() {
		if (getActionsLeft() == 0){
			return (getName()+" has no more actions left.");
		}
		if (!isDead){
			//loses hunger when sleeping.
			if (hunger < 33.005){
				return getName()+" is too hungry to go to sleep!";
			}
			hunger -= 33;
			actions -= 1;
			
			// gains energy when sleeping.
			double eneGain = 50;
			energy += eneGain;
			if (energy > 100){
				eneGain -= (energy - 100);
				energy = 100;
			}
				
			// special trait, gains happiness when sleeping.
			double hapGain = 25.0;
			happiness += hapGain;
			if (happiness > 100){
				hapGain -= happiness - 100;
				happiness = 100;
			}
				
			if (new Random().nextInt(100) < 20){ // 20% chance to heal itself.
				double heaGain = 20;
				health += heaGain;
				if (health > 100){
					heaGain -= health - 100;
					health = 100;
				}
				if (isSick){
					isSick = false;
					
					// different return statements based on the different outcomes.
					return getName()+" slept and became healthy! It regained "+String.format("%1$.2f, lost 33.00%% hunger, gained %2$.2f energy and %3.2f happiness!", heaGain, eneGain, hapGain);
				} else {
					return getName()+" slept and regained "+String.format("%1$.2f health, %2$.2f energy and %3.2f happiness, but lost 33.00%% hunger.", heaGain, eneGain, hapGain);
				}	
			} else {
				return getName()+" slept and regained "+String.format("%1$.2f energy, gained %2$.2f happiness and lost 33.00%% hunger.", eneGain, hapGain);
			}
		} else {
			return getName() +" is dead.";
		}
	}
	
	/**
	 * Method to be called by the Player to exercise/play with the pet.
	 * @param t Item of type Toy. Consult the Item documentation for more information.
	 * @return a confirmation message to tell the player if it was successful or not.
	 */
	@Override
	public String playWithToy(Item t) {
		if (getActionsLeft() == 0){
			return (getName()+" has no more actions left.");
		}
		if (!isDead){
		double catMultiplier = 1; //specific animal multiplier
		if (t.equals(favouriteToy)){
			catMultiplier = 1.5;
		}
		
		// loses energy from playing with the toy.
		double eneLoss = t.getRestoreValue()*0.35;
		if ((energy - eneLoss) < 0.005){
			return (getName()+" doesn't have the energy to play with that toy!");
		} else {
			energy -= eneLoss;
			actions -= 1;
			// gains happiness from playing with the toy.
			double hapGain = t.getRestoreValue() * catMultiplier;
			happiness += hapGain;
			if (happiness > 100){
				hapGain -= happiness - 100;
				happiness = 100;
			}
			
			// loses weight from exercise.
			double weiLoss = t.getRestoreValue()*0.10;
			weight -= weiLoss;
			if (weight < 2.5){ //less than half the starting value
				return die(2); //it is malnourished and dies
				
			} else {
				if (!isMisbehaving){
					return (getName()+" played with "+t.getName()+String.format(", using %1$.2f energy and gaining %2$.2f happiness towards you. It also lost %3$.2f kg.", eneLoss, hapGain, weiLoss));
					} else {
						if (new Random().nextInt(100) < 20){
							String message = breakToy(t)+"\r\n";
							message += (getName()+" played with "+t.getName()+String.format(", using %1$.2f energy and gaining %2$.2f happiness towards you. It also lost %3$.2f kg.", eneLoss, hapGain, weiLoss));
							return message;
						} else {
							return (getName()+" played with "+t.getName()+String.format(", using %1$.2f energy and gaining %2$.2f happiness towards you. It also lost %3$.2f kg.", eneLoss, hapGain, weiLoss));
						}
					}
			}
		}
		} else {
			return (getName()+" is dead.");
		}
	}

	/**
	 * Method to heal the pet using medicine.
	 * @param m An Item of type Medicine, consult the Item and Medicine documentation for more information.
	 * @return a confirmation message.
	 */
	@Override
	public String heal(Item m) {
		if (getActionsLeft() == 0){
			return getName()+ " has no more actions left.";
		}
		if (!isDead){
			actions -= 1;
			// gains health from medicine
			double heaGain = m.getRestoreValue();
			health += heaGain;
			if (health > 99.995){
				heaGain -= (health - 100);
				health = 100;
			}
			
			//loses happiness from having to take the medicine
			double hapLoss = m.getRestoreValue() * 0.15;
			happiness -= hapLoss;
			if (happiness < 0.005){
				hapLoss -= (0-happiness);
				happiness = 0;
			}
			
			// no longer is sick
			if (isSick){
				isSick = false;
				return (getName()+" is now healthy thanks to the medicine! He also lost "+String.format("%1$.2f happiness and gained %2$.2f health!", hapLoss, heaGain));
				
			} else {
				return getName()+String.format("regained %1$.2f health and lost %2$.2f happiness.", heaGain, hapLoss);
			}
			
		} else {
			return getName() + " is dead.";
		}
	}


}
