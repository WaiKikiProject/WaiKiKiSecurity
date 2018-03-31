/**
 * http://usejsdoc.org/
 */


module.exports = function(connection){
	  return {
		  
		  findUser: function(email,callback){
			  var finduserstmt = "select * from member where e_mail like ?";
			  connection.query(finduserstmt,[email],function(err, result){
					if(err){
						
					}else{
						if(result != 0 ){
							console.log("Find User");
							return true;
						}else{
							console.log("Not Find User");
							return false;
						}
					}
				});
		  },
		  
		  findDevice: function(device_id,callback){
			  var finddevicetmt = "select * from device where id like ?";
			  connection.query(finddevicetmt,[device_id],function(err, result){
					if(err){
					
					}else{
						if(result != 0 ){
							return true;
						}else{
							return false;
						}
					}
				});
		  }
		  
	  }
}