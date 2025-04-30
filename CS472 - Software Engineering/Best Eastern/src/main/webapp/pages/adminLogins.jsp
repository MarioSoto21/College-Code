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
    <title>Logins Admin</title>
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
    <div class="sidebar">
        <div class="sbItem"><a href="./adminLogins.jsp">Logins Database</a></div>
        <div class="sbItem"><a href="./adminCustomers.jsp">Customers Database</a></div>
        <div class="sbItem"><a href="./adminRooms.jsp">Rooms Database</a></div>
        <div class="sbItem"><a href="./adminReservations.jsp">Reservations Database</a></div>
    </div>
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
                out.println("<div class=\"box\"><h1>Logins Database</h1></div>");
                out.println("<div class=\"box\"><table border=\"1\"><tr><th>UserID</th><th>Username</th><th>Password</th></tr>");
                List logins = loginController.getLoginDatabase().getLogins();
                for(int i = 0; i < logins.size();i++){
                    Login currentLogin = (Login)logins.get(i);
                    int loginUserID = currentLogin.getUserID();
                    String username = currentLogin.getUsername();
                    String password = currentLogin.getPassword();
                    out.println("<tr>");
                    out.println("<td>" + loginUserID + "</td>");
                    out.println("<td>" + username + "</td>");
                    out.println("<td>" + password + "</td>");
                    out.println("<td>");
                    out.println("<form action=\"adminLogins.jsp\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"loginID\" value=\""+ loginUserID + "\">");
                    out.println("<input type=\"hidden\" name=\"username\" value=\""+ username + "\">");
                    out.println("<input type=\"hidden\" name=\"password\" value=\""+ password + "\">");
                    out.println("<input style=\"background-color: #ff5733;\" class=\"submit-btn\" type=\"submit\" value=\"Delete\">");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                out.println("<tr>");
                out.println("<form action=\"adminLogins.jsp\" method=\"post\">");
                out.println("<td><input type=\"text\" name=\"newID\"></td>");
                out.println("<td><input type=\"text\" name=\"newUsername\"></td>");
                out.println("<td><input type=\"text\" name=\"newPassword\"></td>");
                out.println("<td><input style=\"background-color: #33ff33;\" class=\"submit-btn\" type=\"submit\" value=\"Add\"></td>");
                if(request.getMethod().equals("POST")){
                    if(request.getParameter("username") != null){
                        String username = request.getParameter("username");
                        out.println(username);
                        loginController.removeLogin(username);
                        response.sendRedirect(request.getRequestURI());
                    }
                    else if(request.getParameter("newUsername") != null && request.getParameter("newPassword") != null){
                        try {
                            String newUsername = request.getParameter("newUsername");
                            String newPassword = request.getParameter("newPassword");
                            int newUserID = Integer.parseInt(request.getParameter("newID"));
                            Login newLogin = new Login(newUserID,newUsername,newPassword);
                            loginController.addLogin(newLogin);
                            response.sendRedirect(request.getRequestURI());
                        } catch (Exception e){
                            response.sendRedirect(request.getRequestURI());
                        }
                    }
                    
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