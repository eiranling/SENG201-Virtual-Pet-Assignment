package medicine;

import Game.Item;

/**
 * Abstract class such that it can not be instantiated to create Medicine objects. To create a Medicine object
 * instantiate one of the classes which inherit this class. Classes which inherit this will pass 
 * arguments into the constructor to set values to its properties unique to the medical item. 
 * 
 * This class inherits from the abstract class Item, which provides methods and functionality to the medical items.
 * 
 * Medical items are consumable, this mean that medical items are stackable. Hence medical items are not added to the player's inventory as a 
 * new medical item if one already exists. The number of uses is simply increased as it is 'stacking' the items in the player's inventory.
 * Their durability is equivalent to the number of uses the item has left. Misbehaving pets can not break consumable items, hence 
 * medical items can not be broken. 
 * 
 * 
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public abstract class Medicine extends Item {

	
	/**
	 * Overloaded constructor - creates a demo medical item. Used for testing only.
	 */
	public Medicine() {
		restoreValue = 20;
		name = "Demo  Medicine";
		cost = 10;
		description = "Demo medicine for testing.";
		numUses = 1; //Is a consumable
		restoreType = 1;
		isStackable = true;
	}
	
	/**
	 * Overloaded constructor to set custom values to the medicine item properties. Number of uses is by
	 * default set to 1, since it is a consumable.
	 * 
	 * @param restoreValueInput The amount the medicine will restore/affect a pet's stat(s) by. 
	 * @param nameInput The name the medical item will be set to.
	 * @param costInput The cost the medical item will be set to.
	 * @param descriptionInput The description the medical item will be set to.
	 */
	public Medicine(double restoreValueInput, String nameInput, double costInput, String descriptionInput) {
		restoreValue = restoreValueInput;
		name = nameInput;
		cost = costInput;
		description = descriptionInput;
		numUses = 1; //Is a consumable
		restoreType = 1;
		isStackable = true;
	}
	
}
