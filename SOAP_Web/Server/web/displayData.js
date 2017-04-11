// global object for storing the data
var obj;

$(document).ready(function () {
    // load google charts packages
    google.charts.load('current', {'packages':['gauge']});

    $(btnGetWeather).click(retrieveData); 
});

function retrieveData(){
    var requestData=$('#txtCity').val();
    var webserUrl = "http://localhost:8080/Server/Weather_Service?WSDL";
    
    var soapRequest = '<?xml version="1.0" encoding="UTF-8"?>\
                                     <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">\
                                     <SOAP-ENV:Header/>\
                                     <S:Body>\
                                     <ns2:setLocation xmlns:ns2="http://soap.com/">\
                                     <location>'+requestData+'</location>\
                                     </ns2:setLocation>\
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
            var txt =data.getElementsByTagName("return")[0].firstChild.nodeValue;
            obj=JSON.parse(txt);
            // display the result if success
            display();
        },
        error: function(data, status, req){
            alert(req.responseText + " " + status);
        }
    });
}

function display() {
    var resultElement= $('#resultDiv');
    resultElement.html(
        'temp_f: '+obj.current.temp_f+'<br/>'+
            'Last_updated: '+obj.current.last_updated+'<br/>'+
            'Temprature_feels_like: '+obj.current.feelslike_c+'<br/>'+
            'wind_degree: '+obj.current.wind_degree+'<br/>'+
            'wind_dir: '+obj.current.wind_dir+'<br/>'+
            'wind_kph: '+obj.current.wind_kph+'<br/>'
        
    );

    if (obj != null) {
        google.charts.setOnLoadCallback(drawFahrenheitTemp);
        google.charts.setOnLoadCallback(drawCelsiusTemp);
    }
}

function drawCelsiusTemp() {

    var data = google.visualization.arrayToDataTable([
        ['Label', 'Value'],
        ['Celsius', obj.current.temp_c]
    ]);

    var options = {
        width: 400, height: 120,
        redFrom: 40, redTo: 50,
        min: -30,
        max: 50,
        yellowFrom:30, yellowTo: 40,
        greenFrom :-30, greenTo: -10,
        minorTicks: 5
    };

    var chart = new google.visualization.Gauge(document.getElementById('Celsius_div'));

    chart.draw(data, options);
}

function drawFahrenheitTemp() {

    var data = google.visualization.arrayToDataTable([
        ['Label', 'Value'],
        ['Fahrenheit', obj.current.temp_f]
    ]);

    var options = {
        width: 400, height: 120,
        min: -22,
        max: 122,
        redFrom: 104, redTo: 122,
        yellowFrom:86, yellowTo: 104,
        greenFrom :-22, greenTo: 14,
        minorTicks: 5
    };

    var chart = new google.visualization.Gauge(document.getElementById('Fahrenheit_div'));

    chart.draw(data, options);

}
