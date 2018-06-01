/**
 * http://usejsdoc.org/
 */

exports.Install = function(email,device_id,connection,callback){

	console.log("start install");

	var result_code = require("../conf/ResultCode");

	var async = require('async');

	async.series({

		checkParameter : function(asyncCallback){
			console.log("start installCheck method");
			if(email == null || device_id == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},

		findUser :function(asyncCallback){

			console.log("start findUser method");

			  var finduserstmt = "select * from user where email like ?";
			  connection.query(finduserstmt,[email],function(err, result){
					if(err){
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						if(result != 0 ){
							console.log("Find User");
							asyncCallback(null);
						}else{
							console.log("Not Find User");
							callback.resultcallback(result_code.NotExistEMailMessage,result_code.NotExistEMailCode);
							asyncCallback(true);
						}
					}
				});
		},

		 findDevice: function(asyncCallback,result){

			  console.log("start findDevice method");

			  var finddevicestmt = "select * from device where device_id like ?";
			  connection.query(finddevicestmt,[device_id],function(err, result){
					if(err){
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						if(result != 0 ){
							asyncCallback(null);
						}else{
							callback.resultcallback(result_code.NotExistDeivceMessage,result_code.NotExistDeivceCode);
							asyncCallback(true);
						}
					}
				});
		 	},


			insertInstall: function(asyncCallback){

				console.log("Start InsertQuery");

				var date = new Date();

				var insertstmt = "insert into install values(?,?)";
				connection.query(insertstmt, [email,device_id], function(err, result) {
					if(err){
						console(err)
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						asyncCallback(null);
					}
				})
			},

			resultJson : function(asyncCallback){

					console.log("Start resultJson");

					var selectstmt = "select device_id,master,device_name,s_mode from device join install using(device_id) where email like ? and device_id like ?";
					connection.query(selectstmt,[email,device_id],function(err,result){
					if(err){
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						if(result != 0){
							callback.resultcallback(JSON.stringify(result),result_code.SuccessCode);
							asyncCallback(true);
						}
						else{
							callback.resultcallback(result_code.NotDistmatchErrorMessage,result_code.NotDistmatchErrorCode);
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