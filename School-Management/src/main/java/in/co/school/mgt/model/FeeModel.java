package in.co.school.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.FeeBean;
import in.co.school.mgt.bean.SubjectBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DatabaseException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.util.JDBCDataSource;

public class FeeModel {
	
	private static Logger log = Logger.getLogger(FeeModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_FEE");
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
	
	public Integer maxPaid(long studentID) throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(paid) FROM S_FEE where studentID=?");
			pstmt.setLong(1,studentID);
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
		return pk;
	}
	
	public FeeBean findByName(String name) throws ApplicationException {
		log.debug("Model findBy Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Subject WHERE StudentName=?");
		FeeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeeBean();
				bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setStudentName(rs.getString(3));
				bean.setPay(rs.getLong(4));
				bean.setTotalfee(rs.getLong(5));
				bean.setPaid(rs.getLong(6));
				bean.setDue(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Subject by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Name End");
		return bean;
	}
	
	public FeeBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Fee WHERE ID=?");
		FeeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeeBean();
				bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setStudentName(rs.getString(3));
				bean.setPay(rs.getLong(4));
				bean.setTotalfee(rs.getLong(5));
				bean.setPaid(rs.getLong(6));
				bean.setDue(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Subject by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	public long add(FeeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO S_Fee VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getStudentName());
			pstmt.setLong(4, bean.getPay());
			pstmt.setLong(5, bean.getTotalfee());
			pstmt.setLong(6, bean.getPaid());
			pstmt.setLong(7, bean.getDue());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	public void delete(FeeBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_Fee WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}
	
	public void update(FeeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		
		
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE S_Fee SET StudentId=?,StudentName=?, Pay=?,Totalfee=?,Paid=?,Due=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getStudentId());
			pstmt.setString(2, bean.getStudentName());
			pstmt.setLong(3, bean.getPay());
			pstmt.setLong(4, bean.getTotalfee());
			pstmt.setLong(5, bean.getPaid());
			pstmt.setLong(6, bean.getDue());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Subject ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	public List search(FeeBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

    public List search(FeeBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM S_Fee WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
           
            if (bean.getStudentId() > 0) {
                sql.append(" AND StudentId = " + bean.getStudentId());
            }
            
            if (bean.getStudentName() != null && bean.getStudentName().length() > 0) {
				sql.append(" AND StudentName LIKE '" + bean.getStudentName() + "%'");
            }
            
            
        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }
        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new FeeBean();
                bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setStudentName(rs.getString(3));
				bean.setPay(rs.getLong(4));
				bean.setTotalfee(rs.getLong(5));
				bean.setPaid(rs.getLong(6));
				bean.setDue(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Subject");
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
        StringBuffer sql = new StringBuffer("select * from S_Fee");
        
        if (pageSize > 0) {
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }
       
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FeeBean bean = new FeeBean();
                bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setStudentName(rs.getString(3));
				bean.setPay(rs.getLong(4));
				bean.setTotalfee(rs.getLong(5));
				bean.setPaid(rs.getLong(6));
				bean.setDue(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Subject");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }

}
