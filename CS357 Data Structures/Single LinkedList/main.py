from Node import Node
from LinkedList import LinkedList
h = LinkedList()
h.append(1)
h.append(2)
print(h)
print(len(h))

h.insert_after(2, 4)
print(h)
h.insert_after(2, 3)
h.append(5)
print(h)
h. insert_after(2, 2.5)
print(h)
print(h.search(2.5))
print(h.search(10))