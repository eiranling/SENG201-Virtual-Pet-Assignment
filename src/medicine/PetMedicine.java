package medicine;

/**
 * Create a PetMedicine object. Inherits methods and attributes from the Medicine abstract class;
 * thus behaves as a medical item used to heal pets. Properties are unique to the PetMedicine object.
 * 
 * PetMedicine objects are created such that for all instances acting as pet medicine, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class PetMedicine extends Medicine {

	
	/**
	 * Constructor to create a pet medicine medicine object by passing arguments to parent abstract class Medicine constructor.
	 */
	public PetMedicine(){
		super(20, "Pet Medicine", 50, "A better alternative to healing your beloved pets.");
	}
}
