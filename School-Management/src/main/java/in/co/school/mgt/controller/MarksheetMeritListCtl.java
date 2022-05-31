package in.co.school.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.GenrateMarksheetBean;
import in.co.school.mgt.bean.MarksheetBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.model.GenrateMarksheetModel;
import in.co.school.mgt.model.MarksheetModel;
import in.co.school.mgt.util.DataUtility;
import in.co.school.mgt.util.PropertyReader;
import in.co.school.mgt.util.ServletUtility;



/**
 * Servlet implementation class MarksheetMeritListCtl
 */

/**
 * Marksheet Merit List functionality Controller. Performance operation of
 * Marksheet Merit List
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c)Navigable Set
 */
@ WebServlet(name="MarksheetMeritListCtl",urlPatterns={"/ctl/marksheetMeritList"})
public class MarksheetMeritListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
    
	 private static Logger log = Logger.getLogger(MarksheetMeritListCtl.class);
	 /**
		 * Populates bean object from request parameters
		 * 
		 * @param request
		 * @return
		 */
	    

	    /**
	     * Contains Display logics
	     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MarksheetMeritListCtl doGet Start");

        int pageNo = 1;

        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

        GenrateMarksheetBean bean = (GenrateMarksheetBean) populateBean(request);

        String op = DataUtility.getString(request.getParameter("operation"));

        GenrateMarksheetModel model = new GenrateMarksheetModel();
        List list = null;
        try {
            list = model.Meritlist();
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(SCMView.MARKSHEET_MERIT_LIST_VIEW, request,response);
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("MarksheetMeritListCtl doGet End");
    }

    /**
     * Contains Submit logics
     */
    @Override
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("MarksheetMeritListCtl doGet Start");
        
    	doGet(request, response);
        log.debug("MarksheetMeritListCtl doPost End");
	}	
    /**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
    @Override
    protected String getView() {
        return SCMView.MARKSHEET_MERIT_LIST_VIEW;
    }

}
