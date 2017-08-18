package medicine;
/**
 * Create a Bandage object. Inherits methods and attributes from the Medicine abstract class;
 * thus behaves as a medical item used to heal pets. Properties are unique to the Bandage object.
 * 
 * Bandage objects are created such that for all instances acting as a bandage, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class Bandage extends Medicine {

	
	/**
	 * Constructor to create a bandage medicine object by passing arguments to parent abstract class Medicine constructor.
	 */
	public Bandage(){
		super(5, "Bandage", 10, "Cheap bit of medical aid but only heals a small amount.");
	}
}
