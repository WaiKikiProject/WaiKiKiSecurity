exports.eventList = function(install_code,event_code,connection,callback){
	
	console.log("start eventListView");
	
	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({

		checkParameter : function(asyncCallback){		
			console.log("start checkParameter");
			if(install_code == null || event_code == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},
		
				
		pictureCheck : function(asyncCallback){
			console.log("start pictureCheck method");
			
			var confirmstmt = "select picture_url from picture where event_code like ?;";
			
			connection.query(confirmstmt ,[event_code],function(err, result){
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
		},
		
		confirmCheck : function(asyncCallback){
			console.log("start confirmCheck method");
			
			var confirmstmt = "update confirmevent set confirm_result='o' where install_code='?' and event_code = '?';";
			
			connection.query(confirmstmt ,[install_code,event_code],function(err, result){
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