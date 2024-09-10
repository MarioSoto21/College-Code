/*Mario Soto 
This class contains a single instance variable for a list of Courses (objects 
for each course).
The constructor initializes the list of courses.
The method addCourse(), receives as explicit parameter an object of class Course
and adds it to the list of courses.
The method getNumCourses(), returns the total number of courses stored in the 
list of courses.
The method getCourseInfo(), receives as explicit parameter a course id. If the 
course id exists, then it prints out the information of that course. Otherwise, 
it should send a message to the user saying that the course does not exists */
import java.util.ArrayList;
public class School {
private ArrayList<Course> Courses;

public School(){
    this.Courses = new ArrayList<>();
}
public void addCourse(Course course) {
    this.Courses.add(course);
}
public int getNumCourses(){
 int numCourses = Courses.size();
       return numCourses;
}
public void getCourseInfo(int courseId) {
    if(Course.getCourseID(courseID)){
       Course Course =Courses.get(courseID);
        System.out.println("The course"+Course.getName()+"has");
        System.out.println(+Course.getNumStudents()+" students");
    }
    else
        System.out.println("The course"+courseID+"does not exist");
    
}
}
