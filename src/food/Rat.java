package food;

/**
 * Class to create a rat food object. Inherits methods and attributes from the Food abstract class; thus behaves
 * as a food to be fed to pets. Properties are unique to the rat object.
 * 
 * Rat objects are created such that for all instances acting as rat, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class Rat extends Food {

	/**
	 * Constructor to create a rat food object by passing arguments to parent abstract class Food constructor.
	 */
	public Rat(){
		super(25, "Rat", 20, "A live rat. Fit for snake consumption.");
	}
}
