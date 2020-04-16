/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.account;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import truonghn.tbl_UserInfo.CheckLoginError;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.tbl_UserInfo.Tbl_UserInfoDTO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class LoginAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
    private String userEmail;
    private String userPassword;
    private String verifyCode;
    private String userName;
    private String CUSTOMER = "customer";
    private String MANAGER = "manager";
    private String VERIFY = "verify";
    private String FAIL = "fail";
    private String SECRET_KEY = "6LcBx90UAAAAAD6qB8r5_XQ78ObtO_fwjLb5xOO_";
    private CheckLoginError loginErrors;
    public LoginAction() {
    }
    
    @Override
    public String execute() throws Exception{
        String url = FAIL;
        boolean foundErr = false;
        String emailformat = "[a-z0-9A-Z\\_\\-]{1,30}[@][a-z0-9A-Z]{1,30}[.][a-z]{1,10}([.][a-z]{1,10})?";                                                                                               String format = "[0-9a-zA-Z\\s]{1,30}";
        String passformat="[0-9a-zA-Z\\s]{1,30}";
        loginErrors = new CheckLoginError();
        try {
            if(userEmail.length()<=0 || userEmail.length() > 254){
                foundErr = true;
                loginErrors.setEmailLengthErr("Email requires!!!");
            }else if(userEmail.matches(emailformat)==false){
                foundErr = true;
                loginErrors.setEmailFormatErr("Invalid Email format!!!");
            }
            
            if (userPassword.length() <= 0 || userPassword.length() > 30){
                foundErr = true;
                loginErrors.setPasswordFormatErr("Password length required from 1 to 30 chars!!!");
            }else if(userPassword.matches(passformat)==false){
                foundErr =true;
                loginErrors.setPasswordFormatErr("Password contain no special chars!!!");
            }
            if(!isCaptchaValid(SECRET_KEY,servletRequest.getParameter("g-recaptcha-response"))){
                foundErr =true;
                loginErrors.setValidRecaptcha("Please velify Google ReCaptcha!!!");
            }
            if(!foundErr){
                Tbl_UserInfoDAO userDao = new Tbl_UserInfoDAO();
                boolean exist = userDao.checkExisted(userEmail);
                if(exist){
                    boolean existed = userDao.checkExistedUser(userEmail, userPassword);
                    if(existed){
                        String userStatus = "Active";
                        boolean isActive = userDao.isActive(userEmail, userStatus);
                        Tbl_UserInfoDTO dto = userDao.getUserinfo(userEmail);
                        userName = dto.getU_name();
                        if(isActive){
                            url = CUSTOMER;
                            String userRole = "Manager";
                            boolean isAdmin = userDao.isAdmin(userEmail, userRole);
                            if(isAdmin){
                                url = MANAGER;
                            }
                            Cookie cookie = new Cookie(Utils.encodeMail(userEmail), userPassword);
                            cookie.setMaxAge(24*60*60);
                            servletResponse.addCookie(cookie);
                            Map session = ActionContext.getContext().getSession();
                            session.put("NAME", dto.getU_name());
                            session.put("EMAIL", dto.getU_email());
                        }
                        else{
                            url = VERIFY;
                            verifyCode = Utils.activeAcountCode(userEmail);
                        }
                    }    
                    else{
                            loginErrors.setIncorrectPassword("Incorrect password!!!!");
                        }
                }else{
                    loginErrors.setNotExistErr("This email is not Existed!!!!");
                }
            }
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (IOException e) {
            Utils.myBlogFile(e.toString());
        } catch (NoSuchAlgorithmException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        }catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
            return url;
        }
    }

    public String getVERIFY() {
        return VERIFY;
    }

    public void setVERIFY(String VERIFY) {
        this.VERIFY = VERIFY;
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

    public String getCUSTOMER() {
        return CUSTOMER;
    }

    public void setCUSTOMER(String CUSTOMER) {
        this.CUSTOMER = CUSTOMER;
    }

    public String getMANAGER() {
        return MANAGER;
    }

    public void setMANAGER(String MANAGER) {
        this.MANAGER = MANAGER;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    public void setSECRET_KEY(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    public CheckLoginError getLoginErrors() {
        return loginErrors;
    }

    public void setLoginErrors(CheckLoginError loginErrors) {
        this.loginErrors = loginErrors;
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
    
    public synchronized boolean isCaptchaValid(String secretKey, String response) throws IOException {
        boolean valid = false;
            String url = "https://www.google.com/recaptcha/api/siteverify",
                    params = "secret=" + secretKey + "&response=" + response;

            HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            OutputStream out = http.getOutputStream();
            out.write(params.getBytes("UTF-8"));
            out.flush();
            out.close();

            InputStream res = http.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(res, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            JSONObject json = new JSONObject(sb.toString());
            res.close();
            valid = json.getBoolean("success");
            return valid;
    }
}
