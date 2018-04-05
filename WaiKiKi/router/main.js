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
		var master = req.body.master;
		console.log(email);
		console.log(device_id);
		console.log(master);
		
		var dInstall = require("../api/DeviceInstallAPI");
	    dInstall.checkInstall(email,device_id,master,connection,callback(res));	
	 });

	 
     app.get('/install',function(req,res){
    	
     });
     
     app.post('/event',function(req,res){
     	var event_code = req.body.event_code;
     	var device_id = req.body.device_id;
     	console.log(event_code);
		console.log(device_id);
	
		var CreateEventAPI = require("../api/CreateEventAPI");
		CreateEventAPI.create(event_code, device_id,connection, callback(res));	
     });
     
     app.get('/',function(req,res){

    	 res.end('result OK');
     });
     
}