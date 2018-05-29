exports.userOut = function(email,connection,callback){
	
	console.log("start logout");
	
	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({
		
		checkParameter : function(asyncCallback){
			console.log("start checkToken method");
			if(email == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},
		
		deleteToken: function(asyncCallback){

				console.log("Start DeleteQuery");

				var insertstmt = "update user set token=NULL where email like ?";
				connection.query(insertstmt, [email], function(err, result) {
					if(err){
						callback.resultcallback(result_code.DatabaseOverlapMessage,result_code.DatabaseOverlapCode);
						console.log("이메일이 틀렸어요.");
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
				})
		}

	},
	
	asyncCallback = function(err){
		 if (err)
		        console.log('err');
		 else
		        console.log('done');
	});

}