

/* Mario Soto
This class contains two instance variables. One for the name of the student and 
another for the age of the student.
The constructor receives as explicit parameters the name and age and assigns 
them to the instance variables.
The method getName(), returns the name of the student
The method getAge(), returns the age of the student
The method setName(), receives as explicit parameter the name of the student 
and assigns it to the instance variable.
The method setAge(), receives as explicit parameter the age of the student and 
assigns it to the instance variable.

 */

public class Student {
	// Instance variables
    private String name;
    private int age;
    
       public Student(String name, int age) {
        this.name = "";
        this.age = age;
    }

    /**
     * Sets the name for the student
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    /*
     Gets the student's name
     @return the student's name
     */
    public String getName()
    {
        return name;
    }
    //Sets the age for the student
    public void setAge(int age) {
        this.age = age;
    }
    public int setAge()
    {
        return age;
    }
}
