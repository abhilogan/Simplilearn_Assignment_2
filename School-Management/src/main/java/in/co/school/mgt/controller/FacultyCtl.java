package in.co.school.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.BaseBean;
import in.co.school.mgt.bean.ClassBean;
import in.co.school.mgt.bean.FacultyBean;
import in.co.school.mgt.bean.StudentBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.model.AccountantModel;
import in.co.school.mgt.model.ClassModel;
import in.co.school.mgt.model.FacultyModel;
import in.co.school.mgt.model.StudentModel;
import in.co.school.mgt.util.DataUtility;
import in.co.school.mgt.util.DataValidator;
import in.co.school.mgt.util.PropertyReader;
import in.co.school.mgt.util.ServletUtility;

/**
 * Servlet implementation class FacultyCtl
 */
@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/faculty" })
public class FacultyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(FacultyCtl.class);
       
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FacultyCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		
		String dob = request.getParameter("dob");
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.invalid", "Mobile No"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}

		

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}
		
		
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification",
					PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		}

		log.debug("FacultyCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("FacultyCtl Method populatebean Started");

		FacultyBean bean = new FacultyBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request	.getParameter("name")));


		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		
		
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		
		bean.setQualification(DataUtility.getString(request.getParameter("qualification")));
		
		
		populateDTO(bean, request);

		log.debug("FacultyCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FacultyCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		FacultyModel model = new FacultyModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			FacultyBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
                log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("FacultyCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FacultyCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        FacultyModel model = new FacultyModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            FacultyBean bean = (FacultyBean) populateBean(request);
           
            
            try {
            	
            	
            	 
                if (id > 0) {
                	FacultyBean uBean = model.findByPK(id);
               	 	bean.setProfilePic(uBean.getProfilePic());
               	 	bean.setSchoolCode(uBean.getSchoolCode());
                    model.update(bean);
                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
                } else {
                	
                    long pk = model.add(bean);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
                }

               
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage(e.getMessage(), request);
            }
            ServletUtility.forward(getView(), request, response);
        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            FacultyBean bean = (FacultyBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(SCMView.FACULTY_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(SCMView.FACULTY_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(SCMView.FACULTY_CTL, request, response);
    		return;
    }
    				
    		
        ServletUtility.forward(getView(), request, response);
        

        log.debug("FacultyCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SCMView.FACULTY_VIEW;
	}

}
