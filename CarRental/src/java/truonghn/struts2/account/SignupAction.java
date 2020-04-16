/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.account;

import com.opensymphony.xwork2.ActionSupport;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import truonghn.tbl_UserInfo.SignUpCheckError;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.tbl_UserRole.Tbl_UserRoleDAO;
import truonghn.tbl_UserStatus.Tbl_UserStatusDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class SignupAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userConfirm;
    private String userPhone;
    private String userAddress;
    private String userRole;
    private String userStatus;
    private String verifyCode;
    private String VERIFY = "verify";
    private String FAIL = "fail";
    private SignUpCheckError signUpErrors;
    public SignupAction() {
    }
    
    @Override
    public String execute() throws Exception{
        String url = FAIL;
        Tbl_UserInfoDAO dao;
        String emailformat = "[a-z0-9A-Z\\_\\-]{1,30}[@][a-z0-9A-Z]{1,30}[.][a-z]{1,10}([.][a-z]{1,10})?";                                                                                               String format = "[0-9a-zA-Z\\s]{1,30}";
        String passformat="[0-9a-zA-Z\\s]{1,30}";
        String nameformat="[0-9a-zA-Z\\s]{1,30}";
        String phoneformat="[0-9]{10}";
        boolean foundErr = false;
        signUpErrors = new SignUpCheckError();
        try {
            if(userName.length()<=0 || userName.length() > 30){
                foundErr = true;
                signUpErrors.setNameLengthErr("Name length requires from 1 to 30 chars!!!");
            }else if(userName.matches(nameformat)==false){
                foundErr = true;
                signUpErrors.setNameFormatErr("Name contain no special chars!!!");
            }
            
            if(userEmail.length()<=0 || userEmail.length() > 254){
                foundErr = true;
                signUpErrors.setEmailLengthErr("Email requires!!!");
            }else if(userEmail.matches(emailformat)==false){
                foundErr = true;
                signUpErrors.setEmailFormatErr("Invalid Email format!!!");
            }
            
            if (userPassword.length() <= 0 || userPassword.length() > 30){
                foundErr = true;
                signUpErrors.setPasswordFormatErr("Password length requires from 1 to 30 chars!!!");
            }else if(userPassword.matches(passformat)==false){
                foundErr =true;
                signUpErrors.setPasswordFormatErr("Password contain no special chars!!!");
            }else if(userPassword.equals(userConfirm)==false){
                foundErr =true;
                signUpErrors.setPasswordFormatErr("Confirm must match password!!!");
            }
            
            if (!userPhone.matches(phoneformat)){
                foundErr = true;
                signUpErrors.setPhoneformatErr("Invalid Phone format!!!");
            }
            if (userAddress.isEmpty()){
                foundErr = true;
                signUpErrors.setAddressformatErr("Address is required!!!");
            }
            
            dao = new Tbl_UserInfoDAO();
            if(dao.checkExisted(userEmail)){
                foundErr = true;
                signUpErrors.setEmailExistedErr("This email is existed!!!");
            }
            
            if(foundErr){
                
            }else{
//                userStatus = "New";
//                userRole = "Customer";
                Tbl_UserStatusDAO stdao = new Tbl_UserStatusDAO();
                String statusID = stdao.getStatusID(userStatus);
                Tbl_UserRoleDAO rldao = new Tbl_UserRoleDAO();
                String roleID = rldao.getRoleID(userRole);
                dao.createNewAccount(userEmail, userName, userPassword, roleID, statusID, userPhone, userAddress);
                
                    //send userEmail and keep verifycode and userPassword.
                    verifyCode = Utils.activeAcountCode(userEmail);
                url = VERIFY;
            }
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }catch(NoSuchAlgorithmException e){
            Utils.myBlogFile(e.toString());
        }catch(SQLException e){
            Utils.myBlogFile(e.toString());
        }catch(UnsupportedEncodingException e){
            Utils.myBlogFile(e.toString());
        }catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
            return url;
        }
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

    public String getUserConfirm() {
        return userConfirm;
    }

    public void setUserConfirm(String userConfirm) {
        this.userConfirm = userConfirm;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getVERIFY() {
        return VERIFY;
    }

    public void setVERIFY(String VERIFY) {
        this.VERIFY = VERIFY;
    }

    public String getFAIL() {
        return FAIL;
    }

    public void setFAIL(String FAIL) {
        this.FAIL = FAIL;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public SignUpCheckError getSignUpErrors() {
        return signUpErrors;
    }

    public void setSignUpErrors(SignUpCheckError signUpErrors) {
        this.signUpErrors = signUpErrors;
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
