<%@ page import="java.util.*, controllers.*, objects.*, java.io.*" contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Login Best Eastern</title>
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
    <form method="post">
        <div class="container">
            <div class="box">
                <h2>Create</h2>
                <form:form>
                    <%-- <input type="text" placeholder="First name" name="firstname" required><br>
                    <input type="text" placeholder="Last name" name="lastname" required><br> --%>
                    <input type="text" placeholder="name" name="name" required><br>
                    <input type="text" placeholder="email" name="email" required><br>
                    <input type="text" placeholder="age" name="age" required><br>
                    <input type="password" placeholder="Password" name="password" required><br><br>
                    <input type="submit" value="Create"><br><br>
                </form:form>
                <%
                    if(request.getMethod().equals("POST")){
                        String name = request.getParameter("name");
                        String email = request.getParameter("email");
                        String pass = request.getParameter("password");
                        int age = Integer.parseInt(request.getParameter("age"));
                        List<Integer> empty = new ArrayList<>();
                        Card card = null;
                        Cart cart = new Cart(empty);
                        int newUserID = customerController.getNextAvailableUserId();

                        Customer newCustomer = new Customer(newUserID,name,email,card,10,age,false,cart);
                        customerController.addCustomer(newCustomer);

                        // 5 WILL BE CHANGED TO METHOD getNextAvailableId()
                        Login newLogin = new Login(newUserID,email,pass);
                        loginController.addLogin(newLogin);
                        out.println("<div class=\"box\" style=\"background-color: #444;\"><h1><a href=\"./login.jsp\">Go to Login</a></h1>"); 

                    }
                %>
            </div>
        </div>
    </form>
</body>
</html>