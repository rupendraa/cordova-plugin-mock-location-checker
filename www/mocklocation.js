// Empty constructor
function MockLocationChecker() {}

// The function that passes work along to native shells
MockLocationChecker.prototype.check = function(successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, 'MockLocationChecker', 'check', []);
}

// Installation constructor that binds MockLocationChecker to window
MockLocationChecker.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.mocklocationchecker = new MockLocationChecker();
  return window.plugins.mocklocationchecker;
};
cordova.addConstructor(MockLocationChecker.install);
