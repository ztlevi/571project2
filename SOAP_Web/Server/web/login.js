$(document).ready(function () {
    
            $(Login).click(checkID);
            $(CancelButton).click(function(){
                window.location.assign("https://www.google.com/")
            });
            
});

function changePage() {
    window.location.assign("http://localhost:8080/Server/weather.html")
}

function checkID(){
        //var requestData=$('#txtCity').val();
        
    var webserUrl = "http://localhost:8080/Server/User_Service?WSDL";
    var userName=$('#username').val();
    var passWord=$('#psw').val();
       
    var soapRequest = '<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">\
                       <SOAP-ENV:Header/>\
                       <S:Body>\
                       <ns2:validUser xmlns:ns2="http://soap.com/">\
                       <username>'+userName+'</username>\
                       <password>'+passWord+'</password>\
                       </ns2:validUser>\
                       </S:Body>\
                       </S:Envelope>';
    $.ajax({
        type: "POST",
        url:webserUrl,
        contentType: "text/xml",
        dataType: "xml",
        data: soapRequest,
        success: function(data, status, req){
            console.log(data);
            var  result=data.getElementsByTagName("return")[0].firstChild.nodeValue;
            //obj=JSON.parse(txt);
            // display the result if success
            if (result=="true"){
                console.log("perfect");
                changePage();
            }else{
                console.log("bad");
                alert("Your Account name or password is wrong, please write correct data!")
            }
        },
        error: function(data, status, req){
            alert(req.responseText + " " + status);
        }
    });
    }