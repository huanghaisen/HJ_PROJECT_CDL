/**
 * Created by daniel on 2015/9/14.
 */
var map_data=new Object();
map_data.item={
    "4403":"114.118691-22.543869",
    "4419":"113.746576-23.028995",
    "4401":"113.243662-23.120617"
}
map_data.datas="{'result':{'message':'操作成功','success':true},'count':5,'items':[{'mmark':'国贸地铁站','mtitle':'标题111111','oid':'212','maddress':'国贸地铁站','mx':'22.53968','mid':'1','my':'114.1189'},{'mmark':'老街地铁站','mtitle':'标题222222','oid':'212','maddress':'老街地铁站','mx':'22.54422','mid':'2','my':'114.11692'},{'mmark':'大剧院地铁站','mtitle':'标题333333','oid':'212','maddress':'大剧院地铁站','mx':'22.54115','mid':'3','my':'114.1082'},{'mmark':'科学馆地铁站','mtitle':'标题444444','oid':'212','maddress':'科学馆地铁站','mx':'22.54056','mid':'4','my':'114.09491'},{'mmark':'福田口岸地铁站','mtitle':'标题555555','oid':'212','maddress':'福田口岸地铁站','mx':'22.51573','mid':'5','my':'114.06924'}]}";
var kmap={
    zoom:14,//放大
    zooms:[11,20],
    lang:"zh",
    mapStyle:"normal",
    features:["bg","point","road","building"],
    isHotspot:true,
    mapmarker:null,//记录不同的地图标记
    options:{
        mainChartDiv:null //容器
    },
    //初始
    initialize:function(options){
        this.setOptions(options || {});
        this.option = this.options;
        this.map=null;
        this.marker=AMap.Marker;//标记
        this.icon=AMap.Icon;//图标
        this.Polyline=AMap.Polyline;//弧线
        this.infoWindow=AMap.InfoWindow;//窗体
        this.contextMenu=AMap.ContextMenu;//右侧菜单
        this.markerMenu=AMap.ContextMenu;//标记右侧菜单
        this.toolBar=AMap.ToolBar;
        this.mouseTool = AMap.MouseTool;
        this.contextMenu=null;
        this.contextMenuPositon=null;
        //获取绑定控件
        if(this.option.mainChartDiv != null ){
            this.mainChartDiv = $(this.option.mainChartDiv);
        }
    },
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
    },
    //加载
    onLoad:function(){
        var _this = this;
        _this.start();
        _this.mapClickListener();
        //处理数据异步加载
        this.URL = this.option['url'];//获取数据地址
        _this.dataAssembly(map_data.datas);
        jQuery("#editpanel").panelleft("container",5,5);



    },
    //首次数据包装
    dataAssembly:function(o){
        var _this=this;
        var code = typeof o == 'string' ? JSON.decode( o ): o ;
        if(code.result.success && code.count>0){
            for(var i=0;i<code.count;i++){
                //jQuery("#datas").append(code.items[i].oid+"<==>"+code.items[i].mtitle+"<==>"+code.items[i].mx+"<===>"+code.items[i].my+"</br>");
                this.addMarker(code.items[i].my+"-"+code.items[i].mx,code.items[i].mtitle);
            }
        }
    },
    //添加标记
    addMarker:function(positem,title){
        var _this=this;
        var positemarr=positem.split("-");
        this.marker = new AMap.Marker({
            map: this.map,
            position: positemarr,
            title:title,
            draggable: false,
            clickable:true,
            cursor: 'move',
            icon:"http://webapi.amap.com/images/marker_sprite.png",
            raiseOnDrag: true
        });
        this.marker.on("rightclick",function(e){
            _this.markerMenu.open(_this.map, e.lnglat);
            _this.marker=this;
        });
        //地图中添加地图操作ToolBar插件、鼠标工具MouseTool插件
        this.map.plugin(["AMap.ToolBar", "AMap.MouseTool"], function() {
            _this.toolBar=new AMap.ToolBar();
            _this.map.addControl(_this.toolBar);
            _this.mouseTool =new AMap.MouseTool(_this.map);
            //自定义右键菜单内容
            var menuContent = jQuery("<div></div>");
            menuContent.html("<ul style='margin: 2px; padding: 2px; list-style-type: none; position: relative; background-color: rgb(255, 255, 255); border: 1px solid rgb(175, 175, 175); border-top-left-radius: 3px; border-top-right-radius: 3px; border-bottom-right-radius: 3px; border-bottom-left-radius: 3px; box-shadow: rgb(153, 153, 153) 2px 2px 8px; white-space: nowrap; cursor: pointer;'>" +
                "<li style='text-indent: 0.5em; color:blue; font-size:13px;font-family:verdana;height: 20px; line-height: 20px; word-break: break-all; white-space: nowrap;'onclick='map.moveMarkerClock(1)'>标签解锁</li>" +
                "<li style='text-indent: 0.5em; color:blue; font-size:13px;font-family:verdana;height: 20px; line-height: 20px; word-break: break-all; white-space: nowrap;' onclick='map.moveMarkerClock(0)'>标签锁定</li></ul>");
            _this.markerMenu = new AMap.ContextMenu({content: menuContent.html()});//通过content自定义右键菜单内容
        });
    },
    //异步请求类
    ajaxRequest:function(o){
        var xhr = new Request({
            url:localhostUrl +  o['url'] ,
            onRequest:function(b){o['loading'] && o['loading'](b);},
            onSuccess:function(s){o.callback && o.callback(s);}
        }).send();
    },

    //地图点击监听
    mapClickListener:function(){
        var _this=this;
        this.map.on( 'rightclick', function(e){
            _this.contextMenu.open(_this.map, e.lnglat);
            _this.contextMenuPositon=e.lnglat;
        });

        //地图中添加地图操作ToolBar插件、鼠标工具MouseTool插件
        this.map.plugin(["AMap.ToolBar", "AMap.MouseTool"], function() {
            _this.toolBar=new AMap.ToolBar();
            _this.map.addControl(_this.toolBar);
            _this.mouseTool =new AMap.MouseTool(_this.map);
            //自定义右键菜单内容
            var menuContent = jQuery("<div></div>");
            menuContent.html("<ul style='margin: 2px; padding: 2px; list-style-type: none; position: relative; background-color: rgb(255, 255, 255); border: 1px solid rgb(175, 175, 175); border-top-left-radius: 3px; border-top-right-radius: 3px; border-bottom-right-radius: 3px; border-bottom-left-radius: 3px; box-shadow: rgb(153, 153, 153) 2px 2px 8px; white-space: nowrap; cursor: pointer;'>" +
                "<li style='text-indent: 0.5em; color:blue; font-size:13px;font-family:verdana;height: 20px; line-height: 20px; word-break: break-all; white-space: nowrap;'onclick='map.getMarkerCoordin()'>获取坐标</li>" +
                "<li style='text-indent: 0.5em; color:blue; font-size:13px;font-family:verdana;height: 20px; line-height: 20px; word-break: break-all; white-space: nowrap;'onclick='addMarkerMenu()'>添加标签</li>" +
                                "<li style='text-indent: 0.5em; color:blue; font-size:13px;font-family:verdana;height: 20px; line-height: 20px; word-break: break-all; white-space: nowrap;' onclick='map.moveMarkerClock()'>标签锁定</li></ul>");
            _this.contextMenu = new AMap.ContextMenu({content: menuContent.html()});//通过content自定义右键菜单内容
        });
    },

    //
    moveMarkerClock:function(o){
        if(o==0){
            this.marker.setDraggable(false);
        }else{
            this.marker.setDraggable(true);
        }
        this.marker.setMap(this.map);
        this.markerMenu.close();
    },

    //测试获取坐标值
    getMarkerCoordin:function(){
        jQuery("#map_y").val(this.contextMenuPositon.getLat());
        jQuery("#map_x").val(this.contextMenuPositon.getLng());
        this.contextMenu.close();
    }

};







