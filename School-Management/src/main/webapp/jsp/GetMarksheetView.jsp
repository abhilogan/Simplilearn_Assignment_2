<%@page import="in.co.school.mgt.controller.GetMarksheetCtl"%>
<%@page import="in.co.school.mgt.util.ServletUtility"%>
<%@page import="in.co.school.mgt.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Marksheet</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Get Marksheet</li>
	</ol>
	</nav>
	
	
<div col-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-5">
					<div class="form-area">
						<form role="form" action="<%=SCMView.GET_MARKSHEET_CTL%>" method="post" >
							<br style="clear: both">
		
							<jsp:useBean id="bean" class="in.co.school.mgt.bean.GenrateMarksheetBean"
         					   scope="request"></jsp:useBean>
         			  
             			 
             <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            
							<h3 style="margin-bottom: 15px; text-align: left: ;">Get Marksheet</h3>
							
								<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
							
							
							<div class="form-group">
								<input type="text" class="form-control"  name="code"
									placeholder="School Code" value="<%=(bean.getSchoolCode()!=0)?bean.getSchoolCode():""%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("code", request)%></font>
							</div>
							
								
                  			  
							
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=GetMarksheetCtl.OP_GO%>">
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=GetMarksheetCtl.OP_RESET %>">
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--feedback-->
		<br>
		<div style="margin-top: 259px">
		<%@ include file="Footer.jsp"%>
		</div>
</body>
</html>