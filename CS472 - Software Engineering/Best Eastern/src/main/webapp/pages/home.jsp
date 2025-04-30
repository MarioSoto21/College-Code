<%@ page import="java.util.*, objects.*, databases.*, controllers.*, java.io.*" contentType="text/html;charset=UTF-8" language="java" %>
<% 
    Controller mainController = Controller.getInstance();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.75">
    <link rel="stylesheet" type="text/css" href="./style.css">
    <title>Best Eastern</title>
    <style> 
    body{
        /* Temp image */
            background-image: url('https://enmucs.com/calebparten/homepageImage.jpg');
        /* Temp image */
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
    }
    .logoe img {
            max-width: 100px; /* Sets the size of the logo */
            margin-bottom: 10px;
            background: #333; /* White background */
            border-radius: 50%; /* Makes the background rounded */
            padding: 10px; /* Adds space around the logo inside the circle */
            box-shadow: 0 0 8px rgba(0,0,0,0.6); /* Optional: Adds subtle shadow for better visibility */
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
        <div class="logoe" style="max-width: 50%;">
            <img src="https://enmucs.com/calebparten/BE-02.png" alt="Best Eastern logo" style="max-width: 100%; height: auto;">
        </div>
    </div>
    <div class="container">
        <!-- Accommodations Section -->
        <div class="box">
            <h2>Accommodations</h2>
            <p>Experience comfort and relaxation in our well-appointed rooms and suites. Each room is designed with your comfort in mind, featuring modern amenities and stylish decor.</p>
            <p>Choose from a variety of room options to suit your needs, whether you're traveling solo, with family, or for business.</p>
        </div>

        <!-- Dining Section -->
        <div class="box">
            <h2>Dining</h2>
            <p>Indulge in delicious culinary creations at our onsite restaurant. Our talented chefs use only the freshest ingredients to craft mouthwatering dishes that will tantalize your taste buds.</p>
            <p>Start your day with a hearty breakfast buffet or enjoy a leisurely dinner with friends and family.</p>
        </div>

        <!-- Meetings and Events Section -->
        <div class="box">
            <h2>Meetings and Events</h2>
            <p>Host your next business meeting, conference, or special event at Best Eastern Hotel. Our flexible event spaces can accommodate gatherings of all sizes, and our professional staff will ensure that every detail is taken care of.</p>
            <p>From state-of-the-art technology to personalized catering options, we'll help you create a successful and memorable event.</p>
        </div>

        <!-- Explore the Area Section -->
        <div class="box">
            <h2>Explore the Area</h2>
            <p>Conveniently located near a variety of attractions and activities, Best Eastern Hotel is the perfect base for exploring the area. Whether you're interested in outdoor adventures, shopping, or cultural experiences, there's something for everyone to enjoy.</p>
            <p>Our friendly staff are happy to provide recommendations and assistance to help you make the most of your stay.</p>
        </div>

    </div>
    <!-- Footer Section -->
    <footer class="footer">
        <!-- Footer content -->
    </footer>
</body>
</html>

