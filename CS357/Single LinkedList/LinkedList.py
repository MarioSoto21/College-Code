from Node import Node    
class LinkedList:

    def __init__(self): #O(1)
        """ Constructor for the class. It intializes the head, tail, and size
        """
        self.head = None
        self.tail = None
        self.size = 0
    
    def __len__(self): # O(1)
        """ Method to return the length of the list
        """
        return self.size
    
    def append(self, new_node): # O(1)
        """ Method to append a new node to the end of the list 
        The method receives a node object
        The method updates the size of the list
        """
        new_node = Node(new_node)
        if self.head == None:
            self.head = new_node
            self.tail = new_node
        else:
            self.tail.next = new_node
            self.tail = new_node
        self.size += 1

    def insert_after(self, current_item, new_item):
        #Case 1
        new_node = Node(new_item)
        if self.head == None:
            self.append(new_item)
        elif self.tail.data == current_item:
            self.tail.next = new_node
            self.tail = new_node
        else:
            current_node = self.head
            while current_node.next !=None:
                if current_node.data == current_item:
                    new_node.next = current_node.next
                    current_node.next = new_node
                current_node = current_node.next
        self.size += 1
    def remove(self, current_node):
        """ Method to remove a given node from the list
        The method updates the size of the list
        """
        if self.head == None:
            return
        else:
            current_node = self.head
            while current_node.next.next != None:
                current_node = current_node.next
            self.tail = current_node
            current_node.next = None
        self.size -= 1
    def search(self, value): # O(n)
        """ Method to search for a node with a particular value 
        The method returns the postion of the node
        Otherwise, it returns -1
        """
        pos = 0
        current_node = self.head
        while current_node != None:
            pos +=1
            if current_node.data == value:
                return (pos)
            current_node = current_node.next
        return -1
    
    
    def __repr__(self): # O(n)
        """ Method to show the content of the list
        This method returnns a list
        """
        s = "["
        node = self.head
        while node != None:
            s += str(node.data) + " "
            node = node.next
        s += "]"
        return s
