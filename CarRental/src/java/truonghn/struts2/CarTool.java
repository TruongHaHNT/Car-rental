/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import truonghn.tbl_Booking_Details_Status.Tbl_Booking_Details_StatusDAO;
import truonghn.tbl_Booking_Status.Tbl_Booking_StatusDAO;
import truonghn.tbl_CarBrand.Tbl_CarBrandDAO;
import truonghn.tbl_CarBrand.Tbl_CarBrandDTO;
import truonghn.tbl_CarColor.Tbl_CarColorDAO;
import truonghn.tbl_CarColor.Tbl_CarColorDTO;
import truonghn.tbl_CarStatus.Tbl_CarStatusDAO;
import truonghn.tbl_CarType.Tbl_CarTypeDAO;
import truonghn.tbl_CarType.Tbl_CarTypeDTO;
import truonghn.tbl_Discount.Tbl_DiscountDAO;
import truonghn.tbl_Discount_Status.Tbl_Discount_StatusDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class CarTool implements Serializable{
    public List<Tbl_CarBrandDTO> getCarBrandList(int statusID) throws SQLException, NamingException{
        Tbl_CarBrandDAO dao = new Tbl_CarBrandDAO();
        dao.getCarBrandList(statusID);
        return dao.getList();
    }
    public List<Tbl_CarTypeDTO> getCarTypeList(int statusID) throws SQLException, NamingException{
        Tbl_CarTypeDAO dao = new Tbl_CarTypeDAO();
        dao.getCarTypeList(statusID);
        return dao.getList();
    }
    public List<Tbl_CarBrandDTO> getCarBrandList() throws SQLException, NamingException{
        Tbl_CarBrandDAO dao = new Tbl_CarBrandDAO();
        dao.getCarBrandList();
        return dao.getList();
    }
    public List<Tbl_CarTypeDTO> getCarTypeList() throws SQLException, NamingException{
        Tbl_CarTypeDAO dao = new Tbl_CarTypeDAO();
        dao.getCarTypeList();
        return dao.getList();
    }
    public List<Tbl_CarColorDTO> getCarColorList() throws SQLException, NamingException{
        Tbl_CarColorDAO dao = new Tbl_CarColorDAO();
        dao.getCarColorList();
        return dao.getList();
    }
    
    public int getCarTypeID(String type) throws SQLException, NamingException{
        Tbl_CarTypeDAO dao = new Tbl_CarTypeDAO();
        return dao.getTypeID(type);
    }
    public int getCarColorID(String color) throws SQLException, NamingException{
        Tbl_CarColorDAO dao = new Tbl_CarColorDAO();
        return dao.getColorID(color);
    }
    public int getValidDiscountCode(String discountCode, int discountStatusID) throws SQLException, NamingException{
        Tbl_DiscountDAO dao = new Tbl_DiscountDAO();
        int disID = dao.checkValidDiscountCode(discountCode, Utils.getTime(), discountStatusID);
        return disID;
    }
    public void changeDiscountStatus(int discountID, int discountStatusID) throws SQLException, NamingException{
        Tbl_DiscountDAO dao = new Tbl_DiscountDAO();
        dao.changeDiscountStatus(discountID, discountStatusID);
    }
    
    public int getCarBrandID(String brand) throws SQLException, NamingException{
        Tbl_CarBrandDAO dao = new Tbl_CarBrandDAO();
        return dao.getBrandID(brand);
    }
    public int getCarStatusID(String status) throws SQLException, NamingException{
        Tbl_CarStatusDAO dao = new Tbl_CarStatusDAO();
        return dao.getStatusID(status);
    }
    public int getBookingStatusID(String status) throws SQLException, NamingException{
        Tbl_Booking_StatusDAO dao = new Tbl_Booking_StatusDAO();
        return dao.getBookingStatusID(status);
    }
    public int getBookingDetailStatusID(String status) throws SQLException, NamingException{
        Tbl_Booking_Details_StatusDAO dao = new Tbl_Booking_Details_StatusDAO();
        return dao.getBookingDetailStatusID(status);
    }
    
    public int getDiscountStatusID(String status) throws SQLException, NamingException{
        Tbl_Discount_StatusDAO dao = new Tbl_Discount_StatusDAO();
        return dao.getDiscountStatusID(status);
    }
    
}
