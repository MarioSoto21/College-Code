/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Student {
    String name;
    int age;

    /**
     * A constructor to initialize the student name and age
     * @param name
     * @param age 
     */
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets a name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets an age
     * @param age 
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    
    
    
}
