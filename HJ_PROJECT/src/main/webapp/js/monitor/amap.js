
/**
 * 地图控件使用
 */

var Kxdamap = function(options){
	this.zoom=10,//放大
	this.zooms=[6,20],
	this.lang="zh",
	this.mapStyle="normal",
	this.features=["bg","point","road","building"],
	this.isHotspot=true,
	this.mapmarker=null,//记录不同的地图标记
    this.option = options;
    this.map=null;
    this.marker=AMap.Marker;//标记
    this.icon=AMap.Icon;//图标
    this.infoWindow=AMap.InfoWindow;//窗体
    this.toolBar=AMap.ToolBar;
    this.mouseTool = AMap.MouseTool;
    this.contextMenuPositon=null;
    this.city=this.option.city;
    this.orgid=this.option.orgid;
    //获取绑定控件
    if(this.option.mainChartDiv != null ){
        this.mainChartDiv = this.option.mainChartDiv;
    }
    if(this.option.center != null){
    	this.center = this.option.center;
    }
}
Kxdamap.prototype={
	    //开始
	    start:function(){
	        this.map=new AMap.Map(this.mainChartDiv,{
	            zoom: this.zoom,
	            zooms: this.zooms,
	            lang: this.lang,
	            mapStyle:this.mapStyle,
	            features:this.features,
	            isHotspot:this.isHotspot
	        });
	        this.map.clearMap();
	        this.map.setCity(this.city);//坐标定位
	    },
	    //加载
	    onLoad:function(){
	        var _this = this;
	        _this.start();
	        this.URL = this.option['url'];//获取数据地址
	        this.ajaxRequest({
	            url:this.URL+"?orgid="+_this.orgid,
	            callback:function(e){
	                _this.dataAssembly(e);
	            }
	        });
	    },
	    //首次数据包装
	    dataAssembly:function(o){
	        var _this=this;
	        var code = typeof o == 'string' ? JSON.decode( o ).data : o.data ;
	        for(var i=0;i<code.length;i++) {
	        	console.log("----------"+code[i].mapY+","+ code[i].mapX+","+code[i].orgName+","+code[i].id);
	            this.initMarker(code[i].mapY, code[i].mapX,code[i].orgName,code[i].id);
	        }
	    },
	    //处理设备获取
	    deviceRequest:function(oid){
	        var _this=this;
	        console.log(oid);
	        $("#drvice_panel").html("").hide();
	        var url="/webtrade?executor=trade&tradedriver=ims&cmd=query_devicecache&id="+oid;
	        this.ajaxRequest({
	            url:url,
	            callback:function(o){
	                var code = typeof o == 'string' ? JSON.decode( o ) : o ;
	                var drvices_html="";
	                if(code.device_count>0) {
	                    var offline=0;
	                    var online=0;
	                    var exd=0;
	                    for (var i = 0; i < code.device_count; i++) {
	                        if(code.device_items[i].device_mapping==0){
	                            offline++;
	                        }else{
	                            if(code.device_items[i].device_status==2){
	                                exd++;
	                            }else if(code.device_items[i].device_status==0){
	                                offline++;
	                            }
	                            online++;
	                        }
	                    }
	                    drvices_html="<ul><li style='line-height: 20px;'>设备总数 [ <font style='color: red'>"+code.device_count+"</font> ]</li><li style='line-height: 20px;'>在线数 [ <font style='color: red'>"+online+"</font> ]&nbsp;&nbsp;&nbsp;离线数 [ <font style='color: red'>"+offline+"</font> ]&nbsp;&nbsp;&nbsp;异常数 [ <font style='color: red'>"+exd+"</font> ]</li></ul>";
	                }else{
	                    drvices_html="<ul><li>没有可用的设备</li></ul>";
	                }
	                $("#drvice_" + oid).html(drvices_html);
	            }
	        });
	    },
	    //初始标记
	    initMarker:function(mapy,mapx,title,oid){
	        var _this=this;
	        if(mapy==0&&mapx==0){
	        	this.map.setCity(title);
	        }else{
	        	_this.contextMenuPositon =new AMap.LngLat(mapy,mapx);
		        this.marker = new AMap.Marker({
		            map: this.map,
		            position: _this.contextMenuPositon ,
		            title:title,
		            draggable: false,
		            clickable:true,
		            cursor: 'pointer',
		            icon:"http://webapi.amap.com/images/marker_sprite.png",
		            raiseOnDrag: true
		        });
		        this.addpanel("<h3 class='title'>"+title+"</h3>",oid,this.marker);
		        this.map.setCenter([mapy,mapx]);
	        }
	    },
	    //异步请求类
	    ajaxRequest:function(o){
	        $.ajax({
	           	url:contextPath+o['url'],    
	           	type:"post",
	           	dataType:"json",
	           	async:true,
	           	success: function(result){  
	           		console.log("result="+result);
	           		o.callback(result);
	           	} 
	        });
	    },
	    //添加提示面板
	    addpanel:function(content,oid,marker){
	        var _this=this;
	        var infoWindow = new AMap.InfoWindow({
	            content: _this.createpanel(content,oid),
	            isCustom: true,
	            showShadow:true,
	            autoMove:true,
	            offset: new AMap.Pixel(0, -36)
	        });
	        var clickHandle = AMap.event.addListener(marker, 'click', function() {
	            infoWindow.open(_this.map, marker.getPosition());
	            _this.deviceRequest(oid);
	        });
	    },
	    //关闭窗体
	    closeWindows:function(){
	        var _this=this;
	        _this.map.clearInfoWindow();
	        $("#drvice_panel").slideUp(2000,function(){
	            $("#drvice_panel").html("");
	        });
	    },
	    //自定义框
	    createpanel:function(title,oid){
	        var _this=this;
	        var panelinfo = document.createElement("div");
	        panelinfo.setAttribute("style","width:220px;height:auto;border:1px solid #CCC;background: white; -moz-border-radius: 6px; -webkit-border-radius: 6px; border-radius: 6px;");

	        //top
	        var top = document.createElement("div");
	        var titleD = document.createElement("div");
	        var closeX = document.createElement("img");
	        top.setAttribute("style","position: relative; background: none repeat scroll 0 0 #F9F9F9;border-bottom: 1px solid #CCC;border-radius: 5px 5px 0 0;");
	        titleD.setAttribute("style","display: inline-block;color: #333333;font-size: 14px;font-weight: bold;line-height: 31px;padding: 0 10px;");
	        closeX.setAttribute("style","position: absolute;top: 10px;right: 10px;transition-duration: 0.25s;");
	        titleD.innerHTML = title;
	        closeX.src = "http://webapi.amap.com/images/close2.gif";
	        closeX.onclick = function(){_this.closeWindows()};
	        top.appendChild(titleD);
	        top.appendChild(closeX);
	        panelinfo.appendChild(top);

	        // 定义中部内容
	        var middle = document.createElement("div");
	        middle.setAttribute("style"," background:white;font-size: 12px;padding: 5px;line-height: 21px;");
	        middle.setAttribute("id","drvice_"+oid);
	        panelinfo.appendChild(middle);
	        return panelinfo;
	    },
	    //查询相关详细信息
	    showpart:function(drviceid,drvicename){

	        //var url="/webtrade?executor=trade&tradedriver=device&cmd=querydevicelist&busoffs_id="+oid;
	        //this.ajaxRequest({
	        //    url:url,
	        //    callback:function(o) {
	        //        var code = typeof o == 'string' ? JSON.decode(o) : o;
	        //
	        //    }
	        //});
	        //Load.html({
	        //    url:'pages/monitor/termdetail/view1.go?term_id='+drviceid,
	        //    update:$('drvice_panel'),
	        //    onSuccess:function(){
	        //        $("#drvice_panel").slideDown(2000);
	        //    }
	        //});
	    }
}
