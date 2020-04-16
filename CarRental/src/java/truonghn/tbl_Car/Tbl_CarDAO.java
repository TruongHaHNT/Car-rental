/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.tbl_Car;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import truonghn.utils.Utils;
 
/**
 *
 * @author SE130204
 */
public class Tbl_CarDAO implements Serializable{
    List<Tbl_CarDTO> list = null;

    public List<Tbl_CarDTO> getList() {
        return list;
    }
 

    public void getCarList(String car, int typeID, int brandID, int statusID, int min, int max, String dateRent, String dateReturn, int bookingStatusID, int bookingDetailsStatusID, int page, Integer totalContentPerPage) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int buttonPage = totalContentPerPage*(page-1);
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = 
                    " declare @min int = ? ; " +
"                     declare @max int = ? ;  " +
"                     declare @type int = ? ; " +
"                     declare @brand int = ? ; " +
"                     declare @car_status int = ? ; " +
"                     declare @dateRent dateTime = ? ; " +
"                     declare @dateReturn dateTime = ? ; " +
"                     declare @booking_status int = ? ; " +
"                     declare @booking_detail_status int = ? ; " +
"                     declare @offset int = ? ; " +
"                     declare @total_Page int = ? ; " +
"                     declare @searchVar varchar(50) = ? ; " +
"                     SELECT c_ID, c_name, avail_amount, c_image, c_ty_name, c_br_name, c_dateRent, c_dateReturn, c_pricePerDay, c_col_name " +
"                     FROM tbl_Car, tbl_CarBrand, tbl_CarType, tbl_CarColor, " +
"                    			( " +
"                                           SELECT avail_carID, c_amount-(CASE WHEN bk_Amount is null THEN 0 ELSE bk_Amount END) as avail_amount " +
"                                           FROM " +
"                    				( SELECT c_ID as avail_carID, c_amount " +
"                    				FROM tbl_Car, tbl_CarBrand, tbl_CarType " +
"                    				WHERE @dateRent > c_dateRent " +
"                    				AND @dateReturn < c_dateReturn " +
"                    				AND c_statusID = @car_status " +
"                    				AND c_br_ID = c_brandID " +
"                    				AND c_ty_ID = c_typeID " +
"                    				AND c_ty_ID = CASE WHEN @type = 0 THEN c_typeID ELSE @type END " +
"                    				AND c_br_ID = CASE WHEN @brand = 0 THEN c_brandID ELSE @brand END " +
"                    				AND c_name LIKE  @searchVar ) AS car_list " +
"                    						LEFT JOIN " +
"                    						( SELECT dt_carID AS bk_CarID, SUM (dt_amount) AS bk_Amount " +
"                    						FROM tbl_Booking_Bill_Details, tbl_Booking_Bill, tbl_Car, tbl_CarBrand, tbl_CarType " +
"                    						WHERE dt_statusID = @booking_detail_status " +
"								AND bill_statusID =  @booking_status " +
"                    						AND bill_ID = dt_bill_ID " +
"                    						AND @dateRent < dt_dateReturn " +
"                    						AND @dateReturn > dt_dateRent " +
"                    						AND dt_carID = c_ID " +
"                    						AND c_statusID = @car_status " +
"                    						AND c_br_ID = c_brandID " +
"                    						AND c_ty_ID = c_typeID " +
"                    						AND c_ty_ID = CASE WHEN @type = 0 THEN c_typeID ELSE @type END " +
"                    						AND c_br_ID = CASE WHEN @brand = 0 THEN c_brandID ELSE @brand END " +
"                    						AND c_name LIKE  @searchVar " +
"                    						GROUP BY dt_carID ) AS booking_Car " +
"                    							ON avail_carID = bk_CarID " +
"                    		) AS final_list " +
"                    			WHERE c_br_ID = c_brandID " +
"                    			AND c_ty_ID = c_typeID " +
"                    			AND c_ID = avail_carID " +
"                    			AND c_colorID = c_col_ID " +
"                    			AND avail_amount >= @min " +
"                    			AND avail_amount <= @max " +
"                    			ORDER BY c_updateDate DESC " +
"                    			OFFSET @offset ROWS " +
"                    			FETCH NEXT @total_Page ROWS ONLY; ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, min);
                stm.setInt(2, max);
                stm.setInt(3, typeID);
                stm.setInt(4, brandID);
                stm.setInt(5, statusID);
                stm.setString(6, dateRent);
                stm.setString(7, dateReturn);
                stm.setInt(8, bookingStatusID);
                stm.setInt(9, bookingDetailsStatusID);
                stm.setInt(10, buttonPage);
                stm.setInt(11, totalContentPerPage);
                stm.setString(12, "%"+car+"%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int CarID = rs.getInt(1);
                    String CarName = rs.getString(2);
                    int amount = rs.getInt(3);
                    String CarImg = Utils.path + rs.getString(4);
                    String type = rs.getString(5);
                    String brand = rs.getString(6);
                    String availableDate = Utils.timeFormat(rs.getTimestamp(7));
                    String returnDate = Utils.timeFormat(rs.getTimestamp(8));
                    float pricePerDay = Utils.roundFloatNumber(rs.getFloat(9));
                    String color = rs.getString(10);
                    Tbl_CarDTO dto = new Tbl_CarDTO(CarID, CarName, amount, CarImg, type, brand, null, availableDate, returnDate, pricePerDay,color);
                    if(this.list == null){// khoi tao list neu chua co
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }

    public int getTotalPage(String car, int typeID, int brandID, int statusID, int min, int max, String dateRent, String dateReturn, int bookingStatusID, int bookingDetailsStatusID, int totalContentPerPage) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float page = 0;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql =" declare @min int = ? ; " +
                    " declare @max int = ? ;  " +
                    " declare @type int = ? ; " +
                    " declare @brand int = ? ; " +
                    " declare @car_status int = ? ; " +
                    " declare @dateRent dateTime = ? ; " +
                    " declare @dateReturn dateTime = ? ; " +
                    " declare @booking_status int = ? ; " +
                    " declare @booking_detail_status int = ? ; " +                   
                    " declare @searchVar varchar(50) = ? ; " +
                    " SELECT COUNT(c_ID) " +
                    " FROM tbl_Car, " +
                    "			( " +
                    "                       SELECT avail_carID, c_amount-(CASE WHEN bk_Amount is null THEN 0 ELSE bk_Amount END) as avail_amount " +
                    "                       FROM " +
                    "				( SELECT c_ID as avail_carID, c_amount " +
"                    				FROM tbl_Car, tbl_CarBrand, tbl_CarType " +
"                    				WHERE @dateRent > c_dateRent " +
"                    				AND @dateReturn < c_dateReturn " +
"                    				AND c_statusID = @car_status " +
"                    				AND c_br_ID = c_brandID " +
"                    				AND c_ty_ID = c_typeID " +
"                    				AND c_ty_ID = CASE WHEN @type = 0 THEN c_typeID ELSE @type END " +
"                    				AND c_br_ID = CASE WHEN @brand = 0 THEN c_brandID ELSE @brand END " +
"                    				AND c_name LIKE  @searchVar ) AS car_list " +
"                    						LEFT JOIN " +
"                    						( SELECT dt_carID AS bk_CarID, SUM (dt_amount) AS bk_Amount " +
"                    						FROM tbl_Booking_Bill_Details, tbl_Booking_Bill, tbl_Car, tbl_CarBrand, tbl_CarType " +
"                    						WHERE dt_statusID = @booking_detail_status " +
"								AND bill_statusID =  @booking_status " +
"                    						AND bill_ID = dt_bill_ID " +
"                    						AND @dateRent < dt_dateReturn " +
"                    						AND @dateReturn > dt_dateRent " +
"                    						AND dt_carID = c_ID " +
"                    						AND c_statusID = @car_status " +
"                    						AND c_br_ID = c_brandID " +
"                    						AND c_ty_ID = c_typeID " +
"                    						AND c_ty_ID = CASE WHEN @type = 0 THEN c_typeID ELSE @type END " +
"                    						AND c_br_ID = CASE WHEN @brand = 0 THEN c_brandID ELSE @brand END " +
"                    						AND c_name LIKE  @searchVar " +
"                    						GROUP BY dt_carID ) AS booking_Car " +
"                    							ON avail_carID = bk_CarID " +
"                    		) AS final_list " +
                    "			WHERE c_ID = avail_carID " +
                    "			AND avail_amount >= @min " +
                    "			AND avail_amount <= @max ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, min);
                stm.setInt(2, max);
                stm.setInt(3, typeID);
                stm.setInt(4, brandID);
                stm.setInt(5, statusID);
                stm.setString(6, dateRent);
                stm.setString(7, dateReturn);
                stm.setInt(8, bookingStatusID);
                stm.setInt(9, bookingDetailsStatusID);
                stm.setString(10, "%"+car+"%");
                    //end
                rs = stm.executeQuery();
                if (rs.next()) {
                    page = (float)rs.getInt(1);
                    if(page>0){
                        return (int)Math.ceil((page/totalContentPerPage));
                    }
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return 0;
    }

    public int getAvailableAmount(int cartcarID, int statusID, int bookingStatusID, int bookingDetailStatusID, String cartrentDate, String CartreturnDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql =" declare @car_status int = ? ; " +
                            " declare @dateRent dateTime = ? ; " +
                            " declare @dateReturn dateTime = ? ; " +
                            " declare @booking_status int = ? ; " +
                            " declare @booking_detail_status int = ? ; " + 
                            " declare @carID int = ?; " +
                            " SELECT avail_amount " +
                            " FROM tbl_Car, " +
                            "			( " +
                            "			SELECT avail_carID, c_amount-(CASE WHEN bk_Amount is null THEN 0 ELSE bk_Amount END) as avail_amount " +
                            "			FROM " +
                            "				( SELECT c_ID as avail_carID, c_amount " +
                            "				FROM tbl_Car " +
                            "				WHERE @dateRent > c_dateRent " +
                            "				AND @dateReturn < c_dateReturn " +
                            "				AND c_statusID = @car_status " +
                            "				AND c_ID = @carID ) AS car_list " +
                            "						LEFT JOIN " +
                            "						( SELECT dt_carID AS bk_CarID, SUM (dt_amount) AS bk_Amount " +
                            "						FROM tbl_Booking_Bill_Details, tbl_Booking_Bill, tbl_Car " +
                            "						WHERE dt_statusID = @booking_detail_status " +
                            "                                           AND bill_statusID =  @booking_status " +
                            "						AND c_statusID = @car_status " +
                            "						AND bill_ID = dt_bill_ID " +
                            "						AND @dateRent < dt_dateReturn " +
                            "						AND @dateReturn > dt_dateRent " +
                            "						AND dt_carID = c_ID " +
                            "						AND c_ID = @carID " +
                            "						GROUP BY dt_carID ) AS booking_Car " +
                            "							ON avail_carID = bk_CarID " +
                            "			) AS final_list " +
                            "			WHERE c_ID = avail_carID ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, statusID);
                stm.setString(2, cartrentDate);
                stm.setString(3, CartreturnDate);
                stm.setInt(4, bookingStatusID);
                stm.setInt(5, bookingDetailStatusID);
                stm.setInt(6, cartcarID);
                
                rs = stm.executeQuery();
                
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return 0;
    }

    public Tbl_CarDTO getCarInfo(int cartcarID, int statusID, int bookingStatusID, int bookingDetailStatusID, int minAmount, int maxAmount, String cartrentDate, String CartreturnDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql =" declare @min int = ? ; " +
                    " declare @max int = ? ;  " +
                    " declare @car_status int = ? ; " +
                    " declare @dateRent dateTime = ? ; " +
                    " declare @dateReturn dateTime = ? ; " +
                    " declare @booking_status int = ? ; " +
                    " declare @booking_detail_status int = ? ; " + 
                    " declare @carID int = ? ; " +
                    " SELECT c_ID, c_name, avail_amount, c_image, c_ty_name, c_br_name, c_dateRent, c_dateReturn, c_pricePerDay, c_col_name " +
                    " FROM tbl_Car, tbl_CarBrand, tbl_CarType, tbl_CarColor, " +
                    "			( " +
                    "                       SELECT avail_carID, c_amount-(CASE WHEN bk_Amount is null THEN 0 ELSE bk_Amount END) as avail_amount " +
                    "                       FROM " +
                    "				( SELECT c_ID as avail_carID, c_amount " +
                    "				FROM tbl_Car, tbl_CarBrand, tbl_CarType " +
                    "				WHERE @dateRent > c_dateRent " +
                    "				AND @dateReturn < c_dateReturn " +
                    "				AND c_statusID = @car_status " +
                    "				AND c_br_ID = c_brandID " +
                    "				AND c_ty_ID = c_typeID " +
                    "				AND c_ID = @carID ) AS car_list " +
                    "						LEFT JOIN " +
                    "						( SELECT dt_carID AS bk_CarID, SUM (dt_amount) AS bk_Amount " +
                    "						FROM tbl_Booking_Bill_Details, tbl_Booking_Bill, tbl_Car, tbl_CarBrand, tbl_CarType " +
                    "						WHERE dt_statusID = @booking_detail_status " +
                    "                                           AND bill_statusID =  @booking_status " +
                    "						AND bill_ID = dt_bill_ID " +
                    "						AND @dateRent < dt_dateReturn " +
                    "						AND @dateReturn > dt_dateRent " +
                    "						AND dt_carID = c_ID " +
                    "						AND c_statusID = @car_status " +
                    "						AND c_br_ID = c_brandID " +
                    "						AND c_ty_ID = c_typeID " +
                    "						AND c_ID = @carID " +
                    "						GROUP BY dt_carID ) AS booking_Car " +
                    "							ON avail_carID = bk_CarID " +
                    "			) AS final_list " +
                    "			WHERE c_br_ID = c_brandID " +
                    "			AND c_ty_ID = c_typeID " +
                    "			AND c_ID = avail_carID " +
                    "			AND c_colorID = c_col_ID " +
                    "			AND avail_amount >= @min " +
                    "			AND avail_amount <= @max ";
                stm = con.prepareStatement(sql);
                    stm.setInt(1, minAmount);
                    stm.setInt(2, maxAmount);
                    stm.setInt(3, statusID);
                    stm.setString(4, cartrentDate);
                    stm.setString(5, CartreturnDate);
                    stm.setInt(6, bookingStatusID);
                    stm.setInt(7, bookingDetailStatusID);
                    stm.setInt(8, cartcarID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int CarID = rs.getInt(1);
                    String CarName = rs.getString(2);
                    int amount = rs.getInt(3);
                    String CarImg = Utils.path + rs.getString(4);
                    String type = rs.getString(5);
                    String brand = rs.getString(6);
                    String availableDate = Utils.timeFormat(rs.getTimestamp(7));
                    String returnDate = Utils.timeFormat(rs.getTimestamp(8));
                    float pricePerDay = rs.getFloat(9);
                    String color = rs.getString(10);
                    Timestamp mintime = Timestamp.valueOf(cartrentDate);
                    Timestamp maxtime = Timestamp.valueOf(CartreturnDate);
                    long milisec = maxtime.getTime() - mintime.getTime();
                    float day = Float.parseFloat(milisec+"")/(1000*60*60*24);
                    pricePerDay = Utils.roundFloatNumber(pricePerDay * day);
                    Tbl_CarDTO dto = new Tbl_CarDTO(CarID, CarName, amount, CarImg, type, brand, null, availableDate, returnDate, pricePerDay,color);
                    return dto;
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return null;
    }

    public Tbl_CarDTO getCarInfo(int cartCarID, String rentDate, String returnDate, int amount) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql =" SELECT c_name, c_image, c_ty_name, c_br_name, c_pricePerDay, c_col_name " +
                            " FROM tbl_Car, tbl_CarBrand, tbl_CarType, tbl_CarColor " +
                            " WHERE c_br_ID = c_brandID " +
                            " AND c_ty_ID = c_typeID " +
                            " AND c_ID = ? " +
                            " AND c_colorID = c_col_ID ";
                stm = con.prepareStatement(sql);
                    stm.setInt(1, cartCarID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String CarName = rs.getString(1);
                    String CarImg = Utils.path + rs.getString(2);
                    String type = rs.getString(3);
                    String brand = rs.getString(4);
                    float pricePerDay = rs.getFloat(5);
                    String color = rs.getString(6);
                    Timestamp mintime = Timestamp.valueOf(rentDate);
                    Timestamp maxtime = Timestamp.valueOf(returnDate);
                    long milisec = maxtime.getTime() - mintime.getTime();
                    float day = Float.parseFloat(milisec+"")/(1000*60*60*24);
                    pricePerDay = Utils.roundFloatNumber(pricePerDay * day);
                    Tbl_CarDTO dto = new Tbl_CarDTO(cartCarID, CarName, amount, CarImg, type, brand, null, rentDate, returnDate, pricePerDay,color);
                    return dto;
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return null;
    }

    public boolean getCarDetailsList(String searchVar, int billID, Timestamp curTime, String Detailstatus) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean isEnded;
        int totalItems = 0;
        int invalidItems = 0;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = " declare @billID int = ? ; " +
"                                 declare @searchVar varchar(30) = ? ; " +
"                                 SELECT dt_ID, c_name, dt_amount, c_image, c_ty_name, c_br_name, dt_dateRent, dt_dateReturn, dt_price, c_col_name, bk_dt_st_status " +
"                                 FROM tbl_Booking_Bill_Details, tbl_Car, tbl_CarBrand, tbl_CarType, tbl_CarColor, tbl_Booking_Details_Status " +
"                                 WHERE dt_carID = c_ID " +
"                                 AND bk_dt_st_ID = dt_statusID " +
"                                 AND c_name LIKE @searchVar " +
"                                 AND dt_bill_ID = @billID " +
"       			  AND c_typeID = c_ty_ID " +
" 			          AND c_br_ID = c_brandID " +
"				  AND c_colorID = c_col_ID " +
"                                 ORDER BY dt_dateReturn ";   
                stm = con.prepareStatement(sql);
                stm.setInt(1, billID);
                stm.setString(2, "%"+searchVar+"%");
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int CarBillID = rs.getInt(1);
                    String CarName = rs.getString(2);
                    int amount = rs.getInt(3);
                    String CarImg = Utils.path + rs.getString(4);
                    String type = rs.getString(5);
                    String brand = rs.getString(6);
                    String rentDate = Utils.timeFormat(rs.getTimestamp(7));
                    String returnDate = Utils.timeFormat(rs.getTimestamp(8));
                    float price = rs.getFloat(9);
                    String color = rs.getString(10);
                    String status = rs.getString(11);
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    totalItems++;
                    if(status.matches(Detailstatus)){
                        status = "Waiting";
                        if(curTime.after(rs.getTimestamp(7))){
                            status = "Renting";
                        }
                        if(curTime.after(rs.getTimestamp(8))){
                            status = "Ended";
                            invalidItems++;
                        }
                    }else{
                        status = "Canceled";
                        invalidItems++;
                    }
                    
                    Tbl_CarDTO dto = new Tbl_CarDTO(CarBillID, CarName, amount, CarImg, type, brand, status, rentDate, returnDate, price, color);
                    list.add(dto);
                }
            //to check if all car is expired or canceled=========================================    
                if(totalItems == invalidItems){
                    isEnded = true;
                }else{
                    isEnded = false;
                }
                return isEnded;
            }
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }    
        return false;
    }

    public boolean isRenting(int statusID, Timestamp curDate, int carID, String email, int bookingDetailStatusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql =" declare @carStatusID int = ? ; " +
                            " declare @curTime dateTime = ? ; " +
                            " declare @carID int = ? ; " +
                            " declare @email varchar(254)= ? ; " +
                            " declare @bookingDetalStatusID int = ? ; " +
                            " SELECT top (1) c_ID " +
                            " FROM tbl_Car, tbl_Booking_Bill, tbl_Booking_Bill_Details " +
                            " WHERE bill_ID = dt_bill_ID " +
                            " AND dt_carID = c_ID " +
                            " AND c_statusID = @carStatusID " +
                            " AND c_ID = @carID " +
                            " AND dt_dateRent < @curTime " +
                            " AND ( " +
                            "       dt_statusID = @bookingDetalStatusID " +
                            "       OR ( " +
                            "               dt_statusID != @bookingDetalStatusID " +
                            "               AND dt_updateDate > dt_dateRent " +
                            "          ) " +
                            "     ) " +
                            " AND bill_u_email = @email ";
                stm = con.prepareStatement(sql);
                    stm.setInt(1, statusID);
                    stm.setTimestamp(2, curDate);
                    stm.setInt(3, carID);
                    stm.setString(4, email);
                    stm.setInt(5, bookingDetailStatusID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }

    public void createNewCar(String carName, int brandID, int typeID, int colorID, String carImg, float carPrice, int carAmount, int statusID, String rentD, String returnD) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql =" INSERT INTO tbl_Car(c_name, c_image, c_brandID, c_typeID, c_colorID, c_amount, c_dateRent, c_dateReturn, c_statusID, c_pricePerDay, c_updateDate) " +
                            " VALUES (?,?,?,?,?,?,?,?,?,?,?); ";
                stm = con.prepareStatement(sql);
                stm.setString(1, carName);
                stm.setString(2, carImg);
                stm.setInt(3, brandID);
                stm.setInt(4, typeID);
                stm.setInt(5, colorID);
                stm.setInt(6, carAmount);
                stm.setTimestamp(7, Timestamp.valueOf(rentD));
                stm.setTimestamp(8, Timestamp.valueOf(returnD));
                stm.setInt(9, statusID);
                stm.setFloat(10, carPrice);
                stm.setTimestamp(11, Utils.getTime());
                int row = stm.executeUpdate();
            }
        }finally{
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }

}