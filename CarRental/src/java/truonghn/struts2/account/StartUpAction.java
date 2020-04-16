/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.account;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import truonghn.tbl_UserInfo.Tbl_UserInfoDAO;
import truonghn.tbl_UserInfo.Tbl_UserInfoDTO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class StartUpAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
    private String CUSTOMER = "customer";
    private String MANAGER = "manager";
    private String FAIL = "fail";
    public StartUpAction() {
    }
    
    @Override
    public String execute() throws Exception{
        String url = FAIL;
        try {    
            Utils.setCalendar();
            Cookie cookie[] = servletRequest.getCookies();
            if(cookie != null){
                Tbl_UserInfoDAO userDao = new Tbl_UserInfoDAO();
                for(Cookie c : cookie) {
                    String mail = Utils.decodeMail(c.getName());
                    String pass = c.getValue();
                    boolean existed = userDao.checkExistedUser(mail, pass);
                    if(existed){
                        String userStatus = "Active";
                        boolean isActive = userDao.isActive(mail, userStatus);
                        if(isActive){
                            url = CUSTOMER;
                            String userRole = "Manager";
                            boolean isAdmin = userDao.isAdmin(mail, userRole);
                            if(isAdmin){
                                url = MANAGER;
                            }
                            Tbl_UserInfoDTO dto = userDao.getUserinfo(mail);
                            Map session = ActionContext.getContext().getSession();
                            session.put("NAME", dto.getU_name());
                            session.put("EMAIL", dto.getU_email());
                            break;
                        }
                    }
                }
            }
        } catch (NamingException e) {
            Utils.myBlogFile(e.toString());
        } catch (NoSuchAlgorithmException e) {
            Utils.myBlogFile(e.toString());
        } catch (SQLException e) {
            Utils.myBlogFile(e.toString());
        } catch (UnsupportedEncodingException e) {
            Utils.myBlogFile(e.toString());
        }catch(NullPointerException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
            return url;
        }
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
