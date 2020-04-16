/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.search;

import java.sql.SQLException;
import javax.naming.NamingException;
import truonghn.struts2.CarTool;
import truonghn.tbl_Car.Tbl_CarDAO;
import truonghn.tbl_Car.Tbl_CarDTO;
import truonghn.tbl_Rating.Tbl_RatingDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class ViewCarDetailAction {
    private int cartCarID;
    private String carType;
    private String carBrand;
    private String carName;
    private String carMinAmount;
    private String carMaxAmount;
    private String carDateRent;
    private String carRentHour;
    private String carRentMin;
    private String carDateReturn;
    private String carReturnHour;
    private String carReturnMin;
    private float carRatingValue;
    private String VIEW_CAR_INFORMATION = "view";
    private String FAIL = "fail";
    private Tbl_CarDTO getcarInformation;
    public ViewCarDetailAction() {
    }
    
    public String execute() throws Exception{
        String url = FAIL;
        try {
            checkInput();
            int min = Integer.parseInt(carMinAmount);
            int max;
        
            if(carMaxAmount == null){
                max = Integer.MAX_VALUE;
            }else if(carMaxAmount.trim().isEmpty()){
                max = Integer.MAX_VALUE;
            }else{
                max = Integer.parseInt(carMaxAmount);
            }
                CarTool tool = new CarTool();
                Tbl_CarDAO cardao = new Tbl_CarDAO();
                String STATUS = "Active";
                String BOOKING_STATUS = "Active";
                String BOOKING_DETAIL_STATUS = "Active";
                int bookingDetailStatusID = tool.getBookingDetailStatusID(BOOKING_DETAIL_STATUS);
                int statusID = tool.getCarStatusID(STATUS);
                int bookingStatusID = tool.getBookingStatusID(BOOKING_STATUS);
                String cartrentDate = carDateRent+" "+carRentHour+":"+carRentMin+":00";
                String CartreturnDate = carDateReturn+" "+carReturnHour+":"+carReturnMin+":00";
                getcarInformation = cardao.getCarInfo(cartCarID, statusID, bookingStatusID, bookingDetailStatusID, min, max, cartrentDate, CartreturnDate);
                if(getcarInformation != null){
                    Tbl_RatingDAO rtDAO = new Tbl_RatingDAO();
                    carRatingValue = rtDAO.getCarRating(cartCarID, statusID);
                }
                url = VIEW_CAR_INFORMATION;
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        } catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        } catch(NumberFormatException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
            return url;
        }
    }
    private void checkInput(){
        if(carMinAmount == null){
            carMinAmount = "1";
        }else if(carMinAmount.trim().isEmpty()){
            carMinAmount = "1";
        }
        
    }   
    public int getCartCarID() {
        return cartCarID;
    }

    public void setCartCarID(int cartCarID) {
        this.cartCarID = cartCarID;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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

    public String getVIEW_CAR_INFORMATION() {
        return VIEW_CAR_INFORMATION;
    }

    public void setVIEW_CAR_INFORMATION(String VIEW_CAR_INFORMATION) {
        this.VIEW_CAR_INFORMATION = VIEW_CAR_INFORMATION;
    }

    public String getFAIL() {
        return FAIL;
    }

    public void setFAIL(String FAIL) {
        this.FAIL = FAIL;
    }

    public Tbl_CarDTO getGetcarInformation() {
        return getcarInformation;
    }

    public void setGetcarInformation(Tbl_CarDTO getcarInformation) {
        this.getcarInformation = getcarInformation;
    }

    public String getCarMinAmount() {
        return carMinAmount;
    }

    public void setCarMinAmount(String carMinAmount) {
        this.carMinAmount = carMinAmount;
    }

    public String getCarMaxAmount() {
        return carMaxAmount;
    }

    public void setCarMaxAmount(String carMaxAmount) {
        this.carMaxAmount = carMaxAmount;
    }

    public float getCarRatingValue() {
        return carRatingValue;
    }

    public void setCarRatingValue(float carRatingValue) {
        this.carRatingValue = carRatingValue;
    }

    
}
