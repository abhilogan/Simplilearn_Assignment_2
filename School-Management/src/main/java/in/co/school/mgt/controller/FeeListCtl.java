package in.co.school.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.AccountantBean;
import in.co.school.mgt.bean.AttendanceBean;
import in.co.school.mgt.bean.BaseBean;
import in.co.school.mgt.bean.FeeBean;
import in.co.school.mgt.bean.StudentBean;
import in.co.school.mgt.bean.UserBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.model.AttendanceModel;
import in.co.school.mgt.model.FeeModel;
import in.co.school.mgt.model.StudentModel;
import in.co.school.mgt.util.DataUtility;
import in.co.school.mgt.util.PropertyReader;
import in.co.school.mgt.util.ServletUtility;

/**
 * Servlet implementation class FeeListCtl
 */
@WebServlet(name = "FeeListCtl", urlPatterns = { "/ctl/feeList" })
public class FeeListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(FeeListCtl.class);


	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("FeeListCtl populateBean method start");
		FeeBean bean = new FeeBean();

		bean.setStudentName(DataUtility.getString(request.getParameter("name")));

		log.debug("FeeListCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FeeListCtl doGet Start");
		List list = null;

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		FeeBean bean = (FeeBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		FeeModel model = new FeeModel();
		try {
			
           HttpSession session=request.getSession();
			
			UserBean uBean=(UserBean)session.getAttribute("user");
			
			if(uBean.getRoleId()==4) {
				
				StudentModel sModel=new StudentModel();
				StudentBean sBean=sModel.findByLogin(uBean.getLogin());
				bean.setStudentId(sBean.getId());
			}

			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("FeeListCtl doPOst End");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FeeListCtl doPost Start");

		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		FeeBean bean = (FeeBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		FeeModel model = new FeeModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(SCMView.FEE_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					FeeBean deletebean = new FeeBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(SCMView.FEE_LIST_CTL, request, response);
				return;

			}
			
			
            HttpSession session=request.getSession();
			
			UserBean uBean=(UserBean)session.getAttribute("user");
			
			if(uBean.getRoleId()==4) {
				
				StudentModel sModel=new StudentModel();
				StudentBean sBean=sModel.findByLogin(uBean.getLogin());
				bean.setStudentId(sBean.getId());
			}

			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("FeeListCtl doGet End");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SCMView.FEE_LIST_VIEW;
	}

}
