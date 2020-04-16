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
import truonghn.tbl_Booking_Bill.Tbl_Booking_BillDAO;
import truonghn.tbl_Booking_Bill_Details.Tbl_Booking_Bill_DetailsDAO;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class DeleteSelectedCarInBillAction {
    private int billDetailsID;
    private int billID;
    private String CHECK_AUTH = "loginRequired";
    private String VIEW_BOOKING_CAR = "viewBooking";
    public DeleteSelectedCarInBillAction() {
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
                        Timestamp curTime = Utils.getTime();
                        CarTool tool = new CarTool();
                        String BOOKING_DETAIL_STATUS = "InActive";
                        int bookingDetailStatusID = tool.getBookingDetailStatusID(BOOKING_DETAIL_STATUS);
                        Tbl_Booking_Bill_DetailsDAO dtDAO = new Tbl_Booking_Bill_DetailsDAO();
                        dtDAO.updateDetailStatus(billDetailsID, bookingDetailStatusID, curTime);
                        
                        BOOKING_DETAIL_STATUS = "Active";
                        bookingDetailStatusID = tool.getBookingDetailStatusID(BOOKING_DETAIL_STATUS);
                        Tbl_Booking_BillDAO billDAO = new Tbl_Booking_BillDAO();
                        int row = billDAO.getNumberOfActiveCar(email, billID, bookingDetailStatusID);
                        if(row <= 0){
                            String BOOKING_STATUS = "InActive";
                            int bookingStatusID = tool.getBookingStatusID(BOOKING_STATUS);
                            billDAO.updateBillStatus(email, billID, bookingStatusID, curTime);
                        }
                        url = VIEW_BOOKING_CAR;
                    }
                }
            }
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        }finally{
            return url;
        }
    }

    public int getBillDetailsID() {
        return billDetailsID;
    }

    public void setBillDetailsID(int billDetailsID) {
        this.billDetailsID = billDetailsID;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getCHECK_AUTH() {
        return CHECK_AUTH;
    }

    public void setCHECK_AUTH(String CHECK_AUTH) {
        this.CHECK_AUTH = CHECK_AUTH;
    }

    public String getVIEW_BOOKING_CAR() {
        return VIEW_BOOKING_CAR;
    }

    public void setVIEW_BOOKING_CAR(String VIEW_BOOKING_CAR) {
        this.VIEW_BOOKING_CAR = VIEW_BOOKING_CAR;
    }
    
    
}
