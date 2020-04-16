/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.booking;

import com.opensymphony.xwork2.ActionContext;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import javax.naming.NamingException;
import truonghn.struts2.CarTool;
import truonghn.struts2.cart.CartObj;
import truonghn.tbl_Booking_Bill.Tbl_Booking_BillDAO;
import truonghn.tbl_Booking_Bill_Details.Tbl_Booking_Bill_DetailsDAO;
import truonghn.tbl_Car.Tbl_CarDTO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class AddCarToTblBookingAction {
    private String SEARCH_CAR_PAGE = "searchCar";
    private String FAIL_ADDING_TO_DB = "failAdding";
    private String notification;
    public AddCarToTblBookingAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL_ADDING_TO_DB;
        try {
            //get cart=====================================================
            Map session = ActionContext.getContext().getSession();
            CartObj sessionCart = (CartObj)session.get("CART");
            //check existed cart=============================================
            if(sessionCart != null){
                //get latest bill id=======================================================
                Tbl_Booking_BillDAO billDAO = new Tbl_Booking_BillDAO();
                int billID = billDAO.getNewestBillID(sessionCart.getCustomerID());
                if(billID>0){
                    CarTool tool = new CarTool();
                    String BOOKING_DETAIL_STATUS = "Active";
                    int bookingDetailStatusID = tool.getBookingDetailStatusID(BOOKING_DETAIL_STATUS);
                    Tbl_Booking_Bill_DetailsDAO billDetailDAO = new Tbl_Booking_Bill_DetailsDAO();
                    Timestamp curDate = Utils.getTime();
                    for (Map.Entry<Integer, Tbl_CarDTO> entry : sessionCart.getListOfCar().entrySet()) {
                        Tbl_CarDTO value = entry.getValue();
                            int carID = value.getC_id();
                            Timestamp rentDate = Timestamp.valueOf(value.getC_dateRent());
                            Timestamp returnDate = Timestamp.valueOf(value.getC_dateReturn());
                            int amount = value.getC_amount();
                            float price = value.getC_price();
                        billDetailDAO.addCarToBill(billID, carID, rentDate, returnDate, amount, bookingDetailStatusID, price, curDate);
                    }
                    session.put("CART",null);
                    session.remove("CART");
                    url = SEARCH_CAR_PAGE;
                }else{
                    notification = "Can not access your Bill!!!";
                }
            }else{
                notification = "FAIL booking your car!!!";
            }
            
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        }finally{
            return url;
        }
    }

    public String getSEARCH_CAR_PAGE() {
        return SEARCH_CAR_PAGE;
    }

    public void setSEARCH_CAR_PAGE(String SEARCH_CAR_PAGE) {
        this.SEARCH_CAR_PAGE = SEARCH_CAR_PAGE;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getFAIL_ADDING_TO_DB() {
        return FAIL_ADDING_TO_DB;
    }

    public void setFAIL_ADDING_TO_DB(String FAIL_ADDING_TO_DB) {
        this.FAIL_ADDING_TO_DB = FAIL_ADDING_TO_DB;
    }
    
}
