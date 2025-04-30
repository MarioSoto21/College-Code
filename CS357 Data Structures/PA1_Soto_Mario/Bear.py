'''
Mario Soto
'''
from Animal import Animal

class Bear(Animal):

    def __init__(self):
        """Constructor for the Bear
        This constructor initializes the features of the parent class
        Assigns "Bear" as the name of the animal
        """
        super().__init__()
        self.name = "Bear"
        

    def speak(self):
        print("\t       It says: I am eating!")