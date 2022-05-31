<%@page import="in.co.school.mgt.bean.StudentBean"%>
<%@page import="in.co.school.mgt.model.StudentModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.school.mgt.bean.GenrateMarksheetBean"%>
<%@page import="in.co.school.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marit List</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<br>

	<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">

		<li class="breadcrumb-item active" aria-current="page">Marit
			List</li>
	</ol>
	</nav>
	<form action="<%=SCMView.ATTENDANCE_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">

						<h3 style="margin-bottom: 15px; text-align: left;">Marit 
							List</h3>
						
					</div>
				</div>
			</div>
		</div>
		<center>
			<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
		</center>

		<table class="table">
			<thead class="thead-dark">
				<tr>
				
					<th scope="col">S.No</th>
					<th scope="col">Profile</th>
					<th scope="col">Name</th>
					<th scope="col">Roll No</th>
					<th scope="col">Class</th>
					<th scope="col">School Code</th>
					<th scope="col">Total</th>
					<th scope="col">Total Creadit</th>
					<th scope="col">Percentage</th>
				</tr>
			</thead>
			<tbody>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					GenrateMarksheetBean bean = null;

					List list = ServletUtility.getList(request);

					Iterator<GenrateMarksheetBean> it = list.iterator();

					while (it.hasNext()) {
						bean = it.next();
				%>
				<tr>

					
					<td><%=index++%></td>
					<% StudentModel sModel=new StudentModel();
						StudentBean sBean=sModel.findByPK(bean.getStudentId());
					%>
					<td><img width="100px" height="100px"
						src="<%=SCMView.APP_CONTEXT%>/image/<%=sBean.getProfilePic()%>"></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getClassName()%></td>
					<td><%=bean.getSchoolCode()%></td>
					<td><%=bean.getTotal()%></td>
					<td><%=bean.getTotalCreadit()%></td>
					<td><%=bean.getCGPA()%>&nbsp;%</td>
				</tr>

				<%
					}
				%>
			</tbody>
		</table>
		<hr>
		
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	<br>
	<%@ include file="Footer.jsp"%>
</body>
</html>