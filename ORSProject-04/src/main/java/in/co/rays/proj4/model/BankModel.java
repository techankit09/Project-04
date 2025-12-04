package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.BankBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CollegeModel provides CRUD and search operations for {@link CollegeBean}
 * against the database table {@code st_bank}.
 * <p>
 * It uses {@link JDBCDataSource} to obtain and close connections and throws
 * application-specific checked exceptions to signal error conditions.
 * </p>
 * 
 * @author  Ankit Rawat
 * @version 1.0
 */
public class BankModel {

    /**
     * Returns the next primary key value for the st_bank table.
     *
     * @return next primary key value
     * @throws DatabaseException if a database error occurs while retrieving the maximum id
     */
    public Integer nextPk() throws DatabaseException {
        Connection conn = null;
        int pk = 0;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_bank");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk + 1;
    }

    /**
     * Adds a new College record to the database.
     * <p>
     * Before insertion it checks for duplicate bank name and throws
     * {@link DuplicateRecordException} if a record with same name exists.
     * </p>
     *
     * @param bean {@link BankBean} containing bank data to add
     * @return the primary key of the newly inserted bank
     * @throws ApplicationException       if a general application/database error occurs
     * @throws DuplicateRecordException   if a Bank with same name already exists
     */
    public long add(BankBean bean) throws ApplicationException, DuplicateRecordException {
        Connection conn = null;
        int pk = 0;

        BankBean duplicateBankName = findByName(bean.getAccountHolderName());

        if (duplicateBankName  != null) {
            throw new DuplicateRecordException("Account Holder  Name already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPk();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn
                    .prepareStatement("insert into st_bank values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getAccountHolderName());
            pstmt.setString(3, bean.getAccountNo());
            pstmt.setString(4, bean.getAccountType());
            pstmt.setString(5, bean.getAccountBalance());
            pstmt.setString(6, bean.getBranch());
            pstmt.setInt(7, bean.getPhoneNo());
            pstmt.setString(8, bean.getCreatedBy());
            pstmt.setString(9, bean.getModifiedBy());
            pstmt.setTimestamp(10, bean.getCreatedDatetime());
            pstmt.setTimestamp(11, bean.getModifiedDatetime());
            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add College");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return pk;
    }

    /**
     * Updates an existing Bank record.
     * <p>
     * It checks for duplicate college name (other than the current record) and
     * throws {@link DuplicateRecordException} if a different record with same
     * name exists.
     * </p>
     *
     * @param bean {@link BankBean} containing updated Bank data
     * @throws ApplicationException     if a general application/database error occurs
     * @throws DuplicateRecordException if another Bank with same name exists
     */
    public void update(BankBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;

        BankBean beanExist = findByName(bean.getAccountHolderName());

        if (beanExist != null && beanExist.getId() != bean.getId()) {
            throw new DuplicateRecordException("Account Holder  is already exist");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_bank set account_holder_name = ?, AccountNo = ?, acccount_type  = ?, branch = ?, balance = ?,phoneNo = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
            pstmt.setString(1, bean.getAccountHolderName());
            pstmt.setString(2, bean.getAccountNo());
            pstmt.setString(3, bean.getAccountType());
            pstmt.setString(4, bean.getAccountBalance());
            pstmt.setString(5, bean.getBranch());
            pstmt.setInt(6, bean.getPhoneNo());
            pstmt.setString(7, bean.getCreatedBy());
            pstmt.setString(8, bean.getModifiedBy());
            pstmt.setTimestamp(9, bean.getCreatedDatetime());
            pstmt.setTimestamp(10, bean.getModifiedDatetime());
            pstmt.setLong(11, bean.getId());
            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating Bank ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    /**
     * Deletes a College record.
     *
     * @param bean {@link CollegeBean} whose id identifies the Bank to delete
     * @throws ApplicationException if a general application/database error occurs
     */
    public void delete(BankBean bean) throws ApplicationException {
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("delete from st_bank where id = ?");
            pstmt.setLong(1, bean.getId());
            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in delete bank");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    /**
     * Finds a Bank record by primary key.
     *
     * @param pk primary key (id) of the Bank
     * @return {@link BankBean} if found; {@code null} otherwise
     * @throws ApplicationException if a general application/database error occurs
     */
    public BankBean findByPk(long pk) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_bank where id = ?");

        BankBean bean = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new BankBean();
                bean.setId(rs.getLong(1));
                bean.setAccountHolderName(rs.getString(2));
                bean.setAccountNo(rs.getString(3));
                bean.setAccountType(rs.getString(4));
                bean.setAccountBalance(rs.getString(5));
                bean.setBranch(rs.getString(6));
                bean.setPhoneNo(rs.getInt(7));
                bean.setCreatedBy(rs.getString(8));
                bean.setModifiedBy(rs.getString(9));
                bean.setCreatedDatetime(rs.getTimestamp(10));
                bean.setModifiedDatetime(rs.getTimestamp(11));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Bank by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    /**
     * Finds a Bank record by its name.
     *
     * @param name name of the Bank
     * @return {@link CollegeBean} if found; {@code null} otherwise
     * @throws ApplicationException if a general application/database error occurs
     */
    public BankBean findByName(String name) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_bank where name = ?");

        BankBean bean = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new BankBean();
                bean.setId(rs.getLong(1));
                bean.setAccountHolderName(rs.getString(2));
                bean.setAccountNo(rs.getString(3));
                bean.setAccountType(rs.getString(4));
                bean.setAccountBalance(rs.getString(5));
                bean.setBranch(rs.getString(6));
                bean.setPhoneNo(rs.getInt(7));
                bean.setCreatedBy(rs.getString(8));
                bean.setModifiedBy(rs.getString(9));
                bean.setCreatedDatetime(rs.getTimestamp(10));
                bean.setModifiedDatetime(rs.getTimestamp(11));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting College by Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return bean;
    }

    /**
     * Returns a list of all colleges. This is a convenience wrapper around
     * {@link #search(BankBean, int, int)} with no filter and no pagination.
     *
     * @return list of all {@link BankBean}
     * @throws ApplicationException if a general application/database error occurs
     */
    public List<BankBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    /**
     * Searches for Bank matching the criteria provided in {@code bean}.
     * If {@code pageSize} &gt; 0, results are paginated.
     *
     * @param bean     filter criteria; if {@code null} returns all records
     * @param pageNo   page number (1-based) when paginating; ignored if pageSize is 0
     * @param pageSize number of records per page; pass 0 to disable pagination
     * @return list of matching {@link BankBean}
     * @throws ApplicationException if a general application/database error occurs
     */
    public List<BankBean> search(BankBean bean, int pageNo, int pageSize) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_college where 1 = 1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" and id = " + bean.getId());
            }
            if (bean.getAccountHolderName() != null && bean.getAccountHolderName().length() > 0) {
                sql.append(" and account_holder_name like '" + bean.getAccountHolderName() + "%'");
            }
            if (bean.getAccountNo() != null && bean.getAccountNo().length() > 0) {
                sql.append(" and AccountNo like '" + bean.getAccountNo() + "%'");
            }
            if (bean.getAccountType() != null && bean.getAccountType().length() > 0) {
                sql.append(" and account_type like '" + bean.getAccountType() + "%'");
            }
            if (bean.getAccountBalance() != null && bean.getAccountBalance().length() > 0) {
                sql.append(" and balance like '" + bean.getAccountBalance() + "%'");
            }
            if (bean.getPhoneNo() != null && bean.getPhoneNo().intValue() > 0) {
                sql.append(" and phone_no = " + bean.getPhoneNo());
            }
        }

        if (pageSize > 0) {
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + ", " + pageSize);
        }

        ArrayList<BankBean> list = new ArrayList<BankBean>();
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new BankBean();
                bean.setId(rs.getLong(1));
                bean.setAccountHolderName(rs.getString(2));
                bean.setAccountNo(rs.getString(3));
                bean.setAccountType(rs.getString(4));
                bean.setAccountBalance(rs.getString(5));
                bean.setBranch(rs.getString(6));
                bean.setPhoneNo(rs.getInt(7));
                bean.setCreatedBy(rs.getString(8));
                bean.setModifiedBy(rs.getString(9));
                bean.setCreatedDatetime(rs.getTimestamp(10));
                bean.setModifiedDatetime(rs.getTimestamp(11));
                list.add(bean);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search Bank");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        return list;
    }
}
