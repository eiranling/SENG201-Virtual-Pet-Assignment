package toy;

import Game.Item;

/**
 * Abstract class such that it can not be instantiated to create Toy objects. To create a Toy object
 * instantiate one of the classes which inherit this class. Classes which inherit this will pass 
 * arguments into the constructor to set values to its properties unique to the toy item. 
 * 
 * This class inherits from the abstract class Item, which provides methods and functionality to the toy items.
 * 
 * Toy items are NOT consumable, this means toy items are not stackable resulting in the player only being able to keep
 * one of each toy in their inventory at a time. Players can not buy a toy if it is already in their inventory. 
 * The toy's durability is equivalent to the number of uses it has left. A non-consumable can be broken by misbehaving pets, 
 * hence pets can break toys. When a toy is broken it is removed from the player's inventory. 
 * 
 * 
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public abstract class Toy extends Item {
	
	/**
	 * Overloaded constructor - used for creating a demo toy used for testing only.
	 */
	public Toy() {
		restoreValue = 20;
		name = "Demo Food";
		cost = 10;
		description = "Demo Toy for testing.";
		numUses = 1;
		restoreType = 3;
		isStackable = false;
	}
	
	/**
	 * Overloaded constructor for setting custom values to a toy object.
	 * 
	 * @param restoreValueInput The value which a toy will affect a pet's stat(s) by.
	 * @param nameInput The name the toy will be set to.
	 * @param costInput The cost the toy will be set to.
	 * @param descriptionInput The description the toy will bet set to.
	 * @param durability The durability the toy will be set to.
	 */
	public Toy(double restoreValueInput, String nameInput, double costInput, String descriptionInput, int durability) {
		restoreValue = restoreValueInput;
		name = nameInput;
		cost = costInput;
		description = descriptionInput;
		numUses = durability;
		restoreType = 3;
		isStackable = false;
	}
}
