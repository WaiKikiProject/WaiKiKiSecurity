
exports.getEventList = function(email,device_id,connection,callback){

	console.log("start MemberList");

	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({

		checkParameter : function(asyncCallback){
			console.log("start ParameterCheck method");
			if(device_id == null || email == null){
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

		findEvent :function(asyncCallback){

			console.log("start findMember method");

			  var findmemberstmt = "select * from event join confirmevent using(event_code) where device_id like ? and email like ?";
			  connection.query(findmemberstmt,[device_id,email],function(err, result){
					if(err){
						callback.resultcallback(result_code.DatabaseErrorMessage,result_code.DatabaseErrorCode);
						asyncCallback(true);
					}else{
						if(result != 0 ){
							callback.resultcallback(result,result_code.SuccessCode);
							asyncCallback(null);
						}else{
							console.log("Not Find Event");
							callback.resultcallback(result_code.NotExistEventMessage,result_code.NotExistEventCode);
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
