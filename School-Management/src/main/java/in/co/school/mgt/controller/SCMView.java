package in.co.school.mgt.controller;

public interface SCMView {
	
	public String APP_CONTEXT = "/School-Management";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";
	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMaritListView.jsp";
	
	
	public String GENRATE_MARKSHEET_VIEW = PAGE_FOLDER + "/GenrateMarksheetView.jsp";
	
	public String MARKSHEET = PAGE_FOLDER + "/Marksheet.jsp";
	
	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	public String CLASS_VIEW = PAGE_FOLDER + "/ClassView.jsp";	
	public String CLASS_LIST_VIEW = PAGE_FOLDER + "/ClassListView.jsp";
	
	public String FEE_VIEW = PAGE_FOLDER + "/FeeView.jsp";	
	public String FEE_LIST_VIEW = PAGE_FOLDER + "/FeeListView.jsp";
	
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";	
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";
	
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";	
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	
	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";	
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	
	public String ACCOUNTANT_VIEW = PAGE_FOLDER + "/AccountantView.jsp";	
	public String ACCOUNTANT_LIST_VIEW = PAGE_FOLDER + "/AccountantListView.jsp";
	
	public String ATTENDANCE_VIEW = PAGE_FOLDER + "/AttendanceView.jsp";	
	public String ATTENDANCE_LIST_VIEW = PAGE_FOLDER + "/AttendanceListView.jsp";
		
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/error";

	
	
	public String USER_CTL = APP_CONTEXT + "/ctl/user";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/userList";
	
	public String CLASS_CTL = APP_CONTEXT + "/ctl/class";
	public String CLASS_LIST_CTL = APP_CONTEXT + "/ctl/classList";
	
	public String FEE_CTL = APP_CONTEXT + "/ctl/fee";
	public String FEE_LIST_CTL = APP_CONTEXT + "/ctl/feeList";
	
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/subject";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/subjectList";
	
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/student";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/studentList";
	
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/faculty";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/facultyList";
	
	public String ACCOUNTANT_CTL = APP_CONTEXT + "/ctl/accountant";
	public String ACCOUNTANT_LIST_CTL = APP_CONTEXT + "/ctl/accountantList";
	
	public String ATTENDANCE_CTL = APP_CONTEXT + "/ctl/attendance";
	public String ATTENDANCE_LIST_CTL = APP_CONTEXT + "/ctl/attendanceList";
	
	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/marksheet";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/marksheetList";
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/register";
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String WELCOME_CTL = APP_CONTEXT + "/welcome";
	public String LOGOUT_CTL = APP_CONTEXT + "/login";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/getMarksheet";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/changePassword";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/myProfile";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/forgetPassword";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/marksheetMeritList";



}
