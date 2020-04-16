/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.account;

import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class VerifyOrCancelAction {
    private String btAction;
    private String VERIFY = "verify";
    private String CANCEL = "cancel";
    private String RESEND = "resend";
    
    public VerifyOrCancelAction() {
    }
    
    public String execute() throws Exception{
        String url ="";
        try {
            if(btAction.equals("Verify")){
                url = VERIFY;
            }else if(btAction.equals("Cancel")){
                url = CANCEL;
            }else if(btAction.equals("Click here to re-send verify code")){
                url = RESEND;
            }
        } catch (NullPointerException e) {
            Utils.myBlogFile(e.toString());
        }finally{
            return url;
        }
    }

    public String getBtAction() {
        return btAction;
    }

    public void setBtAction(String btAction) {
        this.btAction = btAction;
    }

    public String getVERIFY() {
        return VERIFY;
    }

    public void setVERIFY(String VERIFY) {
        this.VERIFY = VERIFY;
    }

    public String getCANCEL() {
        return CANCEL;
    }

    public void setCANCEL(String CANCEL) {
        this.CANCEL = CANCEL;
    }

    public String getRESEND() {
        return RESEND;
    }

    public void setRESEND(String RESEND) {
        this.RESEND = RESEND;
    }
    
}
