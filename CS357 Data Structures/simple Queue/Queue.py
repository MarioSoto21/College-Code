"""
Queue not using a fixed capacity
It has a moving pointer for the front element
"""
class Queue:
    def __init__(self):
        self.items = []
        self.front = 0
        self.size = 0

    def __len__(self):
        return self.size
    
    def isEmpty(self):
        return self.size == 0
    
    def enqueue(self, value):
        self.items.append(value)
        self.size += 1

    def dequeue(self):
        if self.isEmpty():
            print("Queue is empty")
        else:
            temp = self.items[self.front]
            self.items[self.front] = None
            self.size -= 1
            self.front += 1
            return temp
        
    def peek(self):
        if self.isEmpty():
            print("Queue is empty")
        else:
            return self. items[self.front]
    
    def __repr__(self):
        values = [str(x) for x in self.items]
        return "[" + ' '.join(values) +"]"