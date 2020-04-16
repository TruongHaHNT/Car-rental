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
public class ResendVerifyCodeAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
    private String userEmail;
    private String userPassword;
    private String userName;
    private String verifyCode;
    private String VERIFY = "verify";
    public ResendVerifyCodeAction() {
    }
    
    @Override
    public String execute() throws Exception{
        String url = VERIFY;
        try {
            verifyCode = Utils.activeAcountCode(userEmail);
        }catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
            return url;
        }
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVERIFY() {
        return VERIFY;
    }

    public void setVERIFY(String VERIFY) {
        this.VERIFY = VERIFY;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
    
    
}
