/**
 * http://usejsdoc.org/
 */

exports.create = function(device_id,event_code,connection,callback){
	
	console.log("start CreateEventAPI");
	
	var device_id;
	
	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({
		
		checkParameter : function(asyncCallback){		
			console.log("start checkParameter");
			if(event_code == null || device_id == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},

		  insertEvent: function(asyncCallback){
			
			  console.log("Start InsertQuery");
			
			  var insertstmt = "insert into event values(?,now(),?)";
			  connection.query(insertstmt, [event_code,device_id], function(err, result) {
				  if(err){
					connection.rollback(function () {
					  	callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
                     });
				  }else{
					  var insertstmt = "insert into confirmevent values(?,?,'x')";
					  connection.query(insertstmt, [email,event_code], function(err, result) {
						  if(err){
							  connection.rollback(function () {
								  	callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
									asyncCallback(true);
			                     });
						  }else{
							asyncCallback(null);
						  }
					  });
				  }
			  });
		  },
		  
		  resultJson : function(asyncCallback){
			  
			console.log("Start resultJson");
			
			var selectstmt = "select * from event where event_code like ?";
			connection.query(selectstmt,[event_code],function(err,result){
				if(err){
					callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
					asyncCallback(true);
				}else{
					if(result != 0){
						callback.resultcallback(JSON.stringify(result),result_code.SuccessCode);
						asyncCallback(null);
					}
					else{
						callback.resultcallback(result_code.NotDistmatchErrorMessage,result_code.NotDistmatchErrorCode);
						asyncCallback(null);
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