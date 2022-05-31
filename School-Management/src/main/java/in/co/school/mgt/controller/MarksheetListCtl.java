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
import in.co.school.mgt.bean.MarksheetBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.model.MarksheetModel;
import in.co.school.mgt.util.DataUtility;
import in.co.school.mgt.util.PropertyReader;
import in.co.school.mgt.util.ServletUtility;

/**
 * Servlet implementation class MarksheetListCtl
 */
@WebServlet(name = "MarksheetListCtl", urlPatterns = { "/ctl/marksheetList" })
public class MarksheetListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(MarksheetListCtl.class);

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("MarksheetListCtl populateBean method start");
		MarksheetBean bean = new MarksheetBean();
		bean.setRollNo(DataUtility.getLong(request.getParameter("rollNo")));
		bean.setStudentName(DataUtility.getString(request.getParameter("name")));
		bean.setSchoolCode(DataUtility.getLong(request.getParameter("code")));
		log.debug("MarksheetListCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("MarksheetListCtl doGet method start");

		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		MarksheetBean bean = (MarksheetBean) populateBean(request);

		MarksheetModel model = new MarksheetModel();
		try {
			list = model.search(bean, pageNo, pageSize);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found ", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		log.debug("MarksheetListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("MarksheetListCtl doPost Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		MarksheetBean bean = (MarksheetBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");

		MarksheetModel model = new MarksheetModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(SCMView.MARKSHEET_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					MarksheetBean deletebean = new MarksheetBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(SCMView.MARKSHEET_LIST_CTL, request, response);
				return;

			}
			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
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

		log.debug("MarksheetListCtl doPost End");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SCMView.MARKSHEET_LIST_VIEW;
	}

}
