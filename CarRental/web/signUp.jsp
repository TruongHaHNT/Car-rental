<%-- 
    Document   : SignUp
    Created on : Jan 22, 2020, 4:16:02 PM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <title>Sign Up</title>
    </head>
    <body>
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
                    <input type="submit" name="btAction" value="Go back to Home Page">
                </form>
                <form action="signup" method="POST">
                    <c:set var="errors" value="signUpErrors" />
                    
                  <div class="form-group">
                     <label>Mail</label><br>
                     <input type="text" name="userEmail" value="<c:property value="userEmail"/>" class="form-control" placeholder="User Mail"><br/>
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
                <c:if test="%{#errors.emailExistedErr != null}">
                    <font color="red">
                    <c:property value="%{#errors.emailExistedErr}"/>
                    </font><br/>
                </c:if>
                  </div>
                    <div class="form-group">
                     <label>User Name</label><br>
                     <input type="text" name="userName" value="<c:property value="userName"/>" class="form-control" placeholder="User Name"><br/>
                        <c:if test="%{#errors.nameLengthErr != null}">
                            <font color="red">
                            <c:property value="%{#errors.nameLengthErr}"/>
                            </font><br/>
                        </c:if>
                        <c:if test="%{#errors.nameFormatErr != null}">
                            <font color="red">
                            <c:property value="%{errors.nameFormatErr}"/>
                            </font><br/>
                        </c:if>
                  </div>
                  <div class="form-group">
                     <label>Password</label><br>
                     <input type="password" name="userPassword" value="" class="form-control" placeholder="Password"><br/>
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
                  </div>
                  
                    <div class="form-group">
                     <label>Confirm</label><br>
                     <input type="password" name="userConfirm" value="" class="form-control" placeholder="Confirm"><br/>
                <c:if test="%{#errors.confirmPasswordErr != null}">
                    <font color="red">
                    <c:property value="%{#errors.confirmPasswordErr}"/>
                    </font><br/>
                </c:if>
                  </div>
                    <div class="form-group">
                     <label>Phone</label><br>
                     <input type="text" name="userPhone" value="<c:property value="userPhone"/>" class="form-control" placeholder="Phone"><br/>
                <c:if test="%{#errors.phoneformatErr != null}">
                    <font color="red">
                    <c:property value="%{#errors.phoneformatErr}"/>
                    </font><br/>
                </c:if>
                  </div>
                    <div class="form-group">
                     <label>Address</label><br>
                     <input type="text" name="userAddress" value="<c:property value="userAddress"/>" class="form-control" placeholder="Address"><br/>
                <c:if test="%{#errors.addressformatErr != null}">
                    <font color="red">
                    <c:property value="%{#errors.addressformatErr}"/>
                    </font><br/>
                </c:if>
                  </div>
                    
                    <input type="submit" name="btAction" value="Sign Up" class="btn btn-black"/>
                    <input type="reset" value="Reset"/>
                    <input type="hidden" name="userRole" value="Customer"/>
                    <input type="hidden" name="userStatus" value="New"/>
               </form><br>
               <form action="loginOrSignup" method="POST">
                    <input type="submit" name="btAction" value="Login">
                </form>
            </div>
         </div>
      </div>
    </body>
</html>
