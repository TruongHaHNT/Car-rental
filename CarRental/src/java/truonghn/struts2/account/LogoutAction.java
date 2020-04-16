/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.struts2.account;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class LogoutAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
    private String SUCCESS = "success";
    public LogoutAction() {
    }
    
    @Override
    public String execute() throws Exception{
        String url = SUCCESS;
        try {
            Map session = ActionContext.getContext().getSession();
            if(session != null){
                session.put("NAME", null);
                session.put("EMAIL", null);
                session.put("CART", null);
                session.remove("NAME");
                session.remove("EMAIL");
                session.remove("CART");
                
                //populate the struts session
                session.entrySet();
                Cookie[] cookies = servletRequest.getCookies();
                if(cookies != null){
                    for(Cookie cookie : cookies){
                        cookie.setMaxAge(1);
                        servletResponse.addCookie(cookie);
                    }
                }
            }
        } catch (NullPointerException e) {
            Utils.myBlogFile(e.toString());
        }finally{
            return url;
        }
    }

    public String getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(String SUCCESS) {
        this.SUCCESS = SUCCESS;
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
