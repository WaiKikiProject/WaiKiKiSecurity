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

	 app.post('/convert',function(req,res){
     	var device_id = req.body.device_id;
			var email = req.body.email;
      console.log(device_id);

 			var ConvertSequrityAPI = require("../api/ConvertSequrityAPI");
 			ConvertSequrityAPI.convert(email,device_id,connection, callback(res));
    });

	 app.post('/event',function(req,res){
    	var device_id = req.body.device_id;
		console.log(device_id);

		var CreateEventAPI = require("../api/CreateEventAPI");
		CreateEventAPI.create(device_id,connection, callback(res));
     });

	 app.post('/deviceinstall',function(req,res){

		var email = req.body.email;
		var device_id = req.body.device_id;
		var device_name = req.body.device_name;
		console.log(email);
		console.log(device_id);
		console.log(device_name);

		var dInstall = require("../api/DeviceInstallAPI");
	    dInstall.checkInstall(email,device_id,device_name,connection,callback(res));
	 });

	 app.post('/token',function(req,res){
		 var email = req.body.email;
		 var token = req.body.token;
		 console.log(email);
		 console.log(token);

		 var cheekToken = require("../api/DeviceTokenAPI");
		 cheekToken.devicetoken(email,token,connection,callback(res));
     });

	 app.get('/eventdetail',function(req,res){

		 var cheekLookup = require("../api/EventDetailAPI");
		 cheekLookup.eventLookup(connection,callback(res));
     });

		 app.get('/installlist/:email',function(req,res){
	 	 	 var email = req.params.email;
			 var Installlist = require("../api/InstallListAPI");
			 Installlist.getInstallList(email,connection,callback(res));
		});

		app.get('/memberlist/:device_id',function(req,res){
			var device_id = req.params.device_id;
			var MemberList = require("../api/MemberListAPI");
			MemberList.getMemberList(device_id,connection,callback(res));
	 });

	 app.get('/eventlist/:email/:device_id',function(req,res){
		 var device_id = req.params.device_id;
		 var email = req.params.email;
		 var EventList = require("../api/EventListAPI");
		 EventList.getEventList(email,device_id,connection,callback(res));
	});

	 app.post('/eventlistview',function(req,res){

		var install_code = req.query.install_code;
		var event_code = req.query.event_code;
		console.log(install_code);
		console.log(event_code);

		 var cheeklistview = require("../api/EventListViewAPI");
		 cheeklistview.eventList(install_code,event_code,connection,callback(res));
     });

	 app.post('/install',function(req,res){

		var email = req.body.email;
		var device_id = req.body.device_id;
		console.log(email);
		console.log(device_id);

		 var checkinstall = require("../api/InstallAPI");
		 checkinstall.Install(email,device_id,connection,callback(res));
     });

		 app.post('/invite',function(req,res){

			var email = req.body.email;
			var device_id = req.body.device_id;
			console.log(email);
			console.log(device_id);

			 var Invite = require("../api/InviteAPI");
			 Invite.invite(email,device_id,connection,callback(res));
	     });


     app.post('/login',function(req,res){
    	 var email = req.body.email;
		 var password = req.body.password;
		 console.log(email);
		 console.log(password);

		 var login = require("../api/LoginAPI");
		 login.checkLogin(email,password,connection,callback(res));
     });

	 app.delete('/logout',function(req,res){
		 var email = req.body.email;
		 console.log(email);

		 var logout = require("../api/LogOutAPI");
		 logout.userOut(email,connection,callback(res));
     });

     app.post('/picture',function(req,res){
		 var fs = require('fs');

		 var event_code = req.body.event_code;
		 console.log(event_code);

		 var pic = require("../api/PictureAPI");
		 pic.checkPicture(event_code,connection,callback(res));
     });

	 app.post('/signup',function(req,res){
    	 var email = req.body.email;
		 var name = req.body.name;
		 var password = req.body.password;
		 console.log(email);
		 console.log(name);
		 console.log(password);

		 var signup = require("../api/SignUpAPI");
		 signup.checkSignup(email,name,password,connection,callback(res));
     });

}
