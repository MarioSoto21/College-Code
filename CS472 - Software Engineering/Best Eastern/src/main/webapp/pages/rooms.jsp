<%@ page import="java.util.*, controllers.*, databases.*, objects.*, java.io.*" contentType="text/html;charset=UTF-8" language="java" %>
<%
    Controller mainController = Controller.getInstance();
    LoginController loginController = (LoginController)mainController.getLoginController();
    CustomerController customerController = (CustomerController)mainController.getCustomerController();
    RoomController roomController = (RoomController)mainController.getRoomController();
    LayoutDatabase layoutDatabase = (LayoutDatabase)mainController.getLayoutDatabase();
    ReservationController reservationController = (ReservationController)mainController.getReservationController();
    int currentUserID = 0;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userID")) {
                currentUserID = Integer.parseInt(cookie.getValue());
            }
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.75">
    <link rel="stylesheet" type="text/css" href="./style.css">
    <title>Available Rooms</title>
    <style>
        .available { background-color: green; }
        .mismatch { background-color: yellow;}
        .unavailable { background-color: red; }
        .room-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(80px, 1fr));
            display: flex;
            flex-direction: row;
            grid-gap: 10px;
            padding: 5px;
        }
        .floor {
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .room {
            width: 80px;
            height: 80px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
        }
        .blankRoom {
            width: 80px;
            height: 80px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 5px;
            background-color: #333;
        }
        .rdropdown {
            margin: 0px;
        }
        .rdropbtn {
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        .rdropdown-content {
            display: none;
            position: absolute;
            background-color: #333;
            min-width: 70px;
            z-index: 1;
            border-radius: 5px;
            border: 2px solid white;
        }
        .rdropdown-content a {
            color: #fff;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .rdropdown-content a:hover {
            background-color: #186c34;
            border-radius: 5px;
        }
        .rdropdown:hover .rdropdown-content {
            display: block;
        }
    </style>
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
            <!-- Form to select start and end dates -->
            <form action="rooms.jsp" method="post">
                Start Date: <input type="date" name="startDate" value="<%= request.getParameter("startDate") %>" required><br>
                End Date: <input type="date" name="endDate" value="<%= request.getParameter("endDate") %>" required><br>
                Number of People: <input type="number" name="numHumans" value="<%= request.getParameter("numHumans") %>" required><br>
                Pets Allowance needed: <input type="checkbox" name="petsAllowed" <% if (request.getParameter("petsAllowed") != null) { %>checked<% } %> ><br>
                <input type="submit" value="Check Availability">
            </form>
        </div>
        <div class="box">
            <!-- Form to enter roomNumber -->
            <form action="rooms.jsp" method="post">
                Enter roomNumber: <input type="number" name="roomNumber" required><br>
                <!-- Hidden inputs to carry over other form data -->
                <input type="hidden" name="startDate" value="<%= request.getParameter("startDate") %>">
                <input type="hidden" name="endDate" value="<%= request.getParameter("endDate") %>">
                <input type="hidden" name="numHumans" value="<%= request.getParameter("numHumans") %>">
                <input type="hidden" name="petsAllowed" value="<%= request.getParameter("petsAllowed") %>">
                <input type="submit" value="Add room to cart">
            </form>
        </div>
        <% 
            if (request.getMethod().equals("POST")) {
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                int numHumans = 0;
                if(request.getParameter("numHumans") != null){numHumans = Integer.parseInt(request.getParameter("numHumans"));}
                boolean petsAllowed = request.getParameter("petsAllowed") != null;

                // Load layout data
                int[][][] roomLayout = layoutDatabase.getRoomLayout();
                
                // Display rooms in a 2D grid
                for (int floor = 0; floor < roomLayout.length; floor++) {
                    out.println("<div class='box'><div class='floor'><h1>Floor " + (floor + 1) + "</h1></div>");
                    for (int row = 0; row < roomLayout[floor].length; row++) {
                        out.println("<div class='room-grid'>");
                        for (int col = 0; col < roomLayout[floor][row].length; col++) {
                            int roomNumber = roomLayout[floor][row][col];
                            boolean isAvailable = reservationController.checkReservationAvailable(roomNumber, startDate, endDate);
                            boolean fitCritiera = false;
                            if (roomNumber != 0) { fitCritiera = (roomController.getRoom(roomNumber).getRoomSize() >= numHumans && roomController.getRoom(roomNumber).isPetAllowed() || petsAllowed == false); }
                            String roomClass;
                            if (isAvailable) { if (fitCritiera) { roomClass = "available"; } else { roomClass = "mismatch"; } } else { roomClass = "unavailable"; }
                            if (roomNumber != 0) {
                                if (isAvailable) {
                                    %>
                                        <div class='room <%= roomClass %> rdropdown'>
                                            <button class='rdropbtn <%= roomClass %>'><%= roomNumber %></button>
                                            <div class='rdropdown-content'>
                                                <div>Room: <%= roomNumber %></div>
                                                <div>Size: <%= roomController.getRoom(roomNumber).getRoomSize() %></div>
                                                <div>Pet Allowance: <%= (roomController.getRoom(roomNumber).isPetAllowed() ? "Allowed" : "Not Allowed") %></div>
                                                <div>Price per Night: $<%= roomController.getRoom(roomNumber).getRoomPrice() %></div>
                                            </div>
                                        </div>
                                    <%
                                } else {
                                    out.println("<div class='room " + roomClass + "'>" + roomNumber + "</div>");
                                }
                            } else {
                                out.println("<div class='blankRoom'></div>");
                            }
                        }
                        out.println("</div>");
                    }
                    out.println("</div>");
                }
            }
        %>
        <%
            if (request.getParameter("roomNumber") != null) {
                if (reservationController.checkReservationAvailable(Integer.parseInt(request.getParameter("roomNumber")), request.getParameter("startDate"), request.getParameter("endDate"))) {
                    int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
                    String startDate = request.getParameter("startDate");
                    String endDate = request.getParameter("endDate");
                    if (customerController.getCart(currentUserID) != null) {
                        int reservationID = reservationController.getNextAvailableReservationID();
                        Reservation reservation = new Reservation(currentUserID, reservationController.getPriceFromDates(roomController.getRoom(roomNumber).getRoomPrice(), startDate, endDate), startDate, endDate, roomNumber, reservationID);
                        reservationController.addReservation(reservation);
                        customerController.getCart(currentUserID).addItem(reservationID);
                        
                    }
                }
                response.sendRedirect(mainController.getLocation("rooms"));
            }
        %>
    </div>
</body>
</html>
