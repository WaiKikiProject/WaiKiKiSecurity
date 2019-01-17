var StatusType
	{
		EVENT = 1,
 		SEQURITY = 2,
		UNSEQURITY = 3,
		INVITE = 4,
		APPROVE = 5,
		INVITECOMPLETE = 6
	};
exports.StatusType;

exports.sendMessage = function(token,type){
	var PushMessage = require('../conf/PushMessage');
	var FCM = require('fcm-push');

	var serverKey = 'AAAAae8MGHw:APA91bG7FX8ftAVRnlGEunyHPOhJKXlZnCMfIULcsw-NdYzoe6aQXHltPpBrHdqDZRtGe19-wIBqoWBXG9IFQ6C5xOeskc-s16c6JluaJZqVILbyKRnizl8mFwawr69YtK7opwTecY4X';

	var fcm = new FCM(serverKey);

	var body_message;

	switch(type){
		case EVENT:
			body_message = PushMessage.EVENT_MESSAGE;
			break;
		case SEQURITY:
			body_message = PushMessage.SCURITY_MESSAGE;
			break;
		case UNSEQURITY:
			body_message = PushMessage.UNSCURITY_MESSAGE;
			break;
		case INVITE:
			body_message = PushMessage.INVITE_MESSAGE;
			break;
		case APPROVE:
			body_message = PushMessage.APPROVE_MESSAGE;
			break;
		case INVITECOMPLETE:
			body_message = PushMessage.INVITECOMPLETE_MESSAGE;
			break;
		};

		var message = {
		to: token, // required fill with device token or topics
		collapse_key: '',
		android: {
			 notification: {
				 bodyLocKey: "test"
			 }
		 },
		data: {
			title: PushMessage.APP_NAME,
			message: body_message
		},
		notification: {
			title: PushMessage.APP_NAME,
			body: body_message
		}
		};

		//callback style
		fcm.send(message, function(err, response){
			if (err) {
				console.log("Something has gone wrong! :"+ err);
			} else {
				console.log("Successfully sent with response: ", response);
			}
		});

}
