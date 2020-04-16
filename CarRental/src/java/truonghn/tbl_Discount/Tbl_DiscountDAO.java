/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_Discount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class Tbl_DiscountDAO implements Serializable{
    public int checkValidDiscountCode(String discountCode, Timestamp today, int discountStatusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = "SELECT dsc_ID " +
                                " FROM tbl_Discount " +
                                " WHERE dsc_code = ? " +
                                " AND dsc_statusID = ? " +
                                " AND dsc_exDate >= ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountCode);
                stm.setInt(2, discountStatusID);
                stm.setTimestamp(3, today);
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
    
    public void changeDiscountStatus(int discountID ,int discountStatusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = " UPDATE tbl_Discount SET dsc_statusID = ? " +
                                " WHERE dsc_ID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, discountStatusID);
                stm.setInt(2, discountID);
                int row = stm.executeUpdate();
            }
        }finally{
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
}
