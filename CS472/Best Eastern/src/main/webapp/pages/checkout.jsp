<%@ page import="java.util.*, controllers.*, databases.*, objects.*, java.io.*, java.text.SimpleDateFormat, java.util.Date" contentType="text/html;charset=UTF-8" language="java" %>
<%
    Controller mainController = Controller.getInstance();
    ReservationController reservationController = (ReservationController) mainController.getReservationController();
    LoginController loginController = (LoginController) mainController.getLoginController();
    CustomerController customerController = (CustomerController) mainController.getCustomerController();
    RoomController roomController = (RoomController) mainController.getRoomController();

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

    // Tax rate
    double taxRate = 0.1; // 10%
    
    // Calculate total price without tax
    double totalPrice = 0.0;
    for (Reservation reservation : cart) {
        Date startDate = sdf.parse(reservation.getStartDate());
        Date endDate = sdf.parse(reservation.getEndDate());
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        int days = (int) ((endTime - startTime) / (1000 * 60 * 60 * 24));
        double roomPrice = reservation.getPrice(); 
        double reservationTotalPrice = (days + 1) * roomPrice; 
        totalPrice += reservationTotalPrice;
    }
    
    // Calculate tax amount
    double taxAmount = totalPrice * taxRate;
    
    // Calculate total price including tax
    double totalPriceWithTax = totalPrice + taxAmount;
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./style.css">
    <title>Checkout - Best Eastern</title>
    <style>
        *, *::before, *::after {
            box-sizing: border-box;
        }

        body {
            font-family: Arial;
            font-size: 17px;
            padding: 8px;
        }

        .row {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
            margin: 0 -16px;
        }

        .col-25 {
            -ms-flex: 25%;
            flex: 25%;
        }

        .col-50 {
            -ms-flex: 50%;
            flex: 50%;
        }

        .col-75 {
            -ms-flex: 75%;
            flex: 75%;
        }

        .col-25,
        .col-50,
        .col-75 {
            padding: 0 16px;
        }

        .container {
            background-color: #444;
            padding: 5px 20px 15px 20px;
        }

        input[type=text] {
            width: 100%;
            margin-bottom: 20px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type=date] {
            width: 100%;
            margin-bottom: 20px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        label {
            margin-bottom: 10px;
            display: block;
        }

        .icon-container {
            margin-bottom: 20px;
            padding: 7px 0;
            font-size: 24px;
        }

        .btn {
            background-color: #186c34;
            color: black;
            padding: 12px;
            margin: 10px 0;
            border: none;
            width: 100%;
            border-radius: 3px;
            cursor: pointer;
            font-size: 17px;
            font-family: cursive;
        }

        .btn:hover {
            background-color: #45a049;
        }

        a {
            color: #2196F3;
        }

        hr {
            border: 1px solid lightgrey;
        }

        span.price {
            float: right;
            color: white;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
            margin-bottom: 30px;
        }

        /* Responsive layout */
        @media screen and (max-width: 800px) {
            body {
                padding: 0;
            }
            
            .col-25, .col-50, .col-75 {
                width: 100%;
                margin-top: 0;
            }
            
            input[type=text], input[type=date], select {
                width: 100%;
                margin-top: 0;
            }

            .row {
                flex-direction: column;
            }

            .col-25 {
                margin-bottom: 20px;
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

<div style="text-align: center;">
    <div class="row">
        <div class="col-75">
            <div class="container">
                <h1 style="font-size: 48px">Checkout</h1>
                <form id="checkoutForm" action="confirmation.jsp" method="post">

                    <div class="row">
                        <div class="col-50">
                            <h3>Billing Address</h3>
                            <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                            <input type="text" id="fname" name="fullname" placeholder="Enter Name Here">
                            <label for="email"><i class="fa fa-envelope"></i> Email</label>
                            <input type="text" id="email" name="email" placeholder="Enter Email Here">
                            <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                            <input type="text" id="adr" name="address" placeholder="Enter Address Here">
                            <label for="city"><i class="fa fa-institution"></i> City</label>
                            <input type="text" id="city" name="city" placeholder="Enter City Here">

                            <div class="row">
                                <div class="col-50">
                                    <label for="state">State</label>
                                    <input type="text" id="state" name="state" placeholder="Enter State Here">
                                </div>
                                <div class="col-50">
                                    <label for="zip">Zip</label>
                                    <input type="text" id="zip" name="zip" placeholder="Enter Zip Code Here">
                                </div>
                            </div>
                        </div>

                        <div class="col-50">
                            <h3>Payment</h3>
                            <label for="fname">Accepted Cards</label>
                            <div class="icon-container">
                                <i class="fa fa-cc-visa" style="color:navy;"></i>
                                <i class="fa fa-cc-amex" style="color:blue;"></i>
                                <i class="fa fa-cc-mastercard" style="color:red;"></i>
                                <i class="fa fa-cc-discover" style="color:orange;"></i>
                            </div>
                            <label for="cname">Name on Card</label>
                            <input type="text" id="cname" name="cardname" placeholder="Enter Full Name">
                            <label for="ccnum">Credit card number</label>
                            <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444">
                            <label for="expdate">Exp Month/Year</label>
                            <input type="text" id="expdate" name="expdate" placeholder="Month/Year">
                            <div class="row">
                                <div class="col-50">
                                    <label for="cvv">CVV</label>
                                    <input type="text" id="cvv" name="cvv" placeholder="352">
                                </div>
                            </div>
                        </div>

                    </div>
                    <input type="submit" name="checkoutBtn" value="Place Order" class="btn">
                </form>
            </div>
        </div>
        <div class="col-25">
            <!-- Cart summary -->
            <div class="container">
                <h1>Cart</h1>
                <ul>
                    <% 
                        for (Reservation reservation : cart) {
                            Date startDate = sdf.parse(reservation.getStartDate());
                            Date endDate = sdf.parse(reservation.getEndDate());
                            long startTime = startDate.getTime();
                            long endTime = endDate.getTime();
                            int days = (int) ((endTime - startTime) / (1000 * 60 * 60 * 24));
                            double roomPrice = reservation.getPrice(); 
                            double reservationTotalPrice = (days + 1) * roomPrice; 
                    %>
                    <!-- Removed reservation ID display -->
                    <li>Room: <%= reservation.getRoomNumber() %> - $<%= reservationTotalPrice %></li>
                    <% } %>
                    <!-- Display total price without tax -->
                    <li>Subtotal: $<%= totalPrice %></li>
                    <!-- Display tax amount -->
                    <li>Tax (10%): $<%= taxAmount %></li>
                    <!-- Display total price including tax -->
                    <li>Total: $<%= totalPriceWithTax %></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script>
    function validateForm() {
        var fullname = document.getElementById("fname").value;
        var email = document.getElementById("email").value;
        var address = document.getElementById("adr").value;
        var city = document.getElementById("city").value;
        var state = document.getElementById("state").value;
        var zip = document.getElementById("zip").value;
        var cardname = document.getElementById("cname").value;
        var cardnumber = document.getElementById("ccnum").value;
        var expdate = document.getElementById("expdate").value;
        var cvv = document.getElementById("cvv").value;

        if (fullname === "" || email === "" || address === "" || city === "" || state === "" || zip === "" || cardname === "" || cardnumber === "" || expdate === "" || cvv === "") {
            alert("Please fill out all fields before proceeding.");
            return false;
        }
        return true;
    }

    document.getElementById("checkoutForm").addEventListener("submit", function(event) {
        if (!validateForm()) {
            event.preventDefault(); // Prevent form submission
        }
    });
</script>

</body>
</html>
