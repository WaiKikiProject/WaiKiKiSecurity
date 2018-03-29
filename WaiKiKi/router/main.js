/**
 * http://usejsdoc.org/
 */


module.exports = function(app)
{
	 app.post('/device',function(req,res){
		var dinstall = require("../api/DeviceInstallAPI");
		var email = req.params.email;
		var device_id = req.params.device_id;
    	res.setHeader('Content-Type', 'application/json');
    	if(email != null && device_id != null){
    		res.statusCode = dinstall.checkinstall(email,device_id);
    	}
    	res.end('result OK');
	 });

	 
     app.get('/device',function(req,res){
    	
     });
     
}