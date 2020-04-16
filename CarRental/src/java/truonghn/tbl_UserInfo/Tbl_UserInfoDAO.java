/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_UserInfo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class Tbl_UserInfoDAO implements Serializable{
    public boolean checkExistedUser(String email, String password) throws SQLException, NamingException, NoSuchAlgorithmException, UnsupportedEncodingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "SELECT u_email, u_password "
                        + " FROM tbl_UserInfo "
                        + " WHERE u_email = ? and u_password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                password = Utils.encryptPass(password);
                stm.setString(2, password);
                
                rs = stm.executeQuery();
                
                if(rs.next()){
                    return true;
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
        return false;
    }
    
    public boolean isActive(String email, String status) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="SELECT u_st_status "
                        + " FROM tbl_UserInfo, tbl_UserStatus "
                        + " WHERE u_st_statusID = u_statusID "
                        + " AND u_email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    if(rs.getString(1).matches(status)){
                        return true;
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
        return false;
    }
    
    public boolean isAdmin(String email , String role) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="SELECT u_rl_role "
                        + " FROM tbl_UserInfo, tbl_UserRole "
                        + " WHERE u_rl_roleID = u_roleID "
                        + " AND u_email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    if(rs.getString(1).matches(role)){
                        return true;
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
        return false;
    }
    
    private List<Tbl_UserInfoDTO> list = null;

    public List<Tbl_UserInfoDTO> getList() {
        return list;
    }
    
    public Tbl_UserInfoDTO getUserinfo(String email) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Tbl_UserInfoDTO dto = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="SELECT u_email, u_name, u_rl_role, u_st_status"
                        + " FROM tbl_UserInfo, tbl_UserRole, tbl_UserStatus "
                        + " WHERE u_roleID = u_rl_roleID"
                        + " AND u_statusID = u_st_statusID"
                        + " AND u_email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    String useremail = rs.getString(1);
                    String username = rs.getString(2);
                    String role = rs.getString(3);
                    String status = rs.getString(4);
                    dto = new Tbl_UserInfoDTO(useremail, username, null, role, status, null, null, null);
                    return dto;
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
        return dto;
    }
    
    public boolean checkExisted(String email) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="SELECT u_email "
                        + " FROM tbl_UserInfo "
                        + " WHERE u_email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    return true;
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
        return false;
    }
    
    public void activeUserStatus(String email, String statusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql ="UPDATE tbl_UserInfo SET u_statusID = ? " +
                            " WHERE u_email = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, statusID);
                stm.setString(2, email);
                
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

    public void createNewAccount(String email, String name, String password, String roleID, String statusID, String phone, String address) throws SQLException, NamingException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = "INSERT INTO tbl_UserInfo(u_email, u_name, u_password, u_roleID, u_statusID, u_phone, u_address, u_createDate) "
                        + "VALUES (?,?,?,?,?,?,?,?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, email.toLowerCase());
                stm.setString(2, name);
                password = Utils.encryptPass(password);
                stm.setString(3, password);
                stm.setString(4, roleID);
                stm.setString(5, statusID);
                stm.setString(6, phone);
                stm.setString(7, address);
                stm.setTimestamp(8, Utils.getTime());
                
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
}
