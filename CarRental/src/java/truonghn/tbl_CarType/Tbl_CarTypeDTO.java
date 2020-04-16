/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_CarType;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class Tbl_CarTypeDTO implements Serializable{
    private int c_ty_id;
    private String c_ty_name;

    public Tbl_CarTypeDTO(int c_st_id, String c_st_name) {
        this.c_ty_id = c_st_id;
        this.c_ty_name = c_st_name;
    }

    public int getC_ty_id() {
        return c_ty_id;
    }

    public void setC_ty_id(int c_ty_id) {
        this.c_ty_id = c_ty_id;
    }

    public String getC_ty_name() {
        return c_ty_name;
    }

    public void setC_ty_name(String c_ty_name) {
        this.c_ty_name = c_ty_name;
    }

    
}
