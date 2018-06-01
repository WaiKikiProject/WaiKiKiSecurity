
exports.getMemberList = function(device_id,connection,callback){

	console.log("start MemberList");

	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({

		checkParameter : function(asyncCallback){
			console.log("start ParameterCheck method");
			if(device_id == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},

		findMember :function(asyncCallback){

			console.log("start findMember method");

			  var findmemberstmt = "select * from install join user using(email) where device_id like ?";
			  connection.query(findmemberstmt,[device_id],function(err, result){
					if(err){
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						if(result != 0 ){
							callback.resultcallback(result,result_code.SuccessCode);
							asyncCallback(null);
						}else{
							console.log("Not Find Install");
							callback.resultcallback(result,result_code.SuccessCode);
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
	}
	);


}
