<%-- 
    Document   : mailCheck
    Created on : Jan 11, 2020, 5:00:57 AM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <title>Verify your Account</title>
    </head>
    <body>
            <div class="sidenav">
            <div class="login-main-text">
            <h2>welcome:<br><c:property value="userEmail"/></h2>
            <p>Enter verify code we have sent to your email<br> to active your Account.</p>
         </div>
      </div>
      <div class="main">
         <div class="col_md_6_col_sm_12">
            <div class="login-form">
                <form action="verifyOrCancel" method="POST">
                  <div class="form-group">
                    <label>VERIFY</label><br>
                    <input type="text" name="txtVerify" value="" class="form-control" placeholder="Enter your verify code"/><br>
                  </div>
                    <input type="hidden" name="verifyCode" value="<c:property value="verifyCode"/>" />
                    <input type="hidden" name="userEmail" value="<c:property value="userEmail"/>" />
                    <input type="hidden" name="userPassword" value="<c:property value="userPassword"/>" />
                    <input type="hidden" name="userName" value="<c:property value="userName"/>" />
                    <c:set var="errors" value="verifyCodeError" />
                    <font color="red"><br>
                    <c:if test="%{#errors.invalidVerifyCode != null}">
                        <c:property value="%{#errors.invalidVerifyCode}"/><br>
                    </c:if>
                    </font><br>
                    <input type="submit" name="btAction" value="Verify" class="form-control"/>
                    <input type="submit" name="btAction" value="Cancel" class="form-control"/>

                    <br>Can not receive verify code?<br> <input type="submit" name="btAction" value="Click here to re-send verify code" /> 
               </form><br>
            </div>
         </div>
      </div>
    </body>
</html>
