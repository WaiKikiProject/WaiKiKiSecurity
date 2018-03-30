/**
 * http://usejsdoc.org/
 */


exports.SuccessCode = 200;
exports.SuccessMessage = {"message":"성공"}; 
	
exports.MissMatchParameter = 400;
exports.MissMatchParameterMessage = {"message":"잘못된 파라미터 값"}; 
	
exports.AleadyExistEMail = 401;
exports.AleadyExistEMailMessage = {"message":"존재하는 E-Mail"}; 
	
exports.NotMatchPW = 402;
exports.NotMatchPWMessage = {"message":"비밀번호 틀림"}; 
	
exports.AleadyInstallDevice = 410;
exports.AleadyInstallDeviceMessage = {"message":"이미 설치"};

exports.NotInstallMaster = 411;
exports.NotInstallMasterMessage = {"message":"Master가 없는 디바이스"};

exports.DatabaseError = 460;
exports.DatabaseErrorMessage  = {"message":"DB에러"};

exports.NotDistmatchError = 500;
exports.NotDistmatchErrorMessage  = {"message":"알수 없는 에러"};

	