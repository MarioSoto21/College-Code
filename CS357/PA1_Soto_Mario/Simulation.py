'''
Mario Soto
'''
import random
from Animal import Animal
from Bear import Bear
from Fish import Fish

# random.seed(42)    # Change this number for your different tests.

class Ecosystem():
    def __init__(self, size):
        """ Constructor for the Ecosystem
        It initializes the river (a list with a given size)
        It calls the method to initialize the river with animals in random places
        """
        # Remove pass and write your code here
        self._size = size
        self._river = [None] * size
        self.initialize_random_ecosystem()


    def initialize_random_ecosystem(self):
        """ Method to randomly initialize the ecosystem
        Randomly picks a number between 1 and 4 to decide the number of Bears
        Randomly places the Bears in the river in empty positions
        Randomly picks a number between 1 and 4 to decide the number of Fish
        Randomly places the Fish in the river in empty positions
        """
        # Remove pass and write your code here
        number_bear = random.randint(1, 4)
        print("\nInitial number of bears: " + str(number_bear))
        number_fish = random.randint(1, 4)
        print("\nInitial number of fish: " + str(number_fish))
        for i in range(number_bear):
            b = Bear()
            a = True
            while (a == True):
                l = random.randrange(len(self._river))
                if self._river[l] == None:
                    self._river[l] = b
                    b.location = l
                    a = False
                    print("\nBear assigned to position " + str(b._get_location())) 
                
            
        for i in range(number_fish):
            f = Fish()
            aa = True
            while (aa == True):
                l = random.randrange(len(self._river))
                if self._river[l] == None:
                    self._river[l] = f
                    f.location = l
                    aa = False
                    print("\nFish assigned to position " + str(f._get_location())) 
            

       

    def animal_creation(self, type_of_animal):
        """Method to create a new type of animal when two animals of the same type collides
        The new created animal is placed randomly in the river in any empty position
        """
        # Remove pass and write your code here
        a = type_of_animal
        n = True
        for i in range(self._size):
            while(n == True):
                l = random.randrange(len(self._river))
                if self._river[l] == None:
                    self._river[l] = a
                    a.location = l
                    n = False
                    print("\nNew"+ str(a.name)+ " assigned to postion " + str(a._get_location()))                
       

    def show(self):
        """Method to show each position in the river and the animal each position contains
        """
        # Remove pass and write your code here
        s = ''
        for i in self._river:
            if i:
                if type(i) == Bear:
                    s += "\nBear at location " + str(i._get_location())
                elif type(i) == Fish:
                    s += "\nFish at location " + str(i._get_location())
            else:
                s += "\nNone"
        print(s)

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
        # Write your code here
        
        i = iterations
        for i in range(1,iterations+1):
            print("~~~ Initializing the Simulation ~~~")
            print("~~~ Iteration " + str(i) + " ~~~~")
            print("\n")
            print("\n")
            for i in self._river:
                if i:
                    if type(i) == Bear:
                        print("\nProcessing Animal at location " + str(i._get_location()) )
                        randomMove = random.randint(0, 1)
                        i = i._get_location()
                        
                            
                        if randomMove == 0:
                            #x = i
                            if i + 1 < len(self._river):
                                #Animal moves onto an empty list
                                if self._river[i + 1] == None:
                                    self._river[i + 1] = self._river[i]
                                    self._river[i] = None
                                    print("\n   Animal chose to 0")
                                    #print("\n           Location"+str(i._get_location())+" is empty, so move it")
                                elif type(self._river[i] == type(self._river[i+1])):
                                    #two animals of the same type meet
                                    if type(self._river[i]) == Bear:
                                        #print("\nStay Bear at"+str(i._get_location()))
                                        print("\nCreating new Bear")
                                        self.animal_creation(Bear())
                                    else:
                                        self.animal_creation(Fish())
                                else:
                                    #Bear Kills fish if they encounter eachother
                                    if type(self._river[i]) == Bear:
                                        self._river[i + 1] = self._river[i]
                                        self._river[i] = None
                                        print("\nBear eats Fish")
                                        Bear.speak(self)
                        if randomMove == 1:
                            print("\tAnimal Choose to 1")
                            print("\t   Stay")
                    elif type(i) == Fish:
                        print("\nProcessing Animal at location " + str(i._get_location()) )
                        randomMove = random.randint(0, 1)
                        i = i._get_location()
                        if randomMove == 0:
                            #x = i
                            if i + 1 < len(self._river):
                                #Animal moves onto an empty list
                                if self._river[i + 1] == None:
                                    self._river[i + 1] = self._river[i]
                                    self._river[i] = None
                                    print("\n   Animal chose to 0")
                                    #print("\n           Location"+str(i._get_location())+" is empty, so move it")
                                elif type(self._river[i + 1] == type(self._river[i])):
                                    #two animals of the same type meet
                                    if type(self._river[i]) == Bear:
                                        self.animal_creation(Bear())
                                    else:
                                        print("\n   Creating a new Fish")
                                        self.animal_creation(Fish())
                                        
                                else:
                                    # Fish is killed if Bear is encountered
                                    if type(self._river[i]) == Bear:
                                        self._river[i + 1] = self._river[i]
                                        self._river[i] = None
                                        print("\nfish is eaten by Bear")
                                        Fish.speak(self)
                        if randomMove == 1:
                            print("\tAnimal Choose to 1")
                            print("\t   Stay")
                    else:
                        break
                        #if randomMove == 1:
                         #   print("\tAnimal Choose to 1")
                          #  print("\t   Stay")
            
            #self.simulate(i)
                
                    
    def create_new_animal(self, a):
        """Optional Method
        You can optionally use this method to get the animal's class name
        before you use the method animal_creation()
        """
        # Remove pass and Write your code here
        aName = a
        print(aName.get_class_name())
        pass