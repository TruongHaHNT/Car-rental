<%-- 
    Document   : Login
    Created on : Jan 22, 2020, 6:47:46 AM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <title>Login Page</title>
    </head>
    <body>
        <c:set var="errors" value="loginErrors" />
        <div class="sidenav">
            <div class="login-main-text">
            <h2>Car Rental<br> Login Page</h2>
            <p>Login or register from here to access.</p>
         </div>
      </div>
      <div class="main">
         <div class="col_md_6_col_sm_12">
            <div class="login-form">
                <form action="searchCar" method="POST">
                    <input type="submit" name="" value="Go back to Home Page">
                </form>
                <form action="login" method="POST">
                <div class="form-group">
                    <label>Mail</label><br>
                    <input type="text" name="userEmail" value="<c:property value="userEmail"/>" class="form-control" placeholder="User Mail">
                    <br> 
                    <c:if test="%{#errors.emailLengthErr != null}">
                        <font color="red">
                        <c:property value="%{#errors.emailLengthErr}"/>
                        </font><br/>
                    </c:if>
                    <c:if test="%{#errors.emailFormatErr != null}">
                        <font color="red">
                        <c:property value="%{#errors.emailFormatErr}"/>
                        </font><br/>
                    </c:if>
                        <c:if test="%{#errors.notExistErr != null}">
                    <font color="red">
                    <c:property value="%{#errors.notExistErr}"/>
                    </font><br/>
                </c:if>
                  </div>
                  <div class="form-group">
                    <label>Password</label><br>
                    <input type="password" name="userPassword" value="" class="form-control" placeholder="Password">
                    <br>
                    <c:if test="%{#errors.passwordLengthErr != null}">
                        <font color="red">
                        <c:property value="%{#errors.passwordLengthErr}"/>
                        </font><br/>
                    </c:if>
                    <c:if test="%{#errors.passwordFormatErr != null}">
                        <font color="red">
                        <c:property value="%{#errors.passwordFormatErr}"/>
                        </font><br/>
                    </c:if>
                    <c:if test="%{#errors.incorrectPassword != null}">
                        <font color="red">
                        <c:property value="%{#errors.incorrectPassword}"/>
                        </font><br/>
                    </c:if>
                  </div>
                    <div class="g-recaptcha" data-sitekey="6LcBx90UAAAAAOMOLAy6xqLgndjzGc6ZfZ-MMwDQ" data-callback="onSubmit"></div>
                    <c:if test="%{#errors.validRecaptcha != null}">
                        <font color="red">
                        <c:property value="%{#errors.validRecaptcha}"/>
                        </font><br/>
                    </c:if>
                    <input type="submit" name="btAction" value="Login" class="btn btn-black"/>
                    <input type="reset" value="Reset"/>
               </form><br>
               <form action="loginOrSignup" method="POST">
                    <input type="submit" name="btAction" value="Sign up">
                </form>
            </div>
         </div>
      </div>
    </body>
</html>
