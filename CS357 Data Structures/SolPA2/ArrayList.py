"""
    Class for a Array-list based data structure
"""

class Array:
    def __init__(self, capacity=10):    # You cannot change this line
        """Constructor for the class. It initializes the array with a list of 
        None elements.
        The size of the initial array is provided by the user
        It also initializes the length of the array. 
        Parameters
        ----------
        capacity : int
            The initial capacity for the array
        """
        self.array = [None] * capacity
        self.allocation_size = capacity
        self.length = 0
    
    def isEmpty(self):
        """ Method to check if the array is empty
            The method returns True or False if the array is empty
        """
        return self.length == 0;
    
    
    def append(self, new_item): # You cannot change this line
        """ Method to append a new item at the end of the array
        The metod receives a new item and append it to the array
        The method needs to check if the array is full and call the resize() method
        The new size is the double of the current size.
        Parameters
        ----------
        new_item: int
            The new item to be appended
        """
        # resize() if the array is full
        if self.allocation_size == self.length:
            self.resize(self.length * 2)

        # Insert the new item at index equal to self.length
        self.array[self.length] = new_item

        # Increment the array's length
        self.length = self.length + 1
        
    def resize(self, new_allocation_size):  # You cannot change this line
        """ Method to resize
        This method performs the resizing of the array
        Parameters
        ----------
        new_allocation_size: int
            The new size for the array
        """
        # Create a new array with the indicated size
        print("\t Resizing...")
        new_array = [None] * new_allocation_size

        # Copy items in current array into the new array
        for i in range(self.length):
            new_array[i] = self.array[i]

        # Assign the array data member with the new array
        self.array = new_array

        # Update the allocation size
        self.allocation_size = new_allocation_size
        
        
    def prepend(self, new_item):    # You cannot change this line
        """ Method to prepend a new item at the beginning of the array
        The metod receives a new item and prepend it to the array
        The method needs to check if the array is full and call the resize() method
        The new size is the double of the current size.
        Parameters
        ----------
        new_item: int
            The new item to be prepended
        """
        # resize() if the array is full
        if self.allocation_size == self.length:
            self.resize(self.length * 2)
     
        # Shift all array items to the right,
        # starting from the last index and moving 
        # down to the first index.
        for i in reversed(range(1, self.length+1)):
            self.array[i] = self.array[i-1]
           
        # Insert the new item at index 0
        self.array[0] = new_item
        
        # Update the array's length
        self.length = self.length + 1
                
    def insert_after(self, index, new_item):    # You cannot change this line
        """ Method to insert a new item after a particular element from the array
        The metod receives a new item and insert it after another element
        The method needs to check if the array is full and call the resize() method
        The new size is the double of the current size.
        Parameters
        ----------
        index: int
            The existing element's position
        new_item: int
            The new item to be appended
        """
        # resize() if the array is full
        if self.allocation_size == self.length:
            self.resize(self.length * 2)

        # Shift all the array items to the right,
        # starting from the last item and moving down to
        # the item just past the given index.
        for i in reversed(range(index+1, self.length+1)):
            self.array[i] = self.array[i-1]
            
        # Insert the new item at the index just past the
        # given index.
        self.array[index+1] = new_item
        
        # Update the array's length
        self.length = self.length + 1
        

    def search(self, item): # You cannot change this line
        """ Method to search for an element in the array
        The method returns the index of the first element that matches the value
        The method returns -1 if the item is not in the list
        Parameters
        ----------
        item: int
            The item to be searched
        """
        # Iterate through the entire array
        for i in range(self.length):
            # If the current item matches the search
            # item, return the current index immediately.
            if self.array[i] == item:
                return i
                
        # If the above loop finishes without returning,
        # it means the search item was not found.
        return -1
        
    def remove_at(self, index): # You cannot change this line
        """ Method to remove an item at a specific index from the array
        The metod receives an index and removes that element from the array
        The method needs to check if the array is empty and it shows 
        "The array is empty" message if it is empty and returns -1
        The method needs to check if the index is in the correct range
        The method needs to resize the array if the length has fall below 25%
        The new size is the rounded to 25% of the current size.
        Parameters
        ----------
        index: int
            The index of the element to remove
        """
        # Check if the array is empty
        # if self.length == 0:
        if self.isEmpty():
            print("The array is empty.")
            return -1
        # Make sure the index is valid for the current array
        if index >= 0 and index < self.length:
            # Shift items from the given index up to the
            # end of the array.
            # for i in range(index, self.length-1):
            for i in range(index, self.length):
                self.array[i] = self.array[i+1]

            # Update the array's length
            self.length = self.length - 1
            
            # check if we need to resize (0.25)
            # check if we need to resize (0.25)
            if (self.length/self.allocation_size) <= 0.25:
                self.resize(round(self.length * 0.25))

    def remove(self):   # You cannot change this line
        """ Method to remove an item from the end of the array
        The method needs to check if the array is empty and it shows 
        "The array is empty" message if it is empty and returns -1
        The method needs to resize the array if the length has fall below 25%
        The new size is the rounded to 25% of the current size.
        """
        # if self.length == 0:
        if self.isEmpty():
            print("The array is empty.")
            return -1
        # put the last element to None
        self.array[self.length - 1] = None
        self.length -= 1
        
        # check if we need to resize (0.25), Just round it
        if (self.length/self.allocation_size) <= 0.25:
            self.resize(round(self.allocation_size * 0.25))
        
    def __getitem__(self, index):
        """Method to get an element from a given index
            The method checks if the index is valid
            and returns the value
            Otherwise, just ends.
        """
        if index >=0 and index < len(self.array):
            return self.array[index]
        
    
    def __setitem__(self, index, item):
        """Method to change the value of a given index
            The method checks if the index is valid
            if it is valid it makes the change
            Otherwise, just ends
        """
        if index >= 0 and index < len(self.array):
            self.array[index] = item
        
    def __repr__(self): # You cannot change this line
        """ Method to show the content of the array
        This method returns a String
        """
        s = "["        
        for i in range(self.length):
        # for i in range(self.allocation_size):
           s+= str(self.array[i]) + " "
        
        s += "]"
        return s

    def __len__(self): # You cannot change this line
        """ Method to return the length of the array
        """
        return self.length
