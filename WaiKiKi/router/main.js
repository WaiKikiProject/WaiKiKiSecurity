/**
 * http://usejsdoc.org/
 */


module.exports = function(app)
{
	 var result_code = require("../conf/ResultCode");
	 var bodyParser = require('body-parser');
	 
	 var mysql_dbc = require("../db/ConnectServer")();
	 var connection = mysql_dbc.initialize();
	 mysql_dbc.databaseOpen(connection);
	 
	 app.use(bodyParser.urlencoded({extended:true}));
	 app.use(bodyParser.json());
	
	 app.post('/device',function(req,res){
		 
		var statuscode;
		var resultmessage;
		var email = req.body.email;
		var device_id = req.body.device_id;
		var master = req.body.master;
		console.log(email);
		console.log(device_id);
		console.log(master)
		var dinstall = require("../api/DeviceInstallAPI");
		
	   statuscode = dinstall.checkinstall(email,device_id,master,connection,
	    	function(message, code){
	    		resultmessage = message;
	    		console.log(resultmessage);
	    		res.setHeader('Content-Type', 'application/json');
	    		res.statusCode = code;
	    	    res.json(resultmessage);
	    	});	
	 });

	 
     app.get('/device',function(req,res){
    	
     });
     
     app.get('/',function(req,res){
    	 
    	 res.end('result OK');
     });
     
}