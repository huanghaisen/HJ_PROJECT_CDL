
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
    this.isedit=true;
    //获取绑定控件
    if(this.option.mainChartDiv != null ){
        this.mainChartDiv = this.option.mainChartDiv;
    }
    if(this.option.orgtitle!=null){
        this.orgtitle=this.option.orgtitle;
    }
    if(this.option.center != null){
    	if(this.option.center[0]==null || this.option.center[0]==0){
    		this.centerflag=false;
    	}else{
    		this.centerflag=true;
    	}
    }else{
    	this.centerflag=false;
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
	       if(this.centerflag){
	    	   this.isedit=false;
	    	   $("#markbtn_add").hide();
	           $("#markbtn_save").hide();
	           $("#markbtn_edit").show();
	           $("#markbtn_rest").show();
	           $("#mapY").val(this.option.center[0]);
	           $("#mapX").val(this.option.center[1]);
	    	   this.map.setCenter(this.option.center);
	       }else{
	    	   this.isedit=true;
	    	   $("#markbtn_add").show();
	           $("#markbtn_save").hide();
	           $("#markbtn_edit").hide();
	           $("#markbtn_rest").hide();
	    	   this.map.setCity(this.city);
	       }
	    },
	  //加载
	    onLoad:function(){
	        var _this = this;
	        _this.start();
	        _this.mapClickListener();
	        _this.dataAssembly();
	        //$("#editpanel").panelleft(null,2,2);

	    },
	    //首次数据包装
	    dataAssembly:function(){
	        var _this=this;
	        if(this.centerflag){
	            this.initMarker(this.option.center,this.orgtitle);
	        }
	    },
	    //初始标记
	    initMarker:function(positem,title){
	        var _this=this;
	        console.log(positem[0]+"---"+positem[1]);
	        _this.contextMenuPositon =new AMap.LngLat(positem[0],positem[1]);
	        this.marker = new AMap.Marker({
	            map: this.map,
	            position: _this.contextMenuPositon ,
	            title:this.orgtitle,
	            draggable: false,
	            clickable:true,
	            cursor: 'move',
	            icon:"http://webapi.amap.com/images/marker_sprite.png",
	            raiseOnDrag: true
	        });
	       this.markerListener(this.marker);
	    },
	    //添加标记
	    addMarker:function(){
	        var _this=this;
	        this.isedit=false;
	        if(_this.contextMenuPositon!=null) {
	            this.marker = new AMap.Marker({
	                map: this.map,
	                position: _this.contextMenuPositon,
	                title: this.orgtitle,
	                draggable: true,
	                animation: "AMAP_ANIMATION_DROP",
	                clickable: true,
	                cursor: 'move',
	                icon: "http://webapi.amap.com/images/marker_sprite.png",
	                raiseOnDrag: true
	            });
	            this.markerListener(this.marker);
	            $("#markbtn_add").hide();
	            $("#markbtn_save").show();
	            $("#markbtn_edit").hide();
	        }else{
	            alert("请先确定坐标在点击增加按钮");
	        }
	    },
	    //修改坐标
	    editMarker:function(){
	        this.isedit=false;
	        //$("#markbtn_add").show();
	        $("#markbtn_save").show();
	        $("#markbtn_edit").hide();
	        this.marker.setDraggable(true);
	    },
	    //确认坐标
	    saveMarker:function(){
	        this.isedit=false;
	        //$("#markbtn_add").show();
	        $("#markbtn_save").hide();
	        $("#markbtn_edit").show();
	        this.marker.setDraggable(false);
	    },
	    //重置
	    restMarker:function(){
	        $("#mapY").val(this.option.center[0]);
	        $("#mapX").val(this.option.center[1]);
	        this.marker.setPosition(new AMap.LngLat(this.option.center[0],this.option.center[1]));
	    },
	    //异步请求类
	    ajaxRequest:function(url,data,fn){
	    	$.ajax({
	           	url:contextPath+url,  
	           	data:data,
	           	type:"post",
	           	dataType:"json",
	           	async:true,
	           	success: function(result){ 
	           		if(isFunction(fn)){
	           			fn(result);
	           		}
	           	} 
	        });
	    },

	    //地图点击监听
	    mapClickListener:function(){
	        var _this=this;
	        this.map.on( 'click', function(e) {
	            if (_this.isedit) {
	                $("#mapY").val(e.lnglat.getLng());
	                $("#mapX").val(e.lnglat.getLat());
	                _this.contextMenuPositon = e.lnglat;
	            }
	        });
	    },
	    //监听标记事件
	    markerListener:function(marker){
	        var _this=this;
	        marker.on("click", function (e) {
	            if(!this.getDraggable()){
	                alert("请先点击编辑按钮");
	            }
	        });
	        marker.on("dragend", function (e) {
	            $("#mapY").val(e.lnglat.getLng());
	            $("#mapX").val(e.lnglat.getLat());
	            this.setPosition(e.lnglat);
	            _this.marker = this;
	        });
	    }
}
var isFunction=function(a) {
    return "[object Function]" === Object.prototype.toString.call(a)
  }