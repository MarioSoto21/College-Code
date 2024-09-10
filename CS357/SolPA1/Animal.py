class Animal:

    def __init__(self):
        """
        Constructor for the Amimal.
        Every animal has a location and name
        """
        self._location = 0
        self._name = ""

    def __str__(self):
        """Method used to print the name o the animal and its location
        """
        return f"{self._name} at location {self._location}"

    def _move(self, new_location):
        """ Method to set the new location
        """
        self._location = new_location

    def _get_location(self):
        """ Method to return the location
        """
        return self._location

    def get_class_name(self):
        """ Method to return the class name
        """
        return self.__class__.__name__
    
    def speak(self):
        """Method that allows the animal to speak
        """
        print("\tIt says: *Silence...*")