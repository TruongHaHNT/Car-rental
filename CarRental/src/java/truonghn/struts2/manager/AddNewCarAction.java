/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.manager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import static org.apache.struts2.ServletActionContext.getServletContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import truonghn.struts2.CarTool;
import truonghn.tbl_Car.Tbl_CarDAO;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddNewCarAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
    private String ADD_PAGE = "addPage";
    private String FAIL = "fail";
    private String carType;
    private String carBrand;
    private String carColor;
    private String carName;
    private float carPrice;
    private int carAmount;
    private String carDateRent;
    private String carRentHour;
    private String carRentMin;
    private String carDateReturn;
    private String carReturnHour;
    private String carReturnMin;
    private String notification;
    public AddNewCarAction() {
    }
    private static final long serialVersionUID = 1L;
    
    @Override
    public String execute() throws Exception {
        String url = FAIL;
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
                    if(isAdmin){
                        Part part = servletRequest.getPart("carImg");
                        String fileName = Utils.extractFileName(part);
                        
                        notification="ERROR detected!!!";
                        CarTool tool = new CarTool();
                        String STATUS = "Active";
                        int statusID = tool.getCarStatusID(STATUS);
                        int typeID = tool.getCarTypeID(carType);
                        int brandID = tool.getCarBrandID(carBrand);
                        int colorID = tool.getCarColorID(carColor);
                        Tbl_CarDAO dao = new Tbl_CarDAO();
                        String rentD = carDateRent+" "+carRentHour+":"+carRentMin+":00";
                        String returnD = carDateReturn+" "+carReturnHour+":"+carReturnMin+":00";
                        dao.createNewCar(carName,brandID,typeID,colorID,fileName,carPrice,carAmount,statusID,rentD,returnD);
                        if (fileName != null && !fileName.isEmpty()) {
                            String realPath = getServletContext().getRealPath("/")+"image\\"+fileName;
                            String host = getServletContext().getRealPath("/");
                            String hostImage = host.substring(0, host.length()-11)+"\\web\\image\\"+fileName;
                           part.write(realPath);
                           part.delete();
                           part.write(hostImage);
                           notification = "New Car has been created.";
                       }
                       else {
                           notification = "ERROR detected!!!";
                       }
                       url = ADD_PAGE;
                    }
                }
            }
        }catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        } catch(IOException e){
            Utils.myBlogFile(e.toString());
        }finally{
            return url;
        }
    }

    public String getADD_PAGE() {
        return ADD_PAGE;
    }

    public void setADD_PAGE(String ADD_PAGE) {
        this.ADD_PAGE = ADD_PAGE;
    }

    public String getFAIL() {
        return FAIL;
    }

    public void setFAIL(String FAIL) {
        this.FAIL = FAIL;
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

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
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

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public float getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(float carPrice) {
        this.carPrice = carPrice;
    }

    public int getCarAmount() {
        return carAmount;
    }

    public void setCarAmount(int carAmount) {
        this.carAmount = carAmount;
    }
    
    protected HttpServletResponse servletResponse;
    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
      this.servletResponse = servletResponse;
    }

    protected HttpServletRequest servletRequest;
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
      this.servletRequest = servletRequest;
    }
}
