/**
 * http://usejsdoc.org/
 */


module.exports = function(app,connection)
{
	 var result_code = require("../conf/ResultCode");
	 var bodyParser = require('body-parser');
	 app.use(bodyParser.urlencoded({extended:true}));
	 app.use(bodyParser.json());
	 
	 var statuscode;
	 var resultmessage;
	 
	 var callback = function(res){
		return {
			resultcallback : function(message, code){
			 	resultmessage = message;
			 	console.log(resultmessage);
			 	res.setHeader('Content-Type', 'application/json');
			 	res.statusCode = code;
			 	res.json(resultmessage);
			 }
		}
	}
	
	 app.post('/install',function(req,res){
		 
		var email = req.body.email;
		var device_id = req.body.device_id;
		var device_name = req.body.device_name;
		console.log(email);
		console.log(device_id);
		console.log(device_name);
		
		var dInstall = require("../api/DeviceInstallAPI");
	    dInstall.checkInstall(email,device_id,device_name,connection,callback(res));	
	 });

	 
     app.get('/install',function(req,res){
    	
     });
     
     app.post('/event',function(req,res){
    	var email = req.body.email;
    	var device_id = req.body.device_id;
     	var event_code = req.body.event_code;
		console.log(event_code);
	
		var CreateEventAPI = require("../api/CreateEventAPI");
		CreateEventAPI.create(email,device_id,event_code,connection, callback(res));	
     });
     
     app.get('/event/:email/:device_id',function(req,res){
      	var email = req.body.email;
      	var device_id = req.body.device_id;
      	console.log(email);
      	console.log(device_id);

 		var CheckEventAPI = require("../api/GetEventAPI");
 		CheckEventAPI.chek(event_code,connection, callback(res));	
      });
     
     app.post('/convert',function(req,res){
     	var device_id = req.body.device_id;
      	var install_code = req.body.install_code;
      	console.log(device_id);
      	console.log(install_code);
 	
 		var ConvertSequrityAPI = require("../api/ConvertSequrityAPI");
 		ConvertSequrityAPI.convert(device_id,install_code,connection, callback(res));
      });
     
     app.get('/',function(req,res){

    	 res.end('result OK');
     });
     
}