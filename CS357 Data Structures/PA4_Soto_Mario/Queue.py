"""
Mario Soto
"""
class Queue:
    def __init__(self, capacity = 10): # O(1)
        """Constructor for the class. It initializes the queue with a list of 
        None elements.
        The capacity of the initial queue is providide by the user
        It also initializes the size and the front of the queue
        """
        self.items = [None] *capacity
        self.front = 0
        self.capacity = capacity
        self.size = 0

    def __len__(self): # O(1)
        """ Method to return the length of the queue
        """
        return self.size
    
    def __repr__(self): # O(n)
        """Method to show the content of the array
        (including the None elements for test purposes)
        This method returns a String
        """
        values = [str(x) for x in self.items]
        return "[" + ' '.join(values) +"]"
        
    def isEmpty(self): # O(1)
        """ Mehtod to check if teh queue is empty
            The method returns True or False if the queue is empty
        """
        return self.size == 0
    
    def peek(self): # O(1)
        """ Method to return the element at the front of the queue
            The Method returns None if the queue is empty
            This method does not remove the element from the queue
        """
        # If queue is empty
        if self.isEmpty():
            print("Queue is empty")
        # Gives the front of the queue
        else:
            return self.items[self.front]

    def dequeue(self): # O(n)
        """ Method to remove and return the logical front element from the queue
            if the queue is empty it prints a message "Empty"
            otherwise, it returns the element that is at the FRONT of the queue
            The method needs to check if the size of the que is below 1/4 of 
            its capacity and calls the resize operation. The new size is 1/4 of
            the current capacity
            the method changes the size of the queue and the front of the queue
            """
        # Checks if queue is empty
        if self.isEmpty():
            print("Empty")
        else:
            temp = self.items[self.front]
            self.items[self.front] = None
            self.front = (self.front + 1) % len(self.items)
            self.size -= 1
            print("Removing. New Front: "+str(self.front))
            # Resizes Queue
            cap = len(self.items)
            if 0 < self.size <= cap//4 :
                self. resize(cap//4)
            return temp
    
    def enqueue(self, item): # O(n)
        """ Method to add an item at the logical end of the queue
            if the queue is full it needs to be resized by the double of its
            current capacity
            the method needs to compute the next availbale index in the queue
        """
        # Resize the queue if full
        if self.size == len(self.items):
            self.resize(2 * len(self.items))
        # Adds item at end of queue
        avail = (self.front + self.size) % len(self.items)
        self.items[avail] = item
        self.size += 1
        print("Inserting at index: " +str(avail))
        
    
    def resize(self, new_cap): # O(n)
        """ Method to change the capacity of the queue 
            The method needs to move the logical positions of the items
            in the old queue to move the logical positions of the items 
            in the old que to their corresponding positons in the new
            queue
        """
        print("\t       Resizing...")
        old = self.items
        # Creates resized queue
        self.items = [None]*new_cap
        front = self.front
        # Copies old queue to new queue
        for i in range(self.size):
            self.items[i] = old[front]
            front = (1 + front) % len(old)
        self.front = 0