package toy;
/**
 * Create a HamBall object. Inherits methods and attributes from the Toy abstract class;
 * thus behaves as a toy item used to play with pets. Properties are unique to the HamBall object.
 * 
 * HamBall objects are created such that for all instances acting as a hamster ball, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class HamBall extends Toy {

	
	/**
	 * Constructor to create a hamster ball toy object by passing arguments to parent abstract class Toy constructor.
	 */
	public HamBall(){
		super(7, "Hamster Ball", 15, "A transparent globe that hamsters can run around freely in.", 20);
	}
}
