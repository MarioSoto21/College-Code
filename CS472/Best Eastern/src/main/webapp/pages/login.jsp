<%@ page import="java.util.*, controllers.*, objects.*, java.io.*" contentType="text/html;charset=UTF-8" language="java" %>
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
    <form method="post" action="login.jsp">
        <div class="container">
            <div class="box">
                <h2>Login</h2>
                <form method="post">
                    <input type="text" placeholder="Username" name="username" required><br>
                    <input type="password" placeholder="Password" name="password" required><br><br>
                    <input type="submit" value="Login"><br><br>
                </form>
                <div class="links">
                    <!-- NEEDS TO BE MADE -->
                    <a href="createaccount.jsp">Create Account</a>
                    <a href="forgotpassword.jsp">Forgot Password</a>
                </div>
            </div>
            <%
            



                    Controller mainController = Controller.getInstance();
                    LoginController lc = (LoginController)mainController.getLoginController();
                    
                    if(request.getMethod().equals("POST")){
                        
                        String un = request.getParameter("username");
                        String pass = request.getParameter("password");
                        if(lc.login(un,pass) != 0){
                            //out.println("<div class\"box\" style=\"background-color: green;\">Hello " + un + ", You have been successfully logged in!</div>");
                            int userid = lc.login(un,pass);
                            if(userid < 0){
                                Cookie cookie = new Cookie("userID",String.valueOf(userid));
                                Cookie adminCookie = new Cookie("adminstatus","true");
                                response.addCookie(cookie);
                                response.addCookie(adminCookie);
                                out.println("<div class=\"box\" style=\"background-color: green;\">Successfully Logged in as Admin</div>");
                                out.println("<div class=\"box\" style=\"background-color: green;\">To view Admin page, press the button below</div>");
                                out.println("<div class=\"box\" style=\"background-color: #444;\"><h1><a href=\"./admin.jsp\">Admin Page</a></h1>"); 
                            }
                            else{
                                session.setAttribute("userID",userid);
                                Cookie cookie = new Cookie("userID",String.valueOf(userid));
                                Cookie admincookie = new Cookie("adminstatus","false");
                                response.addCookie(admincookie);
                                response.addCookie(cookie);
                                out.println("<div class=\"box\" style=\"background-color: green;\">You have Successfully Logged In</div>");
                                out.println("<div class=\"box\" style=\"background-color: green;\">To view your account press the button below</div>");
                                out.println("<div class=\"box\" style=\"background-color: #444;\"><h1><a href=\"./account.jsp\">Account View</a></h1>"); 
                            }
                            
                        }
                        else{
                            out.println("<div class\"box\" style=\"background-color: red;\">Sorry, you entered an incorrect username or password.</div>");
                        }
                    };
                %>
        </div>
    </form>
</body>
</html>