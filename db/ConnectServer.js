/**
 * http://usejsdoc.org/
 **/

var mysql = require("mysql");
var config = require("./ServerConfig").local;

module.exports = function(){
	  return {
	    initialize: function () {
	      return mysql.createConnection({
	        host: config.host,
	        port: config.port,
	        user: config.user,
	        password: config.password,
	        database: config.database
	      })
	    },
	    
	    databaseOpen: function (con) {
	        con.connect(function (err) {
	          if (err) {
	            console.error('mysql connection error :' + err);
	          } else {
	            console.info('mysql is connected successfully.');
	          }
	        })
	      }
	    }
	  };