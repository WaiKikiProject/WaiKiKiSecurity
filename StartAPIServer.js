var express = require('express');
var app = express();

var mysql_dbc = require("./db/ConnectServer")();
var connection = mysql_dbc.initialize();
mysql_dbc.databaseOpen(connection);

var router = require('./router/main')(app,connection);

var server = app.listen(7777, function(){
    console.log("Express server has started on port 7777")
});
