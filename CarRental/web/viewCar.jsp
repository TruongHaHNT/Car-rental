<%-- 
    Document   : viewCar
    Created on : Mar 8, 2020, 6:36:00 PM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/CarInfo.css" type="text/css">
        <link rel="stylesheet" href="js/ViewCar.js" type="text/javascript">
        <link rel="stylesheet" href="js/SearchEngine.js" type="text/javascript">
        <title>View Car</title>
        <script src="js/ViewCar.js"></script>
        <script src="js/SearchEngine.js"></script>
    </head>
    <body>
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
                <c:set var="carInfo" value="getcarInformation"/>
                <c:if test="%{#carInfo != null}">
                    <div class="carInfo_Container">
                        <div class="carInfoBox">
                            <form action="viewOrAddCar" method="POST">
                            <table>
                                <tr>
                                    <th colspan="4"><img src="<c:property value="%{#carInfo.c_image}"/>" style="width: 540px; height: 400px"></th>
                                </tr>
                                <tr>
                                  <td colspan="4"><c:property value="%{#carInfo.c_brand}"/><br>
                                      car's name;<c:property value="%{#carInfo.c_name}"/></td>
                                </tr>
                                <tr>
                                    <td colspan="2">type:</td>
                                    <td colspan="2"><c:property value="%{#carInfo.c_type}"/></td>
                                </tr>
                                <tr>
                                  <td>Amount</td>
                                  <td><input id="carAmount" type="number" name="cartCarAmount" value="1" step="1" required="" oninput="changeAmount('carAmount', 'displayPrice', <c:property value="%{#carInfo.c_price}"/>)"></td>
                                  <td>price:</td>
                                  <td><d id="displayPrice">$<c:property value="%{#carInfo.c_price}"/></d></td>
                                </tr>
                                <tr>
                                  <td>Color:</td>
                                  <td><div style="width: 100px; height: 50px; border: 1px solid black; background: <c:property value="%{#carInfo.color}"/>"></div></td>
                                  <td>Available:</td>
                                  <td><c:property value="%{#carInfo.c_amount}"/></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input class="ReturnButton" type="submit" name="btAction" value="Return" onclick="removeRequired('carAmount')"/>
                                    </td>
                                    <td colspan="2">
                                            <input type="hidden" name="cartCarID" value="<c:property value="%{#carInfo.c_id}"/>">
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
                                            <input class="addButton" type="submit" name="btAction" value="Add to cart"/>
                                        
                                    </td>
                                </tr>
                              </table>
                           </form>
                        </div>
                        <div class="RatingBox">
                            <form action="carRating" method="POST">
                                <c:if test="%{carRatingValue > 0}">
                                    <h1>Rating : <span style="color: green">
                                            <c:if test="%{carRatingValue >= 9}">
                                                <span style="color: green">
                                                    <c:property value="carRatingValue"/>
                                                </span>
                                            </c:if>
                                            <c:elseif test="%{carRatingValue >= 7}">
                                                <span style="color: yellowgreen">
                                                    <c:property value="carRatingValue"/>
                                                </span>
                                            </c:elseif>
                                            <c:elseif test="%{carRatingValue >= 5}">
                                                <span style="color: yellow">
                                                    <c:property value="carRatingValue"/>
                                                </span>
                                            </c:elseif>
                                            <c:elseif test="%{carRatingValue >= 3}">
                                                <span style="color: orange">
                                                    <c:property value="carRatingValue"/>
                                                </span>
                                            </c:elseif>
                                            <c:else>
                                                <span style="color: crimson">
                                                    <c:property value="carRatingValue"/>
                                                </span>
                                            </c:else>/10.00
                                        </span>
                                    </h1>
                                </c:if>
                                <c:else>
                                    <h1>
                                        This car has not been rated.
                                    </h1>
                                </c:else>
                                    <input type="hidden" name="carID" value="<c:property value="%{#carInfo.c_id}"/>">
                                    <input type="number" name="ratingValue" value="" min="1" max="10" step="1" required="">
                                    <input class="addButton" type="submit" value="Submit Rating">
                            </form>
                        </div>
                    </div>
                </c:if>                           
                <c:elseif test="%{#carInfo == null}">
                    <h1>This car is not Available right now! (-_-)</h1>
                </c:elseif>
            </div>
            <footer>
            
            </footer>    
        </div>
    </body>
</html>
