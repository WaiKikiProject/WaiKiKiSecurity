
exports.getEventList = function(email,connection,callback){

	console.log("start MemberList");

	var result_code = require("../conf/ResultCode");
	var async = require('async');

	async.series({

		checkParameter : function(asyncCallback){
			console.log("start ParameterCheck method");
			if(email == null){
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

		findEvent :function(asyncCallback){

			console.log("start findMember method");

			  var findmemberstmt = "select * from confirmevent where email like ?";
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
							callback.resultcallback(result,result_code.SuccessCode);
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
