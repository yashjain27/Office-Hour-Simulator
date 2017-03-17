/**
 *This is the Helper class, which is used to create professor and TA objects
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 4 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class Helper  {
    //Data fields
    private int timeLeftTilFree;
    private final boolean isProfessor;
    private Student student;

    //Constructor

    /**
     * constructor that creates a Helper that could be either a professor or a TA.
     * If the value of isProfessor is false, then the Helper is a TA. Inversely,
     * if the value of isProfessor is true, then the Helper is a professor.
     * @param isProfessor
     *      Boolean value passed to indicate whether the helper is a TA or professor.
     * <dd><b>Postconditions:</b></dd>
     * <db>timeLeftTilFree initialized to 0.</db>
     */
    public Helper(boolean isProfessor){
        //Set the boolean value of whether it's a prof or TA
        this.isProfessor = isProfessor;

        //Initialize the timeLeftTilFree to 0
        timeLeftTilFree = 0;
    }

    //Accessors

    /**
     * Time left until the helper is free again.
     * @return
     *      Returns the value of getTimeLeftTilFree to indicate how much time is left until
     *      the helper is free.
     */
    public int getTimeLeftTilFree() {
        return timeLeftTilFree;
    }

    /**
     * Professor: true, or TA: false
     * @return
     *      Indicates whether it is a professor (true) or TA (false)
     */
    public boolean getIsProfessor(){
        return isProfessor;
    }

    /**
     * Get the student that is being helped
     * @return
     *      Returns the student that is being helped by the calling Helper object
     */
    public Student getStudent(){
        return student;
    }
    //Mutator

    /**
     * Sets the value of timeLeftTilFree to indiciate how much time is being spent by the helper
     * on student.
     * @param timeLeftTilFree
     *      The amount of time required by the student.
     */
    public void setTimeLeftTilFree(int timeLeftTilFree) {
        if(timeLeftTilFree >= 1)
            this.timeLeftTilFree = timeLeftTilFree;
        else
            this.timeLeftTilFree = 1;
    }

    /**
     * Sets the student that the calling Helper is going to help.
     * @param s
     *      Student that the Helper is going to help
     */
    public void setStudent(Student s){
        student = s;
    }
}
