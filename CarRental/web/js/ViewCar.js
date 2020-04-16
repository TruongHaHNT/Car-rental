/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function changeAmount(inputID, displayID, price){
    var amount = document.getElementById(inputID).value;
    if(amount <= 0){
        document.getElementById(inputID).value = "";
    }
    document.getElementById(displayID).innerHTML = "$"+(amount*price).toFixed(2);
}

function removeRequired(inputID){
    document.getElementById(inputID).required = false;
}