
var timer;
var settime;
$(function(){
	soaIndexfn(0);
});

//添加首页地图监控
function soaIndexfn(obj){
	if(obj!=0){
		setWebnav(["系统首页","面板"]);
	}
	ajax(mainurl,"html",function(obj){
		$(".content").html(obj);
	});
}