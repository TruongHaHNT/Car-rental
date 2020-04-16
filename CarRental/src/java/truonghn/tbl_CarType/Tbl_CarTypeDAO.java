/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_CarType;

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
public class Tbl_CarTypeDAO implements Serializable{
    public int getTypeID(String type) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = "SELECT c_ty_ID "
                        + " FROM tbl_CarType "
                        + " WHERE c_ty_name = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, type);
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
    
    List<Tbl_CarTypeDTO> list = null;

    public List<Tbl_CarTypeDTO> getList() {
        return list;
    }
    
    public void getCarTypeList(int StatusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "SELECT DISTINCT c_ty_ID, c_ty_name "
                        + " FROM tbl_Car, tbl_CarType "
                        + " WHERE c_ty_ID = c_typeID "
                        + " AND c_amount >= 1 "
                        + " AND c_statusID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, StatusID);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int typeID = rs.getInt(1);
                    String type = rs.getString(2);
                    Tbl_CarTypeDTO dto = new Tbl_CarTypeDTO(typeID, type);
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
    public void getCarTypeList() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "SELECT c_ty_ID, c_ty_name "
                        + " FROM tbl_CarType ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int typeID = rs.getInt(1);
                    String type = rs.getString(2);
                    Tbl_CarTypeDTO dto = new Tbl_CarTypeDTO(typeID, type);
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
