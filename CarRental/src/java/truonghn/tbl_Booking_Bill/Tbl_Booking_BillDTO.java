/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_Booking_Bill;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class Tbl_Booking_BillDTO implements Serializable{
    private int bill_ID;
    private String bill_u_email;
    private String bill_booking_Date;
    private String bill_discount;
    private String bill_status;

    public Tbl_Booking_BillDTO(int bill_ID, String bill_u_email, String bill_booking_Date, String bill_discount, String bill_status) {
        this.bill_ID = bill_ID;
        this.bill_u_email = bill_u_email;
        this.bill_booking_Date = bill_booking_Date;
        this.bill_discount = bill_discount;
        this.bill_status = bill_status;
    }

    public int getBill_ID() {
        return bill_ID;
    }

    public void setBill_ID(int bill_ID) {
        this.bill_ID = bill_ID;
    }

    public String getBill_u_email() {
        return bill_u_email;
    }

    public void setBill_u_email(String bill_u_email) {
        this.bill_u_email = bill_u_email;
    }

    public String getBill_booking_Date() {
        return bill_booking_Date;
    }

    public void setBill_booking_Date(String bill_booking_Date) {
        this.bill_booking_Date = bill_booking_Date;
    }

    public String getBill_discount() {
        return bill_discount;
    }

    public void setBill_discount(String bill_discount) {
        this.bill_discount = bill_discount;
    }

    public String getBill_status() {
        return bill_status;
    }

    public void setBill_status(String bill_status) {
        this.bill_status = bill_status;
    }

}
