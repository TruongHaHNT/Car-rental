/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.cart;

import com.opensymphony.xwork2.ActionContext;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class ViewCarInCartAction {
    private String VIEW_CAR_IN_CART = "viewCarInCart";
    private String CHECK_AUTH = "loginRequired";
    private String btAction;
    public ViewCarInCartAction() {
    }
    
    public String execute() throws Exception{
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
                        btAction = "Cart";
                        url = VIEW_CAR_IN_CART;
                    }
                }
            }
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }catch(SQLException e){
            Utils.myBlogFile(e.toString());
        }catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
            return url;
        }
    }
    
    public String getVIEW_CAR_IN_CART() {
        return VIEW_CAR_IN_CART;
    }

    public void setVIEW_CAR_IN_CART(String VIEW_CAR_IN_CART) {
        this.VIEW_CAR_IN_CART = VIEW_CAR_IN_CART;
    }
    
    public String getBtAction() {
        return btAction;
    }

    public void setBtAction(String btAction) {
        this.btAction = btAction;
    }

    public String getCHECK_AUTH() {
        return CHECK_AUTH;
    }

    public void setCHECK_AUTH(String CHECK_AUTH) {
        this.CHECK_AUTH = CHECK_AUTH;
    }
    
    
}
