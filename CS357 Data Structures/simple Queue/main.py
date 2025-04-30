from Queue import Queue

if __name__== "__main__":
    q = Queue()
    print(len(q))
    print(q)
    print(q.isEmpty())

    q.enqueue(1)
    q.enqueue(2)
    q.enqueue(3)
    print(len(q))
    print(q)
    print(q.front)

    print(q.dequeue())
    print(q.front)
    print(q)

    q.enqueue(4)
    q.enqueue(5)
    q.enqueue(6)
    print(q)

    q.dequeue()
    q.dequeue()
    q.dequeue()
    print(len(q))
    print(q.front)
    print(q)

    print(q.peek())