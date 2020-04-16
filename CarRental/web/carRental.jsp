<%-- 
    Document   : carRental
    Created on : Mar 2, 2020, 5:55:57 PM
    Author     : SE130204
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/Product.css" type="text/css">
        <link rel="stylesheet" href="css/pagging.css" type="text/css">
        <link rel="stylesheet" href="css/PopupForm.css" type="text/css">
        <link rel="stylesheet" href="js/OnPageLoad.js" type="text/javascript">
        <link rel="stylesheet" href="js/Paging.js" type="text/javascript">
        <title>Car Rental</title>
        <script src="js/SearchEngine.js"></script>
        <script src="js/OnPageLoad.js"></script>
        <script src="js/Paging.js"></script>
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
                <form action="searchCar" method="POST">
                    <input class="leftnavBar" type="submit" name="btAction" value="Car rentals">
                </form>
                <form action="viewBookingCar" method="POST">
                    <input class="rightnavBar" type="submit" name="btAction" value="Manage Booking">
                </form>
                <form action="viewCarInCart" method="POST">
                    <input class="rightnavBar" type="submit" name="btAction" value="Cart">
                </form>
            </nav>
                    
            <div class="searchEngine">
                <div class="search" style="background-image: url(<c:property value="searchImg"/>);background-size: cover">
                    <form action="searchCar" method="POST">
                    <!--search box========================================================================-->
                            <div class="advSearchBox">
                                <!--search bar============================-->
                                <input id="txtsearch" type="text" name="carName" value="<c:property value="carName"/>" placeholder="Search Car"/>
                                <input id="searchButton" type="submit" name="btAction" value="" style="background-image: url(<c:property value="searchIcon"/>)"/><br>
                                <!--category box=========================================-->
                                <c:set var="selectedbrand" value="carBrand"/>
                                <select class="cat" name="carBrand">
                                    <option value="any">Any Brand</option>
                                    <c:iterator value="listOfBrand" status="brand">
                                        <option value="<c:property value="%{c_br_name}"/>" <c:if test="%{c_br_name == #selectedbrand}">selected=""</c:if>><c:property value="%{c_br_name}"/></option>
                                        </c:iterator>
                                </select>
                                <c:set var="selectedtype" value="carType"/>
                                <select class="cat" name="carType">
                                    <option value="any">Any Type</option>
                                        <c:iterator value="listOfType" status="type">
                                            <option value="<c:property value="%{c_ty_name}"/>" <c:if test="%{c_ty_name == #selectedtype}">selected=""</c:if>><c:property value="%{c_ty_name}"/></option>
                                        </c:iterator>
                                </select><br>
                                <!--range===================================================-->
                                <d>Amount from:</d> 
                                <input class="txtAmount" id="minAmount" type="number" name="carMinAmount" value="<c:property value="carMinAmount"/>" 
                                    step="1" min="1" placeholder="1" 
                                        oninput="checkValidAmount('minAmount', 'maxAmount', 'errorAmountRange', 'searchButton')"/>
                                <d>-to-</d>
                                <input class="txtAmount" id="maxAmount" type="number" name="carMaxAmount" value="<c:property value="carMaxAmount"/>" 
                                    step="1" min="1" placeholder="Max" 
                                        oninput="checkValidAmount('minAmount', 'maxAmount', 'errorAmountRange', 'searchButton')"/><br>
                                <div class="datebox">
                                    
                                <!--ren=================================================================t-->    
                                    <d>Rent from:</d><br>
                                    <input class="txtDate" id="minDate" type="date" name="carDateRent" value="<c:property value="carDateRent"/>" 
                                            min="" placeholder="MM/DD/YYYY" max="9999-12-31"
                                        oninput="checkValidDate('minDate', 'maxDate', 'errorRange', 'searchButton', 'minHour', 'minMin', 'maxHour', 'maxMin')"/>
                                    <select id="minHour" class="time" name="carRentHour" onchange="checkValidDate('minDate', 'maxDate', 'errorRange', 'searchButton', 'minHour', 'minMin', 'maxHour', 'maxMin')">
                                        <c:set var="rentHours" value="%{0}"/>
                                        <c:iterator begin="%{1}" end="%{24}" step="1">
                                            <option value="<c:property value="%{#rentHours}"/>" <c:if test="%{carRentHour == #rentHours}">selected=""</c:if>><c:property value="%{#rentHours}"/>H</option>
                                            <c:set var="rentHours" value="%{#rentHours + 1}"/>
                                        </c:iterator>
                                    </select>
                                    <select id="minMin" class="time" name="carRentMin" onchange="checkValidDate('minDate', 'maxDate', 'errorRange', 'searchButton', 'minHour', 'minMin', 'maxHour', 'maxMin')">
                                        <c:set var="rentMins" value="%{0}"/>
                                        <c:iterator begin="%{1}" end="%{4}" step="1">
                                            <option value="<c:property value="%{#rentMins}"/>" <c:if test="%{carRentMin == #rentMins}">selected=""</c:if>><c:property value="%{#rentMins}"/>M</option>
                                            <c:set var="rentMins" value="%{#rentMins + 15}"/>
                                        </c:iterator>
                                    </select>
                                </div>
                                <div class="datebox">
                                    <d>-to</d><br>
                                    <input class="txtDate" id="maxDate" type="date" name="carDateReturn" value="<c:property value="carDateReturn"/>" 
                                            min="" placeholder="MM/DD/YYYY" max="9999-12-31"
                                        oninput="checkValidDate('minDate', 'maxDate', 'errorRange', 'searchButton', 'minHour', 'minMin', 'maxHour', 'maxMin')"/>    
                                    <select id="maxHour" class="time" name="carReturnHour" onchange="checkValidDate('minDate', 'maxDate', 'errorRange', 'searchButton', 'minHour', 'minMin', 'maxHour', 'maxMin')">
                                        <c:set var="returnHours" value="%{0}"/>
                                        <c:iterator begin="%{1}" end="%{24}" step="1">
                                            <option value="<c:property value="%{#returnHours}"/>" <c:if test="%{carReturnHour == #returnHours}">selected=""</c:if>><c:property value="%{#returnHours}"/>H</option>
                                            <c:set var="returnHours" value="%{#returnHours + 1}"/>
                                        </c:iterator>
                                    </select>
                                    <select id="maxMin" class="time" name="carReturnMin" onchange="checkValidDate('minDate', 'maxDate', 'errorRange', 'searchButton', 'minHour', 'minMin', 'maxHour', 'maxMin')">
                                        <c:set var="returnMins" value="%{0}"/>
                                        <c:iterator begin="%{1}" end="%{4}" step="1">
                                            <option value="<c:property value="%{#returnMins}"/>" <c:if test="%{carReturnMin == #returnMins}">selected=""</c:if>><c:property value="%{#returnMins}"/>M</option>
                                            <c:set var="returnMins" value="%{#returnMins + 15}"/>
                                        </c:iterator>
                                    </select>
                                </div>
                                <d id="errorAmountRange">Invalid amount range!</d><br>
                                <d id="errorRange">Invalid date range!</d>

                    <!--submit=======================================================================================-->
                            </div>
                        </form>
                </div>
            </div>
                    
            <div class="CarRentalPage">
                <c:set var="list" value="listOfCar"/>
                <c:if test="%{#list != null}">
                    <c:iterator value="listOfCar" status="dto">
                        <div class="CarRental">
                        <form action="viewOrAddCar" method="POST">
                            <input type="hidden" name="cartCarID" value="<c:property value="%{c_id}"/>">
                            <input type="hidden" name="carName" value="<c:property value="carName"/>"/>
                            <input type="hidden" name="carBrand" value="<c:property value="carBrand"/>"/>
                            <input type="hidden" name="carType" value="<c:property value="carType"/>"/>
                            <input type="hidden" name="carMinAmount" value="<c:property value="carMinAmount"/>"/>
                            <input type="hidden" name="carMaxAmount" value="<c:property value="carMaxAmount"/>"/>
                            <input type="hidden" name="carDateRent" value="<c:property value="carDateRent"/>"/>
                            <input type="hidden" name="carRentHour" value="<c:property value="carRentHour"/>"/>
                            <input type="hidden" name="carRentMin" value="<c:property value="carRentMin"/>"/>
                            <input type="hidden" name="carDateReturn" value="<c:property value="carDateReturn"/>"/>
                            <input type="hidden" name="carReturnHour" value="<c:property value="carReturnHour"/>"/>
                            <input type="hidden" name="carReturnMin" value="<c:property value="carReturnMin"/>"/>
                            <table border="0" >
                            <tbody>
                                <tr>
                                    <td colspan="2" rowspan="4"><img src="<c:property value="%{c_image}"/>" style="height: 250px; width: 350px"></td>
                                    <td colspan="2"><d class="CarBrand"></d><c:property value="%{c_brand}"/></td>
                                    <td>Amount:</td>
                                    <td><c:property value="%{c_amount}"/></td>
                                    <td colspan="2" rowspan="2"><input class="viewButton" type="submit" name="btAction" value="View detail"></td>
                                </tr>
                                <tr>
                                  <td>Car's name:</td>
                                  <td><c:property value="%{c_name}"/></td>
                                  <td>Price per day:</td>
                                  <td><d class="CarPrice">$<c:property value="%{c_price}"/></d></td>
                                </tr>
                                <tr>
                                  <td>Car's type:</td>
                                  <td><c:property value="%{c_type}"/></td>
                                  <td>Car's color:</td>
                                  <td><div style="width: 50px; height: 40px; background-color: <c:property value="%{c_color}"/>; border: 1px solid black"></div></td>
                                  <td colspan="2" rowspan="2"><input class="rentButton" type="submit" name="btAction" value="Add to cart"></td>
                                </tr>
                                <tr>
                                    <td>Available:</td>
                                    <td><c:property value="%{c_dateRent}"/></td>
                                    <td>Expired:</td>
                                    <td><c:property value="%{c_dateReturn}"/></td>
                                  </tr>
                            </tbody>
                            </table>
                        </form>
                        </div> 
                    </c:iterator>
                    
                    <div class="Pagging">
                        <c:set var="lastPage" value="totalPage"/>
                        <c:set var="page" value="curPage"/>

                        <c:set var="pagelist" value="%{#lastPage>=7 ? 7:#lastPage}"/>
                        <c:set var="offset" value="%{1}"/>
                        <div class="pagecontainer">
                            <form action="searchCar" method="POST">
                                <input type="hidden" name="carName" value="<c:property value="carName"/>"/>
                                <input type="hidden" name="carBrand" value="<c:property value="carBrand"/>"/>
                                <input type="hidden" name="carType" value="<c:property value="carType"/>"/>
                                <input type="hidden" name="carMinAmount" value="<c:property value="carMinAmount"/>"/>
                                <input type="hidden" name="carMaxAmount" value="<c:property value="carMaxAmount"/>"/>
                                <input type="hidden" name="carDateRent" value="<c:property value="carDateRent"/>"/>
                                <input type="hidden" name="carRentHour" value="<c:property value="carRentHour"/>"/>
                                <input type="hidden" name="carRentMin" value="<c:property value="carRentMin"/>"/>
                                <input type="hidden" name="carDateReturn" value="<c:property value="carDateReturn"/>"/>
                                <input type="hidden" name="carReturnHour" value="<c:property value="carReturnHour"/>"/>
                                <input type="hidden" name="carReturnMin" value="<c:property value="carReturnMin"/>"/>
            <!--===============================================================================================--> 
                                <input id="fistPage" type="submit" name="curPage" onclick="changeValue_PH(1,'fistPage')" value="<<" <c:if test="%{#page <= 1}">disabled=""</c:if>>
                                <input id="prev" type="submit" name="curPage" onclick="changeValue_PH(<c:property value="%{#page-1}"/>,'prev')" value="<" <c:if test="%{#page <= 1}">disabled=""</c:if>>
            <!--===============================================================================================-->                    
                                <c:if test="%{#page>4 && #lastPage > 7}">
                                    <input type="button" value="..." disabled=""/>
                                </c:if>
            <!--===============================================================================================-->                        
                                    <c:set var="count" value="%{((#lastPage <= 7) || (#page-3<=1)) ? 1:((#lastPage-3) >=#page ? (#page-3):(#lastPage-6))}"/>
                                    <c:iterator begin="%{#offset}" end="%{#pagelist}" step="1">
                                        <input type="submit" name="curPage" value="<c:property value="%{#count}"/>" <c:if test="%{#count==#page}">id="selected"</c:if>>
                                        <c:set var="count" value="%{#count + 1}"/>
                                    </c:iterator>
            <!--===============================================================================================-->                        
                                <c:if test="%{#lastPage>7 && #page<(#lastPage-3)}">
                                    <input type="button" value="..." disabled=""/>
                                </c:if>
            <!--===============================================================================================-->                    
                                <input id="next" type="submit" name="curPage" onclick="changeValue_PH(<c:property value="%{#page+1}"/>,'next')" value=">" <c:if test="%{#page>=#lastPage}">disabled=""</c:if>>
                                <input id="lastPage" type="submit" name="curPage" onclick="changeValue_PH(<c:property value="%{#lastPage}"/>,'lastPage')" value=">>" <c:if test="%{#page>=#lastPage}">disabled=""</c:if>>
                                <input id="displayPage" type="button" disabled="" value="<c:property value="%{#page}"/>/<c:property value="%{#lastPage}"/>">
                            </form>
                        </div>
                    </div>
                </c:if>                           
                <c:elseif test="%{#list == null}">
                    <h1>Search result could not be found! (-_-)</h1>
                </c:elseif>
                <div id="productnotify">
                    <h1><c:property value="notification"/></h1>
                </div>
            </div>
            <footer>
            
            </footer>    
        </div>
    </body>
</html>
