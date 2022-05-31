<%@page import="in.co.school.mgt.controller.LoginCtl"%>
<%@page import="in.co.school.mgt.bean.UserBean"%>
<%@page import="in.co.school.mgt.controller.SCMView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=SCMView.APP_CONTEXT%>/css/bootstrap.min.css" >
<script src="<%=SCMView.APP_CONTEXT%>/js/jquery-3.3.1.slim.min.js" ></script>
<script src="<%=SCMView.APP_CONTEXT%>/js/popper.min.js" ></script>
<script src="<%=SCMView.APP_CONTEXT%>/js/bootstrap.min.js" ></script>

</head>
<body>

 <%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>

<nav class="navbar navbar-expand-lg navbar-light " style="background-color: #e3f2fd;">
  <a class="navbar-brand" href="#">Learner's Academy</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    
   		<li class="nav-item active">
        <a class="nav-link" href="<%=SCMView.WELCOME_CTL%>">Home <span class="sr-only">(current)</span></a>
      	</li>
      	<%if(userLoggedIn){%>
      	<%if(userBean.getRoleId()==1){ %>
       <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.USER_LIST_CTL%>">UserList</a>
      </li>
      
          <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Class
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.CLASS_CTL%>">Add Class</a>
          <a class="dropdown-item" href="<%=SCMView.CLASS_LIST_CTL%>">Class List</a>
        
        </div>
      </li>
      
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Student
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.STUDENT_CTL%>">Add Student</a>
          <a class="dropdown-item" href="<%=SCMView.STUDENT_LIST_CTL%>">Student List</a>
        
        </div>
      </li>
      
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Faculty
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.FACULTY_CTL%>">Add Faculty</a>
          <a class="dropdown-item" href="<%=SCMView.FACULTY_LIST_CTL%>">Faculty List</a>
        
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Accountant
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.ACCOUNTANT_CTL%>">Add Accountant</a>
          <a class="dropdown-item" href="<%=SCMView.ACCOUNTANT_LIST_CTL%>">Accountant List</a>
        
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Subject
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.SUBJECT_CTL%>">Add Subject</a>
          <a class="dropdown-item" href="<%=SCMView.SUBJECT_LIST_CTL%>">Subject List</a>
        
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.ATTENDANCE_LIST_CTL%>">Attendance List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.FEE_LIST_CTL%>">Fee List</a>
      </li>
     
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Marksheet
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.GET_MARKSHEET_CTL%>">Get Marksheet</a>
          <a class="dropdown-item" href="<%=SCMView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
       	 <a class="dropdown-item" href="<%=SCMView.MARKSHEET_MERIT_LIST_CTL%>">Marit List</a>
        </div>
      </li>
      
      <%}else if(userBean.getRoleId()==2){%>
      
       <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Marksheet
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.MARKSHEET_CTL%>">Add Marksheet</a>
          <a class="dropdown-item" href="<%=SCMView.SUBJECT_LIST_CTL%>">Subject List</a>
        </div>
      </li>
      
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Attendance
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.ATTENDANCE_CTL%>">Add Attendance</a>
          <a class="dropdown-item" href="<%=SCMView.ATTENDANCE_LIST_CTL%>">Attendance List</a>
        </div>
      </li>
      
      
      <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.CLASS_LIST_CTL%>">Class List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.FEE_LIST_CTL%>">Fee List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.GET_MARKSHEET_CTL%>">Get Marksheet</a>
      </li>
        <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.MARKSHEET_MERIT_LIST_CTL%>">Marit List</a>
      </li>
      
      <%}else if(userBean.getRoleId()==3){ %>
      
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Fee
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.FEE_CTL%>">Add Fee</a>
          <a class="dropdown-item" href="<%=SCMView.FEE_LIST_CTL%>">Fee List</a>
        </div>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.STUDENT_CTL%>">Student List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.ATTENDANCE_LIST_CTL%>">Attendance List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.GET_MARKSHEET_CTL%>">Get Marksheet</a>
      </li>
        <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.MARKSHEET_MERIT_LIST_CTL%>">Marit List</a>
      </li>
      
      <%}else if(userBean.getRoleId()==4){ %>
       
      <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.ATTENDANCE_LIST_CTL%>">Attendance List</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.FEE_LIST_CTL%>">Fee List</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.GET_MARKSHEET_CTL%>">Get Marksheet</a>
      </li>
        <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=SCMView.MARKSHEET_MERIT_LIST_CTL%>">Marit List</a>
      </li>
      <%} %>
      <%} %>
    </ul>
    <form class="form-inline my-2 my-lg-0">
     <ul class="navbar-nav mr-auto">
     <%if (userLoggedIn){%>
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.MY_PROFILE_CTL%>">My Profile</a>
          <a class="dropdown-item" href="<%=SCMView.CHANGE_PASSWORD_CTL%>">Change Password</a>
          <a class="dropdown-item" href="<%=SCMView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
        </div>
      </li>
      <%}else { %>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=SCMView.LOGIN_CTL%>">SignIn</a>
          <a class="dropdown-item" href="<%=SCMView.USER_REGISTRATION_CTL%>">SignUp</a>
          
        </div>
      </li>
      <%} %>
     </ul>
      
    </form>
  </div>
</nav>
</body>
</html>