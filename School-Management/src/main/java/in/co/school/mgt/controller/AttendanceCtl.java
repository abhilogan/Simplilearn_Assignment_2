package in.co.school.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.AttendanceBean;
import in.co.school.mgt.bean.BaseBean;
import in.co.school.mgt.bean.ClassBean;
import in.co.school.mgt.bean.StudentBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.model.AttendanceModel;
import in.co.school.mgt.model.ClassModel;
import in.co.school.mgt.model.StudentModel;
import in.co.school.mgt.util.DataUtility;
import in.co.school.mgt.util.DataValidator;
import in.co.school.mgt.util.PropertyReader;
import in.co.school.mgt.util.ServletUtility;

/**
 * Servlet implementation class AttendanceCtl
 */
@WebServlet(name = "AttendanceCtl", urlPatterns = { "/ctl/attendance" })
public class AttendanceCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(AttendanceCtl.class);
    
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("AttendanceCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("code"))) {
            request.setAttribute("code",
                    PropertyReader.getValue("error.require", "schoolCode"));
            pass = false;
        }

        if ("-----Select-----".equalsIgnoreCase(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "Status"));
			pass = false;
		}

        log.debug("AttendanceCtl validate method end");
        return pass;
    }
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("AttendanceCtl populateBean method start");
		AttendanceBean bean=new AttendanceBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setSchoolCode(DataUtility.getLong(request.getParameter("code")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		populateDTO(bean, request);
		log.debug("AttendanceCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AttendanceCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       AttendanceModel model = new AttendanceModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            AttendanceBean bean;
	            try {
	                bean = model.findByPK(id);
	                ServletUtility.setOpration("Edit", request);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }

	        ServletUtility.forward(getView(), request, response);
	        log.debug("AttendanceCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AttendanceCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		AttendanceModel model=new AttendanceModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			AttendanceBean bean=(AttendanceBean)populateBean(request);
			
			StudentModel sModel=new StudentModel();
			ClassModel cModel=new ClassModel();
			
			
			
			
				try {
					
					StudentBean sBean= sModel.findBySchoolCode(bean.getSchoolCode());
					if(sBean!=null) {
					ClassBean cBean=cModel.findByName(sBean.getClassName());
					bean.setStudentId(sBean.getId());
					bean.setName(sBean.getName());
					bean.setClassId(cBean.getId());
					bean.setClassName(cBean.getName());
					if(id>0){
						
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					}
					
					}else {
						ServletUtility.setErrorMessage("School Code Is Inccorect", request);
						ServletUtility.forward(getView(), request, response);
					}
				  
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(SCMView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(),
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
		AttendanceBean bean=	(AttendanceBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(SCMView.ATTENDANCE_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SCMView.ATTENDANCE_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(SCMView.ATTENDANCE_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("AttendanceCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SCMView.ATTENDANCE_VIEW;
	}

}
