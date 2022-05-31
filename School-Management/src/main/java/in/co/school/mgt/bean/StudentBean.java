package in.co.school.mgt.bean;

import java.util.Date;

public class StudentBean extends BaseBean {

	private String name;
	private String fatherName;
	private String login;
	private String className;
	private long schoolcode;
	private String address;
	private String bloodGroup;
	private String gender;
	private Date dob;
	private String mobileNo;
	private String profilePic;
	
	private long classId;
	
	
	
	
	
	
	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getSchoolcode() {
		return schoolcode;
	}

	public void setSchoolcode(long schoolcode) {
		this.schoolcode = schoolcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	

	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}
