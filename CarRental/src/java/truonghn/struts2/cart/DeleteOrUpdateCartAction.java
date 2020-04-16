/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.cart;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class DeleteOrUpdateCartAction {
    private String btAction;
    private String DELETE_FROM_CART = "deleteFromCart";
    private String UPDATE_FROM_CART = "updateFromCart";
    private String VIEW_CART = "viewCart";
    private String CHECK_AUTH = "loginRequired";
    public DeleteOrUpdateCartAction() {
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
                        if(btAction == null){
                            url =VIEW_CART;
                        }
                        else if(btAction.equals("Confirm Delete")){
                            url = DELETE_FROM_CART;
                        }
                        else if(btAction.equals("Confirm Update")){
                            url = UPDATE_FROM_CART;
                        }
                    }
                }
            }
        }catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }
        finally {
            return url;
        }
    }

    public String getBtAction() {
        return btAction;
    }

    public void setBtAction(String btAction) {
        this.btAction = btAction;
    }

    public String getVIEW_CART() {
        return VIEW_CART;
    }

    public void setVIEW_CART(String VIEW_CART) {
        this.VIEW_CART = VIEW_CART;
    }

    public String getDELETE_FROM_CART() {
        return DELETE_FROM_CART;
    }

    public void setDELETE_FROM_CART(String DELETE_FROM_CART) {
        this.DELETE_FROM_CART = DELETE_FROM_CART;
    }

    public String getUPDATE_FROM_CART() {
        return UPDATE_FROM_CART;
    }

    public String getCHECK_AUTH() {
        return CHECK_AUTH;
    }

    public void setCHECK_AUTH(String CHECK_AUTH) {
        this.CHECK_AUTH = CHECK_AUTH;
    }

    public void setUPDATE_FROM_CART(String UPDATE_FROM_CART) {
        this.UPDATE_FROM_CART = UPDATE_FROM_CART;
    }
    
}
