package in.co.school.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.MarksheetBean;
import in.co.school.mgt.bean.StudentBean;
import in.co.school.mgt.bean.SubjectBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DatabaseException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.util.JDBCDataSource;

public class MarksheetModel {
	
	private static Logger log = Logger.getLogger(MarksheetModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_Marksheet");
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
	
	public MarksheetBean findByName(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Marksheet WHERE StudentName=?");
		MarksheetBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setClassId(rs.getLong(3));
				bean.setClassName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setSubjectName(rs.getString(6));
				bean.setStudentId(rs.getLong(7));
				bean.setStudentName(rs.getString(8));
				bean.setSchoolCode(rs.getLong(9));
				bean.setMark(rs.getLong(10));
				bean.setGrade(rs.getString(11));
				bean.setResult(rs.getString(12));
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
	
	public MarksheetBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Marksheet WHERE ID=?");
		MarksheetBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setClassId(rs.getLong(3));
				bean.setClassName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setSubjectName(rs.getString(6));
				bean.setStudentId(rs.getLong(7));
				bean.setStudentName(rs.getString(8));
				bean.setSchoolCode(rs.getLong(9));
				bean.setMark(rs.getLong(10));
				bean.setGrade(rs.getString(11));
				bean.setResult(rs.getString(12));
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
	
	
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;
		long schoolCode = 0;

		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO S_Marksheet VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getRollNo());
			pstmt.setLong(3, bean.getClassId());
			pstmt.setString(4, bean.getClassName());
			pstmt.setLong(5, bean.getSubjectId());
			pstmt.setString(6, bean.getSubjectName());
			pstmt.setLong(7, bean.getStudentId());
			pstmt.setString(8, bean.getStudentName());
			pstmt.setLong(9, bean.getSchoolCode());
			pstmt.setLong(10, bean.getMark());
			pstmt.setString(11, bean.getGrade());
			pstmt.setString(12, bean.getResult());
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
	
	public void delete(MarksheetBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_Marksheet WHERE ID=?");
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
	
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE S_Marksheet SET RollNo=?,ClassId=?,ClassName=?,SubjectId=?,SubjectName=?,StudentId=?,StudentNAME=?,schoolCode=?,Mark=?,grade=?,result=?,DOB=?,"
					
					+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getRollNo());
			pstmt.setLong(2, bean.getClassId());
			pstmt.setString(3, bean.getClassName());
			pstmt.setLong(4, bean.getSubjectId());
			pstmt.setString(5, bean.getSubjectName());
			pstmt.setLong(6, bean.getStudentId());
			pstmt.setString(7, bean.getStudentName());
			pstmt.setLong(8, bean.getSchoolCode());
			pstmt.setLong(9, bean.getMark());
			pstmt.setString(10, bean.getGrade());
			pstmt.setString(11, bean.getResult());
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
	
	public List search(MarksheetBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	

	public List search(MarksheetBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Marksheet WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getStudentName() != null && bean.getStudentName().length() > 0) {
				sql.append(" AND StudentName like '" + bean.getStudentName() + "%'");
			}
			if (bean.getClassName() != null && bean.getClassName().length() > 0) {
				sql.append(" AND ClassNAME like '" + bean.getClassName() + "%'");
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" AND SubjectName like '" + bean.getSubjectName() + "%'");
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
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setClassId(rs.getLong(3));
				bean.setClassName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setSubjectName(rs.getString(6));
				bean.setStudentId(rs.getLong(7));
				bean.setStudentName(rs.getString(8));
				bean.setSchoolCode(rs.getLong(9));
				bean.setMark(rs.getLong(10));
				bean.setGrade(rs.getString(11));
				bean.setResult(rs.getString(12));
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
		StringBuffer sql = new StringBuffer("select * from S_Marksheet");
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
				MarksheetBean bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setClassId(rs.getLong(3));
				bean.setClassName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setSubjectName(rs.getString(6));
				bean.setStudentId(rs.getLong(7));
				bean.setStudentName(rs.getString(8));
				bean.setSchoolCode(rs.getLong(9));
				bean.setMark(rs.getLong(10));
				bean.setGrade(rs.getString(11));
				bean.setResult(rs.getString(12));
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
	
	
	public static long getCreditMark(long userId) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_MarkSheet WHERE StudentId=?");
		Connection conn = null;

		long totalcreadit=0;
		
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1,userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				totalcreadit=totalcreadit+rs.getLong("mark");
			}
			rs.close();
			System.out.println("Creadit total finish");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		
		return totalcreadit;	
	}
	

}
