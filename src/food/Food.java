package food;

import Game.Item;

/**
 * Abstract class such that it can not be instantiated to create food objects. To create a food object
 * instantiate one of the classes which inherit this class. Classes which inherit this will pass 
 * arguments into the constructor to set values to its properties unique to the food item. 
 * 
 * This class inherits from the abstract class Item, which provides methods and functionality to the food items. 
 * 
 * Food items are consumable. This means food items are stackable, hence are not added to the player's inventory as a new food 
 * item if one already exists. The number of uses is simply increased 'stacking' the items in the player's inventory. 
 * Durability is equivalent to the number of uses the item has left. Misbehaving pets can not break consumables. Hence
 * can not break food items.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public abstract class Food extends Item {

	
	/**
	 * Overloaded constructor - creates a demo food item. Used for testing only.
	 */
	public Food() {
		restoreValue = 20;
		name = "Demo Food";
		cost = 10;
		description = "Demo food for testing.";
		numUses = 1; //Is a consumable
		restoreType = 2;
		isStackable = true;
	}
	
	/**
	 * Overloaded constructor to set custom values for the food items. Number of uses is by
	 * default set to 1, since it is a consumable.
	 * 
	 * @param restoreValueInput The amount the food item will affect a pets stat(s) by.
	 * @param nameInput The name the food item will set to.
	 * @param costInput The cost the food item will be set to.
	 * @param descriptionInput The description the food item will be set to.
	 */
	public Food(double restoreValueInput, String nameInput, double costInput, String descriptionInput) {
		restoreValue = restoreValueInput;
		name = nameInput;
		cost = costInput;
		description = descriptionInput;
		numUses = 1; //Is a consumable
		restoreType = 2;
		isStackable = true;
	}
	
}
