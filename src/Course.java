/**
 *This class represents a course which is offered at the university.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 4 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class Course {
    //Data fields
    private int courseNumber;           //The course Number
    private int courseDifficulty;       //Level of difficulty of course
    private double arrivalProbability;  //The probability of arrival at office hour
    private int studentCounter;         //Amount of students who came to help in this course
    private int waitTime;               //Total wait time for this course

    //Constructor

    /**
     * Constructor for a new Course object
     * <db><b>Preconditions:</b></db>
     * <dd>courseNumber exists in the given courseNumbers</dd>
     * @param courseNumber
     *        Course number associated with this course
     * @param arrivalProbability
     *         Probability of arrival.
     * <dd><b>Postconditions:</b></dd>
     * <db>Initializes the courseNumber and arrivalProbability</db>
     */
    public Course(int courseNumber, double arrivalProbability){
        this.courseNumber = courseNumber;
        this.arrivalProbability = arrivalProbability;
    }

    //Accessors

    /**
     * The course number.
     * @return
     *      Returns the courseNumber.
     */
    public int getCourseNumber() {
        return courseNumber;
    }

    /**
     * The difficulty of the course
     * @return
     *      Returns the courseDifficulty.
     */
    public int getCourseDifficulty() {
        return courseDifficulty;
    }

    /**
     * The probabilty of arriving to the office hour
     * @return
     *      Returns the arrivalProbability
     */
    public double getArrivalProbability() {
        return arrivalProbability;
    }

    /**
     * The number of students who came for help for this course
     * @return
     *      Indicates how many students came for help in this course.
     */
    public int getStudentCounter(){
        return studentCounter;
    }

    /**
     * The total amount of wait time for this course
     * @return
     *      Returns the value of waitTime.
     */
    public int getWaitTime(){
        return  waitTime;
    }
    //Mutator

    /**
     * Set the difficulty of the course.
     * @param courseDifficulty
     *      Sets the difficulty of the course
     */
    public void setCourseDifficulty(int courseDifficulty) {
        this.courseDifficulty = courseDifficulty;
    }

    /**
     * Sets the new value for studentCounter
     */
    public void setStudentCounter(){
        studentCounter ++;
    }

    /**
     * Sets the total waitTime for the course
     */
    public void setWaitTime(){
        waitTime++;
    }

}
