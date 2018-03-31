/**
 * http://usejsdoc.org/
 */

exports.checkinstall = function(email,deviceID,master,connection,callback){
	
	var result_code = require("../conf/ResultCode");
	var FindEntity = require("../db/FindEntity")(connection);
	
	if(email == null || deviceID == null || master == null){
		console.log("MissmatchParameter");
		callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
		return;
	}
		
	if(FindEntity.findUser(email),callback){
		callback.resultcallback(result_code.NotExistEMailMessage,result_code.NotExistEMailCode);
		return;
	}else if(!FindEntity.findDevice(deviceID),callback){
		callback.resultcallback(result_code.NotExistDeivceMessage,result_code.NotExistDeivceCode);
		return;
	}

	var installstmt = "select * from install where id like ?";
	connection.query(installstmt,[deviceID],function(err, result){
		if(err){
	
		}else{
			console.log(result);
			if(result == 0 && master == "o"){
				console.log(1);
				callback.resultcallback(result_code.SuccessMessage,result_code.SuccessCode);
			}else if(result == 0 && master == 'x'){
				console.log(2);
				callback.resultcallback(result_code.NotInstallMasterMessage,result_code.NotInstallMasterCode);
			}else if(result != 0 && master == 'o'){
				console.log(3);
				callback.resultcallback(result_code.AleadyInstallDeviceMessage,result_code.AleadyInstallDeviceCode);
			}else if(result != 0 && master == 'x'){
				console.log(4);
				callback.resultcallback(result_code.SuccessMessage,result_code.SuccessCode);
			}else{
				console.log(5);
				callback.resultcallback(result_code.NotDistmatchErrorMessage,result_code.NotDistmatchErrorCode);
			}
		}
	});
}

