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
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.model.ClassModel;
import in.co.school.mgt.util.DataUtility;
import in.co.school.mgt.util.DataValidator;
import in.co.school.mgt.util.PropertyReader;
import in.co.school.mgt.util.ServletUtility;

/**
 * Servlet implementation class ClassCtl
 */
@WebServlet(name = "ClassCtl", urlPatterns = { "/ctl/class" })
public class ClassCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log=Logger.getLogger(ClassCtl.class);
       
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("ClassCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description",
                    PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }

        log.debug("ClassCtl validate method end");
        return pass;
    }
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("ClassCtl populateBean method start");
		ClassBean bean=new ClassBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		log.debug("ClassCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ClassCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       ClassModel model = new ClassModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            ClassBean bean;
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
	        log.debug("ClassCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("ClassCtl doPost method start");
			String op=DataUtility.getString(request.getParameter("operation"));
			ClassModel model=new ClassModel();
			long id=DataUtility.getLong(request.getParameter("id"));
			if(OP_SAVE.equalsIgnoreCase(op)){
				
				ClassBean bean=(ClassBean)populateBean(request);
					try {
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
			ClassBean bean=	(ClassBean)populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(SCMView.CLASS_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
			}else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility.redirect(SCMView.CLASS_LIST_CTL, request, response);
				return;
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SCMView.CLASS_CTL, request, response);
			return;
	}
					
			
			ServletUtility.forward(getView(), request, response);
			 log.debug("ClassCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SCMView.CLASS_VIEW;
	}

}
