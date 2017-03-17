import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The OfficeHourSimulator class is the driver class for the Office Hour Simulator.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 4 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class OfficeHourSimulator {
    //Create a new static StudentQueue object, could be accessed anywhere within this class
    static StudentQueue studentQueue = new StudentQueue();

    //Create a new professor object
    static Helper professor = new Helper(true);

    //Create an array for TAs equal to the size of numTAs
    static Helper[] taArray;

    //Total wait time timer
    static int totalWaitTime;

    //Main method
    public static void main(String[] args) {
        //Declaration of variables
        int numCourses = 0, minTime = 0, maxTime = 0, numCups = 0, officeHrTime = 0, numTAs = 0;
        int[] courseNumbers = {};
        double[] arrivalProbability = {};
        String fileName;

        //Scanner
        Scanner input = new Scanner(System.in);

        //Request a file to be read
        while ((true)) {
            try {
                //Enter file name to be read
                print("Please enter a file name: ");
                fileName = input.nextLine();

                //See if the user wants to EXIT instead
                if (fileName.equals("EXIT"))
                    System.exit(0);

                //Try reading the file if valid
                input = new Scanner(new File("src\\" + fileName));
                break;
            } catch (FileNotFoundException e) {
                println("File not found. Try again, or type EXIT to exit.");
            }
        }

        /**Read file**/
        //Read Number of Courses
        fileName = input.nextLine();
        fileName = fileName.substring(fileName.indexOf(':') + 1, fileName.length());
        numCourses = Integer.parseInt(fileName);

        //Initialize the size of courseNumbers and arrivalProbability array.
        courseNumbers = new int[numCourses]; //Initialize the size of courseNumbers array.
        arrivalProbability = new double[numCourses]; //Initialize the size arrivalProbability array.

        //Read the CourseNumbers from the file
        fileName = input.nextLine();
        fileName = fileName.substring(fileName.indexOf(':') + 1, fileName.length());
        String[] stringArray = fileName.split(" ");

        //Convert CourseNumbers from string array to int array, numCourses times.
        for (int i = 0; i < numCourses; i++) {
            courseNumbers[i] = Integer.parseInt(stringArray[i]);
        }

        //Read the arrivalProbability from the file
        fileName = input.nextLine();
        fileName = fileName.substring(fileName.indexOf(':') + 1, fileName.length());
        stringArray = fileName.split(" ");

        //Convert arrivalProbability from string array to int array, numCourses times.
        for (int i = 0; i < numCourses; i++) {
            arrivalProbability[i] = Double.parseDouble(stringArray[i]);
        }

        //Read minTime
        fileName = input.nextLine();
        fileName = fileName.substring(fileName.indexOf(':') + 1, fileName.length());
        minTime = Integer.parseInt(fileName);

        //Read maxTime
        fileName = input.nextLine();
        fileName = fileName.substring(fileName.indexOf(':') + 1, fileName.length());
        maxTime = Integer.parseInt(fileName);

        //Read number of Coffee cups
        fileName = input.nextLine();
        fileName = fileName.substring(fileName.indexOf(':') + 1, fileName.length());
        numCups = Integer.parseInt(fileName);

        //Read simulation time
        fileName = input.nextLine();
        fileName = fileName.substring(fileName.indexOf(':') + 1, fileName.length());
        officeHrTime = Integer.parseInt(fileName);

        //Read the number of TAs
        fileName = input.nextLine();
        fileName = fileName.substring(fileName.indexOf(':') + 1, fileName.length());
        numTAs = Integer.parseInt(fileName);

        //Course array
        Course[] courses = new Course[numCourses];

        //Sort probabilities and courseDifficulty
        Arrays.sort(courseNumbers);
        Arrays.sort(arrivalProbability);
        for(int i = 0; i < numCourses/2; i++){
            int course = courseNumbers[i];
            double prob = arrivalProbability[i];
            courseNumbers[i] = courseNumbers[numCourses - 1 - i];
            arrivalProbability[i] = arrivalProbability[numCourses - 1 - i];
            courseNumbers[numCourses - 1 - i] = course;
            arrivalProbability[numCourses - 1 - i] = prob;
        }

        //Set up the Course array
        for (int i = 0; i < numCourses; i++) {
            Course course = new Course(courseNumbers[i], arrivalProbability[i]); //new course object
            course.setCourseDifficulty((numCourses - 1) - i); //set the course difficulty
            courses[i] = course; //add the course to the array.
        }

        //Print out the general rules below
        println("\nCourse   Probability");
        println("--------------------");

        for(int i = 0; i < numCourses; i++){  //courseNumbers and it's probability
            print("" + courseNumbers[i]);
            println("\t\t" + arrivalProbability[i]);
        }
        println("Number of TAs: " + numTAs);
        println("Coffee cups: " + numCups);
        println("Base time interval " + minTime + "-" + maxTime + " minutes.");
        println("Time: " + officeHrTime + " minutes.\n");

        //Simulate
        simulate(officeHrTime, arrivalProbability, courses, minTime, maxTime, numCups, numTAs);

        println("\n__________________________________________");
        println("End simulation.");

        //Total wait time
        for(int i = 0; i < numCourses; i++){
            totalWaitTime += courses[i].getWaitTime();
        }

        //Print out statistics
        println("\nStatistics:");
        println("\nCourse\t\t#StudentsHelped\t\tAvg.Time");
        println("__________________________________________");
        System.out.printf("%-14s%-19d%-3.2f mins", "Total", Student.getStudentCounter(),
                (double) totalWaitTime/Student.getStudentCounter() );

        for(int i = 0; i < numCourses; i++){
            System.out.printf("\n%-14d%-19d%-3.2f mins", courseNumbers[i], courses[i].getStudentCounter(),
                     (double) courses[i].getWaitTime()/courses[i].getStudentCounter());
        }
        println("\n\nThanks for using this simulation software!");
    }

    /**
     * Simulation. This method simulates.
     * @param officeHrTime
     *       The total Office hour time
     * @param arrivalProbability
     *       The probability of arrival for every course.
     * @param courses
     *       All the courses the professor is teaching.
     * @param minTime
     *       Minimum number of time required by the student.
     * @param maxTime
     *       Maximum Number of time required by the student.
     * @param numCups
     *       Number of coffee cups the professor drank
     * @param numTAs
     *       Number of TAs helping the professor
     */
    public static void simulate(int officeHrTime, double[] arrivalProbability,
                                Course[] courses, int minTime, int maxTime, int numCups, int numTAs) {
        //Initialize the TA array
        taArray = new Helper[numTAs];

        //Add the amount of TAs to the array
        for (int i = 0; i < numTAs; i++) {
            Helper newTa = new Helper(false);
            taArray[i] = newTa;
        }

        //New boolean source object
        BooleanSource booleanSource = new BooleanSource();

        println("Begin simulation:"); //print out this message

        //Simulate the simulation, officeHrTime times.
        for (int i = 0; i < officeHrTime; i++) {
            //Print out current time step                                      /
            println("__________________________________________________________");
            println("Time Step " + (i + 1) + ":");

            //Simulate whether the students for each course has arrived at every step of time
            for (int j = 0; j < courses.length; j++) {
                //Set the probability of the booleanSource at the current iteration for course Array.

                booleanSource.setProbability(courses[j].getArrivalProbability());

                //If occurs() return true, create a new Student object for the current iteration of course Array.
                if (booleanSource.occurs()) {
                    //Create new student object at current iteration of course array.
                    Student student = new Student((i + 1), courses[j], minTime, maxTime);

                    //Increment the course's studentCounter
                    courses[j].setStudentCounter();

                    //Print out the student information: studentID, courseNumber, time needed
                    println("Student " + student.getStudentId() + " has arrived for "
                            + courses[j].getCourseNumber() + " requiring "
                            + student.getTimeRequired() + " minutes.");

                    //Put him or her in StudentQueue
                    studentQueue.enqueue(student);
                } else {
                    println("No Student has arrived for " + courses[j].getCourseNumber() + ".");
                }
            }

            help(numCups, numTAs);
        } //For-loop iterator for officeHrTime ends here

        println("OFFICE HOURS TIME OVER");
        println("_________________________________________________________");

        while (!studentQueue.isEmpty())
            help(numCups, numTAs);

    }

    /**
     * Professor and TAs help students every minute. Manages remaining minutes, and dequeues students of
     * StudentQueue
     * @param numCups
     *      Number of coffee cups the Professor has consumed.
     * @param numTAs
     *      Number of TAs in the office Hour.
     */
    public static void help(int numCups, int numTAs) {
        //Increment the wait time by one;
        studentQueue.incWaitTime();

        //Simulate the professor helping the students out in the queue if available
        if (professor.getTimeLeftTilFree() <= 1) { //If professor has less than one min left, get new student
            //Create a temp Student object for operation
            Student s = null;

            //Dequeue the line, if there is anyone in the studentQueue, and set it to student obj.
            try {
                s = studentQueue.dequeue();

                //Dequeue student in front of the studentQueue line, and set the timer for professor
                professor.setTimeLeftTilFree(s.getTimeRequired());

                //Set the student that the professor is going to help
                professor.setStudent(s);

                //Subtract the time until the professor is free due to coffee
                if (professor.getTimeLeftTilFree() - numCups >= 1) {
                    //Set the new time left for professor until he's free
                    professor.setTimeLeftTilFree(professor.getTimeLeftTilFree() - numCups);
                } else {
                    //Else set the time until free to one if time left due to coffee is < 1
                    professor.setTimeLeftTilFree(1);
                }

                //Print out who the professor is helping
                println("\nProfessor is helping Student " + professor.getStudent().getStudentId() + ", "
                        + professor.getTimeLeftTilFree() + " minutes remaining.");
            } catch (EmptyQueueException e) {
                /**
                 * Over here "professor and TA waiting for student to arrive.
                 */
            }

        } else {
            //Else decrement a minute from TimeLeftTiFree
            professor.setTimeLeftTilFree(professor.getTimeLeftTilFree() - 1);

            //Print out who the professor is helping
            println("\nProfessor is helping Student " + professor.getStudent().getStudentId() + ", "
                    + professor.getTimeLeftTilFree() + " minutes remaining.");
        }


        //Simulate the TAs helping the students out in the queue if they are available
        for (int j = 0; j < numTAs; j++) {
            if (taArray[j].getTimeLeftTilFree() <= 1) { //If TA has less than a min left, get next student
                //Create a temp Student object for operation
                Student s = null;

                //Dequeue the line, if there is anyone in the studentQueue, and set it to student obj.
                try {
                    s = studentQueue.dequeue();

                    //Dequeue student from the studentQueue line, and set the timer for the TA
                    taArray[j].setTimeLeftTilFree(s.getTimeRequired() * 2);

                    //Set the student that the TA is going to help
                    taArray[j].setStudent(s);

                    //Print out who the TA is helping
                    println("TA " + (j + 1) + " is helping Student " + taArray[j].getStudent().getStudentId() + ", "
                            + taArray[j].getTimeLeftTilFree() + " minutes remaining.");
                } catch (EmptyQueueException e) {

                }

            } else {
                //Else decrement a minute from TimeLeftTilFree
                taArray[j].setTimeLeftTilFree(taArray[j].getTimeLeftTilFree() - 1);

                //Print out who the TA is helping
                println("TA " + (j + 1) + " is helping Student " + taArray[j].getStudent().getStudentId() + ", "
                        + taArray[j].getTimeLeftTilFree() + " minutes remaining.");
            }
        }
        println("\n");

        //local int for size of queue.
        int size = studentQueue.size();

        //Set up a nice printable table of the studentQueue
        println("Student Queue:");
        println("ID     Course      Required Time       Arrival Time");
        println("----------------------------------------------------");
        println(studentQueue.toString()); //Print the studentQueue
    }

    /**
     * Method that prints out a message
     *
     * @param message Message that is to to be outputted to the screen.
     */
    public static void print(String message) {
        System.out.print(message);
    }

    /**
     * Method that prints out a message and creates a new line.
     *
     * @param message Message that is to be outputted to the screen
     */
    public static void println(String message) {
        System.out.println(message);
    }

}
