package in.co.school.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.BaseBean;
import in.co.school.mgt.bean.GenrateMarksheetBean;
import in.co.school.mgt.bean.StudentBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.model.GenrateMarksheetModel;
import in.co.school.mgt.model.StudentModel;
import in.co.school.mgt.util.DataUtility;
import in.co.school.mgt.util.DataValidator;
import in.co.school.mgt.util.PropertyReader;
import in.co.school.mgt.util.ServletUtility;

/**
 * Servlet implementation class GetMarksheetCtl
 */
@WebServlet(name="GetMarksheetCtl",urlPatterns={"/ctl/getMarksheet"})
public class GetMarksheetCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(GetMarksheetCtl.class);

	 /**
		 * Validate input Data Entered By User
		 * 
		 * @param request
		 * @return
		 */
	    @Override
	    protected boolean validate(HttpServletRequest request) {

	        log.debug("GetMarksheetCTL Method validate Started");

	        boolean pass = true;

	        if (DataValidator.isNull(request.getParameter("code"))) {
	            request.setAttribute("code",
	                    PropertyReader.getValue("error.require", "School Code"));
	            pass = false;
	        } 

	        log.debug("GetMarksheetCTL Method validate Ended");
	        System.out.println(pass);
	        return pass;
	    }
	    
	    @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("GetMarksheetCtl Method populatebean Started");

	        GenrateMarksheetBean bean = new GenrateMarksheetBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setSchoolCode(DataUtility.getLong(request.getParameter("code")));

	       

	        log.debug("GetMarksheetCtl Method populatebean Ended");

	        return bean;
	    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("GetMarksheetCtl Method doGet Started");
        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        GenrateMarksheetModel model=new GenrateMarksheetModel();
        
        HttpSession session=request.getSession();
        GenrateMarksheetBean bean = (GenrateMarksheetBean) populateBean(request);

        if (OP_GO.equalsIgnoreCase(op)) {
        	
            try {
            	StudentModel sModel=new StudentModel();
				StudentBean sBean=sModel.findBySchoolCode(bean.getSchoolCode());
                
				if (sBean != null) {
					session.setAttribute("stuBean", sBean);
					ServletUtility.forward(SCMView.MARKSHEET, request, response);
                } else {
                    ServletUtility.setErrorMessage("School Code  Does Not exists",
                            request);
                    ServletUtility.forward(getView(), request, response);
                    return;
                }
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        }
      
        log.debug("MarksheetCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SCMView.GET_MARKSHEET_VIEW;
	}

}
