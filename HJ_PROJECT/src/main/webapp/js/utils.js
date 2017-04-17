
/**
 * 工具类
 *
 */

//内部请求
function ajax(url,type,callback){
	$.ajax({
		type: "POST",
		dataType: type,
		url: contextPath+url,
		success: function(obj) {
			callback(obj);
		}
	});
}

//外部地址数据请求
function postjson(url,type,data,callback){
	$.ajax({
		type: "POST",
		dataType: type,
		data:data,
		url: url,
		success: callback
	});
}

//内部请求带参数
function post(url,type,data,callback){
	$.ajax({
		type: "POST",
		dataType: type,
		data:data,
		url: contextPath+url,
		success: callback
	});
}


//懒加载JS
function getScript(url,callback){
	$.getScript(contextPath+url).done(callback);
}

//打开新页面
function openwindows(url,title){
	window.open (contextPath+url, title, 'height=768px, width=1024px, scrollbars=yes, resizable=yes');
}

//截取字符长度
function strSub(data,num){
   var value = "";
   if(data.length>num){
	   value=data.substring(0,num)+"..";
   }else{
	   value=data;
   }
   return value;
}
