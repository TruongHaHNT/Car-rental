/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_Rating;

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
public class Tbl_RatingDAO implements Serializable{
    public float getCarRating(int carID, int carStatusID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql =" declare @carID int = ? ; " +
                                " declare @carStatusID int = ? ; " +
                                " SELECT CASE WHEN AVG(rt_Value) is null THEN 0 ELSE AVG(rt_Value) END " +
                                " FROM tbl_Car, tbl_Rating " +
                                " WHERE c_ID = @carID " +
                                " AND c_statusID = @carStatusID " +
                                " AND c_ID = rt_CarID ";
                stm = con.prepareStatement(sql);
                    stm.setInt(1, carID);
                    stm.setInt(2, carStatusID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    float ratingValue = rs.getFloat(1);
                    return Utils.roundFloatNumber(ratingValue);
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

    public void rateThisCar(int carID, String email, int ratingValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql =" declare @carID int = ? ; " +
                            " declare @email varchar(254)= ? ; " +
                            " declare @ratingValue int = ? ; " +
                            " IF EXISTS (SELECT top(1) rt_CarID " +
                            "			FROM tbl_Rating " +
                            "			WHERE rt_CarID = @carID " +
                            "			AND rt_u_email = @email) " +
                            "    UPDATE tbl_Rating SET rt_Value = @ratingValue " +
                            "	 WHERE rt_CarID = @carID " +
                            "	 AND rt_u_email = @email; " +
                            " ELSE " +
                            "    INSERT INTO tbl_Rating(rt_CarID, rt_u_email, rt_Value) VALUES (@carID,@email,@ratingValue); ";
                stm = con.prepareStatement(sql);
                    stm.setInt(1, carID);
                    stm.setString(2, email);
                    stm.setInt(3, ratingValue);
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
