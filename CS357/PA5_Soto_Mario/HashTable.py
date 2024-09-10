"""
Mario Soto
"""
class HashTable:

    def __init__(self, buckets = 10): # O(1)
        """The constructor for this hashtable.
        It initializes:
        the amount of buckets (buckets)
        the table to be used as hashtable.
            You can use the builtin Python list to create a list of lists
        the initiial size of the hashtable
        """
        self.capacity = buckets
        self.table = [[]for _ in range(buckets)]
        self.size = 0

    def hashFunction(self, key): # O(1)
        """Returns the postion in fro the element in the hash table.
        This hash function has two parts.
        1) Uses the builtin Python hash function and absolute function to 
        compute an initial Key
        2) Reduces the initial key to a position in the hash table using the 
        % operator and the number of buckets
        """
        return abs(key)%self.capacity
    
    def put(self, key, value): # O(n)
        """ Method to insert and uptdate the value of a key
        If the key exists, it updates its value
        Else, it inserts the key-value pair in its correspoding bucket and
        updates the size"""
        index = self.hashFunction(key)
        # Searches bucket
        for i in range(len(self.table[index])):
            k,v = self.table[index][i]
            # If key is found change the value
            if k == key :
                self.table[index][i] = (key, value)
                return
        # If not append the tuple
        self.table[index].append(tuple([key, value]))
        self.size += 1

    def get(self, key):
        """Method that returns the value for a given key
        """
        index = self.hashFunction(key)
        for i in range(len(self.table[index])):
            k,v = self.table[index][i]

            if k == key :
                print("(",k ,",", v,")")

    def search(self, key): # O(n)
        """Method to search for an item. It receives the key for that item
            It returns True if the item exists in the table.
            Otherwise, it returns None
        """
        index = self.hashFunction(key)
        #Searches through bucket
        for list in self.table[index]:
            # If key is found
            if key in list:
                return True
            # If key is not found
            else:
                return None

    def remove(self, key): # O(n)
        """ Method that removes an item from the hash table
            The method removes the item from the inner list
            The method uses the list's builtin method remove() to remove
            the (k, v) item
            The method updates the size of the hash table
            If the key does not exist the method shows a message
        """
        index = self.hashFunction(key)
        found = False
        # Checks if key is in the bucket
        for i in range(len(self.table[index])):
            k,v = self.table[index][i]
            if k == key :
                found = True
                break
        # If key is found
        if found == True:
            print("Removing ", key," from bucket ", index)
            self.table[index].pop(i)
            # self.table[index].remove(key)
            self.size -= 1
            return
        # If key is not found
        print(key,"does not exist")

    def __len__(self): # O(1)
        """Returns the number of elements in the hashtable
        """
        return self.size
    
    def isEmpty(self): # O(1)
        """Returns True or False whether the table is empty or not
        """
        return self.size == 0

    def __str__(self): # O(n)
        """Returns a string with the content of the hash table
            It needs to go through each bucket and through each of the inner lists
            to get eaach tuple(i.e., (k, v) pair)
            If the table is empty it returns "Hash Table is Empty"
            Otherwise, it retruns the content of each bucket
        """
        s = ""
        if self.size == 0:
            s +="Hash Table is Empty"
            return s
        for index, element in enumerate(self.table):
            if element is not None:
                s += f"{index}"
                for j in self.table[index]:
                    t = str(j)
                    s+= "-->"+" "
                    s+= t + " " 
                s+= "\n"
        return s