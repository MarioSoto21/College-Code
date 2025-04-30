<%@ page import="java.util.*, controllers.*, databases.*, objects.*, java.io.*" contentType="text/html;charset=UTF-8" language="java" %>
<%
    Controller mainController = Controller.getInstance();
    LoginController loginController = (LoginController)mainController.getLoginController();
    CustomerController customerController = (CustomerController)mainController.getCustomerController();
    RoomController roomController = (RoomController)mainController.getRoomController();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.75">
    <link rel="stylesheet" type="text/css" href="./style.css">
    <title>DB Test Page Best Eastern</title>
</head>
<body>
    <header class="header">
        <div class="menu-container">
            <div class="name-container">
                Best Eastern
            </div>
            <div class="dropdown">
                <button class="dropbtn">Menu</button>
                <div class="dropdown-content">
                    <a href="./home.jsp">Home</a>
                    <a href="./rooms.jsp">Rooms</a>
                    <a href="./cart.jsp">Cart</a>
                    <a href="./account.jsp">Account</a>
                    <a href="./login.jsp">Login</a>
                </div>
            </div>
            <div class="logo-container">
                <div class="logo">
                    <a href="https://www.enmu.edu/">
                        <img src="https://enmucs.com/calebparten/1280px-Eastern_New_Mexico_University_logo.svg.png" alt="ENMU LOGO">
                    </a>
                </div>
            </div>
        </div>
    </header>
    <div class="container">
        <div class="box">
            <% 
                //Print out all of the Logins
                List logins = loginController.getLoginDatabase().getLogins();
                out.println("<h1>LOGINS</h1>");
                out.println("<p> UserID , Username , Password </p>");
                for(int i = 0; i < logins.size();i++){
                    Login currentLogin = (Login)logins.get(i);
                    out.println("<p>"+ currentLogin.getUserID() + ", " + currentLogin.getUsername()+ ", " + currentLogin.getPassword() +  "</p>");
                }

            %>
        </div>
        <div class="box">
            <% 
                //Print out all of the Customers
                List customers = customerController.getAllCustomers();
                out.println("<h1>CUSTOMERS</h1>");
                for(int i = 0; i < customers.size();i++){
                    Customer currentCustomer = (Customer)customers.get(i);
                    int userID = currentCustomer.getUserID();
                    String name = currentCustomer.getName();
                    String email = currentCustomer.getEmail();
                    Card card = currentCustomer.getCardData();
                    int numOfPeople = currentCustomer.getNumberPeople();
                    boolean veteranStatus = currentCustomer.isVeteran();
                    List cartItems = currentCustomer.getCartData().getItems();
                    out.println("<p>"+ userID + ", " + name + ", " + email +  ", " + cartItems +"</p>");
                }

            %>
        </div>
        <div class="box">
            <% 
                //Print out all of the Rooms
                List rooms = roomController.getAllRooms();
                out.println("<h1>ROOMS</h1>");
                out.println("<p> Customer ID , Room Number , Room Size, Is Cleaned?, Pets Allowed?, Room Price</p>");
                for(int i = 0; i < rooms.size();i++){
                    Room currentRoom = (Room)rooms.get(i);
                    int custID = currentRoom.getCustomerId();
                    int roomNum = currentRoom.getRoomNumber();
                    int roomSize = currentRoom.getRoomSize();
                    boolean isClean = currentRoom.isCleaned();
                    boolean pet = currentRoom.isPetAllowed();
                    int roomType = currentRoom.getRoomPrice();
                    out.println("<p>"+ custID + ", " + roomNum+ ", " + roomSize + ", " + isClean+ ", " + pet +", " + roomType  +"</p>");
                }

            %>
        </div>
    </div>
</body>
</html>







