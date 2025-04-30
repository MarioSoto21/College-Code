"""
    Really Basic Stack impementation
    to solve the problem of checking if
    the statement has a balanced numbear of brackets
"""

class Stack:
    
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def push(self,item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()
    
    def peek(self):
        return self.items[-1]
    
    def __len__(self):
        return len(self.items)
    
if __name__ == "__main__":

    def isBalanced(statement):
        stack = Stack()
        for bracket in statement:
            if bracket in "{([":    # if its one of this, then push it
                stack.push(bracket)
            elif bracket in "})]":
                if stack.isEmpty(): # The closing bracket is extra
                    return False
                else:
                    if (bracket == ")" and stack.peek()== "(" or \
                        bracket == "}" and stack.peek()=="{" or \
                        bracket == "]" and stack.peek()=="["):
                            stack.pop()
                    else:
                        return False
        return stack.isEmpty()
                    

    statement1 = "{(x)(y)}[z](((a)b))"
    statement2 = "{(x)(y)}[z](((z)b)c))"
    print(isBalanced(statement2))