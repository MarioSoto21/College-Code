'''
Mario Soto
'''
class Animal:

    def __init__(self):
        """
        Constructor for the Amimal.
        Every animal has a location and name
        """
        self.name = "Default"
        self.location = -1
        

    def __str__(self):
        """Method used to print the name o the animal and its location
        """
        print(self.name)
        print(self.location)
        
        #return self.name
        # self.location

    def _move(self, new_location):
        """ Method to set the new location
        """
        self.location += new_location

    def _get_location(self):
        """ Method to return the location
        """
        return(self.location)
        

    def get_class_name(self):
        """ Method to return the class name
        """
        return(self.name)

    def speak(self):
        """Method that allows the animal to speak
        """
        print("\tIt says: *Silence...*")