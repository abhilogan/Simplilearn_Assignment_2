package in.co.school.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.StudentBean;
import in.co.school.mgt.bean.UserBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DatabaseException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.util.JDBCDataSource;

public class StudentModel {

	private static Logger log = Logger.getLogger(StudentModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_Student");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public StudentBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Student WHERE LOGIN=?");
		StudentBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setFatherName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setClassName(rs.getString(5));
				bean.setSchoolcode(rs.getLong(6));
				bean.setAddress(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setDob(rs.getDate(10));
				bean.setMobileNo(rs.getString(11));
				bean.setProfilePic(rs.getString(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Student by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	
	public StudentBean findBySchoolCode(long schoolCode) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Student WHERE SchoolCode=?");
		StudentBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, schoolCode);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setFatherName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setClassName(rs.getString(5));
				bean.setSchoolcode(rs.getLong(6));
				bean.setAddress(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setDob(rs.getDate(10));
				bean.setMobileNo(rs.getString(11));
				bean.setProfilePic(rs.getString(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Student by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	public StudentBean findByLoginAndClassName(String login, String className) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Student WHERE LOGIN=? and className=?");
		StudentBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, className);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setFatherName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setClassName(rs.getString(5));
				bean.setSchoolcode(rs.getLong(6));
				bean.setAddress(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setDob(rs.getDate(10));
				bean.setMobileNo(rs.getString(11));
				bean.setProfilePic(rs.getString(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Student by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	public StudentBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Student WHERE ID=?");
		StudentBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setFatherName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setClassName(rs.getString(5));
				bean.setSchoolcode(rs.getLong(6));
				bean.setAddress(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setDob(rs.getDate(10));
				bean.setMobileNo(rs.getString(11));
				bean.setProfilePic(rs.getString(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Student by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;
		long schoolCode = 0;

		StudentBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			throw new DuplicateRecordException("Student are already exists in this Class");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO S_Student VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getFatherName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getClassName());
			pstmt.setLong(6, bean.getSchoolcode());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getBloodGroup());
			pstmt.setString(9, bean.getGender());
			pstmt.setDate(10, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(11, bean.getMobileNo());
			pstmt.setString(12, bean.getProfilePic());
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void delete(StudentBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_Student WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		StudentBean beanExist = findByLoginAndClassName(bean.getLogin(), bean.getClassName());
		// Check if updated LoginId already exist
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("Student is already exist in this Class");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE S_Student SET NAME=?,FatherNAME=?,LOGIN=?,ClassName=?,schoolCode=?,address=?,bloodGroup=?,gender=?,DOB=?,MOBILENO=?,profilePic=?,"
					
					+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getFatherName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getClassName());
			pstmt.setLong(5, bean.getSchoolcode());
			pstmt.setString(6, bean.getAddress());
			pstmt.setString(7, bean.getBloodGroup());
			pstmt.setString(8, bean.getGender());
			pstmt.setDate(9, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(10, bean.getMobileNo());
			pstmt.setString(11, bean.getProfilePic());
			pstmt.setString(12, bean.getCreatedBy());
			pstmt.setString(13, bean.getModifiedBy());
			pstmt.setTimestamp(14, bean.getCreatedDatetime());
			pstmt.setTimestamp(15, bean.getModifiedDatetime());
			pstmt.setLong(16, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Student ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	
	
	
	public List search(StudentBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	

	public List search(StudentBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Student WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getClassName() != null && bean.getClassName().length() > 0) {
				sql.append(" AND ClassNAME like '" + bean.getClassName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}
			
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getGender());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILENO = " + bean.getMobileNo());
			}
			
			if (bean.getSchoolcode() > 0) {
				sql.append(" AND Schoolcode = " + bean.getSchoolcode());
			}
			

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("user model search  :"+sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setFatherName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setClassName(rs.getString(5));
				bean.setSchoolcode(rs.getLong(6));
				bean.setAddress(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setDob(rs.getDate(10));
				bean.setMobileNo(rs.getString(11));
				bean.setProfilePic(rs.getString(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}
	
	
	
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from S_Student");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentBean bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setFatherName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setClassName(rs.getString(5));
				bean.setSchoolcode(rs.getLong(6));
				bean.setAddress(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setDob(rs.getDate(10));
				bean.setMobileNo(rs.getString(11));
				bean.setProfilePic(rs.getString(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

}
