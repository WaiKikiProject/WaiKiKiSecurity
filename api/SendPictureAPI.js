exports.checkPicture = function(event_code,connection,callback){
	
	console.log("start picture");
	
	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({
		
		checkParameter : function(asyncCallback){		
			console.log("start checkParameter method");
			if(event_code == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},

		pictureCheck : function(asyncCallback){
			console.log("start pictureCheck method");
			
			var picturestmt = "insert into picture values(?,?)";
			
			connection.query(picturestmt,[event_code,'x'],function(err, result){
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