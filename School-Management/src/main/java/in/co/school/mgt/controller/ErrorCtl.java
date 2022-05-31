package in.co.school.mgt.controller;

/**
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 */


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.school.mgt.util.ServletUtility;


/**
 * Servlet implementation class ErrorCtl
 */
@WebServlet(name = "ErrorCtl", urlPatterns = { "/ctl/error" })
public class ErrorCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * Contains Display logics
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in do get method ERRor Cl");
		ServletUtility.forward(getView(), request, response);
		return;
	}
	/**
	 * Contains Submit logics
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in do get method ERRor Cl");
		ServletUtility.forward(getView(), request, response);
		return;
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		
		return SCMView.ERROR_VIEW;
	}

}
