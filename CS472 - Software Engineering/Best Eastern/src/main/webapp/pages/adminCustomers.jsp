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
    <title>Customers Database</title>
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
        <% 
            int currentUserID = 999;
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (Cookie cookie: cookies){
                    if(cookie.getName().equals("userID")){
                        currentUserID = Integer.parseInt(cookie.getValue());
                    }
                }
            }
            if(currentUserID < 0){
                out.println("<div class=\"box\"><h1><a href=\"./admin.jsp\">Go back to Admin Page</a></h1></div>"); 
                out.println("<div class=\"box\"><h1>Customers Database</h1></div>");
                out.println("<div class=\"box\"><table border=\"1\"><tr><th>UserID</th><th>Name</th><th>Email</th><th>Card Data</th><th>Number of People</th><th>Veteran</th><th>Cart Data</th></tr>");
                List customers = customerController.getAllCustomers();
                for(int i = 0; i < customers.size();i++){
                    Customer currentCustomer = (Customer)customers.get(i);
                    int userID = currentCustomer.getUserID();
                    String name = currentCustomer.getName();
                    String email = currentCustomer.getEmail();
                    Card card = currentCustomer.getCardData();
                    int numOfPeople = currentCustomer.getNumberPeople();
                    boolean veteranStatus = currentCustomer.isVeteran();
                    List cartItems = currentCustomer.getCartData().getItems();
                    out.println("<tr>");
                    out.println("<td>" + userID + "</td>");
                    out.println("<td>" + name + "</td>");
                    out.println("<td>" + email + "</td>");
                    out.println("<td>" + card + "</td>");
                    out.println("<td>" + numOfPeople + "</td>");
                    out.println("<td>" + veteranStatus + "</td>");
                    out.println("<td>" + cartItems + "</td>");
                    out.println("<td>");
                    out.println("<form action=\"adminCustomers.jsp\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"userID\" value=\""+ userID + "\">");
                    out.println("<input type=\"hidden\" name=\"name\" value=\""+ name + "\">");
                    out.println("<input type=\"hidden\" name=\"card\" value=\""+ card + "\">");
                    out.println("<input type=\"hidden\" name=\"numOfPeople\" value=\""+ numOfPeople + "\">");
                    out.println("<input type=\"hidden\" name=\"veteranStatus\" value=\""+ veteranStatus + "\">");
                    out.println("<input type=\"hidden\" name=\"cartItems\" value=\""+ cartItems + "\">");
                    out.println("<input style=\"background-color: #ff5733;\" class=\"submit-btn\" type=\"submit\" value=\"Delete\">");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table></div>");
                if(request.getMethod().equals("POST")){
                    int userid = Integer.parseInt(request.getParameter("userID"));
                    customerController.removeCustomer(userid);
                    response.sendRedirect(request.getRequestURI());
                }
            }
            else{
                out.println("<div class\"box\" style=\"background-color: red;\">You do not have Admin Access</div>");
                out.println("<div class=\"box\" style=\"background-color: #444;\"><h1><a href=\"./home.jsp\">GO BACK</a></h1>"); 
            }
        %>
    </div>
</body>
</html>