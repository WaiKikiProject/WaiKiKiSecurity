/**
 * http://usejsdoc.org/
 */


exports.SuccessCode = 200;
exports.SuccessMessage = {"message":"성공"}; 
	
exports.MissMatchParameterCode = 400;
exports.MissMatchParameterMessage = {"message":"잘못된 파라미터 값"}; 
	
exports.AleadyExistEMailCode = 401;
exports.AleadyExistEMailMessage = {"message":"존재하는 E-Mail"};

exports.NotExistEMailCode = 402;
exports.NotExistEMailMessage = {"message":"존재하지 않는 E-Mail"}; 
	
exports.NotMatchPWCode = 403;
exports.NotMatchPWMessage = {"message":"비밀번호 틀림"}; 

exports.NotExistDeivceCode = 405;
exports.NotExistDeivceMessage = {"message":"존재하지 않는 Device"}; 
	
exports.AleadyInstallDeviceCode = 410;
exports.AleadyInstallDeviceMessage = {"message":"이미 설치"};

exports.NotInstallMasterCode = 411;
exports.NotInstallMasterMessage = {"message":"Master가 없는 디바이스"};

exports.DatabaseErrorCode = 460;
exports.DatabaseErrorMessage  = {"message":"DB에러"};

exports.NotDistmatchErrorCode = 500;
exports.NotDistmatchErrorMessage  = {"message":"알수 없는 에러"};

	