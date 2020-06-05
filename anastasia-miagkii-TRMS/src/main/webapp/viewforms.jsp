<%@page import="com.revature.trms.servlet.Login"%> 
<%@page import="com.revature.trms.dao.ValidateDAO"%> 
<%@page import="com.revature.trms.model.Employee"%> 
<%@page import="com.revature.trms.dao.EmployeeDAO"%> 

<% String name1 = (String)session.getAttribute("userName"); %>
<% String fullname = ValidateDAO.getEmployeeName(name1); %>
<!DOCTYPE html>
<html>
<head>




<meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title><%= name1%>'s form</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='styles/form.css'>
    <link rel="shortcut icon" href="resources/favicon.ico" type="image/x-icon" />
    <link rel="icon" href="resources/favicon.ico" type="image/ico" />
</head>

<body>
    <header class="top">
        <a href="home.html">
            <img src="resources/revature.jpg" alt="Revature" style="width:175px;height:75px;">
        </a>
    </header>
    <form action= "/TRMS/viewform" method="post"> 

    <div class="menu-content">
    
        <h2>Form of <%= fullname%>:</h2>
        

        <br><center> <table border = "5">
        <tr> 
        <th>Event Type</th>
        <th>Event Location</th>
        <th>Examination Date</th>
        <th>Event Start Date</th>
        <th>Event Cost</th>
        <th>Grade Criteria</th>
        <th>Passing Grade</th>
        <th>Status</th> 
        </tr>
        

				    
				    <%=EmployeeDAO.viewEmployeeForm(name1) %>
 

        </center>
      <p></br><a href="welcome.jsp" class="button">GO BACK</a></p> 
							
       </div>
        
        </form> 
    
    </body>
       
        </html>