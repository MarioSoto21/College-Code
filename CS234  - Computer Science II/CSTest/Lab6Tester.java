/**
 *
 * @author sotoM
 */
public class Lab6Tester {

    public static void main(String[] args) {
       School enmu = new School();
       
        System.out.println(enmu.getNumCourses());
//          create students
        Student s1 = new Student("Emma", 18);
        Student s2 = new Student("John", 21);
        Student s3 = new Student("Julian", 20);
        Student s4 = new Student("Bella", 19);
        Student s5 = new Student("Carlos", 22);
        Student s6 = new Student("Stephen", 18);
//          Create instructors
        Instructor ecv = new Instructor("Eduardo", "Portales");
        Instructor ei = new Instructor("Essa", "Clovis");
        Instructor staff = new Instructor();
//          Create courses
        Course cs123 = new Course(123, "Computr Science I");
        cs123.setProfessor(ei);
        Course cs234 = new Course(234, "Computer Science II");
        cs234.setProfessor(ecv);
        Course csxx = new Course(111, "Directed Studies", staff);
//          Add students to courses
        cs123.addStudent(s6);
        cs123.addStudent(s5);

        cs234.addStudent(s1);
        cs234.addStudent(s6);
        cs234.addStudent(s2);
        cs234.addStudent(s4);

        csxx.addStudent(s2);
//          Add courses to the school
        enmu.addCourse(cs123);
        enmu.addCourse(cs234);
        enmu.addCourse(csxx);

        System.out.println(enmu.getNumCourses());

//          This course exists
        enmu.getCourseInfo(234);
//          This course does not exist
        enmu.getCourseInfo(555);
    }
}
