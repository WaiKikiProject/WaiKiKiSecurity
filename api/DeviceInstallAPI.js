/**
 * http://usejsdoc.org/
 */

exports.checkInstall = function(email,device_id,device_name,connection,callback){

	console.log("start deviceinstall");

	var result_code = require("../conf/ResultCode");

	var async = require('async');

	async.series({

		checkParameter : function(asyncCallback){
			console.log("start installCheck method");
			if(email == null || device_id == null || device_name == null){
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

			  var finddevicestmt = "select * from install where device_id like ?";
			  connection.query(finddevicestmt,[device_id],function(err, result){
					if(err){
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						if(result != 0 ){
							callback.resultcallback(result_code.AleadyInstallDeviceMessage,result_code.AleadyInstallDeviceCode);
							asyncCallback(true);
						}else{
							asyncCallback(null);
						}
					}
				});
		 	},


			insertInstall: function(asyncCallback){

				console.log("Start InsertQuery");

				var date = new Date();

				var insertstmt = "insert into device values(?,?,?,?)";
				connection.query(insertstmt, [device_id,device_name,"X",email], function(err, result) {
					if(err){
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						console.log("require InstallAPI");
						var InstallAPI = require('../api/InstallAPI');
						InstallAPI.Install(email,device_id,connection,callback);
						asyncCallback(null);
					}
				})
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
