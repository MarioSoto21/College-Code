import random
from Animal import Animal
from Bear import Bear
from Fish import Fish

random.seed(42)    # Change this number for your different tests.

class Ecosystem():
    def __init__(self, size):
        """ Constructor for the Ecosystem
        It initializes the river (a list with a given size)
        It calls the method to initialize the river with animals in random places
        """
        self.river_size  =  size
        self.river = [None] * self.river_size
        self.initialize_random_ecosystem()


    def initialize_random_ecosystem(self):
        """ Method to randomly initialize the ecosystem
        Randomly picks a number between 1 and 4 to decide the number of Bears
        Randomly places the Bears in the river in empty positions
        Randomly picks a number between 1 and 4 to decide the number of Fish
        Randomly places the Fish in the river in empty positions
        """
        initial_num_bears = random.randint(1,3)
        print(f"Initial number of bears: {initial_num_bears}")
        initial_num_fish = random.randint(1,3)
        print(f"Initial number of fish: {initial_num_fish}")

        for b in range(initial_num_bears):
            bear_assigned = False
            while bear_assigned != True:
                position = random.randint(0, self.river_size-1)
                if self.river[position] == None:
                    print("Bear assigned to position", position)
                    bear = Bear()
                    self.river[position] = bear
                    bear._location = position
                    bear_assigned = True

        
        for b in range(initial_num_fish):
            fish_assigned = False
            while fish_assigned != True:
                position = random.randint(0, self.river_size-1)
                if self.river[position] == None:
                    print("Fish assigned to position", position)
                    fish = Fish()
                    self.river[position] = fish
                    fish._location = position
                    fish_assigned = True

    def animal_creation(self, type_of_animal):
        """Method to create a new type of animal when two animals of the same type collides
        The new created animal is placed randomly in the river in any empty position
        """
        if type_of_animal == "Bear":
            x = Bear()
        else:
            x = Fish()
        animal_assigned = False
        while animal_assigned != True:
            position = random.randint(0, self.river_size-1)
            if self.river[position] == None:
                print(f"New {type_of_animal} assigned to position", position)
                self.river[position] = x
                x._location = position
                animal_assigned = True

    def show(self):
        """Method to show each position in the river and the animal each position contains
        """
        for e in self.river:
            print(e)


    def simulate(self, iterations):
        """Method to simulate the Ecosystem movement
        It similates the ecosystem live depending on the number of iterations passe as argument
        In each iteration, it processes in order each animal
        It randomly picks an action (0=move, 1=stay)
        It moves the animal and do all the similation as defined in the scenario.
            It checks if the next position is empty to move the animal
            If the position is not empty, then it checks what type of animal is in the next position
                It decides what action to perform based on the type of animal in the next position
        """
        print("~~~ Initializing the Simulation ~~~")
        for t in range(iterations):
            print(f"~~~ Iteration {t+1} ~~~~")
            print("\n"*2)
            for i, a in enumerate(self.river):
                if a != None and a._get_location() != self.river_size: #-1
                    print(f"Processing Animal at location {a._get_location()}")
                    move_stay = random.choice([0,1])
                    print(f"\tAnimal chose to {move_stay}")
                    if move_stay == 0:
                        # If the animal is not in the last position
                        if a._get_location()!= self.river_size-1:
                            next_location = a._get_location() + 1
                            # if next location is empty and next location is not beyond the river size, then just move the animal
                            if self.river[next_location] == None:
                                print(f"\t\tLocation {next_location} is empty, so move it")
                                self.river[a._get_location()] = None    # Remove the animal from its original location
                                self.river[next_location] = a   # Move the animal to its new location
                                # a._location = next_location # Change the location of the animal
                                a._move( next_location) # Change the location of the animal
                            

                            else: # Next location has an animal already
                                if type(a) == type(self.river[next_location]): # Same type of animal
                                    print("Stay", a, "BUT")
                                    self.create_new_animal(a)
                                else:   # Not same animal
                                    print("\tMove", a, "to", next_location)
                                    # If the animal moving is a Fish (the other must be a Bear)
                                    # Then a must die
                                    if a.get_class_name() == "Fish":
                                        print("\tFish is eaten by Bear")
                                        a.speak()
                                        self.river[a._get_location()] = None
                                    else: # the animal moving is a Bear and the animal in the receiving location is a Fish
                                        print("Bear eats Fish")
                                        self.river[a._location] = None
                                        a.speak()
                                        # a._location = next_location
                                        a._move(next_location)
                                        self.river[next_location] = a
                        else: # The animal is in the last posiion, just move it from the river
                            self.river[a._get_location()] = None   
                            print(f"\t\t{a} exits the river ")

                    else:   # Stay
                        print("\t\tStay")


    def create_new_animal(self, a):
        """Optional Method
        You can optionally use this method to get the animal's class name
        before you use the method animal_creation()
        """
        t = a.get_class_name()
        print("\tCreating a new", t)
        self.animal_creation(t)