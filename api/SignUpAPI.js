exports.checkSignup = function(email,name,password,connection,callback){
	
	console.log("start checkSignup");
	
	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({
		
		checkParameter : function(asyncCallback){		
			console.log("start checkParameter");
			if(email == null || name == null || password == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},
		
		insertUserInform : function(asyncCallback){		
			console.log("start insertUserInform method");
			
			var findEmailstmt = "insert into user values (?,?,?)";
			
			connection.query(findEmailstmt,[email,name,password],function(err, result){
				if(err){
						callback.resultcallback(result_code.DatabaseOverlapMessage,result_code.DatabaseOverlapCode);
						console.log("이메일이 중복되었습니다.");
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