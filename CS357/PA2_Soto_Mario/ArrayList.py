"""
Mario Soto
"""
class ArrayList:
    def __init__(self, capacity=10): #O(1)
        self.capacity = capacity;
        self.length = 0;
        self.array = [None]*capacity;
    
    def isEmpty(self): #O(1)
        return self.length == 0
    
    def append(self, new_item): #O(1)
        if self.length == self.capacity: #full
            self.resize(10)
        self.array[self.length] = new_item
        self.length += 1
    
    def resize(self, new_allocation_size): #O(n)
        a = new_allocation_size* self.length
        new_array = [None]* a
        for i in range(self.length):
            new_array[i] = self.array[i]
        self.array = new_array
        print("      Resizing...")

    def prepend(self, new_item): #O(n)
        if self.length == self.capacity:
            self.resize(len(self.array)*2)
        i = self.length
        while 0 <= i-1:
            self.array[i] = self.array[i-1]
            i = i-1
        self.array[0] = new_item
        self.length += 1

    def insert_after(self, index, new_item): #O(n)
        if self.length == self.capacity:
            self.resize(15)
        i = self.length
        while index <= i-1:
            if self.length == self.capacity:
                self.array[i] = self.array[i-1]
            else:
                self.resize(15)
            i = i-1
        self.array[index+1] = new_item
        self.length += 1
                

    def search(self, item): # O(n)
        for i in range(self.length):
            if self.array[i] == item:
                return i
        return -1
    
    def remove_at(self,index): #O(n)
        if self.isEmpty():
            print("The array is empty.")
            return
        i = self.length
        a = self.capacity*.25
        if self.length < a:
            self.resize(.25)
        else:
            while index <= i-1:
                self.array[i] = self.array[i-1]
                i = i-1
        self.array[index] = None
        self.length-=1

    def remove(self): #O(1)
        if self.isEmpty():
            print("The array is empty")
            return
        self.array[self.length-1] = None
        self.length-=1
    
    def __getitem__(self, index): #O(1)
        if self.length < index:
            return
        if self.array[index] != None:
            return index
        return
    
    def __setitem__(self, index, item): #O(1)
        if self.length < index:
            return
        elif self.array[index] != None:
            self.array[index] = item
        return


    def __repr__(self): #O(n)
        S = "["
        for i in range(self.length):
            S += str(self.array[i]) + " "
        S += "]"
        return S

    def __len__(self): #O(1)
        return self.length