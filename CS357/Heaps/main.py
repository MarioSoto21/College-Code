
from MinHeap import Heap

if __name__ == "__main__":
    h = Heap()
    print("Size", len(h))

    h.insert(6)
    h.insert(3)
    h.insert(5)
    print("Size", len(h))
    print(h)

    h.insert(2)
    h.insert(7)
    h.insert(9)
    h.insert(10)
    h.insert(1)
    print("Size", len(h))
    print(h)

    ####
    print("Removing...", h.pop())
    print(h)
    print("Removing...", h.pop())
    print(h)
    print("Size", len(h))