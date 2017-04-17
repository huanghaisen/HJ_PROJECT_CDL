

$(function(){ 
    //FastClick.attach(document.body);
  //点击主导航条切换导航
  $('#themnav').find('li').on('click',function(){
    var index = $(this).index();
    var title = $(this).find('p').html();
    showmainareaAnima(index);
    showmainareaTitle(title);
    $(this).addClass('cur').siblings().removeClass('cur');
  });

  //向左滑动切换主导航
  $('#mianwrapper').hammer().bind('swipeleft',function(e){
    var index = getthemnavIndex();
    var nsize = $('#themnav').find('li').length-1;
    index++;
    if(index<=nsize){
      var title = getthemnavTitle(index);
      showmainareaAnima(index);
      showthemnavCur(index);
      showmainareaTitle(title);
    }
    
  });

  //向右滑动切换主导航
  $('#mianwrapper').hammer().bind('swiperight',function(e){
    var index = getthemnavIndex();    
    index--;
    if(index>=0){
      var title = getthemnavTitle(index);
      showmainareaAnima(index);
      showthemnavCur(index);
      showmainareaTitle(title);
    }
    
  });

  mianwrapperShow();

  //主导航内容

  // $('a[data-ajax-page]').on('click',function(){
  //     var url = $(this).attr('data-ajax-page');
  //     $('.content-wrapper').load(url,function(){
  //       setTimeout(function(){
  //         mianwrapperSwipeLeft();
  //         mainpageSwipeLeft();
  //       },50);
        
  //     });
  // });

  $('#exitsys').on('click',function(){
	var d = dialog({
      title:'提示',
      content:'你确定要退出系统吗？',
      okValue:'确认',
      ok:function(){
      	$.post(contextPath+"/logout", function(data) {
      		location.reload();
      		});
      },
      cancelValue:'取消',
      cancel:function(){}
		});
		d.showModal(); 
  });
});



