/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.rating;

import com.opensymphony.xwork2.ActionContext;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import javax.naming.NamingException;
import truonghn.struts2.CarTool;
import truonghn.tbl_Car.Tbl_CarDAO;
import truonghn.tbl_Rating.Tbl_RatingDAO;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class CarRatingAction {
    private String CHECK_AUTH = "loginRequired";
    private String SEARCH_CAR = "view";
    private int carID;
    private int ratingValue;
    private String notification;
    public CarRatingAction() {
    }
    
    public String execute() throws Exception{
        String url = CHECK_AUTH;
        try{
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
                        url = SEARCH_CAR;
                        CarTool tool = new CarTool();
                        Tbl_CarDAO cDAO = new Tbl_CarDAO();
                        Timestamp curDate = Utils.getTime();
                        String STATUS = "Active";
                        int statusID = tool.getCarStatusID(STATUS);
                        String BOOKING_DETAIL_STATUS = "Active";
                        int bookingDetailStatusID = tool.getBookingDetailStatusID(BOOKING_DETAIL_STATUS);
                        boolean isRenting = cDAO.isRenting(statusID, curDate, carID, email, bookingDetailStatusID);
                        if(isRenting){
                            Tbl_RatingDAO rtDAO = new Tbl_RatingDAO();
                            rtDAO.rateThisCar(carID, email, ratingValue);
                            notification = "Your rating has been submitted.";
                        }else{
                            notification = "You have not rented this car yet!!!";
                        }
                    }
                }
            }
        }catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        } 
        finally{
            return url;
        }
    }

    public String getCHECK_AUTH() {
        return CHECK_AUTH;
    }

    public void setCHECK_AUTH(String CHECK_AUTH) {
        this.CHECK_AUTH = CHECK_AUTH;
    }

    public String getSEARCH_CAR() {
        return SEARCH_CAR;
    }

    public void setSEARCH_CAR(String SEARCH_CAR) {
        this.SEARCH_CAR = SEARCH_CAR;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
    
}
