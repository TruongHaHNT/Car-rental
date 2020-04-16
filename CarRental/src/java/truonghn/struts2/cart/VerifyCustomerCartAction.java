/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.cart;

import com.opensymphony.xwork2.ActionContext;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import truonghn.struts2.CarTool;
import truonghn.tbl_Booking_Bill.Tbl_Booking_BillDAO;
import truonghn.tbl_Car.Tbl_CarDAO;
import truonghn.tbl_Car.Tbl_CarDTO;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class VerifyCustomerCartAction {
    private String notification;
    private String discountCode;
    private String SUCCESS = "success";
    private String FAIL = "fail";
    private String CHECK_AUTH = "loginRequired";
    public VerifyCustomerCartAction() {
    }
    
    public String execute() throws Exception {
        String url = CHECK_AUTH;
        try {
            Map session = ActionContext.getContext().getSession();
            if(session.get("EMAIL") != null){
                String email = session.get("EMAIL").toString();
                Tbl_UserInfoDAO userDao = new Tbl_UserInfoDAO();
                String userStatus = "Active";
                boolean isActive = userDao.isActive(email, userStatus);
                if(isActive){
                    String userRole = "Manager";
                    boolean isAdmin = userDao.isAdmin(email, userRole);
                    if(!isAdmin){
                        url =FAIL;
                        notification = "ERROR when Booking your Car!!!";
                        CarTool tool = new CarTool();
//                        Tbl_CarDAO cardao= new Tbl_CarDAO();
                        String STATUS = "Active";
                        int statusID = tool.getCarStatusID(STATUS);
                        String BOOKING_STATUS = "Active";
                        int bookingStatusID = tool.getBookingStatusID(BOOKING_STATUS);
                        String BOOKING_DETAIL_STATUS = "Active";
                        int bookingDetailStatusID = tool.getBookingStatusID(BOOKING_DETAIL_STATUS);
                        CartObj sessioncart = (CartObj)session.get("CART");
//                        get user cart================================================================
                        boolean isValid = false;
                        int disID = 0;
                        //check cart is existed==========================================
                        if(sessioncart != null){
                            //check is any car in cart=============================================
                            if(sessioncart.getListOfCar() != null){
                                //check discount code========================================================
                                if(discountCode.trim().isEmpty()){
                                    isValid = checkingYourCart(statusID, bookingStatusID, bookingDetailStatusID, sessioncart);
                                }else{
                                    int disStatusID = tool.getDiscountStatusID("Avail");
                                    disID = tool.getValidDiscountCode(discountCode, disStatusID);
                                    if(disID >0){
                                        isValid = checkingYourCart(statusID, bookingStatusID, bookingDetailStatusID, sessioncart);
                                    }else{
                                        notification = "Invalid DISCOUNT CODE!!!";
                                        isValid = false;
                                    }
                                }
                            }else{
                                notification = "Your cart has expired!!!";
                            }
                        }else{
                            notification = "Your cart has expired!!!";
                            isValid = false;
                        }
                        // if cart is valid==============================================================
                        if(isValid){
                            url = SUCCESS;
                            Tbl_Booking_BillDAO billDAO =new Tbl_Booking_BillDAO();
                            billDAO.createNewBill(email, Utils.getTime(), disID, bookingStatusID);
                            String discountStatus = "Used";
                            int discountStatusID = tool.getDiscountStatusID(discountStatus);
                            tool.changeDiscountStatus(disID, discountStatusID);
                            notification = "<<===-Booking successfuly.-===>>";
                        }else{
                            url =FAIL;
                        }
                    }
                }
            }           
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        } catch (ArrayIndexOutOfBoundsException e){
            Utils.myBlogFile(e.toString());
        }finally{
            return url;
        }    
    }

    private boolean checkingYourCart(int statusID, int bookingStatusID, int bookingDetailStatusID, CartObj sessioncart) throws SQLException, NamingException{
        boolean valid = false;
        int count = 0;
        for (Map.Entry<Integer, Tbl_CarDTO> entry : sessioncart.getListOfCar().entrySet()) {
            Tbl_CarDTO value = entry.getValue();
            Tbl_CarDAO dao = new Tbl_CarDAO();
            int validAmount = dao.getAvailableAmount(value.getC_id(), statusID, bookingStatusID, bookingDetailStatusID, value.getC_dateRent(), value.getC_dateReturn());
            if(validAmount > 0){
                if(validAmount < value.getC_amount()){
                    value.setC_status("This car only has "+validAmount+" unit(s) available at this moment!");
                    count++;
                }
            }else{
                    value.setC_status("This car is unavailable at this moment!");
                count++;
            }
        }
        if(count <= 0){
            valid = true;
        }
        return valid;
    }
    
    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(String SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public String getFAIL() {
        return FAIL;
    }

    public void setFAIL(String FAIL) {
        this.FAIL = FAIL;
    }

    public String getCHECK_AUTH() {
        return CHECK_AUTH;
    }

    public void setCHECK_AUTH(String CHECK_AUTH) {
        this.CHECK_AUTH = CHECK_AUTH;
    }
    
}
