/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_CarBrand;

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
public class Tbl_CarBrandDAO implements Serializable{
    public int getBrandID(String brand) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = "SELECT c_br_ID "
                        + " from tbl_CarBrand "
                        + " where c_br_name = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, brand);
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
    
    private List<Tbl_CarBrandDTO> list;

    public List<Tbl_CarBrandDTO> getList() {
        return list;
    }
    
    public void getCarBrandList(int StatusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "SELECT DISTINCT c_br_ID, c_br_name "
                        + " FROM tbl_Car, tbl_CarBrand "
                        + " WHERE c_br_ID = c_brandID "
                        + " AND c_amount >= 1 "
                        + " AND c_statusID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, StatusID);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int brandID = rs.getInt(1);
                    String brand = rs.getString(2);
                    Tbl_CarBrandDTO dto = new Tbl_CarBrandDTO(brandID, brand);
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
    
    public void getCarBrandList() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "SELECT c_br_ID, c_br_name "
                        + " FROM tbl_CarBrand ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int brandID = rs.getInt(1);
                    String brand = rs.getString(2);
                    Tbl_CarBrandDTO dto = new Tbl_CarBrandDTO(brandID, brand);
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
