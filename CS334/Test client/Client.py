class Client:
    """
        Class for Clients
    """
    name = "default"
    phone = "(575)0123-456"
    purchases = 0
    
class Vendor:
    name = "Vendor"
    
#### Start testing it
if __name__ == "__main__":

    client1 = Client()
    client1.name = "Jane Doe"

    client2 = Client()
    client2.name = "John Doe"
    client2.phone = "(575)708-453"
    client2.purchases = 3
    
    print(client1.name)
    print(client1.phone)
    
    print(client2.name)
    print(client2.purchases)