package in.co.school.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.school.mgt.bean.ClassBean;
import in.co.school.mgt.bean.SubjectBean;
import in.co.school.mgt.exception.ApplicationException;
import in.co.school.mgt.exception.DatabaseException;
import in.co.school.mgt.exception.DuplicateRecordException;
import in.co.school.mgt.util.JDBCDataSource;

public class SubjectModel {
	
	private static Logger log = Logger.getLogger(SubjectModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_SUBJECT");
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
	
	public SubjectBean findByName(String name) throws ApplicationException {
		log.debug("Model findBy Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Subject WHERE NAME=?");
		SubjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setClassId(rs.getLong(2));
				bean.setClassName(rs.getString(3));
				bean.setName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
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
	
	public SubjectBean findByNameAndClass(String name,long classId) throws ApplicationException {
		log.debug("Model findBy Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Subject WHERE NAME=? and classId=?");
		SubjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setLong(2, classId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setClassId(rs.getLong(2));
				bean.setClassName(rs.getString(3));
				bean.setName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
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
	
	public SubjectBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Subject WHERE ID=?");
		SubjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setClassId(rs.getLong(2));
				bean.setClassName(rs.getString(3));
				bean.setName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
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
	
	
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		SubjectBean duplicataRole = findByNameAndClass(bean.getName(), bean.getClassId());

		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Subject is already exists in this class");
		}
		
		
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO S_subject VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getClassId());
			pstmt.setString(3, bean.getClassName());
			pstmt.setString(4, bean.getName());
			pstmt.setString(5, bean.getDescription());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
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
	
	
	public void delete(SubjectBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_Subject WHERE ID=?");
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
	
	
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		SubjectBean duplicataRole = findByNameAndClass(bean.getName(), bean.getClassId());

		if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
			throw new DuplicateRecordException("Subject is  already exists in this class");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE S_Subject SET classId=?,ClassName=?, NAME=?,DESCRIPTION=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getClassId());
			pstmt.setString(2, bean.getClassName());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
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
	
	
	public List search(SubjectBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

    public List search(SubjectBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM S_Subject WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getClassId() > 0) {
                sql.append(" AND ClassId = " + bean.getClassId());
            }
            if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
            }
            
            if (bean.getClassName() != null && bean.getClassName().length() > 0) {
				sql.append(" AND ClassName LIKE '" + bean.getClassName() + "%'");
            }
            
            if (bean.getDescription() != null
                    && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription()
                        + "%'");
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
                bean = new SubjectBean();
                bean.setId(rs.getLong(1));
				bean.setClassId(rs.getLong(2));
				bean.setClassName(rs.getString(3));
				bean.setName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
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
        StringBuffer sql = new StringBuffer("select * from S_Subject");
        
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
                SubjectBean bean = new SubjectBean();
                bean.setId(rs.getLong(1));
				bean.setClassId(rs.getLong(2));
				bean.setClassName(rs.getString(3));
				bean.setName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
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
