<%-- 
    Document   : manager
    Created on : Mar 19, 2020, 6:43:03 AM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/manager.css" type="text/css">
        <link rel="stylesheet" href="css/PopupForm.css" type="text/css">
        <link rel="stylesheet" href="js/OnPageLoad.js" type="text/javascript">
        <link rel="stylesheet" href="js/SearchEngine.js" type="text/javascript">
        <script src="js/OnPageLoad.js"></script>
        <script src="js/SearchEngine.js"></script>
        <title>Manager</title>
    </head>
    <body onload="showNotification('<c:property value="notification"/>','productnotify')">
        <div class="containerMain">
            <div class="info" style="background-image: url(<c:property value="banner"/>)">
                <!--logo==========================================================================-->
                    <div class="logo">
                        <img src="<c:property value="logo"/>"/>
                    </div>
                <!--search engine====================================================================================-->
  
                        <!--accoun==============================================t-->
                        <form action="loginOrSignup" method="POST">
                            <div class="login">
                            <c:if test="%{#session.NAME == null}">
                                    
                                    <input type="submit" value="Login" name="btAction" style="width: auto">
                                    <input type="submit" value="Sign up" name="btAction" style="width: auto">
                                   
                            </c:if>  
                            <c:if test="%{#session.NAME != null}">
                                <div class="logout">
                                    <input type="submit" value="Logout" name="btAction" style="width: auto">
                                    <button style="width: auto" disabled="">Welcome <c:property value="%{#session.NAME}"/></button>
                                </div>  
                            </c:if>
                            </div> 
                        </form>   
                    
            </div>
            <nav id="navbar">
                <h1>Create new Car</h1>
            </nav>
                    
            <div class="addNewContainer">
                <div class="addNew" style="background-image: url(<c:property value="searchImg"/>);background-size: cover">
                    <form action="addNewCar" enctype="multipart/form-data" method="POST">
                    <!--search box========================================================================-->
                            <div class="addNewBox">
                                <!--search bar============================-->
                                <input id="txtCarName" type="text" name="carName" value="<c:property value="carName"/>" placeholder="Car name" required=""/>
                                <input id="addButton" type="submit" name="btAction" value="Add new" style="background-image: url(<c:property value="searchIcon"/>)"/><br>
                                <!--category box=========================================-->
                                <d>Car brand:</d>
                                <select class="cat" name="carBrand">
                                    <c:iterator value="listOfBrand" status="brand">
                                        <option value="<c:property value="%{c_br_name}"/>"><c:property value="%{c_br_name}"/></option>
                                        </c:iterator>
                                </select>
                                <d>Car type:</d>
                                <select class="cat" name="carType">
                                        <c:iterator value="listOfType" status="type">
                                            <option value="<c:property value="%{c_ty_name}"/>"><c:property value="%{c_ty_name}"/></option>
                                        </c:iterator>
                                </select>
                                <d>Car color:</d>
                                <select class="cat" name="carColor">
                                        <c:iterator value="listOfColor" status="type">
                                            <option value="<c:property value="%{c_col_name}"/>"><c:property value="%{c_col_name}"/></option>
                                        </c:iterator>
                                </select><br>
                                <!--range===================================================-->
                                <d>Amount:</d> <br>
                                <input class="txtAmount" type="number" name="carAmount" value="<c:property value="carAmount"/>" 
                                       step="1" min="1" required=""><br> 
                                <d>Price per day:</d><br> 
                                <input class="txtAmount" type="number" name="carPrice" value="<c:property value="carAmount"/>" 
                                       step="0.01" min="1" required=""> <br>
                                     
                                <!--ren=================================================================t--> 
                                <div class="datebox">
                                    <d>Rent date:</d><br>
                                    <input class="txtDate" id="minDate" type="date" name="carDateRent" value="<c:property value="carDateRent"/>" 
                                            min="" placeholder="MM/DD/YYYY" max="9999-12-31"
                                        oninput="checkValidDate('minDate', 'maxDate', 'errorRange', 'addButton', 'minHour', 'minMin', 'maxHour', 'maxMin')"/>
                                    <select id="minHour" class="time" name="carRentHour" onchange="checkValidDate('minDate', 'maxDate', 'errorRange', 'addButton', 'minHour', 'minMin', 'maxHour', 'maxMin')">
                                        <c:set var="rentHours" value="%{0}"/>
                                        <c:iterator begin="%{1}" end="%{24}" step="1">
                                            <option value="<c:property value="%{#rentHours}"/>" <c:if test="%{carRentHour == #rentHours}">selected=""</c:if>><c:property value="%{#rentHours}"/>H</option>
                                            <c:set var="rentHours" value="%{#rentHours + 1}"/>
                                        </c:iterator>
                                    </select>
                                    <select id="minMin" class="time" name="carRentMin" onchange="checkValidDate('minDate', 'maxDate', 'errorRange', 'addButton', 'minHour', 'minMin', 'maxHour', 'maxMin')">
                                        <c:set var="rentMins" value="%{0}"/>
                                        <c:iterator begin="%{1}" end="%{4}" step="1">
                                            <option value="<c:property value="%{#rentMins}"/>" <c:if test="%{carRentMin == #rentMins}">selected=""</c:if>><c:property value="%{#rentMins}"/>M</option>
                                            <c:set var="rentMins" value="%{#rentMins + 15}"/>
                                        </c:iterator>
                                    </select>
                                </div>
                                <div class="datebox">
                                    <d>Return date:</d><br>
                                    <input class="txtDate" id="maxDate" type="date" name="carDateReturn" value="<c:property value="carDateReturn"/>" 
                                            min="" placeholder="MM/DD/YYYY" max="9999-12-31"
                                        oninput="checkValidDate('minDate', 'maxDate', 'errorRange', 'addButton', 'minHour', 'minMin', 'maxHour', 'maxMin')"/>    
                                    <select id="maxHour" class="time" name="carReturnHour" onchange="checkValidDate('minDate', 'maxDate', 'errorRange', 'addButton', 'minHour', 'minMin', 'maxHour', 'maxMin')">
                                        <c:set var="returnHours" value="%{0}"/>
                                        <c:iterator begin="%{1}" end="%{24}" step="1">
                                            <option value="<c:property value="%{#returnHours}"/>" <c:if test="%{carReturnHour == #returnHours}">selected=""</c:if>><c:property value="%{#returnHours}"/>H</option>
                                            <c:set var="returnHours" value="%{#returnHours + 1}"/>
                                        </c:iterator>
                                    </select>
                                    <select id="maxMin" class="time" name="carReturnMin" onchange="checkValidDate('minDate', 'maxDate', 'errorRange', 'addButton', 'minHour', 'minMin', 'maxHour', 'maxMin')">
                                        <c:set var="returnMins" value="%{0}"/>
                                        <c:iterator begin="%{1}" end="%{4}" step="1">
                                            <option value="<c:property value="%{#returnMins}"/>" <c:if test="%{carReturnMin == #returnMins}">selected=""</c:if>><c:property value="%{#returnMins}"/>M</option>
                                            <c:set var="returnMins" value="%{#returnMins + 15}"/>
                                        </c:iterator>
                                    </select>
                                </div><br>
                                <br><d id="errorRange">Invalid date range!</d>
                            </div>
                            <div class="addnewImg">
                                <img id="uploadImg" width="300" height="200" />
                                <input type="file" name="carImg" value="" required="" onchange="document.getElementById('uploadImg').src = window.URL.createObjectURL(this.files[0])"/>
                            </div>
                        </form>
                </div>
            </div>
                    
            <div id="productnotify">
                <h1><c:property value="notification"/></h1>
            </div>
            <footer>
            
            </footer>    
        </div>
    </body>
</html>
