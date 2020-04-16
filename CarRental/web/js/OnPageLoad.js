/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function showNotification(text,id){
    if(text !== ""){
        document.getElementById(id).style.height = "20%";
        setTimeout(function(){closeNotification(id);},2000);
    }
}
function closeNotification(id){
    document.getElementById(id).style.height = "0%";
}

function popupConfirmWindow(boxID){
    document.getElementById(boxID).style.height = "100%";
}
function closeConfirmWindow(boxID){
    document.getElementById(boxID).style.height = "0%";
}

function getMaxDateToday(minID, maxID, mintext, maxtext){
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
     if(dd<10){
            dd='0'+dd;
        } 
        if(mm<10){
            mm='0'+mm;
        } 

    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById(minID).setAttribute("max", today);
    document.getElementById(maxID).setAttribute("max", today);
    document.getElementById(maxID).setAttribute("placeholder", today);
    
    if(mintext === ""){
        document.getElementById(minID).value = "1753-01-01";
    }else{
        document.getElementById(minID).value = mintext;
    }
    if(maxtext === ""){
        document.getElementById(maxID).value = today;
    }else{
        document.getElementById(maxID).value = maxtext;
    }
}