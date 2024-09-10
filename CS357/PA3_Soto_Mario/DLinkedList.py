"""
    Mario Soto
"""
from Node import Node

class DLinkedList:

    def __init__(self): #O(1)
        """Consturctor for the class. It initializes the head, tail, and size"""
        self.head = None
        self.tail = None
        self.size = 0
    
    def __len__(self): # O(1)
        """Method to return the length of the list"""
        return self.size
    
    def isEmpty(self): # O(1)
        """Methof to check if the list is empyt.
            Return true of false"""
        return self.size == 0
    
    def reverse(self): 
        "Mehtod to reverse the order of the list"
        prev = None
        current = self.head
        while(current != None):
            next = current.next
            current.next = prev
            prev = current
            current = next
        self.head = prev

    def append(self, new_node): # O(1)
        """Method to append a new node to the end of the List"""
        if self.head == None:
            self.head = new_node
            self.tail = new_node
        else:
            self.tail.next = new_node
            self.tail = new_node
        self.size += 1

    def prepend(self, new_node): # O(1)
        """Method to append a new node to the front of the list"""
        new_node = new_node
        if self.head == None:
            self.head = new_node
            self.tail = new_node
        else:
            new_node.next = self.head
            new_node.prev = None
            if self.head != None:
                self.head.prev = new_node
            self.head = new_node
        self.size +=1
    
    def insert_after(self, current_node, new_node): # O(1)
        """Method to insert node after another node"""
        #Case 1 if list is empty
        if self.head == None:
            return
        #Case 2 if current node is tail
        elif self.tail.data == current_node:
            self.tail.next = new_node
            self.tail = new_node
        #Case 3
        else:
            new_node.next = current_node.next
            current_node.next = new_node
            new_node.prev = current_node
            if new_node.next:
                new_node.next.prev = new_node
        self.size += 1

    def insert_before(self, current_node, new_node): #O(1)
        """Method to insert node before another node"""
        #Case 1 if list is empty
        if current_node is None:
            return
        #Case 2 if current node is head
        if current_node == self.head:
            self.prepend(new_node)
        #Case 3
        else:
            new_node.prev = current_node.prev
            current_node.prev.next = new_node
            current_node.prev = new_node
            new_node.next = current_node
        self.size += 1
        
    def remove(self, current_node): # O(1)
        """Method to remove a given node from the list"""
        if self.head == None:
            return
        else:
            current_node = self.head
            while current_node.next.next != None:
                current_node = current_node.next
            self.tail = current_node
            current_node.next = None
        self.size -= 1

    def remove_before(self, current_node): #O(1)
        """Method to remove a node before a given node"""
        if self.head == current_node:
            return
        if current_node.prev == self.head:
            self.head = current_node
        else:
            temp = current_node = self.head
            while current_node.prev.prev != None:
                temp.next = current_node

            
        self.size -= 1

    def remove_after(self, current_node): # O(1)
        """Method to remove a node after a given node"""
        if self.tail == current_node:
            return
        else:
            prev = current_node.prev
            current_node.prev = prev.prev
            prev.prev.next = current_node
        self.size -= 1

    def search(self, value): # O(n)
        """Method to search for a node with a particular value"""
        pos = -1
        current_node = self.head
        while current_node != None:
            pos +=1
            if current_node.data == value:
                return (pos)
            current_node = current_node.next
        return -1
    
    
    def __repr__(self): # O(n)
        """Method to show the content of the list"""
        s = "["
        node = self.head
        while node != None:
            s += str(node.data) + " "
            node = node.next
        s += "]"
        return s