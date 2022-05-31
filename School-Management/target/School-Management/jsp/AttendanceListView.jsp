<%@page import="in.co.school.mgt.bean.StudentBean"%>
<%@page import="in.co.school.mgt.model.StudentModel"%>
<%@page import="in.co.school.mgt.model.AttendanceModel"%>
<%@page import="in.co.school.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.school.mgt.bean.AttendanceBean"%>
<%@page import="in.co.school.mgt.controller.AttendanceListCtl"%>
<%@page import="in.co.school.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attendance List</title>
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

		<li class="breadcrumb-item active" aria-current="page">Attendance
			List</li>
	</ol>
	</nav>
	<form action="<%=SCMView.ATTENDANCE_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">

						<h3 style="margin-bottom: 15px; text-align: left;">Attendance
							List</h3>
							<%if(userBean.getRoleId()!=4){ %>
						<div class="form-row">
							<div class="form-group col-lg-4">
								<input type="text" class="form-control" name="name"
									placeholder="Name"
									value="<%=ServletUtility.getParameter("name", request)%>">
							</div>
							<div class="form-group col-lg-4">
								<input type="text" class="form-control" name="className"
									placeholder="Class Name"
									value="<%=ServletUtility.getParameter("className", request)%>">
							</div>
							
							<div class="form-group col-lg-4">
								<input type="submit" name="operation"
									class="btn btn-primary pull-right"
									value="<%=AttendanceListCtl.OP_SEARCH%>">&nbsp;or&nbsp; <input
									type="submit" name="operation"
									class="btn btn-primary pull-right"
									value="<%=AttendanceListCtl.OP_RESET%>">
							</div>
						</div>
						<%} %>
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
					<%if(userBean.getRoleId()==2){ %>
					<th scope="col">Select</th>
				<%} %>
					<th scope="col">S.No</th>
					<th scope="col">Profile</th>
					<th scope="col">Name</th>
					<th scope="col">Class</th>
					<th scope="col">Date</th>
					<th scope="col">Status</th>
					<%if(userBean.getRoleId()==2){ %>
					<th scope="col">Edit</th>
					<%} %>
				</tr>
			</thead>
			<tbody>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					AttendanceBean bean = null;

					List list = ServletUtility.getList(request);

					Iterator<AttendanceBean> it = list.iterator();

					while (it.hasNext()) {
						bean = it.next();
				%>
				<tr>
					<%if(userBean.getRoleId()==2){ %>
					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getId()%>"></td>
						<%} %>
					<td><%=index++%></td>
					<% StudentModel sModel=new StudentModel();
						StudentBean sBean=sModel.findByPK(bean.getStudentId());
					%>
					<td><img width="100px" height="100px"
						src="<%=SCMView.APP_CONTEXT%>/image/<%=sBean.getProfilePic()%>"></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getClassName()%></td>
					<td><%=DataUtility.getDateString(bean.getDate())%></td>
					<td><%=bean.getStatus()%></td>	

				<%if(userBean.getRoleId()==2){ %>
					<td><a class="btn btn-primary pull-right"
						href="attendance?id=<%=bean.getId()%>">Edit</a></td>
				<%} %>
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
					value="<%=AttendanceListCtl.OP_PREVIOUS%>"
					<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<%if(userBean.getRoleId()==2){ %>
					<td><input type="submit" name="operation"
					class="btn btn-primary pull-right"
					value="<%=AttendanceListCtl.OP_NEW%>"
					></td>

				<td><input type="submit" name="operation"
					class="btn btn-danger pull-right"
					value="<%=AttendanceListCtl.OP_DELETE%>"
					<%=(list.size() == 0) ? "disabled" : ""%>></td>
					<%} %>
				<%
					AttendanceModel model = new AttendanceModel();
				%>
				<td align="right"><input type="submit" name="operation"
					class="btn btn-primary pull-right" value="<%=AttendanceListCtl.OP_NEXT%>"
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