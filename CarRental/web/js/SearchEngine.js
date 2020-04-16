/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkValidAmount(minID, maxID, errorBox, searchButton){
    var txtmin = document.getElementById(minID).value;
    var txtmax = document.getElementById(maxID).value;
    var MaxNumber = Number.MAX_SAFE_INTEGER;
    if(txtmin === null || txtmin === ""){
        txtmin = "1";
    }
    if(txtmax === null || txtmax === ""){
        txtmax = MaxNumber;
    }
    var min = parseInt(txtmin);
    var max = parseInt(txtmax);
    if(min<1){
        min = 1;
        document.getElementById(minID).value = "";
    }
    if(max<1){
        max = parseInt(MaxNumber);
        document.getElementById(maxID).value = "";
    }
    
    if(min <= max){
        document.getElementById(errorBox).style.visibility = "hidden";
        document.getElementById(searchButton).disabled = false;
        document.getElementById(searchButton).style.opacity = "1";
    }else{
        document.getElementById(errorBox).style.visibility = "visible";
        document.getElementById(searchButton).disabled = true;
        document.getElementById(searchButton).style.opacity = "0.65";
    }    
}

function checkValidDate(minID, maxID, errorBox, searchButton, rentHourID, rentMinID, returnHourID, returnMinID){     
    try {
        var rentHour = parseInt(document.getElementById(rentHourID).value);
        rentHour = rentHour < 10 ? ("0"+rentHour):rentHour;
        var rentMin = parseInt(document.getElementById(rentMinID).value);
        rentMin = rentMin < 10 ? ("0"+rentMin):rentMin;
        var returnHour = parseInt(document.getElementById(returnHourID).value);
        returnHour =  returnHour < 10 ? ("0"+returnHour):returnHour;
        var returnMin = parseInt(document.getElementById(returnMinID).value);
        returnMin =  returnMin < 10 ? ("0"+returnMin):returnMin;
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
        var rentdateTime = document.getElementById(minID).value;
        var returndateTime = document.getElementById(maxID).value;
        if(rentdateTime === ""){
            document.getElementById(minID).value = today;
            rentdateTime = today;
        }
        
        if(returndateTime === ""){
            document.getElementById(maxID).value = today;
            returndateTime = today;
        }
        today = new Date();
        var getRentDate = new Date(Date.parse(rentdateTime+" "+rentHour+":"+rentMin+":00"));
        var getReturnDate = new Date(Date.parse(returndateTime+" "+returnHour+":"+returnMin+":00"));
        
        var Difference_In_Time = getReturnDate.getTime() - getRentDate.getTime(); 
  
        var Difference_In_Days = Difference_In_Time / (1000 * 60*60); 
         
        if(getRentDate.getTime()>getReturnDate.getTime()){
            document.getElementById(searchButton).disabled=true;
            document.getElementById(errorBox).style.visibility ="visible";
            document.getElementById(errorBox).innerHTML ="Invalid rental date and time!";
        }
        else if(getRentDate.getTime() < today.setTime(today.getTime()+(1000*60*60))){
            document.getElementById(searchButton).disabled=true;
            document.getElementById(errorBox).style.visibility ="visible";
            document.getElementById(errorBox).innerHTML ="The rental time must be at least 1 hour from the current time";
        }
        else if(Difference_In_Days < 1){
            document.getElementById(searchButton).disabled=true;
            document.getElementById(errorBox).style.visibility ="visible";
            document.getElementById(errorBox).innerHTML ="The total rental period must be at least 1 hour";
        }else{
            document.getElementById(searchButton).disabled=false;
            document.getElementById(errorBox).style.visibility ="hidden";
            document.getElementById(errorBox).innerHTML ="";
        }
    }catch (e){
        alert(e);
    }
}

function checkValidBookingDate(minID, maxID, errorBox, searchButton){
    var txtMinDate = document.getElementById(minID).value;
    var txtMaxDate = document.getElementById(maxID).value;
    
    try {
        var minDate = new Date();
        var maxDate = new Date();
        
        if(txtMinDate === ""){
            document.getElementById(minID).value = "1753-01-01";
            minDate = "1753-01-01";
        }else{
            minDate = txtMinDate;
        }
        if(txtMaxDate === ""){
            document.getElementById(maxID).value = document.getElementById(maxID).max;
            maxDate = document.getElementById(maxID).max;
        }else{
            maxDate = txtMaxDate;
        }
        
        if(minDate <= maxDate){
            document.getElementById(errorBox).style.visibility = "hidden";
            document.getElementById(errorBox).innerHTML = "Invalid date range!";
            document.getElementById(searchButton).disabled = false;
            document.getElementById(searchButton).style.opacity = "1";
        }else{
            document.getElementById(errorBox).style.visibility = "visible";
            document.getElementById(searchButton).disabled = true;
            document.getElementById(searchButton).style.opacity = "0.6";
        }
    } catch (e) {
        alert(e);
    }
}