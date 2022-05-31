<%@page import="in.co.school.mgt.controller.SubjectCtl"%>
<%@page import="in.co.school.mgt.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.school.mgt.util.ServletUtility"%>
<%@page import="in.co.school.mgt.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subject</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Add Subject</li>
	</ol>
	</nav>
	
	
<div col-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-5">
					<div class="form-area">
						<form role="form" action="<%=SCMView.SUBJECT_CTL%>" method="post" >
							<br style="clear: both">
		
							<jsp:useBean id="bean" class="in.co.school.mgt.bean.SubjectBean"
         					   scope="request"></jsp:useBean>
         			  
             			 
             	<input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            
							<h3 style="margin-bottom: 15px; text-align: left: ;">Add Subject</h3>
							
								<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
							
							 <% List list=(List)request.getAttribute("classList"); %>
               				 <div class="form-group">
								<%=HTMLUtility.getList("classId",String.valueOf(bean.getClassName()), list)%>
									<font  color="red"><%=ServletUtility.getErrorMessage("classId", request)%></font>
							</div>
							<div class="form-group">
								<input type="text" class="form-control"  name="name"
									placeholder="Name" value="<%=DataUtility.getStringData(bean.getName())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
							</div>
							
								<div class="form-group">
                   				 <textarea class="form-control" type="textarea" name="description" placeholder="Description"  rows="3"><%=DataUtility.getStringData(bean.getDescription())%></textarea>
                                           <font  color="red"><%=ServletUtility.getErrorMessage("description", request)%></font>
                  			  </div>
                  			  
							
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=SubjectCtl.OP_SAVE %>">
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=SubjectCtl.OP_RESET %>">
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--feedback-->
		<br>
		<div style="margin-top: 103px">
		<%@ include file="Footer.jsp"%>
		</div>
</body>
</html>