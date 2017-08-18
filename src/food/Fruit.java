package food;

/**
 * Class to create a fruit food object. Inherits methods and attributes from the Food abstract class; thus behaves
 * as a food to be fed to pets. Properties are unique to the fruit object.
 * 
 * Fruit objects are created such that for all instances acting as fruit, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class Fruit extends Food{

	
	/**
	 * Constructor to create a fruit food object by passing arguments to parent abstract class Food constructor.
	 */
	public Fruit(){
		super(15, "Fruit", 15, "100% organic and natural.");
	}
}
