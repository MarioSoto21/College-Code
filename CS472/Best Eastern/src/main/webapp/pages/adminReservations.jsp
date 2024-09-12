<%@ page import="java.util.*, controllers.*, databases.*, objects.*, java.io.*" contentType="text/html;charset=UTF-8" language="java" %>
<%
    Controller mainController = Controller.getInstance();
    LoginController loginController = (LoginController)mainController.getLoginController();
    CustomerController customerController = (CustomerController)mainController.getCustomerController();
    RoomController roomController = (RoomController)mainController.getRoomController();
    ReservationController reservationController = (ReservationController)mainController.getReservationController();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.75">
    <link rel="stylesheet" type="text/css" href="./style.css">
    <title>Reservations Admin</title>
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
                out.println("<div class=\"box\"><h1>Reservations Database</h1></div>");
                out.println("<div class=\"box\"><table border=\"1\"><tr><th>Reservation ID</th><th>Customer</th><th>Price</th><th>Start Date</th><th>End Date</th><th>Room Number</th></tr>");
                List reservations = reservationController.getAllReservations();
                for(int i = 0; i < reservations.size();i++){
                    Reservation currentRes = (Reservation)reservations.get(i);
                    int customerID = currentRes.getCustomerId();
                    double price = currentRes.getPrice();
                    String startDate = currentRes.getStartDate();
                    String endDate = currentRes.getEndDate();
                    int roomNumber = currentRes.getRoomNumber();
                    int reservationID = currentRes.getReservationId();

                    out.println("<tr>");
                    out.println("<td>" + reservationID + "</td>");
                    out.println("<td>" + customerID + "</td>");
                    out.println("<td>" + price + "</td>");
                    out.println("<td>" + startDate + "</td>");
                    out.println("<td>" + endDate + "</td>");
                    out.println("<td>" + roomNumber + "</td>");
                    out.println("<td>");
                    out.println("<form action=\"adminReservations.jsp\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"reservationID\" value=\""+ reservationID + "\">");
                    out.println("<input type=\"hidden\" name=\"customerID\" value=\""+ customerID + "\">");
                    out.println("<input type=\"hidden\" name=\"price\" value=\""+ price + "\">");
                    out.println("<input type=\"hidden\" name=\"startDate\" value=\""+ startDate + "\">");
                    out.println("<input type=\"hidden\" name=\"endDate\" value=\""+ endDate + "\">");
                    out.println("<input type=\"hidden\" name=\"roomNumber\" value=\""+ roomNumber + "\">");
                    out.println("<input style=\"background-color: #ff5733;\" class=\"submit-btn\" type=\"submit\" value=\"Delete\">");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<form action=\"adminReservations.jsp\" method=\"post\">");
                out.println("<td><input type=\"text\" name=\"newReservationID\"></td>");
                out.println("<td><input type=\"text\" name=\"newCustomerID\"></td>");
                out.println("<td><input type=\"text\" name=\"newPrice\"></td>");
                out.println("<td><input type=\"text\" name=\"newStartDate\"></td>");
                out.println("<td><input type=\"text\" name=\"newEndDate\"></td>");
                out.println("<td><input type=\"text\" name=\"newRoomNumber\"></td>");
                out.println("<td><input style=\"background-color: #33ff33;\" class=\"submit-btn\" type=\"submit\" value=\"Add\"></td>");
                out.println("</table></div>");
                if(request.getMethod().equals("POST")){
                    if(request.getParameter("reservationID") != null){
                        int resID = Integer.parseInt(request.getParameter("reservationID"));
                        reservationController.removeReservation(resID);
                        response.sendRedirect(request.getRequestURI());
                    }
                    else if (request.getParameter("reservationID") == null){
                        try{
                            int newReservationID = Integer.parseInt(request.getParameter("newReservationID"));
                            int newCustomerID = Integer.parseInt(request.getParameter("newCustomerID"));
                            double newPrice = Double.parseDouble("newPrice");
                            String newStartDate = request.getParameter("newStartDate");
                            String newEndDate = request.getParameter("newEndDate");
                            int newRoomNumber = Integer.parseInt(request.getParameter("newRoomNumber"));
                            
                            Reservation newReservation = new Reservation(newCustomerID,newPrice,newStartDate,newEndDate,newRoomNumber,newReservationID);
                            reservationController.addReservation(newReservation);
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