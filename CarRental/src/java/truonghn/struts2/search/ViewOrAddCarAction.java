/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.search;

import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class ViewOrAddCarAction {
    private String btAction;
    private String VIEW_PAGE ="view";
    private String ADD_TO_CART ="add";
    private String GO_BACK ="error";
    public ViewOrAddCarAction() {
    }
    
    public String execute() throws Exception{
        String url = GO_BACK;
        try {
            if(btAction == null){
                url = GO_BACK;
            }
            if(btAction.equals("View detail")){
                url = VIEW_PAGE;
            }else if(btAction.equals("Add to cart")){
                url = ADD_TO_CART;
            }else if(btAction.equals("Return")){
                url = GO_BACK;
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

    public String getVIEW_PAGE() {
        return VIEW_PAGE;
    }

    public void setVIEW_PAGE(String VIEW_PAGE) {
        this.VIEW_PAGE = VIEW_PAGE;
    }

    public String getADD_TO_CART() {
        return ADD_TO_CART;
    }

    public void setADD_TO_CART(String ADD_TO_CART) {
        this.ADD_TO_CART = ADD_TO_CART;
    }

    public String getGO_BACK() {
        return GO_BACK;
    }

    public void setGO_BACK(String GO_BACK) {
        this.GO_BACK = GO_BACK;
    }
    
    
}
