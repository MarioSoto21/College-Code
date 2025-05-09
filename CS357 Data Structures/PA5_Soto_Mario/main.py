from HashTable import HashTable

if __name__ == "__main__":
    h = HashTable()
    print("Number of items in the HashTable", len(h))
    print(h.isEmpty())
    print(h)
    print("~"*20)
    h.put(77, "Hello")
    h.put(8, "Bye")
    h.put(17, "World")
    print("Number of items in the HashTable", len(h))
    print(h.isEmpty())
    print(h)
    print("#"*20)
    print(h.search(77))
    print(h.search(88))
    h.put(17, "Bye")
    print(h)
    print("*"*20)
    h.remove(17)
    print("Number of items in the HashTable", len(h))
    print(h)
    print("^"*20)
    h.remove(32)
    print("@"*20)
    h.put(8, 'Good Bye')
    print(h)
    print("Number of items in the HashTable", len(h))