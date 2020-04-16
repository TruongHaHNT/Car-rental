<%-- 
    Document   : bookingHistory
    Created on : Mar 12, 2020, 8:52:56 PM
    Author     : SE130204
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Tool.css" type="text/css">
        <link rel="stylesheet" href="css/Booking.css" type="text/css">
        <link rel="stylesheet" href="js/SearchEngine.js" type="text/javascript">
        <link rel="stylesheet" href="js/Booking.js" type="text/javascript">
        <link rel="stylesheet" href="js/OnPageLoad.js" type="text/javascript">
        <link rel="stylesheet" href="js/Paging.js" type="text/javascript">
        <link rel="stylesheet" href="css/pagging.css" type="text/css">
        <title>Booking History</title>
        <script src="js/OnPageLoad.js"></script>
        <script src="js/SearchEngine.js"></script>
        <script src="js/Paging.js"></script>
        <script src="js/Booking.js"></script>
    </head>
   <body onload = "getMaxDateToday('minDate', 'maxDate', '<c:property value="bookingMinDate"/>', '<c:property value="bookingMaxDate"/>')">
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
                    <form action="viewBookingCar" method="POST">
                    <!--search box========================================================================-->
                            <div class="advSearchBox">
                                <!--search bar============================-->
                                <input id="txtsearch" type="text" name="bookingSearch" value="<c:property value="bookingSearch"/>" placeholder="Search Car"/>
                                <input type="hidden" name="btAction" value="Manage Booking"/>
                                <input id="searchButton" type="submit" name="" value="" style="background-image: url(<c:property value="searchIcon"/>)"/><br>
                                <!--ren=================================================================t-->    
                                <div class="datebox">
                                    <d>Rent from:</d><br>
                                    <input class="txtDate" id="minDate" type="date" name="bookingMinDate" value="<c:property value="bookingMinDate"/>" 
                                            min="1753-01-01" placeholder="MM/DD/YYYY" max=""
                                        oninput="checkValidBookingDate('minDate', 'maxDate', 'errorRange', 'searchButton')"/>
                                </div>
                                <div class="datebox">
                                    <d>-to</d><br>
                                    <input class="txtDate" id="maxDate" type="date" name="bookingMaxDate" value="<c:property value="bookingMaxDate"/>" 
                                            min="1753-01-01" placeholder="MM/DD/YYYY" max=""
                                        oninput="checkValidBookingDate('minDate', 'maxDate', 'errorRange', 'searchButton')"/>    
                                </div>
                                <d id="errorRange">Invalid date range!</d>
                            </div>
                    <!--submit=======================================================================================-->
                        </form>
                </div>
            </div>
                    
            <div class="BookingCarForm">
                <c:set var="billInfo" value="bookingList"/>
                <c:if test="%{#billInfo != null}">
                    <div class="booking_Container">
                        <h1>Booking History</h1>
                        <c:set var="count" value="%{1}"/>
                        <c:iterator value="bookingList" status="billdto">
                            <c:set var="showButton" value="%{'showButton' + #count}"/>
                            <c:set var="showBox" value="%{'showDetalsBox' + #count}"/>
                            <c:set var="cancelBillBox" value="%{'cancelBillBox' + #count}"/>
                            <c:set var="cancelBillButton" value="%{'cancelBillButton' + #count}"/>
                            <c:set var="billStatus" value="bill.bill_status"/>
                            <form action="deleteSelectedBill" method="POST">
                                <div class="divTable greenTable">
                                    <div class="divTableBody">
                                        <div class="divTableRow">
                                            <div class="divTableCell">
                                                Bill_ID:<br><c:property value="bill.bill_ID"/>
                                                <input type="hidden" name="billID" value="<c:property value="bill.bill_ID"/>">
                                                <input type="hidden" name="bookingSearch" value="<c:property value="bookingSearch"/>"/>
                                                <input type="hidden" name="bookingMinDate" value="<c:property value="bookingMinDate"/>"/>
                                                <input type="hidden" name="bookingMaxDate" value="<c:property value="bookingMaxDate"/>"/>
                                                <input type="hidden" name="btAction" value="Manage Booking"/>
                                            </div>
                                            <div class="divTableCell">
                                                User Email:<br><c:property value="bill.bill_u_email"/>
                                            </div>
                                            <div class="divTableCell">
                                                Order Date:<br><c:property value="bill.bill_booking_Date"/>
                                            </div>
                                            <div class="divTableCell">
                                                Discount:<br><c:property value="bill.bill_discount"/>%
                                            </div>
                                            <div class="divTableCell">
                                                <input class="detailsButton" id="<c:property value="%{#showButton}"/>" type="button" value="Details" onclick="showBookingDetails('<c:property value="%{#showButton}"/>','<c:property value="%{#showBox}"/>')">
                                            </div>
                                            <div class="divTableCell">
                                                <c:if test="%{#billStatus == 'Active'}">
                                                    <c:if test="%{isEnded == false}">
                                                        <input class="rentingButton" type="button" value="Booking">
                                                        <input id="<c:property value="%{#cancelBillButton}"/>" class="cancelBillButton" type="button" value="cancel booking" onclick="popupBillDeleteBox('<c:property value="%{#cancelBillButton}"/>','<c:property value="%{#cancelBillBox}"/>')">
                                                        <div class="popupBillDeleteBox" id="<c:property value="%{#cancelBillBox}"/>">
                                                            <input type="button" value="Close" onclick="closeBillDeleteBox('<c:property value="%{#cancelBillButton}"/>','<c:property value="%{#cancelBillBox}"/>')">
                                                            <input class="cancelBillButton" type="submit" value="Confirm Cancel">
                                                        </div>
                                                    </c:if>
                                                    <c:elseif test="%{isEnded == true}">
                                                        <input class="endedButton" type="button" value="Ended">
                                                    </c:elseif>
                                                </c:if>
                                                <c:else>
                                                    <input class="deletedCarButton" type="button" value="Ended">
                                                </c:else>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div>
                                <div class="billDetailsTable" id="<c:property value="%{#showBox}"/>">
                                    <c:set var="count2" value="%{1}"/>
                                    <c:iterator value="list" status="detailsdto">
                                        <c:set var="cancelDetailsBox" value="%{'detail' + #cancelBillBox + #count2}"/>
                                        <c:set var="cancelDetailsButton" value="%{'button' + #cancelBillBox + #count2}"/>
                                        <form action="deleteSelectedCarInBill" method="POST">
                                            <table border="0" >
                                                <tbody>
                                                    <tr>
                                                    <input type="hidden" name="billDetailsID" value="<c:property value="%{c_id}"/>">
                                                    <input type="hidden" name="billID" value="<c:property value="bill.bill_ID"/>">
                                                    <input type="hidden" name="bookingSearch" value="<c:property value="bookingSearch"/>"/>
                                                    <input type="hidden" name="bookingMinDate" value="<c:property value="bookingMinDate"/>"/>
                                                    <input type="hidden" name="bookingMaxDate" value="<c:property value="bookingMaxDate"/>"/>
                                                    <input type="hidden" name="btAction" value="Manage Booking"/>
                                                        <td colspan="2" rowspan="4"><img src="<c:property value="%{c_image}"/>" style="height: 125px; width: 200px"></td>
                                                        <td colspan="2"><d class="CarBrand"></d><c:property value="%{c_brand}"/></td>
                                                        <td>Amount:</td>
                                                        <td><c:property value="%{c_amount}"/></td>
                                                        <td colspan="2" rowspan="4">
                                                            <c:if test="%{#billStatus == 'Active'}">
                                                                <c:if test="%{c_status == 'Ended'}">
                                                                    <d class="txtEnded">Expired</d>
                                                                </c:if>
                                                                <c:elseif test="%{c_status == 'Waiting' || c_status == 'Renting'}">
                                                                    <d class="txtRenting"><c:property value="%{c_status}"/></d>
                                                                    <input id="<c:property value="%{#cancelDetailsButton}"/>" class="cancelDetails" type="button" value="Cancel booking" onclick="popupBillDeleteBox('<c:property value="%{#cancelDetailsButton}"/>','<c:property value="%{#cancelDetailsBox}"/>')">
                                                                    <div class="popupBillDeleteBox" id="<c:property value="%{#cancelDetailsBox}"/>">
                                                                        <input type="button" value="Close" onclick="closeBillDeleteBox('<c:property value="%{#cancelDetailsButton}"/>','<c:property value="%{#cancelDetailsBox}"/>')">
                                                                        <input class="cancelDetails" type="submit" value="Confirm Cancel">
                                                                    </div>
                                                                </c:elseif>
                                                                <c:elseif test="%{c_status == 'Canceled'}">
                                                                    <d class="txtDeleted">Canceled</d>
                                                                </c:elseif>
                                                            </c:if>
                                                            <c:else>
                                                                <d class="txtDeleted">Canceled</d>
                                                            </c:else>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                      <td>Car's name:</td>
                                                      <td><c:property value="%{c_name}"/></td>
                                                      <td>Price per Car:</td>
                                                      <td><d class="CarPrice">$<c:property value="%{c_price}"/></d></td>
                                                    </tr>
                                                    <tr>
                                                      <td>Car's type:</td>
                                                      <td><c:property value="%{c_type}"/></td>
                                                      <td>Car's color:</td>
                                                      <td><div style="width: 50px; height: 40px; background-color: <c:property value="%{c_color}"/>; border: 1px solid black"></div></td>

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
                                        <c:set var="count2" value="%{#count2 + 1}"/>
                                    </c:iterator>
                                </div>
                            </div>
                                    <c:set var="count" value="%{#count + 1}"/>  
                        </c:iterator>
                    </div>
                    
                    <div class="Pagging">
                        <c:set var="lastPage" value="totalPage"/>
                        <c:set var="page" value="curPage"/>

                        <c:set var="pagelist" value="%{#lastPage>=7 ? 7:#lastPage}"/>
                        <c:set var="offset" value="%{1}"/>
                        <div class="pagecontainer">
                            <form action="viewBookingCar" method="POST">
                                <input type="hidden" name="bookingSearch" value="<c:property value="bookingSearch"/>"/>
                                <input type="hidden" name="bookingMinDate" value="<c:property value="bookingMinDate"/>"/>
                                <input type="hidden" name="bookingMaxDate" value="<c:property value="bookingMaxDate"/>"/>
                                <input type="hidden" name="btAction" value="Manage Booking"/>
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
                <c:elseif test="%{#billInfo == null}">
                    <h1>Result not found! (-_-)</h1>
                </c:elseif>
            </div>
            <footer>
            
            </footer>    
        </div>
    </body>
</html>
