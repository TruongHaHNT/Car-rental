/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function updateAmountOfItem(amountID, priceID, priceHTML){
    try {
    var amount = document.getElementById(amountID).value;
    var priceVar = 0;
    if(amount === "" || amount<1){
        amount = 0;
    }
    priceVar = amount*parseFloat(document.getElementById(priceID).value);
    document.getElementById(priceHTML).innerHTML ='$'+priceVar.toFixed(2);
    }catch (e) {
        alert(e);
    }
}

function popUpConfirmBox(BoxID,ButtonID,message){
    document.getElementById(BoxID).style.height = "100%";
    document.getElementById(ButtonID).value = message;
}

function closePopUpBox(BoxID){
    document.getElementById(BoxID).style.height = "0%";
}