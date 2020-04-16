/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.account;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mailValidCode.MailError;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.tbl_UserStatus.Tbl_UserStatusDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class VerifyAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
    private String verifyCode;
    private String txtVerify;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String SUCCESS = "success";
    private String FAIL = "fail";
    private MailError verifyCodeError;
    public VerifyAction() {
        
    }
    
    @Override
    public String execute() throws Exception{
        String url = FAIL;
        try {
            if(verifyCode.matches(txtVerify)){
                Tbl_UserStatusDAO stdao = new Tbl_UserStatusDAO();
                Tbl_UserInfoDAO dao = new Tbl_UserInfoDAO();
                dao.activeUserStatus(userEmail,stdao.getStatusID("Active"));
                Cookie cookie = new Cookie(Utils.encodeMail(userEmail), userPassword);
                cookie.setMaxAge(24* 60 * 60);
                servletResponse.addCookie(cookie);
                Map session = ActionContext.getContext().getSession();
                session.put("NAME", userName);
                session.put("EMAIL", userEmail);
                url =SUCCESS;
            }else{
                verifyCodeError = new MailError();
                verifyCodeError.setInvalidVerifyCode("Invaid verify Code!");
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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getTxtVerify() {
        return txtVerify;
    }

    public void setTxtVerify(String txtVerify) {
        this.txtVerify = txtVerify;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public MailError getVerifyCodeError() {
        return verifyCodeError;
    }

    public void setVerifyCodeError(MailError verifyCodeError) {
        this.verifyCodeError = verifyCodeError;
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
