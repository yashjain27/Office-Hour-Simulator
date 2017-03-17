
/**
 * This is the BooleanSource class, which abstracts a random occurence generator.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 4 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class BooleanSource {
    //Data fields
    private double probability; //Probability of a student arriving to office hor


    //Constructor

    /**
     * Empty constructor for a new BooleanSource object
     */
    public BooleanSource(){
        //Empty constructor
    }

    /**
     * Constructor for a new BooleanSource object and initializes probability to the parameter
     * value passed
     * <dt><b>Preconditions:</b></dt>
     * <dd>Throws IllegalArgumentException if the value passed is less than or equal to 0
     * or if it's greater than 1.</dd>
     * @param initProbability
     *      The probablity value to bbe passed
     * @throws IllegalArgumentException
     *      Thrown when probability is 0 or less or greater than 1
     * <dt><b>Postconditions:</b></dt>
     * <dd>Initializes the probability </dd>
     *
     */
    public BooleanSource(double initProbability){
        //Check if the probability is invalid if less than or equal to 0, or greater than 1
        if(initProbability <= 0.0 || initProbability > 1.0){
            throw new IllegalArgumentException();
        }

        //Otherwise initialize probability
        probability = initProbability;
    }

    //Accessor

    /**
     * Returns the value of the probability of the arrival
     * @return
     *      value of the probability
     */
    public double getProbability(){
        return probability;
    }

    //Mutators

    /**
     * Sets the value of the probability of arrival
     * @param prob
     *      Value passed to override the value of probabilty.
     */
    public void setProbability(double prob){
        probability = prob;
    }


    //Methods

    /**
     * Checks whether a students has arrived or not
     * @return
     *      Returns a boolean value whether a student has arrived or not
     */
    public boolean occurs(){
        //Return whether a student has arrived or not
        return (Math.random() < probability);
    }

}
