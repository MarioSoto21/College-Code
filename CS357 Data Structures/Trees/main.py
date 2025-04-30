"""
Binary Search Tree (BST)
"""
from BST import BST
from Node import Node

if __name__ == "__main__":
    tree = BST()
    n1 = Node(300)
    n2 = Node(100)
    n3 = Node(900)
    n4 = Node(25)
    n5 = Node(145)
    n6 = Node(750)
    n7 = Node(925)
    
    tree.insert(n1)
    tree.insert(n2)
    tree.insert(n3)
    tree.insert(n4)
    tree.insert(n5)
    tree.insert(n6)
    tree.insert(n7)

    # print(tree.search(750))

    # Remove case 1
    #tree. remove(925)

    # Remove case 2.1
    #tree.remove(900)

    # Remove case 2.2
    # tree.remove(750) # just to make the parent with 1 child
    # tree.remove(900)

    # Remove case 3
    tree.remove(100)

    print(tree.search(100))

    print(tree)