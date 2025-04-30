<%@ page import="java.util.*, controllers.*, databases.*, objects.*, java.io.*" contentType="text/html;charset=UTF-8" language="java" %>
<%
    Controller mainController = Controller.getInstance();
    LoginController loginController = (LoginController)mainController.getLoginController();
    CustomerController customerController = (CustomerController)mainController.getCustomerController();
    RoomController roomController = (RoomController)mainController.getRoomController();
    ReservationController reservationController = (ReservationController)mainController.getReservationController();
    LayoutController layoutController = (LayoutController)mainController.getLayoutController();
    LayoutDatabase layoutDatabase = (LayoutDatabase)mainController.getLayoutDatabase();
    String databaseFilePath = mainController.getFilePath();

%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.75">
    <link rel="stylesheet" type="text/css" href="./style.css">
    <title>Admin</title>
    <style>
        .available { background-color: #444; }
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
    <div class="sidebar">
        <div>
            <form action="admin.jsp" method="post">
                <input type="hidden" name="buttonSelected" value="logins">
                <input class="sbItem" type="submit" id="sbButton" name="loginsButton" value="Login Database">
            </form>
        </div>
        <div>
            <form action="admin.jsp" method="post">
                <input type="hidden" name="buttonSelected" value="customers">
                <input class="sbItem" type="submit" id="sbButton" name="customersButton" value="Customer Database">
            </form>
        </div>
        <div>
            <form action="admin.jsp" method="post">
                <input type="hidden" name="buttonSelected" value="rooms">
                <input class="sbItem" type="submit" id="sbButton" name="roomsButton" value="Room Database">
            </form>
        </div>
        <div>
            <form action="admin.jsp" method="post">
                <input type="hidden" name="buttonSelected" value="reservations">
                <input class="sbItem" type="submit" id="sbButton" name="reservationsButton" value="Reservation Database">
            </form>
        </div>
        <div>
            <form action="admin.jsp" method="post">
                <input type="hidden" name="buttonSelected" value="layout">
                <input class="sbItem" type="submit" id="sbButton" name="layoutButton" value="Floor Layout">
            </form>
        </div>
    </div>
        
    <div class="container">
        
        <%  
            String buttonClicked = request.getParameter("buttonSelected");
            if(buttonClicked == null){
                buttonClicked = (String) session.getAttribute("buttonSelected");
            }
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
                if (buttonClicked == null){
                    out.println("<div class=\"box\"><h1>ADMIN PAGE</h1></div>");
                }
                else if (buttonClicked.equals("logins")){
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
                        out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"logins\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"deleting\">");
                        out.println("<input type=\"hidden\" name=\"username\" value=\""+ username + "\">");
                        out.println("<input style=\"background-color: #ff5733;\" class=\"Deletebtn\" type=\"submit\" value=\"Delete\">");
                        out.println("</td>");

                        out.println("<td>");
                        out.println("</form>");
                        out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"logins\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditID\" value=\""+ loginUserID + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditUsername\" value=\""+ username + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditPassword\" value=\""+ password + "\">");
                        out.println("<input style=\"background-color: #FFFF00;\" class=\"Editbtn\" type=\"submit\" value=\"Edit\">");
                        out.println("</form>");
                        out.println("</td>");
                        
                        out.println("</tr>");
                    }
                    out.println("<tr>");
                    out.println("<form action=\"admin.jsp\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"logins\">");
                    out.println("<input type=\"hidden\" name=\"formAction\" value=\"adding\">");
                    out.println("<input type=\"hidden\" name=\"oldUsername\" value=\"blank\">");
                    out.println("<td><input type=\"text\" name=\"newID\"></td>");
                    out.println("<td><input type=\"text\" name=\"newUsername\"></td>");
                    out.println("<td><input type=\"text\" name=\"newPassword\"></td>");
                    out.println("<td><input style=\"background-color: #33ff33;\" name=\"editORadd\" class=\"Addbtn\" type=\"submit\" value=\"Add\"></td>");
                    out.println("</tr></form>");
                    out.println("</div>");

                    out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"logins\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"downloading\">");
                        out.println("<input style=\"background-color: #FFFF00;\" class=\"DownBtn\" type=\"submit\" value=\"Export to CSV\">");
                    out.println("</form>");

                    out.println("<script>");
                    out.println("const editButtons = document.querySelectorAll('.Editbtn');");
                    out.println("editButtons.forEach(button => {");
                    out.println("button.addEventListener('click', () => {");
                    out.println("const row = button.parentElement.parentElement.parentElement;");
                    out.println("const selectedID = row.children[0].textContent;");
                    out.println("const selectedUsername= row.children[1].textContent;");
                    out.println("const selectedPassword = row.children[2].textContent;");
                    out.println("document.querySelector('input[name=\"newID\"]').value = selectedID;");
                    out.println("document.querySelector('input[name=\"newUsername\"]').value = selectedUsername;");
                    out.println("document.querySelector('input[name=\"newPassword\"]').value = selectedPassword;");
                    out.println("document.querySelector('input[name=\"oldUsername\"]').value = selectedUsername;");
                    out.println("document.querySelector('.Addbtn').value = \"Edit\";");
                    out.println("document.querySelector('.Addbtn').style.backgroundColor = 'yellow';");
                    out.println("event.preventDefault();");
                    out.println("});");
                    out.println("});");
                    out.println("</script>");

                    String loginPostingForm = request.getParameter("formAction");
                    if(loginPostingForm != null){
                        if (loginPostingForm.equals("deleting")){
                            String username = request.getParameter("username");
                            loginController.removeLogin(username);
                            session.setAttribute("buttonSelected","logins");
                            response.sendRedirect(mainController.getLocation("admin"));
                        }
                        else if(loginPostingForm.equals("adding")){
                            try {
                                String action = request.getParameter("editORadd");
                                if(action.equals("Add")){
                                    String newUsername = request.getParameter("newUsername");
                                    String newPassword = request.getParameter("newPassword");
                                    int newUserID = Integer.parseInt(request.getParameter("newID"));
                                    Login newLogin = new Login(newUserID,newUsername,newPassword);
                                    loginController.addLogin(newLogin);
                                    session.setAttribute("buttonSelected","logins");
                                    response.sendRedirect(mainController.getLocation("admin"));
                                }
                                if(action.equals("Edit")){
                                    String oldUsername = request.getParameter("oldUsername");
                                    String editedUsername = request.getParameter("newUsername");
                                    String editedPassword = request.getParameter("newPassword");
                                    int editedID = Integer.parseInt(request.getParameter("newID"));
                                    Login editedLogin = new Login(editedID,editedUsername,editedPassword);
                                    loginController.removeLogin(oldUsername);
                                    loginController.addLogin(editedLogin);
                                    session.setAttribute("buttonSelected","logins");
                                    response.sendRedirect(mainController.getLocation("admin"));
                                }
                                
                            } catch (Exception e){
                                response.sendRedirect(mainController.getLocation("admin"));
                            }
                        }
                        else if(loginPostingForm.equals("downloading")){
                            response.reset();
                            response.setContentType("text/csv");
                            response.setHeader("Content-Disposition","attachment; filename=\"login_database.csv\"");
                            List<String[]> stringLogins = new ArrayList<>();
                            for(int i = 0; i <logins.size(); i++){
                                Login currentLogin = (Login)logins.get(i);
                                int loginUserID = currentLogin.getUserID();
                                String username = currentLogin.getUsername();
                                String password = currentLogin.getPassword();
                                String[] newLine = {String.valueOf(loginUserID),username,password};
                                stringLogins.add(newLine);
                            }
                            try(PrintWriter writer = response.getWriter()){
                                for(String[] line: stringLogins){
                                    for(int i = 0; i < line.length; i++){
                                        writer.print(line[i]);
                                        if(i < line.length - 1){
                                            writer.print(",");
                                        }
                                    }
                                    writer.println();
                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            session.setAttribute("buttonSelected","logins");
                            response.sendRedirect(mainController.getLocation("admin"));
                        }
                        
                    }
                    else{
                        out.println("");
                    }
                }
                else if(buttonClicked.equals("customers")){
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
                        out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"customers\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"deleting\">");
                        out.println("<input type=\"hidden\" name=\"userID\" value=\""+ userID + "\">");
                        out.println("<input type=\"hidden\" name=\"name\" value=\""+ name + "\">");
                        out.println("<input type=\"hidden\" name=\"card\" value=\""+ card + "\">");
                        out.println("<input type=\"hidden\" name=\"numOfPeople\" value=\""+ numOfPeople + "\">");
                        out.println("<input type=\"hidden\" name=\"veteranStatus\" value=\""+ veteranStatus + "\">");
                        out.println("<input type=\"hidden\" name=\"cartItems\" value=\""+ cartItems + "\">");
                        out.println("<input style=\"background-color: #ff5733;\" class=\"Deletebtn\" type=\"submit\" value=\"Delete\">");
                        out.println("</form>");
                        out.println("</td>");
                        out.println("</tr>");
                    }

                    out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"customers\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"downloading\">");
                        out.println("<input style=\"background-color: #FFFF00;\" class=\"DownBtn\" type=\"submit\" value=\"Export to CSV\">");
                    out.println("</form>");
                    

                    String customerPostingForm = request.getParameter("formAction");
                    if(customerPostingForm != null){
                        if (customerPostingForm.equals("deleting")){
                            int userid = Integer.parseInt(request.getParameter("userID"));
                            customerController.removeCustomer(userid);
                            session.setAttribute("buttonSelected","customers");
                            //response.sendRedirect(mainController.getLocation("admin"));
                            response.sendRedirect(mainController.getLocation("admin"));
                        }
                        else if(customerPostingForm.equals("downloading")){
                            response.reset();
                            response.setContentType("text/csv");
                            response.setHeader("Content-Disposition","attachment; filename=\"customer_database.csv\"");
                            List<String[]> stringCustomers = new ArrayList<>();
                            for(int i = 0; i < customers.size();i++){
                                Customer currentCustomer = (Customer)customers.get(i);
                                int userID = currentCustomer.getUserID();
                                String name = currentCustomer.getName();
                                String email = currentCustomer.getEmail();
                                Card card = currentCustomer.getCardData();
                                int numOfPeople = currentCustomer.getNumberPeople();
                                boolean veteranStatus = currentCustomer.isVeteran();
                                List cartItems = currentCustomer.getCartData().getItems();
                                String[] newLine = {String.valueOf(userID),name,email,String.valueOf(numOfPeople),String.valueOf(veteranStatus)};
                                stringCustomers.add(newLine);
                            }
                            try(PrintWriter writer = response.getWriter()){
                                for(String[] line: stringCustomers){
                                    for(int i = 0; i < line.length; i++){
                                        writer.print(line[i]);
                                        if(i < line.length - 1){
                                            writer.print(",");
                                        }
                                    }
                                    writer.println();
                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            session.setAttribute("buttonSelected","customers");
                            response.sendRedirect(mainController.getLocation("admin"));
                        }
                    }
                    else{
                        out.println("");
                    }
                }
                else if(buttonClicked.equals("rooms")){
                    out.println("<style>.custom-table th, .custom-table td {width: auto;}</style>");
                    
                    out.println("<div class=\"box\"><h1>Rooms Database</h1></div>");
                    out.println("<div class=\"box\"><table border=\"1\"><tr><th>Room #</th><th>Cust ID</th><th> Size </th><th> Clean? </th><th> Pets? </th><th> Price </th></tr>");
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
                        out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"roomNum\" value=\""+ roomNum + "\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"rooms\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"deleting\">");
                        out.println("<input style=\"background-color: #ff5733;\" class=\"Deletebtn\" type=\"submit\" value=\"Delete\">");
                        out.println("</form>");
                        out.println("</td>");

                        out.println("<td>");
                        out.println("</form>");
                        out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"rooms\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditRoomNum\" value=\""+ roomNum + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditCustID\" value=\""+ custID + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditSize\" value=\""+ roomSize + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditClean\" value=\""+ isClean + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditPet\" value=\""+ pet + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditPrice\" value=\""+ roomType + "\">");
                        out.println("<input style=\"background-color: #FFFF00;\" class=\"Editbtn\" type=\"submit\" value=\"Edit\">");
                        out.println("</form>");
                        out.println("</td>");

                        out.println("</tr>");
                    }
                    out.println("<tr>");
                    out.println("<form action=\"admin.jsp\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"rooms\">");
                    out.println("<input type=\"hidden\" name=\"formAction\" value=\"adding\">");
                    out.println("<input type=\"hidden\" name=\"oldRoomNumber\" value=\"blank\">");
                    out.println("<td><input type=\"number\" name=\"newRoomNum\"></td>");
                    out.println("<td><input type=\"number\" name=\"newCustID\"></td>");
                    out.println("<td><input type=\"number\" name=\"newRoomSize\"></td>");
                    out.println("<td><input type=\"checkbox\" name=\"newClean\"></td>");
                    out.println("<td><input type=\"checkbox\" name=\"newPet\"></td>");
                    out.println("<td><input type=\"number\" name=\"newRoomPrice\"></td>");
                    out.println("<td><input style=\"background-color: #33ff33;\" name=\"editORadd\"class=\"Addbtn\" type=\"submit\" value=\"Add\"></td>");
                    out.println("</table></div>");

                    out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"rooms\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"downloading\">");
                        out.println("<input style=\"background-color: #FFFF00;\" class=\"DownBtn\" type=\"submit\" value=\"Export to CSV\">");
                    out.println("</form>");
                    

                    out.println("<script>");
                    out.println("const editButtons = document.querySelectorAll('.Editbtn');");
                    out.println("editButtons.forEach(button => {");
                    out.println("button.addEventListener('click', () => {");
                    out.println("const row = button.parentElement.parentElement.parentElement;");
                    out.println("const selectedRoomNum = row.children[0].textContent;");
                    out.println("const selectedCustID= row.children[1].textContent;");
                    out.println("const selectedSize = row.children[2].textContent;");
                    out.println("const selectedClean = row.children[3].textContent;");
                    out.println("const selectedPet = row.children[4].textContent;");
                    out.println("const selectedPrice = row.children[5].textContent;");
                    out.println("document.querySelector('input[name=\"newRoomNum\"]').value = selectedRoomNum;");
                    out.println("document.querySelector('input[name=\"newCustID\"]').value = selectedCustID;");
                    out.println("document.querySelector('input[name=\"newRoomSize\"]').value = selectedSize;");
                    out.println("document.querySelector('input[name=\"newClean\"]').checked = (selectedClean == \"true\");");
                    out.println("document.querySelector('input[name=\"newPet\"]').checked = (selectedPet == \"true\");");
                    out.println("document.querySelector('input[name=\"newRoomPrice\"]').value = selectedPrice;");
                    out.println("document.querySelector('input[name=\"oldRoomNumber\"]').value = selectedRoomNum;");
                    out.println("document.querySelector('.Addbtn').value = \"Edit\";");
                    out.println("document.querySelector('.Addbtn').style.backgroundColor = 'yellow';");
                    out.println("event.preventDefault();");
                    out.println("});");
                    out.println("});");
                    out.println("</script>");
                    
                    String roomPostingForm = request.getParameter("formAction");
                    if(roomPostingForm != null){
                        if (roomPostingForm.equals("deleting")){
                            int curRoom = Integer.parseInt(request.getParameter("roomNum"));
                            roomController.removeRoom(curRoom);
                            layoutController.removeRoomNumber(curRoom);
                            session.setAttribute("buttonSelected","rooms");
                            response.sendRedirect(mainController.getLocation("admin"));
                        }
                        else if(roomPostingForm.equals("adding")){
                            try{
                                String action = request.getParameter("editORadd");
                                if(action.equals("Add")){
                                    int newCustID = Integer.parseInt(request.getParameter("newCustID"));
                                    int newRoomNum = Integer.parseInt(request.getParameter("newRoomNum"));
                                    int newRoomSize = Integer.parseInt(request.getParameter("newRoomSize"));
                                    int newRoomPrice = Integer.parseInt(request.getParameter("newRoomPrice"));
                                    boolean newClean = (request.getParameter("newClean") != null);
                                    boolean newPet = (request.getParameter("newPet") != null);
                                    Room newRoom = new Room(newCustID, newRoomNum, newRoomSize, newClean, newPet, newRoomPrice);
                                    roomController.addRoom(newRoom);
                                    session.setAttribute("buttonSelected","rooms");
                                    response.sendRedirect(mainController.getLocation("admin"));
                                }
                                if(action.equals("Edit")){
                                    int oldRoomNum = Integer.parseInt(request.getParameter("oldRoomNumber"));
                                    int editedCustID = Integer.parseInt(request.getParameter("newCustID"));
                                    int editedRoomNum = Integer.parseInt(request.getParameter("newRoomNum"));
                                    int editedRoomSize = Integer.parseInt(request.getParameter("newRoomSize"));
                                    int editedRoomPrice = Integer.parseInt(request.getParameter("newRoomPrice"));
                                    boolean editedClean = (request.getParameter("newClean") != null);
                                    boolean editedPet = (request.getParameter("newPet") != null);
                                    Room editedRoom = new Room(editedCustID,editedRoomNum,editedRoomSize,editedClean,editedPet,editedRoomPrice);
                                    roomController.removeRoom(oldRoomNum);
                                    roomController.addRoom(editedRoom);
                                    session.setAttribute("buttonSelected","rooms");
                                    response.sendRedirect(mainController.getLocation("admin"));
                                }
                            } catch (Exception e){
                                response.sendRedirect(mainController.getLocation("admin"));
                            }
                        }
                        else if(roomPostingForm.equals("downloading")){
                            response.reset();
                            response.setContentType("text/csv");
                            response.setHeader("Content-Disposition","attachment; filename=\"room_database.csv\"");
                            List<String[]> stringRooms = new ArrayList<>();
                            for(int i = 0; i < rooms.size(); i++){
                                Room currentRoom = (Room)rooms.get(i);
                                int custID = currentRoom.getCustomerId();
                                int roomNum = currentRoom.getRoomNumber();
                                int roomSize = currentRoom.getRoomSize();
                                boolean isClean = currentRoom.isCleaned();
                                boolean pet = currentRoom.isPetAllowed();
                                int roomType = currentRoom.getRoomPrice();
                                String[] newLine = {String.valueOf(custID),String.valueOf(roomNum),String.valueOf(roomSize),String.valueOf(isClean),String.valueOf(pet),String.valueOf(roomType)};
                                stringRooms.add(newLine);
                            }
                            try(PrintWriter writer = response.getWriter()){
                                for(String[] line: stringRooms){
                                    for(int i = 0; i < line.length; i++){
                                        writer.print(line[i]);
                                        if(i < line.length - 1){
                                            writer.print(",");
                                        }
                                    }
                                    writer.println();
                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            session.setAttribute("buttonSelected","rooms");
                            response.sendRedirect(mainController.getLocation("admin"));
                        }
                    }


                }
                else if(buttonClicked.equals("reservations")){
                    out.println("<div class=\"box\"><h1>Reservations Database</h1></div>");
                    out.println("<div class=\"box\"><table border=\"1\"><tr><th>ID</th><th>Cust ID</th><th>Price</th><th>Start</th><th>End</th><th>Room #</th></tr>");
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
                        out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"reservations\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"deleting\">");
                        out.println("<input type=\"hidden\" name=\"reservationID\" value=\""+ reservationID + "\">");
                        out.println("<input type=\"hidden\" name=\"customerID\" value=\""+ customerID + "\">");
                        out.println("<input type=\"hidden\" name=\"price\" value=\""+ price + "\">");
                        out.println("<input type=\"hidden\" name=\"startDate\" value=\""+ startDate + "\">");
                        out.println("<input type=\"hidden\" name=\"endDate\" value=\""+ endDate + "\">");
                        out.println("<input type=\"hidden\" name=\"roomNumber\" value=\""+ roomNumber + "\">");
                        out.println("<input style=\"background-color: #ff5733;\" class=\"Deletebtn\" type=\"submit\" value=\"Delete\">");
                        out.println("</form>");
                        out.println("</td>");

                        out.println("<td>");
                        out.println("</form>");
                        out.println("<form action=\"admin.jsp\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"reservations\">");
                        out.println("<input type=\"hidden\" name=\"formAction\" value=\"\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditResID\" value=\""+ reservationID + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditCustID\" value=\""+ customerID + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditPrice\" value=\""+ price + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditStart\" value=\""+ startDate + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditEnd\" value=\""+ endDate + "\">");
                        out.println("<input type=\"hidden\" name=\"beforeEditRoomNum\" value=\""+ roomNumber + "\">");
                        out.println("<input style=\"background-color: #FFFF00;\" class=\"Editbtn\" type=\"submit\" value=\"Edit\">");
                        out.println("</form>");
                        out.println("</td>");

                        out.println("</tr>");
                    }

                    out.println("<tr>");
                    out.println("<form action=\"admin.jsp\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"reservations\">");
                    out.println("<input type=\"hidden\" name=\"formAction\" value=\"adding\">");
                    out.println("<input type=\"hidden\" name=\"oldResID\" value=\"blank\">");
                    out.println("<td><input type=\"number\" name=\"newResID\"></td>");
                    out.println("<td><input type=\"number\" name=\"newCustID\"></td>");
                    out.println("<td><input type=\"number\" name=\"newPrice\"></td>");
                    out.println("<td><input type=\"date\" name=\"newStart\"></td>");
                    out.println("<td><input type=\"date\" name=\"newEnd\"></td>");
                    out.println("<td><input type=\"number\" name=\"newRoomNum\"></td>");
                    out.println("<td><input style=\"background-color: #33ff33;\" name=\"editORadd\"class=\"Addbtn\" type=\"submit\" value=\"Add\"></td>");
                    out.println("</table></div>");
                    
                    out.println("<script>");
                    out.println("const editButtons = document.querySelectorAll('.Editbtn');");
                    out.println("editButtons.forEach(button => {");
                    out.println("button.addEventListener('click', () => {");
                    out.println("const row = button.parentElement.parentElement.parentElement;");
                    out.println("const selectedResID = row.children[0].textContent;");
                    out.println("const selectedCustID= row.children[1].textContent;");
                    out.println("const selectedPrice = row.children[2].textContent;");
                    out.println("const selectedStart = row.children[3].textContent;");
                    out.println("const selectedEnd = row.children[4].textContent;");
                    out.println("const selectedRoomNumber = row.children[5].textContent;");
                    out.println("document.querySelector('input[name=\"newResID\"]').value = selectedResID;");
                    out.println("document.querySelector('input[name=\"newCustID\"]').value = selectedCustID;");
                    out.println("document.querySelector('input[name=\"newPrice\"]').value = selectedPrice;");
                    out.println("document.querySelector('input[name=\"newStart\"]').checked = selectedStart;");
                    out.println("document.querySelector('input[name=\"newEnd\"]').checked = selectedEnd;");
                    out.println("document.querySelector('input[name=\"newRoomNum\"]').value = selectedRoomNumber;");
                    out.println("document.querySelector('input[name=\"oldResID\"]').value = selectedResID;");
                    out.println("document.querySelector('.Addbtn').value = \"Edit\";");
                    out.println("document.querySelector('.Addbtn').style.backgroundColor = 'yellow';");
                    out.println("event.preventDefault();");
                    out.println("});");
                    out.println("});");
                    out.println("</script>");
                    
                    String reservationPostingForm = request.getParameter("formAction");
                    if(reservationPostingForm != null){
                        if(reservationPostingForm.equals("deleting")){
                            int resID = Integer.parseInt(request.getParameter("reservationID"));
                            reservationController.removeReservation(resID);
                            session.setAttribute("buttonSelected","reservations");
                            response.sendRedirect(mainController.getLocation("admin"));
                        }
                        else if(reservationPostingForm.equals("adding")){
                            try{
                                String action = request.getParameter("editORadd");
                                int newReservationID = Integer.parseInt(request.getParameter("newResID"));
                                int newCustomerID = Integer.parseInt(request.getParameter("newCustID"));
                                double newPrice = Double.parseDouble(request.getParameter("newPrice"));
                                String newStartDate = request.getParameter("newStart");
                                String newEndDate = request.getParameter("newEnd");
                                int newRoomNumber = Integer.parseInt(request.getParameter("newRoomNum"));
                                int oldResID = Integer.parseInt(request.getParameter("oldResID"));
                                
                                if(action.equals("Add")){
                                    Reservation newReservation = new Reservation(newCustomerID,newPrice,newStartDate,newEndDate,newRoomNumber,newReservationID);
                                    reservationController.addReservation(newReservation);
                                    session.setAttribute("buttonSelected","reservations");
                                    response.sendRedirect(mainController.getLocation("admin"));
                                }
                                if(action.equals("Edit")){
                                    Reservation editedReservation = new Reservation(newCustomerID,newPrice,newStartDate,newEndDate,newRoomNumber,newReservationID);
                                    reservationController.removeReservation(oldResID);
                                    reservationController.addReservation(editedReservation);
                                    session.setAttribute("buttonSelected","reservations");
                                    response.sendRedirect(mainController.getLocation("admin"));
                                }
                                
                            } catch (Exception e){
                                response.sendRedirect(mainController.getLocation("admin"));
                            }
                        }
                    }
                }
                else if(buttonClicked.equals("layout")){
                    
                    out.println("<div class=\"box\"><h1>Layout</h1></div>");
                    int[][][] roomLayout = layoutDatabase.getRoomLayout();
                    // Display rooms in a 2D grid
                    for (int floor = 0; floor < roomLayout.length; floor++) {
                        out.println("<div class='box'><div class='floor'>Floor " + (floor + 1) + ": </div>");
                        for (int row = 0; row < roomLayout[floor].length; row++) {
                            out.println("<div class='room-grid'>");
                            for (int col = 0; col < roomLayout[floor][row].length; col++) {
                                int roomNumber = roomLayout[floor][row][col];
                                String roomClass = "available";
                                if (roomNumber != 0) {
                                    out.println("<div class='room " + roomClass + "'>" + roomNumber + "</div>");
                                } else {
                                    out.println("<div class='blankRoom'></div>");
                                }
                            }
                            out.println("</div>");
                        }
                        out.println("</div>");
                    }
                    
                    out.println("<form action='admin.jsp' method='post'>");
                    out.println("<input type=\"hidden\" name=\"formAction\" value=\"adding\">");
                    out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"layout\">");
                    out.println("<input style=\"background-color: #33ff33;\" name=\"addFloor\"class=\"Addbtn\" type=\"submit\" value=\"Add Floor to Top\">");
                    out.println("</form>");
                    out.println("<form action='admin.jsp' method='post'>");
                    out.println("<input type=\"hidden\" name=\"formAction\" value=\"deleting\">");
                    out.println("<input type=\"hidden\" name=\"buttonSelected\" value=\"layout\">");
                    out.println("<input style=\"background-color: #ff5733;\" class=\"Deletebtn\" type=\"submit\" value=\"Delete Top Floor\">");
                    out.println("</form>");

                    String layoutPostingForm = request.getParameter("formAction");
                    if(layoutPostingForm != null){
                        if(layoutPostingForm.equals("adding")) {
                            try {
                                layoutController.addFloor();
                                session.setAttribute("buttonSelected","layout");
                                response.sendRedirect(mainController.getLocation("admin"));
                            } catch (Exception e) {
                                response.sendRedirect(mainController.getLocation("admin"));
                            }
                        }
                        else if(layoutPostingForm.equals("deleting")){
                            layoutController.removeFloor();
                            session.setAttribute("buttonSelected","layout");
                            response.sendRedirect(mainController.getLocation("admin"));
                        }
                    }
                }
            } else {
                out.println("<div class\"box\" style=\"background-color: red;\">You do not have Admin Access</div>");
                out.println("<div class=\"box\" style=\"background-color: #444;\"><h1><a href=\"./home.jsp\">GO BACK</a></h1>"); 
            }
        %>
    </div>
</body>
</html>