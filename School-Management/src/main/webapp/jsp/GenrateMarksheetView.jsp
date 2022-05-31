<%@page import="in.co.school.mgt.controller.MarksheetCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.school.mgt.bean.SubjectBean"%>
<%@page import="in.co.school.mgt.bean.StudentBean"%>
<%@page import="in.co.school.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Genrate Marksheet</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Genrate Marksheet</li>
	</ol>
	</nav>
<form action="<%=SCMView.MARKSHEET_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">

						<h3 style="margin-bottom: 15px; text-align: left;">Genrate Marksheet
							</h3>
					</div>
				</div>
			</div>
		</div>
		<center>
			<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
		</center>
			<%  StudentBean sBean=(StudentBean)session.getAttribute("stuBean"); %>
		
		<table align="center" width="85%" border="1">
		<tr>
			<th colspan="2">Name</th>
			<td><%=sBean.getName()%></td>
			<th >Father Name</th>
			<td><%=sBean.getFatherName()%></td>
			<td width="100px"><img height="100px" width="100%" src="<%=SCMView.APP_CONTEXT%>/image/<%=sBean.getProfilePic()%>"></td>
			
		</tr>
		<tr>
			<th>Roll No</th>
			<td><input type="text" class="form-control" name="rollNo" placeholder="Enter Roll Number" ></td>
			<th>School Code</th>
			<td><%=sBean.getSchoolcode()%></td>
			<th>Class		</th>
			<td><%=sBean.getClassName()%></td>
		</tr>

		</table>
		<br>
		<table align="center" width="85%" border="1">
		<tr>
			<th>S No.</th>
			<th>Subject Name</th>
			<th>total</th>
			<th>Minimum Mark</th>
			<th>Obtaind</th>
			<th>Grade</th>
			
		</tr>
		<%
					
					int index =  1;
					SubjectBean bean = null;

					List list = ServletUtility.getList(request);

					Iterator<SubjectBean> it = list.iterator();

					while (it.hasNext()) {
						bean = it.next();
						int in=index++;
		%>
		
		<tr>
			<td><%=in%></td>
			<td><%=bean.getName()%></td>
			<td>100</td>
			<td>33</td>
			<td><input type="text" class="form-control" placeholder="Enter Marks" name="mark<%=in%>"></td>
			<td>--</td>		
		</tr>
		<%} %>
		<tr>
		
		</tr>

		</table>
		
		
		<hr>
		<table width="99%" style="bottom: 45px">
			<tr>

				
					
					<td><input type="submit" name="operation"
					class="btn btn-primary pull" style="align-items: center;"
					value="<%=MarksheetCtl.OP_GENRATE%>"
					></td>

				
				
			</tr>
		</table>
	</form>
	<br>
<%@ include file="Footer.jsp" %>
</body>
</html>