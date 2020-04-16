/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.cart;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class UpdateCarInCartAction {
    private int updateCarAmount;
    private int updateCartID;
    private String SUCCESS = "success";
    private String FAIL = "fail";
    public UpdateCarInCartAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        try {
            Map session = ActionContext.getContext().getSession();
            if(session.get("CART") != null){
                CartObj cart = (CartObj)session.get("CART");
                cart.updateAmountOfCarInCart(updateCartID, updateCarAmount);
                session.put("CART", cart);
                url = SUCCESS;
            }
        }catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }finally{
            return url;
        }
    }

    public int getUpdateCarAmount() {
        return updateCarAmount;
    }

    public void setUpdateCarAmount(int updateCarAmount) {
        this.updateCarAmount = updateCarAmount;
    }

    public int getUpdateCartID() {
        return updateCartID;
    }

    public void setUpdateCartID(int updateCartID) {
        this.updateCartID = updateCartID;
    }
    
    public String getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(String SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public String getFAIL() {
        return FAIL;
    }

    public void setFAIL(String FAIL) {
        this.FAIL = FAIL;
    }
    
}
