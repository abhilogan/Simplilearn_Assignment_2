package in.co.school.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.AttendanceBean;
import in.co.school.mgt.bean.GenrateMarksheetBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DatabaseException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.util.JDBCDataSource;

public class AttendanceModel {
	
	private static Logger log = Logger.getLogger(AttendanceModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_Attendance");
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
	
	public AttendanceBean findByName(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Attendance WHERE Name=?");
		AttendanceBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AttendanceBean();
				bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setSchoolCode(rs.getLong(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setStatus(rs.getString(7));
				bean.setDate(rs.getDate(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
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
	
	
	public AttendanceBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Attendance WHERE ID=?");
		AttendanceBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AttendanceBean();
				bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setSchoolCode(rs.getLong(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setStatus(rs.getString(7));
				bean.setDate(rs.getDate(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
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
	
	
	public long add(AttendanceBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;
	
		Date dat=new java.util.Date();
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO S_Attendance VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getName());
			pstmt.setLong(4, bean.getSchoolCode());
			pstmt.setLong(5, bean.getClassId());
			pstmt.setString(6, bean.getClassName());
			pstmt.setString(7, bean.getStatus());
			pstmt.setDate(8, new java.sql.Date(dat.getTime()) );
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
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
	
	public void delete(AttendanceBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_Attendance WHERE ID=?");
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
	
	public void update(AttendanceBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE S_Attendance SET StudentId=?,NAME=?,schoolCode=?,ClassId=?,ClassName=?,status=?,date=?,"
					
					+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getStudentId());
			pstmt.setString(2, bean.getName());
			pstmt.setLong(3, bean.getSchoolCode());
			pstmt.setLong(4, bean.getClassId());
			pstmt.setString(5, bean.getClassName());
			pstmt.setString(6, bean.getStatus());
			pstmt.setDate(7, new java.sql.Date(bean.getDate().getTime()) );
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());
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
	
	public List search(AttendanceBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	

	public List search(AttendanceBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Attendance WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND Name like '" + bean.getName() + "%'");
			}
			if (bean.getClassName() != null && bean.getClassName().length() > 0) {
				sql.append(" AND ClassNAME like '" + bean.getClassName() + "%'");
			}

			if (bean.getSchoolCode() > 0) {
				sql.append(" AND SchoolCode = " + bean.getSchoolCode());
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
				bean = new AttendanceBean();
				bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setSchoolCode(rs.getLong(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setStatus(rs.getString(7));
				bean.setDate(rs.getDate(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
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
		StringBuffer sql = new StringBuffer("select * from S_Attendance");
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
				AttendanceBean bean = new AttendanceBean();
				bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setSchoolCode(rs.getLong(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setStatus(rs.getString(7));
				bean.setDate(rs.getDate(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
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
