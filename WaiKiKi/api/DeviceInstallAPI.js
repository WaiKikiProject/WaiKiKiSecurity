/**
 * http://usejsdoc.org/
 */

exports.checkInstall = function(email,device_id,master,connection,callback){
	
	console.log("start checkinstall");
	
	var result_code = require("../conf/ResultCode");
	
	var async = require('async');	

	async.series({
		
		checkParameter : function(asyncCallback){		
			console.log("start installCheck method");
			if(email == null || device_id == null || master == null){
				console.log("MissmatchParameter");
				callback.resultcallback(result_code.MissMatchParameterMessage,result_code.MissMatchParameterCode);
				asyncCallback(true);
			}else{
				asyncCallback(null);
			}
		},
		findUser :function(asyncCallback){
			  
			console.log("start findUser method");
			  
			  var finduserstmt = "select * from member where e_mail like ?";
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
			  
			  var finddevicestmt = "select * from device where id like ?";
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
		  
		  checkAlreadyInstall : function(asyncCallback){
			  
				console.log("Start checkAlreadyInstall");
				  
				  var finduserstmt = "select * from install where e_mail like ? and id like ?";
				  connection.query(finduserstmt,[email,device_id],function(err, result){
						if(err){
							callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
							asyncCallback(true);
						}else{
							if(result != 0 ){
								console.log("Disable Install");	
								callback.resultcallback(result_code.AleadyInstallDeviceMessage,result_code.AleadyInstallDeviceCode);					
								asyncCallback(true);
							}else{
								console.log("Enable Install");		
								asyncCallback(null);
							}
						}
					});
			},
		  
			deviceInstall: function(asyncCallback){
				
				console.log("start deviceInstall method");
				
				console.log(email);
				console.log(device_id);
				console.log(master);
				
				var installstmt = "select * from install where id like ?";
				connection.query(installstmt,[device_id],function(err, result){
				if(err){
					callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
					asyncCallback(true);
				}else{
					console.log(result);
					if(result == 0 && master == "o"){
						console.log(1);
						asyncCallback(null);
					}else if(result == 0 && master == 'x'){
						console.log(2);
						callback.resultcallback(result_code.NotInstallMasterMessage,result_code.NotInstallMasterCode);
						asyncCallback(true);
					}else if(result != 0 && master == 'o'){
						console.log(3);
						callback.resultcallback(result_code.AleadyInstallMasterMessage,result_code.AleadyInstallMasterCode);
						asyncCallback(true);
					}else if(result != 0 && master == 'x'){
						console.log(4);
						asyncCallback(null);
					}else{
						console.log(5);
						callback.resultcallback(result_code.NotDistmatchErrorMessage,result_code.NotDistmatchErrorCode);
						asyncCallback(true);
					}
				}
			});
		},
		
		insertInstall: function(asyncCallback){
			
			console.log("Start InsertQuery");
			
			var insertstmt = "insert into install values(?,?,?)";
			connection.query(insertstmt, [email,device_id,master], function(err, result) {
				if(err){
					callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
					asyncCallback(true);
				}else{
					callback.resultcallback(result_code.SuccessMessage,result_code.SuccessCode);
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

