from Animal import Animal
from Bear import Bear
from Fish import Fish
from Simulation import Ecosystem



if __name__ == "__main__":
    print("\nInitializing Ecosystem")
    e = Ecosystem(10)
    print("\nEcosystem BEFORE simulation")
    e.show()
    e.simulate(1)
    print("\nEcosystem AFTER simulation")
    e.show()