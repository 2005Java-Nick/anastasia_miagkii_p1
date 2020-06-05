<%@page import="com.revature.trms.servlet.Login"%> 
<%@page import="com.revature.trms.dao.ValidateDAO"%> 

<% String name1 = (String)session.getAttribute("userName"); %>
<% String fullname = ValidateDAO.getEmployeeName(name1); %>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title><%= name1%>'s page</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='styles/main.css'>
    <link rel="shortcut icon" href="resources/favicon.ico" type="image/x-icon" />
    <link rel="icon" href="resources/favicon.ico" type="image/ico" />
</head>

<body>
    <form action= "welcome" method="post"> 



   <!--  <form method="post" action="Welcome"> -->
    <header class="top">
        <a href="home.html">
            <img src="resources/revature.jpg" alt="Revature" style="width:175px;height:75px;">
        </a>
    </header>


    <div class="menu-content">
    
        <h2>Welcome, <%= fullname%>!</h2>

        <p>What would you like to do?</p>

        <p><a href="apply.html" class="button">APPLY</a></p>
    
         <p><a href="viewforms.jsp" class="button">VIEW MY FORM</a></p> 
     
        <p><a href="home.html" class="button">LOG OUT</a></p>


    </div>

    <script src="scripts/main.js" type="text/javascript"></script>
</form>
</body>

</html>