<%@page import="in.co.school.mgt.controller.StudentCtl"%>
<%@page import="in.co.school.mgt.model.StudentModel"%>
<%@page import="in.co.school.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.school.mgt.bean.StudentBean"%>
<%@page import="in.co.school.mgt.controller.StudentListCtl"%>
<%@page import="in.co.school.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student List</title>
<script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<br>

	<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">

		<li class="breadcrumb-item active" aria-current="page">Student
			List</li>
	</ol>
	</nav>
	<form action="<%=SCMView.STUDENT_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">

						<h3 style="margin-bottom: 15px; text-align: left;">Student
							List</h3>
						<div class="form-row">
							<div class="form-group col-lg-4">
								<input type="text" class="form-control" name="name"
									placeholder="Name"
									value="<%=ServletUtility.getParameter("name", request)%>">
							</div>
							<div class="form-group col-lg-4">
								<input type="text" class="form-control" name="login"
									placeholder="Login Id"
									value="<%=ServletUtility.getParameter("login", request)%>">
							</div>
							<div class="form-group col-lg-4">
								<input type="submit" name="operation"
									class="btn btn-primary pull-right"
									value="<%=StudentListCtl.OP_SEARCH%>">&nbsp;or&nbsp; <input
									type="submit" name="operation"
									class="btn btn-primary pull-right"
									value="<%=StudentListCtl.OP_RESET%>">
							</div>
						</div>
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
					<th scope="col">Select</th>
					<th scope="col">S.No</th>
					<th scope="col">Profile</th>
					<th scope="col">Name</th>
					<th scope="col">Father Name</th>
					<th scope="col">Login</th>
					<th scope="col">School Code</th>
					<th scope="col">Class</th>
					<th scope="col">MobileNo</th>
					<th scope="col">DOB</th>
					<th scope="col">Blood Group</th>
					<th scope="col">Gender</th>
					<th scope="col">Address</th>
					<th scope="col">Edit</th>
				</tr>
			</thead>
			<tbody>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					StudentBean bean = null;

					List list = ServletUtility.getList(request);

					Iterator<StudentBean> it = list.iterator();

					while (it.hasNext()) {
						bean = it.next();
				%>
				<tr>

					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><img width="100px" height="100px"
						src="<%=SCMView.APP_CONTEXT%>/image/<%=bean.getProfilePic()%>"></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getFatherName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getSchoolcode()%></td>
					<td><%=bean.getClassName()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=DataUtility.getDateString(bean.getDob())%></td>
					<td><%=bean.getBloodGroup()%></td>
					<td><%=bean.getGender()%></td>


					<td><%=bean.getAddress()%></td>
					<td><a class="btn btn-primary pull-right"
						href="student?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
					}
				%>
			</tbody>
		</table>
		<hr>
		<table width="99%" style="bottom: 45px">
			<tr>

				<td><input type="submit" name="operation"
					class="btn btn-primary pull-right"
					value="<%=StudentListCtl.OP_PREVIOUS%>"
					<%=(pageNo == 1) ? "disabled" : ""%>></td>

				<td><input type="submit" name="operation"
					class="btn btn-danger pull-right"
					value="<%=StudentListCtl.OP_DELETE%>"
					<%=(list.size() == 0) ? "disabled" : ""%>></td>
				<%
					StudentModel model = new StudentModel();
				%>
				<td align="right"><input type="submit" name="operation"
					class="btn btn-primary pull-right" value="<%=StudentListCtl.OP_NEXT%>"
					<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></td>
			</tr>
		</table>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	<br>
	<%@ include file="Footer.jsp"%>
</body>
</html>