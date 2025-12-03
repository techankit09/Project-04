/*
 * package in.co.rays.proj4.model;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.sql.SQLException;
 * 
 * import in.co.rays.proj4.bean.BankBean; import
 * in.co.rays.proj4.exception.ApplicationException; import
 * in.co.rays.proj4.exception.DatabaseException; import
 * in.co.rays.proj4.exception.DuplicateRecordException; import
 * in.co.rays.proj4.util.JDBCDataSource;
 * 
 * public class BankModel {
 * 
 * public Integer nextPk() throws DatabaseException {
 * 
 * Connection conn = null; int pk = 0;
 * 
 * try { conn = JDBCDataSource.getConnection(); PreparedStatement pstmt =
 * conn.prepareStatement("select max (id from st_bank"); ResultSet rs =
 * pstmt.executeQuery(); while(rs.next()) { pk = rs.getInt(1); } rs.close();
 * pstmt.close(); } catch (Exception e) { throw new
 * DatabaseException("Exception : Exception in getting PK"); } finally {
 * JDBCDataSource.closeConnection(conn); } return pk + 1; }
 * 
 * public long add(BankBean bean) {
 * 
 * Connection conn = null; int pk = 0;
 * 
 * BankBean duplicateAccountNo = findbyAccountNo(bean.getAccountNo());
 * 
 * if (duplicateAccountNo != null) { throw new
 * DuplicateRecordException("College Name already exists"); } try { conn =
 * JDBCDataSource.getConnection(); pk = nextPk(); conn.setAutoCommit(false);
 * PreparedStatement pstmt = conn.
 * prepareStatement("insert into st_bank values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
 * ); pstmt.setInt(1, pk); pstmt.setString(2, bean.getAccountHolderName());
 * pstmt.setString(3, bean.getAccountNo()); pstmt.setString(4,
 * bean.getAccountType()); pstmt.setString(5, bean.getAccountBalance());
 * pstmt.setString(6, bean.getBranch()); pstmt.setInt(7, bean.getPhoneNo());
 * pstmt.setString(7, bean.getCreatedBy()); pstmt.setString(8,
 * bean.getModifiedBy()); pstmt.setTimestamp(9, bean.getCreatedDatetime());
 * pstmt.setTimestamp(10, bean.getModifiedDatetime()); pstmt.executeUpdate();
 * conn.commit(); pstmt.close();
 * 
 * } catch (Exception e) { try { conn.rollback(); } catch (Exception ex) {
 * e.printStackTrace(); throw new
 * ApplicationException("Exception : add rollback exception " + e.getMessage());
 * } throw new ApplicationException("Exception : Exception in add College"); }
 * finally { JDBCDataSource.closeConnection(conn); } return pk; } }
 * 
 * 
 */