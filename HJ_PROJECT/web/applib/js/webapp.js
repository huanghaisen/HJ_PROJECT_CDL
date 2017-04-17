var app = app || {};

app.PageHistory = [];
app.currentCompleted = false;//当前处理完成

app.goback = function(e){
  var that = this;
  var $wrap = $('.content-wrapper');
      var url=''; 
      
      if(that.currentCompleted==true){
      if(that.PageHistory.length>1){

        url = that.PageHistory[that.PageHistory.length-2];
        console.log("appjsGOback:"+url);
        that.loadHtml($wrap,url,function(){

            that.PageHistory.length = that.PageHistory.length-1;

        });

      }else{
        //切回首页  
        mainpageSwipeRight();
        mianwrapperSwipeRight();

      }
      }else{
    	  return
      }

}

app.pushPageHistory = function(o){ 
  var that = this; 
  if(o){    
    that.PageHistory.push(o);
  }
}

app.createGobackdata = function(){

}

app.clearPageHistory=function() {
  var that = this;
  that.PageHistory.length = 0;
}

app.loadHtml=function(target,url,callback) {
  var that = this;
  var $content = target;

  $content.empty().load(url,function(){
    callback();
    //console.log(app.PageHistory);    
  });

}

app.showMask = function(){  
  
}

app.hideMask = function(){  
  
}

app.init = function(){
  var that = this;

  //导航
  $(document).on('click','a[data-pannel-page]',function(){
      //todo
      var url = $(this).attr('data-pannel-page');
      var $wrap = $('.content-wrapper');
      that.clearPageHistory();

      that.loadHtml($wrap,url,function(){

          that.pushPageHistory(url);
          mianwrapperSwipeLeft();
          mainpageSwipeLeft();

      });
           
  });

  //列表加载新页面
  $(document).delegate('[data-pageview]','click',function(){
      
      var url = $(this).attr('data-pageview');
      var $wrap = $('.content-wrapper');

      that.loadHtml($wrap,url,function(){

        //$.ajax()
        that.pushPageHistory(url);
        //mainpageSwipeLeft();

      });
        
  });

  //page后退按钮点击事件
  $(document).delegate('.topnav-back','click',function(){
      //todo
      app.goback();      
            
  });

  //page导航righgt按钮 点击事件
  $(document).delegate('.topnav-check','click',function(){
      //todo
      app.goback();
            
  });

  //搜索展示
  $(document).delegate('.topnav-search-btn','click',function(){
      //todo
      $(this).parent().find('.box-tools').show();    
  }); 

}

//首页主导航内容框切换 
function showmainareaAnima(i){  
    $('#mianwrapper').show()
    .find('#mainarea')    
    .transition({
      marginLeft:-100*i+'%'      
    })
    .find('.maincontent')
    .eq(i)
    .addClass('active')
    .siblings()
    .removeClass('active');       
}

//首页主导航title切换
function showmainareaTitle(t){    
  $('#mtitle').html('<h4>'+t+'</h4>');
}

//首页主导航
function showthemnavCur(i){    
  $('#themnav').find('li')
  .eq(i)
  .addClass('cur')
  .siblings()
  .removeClass('cur');
}

function getthemnavIndex(){    
  var index = $('#themnav').find('.cur').index();
  return index;
}

function getthemnavTitle(i){    
  var title = $('#themnav').find('li').eq(i).find('p').html();
  return title;
}

function mianwrapperSwipeLeft(){
   $('#mianwrapper').show().transition({'marginLeft':'-100%'},function(){
    $(this).css({'marginLeft':0}).hide();   
   });  


}
function mianwrapperSwipeRight(){
   $('#mianwrapper').css({'marginLeft':'-100%'}).show().transition({'marginLeft':0});   
}

function mianwrapperShow(){
   $('#mianwrapper').show();   
}

function mianwrapperSetsize(){
  var wheight = $(window).height();
  $("#mianwrapper").css('height', wheight);
}


function setWinheight(){
  var window_height = $(window).height();
   $(".content-wrapper").css('height', window_height);
}

function mainpageSwipeLeft(){
   $('.mainpage').css({'marginLeft':'100%'}).transition({'marginLeft':0}).show();
}

function mainpageSwipeRight(){
   $('.mainpage').transition({'marginLeft':'100%'},function(){
    $(this).css({'marginLeft':0})
   });   
}

function topnavtitleSet(t){
  $('.topnavtitle').html(t);
}




$(function () {

  $("body").removeClass("hold-transition");
  
  setWinheight();  

  app.init();


});
