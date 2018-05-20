exports.userOut = function(email,password,connection,callback){
	
	console.log("start userOut");
	
	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({
		
		checkParameter : function(asyncCallback){		
			console.log("start userOutCheck method");
			if(email == null || password == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},

		UserOutCheck : function(asyncCallback){
			console.log("start userOutCheck method");
			
			var userOutstmt = "delete from user where email like ? and password like ?;";
			
			connection.query(userOutstmt,[email,password],function(err, result){
					if(err){
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						if(result != 0){
							callback.resultcallback(result_code.SuccessMessage,result_code.SuccessCode);
							asyncCallback(null);
						}else{
							callback.resultcallback(result_code.DissMatchLoginMessage,result_code.DissMatchLoginCode);
							asyncCallback(true);
						}
					}
			});
		}

	},
	
	asyncCallback = function(err){
		 if (err)
		        console.log('err');
		 else
		        console.log('done');
	});

}