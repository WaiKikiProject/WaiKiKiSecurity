/**
 * http://usejsdoc.org/
 */

exports.convert = function(device_id,install_code,connection,callback){
	
	console.log("start ConvertAPI");
	
	var result_code = require("../conf/ResultCode");
	var async = require('async');
	
	var s_mode;

	async.series({
		
		checkParameter : function(asyncCallback){		
			console.log("start checkParameter");
			if(device_id == null || install_code == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},
		
		findDevice : function(asyncCallback){
			  
			console.log("start findDevice method");
			  
			var findstmt = "select device_id,s_mode from device where device_id like ?";
			connection.query(findstmt,[device_id],function(err, result){
				if(err){
					callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
					asyncCallback(true);
				}else{
					if(result != 0 ){
						s_mode = result[0].s_mode;
						console.log(s_mode);
						asyncCallback(null);
					}else{
						callback.resultcallback(result_code.NotExistDeivceMessage,result_code.NotExistDeivceCode);
						asyncCallback(true);
					}
				}
			});
		  },
		  
		  updateEvent: function(asyncCallback){
			
			  console.log("Start UpdateQuery");
			  
			  if(s_mode == "o"){
				  console.log("Change Power-OFF S_MODE ");
				  s_mode = "x";
			  }else if(s_mode == "x"){
				  console.log("Change Power-ON S_MODE ");
				  s_mode = "o";
			  }else{
				  console.log("Not Find S_MODE ");
				  callback.resultcallback(result_code.NotFindSMODEMessage,result_code.NotFindSMODECode);
				  asyncCallback(true);	  
			  }
			  var updatestmt = "update device set s_mode=? where device_id like ?";
			  
			  console.log("update check");
			  
			  connection.query(updatestmt, [s_mode,device_id], function(err, result) {
				  if(err){
					  connection.rollback(function () {
						  	callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
							asyncCallback(true);
	                     });
				  }else{
					var insertstmt = "insert into convertlog values(?,now(),?)";
					connection.query(insertstmt, [install_code,s_mode], function(err, result) {
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