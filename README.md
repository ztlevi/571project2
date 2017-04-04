# 571project2
## SOAP web service and BPEL

Goal: We are trying to make a web application about showing local real time weather

 1. Create new SOAP services
 -1.1 Retrieve real time datas from other REST service
 -1.2 Create own SOAP service to save data
 -1.3 Create WSClient to get these data

## Sever Side:
1. Create a database in phpMyAdmin called user_weather.
2. Uder user_weather, import the sql file in "./Server/sql/user_weather.sql".

##  REST Server Usage
1. Database: table user 
Query the user's password ->
`http://localhost:8080/webresources/com.weather.db.user/?username={name}`
2. Database: table usersavedplaces 
Query the user's last check place->
`http://localhost:8080/webresources/com.weather.db.usersavedplaces/?username={name}`
3. Retrieve weateher data.
`http://localhost:8080/webresources/weather/{location}`
