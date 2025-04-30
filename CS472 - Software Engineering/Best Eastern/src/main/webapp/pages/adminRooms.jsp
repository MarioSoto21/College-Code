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
    <title>Rooms Database</title>
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
                out.println("<div class=\"box\"><h1>Rooms Database</h1></div>");
                out.println("<div class=\"box\"><table border=\"1\"><tr><th>Room Number</th><th>Customer ID</th><th>Room Size</th><th>Clean</th><th>Pets Allowed</th><th>Room Type</th></tr>");   
                List rooms = roomController.getAllRooms();
                for(int i = 0; i < rooms.size();i++){
                    Room currentRoom = (Room)rooms.get(i);
                    int custID = currentRoom.getCustomerId();
                    int roomNum = currentRoom.getRoomNumber();
                    int roomSize = currentRoom.getRoomSize();
                    boolean isClean = currentRoom.isCleaned();
                    boolean pet = currentRoom.isPetAllowed();
                    int roomType = currentRoom.getRoomPrice();
                    out.println("<tr>");
                    out.println("<td>" + roomNum + "</td>");
                    out.println("<td>" + custID + "</td>");
                    out.println("<td>" + roomSize + "</td>");
                    out.println("<td>" + isClean + "</td>");
                    out.println("<td>" + pet + "</td>");
                    out.println("<td>" + roomType + "</td>");
                    out.println("<td>");
                    out.println("<form action=\"adminRooms.jsp\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"roomNum\" value=\""+ roomNum + "\">");
                    out.println("<input type=\"hidden\" name=\"custID\" value=\""+ custID + "\">");
                    out.println("<input type=\"hidden\" name=\"roomSize\" value=\""+ roomSize + "\">");
                    out.println("<input type=\"hidden\" name=\"isClean\" value=\""+ isClean + "\">");
                    out.println("<input type=\"hidden\" name=\"pet\" value=\""+ pet + "\">");
                    out.println("<input type=\"hidden\" name=\"roomType\" value=\""+ roomType + "\">");
                    out.println("<input style=\"background-color: #ff5733;\" class=\"submit-btn\" type=\"submit\" value=\"Delete\">");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<form action=\"adminRooms.jsp\" method=\"post\">");
                out.println("<td><input type=\"text\" name=\"newRoomNum\"></td>");
                out.println("<td><input type=\"text\" name=\"newCustID\"></td>");
                out.println("<td><input type=\"text\" name=\"newRoomSize\"></td>");
                out.println("<td><input type=\"text\" name=\"newClean\"></td>");
                out.println("<td><input type=\"text\" name=\"newPet\"></td>");
                out.println("<td><input type=\"text\" name=\"newRoomPrice\"></td>");
                out.println("<td><input style=\"background-color: #33ff33;\" class=\"submit-btn\" type=\"submit\" value=\"Add\"></td>");
                out.println("</table></div>");
                if(request.getMethod().equals("POST")){
                    if(request.getParameter("roomNum") != null){
                        int curRoom = Integer.parseInt(request.getParameter("roomNum"));
                        roomController.removeRoom(curRoom);
                        response.sendRedirect(request.getRequestURI());
                    }
                    else if (request.getParameter("roomNum") == null) {
                        try {
                            int newCustID = Integer.parseInt(request.getParameter("newCustID"));
                            int newRoomNum = Integer.parseInt(request.getParameter("newRoomNum"));
                            int newRoomSize = Integer.parseInt(request.getParameter("newRoomSize"));
                            int newRoomPrice = Integer.parseInt(request.getParameter("newRoomPrice"));
                            boolean newClean = Boolean.parseBoolean(request.getParameter("newClean"));
                            boolean newPet = Boolean.parseBoolean(request.getParameter("newPet"));
                            Room newRoom = new Room(newCustID, newRoomNum, newRoomSize, newClean, newPet, newRoomPrice);
                            roomController.addRoom(newRoom);
                            response.sendRedirect(request.getRequestURI());
                        } catch (Exception e) {
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