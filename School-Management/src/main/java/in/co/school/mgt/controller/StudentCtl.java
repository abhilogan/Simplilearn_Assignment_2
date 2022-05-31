package in.co.school.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import in.co.school.mgt.bean.BaseBean;
import in.co.school.mgt.bean.ClassBean;
import in.co.school.mgt.bean.StudentBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.model.ClassModel;
import in.co.school.mgt.model.StudentModel;
import in.co.school.mgt.util.DataUtility;
import in.co.school.mgt.util.DataValidator;
import in.co.school.mgt.util.PropertyReader;
import in.co.school.mgt.util.ServletUtility;

/**
 * Servlet implementation class StudentCtl
 */
@WebServlet(name = "StudentCtl", urlPatterns = { "/ctl/student" })
public class StudentCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(StudentCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("StudnetCtl preload method start");
		ClassModel model = new ClassModel();
		try {
			List l = model.list();
			request.setAttribute("classList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("StudentCtl preload method end");
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("StudentCtl Method validate Started");

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

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("classId"))) {
			request.setAttribute("classId", PropertyReader.getValue("error.require", "Class Name"));
			pass = false;
		}

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("fatherName"))) {
			request.setAttribute("fatherName",
					PropertyReader.getValue("error.require", "Father Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("bloodGroup"))) {
			request.setAttribute("bloodGroup",
					PropertyReader.getValue("error.require", "Blood Group"));
			pass = false;
		}

		log.debug("StudentCtl Method validate Ended");

		return pass;
	}
	
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("StudentCtl Method populatebean Started");

		StudentBean bean = new StudentBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request	.getParameter("name")));

		bean.setFatherName(DataUtility.getString(request.getParameter("fatherName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		
		bean.setClassId(DataUtility.getLong(request.getParameter("classId")));
		
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		
		bean.setBloodGroup(DataUtility.getString(request.getParameter("bloodGroup")));
		
		
		populateDTO(bean, request);

		log.debug("StudentCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("StudentCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		StudentModel model = new StudentModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			StudentBean bean;
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
        log.debug("StudentCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        StudentModel model = new StudentModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            StudentBean bean = (StudentBean) populateBean(request);
           
            
            try {
            	
            	ClassModel cModel=new ClassModel();
             	ClassBean cBean= cModel.findByPK(bean.getClassId());
            	 bean.setClassName(cBean.getName());
            	 
                if (id > 0) {
                	StudentBean uBean = model.findByPK(id);
               	 	bean.setProfilePic(uBean.getProfilePic());
               	 	bean.setSchoolcode(uBean.getSchoolcode());
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

            StudentBean bean = (StudentBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(SCMView.STUDENT_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(SCMView.STUDENT_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(SCMView.STUDENT_CTL, request, response);
    		return;
    }
    				
    		
        ServletUtility.forward(getView(), request, response);
        

        log.debug("UserCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SCMView.STUDENT_VIEW;
	}

}
