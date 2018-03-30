/**
 * http://usejsdoc.org/
 */

var mysql_dbc = require("../db/ConnectServer")();
var connection = mysql_dbc.initialize();



exports.checkinstall = function(email,deviceID,master,connection,callback){
	
	var result_code = require("../conf/ResultCode");
	
	if(email == null || deviceID == null || master == null){
		callback(result_code.MisMatchParameterMessage,reuslut_code.MisMatchParameter);
		return;
	}

	var stmt = "select * from install where id like ?";
	connection.query(stmt,[deviceID],function(err, result){
		if(err){
			console.log(err);
			callback(result_code.DatabaseErrorMessage,reuslut_code.DatabaseError);
		}else{
			console.log(result);
			if(result == 0 && master == "o"){
				console.log(1);
				callback(result_code.SuccessMessage,result_code.SuccessCode);
			}else if(result == 0 && master == 'x'){
				console.log(2);
				callback(result_code.NotInstallMasterMessage,result_code.NotInstall);
			}else if(result != 0 && master == 'o'){
				console.log(3);
				callback(result_code.AleadyInstallDeviceMessage,result_code.AleadyInstallDevice);
			}else if(result != 0 && master == 'x'){
				console.log(4);
				callback(result_code.SuccessMessage,result_code.SuccessCode);
			}else{
				console.log(5);
				callback(result_code.NotDistmatchErrorMessage,result_code.NotDistmatchError);
			}
		}
	});
}

