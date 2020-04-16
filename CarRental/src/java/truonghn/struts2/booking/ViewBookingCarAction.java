/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.booking;

import com.opensymphony.xwork2.ActionContext;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
//import truonghn.struts2.CarTool;
import truonghn.tbl_Booking_Bill.Tbl_Booking_BillDAO;
import truonghn.tbl_Booking_Bill.Tbl_Booking_BillDTO;
import truonghn.tbl_Car.Tbl_CarDAO;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class ViewBookingCarAction {
    private String VIEW_BOOKING_CAR = "viewBooking";
    private String CHECK_AUTH = "loginRequired";
    private List<BookingBillObj> bookingList;
    private String bookingSearch;
    private String bookingMinDate;
    private String bookingMaxDate;
    private int curPage;
    private int totalPage;
    public ViewBookingCarAction() {
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
                        checkInput();
                        String minDate = bookingMinDate +" 00:00:00.000";
                        String maxDate = bookingMaxDate +" 23:59:59.999";
                        url =VIEW_BOOKING_CAR;
//                        CarTool tool = new CarTool();
                        Tbl_Booking_BillDAO bkDao = new Tbl_Booking_BillDAO();
                        bkDao.getBillList(email, Timestamp.valueOf(minDate), Timestamp.valueOf(maxDate), bookingSearch, curPage, Utils.TOTAL_HISTORY_PAGE);
                        List<Tbl_Booking_BillDTO> getBillList = bkDao.getList();
                        if(getBillList != null){
                            String BOOKING_DETAILS_STATUS = "Active";
                            for (Tbl_Booking_BillDTO billDTO : getBillList) {
                                if(bookingList == null){
                                    bookingList = new ArrayList<>();
                                }
                                Timestamp curTime = Utils.getTime();
                                Tbl_CarDAO bkDetailDao = new Tbl_CarDAO();
                                
                                boolean isEnded = bkDetailDao.getCarDetailsList(bookingSearch, billDTO.getBill_ID(), curTime, BOOKING_DETAILS_STATUS);
                                bookingList.add(new BookingBillObj(billDTO, isEnded, bkDetailDao.getList()));
                            }
                        }
                        totalPage = bkDao.getTotalBill(email, Timestamp.valueOf(minDate), Timestamp.valueOf(maxDate), bookingSearch, Utils.TOTAL_HISTORY_PAGE);
                    }
                }
            }
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        } catch (NullPointerException e) {
            Utils.myBlogFile(e.toString());
        } catch (NumberFormatException e) {
            Utils.myBlogFile(e.toString());
        } 
        finally{
            return url;
        }
    }
    
    private void checkInput(){
        if(bookingSearch == null){
            bookingSearch = "";
        }
        if(bookingMinDate == null){
            bookingMinDate = Utils.minDate;
        }else if(bookingMinDate.trim().isEmpty()){
            bookingMinDate = Utils.minDate;
        }
        
        if(bookingMaxDate == null){
            bookingMaxDate = ""+Utils.maxDate;
        }else if(bookingMaxDate.trim().isEmpty()){
            bookingMaxDate = ""+Utils.maxDate;
        }
        
        if(curPage <= 0){
            curPage = 1;
        }
        
    }
    
    public String getVIEW_BOOKING_CAR() {
        return VIEW_BOOKING_CAR;
    }

    public void setVIEW_BOOKING_CAR(String VIEW_BOOKING_CAR) {
        this.VIEW_BOOKING_CAR = VIEW_BOOKING_CAR;
    }

    public String getBookingSearch() {
        return bookingSearch;
    }

    public void setBookingSearch(String bookingSearch) {
        this.bookingSearch = bookingSearch;
    }

    public String getBookingMinDate() {
        return bookingMinDate;
    }

    public void setBookingMinDate(String bookingMinDate) {
        this.bookingMinDate = bookingMinDate;
    }

    public String getBookingMaxDate() {
        return bookingMaxDate;
    }

    public void setBookingMaxDate(String bookingMaxDate) {
        this.bookingMaxDate = bookingMaxDate;
    }

    public List<BookingBillObj> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<BookingBillObj> bookingList) {
        this.bookingList = bookingList;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getCHECK_AUTH() {
        return CHECK_AUTH;
    }

    public void setCHECK_AUTH(String CHECK_AUTH) {
        this.CHECK_AUTH = CHECK_AUTH;
    }
    
}
