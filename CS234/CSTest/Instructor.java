/*Mario Soto 
This class contains two instance variables. One for the name of the instructor 
and another for the location.
The first constructor, assigns “STAFF” for the name and “Portales” for the 
location.
The second constructor receives as explicit parameters the name and location 
and assigns them to the instance variables.
The method getName(), returns the name of the instructor
The method getLocation(), returns the location of the instructor
The method setName(), receives as explicit parameter the name of the instructor 
and assigns it to the instance variable.
The method setLocation(), receives as explicit parameter the location of the 
instructor and assigns it to the instance variable*/
public class Instructor {
    private String name;
    private String location;
public Instructor(){
    this.name = "STAFF";
    this.location = "Portales";
}
public Instructor(String name, String location) {
    this.name = name;
    this.location = location;
}
public String getName(){
    return name;
}
public String getLocation(){
    return location;
}
public void setName(String name){
    this.name = name;
}
public void setLocation(String location) {
    this.location = location;
}
}
