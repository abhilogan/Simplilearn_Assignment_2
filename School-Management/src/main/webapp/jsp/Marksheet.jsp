<%@page import="in.co.school.mgt.model.GenrateMarksheetModel"%>
<%@page import="in.co.school.mgt.bean.GenrateMarksheetBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.school.mgt.bean.MarksheetBean"%>
<%@page import="in.co.school.mgt.model.MarksheetModel"%>
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
	<%@ include file="Header.jsp"%>
	<br>
	<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">

		<li class="breadcrumb-item active" aria-current="page">Genrate
			Marksheet</li>
	</ol>
	</nav>
	<form action="" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">

						<h3 style="margin-bottom: 15px; text-align: left;">Genrate
							Marksheet</h3>
					</div>
				</div>
			</div>
		</div>
		<center>
			<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
		</center>

		<%  StudentBean sBean=(StudentBean)session.getAttribute("stuBean"); %>
		
		<% GenrateMarksheetModel gModel=new GenrateMarksheetModel();
			GenrateMarksheetBean gBean=gModel.findByStudentId(sBean.getId());
		%>
		
		<table align="center" width="85%" border="1">
			<tr>
				<th colspan="2">Name</th>
				<td><%=sBean.getName()%></td>
				<th>Father Name</th>
				<td><%=sBean.getFatherName()%></td>
				<td width="100px"><img height="100px" width="100%"
					src="<%=SCMView.APP_CONTEXT%>/image/<%=sBean.getProfilePic()%>"></td>

			</tr>
			<tr>
				<th>Roll No</th>
				<td><%=gBean.getRollNo()%></td>
				<th>School Code</th>
				<td><%=sBean.getSchoolcode()%></td>
				<th>Class</th>
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
					MarksheetModel mModel=new MarksheetModel();
					MarksheetBean mBean=new MarksheetBean();
					mBean.setStudentId(sBean.getId());
					mBean.setClassName(sBean.getClassName());
					List list=mModel.search(mBean);
					int index=1;
					Iterator it=list.iterator();
					while(it.hasNext()){
						MarksheetBean bean=(MarksheetBean)it.next();
				%>
			<tr>
				<td><%=index++%></td>
				<td><%=bean.getSubjectName()%></td>
				<td>100</td>
				<td>33</td>
				<td><%=bean.getMark()%></td>
				<td><%=bean.getGrade()%></td>
			</tr>
			<%} %>
			
			<tr>
				<th colspan="2">Total</th>
				<td><%=gBean.getTotal()%></td>
				<td></td>
				<td><%=gBean.getTotalCreadit()%></td>
				<td></td>

			</tr>

		</table>
		<br>
		<table align="center" width="85%" border="1">
			<tr>
				<th>Result</th>
				<td>Pass</td>
				<th>CGPA</th>
				<td><%=gBean.getCGPA()%></td>
				
			</tr>
		</table>

		<hr>

	</form>
	<br>
	<%@ include file="Footer.jsp"%>
</body>
</html>