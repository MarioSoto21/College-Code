<%@ page import="java.util.*, controllers.*, databases.*, objects.*, java.io.*, java.text.SimpleDateFormat, java.util.Date" contentType="text/html;charset=UTF-8" language="java" %>

<%
    Controller mainController = Controller.getInstance();
    ReservationController reservationController = (ReservationController) mainController.getReservationController();

    int currentUserID = 0;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userID")) {
                currentUserID = Integer.parseInt(cookie.getValue());
            }
        }
    }
    List<Reservation> cart = reservationController.getCustomerReservations(currentUserID);

    // Define date format
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

     // Tax rate (you can adjust this value as needed)
    double taxRate = 0.1; // 10%
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./style.css">
    <title>Cart Best Eastern</title>
    <style>   
        *, *::before, *::after {
            box-sizing: border-box;
        }

        .container {
            margin: 20px auto;
            max-width: 800px;
            padding: 0 20px;
        }

        .box {
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .box h1 {
            margin-bottom: 20px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #186c34;
        }

        .checkout-button {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            font-size: 16px;
            font-family: cursive;
            color: #fff;
            background-color: #186c34;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .checkout-button:hover {
            background-color: #45a049;
        }

        @media screen and (max-width: 800px) {
            .box {
                padding: 10px;
            }
            table {
                width: 100%;
            }
            th, td {
                padding: 8px;
            }
            .checkout-button {
                padding: 8px 16px;
                margin: 10px auto;
            }
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
        <h1>Your Cart</h1>
        <% if (cart.isEmpty()) { %>
            <p>Your cart is empty.</p>
        <% } else { %>
            <table>
                <!-- Table header -->
                <thead>
                    <tr>
                        <th>Room Number</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Price per Day</th>
                        <th>Total Price</th>
                    </tr>
                </thead>
                <!-- Table body -->
                <tbody>
                    <% 
                        double totalPrice = 0.0;
                        for (Reservation reservation : cart) {
                            // Convert date strings to Date objects
                            Date startDate = sdf.parse(reservation.getStartDate());
                            Date endDate = sdf.parse(reservation.getEndDate());
                            
                            // Perform date calculations
                            long startTime = startDate.getTime();
                            long endTime = endDate.getTime();
                            int days = (int) ((endTime - startTime) / (1000 * 60 * 60 * 24));
                            
                            double roomPrice = reservation.getPrice(); // Get price of the room
                            double reservationTotalPrice = (days + 1) * roomPrice; // Calculate total price for this reservation including initial day
                            totalPrice += reservationTotalPrice; // Add to total price
                    %>
                        <!-- Table row -->
                        <tr>
                            <td><%= reservation.getRoomNumber() %></td>
                            <td><%= sdf.format(startDate) %></td>
                            <td><%= sdf.format(endDate) %></td>
                            <td>$<%= roomPrice %></td>
                            <td>$<%= reservationTotalPrice %></td>
                        </tr>
                    <% } %>
                    <!-- Total row -->
                    <tr>
                        <td colspan="4" style="text-align: right;">Subtotal:</td>
                        <td>$<%= totalPrice %></td>
                    </tr>
                    <!-- Tax row -->
                    <tr>
                        <td colspan="4" style="text-align: right;">Tax (10%):</td>
                        <td>$<%= totalPrice * taxRate %></td>
                    </tr>
                    <!-- Total with tax row -->
                    <tr>
                        <td colspan="4" style="text-align: right;">Total:</td>
                        <td>$<%= totalPrice + (totalPrice * taxRate) %></td>
                    </tr>
                </tbody>
            </table>
            <!-- Checkout button -->
            <button onclick="window.location.href = 'checkout.jsp'" class="checkout-button">Checkout</button>
        <% } %>
    </div>
</div>
</body>
</html>
