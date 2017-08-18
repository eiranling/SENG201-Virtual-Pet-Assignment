package toy;
/**
 * Create a Pool object. Inherits methods and attributes from the Toy abstract class;
 * thus behaves as a toy item used to play with pets. Properties are unique to the Pool object.
 * 
 * Pool objects are created such that for all instances acting as a pool, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class Pool extends Toy {
	
	/**
	 * Constructor to create a pool toy object by passing arguments to parent abstract class Toy constructor.
	 */
	public Pool(){
		super(25, "Pool", 400, "A small swimming pool filled with water for animals to swim.", 50);
	}
}
