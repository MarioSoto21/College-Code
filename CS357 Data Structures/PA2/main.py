'''
Basic - low level - Array list
'''
from ArrayList import ArrayList
if __name__ == "__main__":
    my_array = ArrayList(2);
    print(my_array)
    print(my_array.isEmpty())
    my_array.remove()
    my_array.append(1)
    my_array.append(2)
    my_array.append(3)
    my_array.append(4)
    print(my_array)
    print(my_array.length)
    print(my_array[5])
    print(my_array[2])
    my_array[8] = 2.5
    my_array[2] = 2.5
    print(my_array)
    my_array.append(5)

    print(my_array.search(3))

    my_array.remove()
    print(my_array)
    print(my_array.length)
    #----
    my_array.prepend(0)
    my_array.prepend(-1)
    my_array.prepend(-2)
    my_array.prepend(-3)
    my_array.prepend(-4)
    print(my_array)
    #----
    my_array.insert_after(0, -3.5)
    my_array.insert_after(4, -0.5)
    my_array.insert_after(8, 2.5)
    print(my_array)
    #----
    my_array.remove_at(3)
    print(my_array)
    #----
    my_array.remove()
    my_array.remove()
    my_array.remove()
    my_array.remove()
    my_array.remove()
    my_array.remove()
    my_array.remove()
    my_array.remove()
    my_array.remove()
    print(my_array)
    print(len(my_array))