<%@page import="com.revature.trms.servlet.Login"%> 
<%@page import="com.revature.trms.dao.ValidateDAO"%> 
<!DOCTYPE html>
<html>
<head>
<%
	String name1 = (String) session.getAttribute("userName");
%>
<%
	String fullname = ValidateDAO.getEmployeeName(name1);
%>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Management Menu</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='styles/main.css'>
    <link rel="shortcut icon" href="resources/favicon.ico" type="image/x-icon" />
    <link rel="icon" href="resources/favicon.ico" type="image/ico" />
</head>

<body>
    <header class="top">
        <a href="home.html">
            <img src="resources/revature.jpg" alt="Revature" style="width:175px;height:75px;">
        </a>
    </header>


    <div class="menu-content">

	<br>
        <h2>Welcome, <%=fullname %></h2>
       <br>
        <p><a href="alldatabaseforms.jsp" class="button">VIEW ALL FORMS</a></p>
        <br>
        <p><a href="home.html" class="button">LOG OUT</a></p>

    </div>

    <script src="scripts/main.js" type="text/javascript"></script>
</body>

</html>