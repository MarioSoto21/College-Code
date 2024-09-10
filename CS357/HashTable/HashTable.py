
class HashTable:

    def __init__(self, capacity = 10):
        self.capacity = capacity
        self.table = [None] * capacity
        self.size = 0

    def __len__(self):
        return self.size
    
    def hashFunction(self, value):
        return abs(value)%self.capacity
    
    def insert(self, value):
        print(f"Insert value {value} in bucket {self.hashFunction(value)}")
        key = self.hashFunction(value)
        if self.table[key] != None:
            print("\tWe have a collision!")
            return
        else:
            self.table[key] = value
            self.size += 1

    def remove(self, value):
        key = self.hashFunction(value)
        print(f"Remove value {value} in bucket {key}")
        self.table[key] = None
        self.size -= 1

    def search(self, value):
        key = self.hashFunction(value)
        print(f"Searching for value {value} in bucket {key}")
        if self.table[key] == value:
            return True
        else:
            return False
    def __repr__(self):
        s = ""
        for i, element in enumerate(self.table):
            s += f"[{i}]->{element}\n"
        return s