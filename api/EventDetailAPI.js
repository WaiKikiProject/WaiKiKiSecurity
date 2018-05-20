exports.eventLookup = function(connection,callback){
	
	console.log("start eventDetail");
	
	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({
		
		EventLookUp : function(asyncCallback){
			console.log("start eventDetail method");
			
			var eventLookupstmt = "select event_code,event_date from event,install where event.device_id = install.device_id;";
			
			connection.query(eventLookupstmt ,function(err, result){
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