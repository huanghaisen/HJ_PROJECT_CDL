var pixelRatio = devicePixelRatio || webkitDevicePixelRatio || mozDevicePixelRatio || 1;
var browser = {
  versions: function() {
    var u = navigator.userAgent,
      app = navigator.appVersion;
    return { //移动终端浏览器版本信息
      trident: u.indexOf('Trident') > -1, //IE内核
      presto: u.indexOf('Presto') > -1, //opera内核
      webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
      gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
      mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
      ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
      android: u.indexOf('Android') > -1, //android终端
      iPhone: u.indexOf('iPhone') > -1, //是否为iPhone
      iPad: u.indexOf('iPad') > -1 //是否iPad
    };
  }(),
  language: (navigator.browserLanguage || navigator.language).toLowerCase()
}


;(function(doc, win) {
  var screenWidth = 0,
    size = 'M',
    root = doc.documentElement;
  if (window.screen && screen.width) {
    screenWidth = screen.width;

    if (screenWidth > 1920) {
      size = 'L';
    } else if (screenWidth < 480) {
      size = 'S';
    }
  }

  root.className = size;

  win.SIZE = size;

})(document, window);