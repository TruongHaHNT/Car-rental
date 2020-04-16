/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_Booking_Bill;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class Tbl_Booking_BillDAO implements Serializable{
    public void createNewBill(String email, Timestamp bookingDate, int discountID, int billStatusID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = "INSERT INTO tbl_Booking_Bill(bill_u_email, bill_booking_Date, bill_discountID, bill_statusID) "
                        + "VALUES (?,?,?,?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, email.toLowerCase());
                stm.setTimestamp(2, bookingDate);
                stm.setInt(3, discountID);
                stm.setInt(4, billStatusID);
                int row = stm.executeUpdate();
            }
        } finally {
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }

    public int getNewestBillID(String customerID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = " SELECT top (1) bill_ID " +
                            " FROM tbl_Booking_Bill " +
                            " WHERE bill_u_email = ? " +
                            " ORDER BY bill_booking_Date DESC ";
                stm = con.prepareStatement(sql);
                stm.setString(1, customerID);
                rs = stm.executeQuery();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return 0;
    }
       
    
    List<Tbl_Booking_BillDTO> list = null;

    public List<Tbl_Booking_BillDTO> getList() {
        return list;
    }
    
    public void getBillList(String email, Timestamp minTime, Timestamp maxTime, String searchVar, int curPage, int totalContentPerPage) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int buttonPage = totalContentPerPage*(curPage-1);
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = " declare @minDate dateTime = ? ; " +
                                " declare @maxDate dateTime = ? ; " +
                                " declare @offset int = ? ; " +
                                " declare @total_Page int = ? ; " +
                                " declare @searchVar varchar(30) = ? ; " +
                                " SELECT DISTINCT bill_ID, bill_u_email, bill_booking_Date, dsc_value, bk_st_status " +
                                " FROM tbl_Booking_Bill, tbl_Booking_Bill_Details, tbl_Car, tbl_Discount, tbl_Booking_Status " +
                                " WHERE bill_ID = dt_bill_ID " +
                                " AND bk_st_ID = bill_statusID " +
                                " AND dt_carID = c_ID " +
                                " AND bill_booking_Date >= @minDate " +
                                " AND bill_booking_Date <= @maxDate " +
                                " AND c_name LIKE @searchVar " + 
                                " AND bill_u_email = ? " +
                                " AND bill_discountID = dsc_ID " +
                                " ORDER BY bill_booking_Date DESC " +
                                " OFFSET @offset ROWS " +
                                " FETCH NEXT @total_Page ROWS ONLY ";   
                stm = con.prepareStatement(sql);
                stm.setTimestamp(1, minTime);
                stm.setTimestamp(2, maxTime);
                stm.setInt(3, buttonPage);
                stm.setInt(4, totalContentPerPage);
                stm.setString(5, "%"+searchVar+"%");
                stm.setString(6, email);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int billID = rs.getInt(1);
                    Timestamp bookingDate = rs.getTimestamp(3);
                    String billDiscount = rs.getString(4);
                    String billStatus = rs.getString(5);
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    Tbl_Booking_BillDTO dto = new Tbl_Booking_BillDTO(billID, email, Utils.timeFormat(bookingDate), billDiscount, billStatus);
                    list.add(dto);
                }
                
            }
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
    public int getTotalBill(String email, Timestamp minTime, Timestamp maxTime, String searchVar, int totalContentPerPage) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float page;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = " declare @minDate dateTime = ? ; " +
                                " declare @maxDate dateTime = ? ; " +
                                " declare @searchVar varchar(30) = ? ; " +
                                " SELECT count (DISTINCT bill_ID) " +
                                " FROM tbl_Booking_Bill, tbl_Booking_Bill_Details, tbl_Car " +
                                " WHERE bill_ID = dt_bill_ID " +
                                " AND dt_carID = c_ID " +
                                " AND bill_booking_Date >= @minDate " +
                                " AND bill_booking_Date <= @maxDate " +
                                " AND c_name LIKE @searchVar " + 
                                " AND bill_u_email = ? ";   
                stm = con.prepareStatement(sql);
                stm.setTimestamp(1, minTime);
                stm.setTimestamp(2, maxTime);
                stm.setString(3, "%"+searchVar+"%");
                stm.setString(4, email);
                rs = stm.executeQuery();
                if (rs.next()) {                    
                    page = (float)rs.getInt(1);
                    if(page>0){
                        return (int)Math.ceil((page/totalContentPerPage));
                    }
                }
                
            }
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return 0;
    }
    
    public void updateBillStatus(String email, int billID, int billStatusID, Timestamp updateTime) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = " UPDATE tbl_Booking_Bill SET bill_statusID = ?, bill_booking_Date = ? " +
                             " WHERE bill_u_email = ? AND bill_ID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, billStatusID);
                stm.setTimestamp(2, updateTime);
                stm.setString(3, email.toLowerCase());
                stm.setInt(4, billID);
                int row = stm.executeUpdate();
            }
        } finally {
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }

    public int getNumberOfActiveCar(String email, int billID, int bookingDetailStatusID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float page;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = " SELECT count(bill_ID) " +
                            " FROM tbl_Booking_Bill, tbl_Booking_Bill_Details " +
                            " WHERE bill_ID = dt_bill_ID " +
                            " AND dt_statusID = ? " +
                            " AND bill_ID = ? " +
                            " AND bill_u_email = ? ";   
                stm = con.prepareStatement(sql);
                stm.setInt(1, bookingDetailStatusID);
                stm.setInt(2, billID);
                stm.setString(3, email);
                rs = stm.executeQuery();
                if (rs.next()) {                    
                    return rs.getInt(1);
                }
                
            }
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return 0;
    }
}
