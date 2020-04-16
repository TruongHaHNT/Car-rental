/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.booking;

import java.io.Serializable;
import java.util.List;
import truonghn.tbl_Booking_Bill.Tbl_Booking_BillDTO;
import truonghn.tbl_Car.Tbl_CarDTO;

/**
 *
 * @author SE130204
 */
public class BookingBillObj implements Serializable{
    private Tbl_Booking_BillDTO bill;
    private boolean isEnded;
    private List<Tbl_CarDTO> list;

    public BookingBillObj(Tbl_Booking_BillDTO bill, boolean isEnded, List<Tbl_CarDTO> list) {
        this.bill = bill;
        this.isEnded = isEnded;
        this.list = list;
    }


    public BookingBillObj() {
    }

    public Tbl_Booking_BillDTO getBill() {
        return bill;
    }

    public void setBill(Tbl_Booking_BillDTO bill) {
        this.bill = bill;
    }

    public List<Tbl_CarDTO> getList() {
        return list;
    }

    public void setList(List<Tbl_CarDTO> list) {
        this.list = list;
    }

    public boolean isIsEnded() {
        return isEnded;
    }

    public void setIsEnded(boolean isEnded) {
        this.isEnded = isEnded;
    }
   
}
