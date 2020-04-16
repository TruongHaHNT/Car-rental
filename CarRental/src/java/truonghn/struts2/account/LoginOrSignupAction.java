/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.account;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class LoginOrSignupAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
    private String btAction;
    private String LOGIN_PAGE = "loginpage";
    private String LOGOUT_PAGE = "logoutpage";
    private String SIGNUP_PAGE = "signuppage";
    private String FAIL = "fail";
    
    public LoginOrSignupAction() {
    }
    
    @Override
    public String execute() throws Exception{
        String url=FAIL;
        try {
            if(btAction.equals("Login")){
                url = LOGIN_PAGE;
            }else if(btAction.equals("Logout")){
                url = LOGOUT_PAGE;
            }else if(btAction.equals("Sign up")){
                url = SIGNUP_PAGE;
            }
        }catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
            return url;
        }
    }

    public String getLOGIN_PAGE() {
        return LOGIN_PAGE;
    }

    public void setLOGIN_PAGE(String LOGIN_PAGE) {
        this.LOGIN_PAGE = LOGIN_PAGE;
    }

    public String getLOGOUT_PAGE() {
        return LOGOUT_PAGE;
    }

    public void setLOGOUT_PAGE(String LOGOUT_PAGE) {
        this.LOGOUT_PAGE = LOGOUT_PAGE;
    }

    public String getSIGNUP_PAGE() {
        return SIGNUP_PAGE;
    }

    public void setSIGNUP_PAGE(String SIGNUP_PAGE) {
        this.SIGNUP_PAGE = SIGNUP_PAGE;
    }

    public String getBtAction() {
        return btAction;
    }

    public void setBtAction(String btAction) {
        this.btAction = btAction;
    }

    public String getFAIL() {
        return FAIL;
    }

    public void setFAIL(String FAIL) {
        this.FAIL = FAIL;
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
