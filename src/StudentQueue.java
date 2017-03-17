import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This is the StudentQueue class, which is used to represent
 * the line of students outside the professor’s office in terms of *priority.
 * So this is a PriorityQueue
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 4 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class StudentQueue {
    //Data fields
    private ArrayList<Student> studentQueue = new ArrayList<>(); //New Linked List that holds the students in a priority queue


    //Constructor

    /**
     * Empty constructor that creates a new line for office hours for the students.
     * Creates a new priority StudentQueue.
     * <dd><b>Postconditions:</b></dd>
     * <db>Created a new StudentQueue object.</db>
     */
    public StudentQueue(){
        //Empty constructor
    }

    //Methods

    /**
     * Enqueues the passed Student into the specified StudentQueue based on priority.
     * <dd><b>Preconditions:</b></dd>
     * <db>The studentQueue object should be instantiated.</db>
     * @param s
     *      Student object that is to be added to queue
     * <dd><b>Postconditions:</b></dd>
     * <db>Student added to the queue.</db>
     */
    public void enqueue(Student s){
        //Find out the courseDifficulty / Priority level
        int courseDifficulty = s.getCourse().getCourseDifficulty();

        //If the queue is empty add it to the front of the line.
        if(isEmpty()){
            studentQueue.add(s);
        }
        //Otherwise, add the Student to his or her respective priority in the Queue
        else {

            for(int i = 0; i < size(); i++){
                //Check whether if the Student's current course difficulty is greater the course difficulty at specified iteration
                //System.out.println("size: " + size());
                if(courseDifficulty > studentQueue.get(i).getCourse().getCourseDifficulty()){
                    studentQueue.add(i, s); //Add student
                    return;
                }
                //Check whether you've reached end of line
                else if(i == size() - 1){
                    studentQueue.add(s);
                    return;
                }
            }

        }
    }

    /**
     * Dequeues the first student in the line, who has the highest priority
     * @return
     *      Student object that is the first in the StudentQueue object.
     * @throws
     *      throws an exception when the Queue is empty.
     *  <dd><b>Postconditions:</b></dd>
     *  <db>A student is dequeued from front of the Queue</db>
     */
    public Student dequeue() throws EmptyQueueException{
        //Remove the Student at the front of line
        if(!studentQueue.isEmpty())
            return studentQueue.remove(0);
        else
            throw new EmptyQueueException();
    }

    /**
     * Gets how many students there are in the line.
     * @return
     *      Returns the size of the StudentQueue object
     */
    public int size(){
        return studentQueue.size();
    }

    /**
     * Tells whether the studentQueue is empty or not
     * @return
     *      Returns <b>boolean</b> value if whether the Queue is empty or not
     *      True: yes, it is empty. False otherwise.
     */
    public boolean isEmpty(){
        return studentQueue.isEmpty();
    }

    /**
     * Increments the total wait time of all the students for the particular course
     */
    public void incWaitTime(){
        //Wait Time
        for(int i = 0; i < studentQueue.size(); i++){
            studentQueue.get(i).getCourse().setWaitTime();
        }
    }

    /**
     * String representation of StudentQueue.
     * @return
     *      String representation of StudentQueue
     */
    @Override
    public String toString(){
        String pQueue = "";
        Student tempStudent;

        for(int i = 0; i < studentQueue.size(); i++){
            tempStudent = studentQueue.get(i);
            pQueue += String.format("%-7d%-14d%-20d%d\n",tempStudent.getStudentId(),
                    tempStudent.getCourse().getCourseNumber(), tempStudent.getTimeRequired(),
                    tempStudent.getTimeArrived());
        }

        return pQueue;
    }

}
