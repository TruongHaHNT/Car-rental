/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function showBookingDetails(showbuttonID,boxID){
    var box = document.getElementById(boxID);
    if(box.style.height === "600px"){
        document.getElementById(boxID).style.height="0px";
        document.getElementById(showbuttonID).value="Details";
    }else{
        document.getElementById(boxID).style.height="600px";
        document.getElementById(showbuttonID).value="Close";
    }
}

function popupBillDeleteBox(buttonID, boxID){
    document.getElementById(buttonID).style.visibility = "hidden";
    document.getElementById(boxID).style.visibility = "visible";
}

function closeBillDeleteBox(buttonID, boxID){
    document.getElementById(boxID).style.visibility = "hidden";
    document.getElementById(buttonID).style.visibility = "visible";
}