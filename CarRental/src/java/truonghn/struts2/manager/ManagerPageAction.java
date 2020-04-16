/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.manager;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import truonghn.struts2.CarTool;
import truonghn.tbl_CarBrand.Tbl_CarBrandDTO;
import truonghn.tbl_CarColor.Tbl_CarColorDTO;
import truonghn.tbl_CarType.Tbl_CarTypeDTO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class ManagerPageAction {
    private String logo;
    private String banner;
    private String searchImg;
    private List<Tbl_CarBrandDTO> listOfBrand;
    private List<Tbl_CarTypeDTO> listOfType;
    private List<Tbl_CarColorDTO> listOfColor;
    private String carDateRent;
    private String carRentHour;
    private String carRentMin;
    private String carDateReturn;
    private String carReturnHour;
    private String carReturnMin;
    private String MANAGER_PAGE = "manager";
    public ManagerPageAction() {
    }
    
    public String execute() throws Exception {
        String url = MANAGER_PAGE;
        try {
            CarTool tool = new CarTool();
            loadDate();
            listOfBrand = tool.getCarBrandList();
            listOfType = tool.getCarTypeList();
            listOfColor = tool.getCarColorList();
            banner = Utils.getimage("banner");
            logo =  Utils.getimage("logo");
            searchImg =  Utils.getimage("seacrhImg");
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        } catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }
        finally {
            return url;
        }
    }
    private void loadDate(){
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
    }
    public List<Tbl_CarBrandDTO> getListOfBrand() {
        return listOfBrand;
    }

    public void setListOfBrand(List<Tbl_CarBrandDTO> listOfBrand) {
        this.listOfBrand = listOfBrand;
    }

    public List<Tbl_CarTypeDTO> getListOfType() {
        return listOfType;
    }

    public void setListOfType(List<Tbl_CarTypeDTO> listOfType) {
        this.listOfType = listOfType;
    }

    public List<Tbl_CarColorDTO> getListOfColor() {
        return listOfColor;
    }

    public void setListOfColor(List<Tbl_CarColorDTO> listOfColor) {
        this.listOfColor = listOfColor;
    }

    public String getMANAGER_PAGE() {
        return MANAGER_PAGE;
    }

    public void setMANAGER_PAGE(String MANAGER_PAGE) {
        this.MANAGER_PAGE = MANAGER_PAGE;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getSearchImg() {
        return searchImg;
    }

    public void setSearchImg(String searchImg) {
        this.searchImg = searchImg;
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
    
}
