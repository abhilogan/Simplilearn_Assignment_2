<%@page import="in.co.school.mgt.model.MarksheetModel"%>
<%@page import="in.co.school.mgt.bean.StudentBean"%>
<%@page import="in.co.school.mgt.model.StudentModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.school.mgt.bean.MarksheetBean"%>
<%@page import="in.co.school.mgt.controller.MarksheetListCtl"%>
<%@page import="in.co.school.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marksheet List View</title>
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

		<li class="breadcrumb-item active" aria-current="page">Marksheet
			List</li>
	</ol>
	</nav>
	<form action="<%=SCMView.MARKSHEET_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">

						<h3 style="margin-bottom: 15px; text-align: left;">Marksheet
							List</h3>
						<div class="form-row">
							<div class="form-group col-lg-4">
								<input type="text" class="form-control" name="name"
									placeholder="Name"
									value="<%=ServletUtility.getParameter("name", request)%>">
							</div>
							<div class="form-group col-lg-4">
								<input type="text" class="form-control" name="code"
									placeholder="School Code"
									value="<%=ServletUtility.getParameter("code", request)%>">
							</div>
							
							<div class="form-group col-lg-4">
								<input type="text" class="form-control" name="rollNo"
									placeholder="Roll No"
									value="<%=ServletUtility.getParameter("Roll No", request)%>">
							</div>
							
							<div class="form-group col-lg-4">
								<input type="submit" name="operation"
									class="btn btn-primary pull-right"
									value="<%=MarksheetListCtl.OP_SEARCH%>">&nbsp;or&nbsp; <input
									type="submit" name="operation"
									class="btn btn-primary pull-right"
									value="<%=MarksheetListCtl.OP_RESET%>">
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
					
					<th scope="col">S.No</th>
					
					<th scope="col">Name</th>
					<th scope="col">Class</th>
					<th scope="col">Roll No</th>
					<th scope="col">SchoolCode</th>
					<th scope="col">Subject</th>
					<th scope="col">Marks</th>
					<th scope="col">Grade</th>
					<th scope="col">Result</th>
				</tr>
			</thead>
			<tbody>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					MarksheetBean bean = null;

					List list = ServletUtility.getList(request);

					Iterator<MarksheetBean> it = list.iterator();

					while (it.hasNext()) {
						bean = it.next();
				%>
				<tr>

					
					<td><%=index++%></td>
					
					
					<td><%=bean.getStudentName()%></td>
					<td><%=bean.getClassName()%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getSchoolCode()%></td>
					<td><%=bean.getSubjectName()%></td>
					<td><%=bean.getMark()+""%><font color="red"><%=(bean.getMark()<33)?"*":""%></font></td>
					<td><%=bean.getGrade()%></td>
					<td><%=bean.getResult()%></td>
			
					
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
					value="<%=MarksheetListCtl.OP_PREVIOUS%>"
					<%=(pageNo == 1) ? "disabled" : ""%>></td>
					
					
				
				<%
					MarksheetModel model = new MarksheetModel();
				%>
				<td align="right"><input type="submit" name="operation"
					class="btn btn-primary pull-right" value="<%=MarksheetListCtl.OP_NEXT%>"
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