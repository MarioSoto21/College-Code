from HashTable import HashTable
if __name__ == "__main__":
    ht = HashTable()
    ht.insert(55) # 55%10 =5
    ht.insert(78) # 78%10 = 8
    ht.insert(75)
    ht.insert(19)
    ht.insert(20)
    print(f"Size: ",len(ht))
    ht.remove(78)
    print(f"Size: ",len(ht))

    print(ht.search(75))

    print(ht.search(19))

    print(ht)