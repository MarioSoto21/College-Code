'''
Mario Soto
'''
from Animal import Animal

class Fish(Animal):

    def __init__(self) -> None:
        """Constructor for the Fish
        This constructor initializes the features of the parent class
        Assigns "Fish" as the name of the animal
        """
        super().__init__()
        self.name = "Fish"
        