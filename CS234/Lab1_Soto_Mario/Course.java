/*
 * A class to define a Course
 */

import java.util.ArrayList;

class Course {
    
    int courseID;
    String name;
    ArrayList<Student> students;
    Instructor professor;

    /**
     * A constructor to initialize the course id and course name
     * @param courseID
     * @param name 
     */
    public Course(int courseID, String name) {
        this.students = new ArrayList<>();
        this.courseID = courseID;
        this.name = name;
    }

    /**
     * A constructor to initialize the course id, course name and the instructor
     * @param courseID
     * @param name
     * @param professor 
     */
    public Course(int courseID, String name, Instructor professor) {
        this.students = new ArrayList<>();
        this.courseID = courseID;
        this.name = name;
        this.professor = professor;
    }

    /**
     * Method to return the course id
     * @return course id
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * Method to return the course name
     * @return course name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to return the Instructor of this course
     * @return an Instructor
     */
    public Instructor getProfessor() {
        return professor;
    }

    /**
     * A method to set the course id
     * @param courseID 
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * S method to set the course name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method to set the instructor
     * @param professor 
     */
    public void setProfessor(Instructor professor) {
        this.professor = professor;
    }

    /**
     * A method to get the number of students in this course
     * @return the size of the list of students
     */
    public int getNumStudents() {
        return students.size();
    }
    
    /**
     * A method to add a new student to the list of students
     * @param aStudent 
     */
    public void addStudent(Student aStudent)
    {
        students.add(aStudent);
    }
    
    
}
