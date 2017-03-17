/**
 * This immutable class represents a Student who goes to the professor's office hours/
 * Each instance must contain the studentId, starting with 1.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 4 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public final class Student {
    //Data fields
    private static int studentCounter = 0; //Number of students
    private final int studentId;           //Student ID number
    private final int timeArrived;         //Time the student arrived
    private final int timeRequired;        //Amount of time required for the office hour
    private final Course course;           //Course the student is enrolled in


    //Constructor

    /**
     * Constructor for a new Student object.
     * <db><b>Preconditions:</b></db>
     * <dd>Time arrived shouldn't be less than or equal to 0.</dd>
     * @param initTimeArrived
     *       What time the student arrived to the officeHour line.
     * @param course
     *       What course the student is enrolled in.
     * @param minTime
     *       The minimum time required for office hours.
     * @param maxTime
     *        The max time required for office hours.
     * @throws IllegalArgumentException
     *        Thrown when timeArrived is 0 or less
     * <dd><b>Postconditions:</b></dd>
     * <db>Initializes studentId, course, and timeArrived for the Student object
     * and increments the studentCounter</db>
     *
     */
    public Student(int initTimeArrived, Course course, int minTime, int maxTime){
        //Check if initTime is not less than or equal to 0 or courseNumber doesn't exist
        if(initTimeArrived <= 0 ){
            throw new IllegalArgumentException();
        }

        //Increment the student counter
        studentCounter++;

        //Set the studentId to the new number
        studentId = studentCounter;

        //Set the course to the course object passed
        this.course = course;

        //Set the value of timeArrived to the value of initTimeArrived passed
        timeArrived = initTimeArrived;

        //Set the value of timeRequired between min and maxTime
        timeRequired = (int) (Math.random()*(maxTime - minTime + 1) + minTime);
    }


    //Accessors

    /**
     * Number of students
     * @return
     *      Returns the number of Students
     */
    public static int getStudentCounter() {
        return studentCounter;
    }

    /**
     * Student's associated ID number
     * @return
     *      Returns the studentID number
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * What time the student arrived.
     * @return
     *      Returns the value of  timeArrived.
     */
    public int getTimeArrived() {
        return timeArrived;
    }

    /**
     * Time required for the student's visit
     * @return
     *      Returns the value of timeRequired
     */
    public int getTimeRequired() {
        return timeRequired;
    }

    /**
     * Course the student currently enrolled in
     * @return
     *      Returns the Course object
     */
    public Course getCourse() {
        return course;
    }
}

