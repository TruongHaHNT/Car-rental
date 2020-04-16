<%-- 
    Document   : cart
    Created on : Mar 9, 2020, 6:24:31 AM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/Cart.css" type="text/css">
        <link rel="stylesheet" href="css/PopupForm.css" type="text/css">
        <link rel="stylesheet" href="js/Cart.js" type="text/javascript">
        <link rel="stylesheet" href="js/OnPageLoad.js" type="text/javascript">
        <title>Cart</title>
        <script src="js/Cart.js"></script>
        <script src="js/OnPageLoad.js"></script>
    </head>
    <body onload="showNotification('<c:property value="notification"/>','productnotify2')">
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
            <div class="CarRentalPage">
                <c:set var="yourCart" value="%{#session.CART.listOfCar}"/>
                <c:if test="%{#yourCart != null}">
                    <c:set var="count" value="%{1}"/>
                    <c:set var="totalMoney" value="%{0}"/>
                    <c:iterator value="%{#yourCart}" status="dto">
                            <div class="CarInCart_Item">
                                <form action="deleteOrUpdateCart" method="POST">
                                <table border="0" >
                                            <c:set var="updateButton" value="%{'updateButton' + #count}"/>
                                            <c:set var="updatePrice" value="%{'upPrice' + #count}"/>
                                            <c:set var="priceHTML" value="%{'priceHTML' + #count}"/>
                                            <c:set var="deleteButton" value="%{'deleteButton' + #count}"/>
                                            <c:set var="updateAmount" value="%{'upAmount' + #count}"/>
                                            <c:set var="popUpBoxID" value="%{'popUp' + #count}"/>
                                            <c:set var="confirmButtonID" value="%{'Confirm' + #count}"/>
                                            <c:hidden name="updateCartID" value="%{key}"/>
                                    <tbody>
                                        <tr>
                                            
                                            <td colspan="2" rowspan="4"><img src="<c:property value="%{value.c_image}"/>" style="height: 150px; width: 250px"></td>
                                            <td colspan="2"><d class="CarBrand"></d><c:property value="%{value.c_brand}"/></td>
                                            <td>Amount:</td>
                                            <td><input id="<c:property value="%{#updateAmount}"/>" type="number" name="updateCarAmount" value="<c:property value="%{value.c_amount}"/>" min="1" step="1"
                                                       required="" oninput="updateAmountOfItem('<c:property value="%{#updateAmount}"/>','<c:property value="%{#updatePrice}"/>','<c:property value="%{#priceHTML}"/>')"></td>
                                            <td colspan="2" rowspan="2"><input id="<c:property value="%{#deleteButton}"/>" class="deleteButton" type="button" name="" value="Delete" onclick="popUpConfirmBox('<c:property value="%{#popUpBoxID}"/>','<c:property value="%{#confirmButtonID}"/>','Confirm Delete')"></td>
                                        </tr>
                                        <tr>

                                          <td>Car's name: </td>
                                          <td><c:property value="%{value.c_name}"/></td>
                                          <td>total Price:</td>
                                          <input id="<c:property value="%{#updatePrice}"/>" type="hidden" name="updateCarPrice" value="<c:property value="%{value.c_price}"/>">
                                          <td><d class="CarPrice" id="<c:property value="%{#priceHTML}"/>"></d></td>
                                          <script>
                                              document.getElementById('<c:property value="%{#priceHTML}"/>').innerHTML = '$'+parseFloat('<c:property value="%{value.c_price*value.c_amount}"/>').toFixed(2);
                                           </script>
                                           <c:set var="totalMoney" value="%{#totalMoney + value.c_price*value.c_amount}"/>
                                        </tr>
                                        <tr>
                                            
                                          <td>Car's type:</td>
                                          <td><c:property value="%{value.c_type}"/></td>
                                          <td>Car's color:</td>
                                          <td><div style="width: 50px; height: 40px; background-color: <c:property value="%{key.c_color}"/>; border: 1px solid black"></div></td>
                                          <td colspan="2" rowspan="2"><input id="<c:property value="%{#updateButton}"/>" class="updateButton" type="button" name="" value="Update" onclick="popUpConfirmBox('<c:property value="%{#popUpBoxID}"/>','<c:property value="%{#confirmButtonID}"/>','Confirm Update')"></td>
                                        </tr>
                                        <tr class="dateTimeBooking">
                                            <td>Rent date:</td>
                                            <td><c:property value="%{value.c_dateRent}"/></td>
                                            <td>Return date:</td>
                                            <td><c:property value="%{value.c_dateReturn}"/></td>
                                            
                                        </tr>
                                    </tbody>
                                </table>
                                            <div class="totalMoney">
                                            <h2>Total: $ <c:property value="%{#totalMoney}"/></h2>    
                                            </div>
                                                
                                            <c:if test="%{value.c_status != null}">  
                                                <font color="red">
                                                    <c:property value="%{value.c_status}"/>
                                                </font><br/>
                                            </c:if>
                                <div class="popUpBox" id="<c:property value="%{#popUpBoxID}"/>">
                                    <table border="">
                                        <tbody>
                                            <tr>
                                                <td><input class="cancelPopupButton" type="button" value="Cancle" onclick="closePopUpBox('<c:property value="%{#popUpBoxID}"/>')"></td>
                                                <td><input class="ConfirmButton" id="<c:property value="%{#confirmButtonID}"/>" type="submit" name="btAction" value=""></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                </form>            
                            </div>
                                            
                        <c:set var="count" value="%{#count + 1}"/>             
                    </c:iterator>
                    <div class="ConfirmBox_buttom">
                        <input class="confirmButton_1" type="button" value="<<===-START-BOOKING-===>>" onclick="popupConfirmWindow('productnotify')">
                    </div>
                    <div id="productnotify">
                        <form action="verifyCustomerCart" method="POST">
                            <div id="ConfirmForm">
                                <h1 id="confirmText"><c:property value="%{#session.CART.customerID}"/><br>-Confirm Your Booking.</h1>
                                <input type="text" name="discountCode" value="" placeholder="Enter discount code to access discount or leave EMPTY" style="width: 500px; font-size: 20px; background: black; color: lightskyblue">
                                <input id="cancleBooking" type="button" value="Cancel" onclick="closeConfirmWindow('productnotify')"> 
                                <input id="confirmBooking" type="submit" name="btAtion" value="Confirm Booking"> 
                            </div>
                        </form>
                    </div>
                </c:if>                           
                <c:elseif test="%{#yourCart == null}">
                    <h1>Your cart is empty!</h1>
                </c:elseif>
                <div id="productnotify2">
                    <h1><c:property value="notification"/></h1>
                </div>
            </div>
            <footer>
            
            </footer>    
        </div>
    </body>
</html>
