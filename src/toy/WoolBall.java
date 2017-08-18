package toy;

/**
 * Create a WoolBall object. Inherits methods and attributes from the Toy abstract class;
 * thus behaves as a toy item used to play with pets. Properties are unique to the WoolBall object.
 * 
 * WoolBall objects are created such that for all instances acting as a ball of wool, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class WoolBall extends Toy {
	
	/**
	 * Constructor to create a ball of wool toy object by passing arguments to parent abstract class Toy constructor.
	 */
	public WoolBall(){
		super(8, "Ball of Wool", 20, "A fluffy ball of wool that gets messed up easily.", 15);
	}
}
