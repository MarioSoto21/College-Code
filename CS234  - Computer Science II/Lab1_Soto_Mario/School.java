/*
 * Class to define a School
 */

import java.util.ArrayList;

public class School {
    
    ArrayList<Course> courses;

    /**
     * Basic constructor
     * Initializes the list of courses
     */
    public School() {
        this.courses  = new ArrayList<>();
    }

    /**
     * Method to add a new course to a list of course
     * @param aCourse 
     */
    public void addCourse(Course aCourse){
        courses.add(aCourse);
    }
    
    
    /**
     * Method to get the number of courses in the list of courses
     * @return 
     */
    public int getNumCourses() {
        return courses.size();
    }
    
    /**
     * Method to show the course information given a course id
     * If the course does not exist, it shows a message to the user
     * If the course exists, then it shows the course name, number of students
     * and the instructor name
     * @param aCourseID 
     */
    public void getCourseInfo(int aCourseID) {
        Course aCourse = null;
        for (Course c : courses)
        {
            if (c.getCourseID() == aCourseID) {
                aCourse = c;
            }
        }
        if (aCourse == null)
        {
            System.out.println("The course " + aCourseID + " does not exist" );
            return;
        }
        
        String name = aCourse.getName();
        int numStudents = aCourse.getNumStudents();
        Instructor aInstructor = aCourse.getProfessor();
        String instructorName = aInstructor.getName();
        
        System.out.println("The course '" + name + "' has " + numStudents + " students. ");
        System.out.println("The instructor is " + instructorName);
        
    }
    
    
    
}
