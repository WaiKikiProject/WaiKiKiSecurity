/**
 * http://usejsdoc.org/
 */

exports.convert = function(email,device_id,connection,callback){

	console.log("start ConvertAPI");

	var result_code = require("../conf/ResultCode");
	var async = require('async');
	var Firebase = require('../util/Firebase');

	var s_mode;

	async.series({

		checkParameter : function(asyncCallback){
			console.log("start checkParameter");
			if(device_id == null || email == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},

		findDevice : function(asyncCallback){

			console.log("start findDevice method");

			var findstmt = "select * from device where device_id like ?";
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

		installcheck : function(asyncCallback){
				console.log("start installcheck method");

				var findstmt = "select * from install where device_id like ? and email like ?";
				connection.query(findstmt,[device_id,email],function(err, result){
							if(err){
								callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
								asyncCallback(true);
							}else{
								if(result != 0 ){
								asyncCallback(null);
							}else{
								callback.resultcallback(result_code.NotInstallUserMessage,result_code.NotInstallUserCode);
								asyncCallback(true);
							}
						}
				});
		},

		  updateEvent: function(asyncCallback){

			  console.log("Start UpdateQuery");

			  if(s_mode == "O"){
				  console.log("Change Power-OFF S_MODE ");
				  s_mode = "X";
			  }else if(s_mode == "X"){
				  console.log("Change Power-ON S_MODE ");
				  s_mode = "O";
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
					var insertstmt = "insert into convertlog values(?,now(),?,?)";
					connection.query(insertstmt, [email,s_mode,device_id], function(err, result) {
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

			sendMessage : function(asyncCallback){
				 console.log("sendMessage");

				 var selectstmt = "select token from install join user using(email) where device_id like ?";
				 connection.query(selectstmt,[device_id],function(err,result){
					 if(err){
						 callback.resultcallback(result_code.SuccessMessage,result_code.SuccessCode);
						 asyncCallback(true);
					 }else{
						 if(result != 0){
							 for(var i in result){
								 if(s_mode == "O"){
										 Firebase.sendMessage(result[i].token,2);
								 }else if(s_mode == "X"){
									 Firebase.sendMessage(result[i].token,3);
								 }
							 }
 						   callback.resultcallback(result_code.SuccessMessage,result_code.SuccessCode);
							 asyncCallback(null);
						 }
						 else{
							 callback.resultcallback(result_code.SuccessMessage,result_code.SuccessCode);
							 asyncCallback(null);
						 }
					 }
				 });
			},
		},

	asyncCallback = function(err){

		 if (err)
		        console.log('err');
		 else
		        console.log('done');
	}
	);
}
