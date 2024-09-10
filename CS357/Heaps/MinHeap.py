"""
    Class to implement MinHeap
    using an array
"""

class Heap:
    def __init__(self):
        self.heap = []
        self.size  = 0

    def __len__(self):
        return self.size
    
    def __str__(self):
        return " ".join([str(x) for x in self.heap])
    
    def insert(self, value):
        self.heap.append(value) # Step 1
        self.moveUp(len(self.heap)-1) #  Step 2 moveUp
        self.size += 1
        
    def moveUp(self, index):
        parent_index = (index-1)//2
        if parent_index < 0: # if this node is the root node
            return
        
        if self.heap[parent_index] > self.heap[index]:
            self.heap[parent_index], self.heap[index] = self.heap[index], self.heap[parent_index]
            self.moveUp(parent_index)
    
    def sink(self, index):
        left_child_index = 2*index+1
        right_child_index = 2*index+2
        min_index = index

        if left_child_index < len(self.heap) and self.heap[left_child_index]< self.heap[min_index]:
            min_index = left_child_index
        
        if right_child_index < len(self.heap) and self.heap[right_child_index]< self.heap[min_index]:
            min_index = right_child_index

        if min_index != index:
            self.heap[index], self.heap[min_index] = self.heap[min_index], self.heap[index]
            self.sink(min_index)


    def pop(self):
        if not self.heap:
            return None
        
        self.heap[0], self.heap[-1] = self.heap[-1], self.heap[0]
        min_value = self.heap.pop()
        self.sink(0)
        self.size -= 1

        return min_value