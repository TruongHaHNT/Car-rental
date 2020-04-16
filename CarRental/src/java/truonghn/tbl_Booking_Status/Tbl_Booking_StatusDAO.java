/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_Booking_Status;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class Tbl_Booking_StatusDAO implements Serializable{
    public int getBookingStatusID(String booking_Status) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = "SELECT bk_st_ID "
                        + " FROM tbl_Booking_Status "
                        + " WHERE bk_st_status = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, booking_Status);
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
}
