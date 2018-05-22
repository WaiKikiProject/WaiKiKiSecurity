/**
 * http://usejsdoc.org/
 */


module.exports = (function () {
  return {
    local: { // localhost
      host: 'localhost',
      port: '3306',
      user: 'root',
      password: 'password',
      database: 'waikiki'
    },
    real: { // real server db info
      host: '13.209.49.159',
      port: '3306',
      user: 'root',
      password: 'password',
      database: 'waikiki'
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
