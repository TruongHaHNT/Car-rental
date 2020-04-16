/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_Car;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class Tbl_CarDTO implements Serializable{
    private int c_id;
    private String c_name;
    private int c_amount;
    private String c_image;
    private String c_type;
    private String c_brand;
    private String c_status;
    private String c_dateRent;
    private String c_dateReturn;
    private float c_price;
    private String c_color;

    public Tbl_CarDTO(int c_id, String c_name, int c_amount, String c_image, String c_type, String c_brand, String c_status, String c_dateRent, String c_dateReturn, float c_price, String c_color) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_amount = c_amount;
        this.c_image = c_image;
        this.c_type = c_type;
        this.c_brand = c_brand;
        this.c_status = c_status;
        this.c_dateRent = c_dateRent;
        this.c_dateReturn = c_dateReturn;
        this.c_price = c_price;
        this.c_color = c_color;
    }

    public Tbl_CarDTO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public int getC_amount() {
        return c_amount;
    }

    public void setC_amount(int c_amount) {
        this.c_amount = c_amount;
    }

    public String getC_image() {
        return c_image;
    }

    public void setC_image(String c_image) {
        this.c_image = c_image;
    }

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    public String getC_brand() {
        return c_brand;
    }

    public void setC_brand(String c_brand) {
        this.c_brand = c_brand;
    }

    public String getC_status() {
        return c_status;
    }

    public void setC_status(String c_status) {
        this.c_status = c_status;
    }

    public String getC_dateRent() {
        return c_dateRent;
    }

    public void setC_dateRent(String c_dateRent) {
        this.c_dateRent = c_dateRent;
    }

    public String getC_dateReturn() {
        return c_dateReturn;
    }

    public void setC_dateReturn(String c_dateReturn) {
        this.c_dateReturn = c_dateReturn;
    }

    public float getC_price() {
        return c_price;
    }

    public void setC_price(float c_price) {
        this.c_price = c_price;
    }

    public String getC_color() {
        return c_color;
    }

    public void setC_color(String c_color) {
        this.c_color = c_color;
    }

    
}
