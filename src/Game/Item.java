package Game;

import pets.Pets;

/**
 * <p>
 * Abstract Item which will be inherited by Toy, Food and Medicine; as this provides the functionality of an item.
 * Items can be used to heal, feed or to be used to play with pets. Every item has an associated name, cost, description,
 * restore value, 'restore type', flag to determine an item is stackable/consumable and number of uses (i.e. durability).
 * </p>
 * <p>
 * If the flag isStackable is true, then the item can be stacked and is a consumable. A consumable item can not be broken by
 * misbehaving pets and can always be added to inventory. Medical items and toys items are consumables. If isStackable is
 * false, then the item is not stackable and not a consumable. Toys is a non-consumable.  A non-consumable can be broken by 
 * misbehaving pets and may not always be enough room to add into the player's inventory. A player can only have one of each
 * toy in their inventory at a time. 
 * </p>
 * <p>
 * Restore type is what determines if an item is a Food, Toy or Medicine object. As the Player class stores items in
 * an ArrayList of type Item, the type of item can not be determined by the class an object was instantiated by. Hence,
 * an integer property 'restoreType' is used to determine what type of the type of item is.
 * </p>
 * <p>
 * Restore Type, their associated item type and what attribute the restoreValue affects</p>
 * <p>1 - Medicine item, primarily restores a pet's health</p>
 * <p>2 - Food item, primarily restores a pet's hunger and increases their weight etc.</p>
 * <p>3 - Toy item, primarily restore's a pet happiness and drains energy etc.</p>
 * 
 * Every time an item is used, it will take durability damage.
 * Durability is equivalent to the number of uses an item has left.
 * 
 *  @author Patrick Ma
 *  @author Eiran Ling
 */
public abstract class Item {
	
	/**
	 * How much the item restores
	 */
	protected double restoreValue;
	/**
	 * Name of the item
	 */
	protected String name;
	/**
	 * cost of the item
	 */
	protected double cost;
	/**
	 * Description of the item
	 */
	protected String description;
	/**
	 * Number of uses the item has remaining
	 */
	protected int numUses;
	/**
	 * Type of attribute that the item restores
	 */
	protected int restoreType;
	/**
	 * true if the item can stack with identical items. False otherwise.
	 */
	protected boolean isStackable;
	
	/**
	 * Getter for returning how much an item restores a pet's attribute by.
	 * 
	 * @return Returns the value which a stat is restored by
	 */
	public final double getRestoreValue(){
		return restoreValue;
	}
	
	/**
	 * Used to get the flag for determining if an item is stackable/consumable.
	 * 
	 * @return True is the item is stackable, false if the item is not stackable.
	 */
	public final boolean returnIsStackable() {
		return isStackable;
	}
	
	/**
	 * Getter for obtaining the restore type.
	 * 1 - Medicine item, primarily restores a pet's health
	 * 2 - Food item, primarily restores a pet's hunger and increases their weight etc.
	 * 3 - Toy item, primarily restore's a pet happiness and drains energy etc.
	 * 
	 * @return Returns the type of the object
	 */
	public final int getRestoreType(){
		return restoreType;
	}
	
	/**
	 * Getter for the name of the item.
	 * 
	 * @return Returns name of the item.
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * Getter for obtaining the cost of the item.
	 * 
	 * @return Returns the cost of the item.
	 */
	public final double getCost() {
		return cost;
	}
	
	/**
	 * Getter for obtaining the description of the item.
	 * 
	 * @return Returns the description of the item.
	 */
	public final String getDescription() {
		return description;
	}
	
	/**
	 * Getter for obtaining the remaining uses an item has, i.e. its durability.
	 * 
	 * @return Returns the remaining uses/durability of the item.
	 */
	public final int getNumUses() {
		return numUses;
	}
	
	/**
	 * An item is used, numUses property decreases. Item 'broken' and is removed from player's 
	 * inventory when it runs out of uses (i.e. no durability left).
	 * 
	 * @param player Player who will use the item.
	 * @param pet Pet that the item will be used on, pets stats will be changed.
	 * @return Message that will be displayed the player, informing them of what happened.
	 */
	public final String use(Player player, Pets pet) {
		String message = "";
		switch(restoreType) {
			case 1: message += player.heal(pet, this); break; //Medicine item
			case 2: message += player.feed(pet, this); break; //Food item
			case 3: message += player.playWithPet(pet, this); break; //Toy item
		}
		if (!message.equals(pet.getName()+" has no more actions left.") && !message.equals(pet.getName()+" doesn't want to eat that.")){
			numUses -= 1; //Used item, numUses decreases
		}
		
		//Remove the item from the player's inventory if item is broken
		if (numUses <= 0) {
			message += breakItem(player);
		}
		return message;
	}
	
	/**
	 * When an item is 'broken' by either running out of durability/number of uses, or because of 
	 * misbehaving pets, item is removed from the player's inventory.
	 * 
	 * @param player Player who will lose the item from their inventory.
	 * @return A message informing the user if an item has been broken.
	 */
	public final String breakItem (Player player) throws RuntimeException {
		if (!isStackable){
			//Is not a consumable, can break regardless of durability
			//System.out.println(name + " broke.");
			player.getInventory().remove(this);
			return " " + getName() + " broke.\n";
		} else {
			//Is a consumable...
			if (this.numUses > 0) {
				//Still has uses left
				throw new RuntimeException("Can't remove/break a consumable when still has uses.");
			} else {
				player.getInventory().remove(this);
				return " You ran out of " + getName() + "\n";
			}
			
		}
		
		
		
	}
	
	/**
	 * Increases the number of use for the item by 1.
	 */
	public void increaseNumUses(){
		numUses += 1;
	}
	
	/**
	 * Overloaded method. Increases the number of uses for the item by a specific integer.
	 * 
	 * @param uses Number of uses to increase the item gains.
	 */
	public void increaseNumUses(int uses){
		numUses += uses;
	}
	
	/**
	 * Method is used to check if two food objects are the same by comparing their names.
	 * Since all the same objects will have the same name, this single comparison is sufficient.
	 * 
	 * @param otherItem The other item this is being compared to.
	 * @return Returns true if they are the same item, false if they are not the same.
	 */
	@Override
	public boolean equals(Object otherItem) {
		//This will only be used for Items, cast Item type
		if(name.equals(((Item) otherItem).getName())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Hashcode method is overridden as we needed to override the equals method.
	 * Not expected to be used in the program; comparisons between items should be
	 * only be used with equals.
	 * 
	 * Hashcode uses the item's restore value as the seed for the hash code.
	 */
	@Override
	public int hashCode() {
		return (int) (this.restoreValue * 31 + 17);
	}
	
	/**
	 * Method for deciding if this item is a toy item.
	 * 
	 * @return True if the item is a toy, false if it is not.
	 */
	public boolean isToy() {
		if(this.restoreType == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method for deciding if this item is a food item.
	 * 
	 * @return True if the item is a food, false if it is not.
	 */
	public boolean isFood() {
		if(this.restoreType == 2) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method for deciding if this item is a medical item.
	 * 
	 * @return True if the item is a medical item, false if it is not.
	 */
	public boolean isMedicine() {
		if(this.restoreType == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * When called for, will return information on the item's name, description, cost and restore value.
	 */
	@Override
	public String toString() {
		String itemInfo = null;
		if(this.isMedicine()) {
			itemInfo = String.format("%1$s: %2$s\n\nCost: $%3$.2f\nBase Health Restoration: +%4$.2f%%\n\n", this.getName(), this.getDescription(), this.getCost(), this.getRestoreValue());
		} else if(this.isFood()) {
			itemInfo = String.format("%1$s: %2$s\n\nCost: $%3$.2f\nBase Hunger Restoration: -%4$.2f%%\n\n", this.getName(), this.getDescription(), this.getCost(), this.getRestoreValue());
		} else if(this.isToy()) {
			itemInfo = String.format("%1$s: %2$s\n\nCost: $%3$.2f\nBase Happiness Restoration: +%4$.2f%%\n\n", this.getName(), this.getDescription(), this.getCost(), this.getRestoreValue());
		}
		return itemInfo;
		
	}
	
}
