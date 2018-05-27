/**
 * http://usejsdoc.org/
 */


module.exports = (function () {
  return {
    local: { // localhost
      host: 'localhost',
      port: '3306',
      user: 'root',
      password: '1234',
      database: 'waikiki'
    },
    real: { // real server db info
      host: '',
      port: '',
      user: '',
      password: '',
      database: ''
    },
    dev: { // dev server db info
      host: '',
      port: '',
      user: '',
      password: '',
      database: ''
    }
  }
})();
