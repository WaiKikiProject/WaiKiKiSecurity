/**
 * http://usejsdoc.org/
 */

var mysql_dbc = require("../db/ConnectServer")();
var connection = mysql_dbc.initialize();
mysql_dbc.test_open(connection);

exports.checkinstall = function(email,deviceID){
	
	var result_code = require("./conf/ResultCode");
	
	var stmt = 'SELECT * FROM INSTALL';
	connection.query(stmt,function(err, result,fields){
		if (err) throw err;
		console.log(result);
	});
	connection.end();
	
	return result_code.SuccessCode;
}

