/*Mario Soto 
Lab6 class This class contains four instance variables. One for the course id, one for the 
course name, one for the list of Students (objects for each student), and one 
for the instructor (the object for the Instructor).
The first constructor, initializes the list of students, assigns the course id, 
and assign the course name.
The second constructor, initializes the list of students, assigns the course 
id, assigns the course name, and assigns the instructor.
The method getCourseID(), returns the course id.
The method getName(), returns the course name.
The method getProfessor(), returns the Instructor
The method setCourseID(), receives as explicit parameter a course id and assigns 
it to the course.
The method setName(), receives as explicit parameter the name of the course and 
assigns it to the course.
The method setProfessor(), receives as explicit parameter the object of an 
instructor and assigns it to the course.
The method getNumStudents(), returns the total number of students in the 
students’ list.
The method addStudent(), receives as explicit parameter the object of a Student 
and stores it in the list of students*/
import java.util.ArrayList;


public class Course {

    private int courseID;
    private String name;
    private ArrayList<Student> Students;
    private Instructor Professor;
    
    public Course(int courseID, String name) {
        this.Students = new ArrayList<>();
        this.courseID = courseID;
        this.name = name;
    }
    public Course(int courseID, String name, Instructor Professor){
        this.Students = new ArrayList<>();
        this.courseID = courseID;
        this.name = name;
        this.Professor = Professor;
    }
    public int getCourseID() {
        return courseID;
}
    public String getName() {
        return name;
    }
    public Instructor getProfessor() {
        return Professor;
    }
    public void setCourseID(int courseID){
        this.courseID = courseID;
    }
    public void setCourse(Instructor Professor) {
        this.Professor = Professor;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setProfessor(Instructor Professor) {
        this.Professor = Professor;
    }
    public int getNumStudents(){
       return Students.size();
    }
    public void addStudent(Student student){
        Students.add(student);
} 
}
