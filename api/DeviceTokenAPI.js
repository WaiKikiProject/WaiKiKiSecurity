exports.devicetoken = function(email,token,connection,callback){
	
	console.log("start deviceToken");
	
	var result_code = require("../conf/ResultCode");
	var async = require('async');
	
	async.series({
		
		checkParameter : function(asyncCallback){
			console.log("start checkToken method");
			if(email == null || token == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},
		
		insertInstall: function(asyncCallback){

				console.log("Start InsertQuery");

				var date = new Date();

				var insertstmt = "update user set token=? where email like ?";
				connection.query(insertstmt, [token,email], function(err, result) {
					if(err){
						console(err)
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						asyncCallback(null);
					}
				})
		},

	},
	
	asyncCallback = function(err){
		 if (err)
		        console.log('err');
		 else
		        console.log('done');
	});

}