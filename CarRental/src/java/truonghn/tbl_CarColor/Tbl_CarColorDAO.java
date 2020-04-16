/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_CarColor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class Tbl_CarColorDAO implements Serializable{
    List<Tbl_CarColorDTO> list = null;

    public List<Tbl_CarColorDTO> getList() {
        return list;
    }
    
    public int getColorID(String color) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = "SELECT c_col_ID "
                        + " FROM tbl_CarColor "
                        + " WHERE c_col_name = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, color);
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
    
    public void getCarColorList() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "SELECT c_col_ID, c_col_name "
                        + " FROM tbl_CarColor ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int brandID = rs.getInt(1);
                    String brand = rs.getString(2);
                    Tbl_CarColorDTO dto = new Tbl_CarColorDTO(brandID, brand);
                    if(list == null){
                        list = new ArrayList<>();
                    }
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
}
