/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_CarBrand;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class Tbl_CarBrandDTO implements Serializable{
    private int c_br_id;
    private String c_br_name;

    public Tbl_CarBrandDTO(int c_br_id, String c_br_name) {
        this.c_br_id = c_br_id;
        this.c_br_name = c_br_name;
    }

    public int getC_br_id() {
        return c_br_id;
    }

    public void setC_br_id(int c_br_id) {
        this.c_br_id = c_br_id;
    }

    public String getC_br_name() {
        return c_br_name;
    }

    public void setC_br_name(String c_br_name) {
        this.c_br_name = c_br_name;
    }

   
}
