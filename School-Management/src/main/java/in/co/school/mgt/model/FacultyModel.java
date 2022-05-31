package in.co.school.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.FacultyBean;
import in.co.school.mgt.bean.StudentBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DatabaseException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.util.JDBCDataSource;

public class FacultyModel {

	private static Logger log = Logger.getLogger(FacultyModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_Faculty");
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
	
	public FacultyBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Faculty WHERE LOGIN=?");
		FacultyBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setLogin(rs.getString(3));
				bean.setMobileNo(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setProfilePic(rs.getString(8));
				bean.setQualification(rs.getString(9));
				bean.setAddress(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Faculty by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	
	public FacultyBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Faculty WHERE ID=?");
		FacultyBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setLogin(rs.getString(3));
				bean.setMobileNo(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setProfilePic(rs.getString(8));
				bean.setQualification(rs.getString(9));
				bean.setAddress(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Faculty by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;
		long schoolCode = 0;

		FacultyBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			throw new DuplicateRecordException("Faculty is already exists ");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO S_Faculty VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getMobileNo());
			pstmt.setString(5, bean.getGender());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));	
			pstmt.setLong(7, bean.getSchoolCode());
			pstmt.setString(8, bean.getProfilePic());
			pstmt.setString(9, bean.getQualification());
			pstmt.setString(10, bean.getAddress());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}
	
	public void delete(FacultyBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_Faculty WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		FacultyBean beanExist = findByLogin(bean.getLogin());
		// Check if updated LoginId already exist
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("Faculty is already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE S_Faculty SET NAME=?,LOGIN=?,MOBILENO=?,gender=?,DOB=?,schoolCode=?,profilePic=?,qualification=?,address=?,"
					
					+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getLogin());
			pstmt.setString(3, bean.getMobileNo());
			pstmt.setString(4, bean.getGender());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));	
			pstmt.setLong(6, bean.getSchoolCode());
			pstmt.setString(7, bean.getProfilePic());
			pstmt.setString(8, bean.getQualification());
			pstmt.setString(9, bean.getAddress());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.setLong(14, bean.getId());
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
			throw new ApplicationException("Exception in updating Faculty ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	
	public List search(FacultyBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	

	public List search(FacultyBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Faculty WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
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
			
			if (bean.getSchoolCode() > 0) {
				sql.append(" AND Schoolcode = " + bean.getSchoolCode());
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
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setLogin(rs.getString(3));
				bean.setMobileNo(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setProfilePic(rs.getString(8));
				bean.setQualification(rs.getString(9));
				bean.setAddress(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Faculty");
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
		StringBuffer sql = new StringBuffer("select * from S_Faculty");
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
				FacultyBean bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setLogin(rs.getString(3));
				bean.setMobileNo(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setProfilePic(rs.getString(8));
				bean.setQualification(rs.getString(9));
				bean.setAddress(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
	
}
