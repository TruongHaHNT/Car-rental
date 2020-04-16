/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import truonghn.tbl_CarBrand.Tbl_CarBrandDTO;
import truonghn.tbl_CarType.Tbl_CarTypeDTO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class LoadLayoutAction {
    private String logo;
    private String banner;
    private String searchImg;
    private String searchIcon;
    private String btAction;
    private List<Tbl_CarBrandDTO> listOfBrand;
    private List<Tbl_CarTypeDTO> listOfType;
    private String CAR_RENTAL = "carRental";
    private String VIEW_CAR_DETAIL_PAGE = "view";
    private String VIEW_CAR_IN_CART_PAGE = "cart";
    private String VIEW_ORDER_HISTORY_PAGE = "history";
    
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

    public String getSearchIcon() {
        return searchIcon;
    }

    public void setSearchIcon(String searchIcon) {
        this.searchIcon = searchIcon;
    }

    public String getCAR_RENTAL() {
        return CAR_RENTAL;
    }

    public void setCAR_RENTAL(String CAR_RENTAL) {
        this.CAR_RENTAL = CAR_RENTAL;
    }
    
    public LoadLayoutAction() {
    }
    
    public String execute() throws Exception {
        String url = CAR_RENTAL;
        try {
            if(btAction == null){
                getImgForWebPage();
                getSearchEngine();
                url = CAR_RENTAL;
            }
            else if(btAction.equals("View detail")
                    || btAction.equals("Submit Rating")){
                getImgForWebPage();
                getSearchEngine();
                url = VIEW_CAR_DETAIL_PAGE; 
            }
            else if(btAction.equals("Car rentals") || btAction.trim().length() == 0){
                getImgForWebPage();
                getSearchEngine();
                url = CAR_RENTAL;
            }
            else if(btAction.equals("Cart") 
                    || btAction.equals("Confirm Delete") 
                    || btAction.equals("Confirm Update")){
                getImgForWebPage();
                url = VIEW_CAR_IN_CART_PAGE;
            }
            else if(btAction.equals("Manage Booking")){
                getImgForWebPage();
                getSearchEngine();
                url = VIEW_ORDER_HISTORY_PAGE;
            }else{
                getImgForWebPage();
                getSearchEngine();
                url = CAR_RENTAL;
            }
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        } catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }finally{
            return url;
        }
    }
    
    private void getImgForWebPage() throws SQLException, NamingException{
        banner=Utils.getimage("banner");
        logo =  Utils.getimage("logo");
        searchIcon =  Utils.getimage("searchIcon");
    }
    private void getSearchEngine() throws SQLException, NamingException{
        String status = "Active";
        CarTool tool = new CarTool();
        int statusID = tool.getCarStatusID(status);
        listOfBrand = tool.getCarBrandList(statusID);
        listOfType = tool.getCarTypeList(statusID);
        searchImg =  Utils.getimage("seacrhImg");
    }

    public String getBtAction() {
        return btAction;
    }

    public void setBtAction(String btAction) {
        this.btAction = btAction;
    }

    public String getVIEW_CAR_DETAIL_PAGE() {
        return VIEW_CAR_DETAIL_PAGE;
    }

    public void setVIEW_CAR_DETAIL_PAGE(String VIEW_CAR_DETAIL_PAGE) {
        this.VIEW_CAR_DETAIL_PAGE = VIEW_CAR_DETAIL_PAGE;
    }

    public String getVIEW_CAR_IN_CART_PAGE() {
        return VIEW_CAR_IN_CART_PAGE;
    }

    public void setVIEW_CAR_IN_CART_PAGE(String VIEW_CAR_IN_CART_PAGE) {
        this.VIEW_CAR_IN_CART_PAGE = VIEW_CAR_IN_CART_PAGE;
    }

    public String getVIEW_ORDER_HISTORY_PAGE() {
        return VIEW_ORDER_HISTORY_PAGE;
    }

    public void setVIEW_ORDER_HISTORY_PAGE(String VIEW_ORDER_HISTORY_PAGE) {
        this.VIEW_ORDER_HISTORY_PAGE = VIEW_ORDER_HISTORY_PAGE;
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
        

    public String getSearchImg() {
        return searchImg;
    }

    public void setSearchImg(String searchImg) {
        this.searchImg = searchImg;
    }
    
}
