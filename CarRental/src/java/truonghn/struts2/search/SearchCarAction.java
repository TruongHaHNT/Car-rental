/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.search;

import java.sql.SQLException;
import javax.naming.NamingException;
import truonghn.tbl_Car.Tbl_CarDAO;
import truonghn.utils.Utils;
import java.util.List;
import truonghn.struts2.CarTool;
import truonghn.tbl_Car.Tbl_CarDTO;

/**
 *
 * @author SE130204
 */
public class SearchCarAction{
    private String CAR_RENTAL_PAGE = "carRentalPage";
    private String FAIL = "fail";
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
    private int curPage;
    private List<Tbl_CarDTO> listOfCar;
    private int totalPage;
    
    public SearchCarAction() {
    }
    private CarTool tool = new CarTool();
    public String execute() throws Exception{
        String url = CAR_RENTAL_PAGE;
        try {
            checkParam();
            String STATUS = "Active";
            String BOOKING_STATUS = "Active";
            String BOOKING_DETAIL_STATUS = "Active";
            int statusID = tool.getCarStatusID(STATUS);
            int bookingStatusID = tool.getBookingStatusID(BOOKING_STATUS);
            int bookingDetailStatusID = tool.getBookingDetailStatusID(BOOKING_DETAIL_STATUS);
            listOfCar = getCarList(statusID,bookingStatusID,bookingDetailStatusID);
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
    
    private void checkParam(){
        if(carType == null){
            carType = "any";
        }
        if(carBrand == null){
            carBrand = "any";
        }
        if(carName == null){
            carName = "";
        }
        if(carMinAmount == null){
            carMinAmount = "1";
        }else if(carMinAmount.trim().isEmpty()){
            carMinAmount = "1";
        }
        
        
        if(carDateRent == null){
            carDateRent = Utils.getTime_onedayAfter().toString().split("\\s+")[0];
        }else if(carDateRent.trim().isEmpty()){
            carDateRent = Utils.getTime_onedayAfter().toString().split("\\s+")[0];
        }
        if(carDateReturn == null){
            carDateReturn = Utils.getTime_onedayAfter().toString().split("\\s+")[0];
        }else if(carDateReturn.trim().isEmpty()){
            carDateReturn = Utils.getTime_onedayAfter().toString().split("\\s+")[0];
        }
        if(carRentHour == null){
            carRentHour = "7";
        }else if(carRentHour.isEmpty()){
            carRentHour = "7";
        }
        if(carRentMin == null){
            carRentMin = "0";
        }else if(carRentMin.isEmpty()){
            carRentMin = "0";
        }
        if(carReturnHour == null){
            carReturnHour = "22";
        }else if(carReturnHour.isEmpty()){
            carReturnHour = "22";
        }
        if(carReturnMin == null){
            carReturnMin = "0";
        }else if(carReturnMin.isEmpty()){
            carReturnMin = "0";
        }
        if(curPage == 0){
            curPage = 1;
        }
    }
    
    private List<Tbl_CarDTO> getCarList(int statusID, int bookingStatusID, int bookingDetailStatusID) throws SQLException, NamingException, NullPointerException{
        int min = Integer.parseInt(carMinAmount);
        int max;
        
        if(carMaxAmount == null){
            max = Integer.MAX_VALUE;
        }else if(carMaxAmount.trim().isEmpty()){
            max = Integer.MAX_VALUE;
        }else{
            max = Integer.parseInt(carMaxAmount);
        }
        
        int typeID = tool.getCarTypeID(carType);
        int brandID = tool.getCarBrandID(carBrand);
        Tbl_CarDAO cDao = new Tbl_CarDAO();
        String rentD = carDateRent+" "+carRentHour+":"+carRentMin+":00";
        String returnD = carDateReturn+" "+carReturnHour+":"+carReturnMin+":00";
        cDao.getCarList(carName,typeID,brandID,statusID,min,max,rentD,returnD,bookingStatusID, bookingDetailStatusID,curPage,Utils.TOTAL_CONTENT_PER_PAGE);
        int total = cDao.getTotalPage(carName,typeID,brandID,statusID,min,max,rentD,returnD,bookingStatusID, bookingDetailStatusID,Utils.TOTAL_CONTENT_PER_PAGE);
        totalPage = total;
        return cDao.getList();
    }
    
    public String getCAR_RENTAL_PAGE() {
        return CAR_RENTAL_PAGE;
    }

    public void setCAR_RENTAL_PAGE(String CAR_RENTAL_PAGE) {
        this.CAR_RENTAL_PAGE = CAR_RENTAL_PAGE;
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

    public String getCarDateRent() {
        return carDateRent;
    }

    public void setCarDateRent(String carDateRent) {
        this.carDateRent = carDateRent;
    }

    public String getCarDateReturn() {
        return carDateReturn;
    }

    public void setCarDateReturn(String carDateReturn) {
        this.carDateReturn = carDateReturn;
    }

    public String getFAIL() {
        return FAIL;
    }

    public void setFAIL(String FAIL) {
        this.FAIL = FAIL;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<Tbl_CarDTO> getListOfCar() {
        return listOfCar;
    }

    public void setListOfCar(List<Tbl_CarDTO> listOfCar) {
        this.listOfCar = listOfCar;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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
    
}
