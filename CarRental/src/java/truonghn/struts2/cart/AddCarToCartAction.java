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
import truonghn.tbl_Car.Tbl_CarDAO;
import truonghn.tbl_Car.Tbl_CarDTO;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class AddCarToCartAction{
    private int cartCarID;
    private int cartCarAmount;
    private String carDateRent;
    private String carRentHour;
    private String carRentMin;
    private String carDateReturn;
    private String carReturnHour;
    private String carReturnMin;
    private String SUCCESS = "success";
    private String FAIL = "fail";
    private String SIGN_IN = "auth";
    private String notification;
    public AddCarToCartAction() {
    }
    
    public String execute(){
        String url = SIGN_IN;
        try {
            cartCarAmount = cartCarAmount <=0 ? 1 : cartCarAmount; 
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
                        notification = "ERROR when adding car to cart !!!";
                        CarTool tool = new CarTool();
                        String cartrentDate = carDateRent+" "+carRentHour+":"+carRentMin+":00";
                        String CartreturnDate = carDateReturn+" "+carReturnHour+":"+carReturnMin+":00";
                        Tbl_CarDAO cardao= new Tbl_CarDAO();
                        String STATUS = "Active";
                        int statusID = tool.getCarStatusID(STATUS);
                        String BOOKING_STATUS = "Active";
                        int bookingStatusID = tool.getBookingStatusID(BOOKING_STATUS);
                        String BOOKING_DETAIL_STATUS = "Active";
                        int bookingDetailStatusID = tool.getBookingStatusID(BOOKING_DETAIL_STATUS);
                        int validAmount = cardao.getAvailableAmount(cartCarID, statusID, bookingStatusID, bookingDetailStatusID, cartrentDate, CartreturnDate);
                        if(validAmount > 0 && validAmount >= cartCarAmount){
                            CartObj sessioncart = (CartObj)session.get("CART");
                            if(sessioncart == null){
                                sessioncart = new CartObj();
                                sessioncart.setCustomerID(email);
                            }
                            Tbl_CarDTO carInfo = cardao.getCarInfo(cartCarID, cartrentDate, CartreturnDate, cartCarAmount);    
                            sessioncart.addNewCarToCart(carInfo);
                            session.put("CART", sessioncart);
                            notification = "<=== Car has been added to your cart. ===>";
                            url = SUCCESS;
                            
                            
                        }
                    }
                }
            }
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        } catch (NullPointerException e) {
            Utils.myBlogFile(e.toString());
        } finally{
            return url;
        }
    }

    public int getCartCarID() {
        return cartCarID;
    }

    public void setCartCarID(int cartCarID) {
        this.cartCarID = cartCarID;
    }

    public String getCarDateRent() {
        return carDateRent;
    }

    public void setCarDateRent(String carDateRent) {
        this.carDateRent = carDateRent;
    }

    public String getCarRentHour() {
        return carRentHour;
    }

    public void setCarRentHour(String carRentHour) {
        this.carRentHour = carRentHour;
    }

    public String getCarRentMin() {
        return carRentMin;
    }

    public void setCarRentMin(String carRentMin) {
        this.carRentMin = carRentMin;
    }

    public String getCarDateReturn() {
        return carDateReturn;
    }

    public void setCarDateReturn(String carDateReturn) {
        this.carDateReturn = carDateReturn;
    }

    public String getCarReturnHour() {
        return carReturnHour;
    }

    public void setCarReturnHour(String carReturnHour) {
        this.carReturnHour = carReturnHour;
    }

    public String getCarReturnMin() {
        return carReturnMin;
    }

    public void setCarReturnMin(String carReturnMin) {
        this.carReturnMin = carReturnMin;
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

    public String getSIGN_IN() {
        return SIGN_IN;
    }

    public void setSIGN_IN(String SIGN_IN) {
        this.SIGN_IN = SIGN_IN;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public int getCartCarAmount() {
        return cartCarAmount;
    }

    public void setCartCarAmount(int cartCarAmount) {
        this.cartCarAmount = cartCarAmount;
    }

}
