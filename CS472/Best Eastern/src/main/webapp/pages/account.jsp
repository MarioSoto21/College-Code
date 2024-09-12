<%@ page import="java.util.*, objects.Customer, objects.Reservation, objects.Login, controllers.Controller, controllers.LoginController, controllers.CustomerController, controllers.ReservationController, databases.*" contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Retrieving necessary controllers
    Controller mainController = Controller.getInstance();
    ReservationController reservationController = mainController.getReservationController();
    CustomerController customerController = mainController.getCustomerController();
    LoginController loginController = mainController.getLoginController();
    

    // Default value if no user is logged in
    int userID = 99999; // Default value to indicate no user logged in
    Customer customer = null;
    Login login = null; // Declaration of the login variable

    // Retrieving customer information using the userID from the cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userID")) {
                String tmpuserID = cookie.getValue();
                if (tmpuserID != null && !tmpuserID.isEmpty()) {
                    try {
                        userID = Integer.parseInt(tmpuserID);
                        customer = customerController.getCustomer(userID);
                        login = loginController.getLogin(userID); // Fetching login information
                    } catch (NumberFormatException e) {
                        // Handle parsing error
                        e.printStackTrace();
                    } catch (Exception ex) {
                        // Handle other exceptions
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    // Retrieving customer's reservations
    List<Reservation> customerReservations = new ArrayList<>();
    if (customer != null) {
        customerReservations = reservationController.getCustomerReservations(customer.getUserID());
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account - Best Eastern</title>
    <link rel="stylesheet" href="./style.css">
    <style>
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        form {
            max-width: 600px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            text-align: left;
        }

        input[type="text"],
        input[type="password"],
        input[type="checkbox"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #186c34;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .checkbox-container {
            display: flex;
            align-items: center;
        }

        .checkbox-label {
            margin-right: 10px;
        }

        @media (max-width: 768px) {
            .menu-container {
                flex-direction: column;
                align-items: flex-start;
            }

            .menu-container a {
                margin: 5px 0;
            }

            .header {
                text-align: center;
            }

            form {
                padding: 0 20px;
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
        <% if (customer != null) { %>
        <h2>Welcome, <%= customer.getName() %>!</h2>
        <h3>Update Account Information</h3>
        <form method="post" action="./account.jsp">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= customer.getName() %>"><br>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="<%= customer.getEmail() %>"><br>
            <label for="age">Age:</label>
            <input type="text" id="age" name="age" value="<%= customer.getAge() %>"><br>
            <div>
                <input type="checkbox" id="veteran" name="veteran" <%= customer.isVeteran() ? "checked" : "" %>>
                <label for="veteran">Veteran</label>
            </div>
            <input type="submit" value="Update">
        </form>

        <h3>Change Username and Password</h3>
        <form method="post" action="./account.jsp">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= login != null ? login.getUsername() : "" %>"><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="<%= login != null ? login.getPassword() : "" %>"><br>
            <input type="submit" name="updateLogin" value="Update">
        </form>


        <h3>Reservation History</h3>
        <% if (customerReservations.isEmpty()) { %>
            <p>No reservations found.</p>
        <% } else { %>
            <table>
                <tr>
                    <th>Room Number</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                </tr>
                <% for (Reservation reservation : customerReservations) { %>
                    <tr>
                        <td><%= reservation.getRoomNumber() %></td>
                        <td><%= reservation.getStartDate() %></td>
                        <td><%= reservation.getEndDate() %></td>
                    </tr>
                <% } %>
            </table>
        <% } %>
        <% } else { 
            // Display login link or admin page link
            if(userID < 0){
                out.println("<h1><a href=\"./admin.jsp\">Admin Page</a></h1>");
            } else {
        %>
        <h1><a href="./login.jsp">Log in</a></h1>
        <% 
            }
        } %>
    </div>

    <%-- Handle form submissions --%>
    <%
    if (request.getMethod().equals("POST") && customer != null) {
        try {
            // Process account information update
            String newName = request.getParameter("name");
            String newEmail = request.getParameter("email");
            String ageParam = request.getParameter("age");
            int newAge = Integer.parseInt(request.getParameter("age"));
            boolean isVeteran = request.getParameter("veteran") != null;
            int numberPeople = customer.getNumberPeople();

            // Update customer information
            customerController.updateCustomerInfo(userID, newName, newEmail, numberPeople, newAge, isVeteran);

            // Check if the "updateLogin" button is clicked
            if (request.getParameter("updateLogin") != null) {
                // Process username and password change
                String newUsername = request.getParameter("username");
                String newPassword = request.getParameter("password");
                
                // Update username and password
                loginController.editLogin(userID, newUsername, newPassword);
                
                out.println("<h3>Username and Password Updated Successfully</h3>");
            } else {
                out.println("<h3>Account Information Updated Successfully</h3>");
            }
            response.sendRedirect(mainController.getLocation("account"));
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>An error occurred while processing your request.</h3>");
        }
    }
%>
</div>
</body>
</html>
