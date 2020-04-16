/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_CarColor;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class Tbl_CarColorDTO implements Serializable{
    private int c_col_id;
    private String c_col_name;

    public Tbl_CarColorDTO() {
    }

    public Tbl_CarColorDTO(int c_col_id, String c_col_name) {
        this.c_col_id = c_col_id;
        this.c_col_name = c_col_name;
    }

    public int getC_col_id() {
        return c_col_id;
    }

    public void setC_col_id(int c_col_id) {
        this.c_col_id = c_col_id;
    }

    public String getC_col_name() {
        return c_col_name;
    }

    public void setC_col_name(String c_col_name) {
        this.c_col_name = c_col_name;
    }
    
    
}
