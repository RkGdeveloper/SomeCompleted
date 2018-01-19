<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AMS</title>
 <link rel="stylesheet" type="text/css" href="css/style.css">
 </head>
<body>


 <center>
        <%
            // Set refresh, autoload time as 60 seconds
            response.setIntHeader("Refresh", 10);
            // Get current time
            Calendar calendar = new GregorianCalendar();
            String am_pm;
            
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            
            if(calendar.get(Calendar.AM_PM) == 0)
               am_pm = "AM";
            else
               am_pm = "PM";
            String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
            out.println("Current Time: " + CT + "\n");
    %>
      </center>
  
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="#">AMS Payment</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
     <li>
     	<a class="nav-link" href="/AMS/CompletedJob">Completed Job</a>
     </li>
    </ul>
  </div>
</nav>
<hr/>
<br/>


<form action = "UpdateRecord" method ="get">

<div class="container">

<table class="table table-hover">
  <thead>
    <tr>
 	  <th>Move to In Completed</th>
      <th scope="col">ID</th>
      <th scope="col">Team</th>
      <th scope="col">JOB NAME</th>
      <th scope="col">Hr When it Runs</th>
    </tr>
  </thead>
  
  <tbody>
  <c:forEach items="${data}" var="record">
   <tr class="table-active">
   	
   	<td><input type="checkbox" name="records" value="${record.recId}|1|${record.hrWhenItsRun}|"/></td>
      <td>${record.recId}</td>
      <td>${record.team}</td>
      <td>${record.jobName}</td>
      <td>${record.hrWhenItsRun}</td>
    </tr>
  </c:forEach>
 </tbody>
</table>

</div>
<input class="btn btn-success" type="submit" value="Save"/>
</form>

</body>
</html>