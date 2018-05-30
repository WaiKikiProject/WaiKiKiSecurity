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

exports.DissMatchLoginCode = 403;
exports.DissMatchLoginMessage = {"message":"사용자 정보가 틀림"};

exports.NotMatchNameCode = 404;
exports.NotMatchNameMessage = {"message":"존재하지 않는 이름"};

exports.NotExistDeivceCode = 405;
exports.NotExistDeivceMessage = {"message":"존재하지 않는 Device"};

exports.NotExistUserCode = 406;
exports.NotExistUserMessage = {"message":"설치된 유저 없음"};

exports.NotInstallUserCode = 408;
exports.NotInstallUserMessage = {"message":"장치에 설치되지 않음"};

exports.AleadyInstallDeviceCode = 409;
exports.AleadyInstallDeviceMessage = {"message":"이미 장치에 설치됌"};

exports.AleadyInstallMasterCode = 410;
exports.AleadyInstallMasterMessage = {"message":"이미 마스터가 설치됌"};

exports.NotInstallMasterCode = 411;
exports.NotInstallMasterMessage = {"message":"Master가 없는 디바이스"};

exports.NotExistEventCode = 450;
exports.NotExistEventMessage = {"message":"이벤트 없음"};

exports.NotExistEventCode = 4;
exports.NotExistEventMessage = {"message":"이벤트 없음"};



exports.DatabaseErrorCode = 460;
exports.DatabaseErrorMessage  = {"message":"DB에러"};

exports.DatabaseOverlapCode = 470;
exports.DatabaseOverlapMessage = {"message":"이메일이 중복되었습니다."};

exports.NotDistmatchErrorCode = 500;
exports.NotDistmatchErrorMessage  = {"message":"알수 없는 에러"};
