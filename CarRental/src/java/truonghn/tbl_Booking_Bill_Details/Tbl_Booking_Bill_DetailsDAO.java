/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_Booking_Bill_Details;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
//import java.util.List;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class Tbl_Booking_Bill_DetailsDAO implements Serializable{
    public void addCarToBill(int billID, int CarID, Timestamp dateRent, Timestamp dateReTurn, int amount, int bookingDetailStatusID, float price, Timestamp updateDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = "INSERT INTO tbl_Booking_Bill_Details(dt_bill_ID, dt_carID, dt_dateRent, dt_dateReturn, dt_amount, dt_statusID, dt_price, dt_updateDate) "
                        + "VALUES (?,?,?,?,?,?,?,?)";
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, billID);
                stm.setInt(2, CarID);
                stm.setTimestamp(3, dateRent);
                stm.setTimestamp(4, dateReTurn);
                stm.setInt(5, amount);
                stm.setInt(6, bookingDetailStatusID);
                stm.setFloat(7, price);
                stm.setTimestamp(8, updateDate);
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
    
    public void updateBillStatus(int billID, int billDetailStatusID, Timestamp updateTime) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = " UPDATE tbl_Booking_Bill_Details SET dt_statusID = ?, dt_updateDate = ? " +
                             " WHERE dt_bill_ID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, billDetailStatusID);
                stm.setTimestamp(2, updateTime);
                stm.setInt(3, billID);
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
    public void updateDetailStatus(int detailID, int billDetailStatusID, Timestamp updateTime) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = " UPDATE tbl_Booking_Bill_Details SET dt_statusID = ?, dt_updateDate = ? " +
                             " WHERE dt_ID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, billDetailStatusID);
                stm.setTimestamp(2, updateTime);
                stm.setInt(3, detailID);
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
//    List<Tbl_Booking_Bill_DetailsDTO> list = null;
//
//    public List<Tbl_Booking_Bill_DetailsDTO> getList() {
//        return list;
//    }
//    
}
