package in.co.school.mgt.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.BaseBean;
import in.co.school.mgt.bean.ClassBean;
import in.co.school.mgt.bean.GenrateMarksheetBean;
import in.co.school.mgt.bean.MarksheetBean;
import in.co.school.mgt.bean.StudentBean;
import in.co.school.mgt.bean.SubjectBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.model.ClassModel;
import in.co.school.mgt.model.GenrateMarksheetModel;
import in.co.school.mgt.model.MarksheetModel;
import in.co.school.mgt.model.StudentModel;
import in.co.school.mgt.model.SubjectModel;
import in.co.school.mgt.util.DataUtility;
import in.co.school.mgt.util.DataValidator;
import in.co.school.mgt.util.PropertyReader;
import in.co.school.mgt.util.ServletUtility;

/**
 * Servlet implementation class MarkseetCtl
 */
@WebServlet(name = "MarksheetCtl", urlPatterns = { "/ctl/marksheet" })
public class MarksheetCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(MarksheetCtl.class);
    
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("MarksheetCtl validate method start");
        boolean pass = true;

        
        
        if (DataValidator.isNull(request.getParameter("schoolCode"))) {
            request.setAttribute("schoolCode",
                    PropertyReader.getValue("error.require", "School Code"));
            pass = false;
        }
        
        String op = DataUtility.getString(request.getParameter("operation"));
        if(OP_GENRATE.equalsIgnoreCase(op)) {
        	pass=true;
        }

        
        log.debug("MarksheetCtl validate method end");
        return pass;
    }
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("MarksheetCtl populateBean method start");
		MarksheetBean bean=new MarksheetBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getLong(request.getParameter("rollNo")));
		bean.setSchoolCode(DataUtility.getLong(request.getParameter("schoolCode")));
		populateDTO(bean, request);
		log.debug("MarksheetCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MarksheetCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       MarksheetModel model = new MarksheetModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            MarksheetBean bean;
	            try {
	                bean = model.findByPK(id);
	                ServletUtility.setOpration("Edit", request);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }

	        ServletUtility.forward(SCMView.MARKSHEET_VIEW, request, response);
	        log.debug("MarksheetCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("MarksheetCtl doPost method start");
			String op=DataUtility.getString(request.getParameter("operation"));
			MarksheetModel model=new MarksheetModel();
			long id=DataUtility.getLong(request.getParameter("id"));
			HttpSession session=request.getSession();
			MarksheetBean bean=(MarksheetBean)populateBean(request);
			
			if(OP_SUBMIT.equalsIgnoreCase(op)){	
				session.setAttribute("sCode", bean.getSchoolCode());
					try {
						
						StudentModel sModel=new StudentModel();
						StudentBean sBean=sModel.findBySchoolCode(bean.getSchoolCode());
						SubjectModel sbModel=new SubjectModel();
						SubjectBean sbBean=new SubjectBean();
						System.out.println("Class Name"+sBean.getClassName());
						sbBean.setClassName(sBean.getClassName());
						List sublist= sbModel.search(sbBean);
						
						if(sBean!=null) {
						
							session.setAttribute("stuBean", sBean);
						ServletUtility.setList(sublist, request);
						ServletUtility.forward(SCMView.GENRATE_MARKSHEET_VIEW, request, response);
						
						}else {
							ServletUtility.setErrorMessage("School Code is wrong", request);
							ServletUtility.forward(SCMView.MARKSHEET_VIEW, request, response);
						}
						
					} catch (ApplicationException e) {
						e.printStackTrace();
						ServletUtility.forward(SCMView.ERROR_VIEW, request, response);
						return;
					
				} 
				
			}else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility.redirect(SCMView.MARKSHEET_CTL, request, response);
				return;
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SCMView.MARKSHEET_CTL, request, response);
			return;
	}else if (OP_GENRATE.equalsIgnoreCase(op)) {
		
		System.out.println("In Genrate Operation");
			StudentBean sBean=(StudentBean)session.getAttribute("stuBean");
			MarksheetBean mBean=new MarksheetBean();
			
			SubjectModel sModel=new SubjectModel();
			SubjectBean sbBean=new SubjectBean();
			
			sbBean.setClassName(sBean.getClassName());
			ClassModel cModel=new ClassModel();
			
			try {
				ClassBean cBean= cModel.findByName(sBean.getClassName());
				List list=sModel.search(sbBean);
				Iterator<SubjectBean> it=list.iterator();
				long roll=DataUtility.getLong(request.getParameter("rollNo"));
				int ind=1;
				while (it.hasNext()) {
					SubjectBean subjectBean = (SubjectBean) it.next();
					int in=ind++;
					System.out.println("Indesx"+in);
					long mark=DataUtility.getLong(request.getParameter("mark"+in));
					System.out.println("Mark value in index "+in+" "+mark);
					mBean.setRollNo(roll);
					mBean.setClassId(cBean.getId());
					mBean.setClassName(sBean.getClassName());
					mBean.setSubjectId(subjectBean.getId());
					mBean.setSubjectName(subjectBean.getName());
					mBean.setStudentId(sBean.getId());
					mBean.setStudentName(sBean.getName());
					mBean.setSchoolCode(sBean.getSchoolcode());
					mBean.setMark(mark);
					
					if(mark>=80) {
						mBean.setGrade("A");
						mBean.setResult("PASS");
					}else if(mark < 80 && mark >=60) {
						mBean.setGrade("B");
						mBean.setResult("PASS");
					}else if (mark <60 && mark >=33) {
						mBean.setGrade("C");
					}else if(mark < 33) {
						mBean.setGrade("F");
						mBean.setResult("FAIL");
					}
					mBean.setCreatedBy(bean.getCreatedBy());
					mBean.setModifiedBy(bean.getModifiedBy());
					mBean.setCreatedDatetime(bean.getCreatedDatetime());
					mBean.setModifiedDatetime(bean.getModifiedDatetime());
					model.add(mBean);
				}
				
				GenrateMarksheetModel gModel=new GenrateMarksheetModel();
				GenrateMarksheetBean gBean=gModel.findByStudentId(sBean.getId());
				long totalCreadit=MarksheetModel.getCreditMark(sBean.getId());
				long total=list.size()*100;
				double dtotal=(double)total;
				double dctotal=(double)totalCreadit;
				double CGPA=(dctotal/dtotal)*100;
				if(gBean!=null) {
					gBean.setTotalCreadit(totalCreadit);
					gBean.setCGPA(CGPA);
					gModel.update(gBean);
					
				}else {					
					GenrateMarksheetBean gnBean=new GenrateMarksheetBean();
					gnBean.setRollNo(bean.getRollNo());
					gnBean.setStudentId(sBean.getId());
					gnBean.setName(sBean.getName());
					gnBean.setSchoolCode(sBean.getSchoolcode());
					gnBean.setClassId(cBean.getId());
					gnBean.setClassName(cBean.getName());
					gnBean.setTotalCreadit(totalCreadit);
					gnBean.setTotal(total);
					gnBean.setCGPA(CGPA);
					gModel.add(gnBean);
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.forward(SCMView.ERROR_VIEW, request, response);
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			ServletUtility.forward(SCMView.MARKSHEET, request, response);
			
	}	
			 log.debug("MarksheetCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
