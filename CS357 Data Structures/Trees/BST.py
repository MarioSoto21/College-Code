
from Node import Node

class BST:

    def __init__(self):
        self.root = None

    def insert(self, node):
        if self.root is None:
            self.root = node
        else:
            current_node = self.root
            while current_node is not None:
                if node.value < current_node.value:
                    if current_node.left is None:
                        current_node.left = node
                        current_node = None
                    else: 
                        current_node = current_node.left
                else:
                    if current_node.right is None:
                        current_node.right = node
                        current_node = None
                    else:
                        current_node = current_node.right
    def search(self, value):
        current_node = self.root
        while current_node is not None:
            if current_node.value == value:
                return current_node.value
            elif value < current_node.value:
                current_node = current_node.left
            else:
                current_node = current_node.right
                
        return None
    
    def remove(self, value):
        parent = None
        current_node = self.root

        while current_node is not None:
            if current_node.value == value:
                # Case1: No children
                if current_node.left is None and current_node.right is None:
                    if parent is None: # root node
                        self.root = None
                    elif parent.left is current_node:
                        parent.left is None
                    else:
                        parent.right = None
                    return
                # Case 2.1 - Only left child
                elif current_node.left is not None and current_node.right is None:
                    if parent is None: # root
                        self.root = current_node.left
                    elif parent.left is current_node:
                        parent.left = current_node.left
                    else:
                        parent.right = current_node.right
                    return
                # Case 2.2 - Only one right child
                elif current_node.left is None and current_node.rihgt is not None:
                    if parent is None: # root
                        self.rott = current_node.right
                    elif parent.left is current_node:
                        parent.left = current_node.right
                    else:
                        parent.right = current_node.right
                    return
                
                # Case - Two Children
                else:
                    successor = current_node.right
                    while successor.left is not None:
                        successor = successor.left

                    # copy the successor to current node
                    current_node.value = successor.value
                    parent = current_node
                    # Remove successor form right subtree
                    current_node = current_node.right
                    # Loop continues with new value
                    value = parent.value

            # continue search to the right
            elif current_node.value < value:
                parent = current_node
                current_node = current_node.right
            
            # continue search to the left
            else:
                parent = current_node
                current_node = current_node.left

        return
    def __repr__(self):
        values = []
        def traverse_in_order(node):
            if node is not None:
                traverse_in_order(node.left)
                values.append(str(node.value))
                traverse_in_order(node.right)

        traverse_in_order(self.root)
        return '[' + ', '.join(values)+']'