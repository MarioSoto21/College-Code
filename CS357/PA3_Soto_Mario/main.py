"""
Tester for Simply Linked list
"""
from Node import Node
from DLinkedList import DLinkedList

if __name__ == "__main__":
    node_a = Node(1)
    node_b = Node(2)
    node_c = Node(3)
    node_d = Node(4)
    node_e = Node(5)
    node_f = Node(6)
    node_g = Node(7)

    num_list = DLinkedList()
    print(num_list)         # []
    print(num_list.isEmpty())   # True
    print(len(num_list))    # 0
    num_list.append(node_a)
    num_list.append(node_b)
    num_list.append(node_c)
    print(len(num_list))    #3
    print(num_list)         # [1 2 3]

    num_list.prepend(node_d)
    print(num_list)         # [4 1 2 3]
    print(len(num_list))    # 4

    num_list.insert_after(node_a, node_e)
    print(num_list)         # [4 1 5 2 3]
    print(len(num_list))    # 5
    num_list.remove(node_c)
    print(len(num_list))
    print(num_list)         # [4 1 5 2]

    # num_list.reverse() 
    # print(num_list)         # [2 5 1 4]

    num_list.insert_before(node_e, node_f)
    print(num_list)       # [2 6 5 1 4]
    print(len(num_list))  # 5

    print(num_list.search(5))   # 2
    print(num_list.search(10))  # -1

    num_list.remove_before(node_d)
    print(num_list)       # [2 6 5 4]
    print(len(num_list))  # 4

    num_list.remove_after(node_f)
    print(num_list)       # [2 6 4]
    print(len(num_list))  # 3