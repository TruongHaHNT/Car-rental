/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_UserInfo;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class Tbl_UserInfoDTO implements Serializable{
    private String u_email;
    private String u_name;
    private String u_password;
    private String u_role;
    private String u_status;
    private String u_phone;
    private String u_address;
    private String u_createDate;

    public Tbl_UserInfoDTO(String u_email, String u_name, String u_password, String u_role, String u_status, String u_phone, String u_address, String u_createDate) {
        this.u_email = u_email;
        this.u_name = u_name;
        this.u_password = u_password;
        this.u_role = u_role;
        this.u_status = u_status;
        this.u_phone = u_phone;
        this.u_address = u_address;
        this.u_createDate = u_createDate;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_role() {
        return u_role;
    }

    public void setU_role(String u_role) {
        this.u_role = u_role;
    }

    public String getU_status() {
        return u_status;
    }

    public void setU_status(String u_status) {
        this.u_status = u_status;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_createDate() {
        return u_createDate;
    }

    public void setU_createDate(String u_createDate) {
        this.u_createDate = u_createDate;
    }

    
    
}
