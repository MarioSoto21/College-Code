/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Instructor {
    
    String name;
    String location;
    
    /**
     * A Constructor that initializes the name with "STAFF"
     * and location with "Portales"
     */
    public Instructor()
    {
        this.name = "STAFF";
        this.location = "Portales";        
    }
    
    /**
     * A Constructor that initializes the instructor name and location
     * @param name
     * @param location 
     */
    public Instructor(String name, String location){
        this.name = name;
        this.location = location;
    }

    /**
     * Returns the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the location
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets a name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a location
     * @param location 
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    
    
    
    
}
