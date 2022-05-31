package in.co.school.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.GenrateMarksheetBean;
import in.co.school.mgt.bean.MarksheetBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DatabaseException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.util.JDBCDataSource;

public class GenrateMarksheetModel {

	private static Logger log = Logger.getLogger(GenrateMarksheetModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_GenrateMarksheet");
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
	
	public GenrateMarksheetBean findByName(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_GenrateMarksheet WHERE Name=?");
		GenrateMarksheetBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new GenrateMarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setTotalCreadit(rs.getLong(8));
				bean.setTotal(rs.getLong(9));
				bean.setCGPA(rs.getDouble(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
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
	
	public GenrateMarksheetBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_GenrateMarksheet WHERE ID=?");
		GenrateMarksheetBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new GenrateMarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setTotalCreadit(rs.getLong(8));
				bean.setTotal(rs.getLong(9));
				bean.setCGPA(rs.getDouble(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));

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
	
	
	public GenrateMarksheetBean findByStudentId(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_GenrateMarksheet WHERE StudentID=?");
		GenrateMarksheetBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new GenrateMarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setTotalCreadit(rs.getLong(8));
				bean.setTotal(rs.getLong(9));
				bean.setCGPA(rs.getDouble(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));

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
	
	public long add(GenrateMarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;
	

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO S_GenrateMarksheet VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, bean.getName());
			pstmt.setLong(5, bean.getClassId());
			pstmt.setString(6, bean.getClassName());
			pstmt.setLong(7, bean.getSchoolCode());
			pstmt.setLong(8, bean.getTotalCreadit());
			pstmt.setLong(9, bean.getTotal());
			pstmt.setDouble(10, bean.getCGPA());
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
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}
	
	
	public void delete(GenrateMarksheetBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_GenrateMarksheet WHERE ID=?");
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
	
	
	public void update(GenrateMarksheetBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE S_GenrateMarksheet SET RollNo=?,StudentId=?,NAME=?,ClassId=?,ClassName=?,schoolCode=?,TotalCreadit=?,Total=?,CGPA=?,"
					
					+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getName());
			pstmt.setLong(4, bean.getClassId());
			pstmt.setString(5, bean.getClassName());
			pstmt.setLong(6, bean.getSchoolCode());
			pstmt.setLong(7, bean.getTotalCreadit());
			pstmt.setLong(8, bean.getTotal());
			pstmt.setDouble(9, bean.getCGPA());
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
			throw new ApplicationException("Exception in updating Student ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	
	public List search(GenrateMarksheetBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	

	public List search(GenrateMarksheetBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_GenrateMarksheet WHERE 1=1");

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
				bean = new GenrateMarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setTotalCreadit(rs.getLong(8));
				bean.setTotal(rs.getLong(9));
				bean.setCGPA(rs.getDouble(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
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
		StringBuffer sql = new StringBuffer("select * from S_GenrateMarksheet");
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
				GenrateMarksheetBean bean = new GenrateMarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setTotalCreadit(rs.getLong(8));
				bean.setTotal(rs.getLong(9));
				bean.setCGPA(rs.getDouble(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
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
	
	public List Meritlist() throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from S_GenrateMarksheet ");
		// if page size is greater than zero then apply pagination
		sql.append(" Order By CGPA DESC " );
			sql.append(" limit " + 0 + "," + 10);
			

		System.out.println(sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				GenrateMarksheetBean bean = new GenrateMarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getLong(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setClassId(rs.getLong(5));
				bean.setClassName(rs.getString(6));
				bean.setSchoolCode(rs.getLong(7));
				bean.setTotalCreadit(rs.getLong(8));
				bean.setTotal(rs.getLong(9));
				bean.setCGPA(rs.getDouble(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
	
}
